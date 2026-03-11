package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.nihilncunia.fa_campaign_manager.common.dto.ListOutDto;
import dev.nihilncunia.fa_campaign_manager.common.helper.SearchHelper;
import dev.nihilncunia.fa_campaign_manager.common.repository.CustomRepositorySupport;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignInDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;

/**
 * 캠페인 커스텀 리포지토리 구현체
 * <p>
 * 1. CustomRepositorySupport: 공통적으로 사용하는 JPAQueryFactory와 페이징 처리 헬퍼 기능을 상속받습니다.
 * 2. JPAQueryFactory: QueryDSL을 사용하여 자바 코드로 쿼리를 작성할 수 있게 해주는 핵심 도구입니다.
 * <p>
 * QueryDSL의 장점:
 * - 쿼리를 자바 코드로 작성하여 컴파일 시점에 문법 오류를 잡을 수 있습니다.
 * - 동적 쿼리(조건에 따른 쿼리 변경)를 매우 깔끔하게 작성할 수 있습니다.
 */
public class CampaignRepositoryCustomImpl extends CustomRepositorySupport implements CampaignRepositoryCustom {
  private final CampaignMapper campaignMapper;
  
  public CampaignRepositoryCustomImpl(JPAQueryFactory queryFactory, CampaignMapper campaignMapper) {
    // 1. 생성자 주입을 통해 상속받은 Support 클래스에 QueryFactory를 전달합니다.
    super(queryFactory);
    this.campaignMapper = campaignMapper;
  }
  
  @Override
  public ListOutDto<CampaignOutDto> findAll(CampaignInDto campaignInDto) {
    // 2. QClass(QCampaignEntity): QueryDSL이 엔티티를 기반으로 생성한 정적 메타 모델 클래스입니다.
    // 이를 통해 쿼리 작성 시 타입 안정성(Type Safety)을 보장받습니다.
    QCampaignEntity campaign = QCampaignEntity.campaignEntity;
    
    // 3. BooleanBuilder: 동적 검색 조건을 조립하기 위한 빌더 객체입니다.
    // 'WHERE 1=1' 같은 처리를 자동으로 해주며, 조건이 null이 아닐 때만 AND 연산자로 쿼리를 추가해줍니다.
    // SearchHelper.buildAll은 DTO의 @SearchCondition 어노테이션 정보를 읽어 자동으로 빌더를 구성해주는 이 프로젝트만의 헬퍼 기능입니다.
    BooleanBuilder builder = SearchHelper.buildAll(campaignInDto, campaign);
    
    // 4. 추가적인 조건 처리: 어노테이션으로 처리하기 힘든 복잡한 조건(예: IN 절)은 수동으로 추가합니다.
    if (campaignInDto.getIdList() != null && !campaignInDto.getIdList().isEmpty()) {
      builder.and(campaign.id.in(campaignInDto.getIdList()));
    }
    
    // 5. 콘텐츠 조회 쿼리: 실제로 데이터를 가져오기 위한 쿼리입니다.
    JPAQuery<CampaignEntity> contentQuery = jpaQueryFactory
      .selectFrom(campaign)
      .where(builder); // 위에서 완성한 동적 조건을 적용
    
    // 6. 카운트 조회 쿼리: 페이징을 위해 전체 데이터 개수가 몇 개인지 별도로 구하는 쿼리입니다.
    // 성능 최적화를 위해 콘텐츠 조회와 카운트 조회를 분리하여 작성하는 것이 정석입니다.
    JPAQuery<Long> countQuery = jpaQueryFactory
      .select(campaign.count())
      .from(campaign)
      .where(builder);
    
    // 7. applyDynamicPagination (핵심 헬퍼):
    // DTO에 담겨온 페이지 번호, 사이즈, 정렬 기준 정보를 읽어서 위에서 만든 쿼리에 붙여줍니다. (limit, offset, order by 추가)
    // 최종적으로 쿼리를 실행한 뒤, 결과를 매퍼(campaignMapper::toDtoList)를 이용해 DTO 리스트로 변환하여 반환합니다.
    return applyDynamicPagination(
      campaignInDto,
      campaign,
      contentQuery,
      countQuery,
      campaign.id.desc(), // 기본 정렬 기준 (최신순)
      campaignMapper::toDtoList
    );
  }
}
