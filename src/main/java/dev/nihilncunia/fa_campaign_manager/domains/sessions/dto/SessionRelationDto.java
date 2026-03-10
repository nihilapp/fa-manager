package dev.nihilncunia.fa_campaign_manager.domains.sessions.dto;

import dev.nihilncunia.fa_campaign_manager.common.constant.SESSION_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.constant.STATUS_CODE;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "세션 관계 정보 DTO (관계 컬럼 제외)")
public class SessionRelationDto {
  @Schema(description = "세션 ID")
  private Long id;
  
  @Schema(description = "세션 번호")
  private Integer no;
  
  @Schema(description = "세션 이름")
  private String name;
  
  @Schema(description = "세션 설명")
  private String description;
  
  @Schema(description = "최대 플레이어 수")
  private Integer maxPlayer;
  
  @Schema(description = "보상 경험치")
  private Integer rewardExp;
  
  @Schema(description = "보상 골드")
  private Integer rewardGold;
  
  @Schema(description = "세션 진행 상태")
  private STATUS_CODE status;
  
  @Schema(description = "세션 시작 시간")
  private OffsetDateTime playDate;

  @Schema(description = "사용자 역할 (MASTER/PLAYER)")
  private SESSION_ROLE sessionRole;
}
