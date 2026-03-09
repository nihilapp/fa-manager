package dev.nihilncunia.fa_campaign_manager.domains.sessions.dto;

import dev.nihilncunia.fa_campaign_manager.common.dto.CommonOutDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "세션 로그 출력 DTO")
public class SessionLogOutDto extends CommonOutDto {

  @Schema(description = "대상 세션 ID")
  private Long sessionId;

  @Schema(description = "대상 세션 이름")
  private String sessionName;

  @Schema(description = "작성자 유저 ID")
  private Long userId;

  @Schema(description = "작성자 유저 이름")
  private String userName;

  @Schema(description = "로그 제목")
  private String title;

  @Schema(description = "로그 내용")
  private String content;

  @Schema(description = "첨부 파일 URL (HTML, TXT)")
  private String fileUrl;
}
