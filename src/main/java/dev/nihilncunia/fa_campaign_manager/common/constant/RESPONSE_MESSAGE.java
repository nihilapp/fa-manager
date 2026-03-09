package dev.nihilncunia.fa_campaign_manager.common.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.text.MessageFormat;

/**
 * 응답 메시지 내용을 관리하는 Enum 클래스입니다.
 */
@Getter
@RequiredArgsConstructor
public enum RESPONSE_MESSAGE {
  // 공통 (Common/Default)
  DEFAULT_OK("요청에 성공하였습니다."),
  DEFAULT_CREATED("리소스가 성공적으로 생성되었습니다."),
  DEFAULT_BAD_REQUEST("잘못된 요청입니다."),
  DEFAULT_UNAUTHORIZED("인증에 실패하였습니다. 로그인을 진행해 주세요."),
  DEFAULT_FORBIDDEN("권한이 없습니다. 접근 권한을 확인해 주세요."),
  DEFAULT_NOT_FOUND("요청하신 리소스를 찾을 수 없습니다."),
  DEFAULT_CONFLICT("리소스 충돌이 발생하였습니다."),
  DEFAULT_INTERNAL_SERVER_ERROR("서버 내부 오류가 발생하였습니다."),
  DEFAULT_BAD_GATEWAY("잘못된 게이트웨이 응답입니다."),
  DEFAULT_VALIDATION_ERROR("입력값 검증에 실패하였습니다."),
  DEFAULT_VALIDATION_ERROR_DETAILS("입력값 검증에 실패하였습니다. ({0})"),
  DEFAULT_MESSAGE_NOT_READABLE("요청 본문을 읽을 수 없습니다. 바디 누락 또는 형식 오류를 확인해 주세요."),
  DEFAULT_MEDIA_TYPE_NOT_SUPPORTED("지원되지 않는 Content-Type입니다. application/json 형식이 필요합니다."),

  // 서버 상태 (Health)
  HEALTH_SUCCESS("서버 시스템이 정상입니다."),
  HEALTH_FAIL("서버 시스템에 오류가 발생하였습니다."),

  // 인증 (Auth)
  AUTH_SIGN_IN_SUCCESS("로그인에 성공하였습니다."),
  AUTH_SIGN_OUT_SUCCESS("로그아웃 처리가 성공적으로 완료되었습니다."),
  AUTH_REFRESH_SUCCESS("인증 토큰 재발급에 성공하였습니다."),
  AUTH_PASSWORD_CHANGE_SUCCESS("비밀번호가 성공적으로 변경되었습니다."),
  AUTH_INVALID_TOKEN("유효하지 않은 토큰입니다."),
  AUTH_TOKEN_MISMATCH("토큰 정보가 일치하지 않습니다."),
  AUTH_INVALID_CREDENTIALS("잘못된 인증 정보입니다. 이메일 또는 비밀번호를 확인해 주세요."),

  // 사용자 (User)
  USER_GET_LIST_SUCCESS("사용자 목록 조회에 성공하였습니다."),
  USER_GET_PAGE_SUCCESS("사용자 페이징 목록 조회에 성공하였습니다."),
  USER_GET_DETAIL_SUCCESS("사용자 정보 조회에 성공하였습니다."),
  USER_CREATE_SUCCESS("사용자가 성공적으로 생성되었습니다."),
  USER_UPDATE_SUCCESS("사용자 정보가 수정되었습니다."),
  USER_DELETE_SUCCESS("사용자 계정이 삭제되었습니다."),
  USER_NOT_FOUND("사용자를 찾을 수 없습니다. (ID: {0})"),
  USER_EMAIL_NOT_FOUND("해당 이메일을 가진 사용자를 찾을 수 없습니다. (Email: {0})"),
  USER_NAME_CONFLICT("이미 존재하는 이름입니다. (이름: {0})"),
  USER_EMAIL_CONFLICT("이미 존재하는 이메일입니다. (이메일: {0})"),
  USER_NAME_IN_USE("이미 사용 중인 이름입니다. 다른 이름을 입력해 주세요."),
  USER_DISCORD_ID_REQUIRED("디스코드 사용자 ID가 필요합니다."),
  USER_DISCORD_ID_CONFLICT("이미 등록된 디스코드 ID입니다."),

  // 캠페인 (Campaign)
  CAMPAIGN_GET_LIST_SUCCESS("캠페인 목록 조회에 성공하였습니다."),
  CAMPAIGN_GET_DETAIL_SUCCESS("캠페인 정보 조회에 성공하였습니다."),
  CAMPAIGN_CREATE_SUCCESS("캠페인이 성공적으로 생성되었습니다."),
  CAMPAIGN_UPDATE_SUCCESS("캠페인 정보 수정이 완료되었습니다."),
  CAMPAIGN_DELETE_SUCCESS("캠페인이 성공적으로 삭제되었습니다."),
  CAMPAIGN_NOT_FOUND("요청하신 캠페인을 찾을 수 없습니다. (ID: {0})"),
  CAMPAIGN_NAME_CONFLICT("이미 존재하는 캠페인 명입니다. (이름: {0})"),

