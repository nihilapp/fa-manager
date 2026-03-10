package dev.nihilncunia.fa_campaign_manager.domains.characters.dto;

import dev.nihilncunia.fa_campaign_manager.domains.characters.constant.CHARACTER_STATUS;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "캐릭터 관계 정보 DTO (관계 컬럼 제외)")
public class CharacterRelationDto {
  @Schema(description = "캐릭터 ID")
  private Long id;

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
  private Integer startCurrency;

  @Schema(description = "주무장")
  private String mainHand;

  @Schema(description = "보조무장")
  private String offHand;

  @Schema(description = "갑옷")
  private String armor;

  @Schema(description = "머리")
  private String head;

  @Schema(description = "장갑")
  private String gauntlet;

  @Schema(description = "부츠")
  private String boots;

  @Schema(description = "벨트")
  private String belt;

  @Schema(description = "망토")
  private String cloak;

  @Schema(description = "기타 장비 1")
  private String accessory1;

  @Schema(description = "기타 장비 2")
  private String accessory2;

  @Schema(description = "기타 장비 3")
  private String accessory3;

  @Schema(description = "기타 장비 4")
  private String accessory4;

  @Schema(description = "힘/민첩 8제한")
  private String reqStrDex8;

  @Schema(description = "힘/민첩 10제한")
  private String reqStrDex10;

  @Schema(description = "힘/민첩 12제한")
  private String reqStrDex12;

  @Schema(description = "힘/민첩 14제한")
  private String reqStrDex14;

  @Schema(description = "힘 16 제한")
  private String reqStr16;

  @Schema(description = "힘 18 제한")
  private String reqStr18;

  @Schema(description = "힘 20 제한")
  private String reqStr20;

  @Schema(description = "건강 8 제한")
  private String reqCon8;

  @Schema(description = "건강 10 제한")
  private String reqCon10;

  @Schema(description = "건강 12 제한")
  private String reqCon12;

  @Schema(description = "건강 14 제한")
  private String reqCon14;

  @Schema(description = "건강 16 제한")
  private String reqCon16;

  @Schema(description = "건강 18 제한")
  private String reqCon18;

  @Schema(description = "건강 20 제한")
  private String reqCon20;

  @Schema(description = "클래스 정보 목록")
  private List<CharacterClassDto> classes;
}
