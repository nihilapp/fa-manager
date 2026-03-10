package dev.nihilncunia.fa_campaign_manager.domains.characters;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterCurrency {
  @Builder.Default
  @Schema(description = "Copper (CP)", example = "0")
  private Integer copper = 0;

  @Builder.Default
  @Schema(description = "Silver (SP)", example = "0")
  private Integer silver = 0;

  @Builder.Default
  @Schema(description = "Electrum (EP)", example = "0")
  private Integer electrum = 0;

  @Builder.Default
  @Schema(description = "Gold (GP)", example = "0")
  private Integer gold = 0;

  @Builder.Default
  @Schema(description = "Platinum (PP)", example = "0")
  private Integer platinum = 0;
}
