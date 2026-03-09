# [DESIGN] 세션 로그 파일 타입 설명 업데이트 상세 설계

## 1. 개요
세션 로그 파일이 HTML/TXT 기반임을 명확히 하기 위해 각 클래스의 `fileUrl` 설명을 수정한다.

## 2. 클래스별 수정 내용
### 2.1 SessionLogEntity
- **Description**: "첨부 파일 URL (HTML, TXT 로그)"
- **Example**: "https://example.com/log/session-20260309.html"

### 2.2 SessionLogInDto
- **Description**: "첨부 파일 URL (HTML, TXT)"

### 2.3 SessionLogOutDto
- **Description**: "첨부 파일 URL (HTML, TXT)"

## 3. 코드 반영 위치
- `SessionLogEntity.java`, `SessionLogInDto.java`, `SessionLogOutDto.java`의 `fileUrl` 필드 위 `@Schema` 어노테이션.
