package dev.nihilncunia.fa_campaign_manager.domains.sessions;

import dev.nihilncunia.fa_campaign_manager.common.entity.CommonEntity;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuperBuilder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
  name = "session_logs",
  indexes = {
    @Index(name = "idx_session_logs_session_id", columnList = "session_id"),
    @Index(name = "idx_session_logs_user_id", columnList = "user_id")
  }
)
@SQLRestriction(value = "delete_yn = 'N'")
public class SessionLogEntity extends CommonEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "session_id", nullable = false, foreignKey = @ForeignKey(name = "fk_session_logs_sessions"))
  @Schema(description = "대상 세션 정보")
  private SessionEntity session;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_session_logs_users"))
  @Schema(description = "작성자 유저 정보")
  private UserEntity user;

  @Column(nullable = false, length = 100)
  @Schema(description = "로그 제목", example = "오크 습격전 기록")
  private String title;

  @Column(columnDefinition = "TEXT")
  @Schema(description = "로그 내용", example = "숲에서 오크 무리를 만나 치열하게 싸웠다.")
  private String content;

  @Column(length = 255)
  @Schema(description = "첨부 파일 URL (HTML, TXT 로그)", example = "https://example.com/log/session-20260309.html")
  private String fileUrl;
}
