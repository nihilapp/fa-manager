# [DESIGN] 캠페인 관계 DTO 상세 설계

## 1. 개요
캠페인 도메인 내의 연관 정보를 위한 출력용 DTO들의 상세 구조를 정의한다.

## 2. DTO 상세 정의
### 2.1 CampaignMemberOutDto
`CampaignMemberEntity`에 대한 출력 데이터.
- `id`: 멤버 레코드 고유 ID
- `userId`: 유저 ID
- `userName`: 유저 이름
- `email`: 유저 이메일
- `role`: 캠페인 내 역할 (MASTER, PLAYER 등)

### 2.2 CampaignCharacterOutDto
캠페인에 소속된 캐릭터 정보를 나타냄.
- `id`: 캐릭터 ID
- `name`: 캐릭터 이름
- `race`: 종족
- `level`: 레벨
- `status`: 상태 (ACTIVE 등)
- `userId`: 소유자 ID
- `userName`: 소유자 이름

## 3. 파일 위치
- `CampaignMemberOutDto.java`: `src/main/java/dev/nihilncunia/fa_campaign_manager/domains/campaigns/dto/CampaignMemberOutDto.java`
- `CampaignCharacterOutDto.java`: `src/main/java/dev/nihilncunia/fa_campaign_manager/domains/campaigns/dto/CampaignCharacterOutDto.java`
