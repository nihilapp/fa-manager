package dev.nihilncunia.fa_campaign_manager.common.config.jwt;

import dev.nihilncunia.fa_campaign_manager.domains.users.AppUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class DiscordAuthenticationFilter extends OncePerRequestFilter {
  
  private final AppUserDetailsService userDetailsService;
  private final boolean useDiscord;
  
  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain)
    throws ServletException, IOException {
    
    String discordId = request.getHeader("x-discord-id");
    
    if (useDiscord && discordId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      try {
        UserDetails userDetails = userDetailsService.loadByDiscordId(discordId);
        
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
        
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        logger.debug("Successfully authenticated with Discord ID: " + discordId);
      } catch (Exception e) {
        // 인증 실패 시 로그를 남기지만 다음 필터로 전달
        logger.error("Discord Auth failed for ID: " + discordId + " - " + e.getMessage());
      }
    }
    
    filterChain.doFilter(request, response);
  }
}
