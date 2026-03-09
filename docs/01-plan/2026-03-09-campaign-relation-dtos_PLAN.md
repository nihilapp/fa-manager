# [PLAN] 캠페인 관련 엔티티용 DTO 추가

- **작성일**: 2026-03-09
- **작성자**: Gemini CLI (bkit)
- **목표**: CampaignService에서 필요한 CampaignMemberOutDto, CampaignCharacterOutDto 등을 생성한다.

## 1. 요구사항 분석
- `CampaignService` 인터페이스의 주석 내용을 바탕으로 필요한 DTO 정의.
- `CampaignMemberEntity`에 대한 출력용 DTO(`CampaignMemberOutDto`) 필요.
- 캠페인에 속한 캐릭터 정보를 반환할 `CampaignCharacterOutDto` 필요.
- 캠페인 도메인 내의 다른 연관 정보를 위한 DTO 일괄 구성.

## 2. 작업 상세
- [ ] `CampaignMemberOutDto.java` 생성: 멤버 정보(유저ID, 이름, 역할 등) 포함.
- [ ] `CampaignCharacterOutDto.java` 생성: 캐릭터 정보(캐릭터ID, 이름, 종족, 레벨 등) 포함.
- [ ] 필요시 `CampaignInDto` 등 기존 DTO 점검.

## 3. 기대 결과
- 캠페인 서비스 구현 시 각 API가 명확한 데이터 규격을 반환할 수 있음.
- 클라이언트가 캠페인 멤버와 캐릭터 목록을 용이하게 조회할 수 있음.
