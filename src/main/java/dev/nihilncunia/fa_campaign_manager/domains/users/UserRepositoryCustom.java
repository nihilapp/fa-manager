package dev.nihilncunia.fa_campaign_manager.domains.users;

import dev.nihilncunia.fa_campaign_manager.common.dto.ListOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserInDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;

public interface UserRepositoryCustom {
  ListOutDto<UserOutDto> findAll(UserInDto userInDto);
}
