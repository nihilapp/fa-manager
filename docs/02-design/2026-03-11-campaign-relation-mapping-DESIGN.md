---
template: design
version: 1.2
description: Campaign Relation Mapping Design
---

# Campaign Relation Mapping Design Document

> **Summary**: CampaignOutDto 하위 엔티티 연관 매핑 및 N+1 쿼리 최적화 설계
>
> **Project**: FA Campaign Manager
> **Author**: Antigravity
> **Date**: 2026-03-11
> **Status**: Draft
> **Planning Doc**: [2026-03-11-campaign-relation-mapping-PLAN.md](../01-plan/2026-03-11-campaign-relation-mapping-PLAN.md)

---

## 1. Overview

### 1.1 Design Goals
현재 API 응답 시 조립되지 않던 캠페인 하위 컬렉션(세션, 캐릭터, 멤버) 데이터를 MapStruct를 이용해 안전하고 자동화된 방식으로 매핑하며, JPA 연관관계 탐색 시 발생하는 N+1 쿼리 성능 저하를 방지합니다.

### 1.2 Design Principles
- **구조 통일**: DTO와 Entity의 필드 명명 규칙을 철저히 일치시켜 보일러플레이트 코드를 최소화.
- **성능 최적화**: 다대일, 일대다 관계 조회 시 N+1 문제를 방지하기 위한 Hibernate 전용 로딩 최적화 적용.

---

## 2. Architecture

### 2.1 Component Dependencies
- `CampaignController` -> `CampaignService` -> `CampaignRepository` & `CampaignMapper`
- `CampaignMapper`는 내부적으로 `SessionMapper`와 `CharacterMapper`를 참조하여 하위 계층 변환을 위임합니다.

---

## 3. Data Model & Response Structure

### 3.1 DTO 수정 사항 (`CampaignOutDto`)
- `sessionList` -> `sessions`
- `characterList` -> `characters`
- `memberList` -> `members`
- 타입은 기존에 등록된 `SessionOutDto`, `CharacterOutDto`, `UserOutDto` 리스트를 그대로 유지합니다.

### 3.2 Mapper 로직 (`CampaignMapper`)
- `@Mapper(uses = {SessionMapper.class, CharacterMapper.class, UserMapper.class})` 선언을 명시적으로 추가.
- `UserOutDto mapCampaignMemberToUserOutDto(CampaignMemberEntity member)` default 메서드를 내부에 작성하여 중간 엔티티를 우회하도록 설계. (또는 UserMapper 주입 방식)

### 3.3 JPA 쿼리 최적화 (`CampaignRepository`)
- `@EntityGraph(attributePaths = {"sessions", "characters", "members.user"})`
  - `findById` 오버라이딩을 통해 캠페인 조회 시 한 번의 Left Outer Join으로 연관 데이터를 Eager 로딩하여 N+1 방지.

---

## 4. API Specification

### Endpoint: `GET /campaigns/{id}`

**기대되는 응답 (200 OK):**
```json
{
  "code": 200,
  "message": "캠페인 상세 조회 성공",
  "data": {
    "id": 1,
    "name": "캠페인 룸1",
    "sessions": [
      { "id": 1, "title": "세션 1화" }
    ],
    "characters": [
      { "id": 1, "name": "전사" }
    ],
    "members": [
      { "id": 1, "name": "유저1" }
    ]
  }
}
```

---

## 5. Next Steps

1. `CampaignOutDto.java` 필드명 변경 적용.
2. `CampaignMapper.java` 에 `@Mapper(uses)` 및 `default mapMember` 구현.
3. `CampaignRepository.java` 에 `@EntityGraph` 적용.
4. Swagger UI 등을 통해 응답 형태 검증.
