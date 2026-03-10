# Coding Rules

## 1. 명명 규칙 (Naming Conventions)
- **클래스/인터페이스**: PascalCase (예: `CampaignController`, `CampaignService`)
- **메서드/변수**: camelCase (예: `getCampaignById`, `campaignEntity`)
- **상수**: UPPER_SNAKE_CASE (예: `RESPONSE_CODE`)

## 2. 아키텍처 및 계층 (Architecture & Layers)
- **Controller**: HTTP 요청/응답 처리 및 검증
- **Service**: 비즈니스 로직 수행
- **Repository**: 데이터베이스 상호작용
- **Mapper**: Entity-DTO 간 변환 (MapStruct 활용)
- 엔티티와 DTO는 분리하여 사용하며, Controller와 Service 간에는 DTO만 교환함.

## 3. Git 커밋 (Git Commit Messages)
- 형식: `[YYYY-MM-DD] <type>: <subject>`
- type: `feat`, `fix`, `docs`, `style`, `refactor`, `perf`, `test`, `chore`
