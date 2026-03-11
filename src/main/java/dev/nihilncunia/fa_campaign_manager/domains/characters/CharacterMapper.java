package dev.nihilncunia.fa_campaign_manager.domains.characters;

import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.CharacterInDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.*;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.SessionPlayerEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CharacterMapper {
  
  @Mapping(target = "currentLevel", ignore = true)
  @Mapping(target = "currentExp", ignore = true)
  @Mapping(target = "currentCurrency", ignore = true)
  CharacterOutDto toDto(CharacterEntity characterEntity);
  
  @Named("toSimpleDto")
  @Mapping(target = "sessionList", ignore = true)
  @Mapping(target = "currentLevel", ignore = true)
  @Mapping(target = "currentExp", ignore = true)
  @Mapping(target = "currentCurrency", ignore = true)
  CharacterOutDto toSimpleDto(CharacterEntity characterEntity);
  
  List<CharacterOutDto> toDtoList(List<CharacterEntity> characterEntityList);
  
  CharacterEntity toEntity(CharacterInDto characterInDto);
  
  /**
   * CharacterInDto 의 startCurrency(Integer) 를 CharacterCurrency 객체로 변환합니다.
   * 기본적으로 입력된 값을 Gold 필드에 할당합니다.
   */
  default CharacterCurrency mapStartCurrency(Integer value) {
    if (value == null) return null;
    return CharacterCurrency.builder()
      .gold(value)
      .build();
  }
  
  // 중첩 객체 매핑
  CharacterCurrencyDto toDto(CharacterCurrency currency);
  
  CharacterEquipmentDto toDto(CharacterEquipment equipment);
  
  CharacterRequirementStrDexDto toDto(CharacterRequirementStrDex req);
  
  CharacterRequirementConDto toDto(CharacterRequirementCon req);
  
  CharacterClassDto toDto(CharacterClass characterClass);
  
  /**
   * 매핑 후 current 필드들을 계산하여 채웁니다.
   */
  @AfterMapping
  default void calculateCurrentStats(CharacterEntity entity, @MappingTarget CharacterOutDto dto) {
    if (entity == null || dto == null) return;
    
    // 1. 경험치 합산
    int totalExp = entity.getStartExp();
    int totalGold = 0;
    
    if (entity.getSessionList() != null) {
      for (SessionPlayerEntity participation : entity.getSessionList()) {
        if (participation.getSession() != null) {
          totalExp += ( participation.getSession().getRewardExp() != null ) ? participation.getSession().getRewardExp() : 0;
          totalGold += ( participation.getSession().getRewardGold() != null ) ? participation.getSession().getRewardGold() : 0;
        }
      }
    }
    
    dto.setCurrentExp(totalExp);
    // 레벨 계산 로직 (임시로 startLevel 사용, 필요시 공식 추가 가능)
    dto.setCurrentLevel(entity.getStartLevel());
    
    // 2. 자금 합산 (골드 보상을 Gold 필드에 합산)
    if (entity.getStartCurrency() != null) {
      dto.setCurrentCurrency(CharacterCurrencyDto.builder()
        .copper(entity.getStartCurrency().getCopper())
        .silver(entity.getStartCurrency().getSilver())
        .electrum(entity.getStartCurrency().getElectrum())
        .gold(entity.getStartCurrency().getGold() + totalGold)
        .platinum(entity.getStartCurrency().getPlatinum())
        .build());
    }
  }
}
