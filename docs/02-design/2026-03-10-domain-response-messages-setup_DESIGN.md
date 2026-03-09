# PDCA Design - 도메인별 응답 코드 및 메시지 구축

## 1. 개요
도출된 도메인별 메시지 리스트를 바탕으로 `RESPONSE_MESSAGE.java`, `RESPONSE_CODE.java`, `response_message.yaml` 파일을 업데이트한다.

## 2. 구현 상세

### 2.1 RESPONSE_MESSAGE.java 업데이트 전략
- **기존 메시지 다듬기**: 기존의 평어체 메시지들을 정중한 존댓말로 변경한다.
- **도메인별 그룹화**: `Auth`, `User`, `Campaign`, `Character`, `Doc`, `Session`, `Log`, `Health` 순으로 그룹화하여 정의한다.
- **플레이스홀더 활용**: `MessageFormat` 호환 플레이스홀더(`{0}`)를 사용하여 상세 정보를 포함할 수 있도록 한다.

### 2.2 RESPONSE_CODE.java 업데이트 전략
- 에러 식별용 코드로 활용되므로, 도메인별 에러가 명확히 구분되도록 에러 코드를 추가한다.
- 예: `CAMPAIGN_NOT_FOUND`, `CHARACTER_NOT_FOUND`, `DOC_NOT_FOUND`, `SESSION_NOT_FOUND` 등.

### 2.3 response_message.yaml 동기화 전략
- `RESPONSE_MESSAGE` enum의 내용을 그대로 YAML 형식으로 반영하여 설정 기반의 메시지 처리도 가능하게 한다.

## 3. 예외 처리
- 기존에 사용 중인 상수의 이름을 변경할 경우, 참조하고 있는 모든 클래스에서의 컴파일 오류를 확인해야 한다. (이번 작업에서는 이름 변경보다는 신규 추가와 메시지 텍스트 변경에 집중한다.)

## 4. 기대 결과
- 모든 API 응답에서 일관되고 정중한 도메인 특화 메시지가 반환된다.
