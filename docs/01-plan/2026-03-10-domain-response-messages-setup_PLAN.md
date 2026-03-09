# PDCA Plan - 도메인별 응답 코드 및 메시지 구축

## 1. 개요
`RESPONSE_CODE.java`와 `RESPONSE_MESSAGE.java`에 프로젝트 내 모든 도메인(`auth`, `campaigns`, `characters`, `docs`, `health`, `logs`, `sessions`, `users`)에 대응하는 정중한 응답 메시지들을 추가한다.

## 2. 목표
- 각 도메인의 서비스 로직에서 필요로 하는 성공/실패 메시지들을 도출한다.
- 모든 메시지를 사용자에게 친근한 존댓말 표현으로 통일한다.
- `RESPONSE_MESSAGE`에 도메인별 섹션을 구성하여 가독성을 높인다.
- 필요 시 `RESPONSE_CODE`에 도메인 특화 에러 식별 코드를 추가한다.

## 3. 작업 상세 (ToDo)
- [ ] 각 도메인별 필요한 메시지 리스트 도출
- [ ] `RESPONSE_MESSAGE.java` 업데이트 (정중한 표현 적용 및 도메인별 추가)
- [ ] `RESPONSE_CODE.java` 업데이트 (도메인 특화 코드 필요 시 추가)
- [ ] `response_message.yaml` 동기화 (설정 파일 기반 메시지 처리용)

## 4. 예상 결과물
- 도메인별 메시지가 완비된 `RESPONSE_MESSAGE.java`
- 보완된 `RESPONSE_CODE.java`
- 동기화된 `response_message.yaml`

## 5. 비고
- `MessageFormat`을 사용하는 `{0}` 형태의 플레이스홀더를 적극 활용한다.
