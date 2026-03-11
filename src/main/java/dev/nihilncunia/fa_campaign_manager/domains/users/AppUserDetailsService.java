package dev.nihilncunia.fa_campaign_manager.domains.users;

import dev.nihilncunia.fa_campaign_manager.common.security.AppUserPrincipalImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Primary
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
  
  private final UserRepository userRepository;
  
  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email)
      .map(user -> AppUserPrincipalImpl.builder()
        .id(user.getId())
        .email(user.getEmail())
        .password(user.getPassword())
        .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRoleCd())))
        .build())
      .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email));
  }
  
  @Transactional(readOnly = true)
  public UserDetails loadByDiscordId(String discordId) throws UsernameNotFoundException {
    return userRepository.findByDiscordId(discordId)
      .map(user -> AppUserPrincipalImpl.builder()
        .id(user.getId())
        .email(user.getEmail())
        .password("") // 디스코드 인증 시 패스워드는 사용하지 않음
        .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRoleCd())))
        .build())
      .orElseThrow(() -> new UsernameNotFoundException("디스코드 ID를 찾을 수 없습니다: " + discordId));
  }
}
