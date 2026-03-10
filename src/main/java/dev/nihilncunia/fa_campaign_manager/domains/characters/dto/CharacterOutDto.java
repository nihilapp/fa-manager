package dev.nihilncunia.fa_campaign_manager.domains.characters.dto;

import dev.nihilncunia.fa_campaign_manager.common.dto.CommonOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.constant.CHARACTER_STATUS;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "캐릭터 출력 DTO")
public class CharacterOutDto extends CommonOutDto {

  @Schema(description = "캐릭터 이름")
  private String name;

  @Schema(description = "캐릭터 상태")
  private CHARACTER_STATUS status;

  @Schema(description = "종족")
  private String race;

  @Schema(description = "시작 레벨")
  private Integer startLevel;

  @Schema(description = "시작 경험치")
  private Integer startExp;

  @Schema(description = "시작 소지 자금")
  private CharacterCurrencyDto startCurrency;

  @Schema(description = "현재 레벨")
  private Integer currentLevel;

  @Schema(description = "현재 경험치")
  private Integer currentExp;

  @Schema(description = "현재 소지 자금")
  private CharacterCurrencyDto currentCurrency;

  @Schema(description = "장비 정보")
  private CharacterEquipmentDto equipment;

  @Schema(description = "힘/민첩 제한 아이템 정보")
  private CharacterRequirementStrDexDto requirementStrDex;

  @Schema(description = "건강 제한 아이템 정보")
  private CharacterRequirementConDto requirementCon;

  @Schema(description = "클래스 정보 목록")
  private List<CharacterClassDto> classes;
}
