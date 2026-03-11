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
public class CharacterEquipment {
  
  @Column(length = 100)
  @Schema(description = "주무장")
  private String mainHand;
  
  @Column(length = 100)
  @Schema(description = "보조무장")
  private String offHand;
  
  @Column(length = 100)
  @Schema(description = "갑옷")
  private String armor;
  
  @Column(length = 100)
  @Schema(description = "머리")
  private String head;
  
  @Column(length = 100)
  @Schema(description = "장갑")
  private String gauntlet;
  
  @Column(length = 100)
  @Schema(description = "부츠")
  private String boots;
  
  @Column(length = 100)
  @Schema(description = "벨트")
  private String belt;
  
  @Column(length = 100)
  @Schema(description = "망토")
  private String cloak;
  
  @Column(length = 100)
  @Schema(description = "기타 장비 1")
  private String accessory1;
  
  @Column(length = 100)
  @Schema(description = "기타 장비 2")
  private String accessory2;
  
  @Column(length = 100)
  @Schema(description = "기타 장비 3")
  private String accessory3;
  
  @Column(length = 100)
  @Schema(description = "기타 장비 4")
  private String accessory4;
}
