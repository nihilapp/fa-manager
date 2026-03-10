package dev.nihilncunia.fa_campaign_manager.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 캠페인 내에서의 사용자 역할을 정의하는 Enum 클래스입니다.
 */
@Getter
@RequiredArgsConstructor
public enum MEMBER_ROLE {
  MEMBER_MAIN_MASTER("메인 마스터"),
  MEMBER_SUB_MASTER("서브 마스터"),
  MEMBER_PLAYER("플레이어");

  private final String description;
}
