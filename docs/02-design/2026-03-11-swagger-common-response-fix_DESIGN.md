# PDCA Design: Swagger 공통 응답 예시 정상화

## 1. 개요
`CommonSwaggerConfig`의 설정을 보완하여 모든 API 엔드포인트에서 공통 에러 응답 예시가 정상적으로 표시되도록 설계합니다.

## 2. 주요 수정 사항

### A. GroupedOpenApi 설정 보완
- `mainApi()` 빌더에서 `OperationCustomizer`를 명시적으로 추가하여 전역 커스텀 로직이 실행되도록 보장합니다.

### B. OperationCustomizer 로직 개선
- **다중 응답 코드 지원**: `200` 뿐만 아니라 `201` 등 성공 응답(2xx) 전체를 대상으로 공통 응답을 주입합니다.
- **미디어 타입 처리**: `application/json`이 기본으로 설정되도록 보장하며, 예시 데이터가 덮어씌워지지 않도록 `LinkedHashMap`을 유지합니다.
- **예외 처리 제외 로직 강화**: `ApiExampleExclude` 어노테이션이 있는 경우 해당 키를 안전하게 제거합니다.

### C. 공통 에러 예시 주입 자동화
- `badRequest`, `unauthorized`, `forbidden`, `internal_error` 4가지 예시를 루프 또는 정적 메서드로 관리하여 코드 중복을 최소화합니다.

## 3. 상세 설계 코드 (수정 예정)

```java
@Bean
public GroupedOpenApi mainApi() {
  return GroupedOpenApi.builder()
    .group("Main API")
    .pathsToMatch("/**")
    .addOpenApiCustomizer(openApiCustomizer())
    .addOperationCustomizer(operationCustomizer()) // 명시적 추가
    .build();
}

@Bean
public OperationCustomizer operationCustomizer() {
  return (operation, handlerMethod) -> {
    ApiResponses responses = operation.getResponses();
    
    // 성공 응답(2xx)들을 찾아서 처리
    responses.entrySet().stream()
      .filter(entry -> entry.getKey().startsWith("2"))
      .forEach(entry -> {
        ApiResponse response = entry.getValue();
        // 공통 예시 주입 로직 실행
        injectCommonExamples(response);
      });
      
    // ApiExampleExclude 처리
    // ... (기존 로직 유지 및 보완)
    
    return operation;
  };
}
```

## 4. 검증 시나리오
- [ ] Swagger UI 접속 시 "Main API" 그룹 선택 확인
- [ ] 임의의 엔드포인트 응답 섹션에 4가지 공통 에러 예시가 드롭다운으로 표시되는지 확인
- [ ] `ApiExampleExclude`가 적용된 컨트롤러 메서드에서 특정 예시가 제외되는지 확인
