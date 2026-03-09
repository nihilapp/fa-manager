# [PLAN] 세계관 설정 문서(Doc) 도메인 구성

- **작성일**: 2026-03-09
- **작성자**: Gemini CLI (bkit)
- **목표**: DocEntity 완성, UserEntity 연관 관계 추가, 입출력 DTO(DocInDto, DocOutDto) 생성.

## 1. 요구사항 분석
- `DocEntity`는 세계관 설정을 담는 문서 테이블임.
- `content` 필드는 JSON 형식의 문자열을 저장해야 함.
- `UserEntity`와 1:N 관계를 맺어 사용자가 작성한 문서 목록을 조회할 수 있어야 함.
- 입력 및 조회를 위한 DTO들이 필요함.

## 2. 작업 상세
- [ ] `DocEntity.java` 업데이트: JPA 매핑, 컬럼 제약 조건, 인덱스 추가.
- [ ] `UserEntity.java` 업데이트: `List<DocEntity>` 필드 및 `@OneToMany` 추가.
- [ ] `DocInDto.java` 생성: 입력 및 검색용 필드 정의.
- [ ] `DocOutDto.java` 생성: 출력용 필드 정의.

## 3. 기대 결과
- 사용자가 작성한 세계관 설정 문서를 체계적으로 관리하고 조회할 수 있음.
- JSON 기반의 유연한 문서 본문 구조 지원.
