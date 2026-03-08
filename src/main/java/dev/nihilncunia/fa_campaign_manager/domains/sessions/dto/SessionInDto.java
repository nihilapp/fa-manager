package dev.nihilncunia.fa_campaign_manager.domains.sessions.dto;

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
@Schema(description = "세션 입력 및 검색 DTO")
public class SessionInDto extends CommonInDto {

  @Schema(description = "다건 처리를 위한 ID 리스트")
  private List<Long> idList;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "소속 캠페인 ID")
  private Long campaignId;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "세션 번호")
  private Integer no;

  @SearchCondition(type = SEARCH_TYPE.LIKE)
  @NotBlank(message = "세션 이름은 필수입니다.")
  @Schema(description = "세션 이름", example = "검은 숲의 위협")
  private String name;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "세션 상태")
  private STATUS_CODE status;

  @Schema(description = "플레이 일시")
  private OffsetDateTime playDate;

  @Schema(description = "최대 플레이어 수")
  private Integer maxPlayer;

  @Schema(description = "보상 경험치")
  private Integer rewardExp;

  @Schema(description = "보상 골드")
  private Integer rewardGold;
}
