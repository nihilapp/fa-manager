# [PLAN] 캐릭터 DTO 필드 동기화

- **작성일**: 2026-03-09
- **작성자**: Gemini CLI (bkit)
- **목표**: CharacterEntity에 추가된 26개의 신규 필드를 CharacterInDto 및 CharacterOutDto에 반영한다.

## 1. 요구사항 분석
- CharacterEntity에 장비 필드(12종) 및 능력치 제한 필드(14종)가 추가됨.
- API를 통한 입출력을 위해 DTO에도 해당 필드들이 필요함.
- `CharacterInDto`: 입력 및 검색 용도
- `CharacterOutDto`: 정보 출력 용도

## 2. 작업 상세
- [ ] `CharacterInDto.java`에 26개 필드 추가 (어노테이션 포함)
- [ ] `CharacterOutDto.java`에 26개 필드 추가 (어노테이션 포함)
- 필드 삽입 위치: 각 DTO의 `startCurrency` 필드 이후

## 3. 기대 결과
- 클라이언트가 캐릭터 생성/수정 시 장비 및 제한 사항을 입력할 수 있음.
- 캐릭터 상세 조회 시 모든 신규 필드 정보를 확인할 수 있음.
