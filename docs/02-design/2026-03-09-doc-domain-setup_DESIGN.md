# [DESIGN] 세계관 설정 문서(Doc) 도메인 상세 설계

## 1. 개요
문서 엔티티와 연관 관계, 입출력 DTO의 세부 명세를 정의한다.

## 2. DocEntity 상세 정의
- **Relation**: `@ManyToOne` (UserEntity)
- **Fields**:
  - `title`: 제목 (String, nullable=false, length=200)
  - `description`: 간단한 설명 (String, length=500)
  - `category`: 문서 카테고리 (DOC_CATEGORY, EnumType.STRING)
  - `content`: 문서 본문 (String, columnDefinition="TEXT") - JSON 데이터 저장용
- **Indexes**: `idx_docs_title`, `idx_docs_category`, `idx_docs_user_id`

## 3. UserEntity 관계 추가
- `private List<DocEntity> docs`: 작성한 문서 목록 추가.

## 4. DTO 설계
### 4.1 DocInDto
- `userId`: 작성자 ID (필수)
- `title`: 제목 (필수, @NotBlank)
- `description`: 설명
- `category`: 카테고리
- `content`: 본문 (JSON 문자열)

### 4.2 DocOutDto
- `id`: 문서 ID
- `userId`: 작성자 ID
- `userName`: 작성자 이름
- `title`: 제목
- `description`: 설명
- `category`: 카테고리 (코드 및 한글 명칭 포함)
- `content`: 본문 (JSON 문자열)
- `createdAt`, `updatedAt` 등 포함

## 5. 파일 위치
- `DocEntity.java`: `src/main/java/dev/nihilncunia/fa_campaign_manager/domains/docs/DocEntity.java`
- `DocInDto.java`: `src/main/java/dev/nihilncunia/fa_campaign_manager/domains/docs/dto/DocInDto.java`
- `DocOutDto.java`: `src/main/java/dev/nihilncunia/fa_campaign_manager/domains/docs/dto/DocOutDto.java`
