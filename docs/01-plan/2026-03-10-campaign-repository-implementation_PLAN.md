# PDCA Plan - 캠페인 커스텀 레포지토리 구현

## 1. 개요
`CampaignRepositoryCustomImpl.java`의 `findAll` 메서드를 완성하여 캠페인 목록 조회 기능을 구현한다.

## 2. 목표
- `CampaignInDto`의 검색 조건을 QueryDSL 쿼리에 반영한다.
- 페이징 및 동적 정렬을 적용한다.
- `CampaignMapper`를 사용하여 엔티티를 `CampaignOutDto`로 변환한다.
- 프로젝트 공통 라이브러리(`SearchHelper`, `CustomRepositorySupport`)를 활용한다.

## 3. 작업 상세 (ToDo)
- [ ] `CampaignRepositoryCustomImpl.java` 파일 분석 및 수정 전략 수립
- [ ] `QCampaignEntity` 인스턴스 확보 및 검색 조건 조립 (`SearchHelper` 활용)
- [ ] `idList` 등 추가 검색 조건 수동 반영
- [ ] `applyDynamicPagination`을 통한 최종 결과 반환 구현
- [ ] 구현 결과 검증

## 4. 예상 결과물
- 완성된 `CampaignRepositoryCustomImpl.java`

## 5. 비고
- `SearchHelper`가 상속된 필드를 처리하는지 확인 필요 (현재는 `getDeclaredFields` 사용 중)
- `idList`는 `CampaignInDto`에 있으나 `@SearchCondition`이 없으므로 수동 처리가 필요할 수 있음
