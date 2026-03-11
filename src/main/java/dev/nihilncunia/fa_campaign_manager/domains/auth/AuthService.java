package dev.nihilncunia.fa_campaign_manager.domains.auth;

import dev.nihilncunia.fa_campaign_manager.domains.auth.dto.TokenInfoDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserInDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;

public interface AuthService {
  
  /**
   * 로그인
   *
   * @param userInDto 사용자 입력 정보
   * @return 로그인한 사용자 정보
   */
  UserOutDto signIn(UserInDto userInDto);
  
  /**
   * 로그아웃
   *
   * @param userId 사용자 아이디
   */
  void signOut(Long userId);
  
  
  TokenInfoDto refreshToken(String refreshToken);
  
  void changePassword(String oldPassword, String newPassword);
}
