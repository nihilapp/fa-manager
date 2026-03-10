package dev.nihilncunia.fa_campaign_manager.domains.characters.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "캐릭터 화폐 정보 DTO")
public class CharacterCurrencyDto {
  @Min(value = 0, message = "화폐 수치는 0 이상이어야 합니다.")
  @Schema(description = "Copper (CP)")
  private Integer copper;

  @Min(value = 0, message = "화폐 수치는 0 이상이어야 합니다.")
  @Schema(description = "Silver (SP)")
  private Integer silver;

  @Min(value = 0, message = "화폐 수치는 0 이상이어야 합니다.")
  @Schema(description = "Electrum (EP)")
  private Integer electrum;

  @Min(value = 0, message = "화폐 수치는 0 이상이어야 합니다.")
  @Schema(description = "Gold (GP)")
  private Integer gold;

  @Min(value = 0, message = "화폐 수치는 0 이상이어야 합니다.")
  @Schema(description = "Platinum (PP)")
  private Integer platinum;
}
