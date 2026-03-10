package dev.nihilncunia.fa_campaign_manager.domains.characters;

import dev.nihilncunia.fa_campaign_manager.common.constant.YN_CODE;
import dev.nihilncunia.fa_campaign_manager.common.response.ResponseExampleBuilder;
import dev.nihilncunia.fa_campaign_manager.domains.characters.constant.CHARACTER_STATUS;
import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CharacterExample {
  private final ResponseExampleBuilder exampleBuilder;

  @Getter
  private final CharacterOutDto characterOutDto = CharacterOutDto.builder()
      .id(1L)
      .useYn(YN_CODE.Y)
      .deleteYn(YN_CODE.N)
      .creatorId(1L)
      .createDate(OffsetDateTime.now().minusMonths(2))
      .updaterId(1L)
      .updateDate(OffsetDateTime.now().minusDays(5))
      .deleterId(null)
      .deleteDate(null)
      .name("리비아의 게롤트")
      .status(CHARACTER_STATUS.ACTIVE)
      .race("위쳐")
      .startLevel(1)
      .startExp(0)
      .startCurrency(CharacterCurrencyDto.builder().copper(10).silver(5).gold(10).build())
      .currentLevel(20)
      .currentExp(5000)
      .currentCurrency(CharacterCurrencyDto.builder().copper(100).silver(50).gold(500).build())
      .classes(List.of(
          CharacterClassDto.builder().className("Witcher").level(20).build()
      ))
      .equipment(CharacterEquipmentDto.builder()
          .mainHand("강철검").offHand("은검")
          .armor("늑대 교단 갑옷")
          .build())
      .build();
}
