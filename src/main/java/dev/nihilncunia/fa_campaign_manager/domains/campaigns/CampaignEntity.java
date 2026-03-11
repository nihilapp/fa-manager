package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.common.constant.STATUS_CODE;
import dev.nihilncunia.fa_campaign_manager.common.entity.CommonEntity;
import dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterEntity;
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
  @OneToMany(mappedBy = "campaign", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
  @Schema(description = "캠페인 멤버 목록")
  private List<CampaignMemberEntity> memberList = new ArrayList<>();
  
  @Builder.Default
  @OneToMany(mappedBy = "campaign", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
  @Schema(description = "세션 목록")
  private List<SessionEntity> sessionList = new ArrayList<>();
  
  @Builder.Default
  @OneToMany(mappedBy = "campaign")
  @Schema(description = "캠페인 소속 캐릭터 목록")
  private List<CharacterEntity> characterList = new ArrayList<>();
  
  /**
   * 캠페인 기본 정보 업데이트
   *
   * @param name        캠페인 이름
   * @param description 캠페인 설명
   * @param status      캠페인 상태
   * @param startDate   시작일
   * @param endDate     종료일
   */
  public void updateInfo(String name, String description, STATUS_CODE status, OffsetDateTime startDate, OffsetDateTime endDate) {
    this.name = name;
    this.description = description;
    this.status = status;
    this.startDate = startDate;
    this.endDate = endDate;
  }
  
  /**
   * 캠페인 멤버 추가 (연관 관계 편의 메소드)
   * JPA의 양방향 연관 관계에서는 '주인' 객체(FK를 가진 쪽)만 수정해도 DB에는 반영되지만,
   * 순수 객체 상태의 일관성을 위해 양쪽 객체 모두에 연관 정보를 설정해주는 것이 객체지향적인 정석입니다.
   * 이렇게 해야 영속성 컨텍스트가 플러시되기 전에도 객체 그래프 탐색 시 올바른 데이터를 볼 수 있습니다.
   *
   * @param member 추가할 멤버 엔티티
   */
  public void addMember(CampaignMemberEntity member) {
    // 1. 현재 캠페인 객체 리스트에 멤버 추가
    this.memberList.add(member);
    // 2. 멤버 객체 쪽에도 현재 캠페인을 연결 (FK 설정을 위한 핵심 작업)
    member.setCampaign(this);
  }
  
  /**
   * 캠페인 멤버 제거 (연관 관계 편의 메소드)
   * 관계를 끊을 때도 양쪽 상태를 모두 정리해줘야 메모리 누수를 방지하고 객체 일관성을 유지할 수 있습니다.
   *
   * @param member 제거할 멤버 엔티티
   */
  public void removeMember(CampaignMemberEntity member) {
    // 1. 현재 리스트에서 제거
    this.memberList.remove(member);
    // 2. 상대방 객체의 캠페인 참조 제거 (FK 관계 끊기)
    member.setCampaign(null);
  }
  
  /**
   * 캠페인 세션 추가 (연관 관계 편의 메소드)
   * 세션은 캠페인에 소속된 하위 개념이므로 추가 시 관계 설정을 강제합니다.
   *
   * @param session 추가할 세션 엔티티
   */
  public void addSession(SessionEntity session) {
    this.sessionList.add(session);
    session.setCampaign(this);
  }
  
  /**
   * 캠페인 세션 제거 (연관 관계 편의 메소드)
   *
   * @param session 제거할 세션 엔티티
   */
  public void removeSession(SessionEntity session) {
    this.sessionList.remove(session);
    session.setCampaign(null);
  }
  
  /**
   * 캠페인 캐릭터 추가 (연관 관계 편의 메소드)
   *
   * @param character 추가할 캐릭터 엔티티
   */
  public void addCharacter(CharacterEntity character) {
    this.characterList.add(character);
    character.setCampaign(this);
  }
  
  /**
   * 캠페인 캐릭터 제거 (연관 관계 편의 메소드)
   *
   * @param character 제거할 캐릭터 엔티티
   */
  public void removeCharacter(CharacterEntity character) {
    this.characterList.remove(character);
    character.setCampaign(null);
  }
}
