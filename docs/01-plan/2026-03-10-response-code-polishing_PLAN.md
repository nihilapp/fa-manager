# PDCA Plan - 응답 메시지 존댓말 변경

## 1. 개요
시스템에서 사용하는 모든 응답 메시지를 사용자에게 친숙하고 정중한 존댓말 표현으로 변경한다.

## 2. 목표
- `RESPONSE_CODE.java`의 Enum 메시지를 정중한 표현으로 수정한다.
- `response_message.yaml` 파일의 메시지들도 일관성을 위해 함께 수정한다.
- 프로젝트 전체의 메시지 톤앤매너를 통일한다.

## 3. 작업 상세 (ToDo)
- [ ] `RESPONSE_CODE.java` 수정 전략 수립 및 적용
- [ ] `response_message.yaml` 수정 전략 수립 및 적용
- [ ] 수정된 메시지들이 정상적으로 반환되는지 확인

## 4. 예상 결과물
- 정중한 표현이 적용된 `RESPONSE_CODE.java`
- 정중한 표현이 적용된 `response_message.yaml`

## 5. 비고
- API 문서(Swagger) 등에 노출되는 메시지이므로 정확한 표현을 사용한다.
