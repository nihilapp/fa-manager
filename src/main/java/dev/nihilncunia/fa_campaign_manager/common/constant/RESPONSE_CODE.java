package dev.nihilncunia.fa_campaign_manager.common.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RESPONSE_CODE {
  // 2xx Success
  OK("요청에 성공하였습니다. (200)"),
  CREATED("리소스가 성공적으로 생성되었습니다. (201)"),
  ACCEPTED("요청이 수락되어 비동기 처리가 진행 중입니다. (202)"),
  NO_CONTENT("성공적으로 처리되었으나 반환할 내용이 없습니다. (204)"),
  
  // 4xx Client Error
  BAD_REQUEST("잘못된 요청입니다. (400)"),
  UNAUTHORIZED("인증이 필요합니다. 로그인을 진행해 주세요. (401)"),
  FORBIDDEN("권한이 없습니다. 접근 권한을 확인해 주세요. (403)"),
  NOT_FOUND("요청하신 리소스를 찾을 수 없습니다. (404)"),
  METHOD_NOT_ALLOWED("허용되지 않은 HTTP 메서드입니다. (405)"),
  REQUEST_TIMEOUT("요청 시간이 초과되었습니다. (408)"),
  CONFLICT("리소스 충돌이 발생하였습니다. 중복 여부를 확인해 주세요. (409)"),
  GONE("리소스가 영구적으로 삭제되었습니다. (410)"),
  PRECONDITION_FAILED("선행 조건이 충족되지 않았습니다. (412)"),
  PAYLOAD_TOO_LARGE("요청 본문의 크기가 너무 큽니다. (413)"),
  UNSUPPORTED_MEDIA_TYPE("지원하지 않는 Content-Type입니다. (415)"),
  UNPROCESSABLE_ENTITY("엔티티를 처리할 수 없습니다. (422)"),
  VALIDATION_ERROR("입력값 검증에 실패하였습니다. (422)"),
  TOO_MANY_REQUESTS("요청 횟수 제한을 초과하였습니다. 잠시 후 다시 시도해 주세요. (429)"),
  
  // 권한 관련
  NOT_OWNER("해당 리소스의 소유자가 아니므로 수정 또는 삭제 권한이 없습니다."),
  NOT_PUBLIC("비공개 리소스입니다. 접근 권한을 확인해 주세요."),
  
  // 인증 관련
  INVALID_CREDENTIALS("잘못된 인증 정보입니다. 이메일 또는 비밀번호를 확인해 주세요."),
  EMAIL_ALREADY_EXISTS("이미 사용 중인 이메일입니다."),
  NAME_ALREADY_EXISTS("이미 사용 중인 이름입니다."),
  DISCORD_ID_ALREADY_EXISTS("이미 등록된 디스코드 ID입니다."),
  INVALID_TOKEN("유효하지 않은 토큰입니다."),
  INVALID_PASSWORD("비밀번호가 올바르지 않습니다."),
  USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
  TOKEN_EXPIRED("인증 토큰이 만료되었습니다. 다시 로그인해 주세요."),
  ACCOUNT_LOCKED("계정이 잠금되었습니다. 관리자에게 문의해 주세요."),

  // 캠페인 관련 (Campaign)
  CAMPAIGN_NOT_FOUND("존재하지 않는 캠페인입니다."),
  CAMPAIGN_NAME_ALREADY_EXISTS("이미 존재하는 캠페인 이름입니다."),
  CAMPAIGN_MEMBER_ALREADY_EXISTS("이미 캠페인에 참여 중인 사용자입니다."),
  CAMPAIGN_MEMBER_NOT_FOUND("해당 캠페인의 멤버가 아닙니다."),
  CAMPAIGN_CHARACTER_ALREADY_EXISTS("이미 캠페인에 소속된 캐릭터입니다."),
  CAMPAIGN_CHARACTER_NOT_FOUND("해당 캠페인에 소속되지 않은 캐릭터입니다."),

  // 캐릭터 관련 (Character)
  CHARACTER_NOT_FOUND("존재하지 않는 캐릭터입니다."),
  CHARACTER_NAME_ALREADY_EXISTS("이미 존재하는 캐릭터 이름입니다."),
  CHARACTER_OWNER_MISMATCH("해당 캐릭터의 소유자가 아닙니다."),

  // 세션 관련 (Session)
  SESSION_NOT_FOUND("존재하지 않는 세션입니다."),
  SESSION_ALREADY_FINISHED("이미 종료된 세션입니다."),
  SESSION_PLAYER_ALREADY_EXISTS("이미 세션에 참여 중인 캐릭터입니다."),
  SESSION_PLAYER_NOT_FOUND("해당 세션에 참여 중인 캐릭터가 아닙니다."),
  SESSION_LOG_NOT_FOUND("존재하지 않는 세션 로그입니다."),

  // 세계관 문서 관련 (Doc)
  DOC_NOT_FOUND("존재하지 않는 문서입니다."),
  DOC_TITLE_ALREADY_EXISTS("이미 존재하는 문서 제목입니다."),
  DOC_CATEGORY_INVALID("유효하지 않은 문서 카테고리입니다."),

  // 로그 관련 (Log)
  LOG_NOT_FOUND("존재하지 않는 로그 내역입니다."),
  LOG_TYPE_INVALID("유효하지 않은 로그 타입입니다."),

  // 5xx Server Error
  INTERNAL_SERVER_ERROR("서버 내부 오류가 발생하였습니다. (500)"),
  MEMBER_ALREADY_EXISTS("이미 캠페인에 등록된 멤버입니다."),
  CHARACTER_ALREADY_IN_CAMPAIGN("이미 다른 캠페인에 소속된 캐릭터입니다."),
  CHARACTER_NOT_IN_CAMPAIGN("해당 캠페인에 소속된 캐릭터가 아닙니다."),
  DUPLICATE_RESOURCE("이미 등록된 리소스입니다."),
  RESOURCE_NOT_FOUND("요청하신 리소스를 찾을 수 없습니다."),
  NOT_IMPLEMENTED("아직 구현되지 않은 기능입니다. (501)"),
  BAD_GATEWAY("게이트웨이 오류가 발생하였습니다. (502)"),
  SERVICE_UNAVAILABLE("현재 서비스를 사용할 수 없습니다. (503)"),
  GATEWAY_TIMEOUT("게이트웨이 응답 시간이 초과되었습니다. (504)"),
  
  // 기타
  ERROR("오류가 발생하였습니다. 구체적인 에러 코드를 확인할 수 없습니다.");
  
  private final String message;
  
  @JsonValue
  public String getCodeName() {
    return this.name();
  }
}
