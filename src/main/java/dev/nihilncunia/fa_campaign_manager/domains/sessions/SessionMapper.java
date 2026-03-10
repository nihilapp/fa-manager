package dev.nihilncunia.fa_campaign_manager.domains.sessions;

import dev.nihilncunia.fa_campaign_manager.domains.sessions.dto.SessionOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SessionMapper {
  SessionOutDto toDto(SessionEntity sessionEntity);

  List<SessionOutDto> toDtoList(List<SessionEntity> sessionEntityList);
}
