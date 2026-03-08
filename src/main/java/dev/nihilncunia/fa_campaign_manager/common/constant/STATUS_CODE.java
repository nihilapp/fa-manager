package dev.nihilncunia.fa_campaign_manager.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum STATUS_CODE {
  PREPARING("준비"),
  IN_PROGRESS("진행"),
  COMPLETED("종료"),
  CANCELED("취소"),
  ON_HOLD("보류");
  
  private final String description;
}
