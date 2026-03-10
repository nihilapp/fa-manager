package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.common.constant.*;
import dev.nihilncunia.fa_campaign_manager.common.response.ResponseExampleBuilder;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterExample;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.SessionExample;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserExample;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class CampaignExample {

  private final ResponseExampleBuilder exampleBuilder;
  private final UserExample userExample;
  private final CharacterExample characterExample;
  private final SessionExample sessionExample;

  @Getter
  private final CampaignOutDto campaignOutDto;

  public CampaignExample(
      ResponseExampleBuilder exampleBuilder,
      UserExample userExample,
      CharacterExample characterExample,
      SessionExample sessionExample) {
    this.exampleBuilder = exampleBuilder;
    this.userExample = userExample;
    this.characterExample = characterExample;
    this.sessionExample = sessionExample;

    this.campaignOutDto = CampaignOutDto.builder()
        .id(1L)
        .useYn(YN_CODE.Y)
        .deleteYn(YN_CODE.N)
        .creatorId(1L)
        .createDate(OffsetDateTime.now().minusDays(10))
        .updaterId(1L)
        .updateDate(OffsetDateTime.now().minusDays(1))
        .deleterId(null)
        .deleteDate(null)
        .name("위쳐: 운명의 검")
        .description("게롤트와 시리의 모험을 다룬 캠페인입니다.")
        .status(STATUS_CODE.IN_PROGRESS)
        .startDate(OffsetDateTime.now())
        .endDate(OffsetDateTime.now().plusMonths(6))
        .members(List.of(userExample.getUserOutDto()))
        .characters(List.of(characterExample.getCharacterOutDto()))
        .sessions(List.of(sessionExample.getSessionOutDto()))
        .build();
  }

  /**
   * 캠페인 목록 조회 성공 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignGetListSuccess() {
    return exampleBuilder.buildPage(
        List.of(campaignOutDto),
        1,
        1,
        10,
        0,
        RESPONSE_CODE.OK,
        RESPONSE_MESSAGE.CAMPAIGN_GET_LIST_SUCCESS);
  }

  /**
   * 캠페인 상세 정보 조회 성공 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignGetDetailSuccess() {
    return exampleBuilder.buildObject(
        campaignOutDto,
        false,
        RESPONSE_CODE.OK,
        RESPONSE_MESSAGE.CAMPAIGN_GET_DETAIL_SUCCESS);
  }

  /**
   * 캠페인 생성 성공 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignCreateSuccess() {
    return exampleBuilder.buildObject(
        campaignOutDto,
        false,
        RESPONSE_CODE.CREATED,
        RESPONSE_MESSAGE.CAMPAIGN_CREATE_SUCCESS);
  }

  /**
   * 캠페인 수정 성공 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignUpdateSuccess() {
    return exampleBuilder.buildObject(
        campaignOutDto,
        false,
        RESPONSE_CODE.OK,
        RESPONSE_MESSAGE.CAMPAIGN_UPDATE_SUCCESS);
  }

  /**
   * 캠페인 삭제 성공 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignDeleteSuccess() {
    return exampleBuilder.buildObject(
        null,
        false,
        RESPONSE_CODE.OK,
        RESPONSE_MESSAGE.CAMPAIGN_DELETE_SUCCESS);
  }

  /**
   * 플레이어 추가 성공 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignMemberAddSuccess() {
    return exampleBuilder.buildObject(
        userExample.getUserOutDto(),
        false,
        RESPONSE_CODE.OK,
        RESPONSE_MESSAGE.CAMPAIGN_MEMBER_ADD_SUCCESS);
  }

  /**
   * 캐릭터 등록 성공 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignCharacterAddSuccess() {
    return exampleBuilder.buildObject(
        characterExample.getCharacterOutDto(),
        false,
        RESPONSE_CODE.OK,
        RESPONSE_MESSAGE.CAMPAIGN_CHARACTER_ADD_SUCCESS);
  }

  /**
   * 캠페인 미존재 실패 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignNotFound() {
    return exampleBuilder.buildObject(
        null,
        true,
        RESPONSE_CODE.CAMPAIGN_NOT_FOUND,
        RESPONSE_MESSAGE.CAMPAIGN_NOT_FOUND);
  }

  /**
   * 캠페인 명 중복 실패 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignNameConflict() {
    return exampleBuilder.buildObject(
        null,
        true,
        RESPONSE_CODE.CAMPAIGN_NAME_ALREADY_EXISTS,
        RESPONSE_MESSAGE.CAMPAIGN_NAME_CONFLICT);
  }

  /**
   * 멤버 중복 등록 실패 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignMemberConflict() {
    return exampleBuilder.buildObject(
        null,
        true,
        RESPONSE_CODE.CAMPAIGN_MEMBER_ALREADY_EXISTS,
        RESPONSE_MESSAGE.CAMPAIGN_MEMBER_CONFLICT);
  }

  /**
   * 캐릭터 중복 등록 실패 응답 예시를 생성합니다.
   * 
   * @return 응답 예시 객체
   */
  public Object campaignCharacterConflict() {
    return exampleBuilder.buildObject(
        null,
        true,
        RESPONSE_CODE.CAMPAIGN_CHARACTER_ALREADY_EXISTS,
        RESPONSE_MESSAGE.CAMPAIGN_CHARACTER_CONFLICT);
  }
}
