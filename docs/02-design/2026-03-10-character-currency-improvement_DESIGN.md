# [DESIGN] 캐릭터 화폐 체계 개선 (Character Currency Improvement)

- **상태**: 설계 (DESIGN)
- **담당자**: Gemini CLI (마스터 NIHILncunia 보좌)
- **참조 계획**: [2026-03-10-character-currency-improvement_PLAN.md](../../01-plan/2026-03-10-character-currency-improvement_PLAN.md)

---

## 1. 설계 목표 (Design Goals)
5단계 화폐 체계를 시스템 전반에 걸쳐 타입 안전(Type-safe)하게 구현하고, API 응답 시 시작 수치와 현재 수치를 모두 명확히 제공함.

## 2. 클래스 상세 설계 (Class Specifications)

### 2.1. [Entity] CharacterCurrency (Embeddable)
- **패키지**: `dev.nihilncunia.fa_campaign_manager.domains.characters`
- **구조**:
    - `private Integer copper = 0;` (CP)
    - `private Integer silver = 0;` (SP)
    - `private Integer electrum = 0;` (EP)
    - `private Integer gold = 0;` (GP)
    - `private Integer platinum = 0;` (PP)
- **어노테이션**: `@Embeddable`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Getter`, `@Setter`

### 2.2. [DTO] CharacterCurrencyDto
- **패키지**: `dev.nihilncunia.fa_campaign_manager.domains.characters.dto`
- **구성**: 위 Entity와 동일한 5개 필드.
- **제약 사항**: 각 필드에 `@Min(0)` 적용하여 음수 값 방지.

## 3. 기존 코드 수정 설계 (Modification Strategy)

### 3.1. CharacterEntity 수정
- `private Integer startCurrency;` 필드를 삭제.
- `@Embedded` 어노테이션과 함께 `private CharacterCurrency startCurrency;` 필드 추가.
- `@AttributeOverrides`를 사용하여 DB 컬럼명을 명확히 지정 (예: `currency_cp`, `currency_sp` 등).

### 3.2. CharacterOutDto 수정
- `private Integer startCurrency;` -> `private CharacterCurrencyDto startCurrency;`
- `private Integer currentCurrency;` -> `private CharacterCurrencyDto currentCurrency;`

### 3.3. UserExample 업데이트
- 기존의 단순 숫자 대신 `CharacterCurrencyDto.builder()`를 사용하여 상세 화폐 데이터를 채움.
- 예: `gold(100), silver(50), copper(10)` 등.

## 4. 데이터베이스 매핑 (DB Mapping)
- 테이블명: `characters`
- 추가/변경 컬럼:
    - `start_currency_cp`, `start_currency_sp`, `start_currency_ep`, `start_currency_gp`, `start_currency_pp` (모두 `int` 타입)

## 5. 비즈니스 로직 (Business Logic)
- **기본값**: 엔티티 생성 시 모든 화폐 필드는 0으로 초기화됨.
- **환전 로직**: (본 단계에서는 데이터 구조만 구현하며, 자동 환전 서비스는 향후 고도화 단계에서 고려)

---
**📊 bkit Feature Usage**
- ✅ **Used**: `/pdca design character-currency-improvement` (직접 작성)
- 💡 **Recommended**: `/pdca do character-currency-improvement`
