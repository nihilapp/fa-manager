package dev.nihilncunia.fa_campaign_manager.domains.sessions;

import dev.nihilncunia.fa_campaign_manager.common.constant.SESSION_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.constant.STATUS_CODE;
import dev.nihilncunia.fa_campaign_manager.common.constant.YN_CODE;
import dev.nihilncunia.fa_campaign_manager.common.response.ResponseExampleBuilder;
import dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterExample;
import dev.nihilncunia.fa_campaign_manager.domains.sessions.dto.SessionOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserExample;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SessionExample {
  private final ResponseExampleBuilder exampleBuilder;
  private final UserExample userExample;
  private final CharacterExample characterExample;
  
  @Getter
  private final SessionOutDto sessionFullData = SessionOutDto.builder()
    .id(1L)
    .no(1)
    .name("케어 모헨의 훈련")
    .description("어린 위쳐들의 훈련을 돕는 세션입니다.")
    .maxPlayer(4)
    .rewardExp(100)
    .rewardGold(50)
    .status(STATUS_CODE.COMPLETED)
    .playDate(OffsetDateTime.now().minusMonths(1))
    .sessionRole(SESSION_ROLE.MASTER)
    .players(List.of(userExample.getUserSimpleData()))
    .characters(List.of(characterExample.getCharacterSimpleData()))
    .useYn(YN_CODE.Y)
    .deleteYn(YN_CODE.N)
    .creatorId(1L)
    .createDate(OffsetDateTime.now().minusMonths(1).minusDays(1))
    .updaterId(1L)
    .updateDate(OffsetDateTime.now().minusMonths(1))
    .deleterId(null)
    .deleteDate(null)
    .build();
  
  @Getter
  private final SessionOutDto sessionSimpleData = SessionOutDto.builder()
    .id(1L)
    .no(1)
    .name("케어 모헨의 훈련")
    .description("어린 위쳐들의 훈련을 돕는 세션입니다.")
    .maxPlayer(4)
    .rewardExp(100)
    .rewardGold(50)
    .status(STATUS_CODE.COMPLETED)
    .playDate(OffsetDateTime.now().minusMonths(1))
    .sessionRole(SESSION_ROLE.MASTER)
    .players(List.of())
    .characters(List.of())
    .useYn(YN_CODE.Y)
    .deleteYn(YN_CODE.N)
    .creatorId(1L)
    .createDate(OffsetDateTime.now().minusMonths(1).minusDays(1))
    .updaterId(1L)
    .updateDate(OffsetDateTime.now().minusMonths(1))
    .deleterId(null)
    .deleteDate(null)
    .build();
}
