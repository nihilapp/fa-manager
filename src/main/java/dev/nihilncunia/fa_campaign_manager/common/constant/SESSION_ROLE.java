package dev.nihilncunia.fa_campaign_manager.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 세션 내에서의 역할을 정의하는 Enum 클래스입니다.
 */
@Getter
@RequiredArgsConstructor
public enum SESSION_ROLE {
    MASTER("마스터"),
    PLAYER("플레이어");

    private final String description;
}
