package dev.nihilncunia.fa_campaign_manager.domains.users;

import dev.nihilncunia.fa_campaign_manager.common.constant.*;
import dev.nihilncunia.fa_campaign_manager.common.response.ResponseExampleBuilder;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.CampaignExample;
import dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterExample;
import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.*;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.SessionExample;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Swagger API 문서화를 위한 사용자 관련 응답 예시 객체들을 관리하는 클래스입니다.
 * 조인 주체 중심의 2뎁스 구조와 그룹화된 캐릭터 정보를 포함한 100% 완전한 예시를 제공합니다.
 */
@Component
@RequiredArgsConstructor
public class UserExample {
  private final ResponseExampleBuilder exampleBuilder;
  private final CampaignExample campaignExample;
  private final CharacterExample characterExample;
  private final SessionExample sessionExample;
  
  // 모든 필드가 100% 채워진 최상위 사용자 객체 (2뎁스 중심)
  @Getter
  private final UserOutDto userFullData = UserOutDto.builder()
    .id(1L)
    .name("NIHILncunia")
    .discordId("197054124020334593")
    .email("nihil_ncunia@naver.com")
    .role(USER_ROLE.ROLE_ADMIN)
    .loginFailureCount(0)
    .lockYn(YN_CODE.N)
    .lastSignInDate(OffsetDateTime.now().minusHours(2))
    .lastPasswordChangeDate(OffsetDateTime.now().minusMonths(1))
    .campaignList(List.of(campaignExample.getCampaignSimpleData()))
    .characterList(List.of(characterExample.getCharacterSimpleData()))
    .sessionList(List.of(sessionExample.getSessionSimpleData()))
    .useYn(YN_CODE.Y)
    .deleteYn(YN_CODE.N)
    .creatorId(1L)
    .createDate(OffsetDateTime.now().minusMonths(1))
    .updaterId(1L)
    .updateDate(OffsetDateTime.now().minusDays(1))
    .deleterId(null)
    .deleteDate(null)
    .build();
  
  @Getter
  private final UserOutDto userSimpleData = UserOutDto.builder()
    .id(1L)
    .name("NIHILncunia")
    .discordId("197054124020334593")
    .email("nihil_ncunia@naver.com")
    .role(USER_ROLE.ROLE_ADMIN)
    .loginFailureCount(0)
    .lockYn(YN_CODE.N)
    .lastSignInDate(OffsetDateTime.now().minusHours(2))
    .lastPasswordChangeDate(OffsetDateTime.now().minusMonths(1))
    .campaignList(List.of())
    .characterList(List.of())
    .sessionList(List.of())
    .useYn(YN_CODE.Y)
    .deleteYn(YN_CODE.N)
    .creatorId(1L)
    .createDate(OffsetDateTime.now().minusMonths(1))
    .updaterId(1L)
    .updateDate(OffsetDateTime.now().minusDays(1))
    .deleterId(null)
    .deleteDate(null)
    .build();
  
  
  // --- API Response Examples ---
  
  public Object userCreateSuccess() {
    return exampleBuilder.buildObject(userFullData, false, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.USER_CREATE_SUCCESS);
  }
  
  public Object userGetDetailSuccess() {
    return exampleBuilder.buildObject(userFullData, false, RESPONSE_CODE.OK, RESPONSE_MESSAGE.USER_GET_DETAIL_SUCCESS);
  }
  
  public Object userGetListSuccess() {
    return exampleBuilder.buildObject(List.of(userFullData), false, RESPONSE_CODE.OK,
      RESPONSE_MESSAGE.USER_GET_LIST_SUCCESS);
  }
  
  public Object userUpdateSuccess() {
    return exampleBuilder.buildObject(userFullData, false, RESPONSE_CODE.OK, RESPONSE_MESSAGE.USER_UPDATE_SUCCESS);
  }
  
  public Object userDeleteSuccess() {
    return exampleBuilder.buildObject(null, false, RESPONSE_CODE.OK, RESPONSE_MESSAGE.USER_DELETE_SUCCESS);
  }
  
  public Object userNotFound() {
    return exampleBuilder.buildObject(null, true, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND);
  }
  
  public Object emailConflict() {
    return exampleBuilder.build(null, true, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.USER_EMAIL_CONFLICT);
  }
  
  public Object discordIdConflict() {
    return exampleBuilder.build(null, true, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.USER_DISCORD_ID_CONFLICT);
  }
  
  public Object discordIdRequired() {
    return exampleBuilder.build(null, true, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.USER_DISCORD_ID_REQUIRED);
  }
}
