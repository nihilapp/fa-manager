package dev.nihilncunia.fa_campaign_manager.domains.users.dto;

import dev.nihilncunia.fa_campaign_manager.common.constant.USER_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonOutDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

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

  @Schema(description = "보유 캐릭터 목록")
  private List<CharacterList> characterList;

  @Schema(description = "참여 중인 캠페인 목록")
  private List<CampaignList> campaignList;

  @Schema(description = "참여한 세션 목록")
  private List<SessionList> sessionList;

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class CharacterList {
    private Long id;
    private String name;
    private String race;
  }

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class CampaignList {
    private Long id;
    private String name;
    private String status;
  }

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class SessionList {
    private Long id;
    private Integer no;
    private String name;
    private String role;
  }
}
