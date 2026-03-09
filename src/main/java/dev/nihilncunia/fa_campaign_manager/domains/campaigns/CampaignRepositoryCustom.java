package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.common.dto.ListOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignInDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;

public interface CampaignRepositoryCustom {
  ListOutDto<CampaignOutDto> findAll(CampaignInDto campaignInDto);
}
