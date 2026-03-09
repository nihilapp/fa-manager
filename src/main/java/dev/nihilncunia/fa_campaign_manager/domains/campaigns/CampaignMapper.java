package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignInDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
  componentModel = "spring",
  unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CampaignMapper {
  CampaignEntity toEntity(CampaignInDto campaignInDto);
  
  CampaignOutDto toDto(CampaignEntity campaignEntity);
  
  List<CampaignOutDto> toDtoList(List<CampaignEntity> campaignEntityList);

  /**
   * DTO 데이터를 기반으로 기존 엔티티를 업데이트합니다.
   * @MappingTarget 어노테이션은 새로운 객체를 생성하지 않고, 두 번째 파라미터로 전달된 '기존 객체'를 수정하겠다는 뜻입니다.
   * MapStruct는 컴파일 시점에 DTO의 필드 값들을 엔티티의 setter를 호출하여 복사하는 코드를 자동으로 생성해줍니다.
   * 
   * @param campaignInDto 소스 데이터 (입력 DTO)
   * @param campaignEntity 업데이트 대상 (기존 영속성 엔티티)
   */
  void updateEntityFromDto(CampaignInDto campaignInDto, @MappingTarget CampaignEntity campaignEntity);
}
