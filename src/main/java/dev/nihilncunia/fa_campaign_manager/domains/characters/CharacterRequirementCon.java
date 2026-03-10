package dev.nihilncunia.fa_campaign_manager.domains.characters;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterRequirementCon {

  @Column(length = 100)
  @Schema(description = "건강 8 제한")
  private String reqCon8;

  @Column(length = 100)
  @Schema(description = "건강 10 제한")
  private String reqCon10;

  @Column(length = 100)
  @Schema(description = "건강 12 제한")
  private String reqCon12;

  @Column(length = 100)
  @Schema(description = "건강 14 제한")
  private String reqCon14;

  @Column(length = 100)
  @Schema(description = "건강 16 제한")
  private String reqCon16;

  @Column(length = 100)
  @Schema(description = "건강 18 제한")
  private String reqCon18;

  @Column(length = 100)
  @Schema(description = "건강 20 제한")
  private String reqCon20;
}
