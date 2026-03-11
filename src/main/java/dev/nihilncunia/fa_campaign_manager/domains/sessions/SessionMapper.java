package dev.nihilncunia.fa_campaign_manager.domains.sessions;

import dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterMapper;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.dto.SessionOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserMapper;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.CharacterOutDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { UserMapper.class,
  CharacterMapper.class })
public interface SessionMapper {
  @Mapping(target = "playerList", source = "playerList")
  @Mapping(target = "characterList", source = "playerList")
  SessionOutDto toDto(SessionEntity sessionEntity);
  
  @Named("toSimpleDto")
  @Mapping(target = "playerList", ignore = true)
  @Mapping(target = "characterList", ignore = true)
  SessionOutDto toSimpleDto(SessionEntity sessionEntity);
  
  List<SessionOutDto> toDtoList(List<SessionEntity> sessionEntityList);
  
  /**
   * SessionPlayerEntity 를 UserOutDto 로 매핑합니다.
   * 엔티티의 role(SESSION_ROLE)이 아닌 연관된 user 의 정보를 직접 매핑하여 충돌을 방지합니다.
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
  UserOutDto toUserOutDto(SessionPlayerEntity sessionPlayerEntity);
  
  /**
   * SessionPlayerEntity 리스트에서 UserOutDto 리스트를 추출합니다.
   * UserMapper 의 toSimpleDto 를 호출하여 모든 필드를 채웁니다.
   */
  default List<UserOutDto> mapPlayers(List<SessionPlayerEntity> playerList, @Context UserMapper userMapper) {
    if (playerList == null || userMapper == null) return null;
    return playerList.stream()
      .map(SessionPlayerEntity::getUser)
      .map(userMapper::toSimpleDto)
      .toList();
  }
  
  /**
   * SessionPlayerEntity 리스트에서 CharacterOutDto 리스트를 추출합니다.
   * CharacterMapper 의 toSimpleDto 를 호출하여 모든 필드를 채웁니다.
   */
  default List<CharacterOutDto> mapCharacters(List<SessionPlayerEntity> playerList, @Context CharacterMapper characterMapper) {
    if (playerList == null || characterMapper == null) return null;
    return playerList.stream()
      .map(SessionPlayerEntity::getCharacter)
      .map(characterMapper::toSimpleDto)
      .toList();
  }
}
