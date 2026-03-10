캠페인(Campaigns)과 이미 구현된 인증/사용자(Auth/Users) 도메인을 제외하고, 앞으로 마스터께서 정복하셔야 할 나머지 API 엔드포인트들을 도메인별로 정리해 대령하겠습니다. (작성하신 엔티티 명칭에 맞추어 세계관 문서는 Docs로 표기하였습니다.)

⚔️ 1. 캐릭터 및 클래스 (Characters)
캐릭터의 진척도와 자산을 관리하는 도메인입니다.

GET /characters : 보유 캐릭터 목록 조회 (검색 및 페이징)

POST /characters : 새로운 캐릭터 생성 (이름, 종족, 초기 스탯 등)

GET /characters/{id} : 캐릭터 상세 조회

PUT /characters/{id} : 캐릭터 기본 설정 정보 수정

DELETE /characters/{id} : 캐릭터 삭제 (논리적 삭제)

PUT /characters/{id}/status : 캐릭터 상태 변경 (활동, 휴식, 사망 등)

PATCH /characters/{id}/wealth : 캐릭터 소지금 단순 조정

GET /characters/{id}/classes : 캐릭터 보유 클래스 목록 조회

POST /characters/{id}/classes : 캐릭터 클래스 추가 (멀티클래스)

PUT /characters/{id}/classes/{className} : 특정 클래스 정보 수정 (레벨업 등)

DELETE /characters/{id}/classes/{className} : 특정 클래스 제거

🎲 2. 세션 (Sessions)
캠페인 내 개별 에피소드와 참여자를 관리합니다. (캠페인과 마찬가지로, 세션 상세 조회 시 참여자 목록을 함께 불러오도록 구성하시면 아래의 GET /sessions/{id}/players는 생략하실 수 있습니다.)

GET /sessions : 세션 목록 조회

POST /sessions : 새로운 세션 생성 및 보상 수치 설정

GET /sessions/{id} : 세션 상세 정보 (및 참여 명단) 조회

PUT /sessions/{id} : 세션 정보(이름, 보상 수치, 상태 등) 수정

DELETE /sessions/{id} : 세션 삭제 (논리적 삭제)

POST /sessions/{id}/players : 특정 캐릭터를 해당 세션의 플레이어로 참전 등록

DELETE /sessions/{id}/players/{userId} : 세션 참가자 제외 및 취소

📜 3. 세계관 문서 (Docs)
커뮤니티 전체가 공유하는 설정 백과사전입니다.

GET /docs : 세계관 설정 문서 목록 조회 (카테고리, 태그 등 검색)

POST /docs : 새로운 세계관 문서 작성

GET /docs/{id} : 설정 문서 상세 내용 열람

PUT /docs/{id} : 기존 세계관 문서 내용 수정 및 갱신

DELETE /docs/{id} : 세계관 문서 삭제 (논리적 삭제)

⚙️ 4. 시스템 로그 (Logs)
데이터 변경 이력을 추적하고 복원하는 관리자용 기능입니다.

GET /logs : 시스템 로그 이력 목록 조회

GET /logs/{id} : 로그 상세 스냅샷 조회

POST /logs/{id}/restore : 과거 로그 스냅샷을 기반으로 데이터 롤백 및 복원

이상이 현재까지 구상된 시스템의 남은 여정입니다. 어느 도메인의 뼈대부터 다시 세워 올리실지 분부만 내려주시면, 즉각 보조할 준비를 갖추겠습니다.