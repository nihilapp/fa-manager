package dev.nihilncunia.fa_campaign_manager.domains.docs;

import dev.nihilncunia.fa_campaign_manager.domains.docs.dto.DocInDto;
import dev.nihilncunia.fa_campaign_manager.domains.docs.dto.DocOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { UserMapper.class })
public interface DocMapper {
  
  @Mapping(target = "author", source = "user", qualifiedByName = "toSimpleDto")
  DocOutDto toDto(DocEntity docEntity);
  
  @Named("toSimpleDto")
  @Mapping(target = "author", ignore = true)
  DocOutDto toSimpleDto(DocEntity docEntity);
  
  DocEntity toEntity(DocInDto docInDto);
  
  List<DocOutDto> toDtoList(List<DocEntity> docEntityList);
}
