package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignInDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterMapper;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.SessionMapper;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserMapper;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { SessionMapper.class,
  CharacterMapper.class, UserMapper.class })
public interface CampaignMapper {
  CampaignEntity toEntity(CampaignInDto campaignInDto);
  
  @Mapping(target = "sessionList", source = "sessionList", qualifiedByName = "toSimpleDto")
  @Mapping(target = "characterList", source = "characterList", qualifiedByName = "toSimpleDto")
  @Mapping(target = "memberList", source = "memberList")
  CampaignOutDto toDto(CampaignEntity campaignEntity);
  
  List<CampaignOutDto> toDtoList(List<CampaignEntity> campaignEntityList);
  
  /**
   * CampaignMemberEntity 를 UserOutDto 로 매핑합니다.
   * 엔티티의 role(MEMBER_ROLE)이 아닌 연관된 user 의 정보를 직접 매핑하여 충돌을 방지합니다.
   */
  @Mapping(target = "role", source = "user.role")
  @Mapping(target = "name", source = "user.name")
  @Mapping(target = "discordId", source = "user.discordId")
  @Mapping(target = "email", source = "user.email")
  @Mapping(target = "id", source = "user.id")
  @Mapping(target = "deleteYn", source = "user.deleteYn")
  @Mapping(target = "createDate", source = "user.createDate")
  @Mapping(target = "updateDate", source = "user.updateDate")
  @Mapping(target = "characterList", ignore = true)
  @Mapping(target = "campaignList", ignore = true)
  @Mapping(target = "sessionList", ignore = true)
  UserOutDto toUserOutDto(CampaignMemberEntity campaignMemberEntity);
  
  /**
   * DTO 데이터를 기반으로 기존 엔티티를 업데이트합니다.
   *
   * @param campaignInDto  소스 데이터 (입력 DTO)
   * @param campaignEntity 업데이트 대상 (기존 영속성 엔티티)
   * @MappingTarget 어노테이션은 새로운 객체를 생성하지 않고, 두 번째 파라미터로 전달된 '기존 객체'를 수정하겠다는 뜻입니다.
   * MapStruct는 컴파일 시점에 DTO의 필드 값들을 엔티티의 setter를 호출하여 복사하는 코드를 자동으로
   * 생성해줍니다.
   */
  void updateEntityFromDto(CampaignInDto campaignInDto, @MappingTarget CampaignEntity campaignEntity);
  
  /**
   * CampaignMemberEntity 리스트에서 UserOutDto 리스트(Simple)를 추출합니다.
   * UserMapper 의 toSimpleDto 를 사용하여 모든 필드를 완벽하게 채웁니다.
   */
  default List<UserOutDto> mapCampaignMemberListToUserOutDtoList(List<CampaignMemberEntity> memberList, @Context UserMapper userMapper) {
    if (memberList == null || userMapper == null) {
      return null;
    }
    return memberList.stream()
      .map(CampaignMemberEntity::getUser)
      .map(userMapper::toSimpleDto)
      .toList();
  }
}
