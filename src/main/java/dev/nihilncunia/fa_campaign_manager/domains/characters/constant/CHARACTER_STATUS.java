package dev.nihilncunia.fa_campaign_manager.domains.characters.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CHARACTER_STATUS {
  ACTIVE("사용중"),
  RESTING("휴식"),
  RETIRED("은퇴"),
  DECEASED("사망");
  
  private final String description;
}
