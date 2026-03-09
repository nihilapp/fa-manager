package dev.nihilncunia.fa_campaign_manager.domains.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "토큰 정보 DTO")
public class TokenInfoDto {
  
  @Schema(description = "액세스 토큰")
  private String accessToken;
  
  @Schema(description = "리프레시 토큰")
  private String refreshToken;
}
