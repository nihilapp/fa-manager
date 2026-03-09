# [DESIGN] 캐릭터 장비 필드 상세 설계

## 1. 개요
CharacterEntity에 캐릭터가 착용 가능한 장비 정보를 담을 필드를 정의하고 관련 어노테이션을 설정한다.

## 2. 장비 필드 및 어노테이션 정의
각 필드는 `String` 타입이며, 최대 100자까지 저장 가능하다.

| 추천 컬럼명 | 한글 설명 | JPA 어노테이션 | Swagger 어노테이션 |
| :--- | :--- | :--- | :--- |
| `mainHand` | 주무장 | `@Column(length = 100)` | `@Schema(description = "주무장", example = "장검")` |
| `offHand` | 보조무장 | `@Column(length = 100)` | `@Schema(description = "보조무장", example = "목재 방패")` |
| `armor` | 갑옷 | `@Column(length = 100)` | `@Schema(description = "갑옷", example = "가죽 갑옷")` |
| `head` | 머리 | `@Column(length = 100)` | `@Schema(description = "머리", example = "철 투구")` |
| `gauntlet` | 장갑 | `@Column(length = 100)` | `@Schema(description = "장갑", example = "가죽 장갑")` |
| `boots` | 부츠 | `@Column(length = 100)` | `@Schema(description = "부츠", example = "가죽 부츠")` |
| `belt` | 벨트 | `@Column(length = 100)` | `@Schema(description = "벨트", example = "가죽 벨트")` |
| `cloak` | 망토 | `@Column(length = 100)` | `@Schema(description = "망토", example = "여행자의 망토")` |
| `accessory1` | 기타 장비 1 | `@Column(length = 100)` | `@Schema(description = "기타 장비 1", example = "은반지")` |
| `accessory2` | 기타 장비 2 | `@Column(length = 100)` | `@Schema(description = "기타 장비 2")` |
| `accessory3` | 기타 장비 3 | `@Column(length = 100)` | `@Schema(description = "기타 장비 3")` |
| `accessory4` | 기타 장비 4 | `@Column(length = 100)` | `@Schema(description = "기타 장비 4")` |

## 3. 코드 반영 위치
`src/main/java/dev/nihilncunia/fa_campaign_manager/domains/characters/CharacterEntity.java` 파일의 `startCurrency` 필드 뒤에 삽입한다.
