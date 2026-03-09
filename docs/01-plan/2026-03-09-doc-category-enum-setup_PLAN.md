# [PLAN] 문서 카테고리 열거형(DOC_CATEGORY) 완성

- **작성일**: 2026-03-09
- **작성자**: Gemini CLI (bkit)
- **목표**: 제공된 12종의 문서 카테고리 정보를 바탕으로 DOC_CATEGORY Enum을 완성한다.

## 1. 요구사항 분석
- 총 12개의 카테고리 코드, 한글 명칭, 설명 정보를 열거형으로 구현.
- 프로젝트 컨벤션에 따라 `@Getter`, `@RequiredArgsConstructor`, `@JsonValue` 적용.
- 필드 구성: `name` (코드), `korName` (한글 명칭), `description` (설명).

## 2. 작업 상세
- [ ] `DOC_CATEGORY.java` 수정: 12개 상수 정의 및 필드/생성자 구현.

## 3. 기대 결과
- 세계관 설정 문서 작성 시 정해진 카테고리 내에서 일관성 있게 분류 가능.
- API 및 데이터베이스 저장 시 표준화된 코드 값 사용.
