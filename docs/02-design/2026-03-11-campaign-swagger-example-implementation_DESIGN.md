# PDCA Design: 캠페인 Swagger 응답 예시 구현

## 1. 개요
`CampaignExample` 클래스를 설계하여 캠페인 도메인의 풍부한 API 문서를 완성합니다.

## 2. CampaignExample 설계 (주요 예시)

### A. 캠페인 CRUD 성공 예시
- **campaignGetListSuccess**: `ListOutDto<CampaignOutDto>` 형태의 페이징 데이터
- **campaignGetDetailSuccess**: 단건 `CampaignOutDto` 데이터
- **campaignCreateSuccess**: 생성된 `CampaignOutDto` 데이터
- **campaignUpdateSuccess**: 수정된 `CampaignOutDto` 데이터

### B. 멤버 및 캐릭터 연동 성공 예시
- **campaignMemberAddSuccess**: 추가된 멤버의 `UserOutDto`
- **campaignCharacterAddSuccess**: 등록된 캐릭터의 `CharacterOutDto`

### C. 도메인 실패 예시 (비즈니스 예외)
- **campaignNotFound**: (404) 존재하지 않는 캠페인 ID 접근 시
- **campaignNameConflict**: (409) 동일한 이름의 캠페인 생성 시
- **campaignMemberConflict**: (409) 이미 참여 중인 플레이어 등록 시
- **campaignCharacterConflict**: (409) 이미 다른 캠페인에 속한 캐릭터 등록 시

## 3. CommonSwaggerConfig 연동 설계
- `CampaignExample` 빈을 주입받아 `openApiCustomizer()` 내에서 `components.addExamples()`를 통해 전역 등록합니다.
- 예시 키(Key) 명명 규칙: `campaignXXX` (예: `campaignGetListSuccess`, `campaignNotFound`)

## 4. 컨트롤러 적용 설계
- `@ApiExampleExclude`를 활용하여 엔드포인트별로 불필요한 공통 예시를 제거하거나, 특정 도메인 예시를 강제하는 방안 검토 (현재는 전역 추가 방식이므로 필터링 위주).
