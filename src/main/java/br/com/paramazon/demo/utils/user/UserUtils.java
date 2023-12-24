package br.com.paramazon.demo.utils.user;

import br.com.paramazon.demo.application.dto.user.profile.preferences.UserPofileBandSingerPreferenceDTO;
import br.com.paramazon.demo.application.dto.user.profile.preferences.UserProfilePreferenceDTO;
import br.com.paramazon.demo.application.dto.user.profile.UserProfileDTO;
import br.com.paramazon.demo.domain.model.show.band.Band;
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
    public static Set<UserProfilePreferenceDTO> buildBaseUserPreferenceList(Set<Band> preferences) {
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
     * Converte uma unica entidade Band para UserProfilePreferenceDTO.
     *
     * @param data A entidade do tipo Band a ser convertida.
     * @return Um UserProfilePreferenceDTO.
     */
    public static UserProfilePreferenceDTO convertUserPreferenceToDTO(Band data) {
        Set<UserPofileBandSingerPreferenceDTO> bandMemberList = data.getBandMembers().stream()
                                                         .map(member -> new UserPofileBandSingerPreferenceDTO(
                                                                 member.getUser().getUsername(),
                                                                 member.getUser().getPhotograph().getS3Key()))
                                                         .collect(Collectors.toSet());

        return new UserProfilePreferenceDTO(
                data.getIdBand(),
                data.getName(),
                data.getPhotograph().getS3Key(),
                bandMemberList,
                data.getDescription());
    }


}
