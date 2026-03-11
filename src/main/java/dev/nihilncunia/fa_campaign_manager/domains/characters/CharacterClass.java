package dev.nihilncunia.fa_campaign_manager.domains.characters;

import jakarta.persistence.Embeddable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterClass {
  @Schema(description = "클래스 명", example = "Wizard")
  private String className;
  
  @Schema(description = "서브 클래스 명", example = "Evocation")
  private String subClass;
  
  @Schema(description = "레벨", example = "5")
  private Integer level;
}
