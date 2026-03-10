# [PLAN] 캐릭터 화폐 체계 개선 (Character Currency Improvement)

- **상태**: 계획 (PLAN)
- **담당자**: Gemini CLI (마스터 NIHILncunia 보좌)
- **작성일**: 2026-03-10

---

## 1. 개요 (Overview)
현재 캐릭터 시스템의 화폐 정보는 `Integer` 타입의 단일 필드(`startCurrency`)로 관리되고 있어, TRPG(D&D 등)의 복잡하고 사실적인 화폐 단위를 표현하는 데 한계가 있음. 마스터의 제안에 따라 5단계 화폐(CP, SP, EP, GP, PP) 체계를 도입하여 게임의 직관성과 몰입도를 높이고 데이터의 정밀도를 확보하고자 함.

## 2. 현재 상태 및 문제점 (Current State)
- **구조**: `CharacterEntity` 내부의 `startCurrency` 필드가 `Integer` 타입임.
- **데이터**: `1010`과 같이 입력될 경우, 이것이 구체적으로 어떤 동전들의 조합인지 알 수 없으며 화폐 간 환전 로직을 적용하기 어려움.
- **확장성**: 향후 상점 기능이나 아이템 구매 시 특정 단위의 동전이 부족한 상황 등을 코드로 제어하기 불가능함.

## 3. 개선 목표 (Goals)
- **화폐 다변화**: Copper, Silver, Electrum, Gold, Platinum의 5종 화폐를 각각 독립된 수치로 관리.
- **아키텍처 최적화**: `@Embeddable`을 활용하여 화폐 정보를 객체지향적으로 캡슐화.
- **DTO 일관성**: 서비스 응답 시 시작 화폐와 현재 화폐 모두 상세 단위를 포함하도록 개선.

## 4. 상세 설계 전략 (Technical Strategy)

### 4.1. 도메인 모델 변경
- **`CharacterCurrency` 생성**: `@Embeddable` 클래스로 구현.
    - `private Integer copper;` (CP)
    - `private Integer silver;` (SP)
    - `private Integer electrum;` (EP)
    - `private Integer gold;` (GP)
    - `private Integer platinum;` (PP)
- **`CharacterEntity` 수정**: `Integer startCurrency` 필드를 `CharacterCurrency` 타입으로 교체.

### 4.2. DTO 및 매퍼 업데이트
- **`CharacterCurrencyDto` 생성**: API 통신을 위한 전용 DTO.
- **`CharacterOutDto` 수정**: `currentLevel` 등과 같이 `currentCurrency` 필드를 `CharacterCurrencyDto` 타입으로 추가하여 응답.
- **MapStruct 매퍼**: 엔티티와 DTO 간의 자동 매핑 규칙 확인.

### 4.3. 예시 데이터 보완
- **`UserExample` 수정**: 5뎁스 객체 그래프 내의 모든 캐릭터 화폐 정보를 새로운 구조에 맞게 풍부하게 채움.

## 5. 작업 리스트 (Task List)
- [ ] `CharacterCurrency` @Embeddable 클래스 생성
- [ ] `CharacterEntity` 필드 타입 및 관련 어노테이션 수정
- [ ] `CharacterCurrencyDto` 클래스 생성
- [ ] `CharacterOutDto` 필드 보완 (시작 및 현재 화폐)
- [ ] `UserExample`의 예시 객체 데이터 업데이트 및 검증

## 6. 기대 효과 (Expected Outcomes)
- 시스템이 실제 TRPG 룰북과 동일한 화폐 체계를 지원하게 됨.
- 향후 자동 환전 기능(예: 10GP -> 1PP)을 객체 내부에 구현할 수 있는 기반 마련.
- API 문서(Swagger)가 더욱 전문적이고 실감 나게 변경됨.

---
**📊 bkit Feature Usage**
- ✅ **Used**: `/pdca plan character-currency-improvement`
- 💡 **Recommended**: `/pdca design character-currency-improvement`
