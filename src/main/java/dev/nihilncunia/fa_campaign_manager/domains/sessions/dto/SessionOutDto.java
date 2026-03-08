package dev.nihilncunia.fa_campaign_manager.domains.sessions.dto;

import dev.nihilncunia.fa_campaign_manager.common.constant.STATUS_CODE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonOutDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "세션 출력 DTO")
public class SessionOutDto extends CommonOutDto {

  @Schema(description = "세션 번호")
  private Integer no;

  @Schema(description = "세션 이름")
  private String name;

  @Schema(description = "상태")
  private STATUS_CODE status;

  @Schema(description = "플레이 일시")
  private OffsetDateTime playDate;

  @Schema(description = "보상 경험치")
  private Integer rewardExp;

  @Schema(description = "보상 골드")
  private Integer rewardGold;

  @Schema(description = "소속 캠페인 정보")
  private CampaignInfo campaignInfo;

  @Schema(description = "참여 플레이어 목록")
  private List<SessionPlayerList> sessionPlayerList;

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class CampaignInfo {
    private Long id;
    private String name;
    private String status;
  }

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class SessionPlayerList {
    private Long userId;
    private String userName;
    private Long characterId;
    private String characterName;
    private String role;
  }
}
