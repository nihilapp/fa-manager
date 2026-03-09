# PDCA Design - 캠페인 커스텀 레포지토리 구현

## 1. 개요
`CampaignRepositoryCustomImpl.java`의 `findAll` 메서드 구현 상세 설계.

## 2. 구현 상세

### 2.1 쿼리 대상 정의
- 대상 엔티티: `CampaignEntity`
- Q타입: `QCampaignEntity campaign = QCampaignEntity.campaignEntity;`

### 2.2 검색 조건 조립 (`BooleanBuilder`)
1. `SearchHelper.buildAll(campaignInDto, campaign)`을 통해 DTO에 정의된 `@SearchCondition` 기반 자동 필터링 생성.
2. `campaignInDto.getIdList()`가 null이 아니고 비어있지 않다면, `campaign.id.in(campaignInDto.getIdList())` 조건을 추가.
3. (선택사항) 상속된 필드 처리가 필요하다면 추가 로직 반영.

### 2.3 쿼리 생성
- `contentQuery`: `jpaQueryFactory.selectFrom(campaign).where(builder)`
- `countQuery`: `jpaQueryFactory.select(campaign.count()).from(campaign).where(builder)`

### 2.4 결과 반환
- `applyDynamicPagination` 메서드 활용:
    - `inDto`: `campaignInDto`
    - `qEntity`: `campaign`
    - `contentQuery`: 생성된 contentQuery
    - `countQuery`: 생성된 countQuery
    - `defaultOrder`: `campaign.id.desc()` (최신순 기본 정렬)
    - `mapper`: `campaignMapper::toDtoList`

## 3. 예외 처리
- 검색 조건이 없을 경우 전체 조회를 수행한다.
- 페이징 정보가 없을 경우(`size < 1`) 전체 조회를 수행한다.

## 4. 기대 결과
- 검색 조건과 페이징이 완벽히 적용된 `ListOutDto<CampaignOutDto>` 반환.
