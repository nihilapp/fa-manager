package dev.nihilncunia.fa_campaign_manager.domains.characters.dto;

import dev.nihilncunia.fa_campaign_manager.common.dto.CommonOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.constant.CHARACTER_STATUS;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

  @Schema(description = "현재 레벨")
  private Integer currentLevel;

  @Schema(description = "시작 경험치")
  private Integer startExp;

  @Schema(description = "현재 경험치")
  private Integer currentExp;

  @Schema(description = "시작 소지 자금")
  private Integer startCurrency;

  @Schema(description = "현재 소지 자금")
  private Integer currentCurrency;

  @Schema(description = "소속 캠페인 정보")
  private CampaignInfo campaignInfo;

  @Schema(description = "소유자 유저 정보")
  private UserInfo userInfo;

  @Schema(description = "클래스 목록")
  private List<CharacterClassList> characterClassList;

  @Schema(description = "세션 참여 이력")
  private List<SessionHistoryList> sessionHistoryList;

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class CharacterClassList {
    private String className;
    private String subClass;
    private Integer level;
  }

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class CampaignInfo {
    private Long id;
    private String name;
    private String status;
  }

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class UserInfo {
    private Long id;
    private String name;
    private String email;
  }

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class SessionHistoryList {
    private Long sessionId;
    private String sessionName;
    private Integer sessionNo;
    private OffsetDateTime playDate;
    private Integer earnedExp;
    private Integer earnedGold;
  }
}
