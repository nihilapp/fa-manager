package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.common.constant.STATUS_CODE;
import dev.nihilncunia.fa_campaign_manager.common.entity.CommonEntity;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.SessionEntity;
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
  name = "campaigns",
  indexes = {
    @Index(name = "idx_campaigns_name", columnList = "name", unique = true),
    @Index(name = "idx_campaigns_status", columnList = "status")
  },
  uniqueConstraints = {}
)
@SQLRestriction(value = "delete_yn = 'N'")
public class CampaignEntity extends CommonEntity {
  @Schema(description = "캠페인 명", example = "검은 날개의 여정")
  @Column(nullable = false, length = 50)
  private String name;
  
  @Schema(description = "캠페인 설명", example = "검은 날개 군단에 맞서 싸우는 모험가들의 이야기")
  @Column(length = 1000)
  private String description;
  
  @Schema(description = "캠페인 상태", example = "PLAYING")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private STATUS_CODE status;
  
  @Schema(description = "캠페인 시작일", example = "2026-03-08T10:00:00+09:00")
  @Column(nullable = false)
  private OffsetDateTime startDate;
  
  @Schema(description = "캠페인 종료일", example = "2026-12-31T23:59:59+09:00")
  @Column(nullable = true)
  private OffsetDateTime endDate;

  @Builder.Default
  @OneToMany(mappedBy = "campaign", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  @Schema(description = "캠페인 멤버 목록")
  private List<CampaignMemberEntity> members = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "campaign", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  @Schema(description = "세션 목록")
  private List<SessionEntity> sessions = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "campaign")
  @Schema(description = "캠페인 소속 캐릭터 목록")
  private List<dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterEntity> characters = new ArrayList<>();
}
