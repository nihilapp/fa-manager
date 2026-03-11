package dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto;

import dev.nihilncunia.fa_campaign_manager.common.constant.STATUS_CODE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.CharacterOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.dto.SessionOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
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

  @Schema(description = "캠페인 설명")
  private String description;

  @Schema(description = "캠페인 상태")
  private STATUS_CODE status;

  @Schema(description = "캠페인 시작일")
  private OffsetDateTime startDate;

  @Schema(description = "캠페인 종료일")
  private OffsetDateTime endDate;

  @Schema(description = "세션 목록")
  private List<SessionOutDto> sessionList;

  @Schema(description = "참여 캐릭터 목록")
  private List<CharacterOutDto> characterList;

  @Schema(description = "캠페인 멤버 목록")
  private List<UserOutDto> memberList;
}
