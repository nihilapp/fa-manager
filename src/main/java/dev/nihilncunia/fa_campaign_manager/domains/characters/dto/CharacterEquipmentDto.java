package dev.nihilncunia.fa_campaign_manager.domains.characters.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "캐릭터 장비 정보 DTO")
public class CharacterEquipmentDto {
  
  @Schema(description = "주무장")
  private String mainHand;
  
  @Schema(description = "보조무장")
  private String offHand;
  
  @Schema(description = "갑옷")
  private String armor;
  
  @Schema(description = "머리")
  private String head;
  
  @Schema(description = "장갑")
  private String gauntlet;
  
  @Schema(description = "부츠")
  private String boots;
  
  @Schema(description = "벨트")
  private String belt;
  
  @Schema(description = "망토")
  private String cloak;
  
  @Schema(description = "기타 장비 1")
  private String accessory1;
  
  @Schema(description = "기타 장비 2")
  private String accessory2;
  
  @Schema(description = "기타 장비 3")
  private String accessory3;
  
  @Schema(description = "기타 장비 4")
  private String accessory4;
}
