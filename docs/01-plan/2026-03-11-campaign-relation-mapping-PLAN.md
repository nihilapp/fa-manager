---
template: plan
version: 1.2
description: Campaign Relation Mapping Plan
---

# Campaign Relation Mapping Planning Document

> **Summary**: CampaignOutDto 응답 시 하위 연관 엔티티(Session, Character, Member) 자동 매핑 및 필드 통일
>
> **Project**: FA Campaign Manager
> **Author**: Antigravity
> **Date**: 2026-03-11
> **Status**: Draft

---

## 1. Overview

### 1.1 Purpose
캠페인 조회 시 하위 연관 데이터(세션, 캐릭터, 유저 리스트)가 `null`로 나오는 문제를 해결하고, JPA 연관 관계에 맞게 자동으로 데이터가 DTO에 매핑되도록 구조를 개선합니다.

### 1.2 Background
현재 `CampaignEntity`의 필드는 `sessions`, `characters`, `members`이나, `CampaignOutDto`는 `sessionList`, `characterList`, `memberList`로 필드명이 불일치합니다. 또한 `CampaignMemberEntity`에서 `UserOutDto`로의 변환 로직이 누락되어 수동으로 데이터를 넣어줘야 하는 불편함이 있습니다.

---

## 2. Scope

### 2.1 In Scope
- [ ] `CampaignOutDto` 하위 컬렉션 필드명을 엔티티와 일치시키기 (예: `sessionList` -> `sessions`)
- [ ] `CampaignMapper`에 `CampaignMemberEntity` -> `UserOutDto` 변환 커스텀 로직 추가
- [ ] MapStruct의 `@Mapper(uses = {...})`를 활용한 하위 매퍼 연결
- [ ] 데이터 조회 시 N+1 쿼리 최적화 여부 검토

### 2.2 Out of Scope
- 페이징 처리 관련 구조 대규모 수정
- 캠페인 도메인 외 다른 도메인의 매핑 로직 수정

---

## 3. Requirements

### 3.1 Functional Requirements

| ID | Requirement | Priority | Status |
|----|-------------|----------|--------|
| FR-01 | CampaignOutDto의 컬렉션 필드명을 Entity와 동일하게 수정 (`sessions`, `characters`, `members`) | High | Pending |
| FR-02 | CampaignMapper에서 멤버 컬렉션을 자동 매핑하도록 default 메서드 구현 (CampaignMember -> User) | High | Pending |
| FR-03 | MapStruct 컴파일 시 자동 매핑이 정상적으로 엮이는지 확인 | High | Pending |

### 3.2 Non-Functional Requirements

| Category | Criteria | Measurement Method |
|----------|----------|-------------------|
| Performance | 캠페인 상세 조회 시 N+1 쿼리 방지 | Hibernate SQL 로그 조회 및 `@EntityGraph` / `BatchSize` 적용 확인 |

---

## 4. Success Criteria

### 4.1 Definition of Done
- [ ] 응답 DTO 필드명 수정 및 Swagger 스키마 갱신 완료
- [ ] Campaign 조회 API 호출 시 하위 컬렉션 데이터 정상 반환 
- [ ] Unit/Integration Test 또는 로컬 구동 테스트 통과

---

## 5. Risks and Mitigation

| Risk | Impact | Likelihood | Mitigation |
|------|--------|------------|------------|
| 프론트엔드 연동 오류 | High | Medium | DTO 필드명 변경(`xxxList` -> `xxx`) 사실을 클라이언트(REST API 사용자)에게 파악 및 공지 필요 |
| N+1 쿼리로 인한 성능 저하 | Medium | High | CampaignRepository 등에서 `@EntityGraph`나 FETCH JOIN 사용 혹은 글로벌 배치 사이즈 설정 확인 |

---

## 6. Architecture Considerations

| Category | Current State | To Define | Priority |
|----------|---------------|-----------|:--------:|
| **Naming** | `sessionList` (DTO) vs `sessions` (Entity) | DTO 필드명도 `sessions` 등 복수형 명사로 통일 | High |
| **Mapping** | 수동 작성 또는 null 방치 | MapStruct 기반 자동 매핑 (`uses` 속성 및 default 메서드 활용) | High |

---

## 7. Next Steps

1. [ ] 플랜 검토 및 승인 대기
2. [ ] `/pdca design` 실행을 통한 구체적 기술 설계 진행
3. [ ] `/pdca do` 실행을 통한 코드 구현