  // 캠페인 멤버 (Campaign Member)
  CAMPAIGN_MEMBER_GET_LIST_SUCCESS("캠페인 멤버 목록 조회에 성공하였습니다."),
  CAMPAIGN_MEMBER_ADD_SUCCESS("사용자({0})가 캠페인 멤버로 추가되었습니다."),
  CAMPAIGN_MEMBER_REMOVE_SUCCESS("사용자({0})가 캠페인 멤버에서 제외되었습니다."),
  CAMPAIGN_MEMBER_NOT_FOUND("해당 캠페인에 소속된 멤버가 아닙니다. (사용자 ID: {0})"),
  CAMPAIGN_MEMBER_CONFLICT("이미 캠페인에 참여 중인 사용자입니다. (사용자: {0})"),

  // 캠페인 캐릭터 (Campaign Character)
  CAMPAIGN_CHARACTER_ADD_SUCCESS("캐릭터({0})가 캠페인에 소속되었습니다."),
  CAMPAIGN_CHARACTER_REMOVE_SUCCESS("캐릭터({0})가 캠페인에서 제외되었습니다."),
  CAMPAIGN_CHARACTER_NOT_FOUND("해당 캠페인에 소속된 캐릭터가 아닙니다. (캐릭터: {0})"),
  CAMPAIGN_CHARACTER_CONFLICT("이미 다른 캠페인에 소속된 캐릭터입니다. (캐릭터: {0})"),

  // 캐릭터 (Character)
  CHARACTER_GET_LIST_SUCCESS("캐릭터 목록 조회에 성공하였습니다."),
  CHARACTER_GET_DETAIL_SUCCESS("캐릭터 정보 조회에 성공하였습니다."),
  CHARACTER_CREATE_SUCCESS("캐릭터가 성공적으로 생성되었습니다."),
  CHARACTER_UPDATE_SUCCESS("캐릭터 정보가 수정되었습니다."),
  CHARACTER_DELETE_SUCCESS("캐릭터가 성공적으로 삭제되었습니다."),
  CHARACTER_NOT_FOUND("캐릭터 정보를 찾을 수 없습니다. (ID: {0})"),
  CHARACTER_NAME_CONFLICT("이미 존재하는 캐릭터 명입니다. (이름: {0})"),
  CHARACTER_OWNER_CONFLICT("본인이 소유한 캐릭터만 조작할 수 있습니다."),

  // 세계관 문서 (Doc)
  DOC_GET_LIST_SUCCESS("문서 목록 조회에 성공하였습니다."),
  DOC_GET_DETAIL_SUCCESS("문서 정보 조회에 성공하였습니다."),
  DOC_CREATE_SUCCESS("세계관 문서가 성공적으로 작성되었습니다."),
  DOC_UPDATE_SUCCESS("문서 정보가 수정되었습니다."),
  DOC_DELETE_SUCCESS("문서가 성공적으로 삭제되었습니다."),
  DOC_NOT_FOUND("요청하신 문서를 찾을 수 없습니다. (ID: {0})"),
  DOC_TITLE_CONFLICT("이미 존재하는 문서 제목입니다. (제목: {0})"),

  // 세션 (Session)
  SESSION_GET_LIST_SUCCESS("세션 목록 조회에 성공하였습니다."),
  SESSION_GET_DETAIL_SUCCESS("세션 정보 조회에 성공하였습니다."),
  SESSION_CREATE_SUCCESS("세션이 성공적으로 생성되었습니다."),
  SESSION_UPDATE_SUCCESS("세션 정보가 수정되었습니다."),
  SESSION_DELETE_SUCCESS("세션이 삭제되었습니다."),
  SESSION_NOT_FOUND("요청하신 세션을 찾을 수 없습니다. (ID: {0})"),
  SESSION_ALREADY_FINISHED("이미 종료된 세션은 수정할 수 없습니다."),

  // 세션 플레이어 (Session Player)
  SESSION_PLAYER_ADD_SUCCESS("캐릭터({0})가 세션 플레이어로 등록되었습니다."),
  SESSION_PLAYER_REMOVE_SUCCESS("캐릭터({0})가 세션 플레이어에서 제외되었습니다."),
  SESSION_PLAYER_NOT_FOUND("해당 세션에 참여 중인 캐릭터가 아닙니다. (캐릭터: {0})"),
  SESSION_PLAYER_CONFLICT("이미 해당 세션에 참여 중인 캐릭터입니다. (캐릭터: {0})"),

  // 세션 로그 (Session Log)
  SESSION_LOG_GET_LIST_SUCCESS("세션 로그 목록 조회에 성공하였습니다."),
  SESSION_LOG_CREATE_SUCCESS("세션 로그가 성공적으로 작성되었습니다."),
  SESSION_LOG_UPDATE_SUCCESS("세션 로그가 수정되었습니다."),
  SESSION_LOG_DELETE_SUCCESS("세션 로그가 삭제되었습니다."),
  SESSION_LOG_NOT_FOUND("요청하신 세션 로그를 찾을 수 없습니다. (ID: {0})"),

  // 로그 히스토리 (Log)
  LOG_GET_LIST_SUCCESS("로그 내역 조회에 성공하였습니다."),
  LOG_GET_DETAIL_SUCCESS("로그 상세 조회에 성공하였습니다."),
  LOG_NOT_FOUND("로그 내역을 찾을 수 없습니다. (ID: {0})");

  @JsonValue
  private final String message;

  /**
   * 메시지 인자를 포함하여 포맷팅된 메시지를 반환합니다.
   *
   * @param args 메시지 인자
   * @return 포맷팅된 메시지
   */
  public String getMessage(Object... args) {
    if (args == null || args.length == 0) {
      return this.message;
    }
    return MessageFormat.format(this.message, args);
  }

  @Override
  public String toString() {
    return this.message;
  }
}
