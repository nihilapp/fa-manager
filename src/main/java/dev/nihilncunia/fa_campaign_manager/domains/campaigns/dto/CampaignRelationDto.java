package dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto;

import dev.nihilncunia.fa_campaign_manager.common.constant.STATUS_CODE;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "캠페인 관계 정보 DTO (관계 컬럼 제외)")
public class CampaignRelationDto {
  @Schema(description = "캠페인 ID")
  private Long id;
  
  @Schema(description = "캠페인 이름")
  private String name;
  
  @Schema(description = "캠페인 설명")
  private String description;
  
  @Schema(description = "캠페인 상태")
  private STATUS_CODE status;
  
  @Schema(description = "캠페인 시작일")
  private OffsetDateTime startDate;
  
  @Schema(description = "캠페인 종료일")
  private OffsetDateTime endDate;
}
