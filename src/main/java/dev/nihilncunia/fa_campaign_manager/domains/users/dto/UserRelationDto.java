package dev.nihilncunia.fa_campaign_manager.domains.users.dto;

import dev.nihilncunia.fa_campaign_manager.common.constant.MEMBER_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.constant.USER_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.constant.YN_CODE;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 관계 정보 DTO (관계 컬럼 제외)")
public class UserRelationDto {
  @Schema(description = "사용자 ID")
  private Long id;
  
  @Schema(description = "사용자 이름")
  private String name;

  @Schema(description = "사용자 이메일")
  private String email;

  @Schema(description = "사용자 시스템 권한")
  private USER_ROLE role;
  
  @Schema(description = "디스코드 사용자 고유 ID")
  private String discordId;

  @Schema(description = "로그인 실패 횟수")
  private Integer loginFailureCount;
  
  @Schema(description = "사용자 잠금 여부")
  private YN_CODE lockYn;
  
  @Schema(description = "사용자 마지막 로그인 날짜")
  private OffsetDateTime lastSignInDate;
  
  @Schema(description = "사용자 마지막 비밀번호 변경 날짜")
  private OffsetDateTime lastPasswordChangeDate;

  // 캠페인 내에서의 역할이 필요한 경우를 위한 추가 필드 (CampaignMember 정보)
  @Schema(description = "캠페인 내 역할 (MASTER/MEMBER_GM/MEMBER_PLAYER)")
  private MEMBER_ROLE campaignRole;
}
