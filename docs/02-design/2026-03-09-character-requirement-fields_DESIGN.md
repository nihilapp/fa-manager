# [DESIGN] 캐릭터 능력치 제한 요구사항 필드 상세 설계

## 1. 개요
CharacterEntity에 캐릭터 능력치에 따른 장비 장착 제한 정보를 담을 14개의 필드를 설계한다.

## 2. 필드 및 어노테이션 정의 (14종)
모든 필드는 `String` 타입이며, JPA `@Column(length = 100)`이 적용된다.

### 2.1 힘/민첩 제한 (벨트/전낭)
- `reqStrDex8`: 힘/민첩 8제한
- `reqStrDex10`: 힘/민첩 10제한
- `reqStrDex12`: 힘/민첩 12제한
- `reqStrDex14`: 힘/민첩 14제한
- `reqStr16`: 힘 16 제한
- `reqStr18`: 힘 18 제한
- `reqStr20`: 힘 20 제한

### 2.2 건강 제한 (배낭/탈것)
- `reqCon8`: 건강 8 제한
- `reqCon10`: 건강 10 제한
- `reqCon12`: 건강 12 제한
- `reqCon14`: 건강 14 제한
- `reqCon16`: 건강 16 제한
- `reqCon18`: 건강 18 제한
- `reqCon20`: 건강 20 제한

## 3. 코드 반영 위치
`src/main/java/dev/nihilncunia/fa_campaign_manager/domains/characters/CharacterEntity.java` 파일의 `accessory4` 필드 바로 뒤에 삽입한다.
