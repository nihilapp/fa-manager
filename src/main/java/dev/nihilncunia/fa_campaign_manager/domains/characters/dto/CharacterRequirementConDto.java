package dev.nihilncunia.fa_campaign_manager.domains.characters.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "캐릭터 건강 제한 정보 DTO")
public class CharacterRequirementConDto {
  
  @Schema(description = "건강 8 제한")
  private String reqCon8;
  
  @Schema(description = "건강 10 제한")
  private String reqCon10;
  
  @Schema(description = "건강 12 제한")
  private String reqCon12;
  
  @Schema(description = "건강 14 제한")
  private String reqCon14;
  
  @Schema(description = "건강 16 제한")
  private String reqCon16;
  
  @Schema(description = "건강 18 제한")
  private String reqCon18;
  
  @Schema(description = "건강 20 제한")
  private String reqCon20;
}
