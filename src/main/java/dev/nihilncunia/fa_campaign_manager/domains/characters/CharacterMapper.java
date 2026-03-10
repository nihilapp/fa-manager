package dev.nihilncunia.fa_campaign_manager.domains.characters;

import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.CharacterOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CharacterMapper {
  CharacterOutDto toDto(CharacterEntity characterEntity);

  List<CharacterOutDto> toDtoList(List<CharacterEntity> characterEntityList);
}
