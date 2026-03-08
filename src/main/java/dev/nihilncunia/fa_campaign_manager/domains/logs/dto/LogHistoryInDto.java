package dev.nihilncunia.fa_campaign_manager.domains.logs.dto;

import dev.nihilncunia.fa_campaign_manager.common.annotation.SearchCondition;
import dev.nihilncunia.fa_campaign_manager.common.constant.SEARCH_TYPE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonInDto;
import dev.nihilncunia.fa_campaign_manager.domains.logs.constant.LOG_ACTION_TYPE;
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
@Schema(description = "로그 히스토리 입력 및 검색 DTO")
public class LogHistoryInDto extends CommonInDto {

  @Schema(description = "다건 처리를 위한 ID 리스트")
  private List<Long> idList;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "작업 수행자 유저 ID")
  private Long userId;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "대상 테이블 명", example = "users")
  private String tableName;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "대상 데이터 PK", example = "1")
  private Long targetId;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "작업 유형")
  private LOG_ACTION_TYPE actionType;

  @Schema(description = "작업 설명")
  private String description;
}
