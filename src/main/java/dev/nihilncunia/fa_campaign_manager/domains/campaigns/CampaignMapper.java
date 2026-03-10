package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignInDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterMapper;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.SessionMapper;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserMapper;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { SessionMapper.class,
    CharacterMapper.class, UserMapper.class })
public interface CampaignMapper {
  CampaignEntity toEntity(CampaignInDto campaignInDto);

  CampaignOutDto toDto(CampaignEntity campaignEntity);

  List<CampaignOutDto> toDtoList(List<CampaignEntity> campaignEntityList);

  /**
   * DTO 데이터를 기반으로 기존 엔티티를 업데이트합니다.
   * 
   * @MappingTarget 어노테이션은 새로운 객체를 생성하지 않고, 두 번째 파라미터로 전달된 '기존 객체'를 수정하겠다는 뜻입니다.
   *                MapStruct는 컴파일 시점에 DTO의 필드 값들을 엔티티의 setter를 호출하여 복사하는 코드를 자동으로
   *                생성해줍니다.
   * 
   * @param campaignInDto  소스 데이터 (입력 DTO)
   * @param campaignEntity 업데이트 대상 (기존 영속성 엔티티)
   */
  void updateEntityFromDto(CampaignInDto campaignInDto, @MappingTarget CampaignEntity campaignEntity);

  /**
   * 중첩 객체인 CampaignMemberEntity 에서 User를 추출하여 UserOutDto 로 매핑합니다.
   * MapStruct는 members(CampaignMemberEntity 리스트) 를 members(UserOutDto 리스트) 로 변환할
   * 때 이 메서드를 사용합니다.
   */
  default UserOutDto mapCampaignMemberToUserOutDto(CampaignMemberEntity member) {
    if (member == null || member.getUser() == null) {
      return null;
    }
    return UserOutDto.builder()
        .id(member.getUser().getId())
        .name(member.getUser().getName())
        .email(member.getUser().getEmail())
        .role(member.getUser().getRole())
        .discordId(member.getUser().getDiscordId())
        .loginFailureCount(member.getUser().getLoginFailureCount())
        .lockYn(member.getUser().getLockYn())
        .lastSignInDate(member.getUser().getLastSignInDate())
        .lastPasswordChangeDate(member.getUser().getLastPasswordChangeDate())
        .campaignRole(member.getRole())
        .useYn(member.getUser().getUseYn())
        .deleteYn(member.getUser().getDeleteYn())
        .creatorId(member.getUser().getCreatorId())
        .createDate(member.getUser().getCreateDate())
        .updaterId(member.getUser().getUpdaterId())
        .updateDate(member.getUser().getUpdateDate())
        .deleterId(member.getUser().getDeleterId())
        .deleteDate(member.getUser().getDeleteDate())
        .build();
  }
}
