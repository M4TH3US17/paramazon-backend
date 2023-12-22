package br.com.paramazon.demo.utils.user;

import br.com.paramazon.demo.application.dto.UserProfileDTO;
import br.com.paramazon.demo.domain.model.user.User;
import br.com.paramazon.demo.utils.Utils;
import br.com.paramazon.demo.utils.media.MediaUtils;
import br.com.paramazon.demo.utils.show.band.BandUtils;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtils {

    public static List<UserProfileDTO> buildBaseUserList(List<User> users) {
        return users.stream()
                .map(user -> convertToDTO(user))
                .collect(Collectors.toList());
    }

    public static UserProfileDTO convertToDTO(User data) {
        return UserProfileDTO.builder()
                .username(data.getUsername())
                .email(Utils.convertValidationEmailToDTO(data.getEmail()))
                .photograph(MediaUtils.convertToDTO(data.getPhotograph()))
                .preferences(BandUtils.buildBaseBandList(data.getPreferences()))
                .build();
    }
}
