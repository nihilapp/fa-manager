package dev.nihilncunia.fa_campaign_manager.domains.characters.dto;

import dev.nihilncunia.fa_campaign_manager.common.annotation.SearchCondition;
import dev.nihilncunia.fa_campaign_manager.common.constant.SEARCH_TYPE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonInDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.constant.CHARACTER_STATUS;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "캐릭터 입력 및 검색 DTO")
public class CharacterInDto extends CommonInDto {

  @Schema(description = "다건 처리를 위한 ID 리스트")
  private List<Long> idList;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "소유자 유저 ID")
  private Long userId;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "소속 캠페인 ID")
  private Long campaignId;

  @SearchCondition(type = SEARCH_TYPE.LIKE)
  @NotBlank(message = "캐릭터 이름은 필수입니다.")
  @Schema(description = "캐릭터 이름", example = "가츠")
  private String name;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "캐릭터 상태")
  private CHARACTER_STATUS status;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "종족", example = "Human")
  private String race;

  @Schema(description = "시작 레벨")
  private Integer startLevel;

  @Schema(description = "시작 경험치")
  private Integer startExp;

  @Schema(description = "시작 소지 자금")
  private Integer startCurrency;
}
