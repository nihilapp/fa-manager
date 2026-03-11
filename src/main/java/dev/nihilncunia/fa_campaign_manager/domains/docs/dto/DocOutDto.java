package dev.nihilncunia.fa_campaign_manager.domains.docs.dto;

import dev.nihilncunia.fa_campaign_manager.common.dto.CommonOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.docs.constant.DOC_CATEGORY;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "세계관 문서 출력 DTO")
public class DocOutDto extends CommonOutDto {

  @Schema(description = "작성자 유저 ID")
  private Long userId;

  @Schema(description = "작성자 이름")
  private String userName;

  @Schema(description = "작성자 정보")
  private UserOutDto author;

  @Schema(description = "문서 제목")
  private String title;

  @Schema(description = "문서 설명")
  private String description;

  @Schema(description = "문서 카테고리 정보")
  private CategoryInfo categoryInfo;

  @Schema(description = "문서 본문 (JSON 문자열)")
  private String content;

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class CategoryInfo {
    private String code;
    private String korName;
  }
}
