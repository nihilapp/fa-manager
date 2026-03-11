package dev.nihilncunia.fa_campaign_manager.domains.sessions.dto;

import dev.nihilncunia.fa_campaign_manager.common.annotation.SearchCondition;
import dev.nihilncunia.fa_campaign_manager.common.constant.SEARCH_TYPE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonInDto;
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
@Schema(description = "세션 로그 입력 및 검색 DTO")
public class SessionLogInDto extends CommonInDto {
  
  @NotNull(message = "세션 ID는 필수입니다.")
  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "대상 세션 ID")
  private Long sessionId;
  
  @NotNull(message = "작성자 ID는 필수입니다.")
  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "작성자 유저 ID")
  private Long userId;
  
  @NotBlank(message = "제목은 필수입니다.")
  @SearchCondition(type = SEARCH_TYPE.LIKE)
  @Schema(description = "로그 제목", example = "오크 습격전 기록")
  private String title;
  
  @Schema(description = "로그 내용")
  private String content;
  
  @Schema(description = "첨부 파일 URL (HTML, TXT)")
  private String fileUrl;
}
