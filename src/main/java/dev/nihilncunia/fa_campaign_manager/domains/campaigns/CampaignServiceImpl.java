package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.common.constant.MEMBER_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.constant.RESPONSE_CODE;
import dev.nihilncunia.fa_campaign_manager.common.constant.RESPONSE_MESSAGE;
import dev.nihilncunia.fa_campaign_manager.common.dto.ListOutDto;
import dev.nihilncunia.fa_campaign_manager.common.exception.CustomException;
import dev.nihilncunia.fa_campaign_manager.common.security.CurrentUserProvider;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignInDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterEntity;
import dev.nihilncunia.fa_campaign_manager.domains.characters.CharacterRepository;
import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.CharacterOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserEntity;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserMapper;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserRepository;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 캠페인 서비스 구현체
 * 
 * 캠페인의 생성, 수정, 삭제(CRUD) 및 멤버/캐릭터와의 연관 관계를 관리합니다.
 * 학습 포인트:
 * 1. @Transactional: 메소드 실행 성공 시 변경 사항을 DB에 커밋하고, 실패 시 롤백합니다.
 * 2. Dirty Checking: 영속화된 엔티티의 필드만 수정하면 JPA가 자동으로 UPDATE 쿼리를 날려줍니다.
 * 3. 응답 체계: 도메인별 전용 RESPONSE_CODE와 MESSAGE를 사용하여 에러 상황을 명확히 전달합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampaignServiceImpl implements CampaignService {
  private final CampaignRepository campaignRepository;
  private final CampaignMemberRepository campaignMemberRepository;
  private final UserRepository userRepository;
  private final CharacterRepository characterRepository;
  private final CampaignMapper campaignMapper;
  private final UserMapper userMapper;

  @Override
  public ListOutDto<CampaignOutDto> getCampaignList(CampaignInDto campaignInDto) {
    // QueryDSL 커스텀 리포지토리를 호출하여 동적 검색 및 페이징 결과를 가져옵니다.
    return campaignRepository.findAll(campaignInDto);
  }

  @Override
  public CampaignOutDto getCampaignById(Long id) {
    // 단건 조회: 존재하지 않을 경우 도메인 전용 예외(CAMPAIGN_NOT_FOUND)를 던집니다.
    CampaignEntity campaignEntity = campaignRepository.findById(id)
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.CAMPAIGN_NOT_FOUND,
            RESPONSE_MESSAGE.CAMPAIGN_NOT_FOUND, id));

    return campaignMapper.toDto(campaignEntity);
  }

  @Override
  @Transactional
  public CampaignOutDto createCampaign(CampaignInDto campaignInDto) {
    // 1. 비즈니스 정합성 체크: 이름 중복 확인
    // 범용 코드가 아닌 'CAMPAIGN_NAME_ALREADY_EXISTS'를 사용하여 어떤 필드에서 문제가 생겼는지 명시합니다.
    if (campaignRepository.existsByName(campaignInDto.getName())) {
      throw new CustomException(
          RESPONSE_CODE.CAMPAIGN_NAME_ALREADY_EXISTS,
          RESPONSE_MESSAGE.CAMPAIGN_NAME_CONFLICT,
          campaignInDto.getName());
    }

    // 2. DTO -> 엔티티 변환 및 저장
    CampaignEntity campaignEntity = campaignMapper.toEntity(campaignInDto);
    CampaignEntity saveEntity = campaignRepository.save(campaignEntity);

    return campaignMapper.toDto(saveEntity);
  }

  @Override
  @Transactional
  public CampaignOutDto updateCampaign(Long id, CampaignInDto campaignInDto) {
    // 1. 업데이트 대상 엔티티 조회 (영속화)
    // 영속성 컨텍스트가 이 객체를 관리하기 시작하면, 필드 수정만으로도 업데이트가 가능해집니다.
    CampaignEntity campaignEntity = campaignRepository.findById(id)
        .orElseThrow(() -> new CustomException(
            RESPONSE_CODE.CAMPAIGN_NOT_FOUND,
            RESPONSE_MESSAGE.CAMPAIGN_NOT_FOUND,
            id));

    // 2. 매퍼를 통한 데이터 덮어쓰기
    // @MappingTarget에 의해 엔티티의 setter들이 호출되며 값이 변경됩니다.
    campaignMapper.updateEntityFromDto(campaignInDto, campaignEntity);

    return campaignMapper.toDto(campaignEntity);
  }

  @Override
  @Transactional
  public void deleteCampaign(Long id) {
    // 1. 삭제 대상 엔티티 조회
    CampaignEntity campaignEntity = campaignRepository.findById(id)
        .orElseThrow(() -> new CustomException(
            RESPONSE_CODE.CAMPAIGN_NOT_FOUND,
            RESPONSE_MESSAGE.CAMPAIGN_NOT_FOUND,
            id));

    // 2. 소프트 삭제 실행
    // CommonEntity의 delete() 메소드에서 useYn='N', deleteYn='Y', deleterId, deleteDate
    // 처리를 수행합니다.
    campaignEntity.delete(CurrentUserProvider.getCurrentUserId().orElse(null));
  }

  @Override
  @Transactional
  public UserOutDto addCampaignMember(Long campaignId, Long userId) {
    // 1. 엔티티 존재 확인
    CampaignEntity campaign = campaignRepository.findById(campaignId)
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.CAMPAIGN_NOT_FOUND, RESPONSE_MESSAGE.CAMPAIGN_NOT_FOUND,
            campaignId));
    UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.USER_NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND, userId));

    // 2. 중복 참여 검증: 도메인 전용 예외 'CAMPAIGN_MEMBER_ALREADY_EXISTS' 사용
    if (campaignMemberRepository.existsByCampaignIdAndUserId(campaignId, userId)) {
      throw new CustomException(RESPONSE_CODE.CAMPAIGN_MEMBER_ALREADY_EXISTS, RESPONSE_MESSAGE.CAMPAIGN_MEMBER_CONFLICT,
          user.getName());
    }

    // 3. 연관 관계 엔티티 생성
    CampaignMemberEntity member = CampaignMemberEntity.builder()
        .user(user)
        .role(MEMBER_ROLE.MEMBER_PLAYER)
        .build();

    // 4. 연관 관계 편의 메소드: 캠페인 리스트에 멤버 추가 + 멤버 엔티티에 캠페인 설정
    campaign.addMember(member);

    // 5. 연결 데이터 저장
    campaignMemberRepository.save(member);

    return userMapper.toDto(user);
  }

  @Override
  @Transactional
  public UserOutDto removeCampaignMember(Long campaignId, Long userId) {
    // 1. 관계 엔티티 조회: 멤버가 아니면 'CAMPAIGN_MEMBER_NOT_FOUND' 예외 발생
    CampaignMemberEntity member = campaignMemberRepository.findByCampaignIdAndUserId(campaignId, userId)
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.CAMPAIGN_MEMBER_NOT_FOUND,
            RESPONSE_MESSAGE.CAMPAIGN_MEMBER_NOT_FOUND, userId));

    // 2. 연관 관계 제거
    // 부모 객체인 Campaign의 리스트에서 제거되면, orphanRemoval 옵션에 의해 DB 레코드도 삭제됩니다.
    member.getCampaign().removeMember(member);

    return userMapper.toDto(member.getUser());
  }

  @Override
  @Transactional
  public CharacterOutDto addCampaignCharacter(Long campaignId, Long characterId) {
    // 1. 엔티티 조회
    CampaignEntity campaign = campaignRepository.findById(campaignId)
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.CAMPAIGN_NOT_FOUND, RESPONSE_MESSAGE.CAMPAIGN_NOT_FOUND,
            campaignId));
    CharacterEntity character = characterRepository.findById(characterId)
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.CHARACTER_NOT_FOUND, RESPONSE_MESSAGE.CHARACTER_NOT_FOUND,
            characterId));

    // 2. 관계 검증: 캐릭터는 오직 하나의 캠페인에만 소속될 수 있음 (1:N)
    // 이미 소속이 있다면 'CAMPAIGN_CHARACTER_ALREADY_EXISTS' 예외를 던집니다.
    if (character.getCampaign() != null) {
      throw new CustomException(RESPONSE_CODE.CAMPAIGN_CHARACTER_ALREADY_EXISTS,
          RESPONSE_MESSAGE.CAMPAIGN_CHARACTER_CONFLICT, character.getName());
    }

    // 3. 연관 관계 편의 메소드 호출
    campaign.addCharacter(character);

    return CharacterOutDto.builder()
        .id(character.getId())
        .name(character.getName())
        .build();
  }

  @Override
  @Transactional
  public CharacterOutDto removeCampaignCharacter(Long campaignId, Long characterId) {
    // 1. 캐릭터 조회
    CharacterEntity character = characterRepository.findById(characterId)
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.CHARACTER_NOT_FOUND, RESPONSE_MESSAGE.CHARACTER_NOT_FOUND,
            characterId));

    // 2. 소속 정합성 체크: 현재 캐릭터가 이 캠페인 소속인지 확인
    if (character.getCampaign() == null || !character.getCampaign().getId().equals(campaignId)) {
      throw new CustomException(RESPONSE_CODE.CAMPAIGN_CHARACTER_NOT_FOUND,
          RESPONSE_MESSAGE.CAMPAIGN_CHARACTER_NOT_FOUND, character.getName());
    }

    // 3. 관계 제거
    character.getCampaign().removeCharacter(character);

    return CharacterOutDto.builder()
        .id(character.getId())
        .name(character.getName())
        .build();
  }
}
