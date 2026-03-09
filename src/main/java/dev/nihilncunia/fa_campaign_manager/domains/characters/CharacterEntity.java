package dev.nihilncunia.fa_campaign_manager.domains.characters;

import dev.nihilncunia.fa_campaign_manager.common.entity.CommonEntity;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.CampaignEntity;
import dev.nihilncunia.fa_campaign_manager.domains.characters.constant.CHARACTER_STATUS;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.SessionPlayerEntity;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
  name = "characters",
  indexes = {
    @Index(name = "idx_characters_name", columnList = "name"),
    @Index(name = "idx_characters_user_id", columnList = "user_id"),
    @Index(name = "idx_characters_status", columnList = "status")
  }
)
@SQLRestriction(value = "delete_yn = 'N'")
public class CharacterEntity extends CommonEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_characters_users"))
  @Schema(description = "소유자 유저 정보")
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "campaign_id", nullable = true, foreignKey = @ForeignKey(name = "fk_characters_campaigns"))
  @Schema(description = "소속 캠페인 정보 (없을 수 있음)")
  private CampaignEntity campaign;

  @Column(nullable = false, length = 50)
  @Schema(description = "캐릭터 이름", example = "가츠")
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  @Schema(description = "캐릭터 상태", example = "ACTIVE")
  private CHARACTER_STATUS status;

  @Column(nullable = false, length = 30)
  @Schema(description = "종족", example = "Human")
  private String race;

  @Builder.Default
  @Column(nullable = false)
  @Schema(description = "시작 레벨", example = "0")
  private Integer startLevel = 0;

  @Builder.Default
  @Column(nullable = false)
  @Schema(description = "시작 경험치", example = "0")
  private Integer startExp = 0;

  @Builder.Default
  @Column(nullable = false)
  @Schema(description = "시작 소지 자금", example = "100")
  private Integer startCurrency = 0;

  @Column(length = 100)
  @Schema(description = "주무장", example = "장검")
  private String mainHand;

  @Column(length = 100)
  @Schema(description = "보조무장", example = "목재 방패")
  private String offHand;

  @Column(length = 100)
  @Schema(description = "갑옷", example = "가죽 갑옷")
  private String armor;

  @Column(length = 100)
  @Schema(description = "머리", example = "철 투구")
  private String head;

  @Column(length = 100)
  @Schema(description = "장갑", example = "가죽 장갑")
  private String gauntlet;

  @Column(length = 100)
  @Schema(description = "부츠", example = "가죽 부츠")
  private String boots;

  @Column(length = 100)
  @Schema(description = "벨트", example = "가죽 벨트")
  private String belt;

  @Column(length = 100)
  @Schema(description = "망토", example = "여행자의 망토")
  private String cloak;

  @Column(length = 100)
  @Schema(description = "기타 장비 1", example = "은반지")
  private String accessory1;

  @Column(length = 100)
  @Schema(description = "기타 장비 2")
  private String accessory2;

  @Column(length = 100)
  @Schema(description = "기타 장비 3")
  private String accessory3;

  @Column(length = 100)
  @Schema(description = "기타 장비 4")
  private String accessory4;

  @Column(length = 100)
  @Schema(description = "힘/민첩 8제한 (벨트/전낭)")
  private String reqStrDex8;

  @Column(length = 100)
  @Schema(description = "힘/민첩 10제한 (벨트/전낭)")
  private String reqStrDex10;

  @Column(length = 100)
  @Schema(description = "힘/민첩 12제한 (벨트/전낭)")
  private String reqStrDex12;

  @Column(length = 100)
  @Schema(description = "힘/민첩 14제한 (벨트/전낭)")
  private String reqStrDex14;

  @Column(length = 100)
  @Schema(description = "힘 16 제한 (벨트/전낭)")
  private String reqStr16;

  @Column(length = 100)
  @Schema(description = "힘 18 제한 (벨트/전낭)")
  private String reqStr18;

  @Column(length = 100)
  @Schema(description = "힘 20 제한 (벨트/전낭)")
  private String reqStr20;

  @Column(length = 100)
  @Schema(description = "건강 8 제한 (배낭/탈것)")
  private String reqCon8;

  @Column(length = 100)
  @Schema(description = "건강 10 제한 (배낭/탈것)")
  private String reqCon10;

  @Column(length = 100)
  @Schema(description = "건강 12 제한 (배낭/탈것)")
  private String reqCon12;

  @Column(length = 100)
  @Schema(description = "건강 14 제한 (배낭/탈것)")
  private String reqCon14;

  @Column(length = 100)
  @Schema(description = "건강 16 제한 (배낭/탈것)")
  private String reqCon16;

  @Column(length = 100)
  @Schema(description = "건강 18 제한 (배낭/탈것)")
  private String reqCon18;

  @Column(length = 100)
  @Schema(description = "건강 20 제한 (배낭/탈것)")
  private String reqCon20;


  @Builder.Default
  @ElementCollection
  @CollectionTable(
    name = "character_classes",
    joinColumns = @JoinColumn(name = "character_id",
      foreignKey = @ForeignKey(name = "fk_character_classes_characters"))
  )
  @Schema(description = "클래스 정보 목록 (멀티클래스 지원)")
  private List<CharacterClass> classes = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "character", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  @Schema(description = "세션 참여 기록")
  private List<SessionPlayerEntity> sessionParticipations = new ArrayList<>();
}

