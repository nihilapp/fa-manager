package dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto;

import dev.nihilncunia.fa_campaign_manager.common.annotation.SearchCondition;
import dev.nihilncunia.fa_campaign_manager.common.constant.SEARCH_TYPE;
import dev.nihilncunia.fa_campaign_manager.common.constant.STATUS_CODE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonInDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "캠페인 입력 및 검색 DTO")
public class CampaignInDto extends CommonInDto {

  @Schema(description = "다건 처리를 위한 ID 리스트")
  private List<Long> idList;

  @SearchCondition(type = SEARCH_TYPE.LIKE)
  @NotBlank(message = "캠페인 명은 필수입니다.")
  @Schema(description = "캠페인 명", example = "검은 날개의 여정")
  private String name;

  @Schema(description = "캠페인 설명")
  private String description;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "캠페인 상태")
  private STATUS_CODE status;

  @SearchCondition(type = SEARCH_TYPE.GREATER_THAN_OR_EQUAL)
  @Schema(description = "검색 시작일 기준 (이후)", example = "2026-03-01T00:00:00.000+09:00")
  private OffsetDateTime startDate;

  @Schema(description = "캠페인 종료일")
  private OffsetDateTime endDate;
}
