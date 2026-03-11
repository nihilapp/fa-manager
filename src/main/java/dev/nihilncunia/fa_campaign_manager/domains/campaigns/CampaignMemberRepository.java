package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 캠페인 멤버 리포지토리
 * <p>
 * 캠페인(Campaign)과 사용자(User) 사이의 연관 관계(CampaignMember)를 관리합니다.
 * JPA의 JpaRepository를 상속받아 기본적인 CRUD 및 쿼리 메소드를 제공받습니다.
 */
@Repository
public interface CampaignMemberRepository extends JpaRepository<CampaignMemberEntity, Long> {
  
  /**
   * 특정 캠페인에서 특정 사용자가 멤버로 등록되어 있는지 조회합니다.
   *
   * @param campaignId 캠페인 ID
   * @param userId     사용자 ID
   * @return 멤버 정보 (존재할 경우)
   */
  Optional<CampaignMemberEntity> findByCampaignIdAndUserId(Long campaignId, Long userId);
  
  /**
   * 특정 캠페인에서 특정 사용자가 이미 멤버인지 여부를 확인합니다.
   *
   * @param campaignId 캠페인 ID
   * @param userId     사용자 ID
   * @return 존재 여부
   */
  boolean existsByCampaignIdAndUserId(Long campaignId, Long userId);
}
