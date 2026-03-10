package dev.nihilncunia.fa_campaign_manager.domains.characters.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "캐릭터 힘/민첩 제한 정보 DTO")
public class CharacterRequirementStrDexDto {

  @Schema(description = "힘/민첩 8 제한")
  private String reqStrDex8;

  @Schema(description = "힘/민첩 10 제한")
  private String reqStrDex10;

  @Schema(description = "힘/민첩 12 제한")
  private String reqStrDex12;

  @Schema(description = "힘/민첩 14 제한")
  private String reqStrDex14;

  @Schema(description = "힘 16 제한")
  private String reqStr16;

  @Schema(description = "힘 18 제한")
  private String reqStr18;

  @Schema(description = "힘 20 제한")
  private String reqStr20;
}
