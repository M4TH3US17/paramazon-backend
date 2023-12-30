package br.com.paramazon.demo.utils.show.band;

import br.com.paramazon.demo.application.dto.show.band.BandMemberDTO;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.show.band.bandMember.BandMember;
import br.com.paramazon.demo.domain.model.show.band.bandMember.BandMemberId;
import br.com.paramazon.demo.domain.model.user.User;
import br.com.paramazon.demo.infrastructure.request.shows.band.RegisterBandMemberRequest;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BandMemberUtils {

    /**
     * Converte uma lista de entidades do tipo BandMember em uma lista de entidades do tipo BandMemberDTO.
     *
     * @param members Um conjunto de entidades BandMember.
     * @return Um conjunto de BandMemberDTO.
     */
    public static List<BandMemberDTO> buildBaseParticipantsList(List<BandMember> members) {
        return  members.stream()
                .map(member -> convertToDTO(member))
                .collect(Collectors.toList());
    }

    /**
     * Converte uma unica entidade de membro do tipo BandMember em BandMemberDTO.
     *
     * @param data Uma entidades BandMember.
     * @return Um BandMemberDTO.
     */
    public static BandMemberDTO convertToDTO(BandMember data) {
        return new BandMemberDTO(
               // data.getId(),
                data.getUser().getUsername(),
                data.getUser().getPhotograph().getS3Key(),
                data.getPayment());
    }

    public static Set<BandMember> makeBandMemberEntityListToPersist(Map<User, Double> paymentValues, Band band) {
        return paymentValues.entrySet().stream()
                .map(entry -> makeBandMemberEntityToPersist(entry.getKey(), entry.getValue(), band))
                .collect(Collectors.toSet());
    }

    public static BandMember makeBandMemberEntityToPersist(User member, Double paymentMember, Band band) {
        BandMemberId id = new BandMemberId(band.getIdBand(), member.getIdUser());

        return BandMember.builder()
                .id(id)
                .band(band)
                .user(member)
                .payment(paymentMember)
                .build();
    }

}
