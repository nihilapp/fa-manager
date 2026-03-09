# [DESIGN] 캐릭터 DTO 필드 추가 상세 설계

## 1. 개요
CharacterEntity에 신규 추가된 26개의 필드를 CharacterInDto와 CharacterOutDto에 동기화한다.

## 2. 신규 필드 및 어노테이션 정의
모든 필드는 `String` 타입이며, `@Schema` 어노테이션을 통해 정보를 제공한다.

### 2.1 장비 필드 (12종)
- `mainHand`, `offHand`, `armor`, `head`, `gauntlet`, `boots`, `belt`, `cloak`, `accessory1`~`accessory4`

### 2.2 능력치 제한 필드 (14종)
- `reqStrDex8`~`reqStrDex14` (4종)
- `reqStr16`~`reqStr20` (3종)
- `reqCon8`~`reqCon20` (7종)

## 3. 코드 반영 위치
### 3.1 CharacterInDto
`private Integer startCurrency;` 필드 바로 뒤에 삽입. 검색을 위한 `@SearchCondition`은 별도로 추가하지 않고 입력/수정용으로 사용함.

### 3.2 CharacterOutDto
`private Integer startCurrency;` 필드 바로 뒤에 삽입.
