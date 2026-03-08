package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.common.constant.MEMBER_ROLE;
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
  name = "campaign_members",
  uniqueConstraints = {
    @UniqueConstraint(name = "uk_campaign_members_user_campaign",
      columnNames = { "user_id", "campaign_id" })
  }
)
@SQLRestriction(value = "delete_yn = 'N'")
public class CampaignMemberEntity extends CommonEntity {
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id",
    foreignKey = @ForeignKey(name = "fk_campaign_members_users"))
  @Schema(description = "사용자 ID", example = "1")
  private UserEntity user;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "campaign_id",
    foreignKey = @ForeignKey(name = "fk_campaign_members_campaigns"))
  @Schema(description = "캠페인 ID", example = "1")
  private CampaignEntity campaign;
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  @Schema(description = "구분", example = "MASTER")
  private MEMBER_ROLE role;
}
