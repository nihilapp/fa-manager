package dev.nihilncunia.fa_campaign_manager.domains.users;

import dev.nihilncunia.fa_campaign_manager.common.constant.USER_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.constant.YN_CODE;
import dev.nihilncunia.fa_campaign_manager.common.entity.CommonEntity;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.CampaignMemberEntity;
import dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterEntity;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.SessionPlayerEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
  name = "users",
  indexes = {
    @Index(name = "idx_users_email", columnList = "email", unique = true),
    @Index(name = "idx_users_name", columnList = "name"),
    @Index(name = "idx_users_discord_id", columnList = "discordId")
  },
  uniqueConstraints = {}
)
@SQLRestriction(value = "delete_yn = 'N'")
public class UserEntity extends CommonEntity {
  
  @Column(nullable = false, length = 50)
  @Schema(description = "사용자 이름", example = "NIHILncunia")
  private String name;

  @Column(nullable = false, length = 100, unique = true)
  @Email(message = "이메일 형식이 올바르지 않습니다.")
  @Schema(description = "사용자 이메일 (로그인 ID 겸용)", example = "example@example.com")
  private String email;

  @Column(nullable = false, length = 255)
  @Schema(description = "사용자 비밀번호 (암호화된 문자열)")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  @Schema(description = "사용자 시스템 권한", example = "ROLE_USER")
  private USER_ROLE role;
  
  @Column(nullable = true, length = 1000)
  @Schema(description = "사용자 리프레시 토큰")
  private String refreshToken;
  
  @Builder.Default
  @Column(nullable = false)
  @Schema(description = "로그인 실패 횟수", example = "0")
  private Integer loginFailureCount = 0;
  
  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  @Schema(description = "사용자 잠금 여부", example = "N")
  private YN_CODE lockYn = YN_CODE.N;
  
  @Column(nullable = true)
  @Schema(description = "사용자 마지막 로그인 날짜")
  private OffsetDateTime lastSignInDate;
  
  @Column(nullable = true)
  @Schema(description = "사용자 마지막 비밀번호 변경 날짜")
  private OffsetDateTime lastPasswordChangeDate;

  @Column(nullable = true, length = 100, unique = true)
  @Schema(description = "디스코드 사용자 고유 ID", example = "123456789012345678")
  private String discordId;

  @Builder.Default
  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  @Schema(description = "보유 캐릭터 목록")
  private List<CharacterEntity> characters = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  @Schema(description = "참여 캠페인 목록")
  private List<CampaignMemberEntity> campaignMembers = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  @Schema(description = "세션 참여 기록")
  private List<SessionPlayerEntity> sessionParticipations = new ArrayList<>();
}
