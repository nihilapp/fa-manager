package dev.nihilncunia.fa_campaign_manager.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SEARCH_TYPE {
  EQUALS("EQUALS"),
  LIKE("LIKE"),
  LEFT_LIKE("LEFT_LIKE"),
  RIGHT_LIKE("RIGHT_LIKE"),
  GREATER_THAN("GREATER_THAN"),
  GREATER_THAN_OR_EQUAL("GREATER_THAN_OR_EQUAL"),
  LESS_THAN("LESS_THAN"),
  LESS_THAN_OR_EQUAL("LESS_THAN_OR_EQUAL"),
  BETWEEN("BETWEEN"),
  IN("IN"),
  NOT_EQUALS("NOT_EQUALS"),
  IS_NULL("IS_NULL"),
  IS_NOT_NULL("IS_NOT_NULL");

  private final String type;
}
