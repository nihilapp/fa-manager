package dev.nihilncunia.fa_campaign_manager.domains.logs.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 로그 이력의 작업 유형을 정의하는 Enum 클래스입니다.
 */
@Getter
@RequiredArgsConstructor
public enum LOG_ACTION_TYPE {
    CREATE("생성"),
    UPDATE("수정"),
    DELETE("삭제"),
    RESTORE("복원");

    private final String description;
}
