package dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto;

import dev.nihilncunia.fa_campaign_manager.common.constant.STATUS_CODE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonOutDto;
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
@Schema(description = "캠페인 출력 DTO")
public class CampaignOutDto extends CommonOutDto {

  @Schema(description = "캠페인 명")
  private String name;

  @Schema(description = "캠페인 상태")
  private STATUS_CODE status;

  @Schema(description = "캠페인 시작일")
  private OffsetDateTime startDate;

  @Schema(description = "캠페인 종료일")
  private OffsetDateTime endDate;

  @Schema(description = "세션 목록")
  private List<SessionList> sessionList;

  @Schema(description = "참여 캐릭터 목록")
  private List<CharacterList> characterList;

  @Schema(description = "캠페인 멤버 목록")
  private List<MemberList> memberList;

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class SessionList {
    private Long id;
    private Integer no;
    private String name;
    private String status;
  }

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class CharacterList {
    private Long id;
    private String name;
    private String race;
  }

  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
  public static class MemberList {
    private Long userId;
    private String userName;
    private String role;
  }
}
