# PDCA Report - 응답 메시지 존댓말 변경

## 1. 작업 개요
시스템 응답 메시지를 보다 정중하고 친절한 존댓말 표현으로 변경하였습니다.

## 2. 주요 구현 내용
- `RESPONSE_CODE.java`의 모든 메시지를 "~에 성공하였습니다.", "~해 주세요." 등의 존댓말로 수정.
- `src/main/resources/response_message.yaml`의 모든 메시지를 동일한 톤으로 수정 및 동기화.
- HTTP 상태 코드 및 플레이스홀더(`{0}`) 구조를 유지하여 기존 로직에 영향이 없도록 조치.

## 3. 구현 결과물
- `src/main/java/dev/nihilncunia/fa_campaign_manager/common/constant/RESPONSE_CODE.java`
- `src/main/resources/response_message.yaml`

## 4. 성과 및 향후 계획
- 사용자 경험(UX) 관점에서 보다 신뢰감 있는 메시지를 전달할 수 있게 되었습니다.
- 추후 신규 추가되는 코드에 대해서도 동일한 정중한 표현을 사용하도록 컨벤션을 유지하겠습니다.
