package br.com.paramazon.demo.utils.user;

import br.com.paramazon.demo.application.dto.user.profile.preferences.UserBandSingerPreferenceDTO;
import br.com.paramazon.demo.application.dto.user.profile.preferences.UserPreferenceDTO;
import br.com.paramazon.demo.application.dto.user.profile.UserProfileDTO;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.show.band.bandSinger.BandSinger;
import br.com.paramazon.demo.domain.model.user.User;
import br.com.paramazon.demo.utils.Utils;
import br.com.paramazon.demo.utils.media.MediaUtils;
import br.com.paramazon.demo.utils.user.role.RoleUtils;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtils {

    /**
     * Converte uma lista de entidades User em uma lista de UserProfileDTO.
     *
     * @param users Um conjunto de entidades usuario do tipo User.
     * @return Um conjunto de UserProfileDTO.
     */
    public static List<UserProfileDTO> buildBaseUserList(List<User> users) {
        return users.stream()
                .map(user -> convertUserToDTO(user))
                .collect(Collectors.toList());
    }

    /**
     * Converte uma lista de entidades bandas para uma lista de DTO de preferências de usuário.
     *
     * @param preferences Um conjunto de entidades banda.
     * @return Um conjunto de DTO de preferências de usuário.
     */
    public static Set<UserPreferenceDTO> buildBaseUserPreferenceList(Set<Band> preferences) {
        return preferences.stream()
                .map(band -> convertUserPreferenceToDTO(band))
                .collect(Collectors.toSet());
    }

    /**
     * Converte uma unica entidade User em um UserProfileDTO.
     *
     * @param data A entidade do tipo User a ser convertida.
     * @return Um UserProfileDTO de usuário.
     */
    public static UserProfileDTO convertUserToDTO(User data) {
        return new UserProfileDTO(
                data.getIdUser(),
                data.getUsername(),
                MediaUtils.convertToDTO(data.getPhotograph()),
                Utils.convertValidationEmailToDTO(data.getEmail()),
                UserUtils.buildBaseUserPreferenceList(data.getPreferences()),
                RoleUtils.buildBaseRoleList(data.getRole()));
    }

    /**
     * Converte uma unica entidade Band para UserPreferenceDTO.
     *
     * @param data A entidade do tipo Band a ser convertida.
     * @return Um UserPreferenceDTO.
     */
    public static UserPreferenceDTO convertUserPreferenceToDTO(Band data) {
        return new UserPreferenceDTO(
                data.getIdBand(),
                data.getName(),
                MediaUtils.convertToDTO(data.getPhotograph()),
                convertListOfBandMembersToDTO(data.getBandSingers()),
                data.getDescription());
    }

    /**
     * Converte a coleção de membros de uma banda (preferida do usuario) para um conjunto de DTOs UserBandSingerPreferenceDTO.
     *
     * @param bandMembers A coleção de entidades de usuário representando os membros da banda.
     * @return Um conjunto de DTOs UserBandSingerPreferenceDTO.
     */
    private static Set<UserBandSingerPreferenceDTO> convertListOfBandMembersToDTO(Set<BandSinger> bandMembers) {
        return bandMembers.stream()
                .map(member -> new UserBandSingerPreferenceDTO(
                        member.getUser().getUsername(),
                        MediaUtils.convertToDTO(member.getUser().getPhotograph())))
                .collect(Collectors.toSet());
    }


}
