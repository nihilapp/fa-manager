package dev.nihilncunia.fa_campaign_manager.domains.sessions;

import dev.nihilncunia.fa_campaign_manager.common.constant.STATUS_CODE;
import dev.nihilncunia.fa_campaign_manager.common.entity.CommonEntity;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.CampaignEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
  name = "sessions",
  indexes = {
    @Index(name = "idx_session_no", columnList = "no"),
    @Index(name = "idx_session_name", columnList = "name"),
    @Index(name = "idx_sessions_campaign_id", columnList = "campaign_id"),
    @Index(name = "idx_sessions_status", columnList = "status")
  }
)
@SQLRestriction(value = "delete_yn = 'N'")
public class SessionEntity extends CommonEntity {
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "campaign_id",
    foreignKey = @ForeignKey(name = "fk_sessions_campaigns"))
  @Schema(description = "캠페인 ID", example = "1")
  private CampaignEntity campaign;
  
  @Column(nullable = false)
  @Schema(description = "세션 번호", example = "1")
  private Integer no;
  
  @Column(nullable = false, length = 50)
  @Schema(description = "세션 이름", example = "첫 번째 세션")
  private String name;
  
  @Column(length = 2000)
  @Schema(description = "세션 설명", example = "첫 번째 세션에 대한 설명입니다.")
  private String description;
  
  @Schema(description = "최대 플레이어 수", example = "10")
  private Integer maxPlayer;
  
  @Schema(description = "보상 경험치", example = "100")
  private Integer rewardExp;
  
  @Schema(description = "보상 골드", example = "1000")
  private Integer rewardGold;
  
  @Enumerated(EnumType.STRING)
  @Schema(description = "세션 진행 상태", example = "OPEN")
  private STATUS_CODE status;
  
  @Schema(description = "세션 시작 시간", example = "2023-01-01T10:00:00.000+09:00")
  private OffsetDateTime playDate;

  @Builder.Default
  @OneToMany(mappedBy = "session", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  @Schema(description = "참여 플레이어 목록")
  private List<SessionPlayerEntity> players = new ArrayList<>();
}
