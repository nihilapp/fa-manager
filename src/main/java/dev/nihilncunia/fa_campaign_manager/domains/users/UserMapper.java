package dev.nihilncunia.fa_campaign_manager.domains.users;

import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserInDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
  componentModel = "spring",
  unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
  
  /**
   * UserInDto를 UserEntity로 변환합니다.
   * 비밀번호는 서비스 계층에서 암호화하여 별도로 세팅하므로 매핑에서 제외합니다.
   */
  @Mapping(target = "password", ignore = true)
  UserEntity toEntity(UserInDto userInDto);
  
  /**
   * UserEntity를 UserOutDto로 변환합니다.
   */
  UserOutDto toDto(UserEntity user);
  
  /**
   * UserEntity를 관계 리스트만 제외하고 모든 필드가 포함된 UserOutDto로 변환합니다.
   */
  @Named("toSimpleDto")
  @Mapping(target = "characterList", ignore = true)
  @Mapping(target = "campaignList", ignore = true)
  @Mapping(target = "sessionList", ignore = true)
  UserOutDto toSimpleDto(UserEntity user);
  
  /**
   * 엔티티 리스트를 DTO 리스트로 변환합니다.
   */
  List<UserOutDto> toDtoList(List<UserEntity> users);
}
