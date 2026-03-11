package dev.nihilncunia.fa_campaign_manager.domains.auth;

import dev.nihilncunia.fa_campaign_manager.common.annotation.ApiExampleExclude;
import dev.nihilncunia.fa_campaign_manager.common.annotation.IsUser;
import dev.nihilncunia.fa_campaign_manager.common.constant.RESPONSE_CODE;
import dev.nihilncunia.fa_campaign_manager.common.constant.RESPONSE_MESSAGE;
import dev.nihilncunia.fa_campaign_manager.common.helper.JwtProvider;
import dev.nihilncunia.fa_campaign_manager.common.response.BaseResponse;
import dev.nihilncunia.fa_campaign_manager.common.security.CurrentUserProvider;
import dev.nihilncunia.fa_campaign_manager.domains.auth.dto.TokenInfoDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserInDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "인증 관리")
public class AuthController {
  
  private final AuthService authService;
  private final JwtProvider jwtProvider;
  
  @PostMapping("/signin")
  @Operation(summary = "로그인", description = "이메일과 비밀번호를 사용하여 로그인합니다. " +
    "성공 시 액세스 토큰과 리프레시 토큰이 쿠키로 발급됩니다.")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = BaseResponse.class),
        examples = {
          @ExampleObject(name = "로그인 성공", ref = "#/components/examples/authSignInSuccess"),
          @ExampleObject(name = "로그인 실패 (자격 증명 오류)", ref = "#/components/examples/authFailInvalidCredentials")
        }
      )
    )
  })
  @ApiExampleExclude(keys = { "인증 실패", "권한 부족", "잘못된 요청" })
  public BaseResponse<UserOutDto> signIn(@RequestBody UserInDto userInDto, HttpServletResponse response) {
    UserOutDto userOutDto = authService.signIn(userInDto);
    
    // 액세스 토큰 쿠키 생성 (1시간)
    String accessToken = jwtProvider.createAccessToken(userOutDto.getId(), userOutDto.getEmail(), userOutDto.getRole().name());
    Cookie accessCookie = jwtProvider.createCookie("accessToken", accessToken, 3600);
    response.addCookie(accessCookie);
    
    // 리프레시 토큰 쿠키 생성 (7일)
    String refreshToken = jwtProvider.createRefreshToken(userOutDto.getId(), userOutDto.getEmail(), userOutDto.getRole().name());
    Cookie refreshCookie = jwtProvider.createCookie("refreshToken", refreshToken, 7 * 24 * 3600);
    response.addCookie(refreshCookie);
    
    return BaseResponse.ok(userOutDto, RESPONSE_CODE.OK, RESPONSE_MESSAGE.AUTH_SIGN_IN_SUCCESS);
  }
  
  @IsUser
  @PostMapping("/signout")
  @Operation(summary = "로그아웃", description = "현재 세션을 종료하고 브라우저에 저장된 인증 쿠키를 삭제합니다.")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = BaseResponse.class),
        examples = {
          @ExampleObject(name = "로그아웃 성공", ref = "#/components/examples/authSignOutSuccess")
        }
      )
    )
  })
  @ApiExampleExclude(keys = { "권한 부족", "잘못된 요청" })
  public BaseResponse<Void> signOut(HttpServletResponse response) {
    Long userId = CurrentUserProvider.getCurrentUserId().orElseThrow();
    authService.signOut(userId);
    
    // 쿠키 제거
    Cookie accessCookie = jwtProvider.createCookie("accessToken", null, 0);
    Cookie refreshCookie = jwtProvider.createCookie("refreshToken", null, 0);
    response.addCookie(accessCookie);
    response.addCookie(refreshCookie);
    
    return BaseResponse.ok(null, RESPONSE_CODE.OK, RESPONSE_MESSAGE.AUTH_SIGN_OUT_SUCCESS);
  }
  
  @PostMapping("/refresh")
  @Operation(summary = "토큰 재발급", description = "리프레시 토큰 쿠키를 사용하여 액세스 토큰을 갱신합니다.")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = BaseResponse.class),
        examples = {
          @ExampleObject(name = "토큰 재발급 성공", ref = "#/components/examples/authRefreshSuccess"),
          @ExampleObject(name = "실패 (유효하지 않은 토큰)", ref = "#/components/examples/authFailInvalidToken"),
          @ExampleObject(name = "실패 (토큰 정보 불일치)", ref = "#/components/examples/authFailTokenMismatch")
        }
      )
    )
  })
  @ApiExampleExclude(keys = { "인증 실패", "권한 부족", "잘못된 요청" })
  public BaseResponse<Void> refresh(
    @CookieValue(value = "refreshToken", required = false) String refreshToken,
    HttpServletResponse response) {
    
    TokenInfoDto tokens = authService.refreshToken(refreshToken);
    
    // 새로운 액세스 토큰 쿠키 설정 (1시간)
    Cookie accessCookie = jwtProvider.createCookie("accessToken", tokens.getAccessToken(), 3600);
    response.addCookie(accessCookie);
    
    // 새로운 리프레시 토큰 쿠키 설정 (7일)
    Cookie refreshCookie = jwtProvider.createCookie("refreshToken", tokens.getRefreshToken(), 7 * 24 * 3600);
    response.addCookie(refreshCookie);
    
    return BaseResponse.ok(null, RESPONSE_CODE.OK, RESPONSE_MESSAGE.AUTH_REFRESH_SUCCESS);
  }
  
  @IsUser
  @PostMapping("/password")
  @Operation(summary = "비밀번호 변경", description = "현재 로그인된 사용자의 비밀번호를 변경합니다.")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = BaseResponse.class),
        examples = {
          @ExampleObject(name = "비밀번호 변경 성공", ref = "#/components/examples/authRefreshSuccess"), // 메시지는 다르지만 구조 동일 활용
          @ExampleObject(name = "실패 (현재 비밀번호 불일치)", ref = "#/components/examples/authFailPasswordMismatch")
        }
      )
    )
  })
  @ApiExampleExclude(keys = { "권한 부족", "잘못된 요청" })
  public BaseResponse<Void> changePassword(@RequestBody Map<String, String> request) {
    authService.changePassword(request.get("oldPassword"), request.get("newPassword"));
    
    return BaseResponse.ok(null, RESPONSE_CODE.OK, RESPONSE_MESSAGE.DEFAULT_OK);
  }
}
