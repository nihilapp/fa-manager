package dev.nihilncunia.fa_campaign_manager.domains.docs.dto;

import dev.nihilncunia.fa_campaign_manager.common.annotation.SearchCondition;
import dev.nihilncunia.fa_campaign_manager.common.constant.SEARCH_TYPE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonInDto;
import dev.nihilncunia.fa_campaign_manager.domains.docs.constant.DOC_CATEGORY;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "세계관 문서 입력 및 검색 DTO")
public class DocInDto extends CommonInDto {

  @NotNull(message = "작성자 ID는 필수입니다.")
  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "작성자 유저 ID")
  private Long userId;

  @NotBlank(message = "제목은 필수입니다.")
  @SearchCondition(type = SEARCH_TYPE.LIKE)
  @Schema(description = "문서 제목", example = "제국 건국사")
  private String title;

  @SearchCondition(type = SEARCH_TYPE.LIKE)
  @Schema(description = "문서 설명", example = "제국의 기원에 대한 기록")
  private String description;

  @NotNull(message = "카테고리는 필수입니다.")
  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "문서 카테고리")
  private DOC_CATEGORY category;

  @Schema(description = "문서 본문 (JSON 문자열)")
  private String content;
}
