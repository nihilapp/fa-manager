# PDCA Plan: 캠페인 Swagger 응답 예시 구현

## 1. 개요
`CampaignController`의 각 엔드포인트에 대한 Swagger 응답 예시를 구현하여 API 문서의 완성도를 높입니다.

## 2. 분석 단계 (Plan)
- [x] `CampaignController.java` 및 `CampaignServiceImpl.java` 내용 분석 완료
- [ ] `CampaignOutDto.java` 등 관련 DTO 구조 확인
- [ ] 캠페인 도메인 전용 성공/실패 시나리오 정의

## 3. 설계 단계 (Design)
- [ ] `CampaignExample.java` 클래스 설계 (성공/실패 데이터 정의)
- [ ] `CommonSwaggerConfig.java`에 `CampaignExample` 등록 설계

## 4. 실행 단계 (Do)
- [ ] `CampaignExample.java` 파일 생성
- [ ] `CommonSwaggerConfig.java` 수정 (주입 및 등록)
- [ ] `CampaignController.java`에 `@ApiExampleExclude` 등 필요 어노테이션 추가 (선택 사항)

## 5. 검증 단계 (Check)
- [ ] Swagger UI에서 캠페인 관련 API의 응답 예시 확인
- [ ] 성공 및 도메인 전용 실패 예시가 정상적으로 노출되는지 점검

## 6. 조치 단계 (Act)
- [ ] 컨트롤러 구현 종료 보고
