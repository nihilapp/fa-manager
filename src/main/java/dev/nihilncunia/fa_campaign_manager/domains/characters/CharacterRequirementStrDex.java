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
public class CharacterRequirementStrDex {

  @Column(length = 100)
  @Schema(description = "힘/민첩 8 제한")
  private String reqStrDex8;

  @Column(length = 100)
  @Schema(description = "힘/민첩 10 제한")
  private String reqStrDex10;

  @Column(length = 100)
  @Schema(description = "힘/민첩 12 제한")
  private String reqStrDex12;

  @Column(length = 100)
  @Schema(description = "힘/민첩 14 제한")
  private String reqStrDex14;

  @Column(length = 100)
  @Schema(description = "힘 16 제한")
  private String reqStr16;

  @Column(length = 100)
  @Schema(description = "힘 18 제한")
  private String reqStr18;

  @Column(length = 100)
  @Schema(description = "힘 20 제한")
  private String reqStr20;
}
