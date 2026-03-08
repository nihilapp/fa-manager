package dev.nihilncunia.fa_campaign_manager.domains.users.dto;

import dev.nihilncunia.fa_campaign_manager.common.annotation.SearchCondition;
import dev.nihilncunia.fa_campaign_manager.common.constant.SEARCH_TYPE;
import dev.nihilncunia.fa_campaign_manager.common.constant.USER_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.dto.CommonInDto;
import dev.nihilncunia.fa_campaign_manager.common.validation.ValidationGroups;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 입력 및 검색 DTO")
public class UserInDto extends CommonInDto {
  
  @Schema(description = "다건 처리를 위한 ID 리스트")
  private List<Long> idList;

  @SearchCondition(type = SEARCH_TYPE.LIKE)
  @NotBlank(message = "이름은 필수 입력 항목입니다.", groups = {ValidationGroups.Create.class})
  @Schema(description = "사용자 이름", example = "NIHILncunia")
  private String name;

  @SearchCondition(type = SEARCH_TYPE.LIKE)
  @NotBlank(message = "이메일은 필수 입력 항목입니다.", groups = {ValidationGroups.Create.class})
  @Email(message = "이메일 형식이 올바르지 않습니다.")
  @Schema(description = "사용자 이메일", example = "example@example.com")
  private String email;

  @NotBlank(message = "비밀번호는 필수 입력 항목입니다.", groups = {ValidationGroups.Create.class})
  @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!#$%^&*?\\- 지나=+]).{8,20}$",
    message = "비밀번호는 숫자, 영문 대소문자, 특수문자(!#$%^&*?-=+)를 모두 포함하여 8~20자로 입력해야 합니다.",
    groups = {ValidationGroups.Create.class}
  )
  @Schema(description = "사용자 비밀번호 (평문)", example = "Password123!")
  private String rawPassword;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "사용자 권한", example = "ROLE_USER")
  private USER_ROLE role;

  @SearchCondition(type = SEARCH_TYPE.EQUALS)
  @Schema(description = "디스코드 사용자 ID", example = "1234567890")
  private String discordId;
}
