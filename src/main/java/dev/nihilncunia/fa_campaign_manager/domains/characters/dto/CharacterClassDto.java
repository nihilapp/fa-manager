package dev.nihilncunia.fa_campaign_manager.domains.characters.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "캐릭터 클래스 정보 DTO")
public class CharacterClassDto {
  @Schema(description = "클래스 명", example = "Wizard")
  private String className;

  @Schema(description = "서브 클래스 명", example = "Evocation")
  private String subClass;

  @Schema(description = "레벨", example = "5")
  private Integer level;
}
