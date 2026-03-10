package dev.nihilncunia.fa_campaign_manager.domains.users;

import dev.nihilncunia.fa_campaign_manager.common.constant.*;
import dev.nihilncunia.fa_campaign_manager.common.response.ResponseExampleBuilder;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.constant.CHARACTER_STATUS;
import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.*;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.dto.SessionOutDto;
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

  // 모든 필드가 100% 채워진 최상위 사용자 객체 (2뎁스 중심)
  @Getter
  private final UserOutDto userOutDto = UserOutDto.builder()
      // --- [L1] User Metadata ---
      .id(1L)
      .useYn(YN_CODE.Y)
      .deleteYn(YN_CODE.N)
      .creatorId(1L)
      .createDate(OffsetDateTime.now().minusMonths(1))
      .updaterId(1L)
      .updateDate(OffsetDateTime.now().minusDays(1))
      .deleterId(null)
      .deleteDate(null)
      // --- [L1] User Fields ---
      .name("NIHILncunia")
      .discordId("197054124020334593")
      .email("nihil_ncunia@naver.com")
      .role(USER_ROLE.ROLE_ADMIN)
      .loginFailureCount(0)
      .lockYn(YN_CODE.N)
      .lastSignInDate(OffsetDateTime.now().minusHours(2))
      .lastPasswordChangeDate(OffsetDateTime.now().minusMonths(1))
      .campaignRole(null)

      // --- [L2] CampaignList (Depth 2: Detail, but no nested L3 lists) ---
      .campaignList(List.of(
          CampaignOutDto.builder()
              .id(10L).useYn(YN_CODE.Y).deleteYn(YN_CODE.N).creatorId(1L)
              .createDate(OffsetDateTime.now().minusMonths(2))
              .updaterId(1L).updateDate(OffsetDateTime.now().minusDays(5))
              .deleterId(null).deleteDate(null)
              .name("검은 날개의 여정")
              .description("용의 부활을 막기 위한 모험가들의 이야기")
              .status(STATUS_CODE.IN_PROGRESS)
              .startDate(OffsetDateTime.now().minusMonths(1))
              .endDate(OffsetDateTime.now().plusMonths(6))
              .members(null) // 2뎁스 제한: 하위 리스트 제외
              .sessions(null)
              .characters(null)
              .build()))

      // --- [L2] CharacterList (Depth 2: Grouped Data) ---
      .characterList(List.of(
          CharacterOutDto.builder()
              .id(50L).useYn(YN_CODE.Y).deleteYn(YN_CODE.N).creatorId(1L)
              .createDate(OffsetDateTime.now().minusWeeks(3))
              .updaterId(1L).updateDate(OffsetDateTime.now().minusDays(2))
              .deleterId(null).deleteDate(null)
              .name("카엘").status(CHARACTER_STATUS.ACTIVE).race("엘프")
              .startLevel(1).startExp(0)
              .startCurrency(
                  CharacterCurrencyDto.builder().copper(10).silver(5).electrum(0).gold(10).platinum(0).build())
              .currentLevel(7).currentExp(15000)
              .currentCurrency(
                  CharacterCurrencyDto.builder().copper(45).silver(120).electrum(10).gold(2500).platinum(50).build())
              // Grouped Equipment
              .equipment(CharacterEquipmentDto.builder()
                  .mainHand("엘븐 롱소드 +2").offHand("미스릴 연을 본뜬 방패")
                  .armor("강화된 사슬 갑옷").head("통찰의 서클릿").gauntlet("거인의 힘 장갑")
                  .boots("차원 도약의 부츠").belt("포션 수납용 가죽 벨트").cloak("보호의 망토 +1")
                  .accessory1("저항의 반지").accessory2("언어 번역의 목걸이")
                  .accessory3("공중 부양 귀걸이").accessory4("마나 증폭 브로치")
                  .build())
              // Grouped Requirements (Str/Dex)
              .requirementStrDex(CharacterRequirementStrDexDto.builder()
                  .reqStrDex8("휴대용 횃불").reqStrDex10("모험가 배낭").reqStrDex12("등반용 로프")
                  .reqStrDex14("야영용 텐트").reqStr16("대형 연금술 도구").reqStr18("중장갑 보관함").reqStr20("거대 괴수 유해")
                  .build())
              // Grouped Requirements (Con)
              .requirementCon(CharacterRequirementConDto.builder()
                  .reqCon8("소형 물통").reqCon10("식량 주머니").reqCon12("보강된 장화")
                  .reqCon14("철제 조리 도구").reqCon16("방한용 침구").reqCon18("그리핀 안장").reqCon20("드래곤 전용 안장")
                  .build())
              .classes(List.of(
                  CharacterClassDto.builder().className("Wizard").subClass("Evocation").level(7).build()))
              .build()))

      // --- [L2] SessionList (Depth 2) ---
      .sessionList(List.of(
          SessionOutDto.builder()
              .id(200L).useYn(YN_CODE.Y).deleteYn(YN_CODE.N).creatorId(1L)
              .createDate(OffsetDateTime.now().minusDays(20))
              .updaterId(1L).updateDate(OffsetDateTime.now().minusDays(1))
              .deleterId(null).deleteDate(null)
              .no(5).name("심연의 끝에서").description("발록과의 최후의 전투")
              .maxPlayer(6).rewardExp(2000).rewardGold(5000)
              .status(STATUS_CODE.IN_PROGRESS).playDate(OffsetDateTime.now().plusDays(3))
              .sessionRole(SESSION_ROLE.PLAYER)
              .build()))
      .build();

  // --- API Response Examples ---

  public Object userCreateSuccess() {
    return exampleBuilder.buildObject(userOutDto, false, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.USER_CREATE_SUCCESS);
  }

  public Object userGetDetailSuccess() {
    return exampleBuilder.buildObject(userOutDto, false, RESPONSE_CODE.OK, RESPONSE_MESSAGE.USER_GET_DETAIL_SUCCESS);
  }

  public Object userGetListSuccess() {
    return exampleBuilder.buildObject(List.of(userOutDto), false, RESPONSE_CODE.OK,
        RESPONSE_MESSAGE.USER_GET_LIST_SUCCESS);
  }

  public Object userUpdateSuccess() {
    return exampleBuilder.buildObject(userOutDto, false, RESPONSE_CODE.OK, RESPONSE_MESSAGE.USER_UPDATE_SUCCESS);
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
