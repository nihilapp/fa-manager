package dev.nihilncunia.fa_campaign_manager.domains.auth;

import dev.nihilncunia.fa_campaign_manager.common.constant.RESPONSE_CODE;
import dev.nihilncunia.fa_campaign_manager.common.constant.RESPONSE_MESSAGE;
import dev.nihilncunia.fa_campaign_manager.common.response.ResponseExampleBuilder;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Swagger API 문서화를 위한 인증 관련 응답 예시 객체들을 관리하는 클래스입니다.
 */
@Component
@RequiredArgsConstructor
public class AuthExample {
  
  private final ResponseExampleBuilder exampleBuilder;
  private final UserExample userExample;
  
  /**
   * 로그인 성공 예시 (UserExample의 상세 사용자 정보 활용)
   */
  public Object authSignInSuccess() {
    return exampleBuilder.buildObject(
      userExample.getUserFullData(),
      false,
      RESPONSE_CODE.OK,
      RESPONSE_MESSAGE.AUTH_SIGN_IN_SUCCESS
    );
  }
  
  /**
   * 로그아웃 성공 예시
   */
  public Object authSignOutSuccess() {
    return exampleBuilder.buildObject(
      null,
      false,
      RESPONSE_CODE.OK,
      RESPONSE_MESSAGE.AUTH_SIGN_OUT_SUCCESS
    );
  }
  
  /**
   * 토큰 재발급 성공 예시
   */
  public Object authRefreshSuccess() {
    return exampleBuilder.buildObject(
      null,
      false,
      RESPONSE_CODE.OK,
      RESPONSE_MESSAGE.AUTH_REFRESH_SUCCESS
    );
  }
  
  /**
   * 잘못된 자격 증명 (로그인 실패)
   */
  public Object authFailInvalidCredentials() {
    return exampleBuilder.buildObject(
      null,
      true,
      RESPONSE_CODE.UNAUTHORIZED,
      RESPONSE_MESSAGE.AUTH_INVALID_CREDENTIALS
    );
  }
  
  /**
   * 유효하지 않은 토큰 (재발급 실패)
   */
  public Object authFailInvalidToken() {
    return exampleBuilder.buildObject(
      null,
      true,
      RESPONSE_CODE.UNAUTHORIZED,
      RESPONSE_MESSAGE.AUTH_INVALID_TOKEN
    );
  }
  
  /**
   * 토큰 정보 불일치
   */
  public Object authFailTokenMismatch() {
    return exampleBuilder.buildObject(
      null,
      true,
      RESPONSE_CODE.UNAUTHORIZED,
      RESPONSE_MESSAGE.AUTH_TOKEN_MISMATCH
    );
  }
  
  /**
   * 비밀번호 변경 시 현재 비밀번호 불일치
   */
  public Object authFailPasswordMismatch() {
    return exampleBuilder.buildObject(
      null,
      true,
      RESPONSE_CODE.BAD_REQUEST,
      RESPONSE_MESSAGE.DEFAULT_BAD_REQUEST // 서비스 로직에서 "현재 비밀번호가 일치하지 않습니다" 메시지 사용 중
    );
  }
}
