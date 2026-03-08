package dev.nihilncunia.fa_campaign_manager.domains.logs;

import dev.nihilncunia.fa_campaign_manager.common.entity.CommonEntity;
import dev.nihilncunia.fa_campaign_manager.domains.logs.constant.LOG_ACTION_TYPE;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 시스템 내 모든 데이터 변경 이력을 기록하는 로그 히스토리 엔티티입니다.
 * 변경 전후 데이터를 JSON 형태로 저장하여 추후 복원(Rollback) 기능을 지원합니다.
 */
@Entity
@Table(
  name = "log_history",
  indexes = {
    @Index(name = "idx_log_history_target", columnList = "tableName, targetId"),
    @Index(name = "idx_log_history_user_id", columnList = "user_id")
  }
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LogHistoryEntity extends CommonEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @Schema(description = "작업 수행자 (누가 건드렸나?)")
  private UserEntity user;

  @Column(nullable = false, length = 50)
  @Schema(description = "대상 테이블 명", example = "users")
  private String tableName;

  @Column(nullable = false)
  @Schema(description = "대상 데이터 PK", example = "1")
  private Long targetId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  @Schema(description = "작업 유형 (CREATE, UPDATE, DELETE, RESTORE)")
  private LOG_ACTION_TYPE actionType;

  @Column(columnDefinition = "TEXT")
  @Schema(description = "변경 전 스냅샷 (JSON)")
  private String oldData;

  @Column(columnDefinition = "TEXT")
  @Schema(description = "변경 후 스냅샷 (JSON)")
  private String newData;

  @Column(length = 1000)
  @Schema(description = "작업 설명 및 사유", example = "마스터 권한으로 사용자 닉네임 강제 수정")
  private String description;
}
