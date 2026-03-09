package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 캠페인 기본 리포지토리
 * 
 * 1. JpaRepository<CampaignEntity, Long>: Spring Data JPA가 제공하는 기본 CRUD 메소드(save, findById, delete 등)를 자동으로 구현해줍니다.
 * 2. CampaignRepositoryCustom: QueryDSL을 이용한 복잡한 동적 쿼리나 통계 쿼리를 정의한 인터페이스입니다.
 * 
 * Spring Data JPA는 이 두 인터페이스를 합쳐서 하나의 빈(Bean)으로 만들어주며, 
 * 개발자는 이 인터페이스 하나만 주입받아 기본 기능과 커스텀 기능을 모두 사용할 수 있습니다.
 */
@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Long>, CampaignRepositoryCustom {
  
  /**
   * 쿼리 메소드(Query Method) 기능:
   * 메소드 이름 자체가 쿼리가 되는 마법 같은 기능입니다.
   * 'existsBy' + 'Name' 이라는 이름을 분석하여 Spring이 자동으로 'SELECT count(id) > 0 FROM campaigns WHERE name = ?' 쿼리를 생성합니다.
   * 
   * @param name 중복 확인을 위한 캠페인 이름
   * @return 존재 여부 (true/false)
   */
  boolean existsByName(String name);
}
