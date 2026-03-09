package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.common.dto.ListOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignInDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.CharacterOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;

public interface CampaignService {
  ListOutDto<CampaignOutDto> getCampaignList(CampaignInDto campaignInDto);
  
  CampaignOutDto getCampaignById(Long id);
  
  CampaignOutDto createCampaign(CampaignInDto campaignInDto);
  
  CampaignOutDto updateCampaign(Long id, CampaignInDto campaignInDto);
  
  void deleteCampaign(Long id);
  
  UserOutDto addCampaignMember(Long campaignId, Long userId);
  
  UserOutDto removeCampaignMember(Long campaignId, Long userId);
  
  CharacterOutDto addCampaignCharacter(Long campaignId, Long characterId);
  
  CharacterOutDto removeCampaignCharacter(Long campaignId, Long characterId);
}
