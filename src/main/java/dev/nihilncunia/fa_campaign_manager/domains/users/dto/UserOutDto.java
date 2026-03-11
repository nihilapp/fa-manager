package dev.nihilncunia.fa_campaign_manager.domains.users.dto;

import dev.nihilncunia.fa_campaign_manager.common.constant.MEMBER_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.constant.USER_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.constant.YN_CODE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.CharacterOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.dto.SessionOutDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 출력 DTO")
public class UserOutDto extends CommonOutDto {
  
  @Schema(description = "사용자 이름")
  private String name;
  
  @Schema(description = "디스코드 사용자 ID")
  private String discordId;
  
  @Schema(description = "사용자 이메일")
  private String email;
  
  @Schema(description = "사용자 권한")
  private USER_ROLE role;
  
  @Schema(description = "로그인 실패 횟수")
  private Integer loginFailureCount;
  
  @Schema(description = "사용자 잠금 여부")
  private YN_CODE lockYn;
  
  @Schema(description = "사용자 마지막 로그인 날짜")
  private OffsetDateTime lastSignInDate;
  
  @Schema(description = "사용자 마지막 비밀번호 변경 날짜")
  private OffsetDateTime lastPasswordChangeDate;
  
  @Schema(description = "보유 캐릭터 목록")
  private List<CharacterOutDto> characterList;
  
  @Schema(description = "참여 중인 캠페인 목록")
  private List<CampaignOutDto> campaignList;
  
  @Schema(description = "참여한 세션 목록")
  private List<SessionOutDto> sessionList;
}
