package dev.nihilncunia.fa_campaign_manager.domains.characters.dto;

import dev.nihilncunia.fa_campaign_manager.common.annotation.SearchCondition;
import dev.nihilncunia.fa_campaign_manager.common.constant.SEARCH_TYPE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonInDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.constant.CHARACTER_STATUS;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "캐릭터 입력 및 검색 DTO")
public class CharacterInDto extends CommonInDto {
  
  @Schema(description = "다건 처리를 위한 ID 리스트")
  private List<Long> idList;
  
  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "소유자 유저 ID")
  private Long userId;
  
  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "소속 캠페인 ID")
  private Long campaignId;
  
  @SearchCondition(type = SEARCH_TYPE.LIKE)
  @NotBlank(message = "캐릭터 이름은 필수입니다.")
  @Schema(description = "캐릭터 이름", example = "가츠")
  private String name;
  
  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "캐릭터 상태")
  private CHARACTER_STATUS status;
  
  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "종족", example = "Human")
  private String race;
  
  @Schema(description = "시작 레벨")
  private Integer startLevel;
  
  @Schema(description = "시작 경험치")
  private Integer startExp;
  
  @Schema(description = "시작 소지 자금")
  private Integer startCurrency;
  
  @Schema(description = "주무장", example = "장검")
  private String mainHand;
  
  @Schema(description = "보조무장", example = "목재 방패")
  private String offHand;
  
  @Schema(description = "갑옷", example = "가죽 갑옷")
  private String armor;
  
  @Schema(description = "머리", example = "철 투구")
  private String head;
  
  @Schema(description = "장갑", example = "가죽 장갑")
  private String gauntlet;
  
  @Schema(description = "부츠", example = "가죽 부츠")
  private String boots;
  
  @Schema(description = "벨트", example = "가죽 벨트")
  private String belt;
  
  @Schema(description = "망토", example = "여행자의 망토")
  private String cloak;
  
  @Schema(description = "기타 장비 1", example = "은반지")
  private String accessory1;
  
  @Schema(description = "기타 장비 2")
  private String accessory2;
  
  @Schema(description = "기타 장비 3")
  private String accessory3;
  
  @Schema(description = "기타 장비 4")
  private String accessory4;
  
  @Schema(description = "힘/민첩 8제한 (벨트/전낭)")
  private String reqStrDex8;
  
  @Schema(description = "힘/민첩 10제한 (벨트/전낭)")
  private String reqStrDex10;
  
  @Schema(description = "힘/민첩 12제한 (벨트/전낭)")
  private String reqStrDex12;
  
  @Schema(description = "힘/민첩 14제한 (벨트/전낭)")
  private String reqStrDex14;
  
  @Schema(description = "힘 16 제한 (벨트/전낭)")
  private String reqStr16;
  
  @Schema(description = "힘 18 제한 (벨트/전낭)")
  private String reqStr18;
  
  @Schema(description = "힘 20 제한 (벨트/전낭)")
  private String reqStr20;
  
  @Schema(description = "건강 8 제한 (배낭/탈것)")
  private String reqCon8;
  
  @Schema(description = "건강 10 제한 (배낭/탈것)")
  private String reqCon10;
  
  @Schema(description = "건강 12 제한 (배낭/탈것)")
  private String reqCon12;
  
  @Schema(description = "건강 14 제한 (배낭/탈것)")
  private String reqCon14;
  
  @Schema(description = "건강 16 제한 (배낭/탈것)")
  private String reqCon16;
  
  @Schema(description = "건강 18 제한 (배낭/탈것)")
  private String reqCon18;
  
  @Schema(description = "건강 20 제한 (배낭/탈것)")
  private String reqCon20;
}
