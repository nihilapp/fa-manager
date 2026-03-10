# PDCA Plan: Swagger 공통 응답 예시 정상화

## 1. 개요
Swagger UI에서 공통 에러 응답(400, 401, 403, 500 등)이 표시되지 않는 문제를 해결합니다. `CommonSwaggerConfig`의 `OperationCustomizer`가 정상적으로 작동하도록 수정합니다.

## 2. 분석 단계 (Plan)
- [ ] `SwaggerExample.java` 내용을 확인하여 예시 데이터 반환 여부 점검
- [ ] `CommonSwaggerConfig.java`의 `mainApi()` 빈 설정에 `operationCustomizer` 등록 여부 확인
- [ ] 현재 Swagger UI에서 응답 섹션의 `200` 외 다른 코드들이 어떻게 정의되어 있는지 확인

## 3. 설계 단계 (Design)
- [ ] `GroupedOpenApi` 빌더에 `addOperationCustomizer` 추가 설계
- [ ] 모든 HTTP 응답 상태 코드(200, 201 등)에 공통 에러 응답이 주입되도록 로직 개선 설계
- [ ] `ApiExampleExclude` 어노테이션이 정상적으로 동작하는지 검증 로직 설계

## 4. 실행 단계 (Do)
- [ ] `CommonSwaggerConfig.java` 수정 적용
- [ ] 필요 시 `SwaggerExample.java` 또는 관련 클래스 보정

## 5. 검증 단계 (Check)
- [ ] Swagger UI 접속 후 각 엔드포인트에서 공통 응답 예시 확인
- [ ] `ApiExampleExclude` 적용 시 해당 예시가 제외되는지 확인

## 6. 조치 단계 (Act)
- [ ] 문제 해결 보고서 작성 및 최종 코드 정리
