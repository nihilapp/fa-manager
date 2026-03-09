# [PLAN] 세션 플레이 로그 도메인 구성

- **작성일**: 2026-03-09
- **작성자**: Gemini CLI (bkit)
- **목표**: SessionLogEntity 완성 및 입출력 DTO(SessionLogInDto, SessionLogOutDto) 생성.

## 1. 요구사항 분석
- `SessionLogEntity`는 세션 플레이 기록을 저장하는 테이블임.
- 엔티티에 JPA 관계 매핑(@ManyToOne) 및 제약 조건 추가 필요.
- 로그 입력 및 검색을 위한 `SessionLogInDto` 생성.
- 로그 조회를 위한 `SessionLogOutDto` 생성.

## 2. 작업 상세
- [ ] `SessionLogEntity.java` 업데이트: 관계 매핑, 컬럼 정의, 인덱스 추가.
- [ ] `SessionLogInDto.java` 생성: 입력 필드 정의 및 유효성 검사 추가.
- [ ] `SessionLogOutDto.java` 생성: 출력 필드 및 정보 포함.

## 3. 기대 결과
- 세션별 플레이 로그를 체계적으로 기록하고 관리할 수 있는 기반 마련.
- API를 통해 로그 데이터의 CRUD 작업 가능.
