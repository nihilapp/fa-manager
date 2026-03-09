# [DESIGN] 세션 플레이 로그 도메인 상세 설계

## 1. 개요
`SessionLogEntity`와 그에 대응하는 DTO들의 구조를 정의한다.

## 2. SessionLogEntity 상세 정의
- **ManyToOne**: `session` (SessionEntity), `user` (UserEntity)
- **Fields**:
  - `title`: 제목 (String, nullable=false, length=100)
  - `content`: 내용 (String, columnDefinition="TEXT")
  - `fileUrl`: 이미지 등 첨부파일 URL (String, length=255)
- **Indexes**: `idx_session_logs_session_id`, `idx_session_logs_user_id`

## 3. DTO 정의
### 3.1 SessionLogInDto
- `sessionId`: 대상 세션 ID (필수)
- `userId`: 작성자 ID (필수)
- `title`: 제목 (필수, @NotBlank)
- `content`: 내용
- `fileUrl`: 파일 URL

### 3.2 SessionLogOutDto
- `id`: 로그 ID
- `sessionId`: 세션 ID
- `sessionName`: 세션명
- `userId`: 작성자 ID
- `userName`: 작성자 이름
- `title`: 제목
- `content`: 내용
- `fileUrl`: 파일 URL
- `createdAt`, `updatedAt` 등 공통 필드 포함

## 4. 파일 위치
- `SessionLogEntity.java`: `src/main/java/dev/nihilncunia/fa_campaign_manager/domains/sessions/SessionLogEntity.java`
- `dto` 패키지 생성: `src/main/java/dev/nihilncunia/fa_campaign_manager/domains/sessions/dto/`
