package dev.nihilncunia.fa_campaign_manager.domains.users;

import dev.nihilncunia.fa_campaign_manager.common.constant.RESPONSE_CODE;
import dev.nihilncunia.fa_campaign_manager.common.constant.RESPONSE_MESSAGE;
import dev.nihilncunia.fa_campaign_manager.common.constant.USER_ROLE;
import dev.nihilncunia.fa_campaign_manager.common.exception.CustomException;
import dev.nihilncunia.fa_campaign_manager.common.security.CurrentUserProvider;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserInDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import dev.nihilncunia.fa_campaign_manager.common.dto.ListOutDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Value("${app.security.use-discord:false}")
  private boolean useDiscord;

  @Override
  @Transactional
  public UserOutDto createUser(UserInDto userInDto) {
    // 1. 디스코드 필수 체크 (설정 활성화 시)
    if (useDiscord && (userInDto.getDiscordId() == null || userInDto.getDiscordId().isBlank())) {
      throw new CustomException(RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.USER_DISCORD_ID_REQUIRED);
    }

    // 2. 중복 확인
    if (userRepository.findByEmail(userInDto.getEmail()).isPresent()) {
      throw new CustomException(RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.USER_EMAIL_CONFLICT);
    }

    if (userInDto.getDiscordId() != null && userRepository.findByDiscordId(userInDto.getDiscordId()).isPresent()) {
      throw new CustomException(RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.USER_DISCORD_ID_CONFLICT);
    }

    // 3. 엔티티 변환 및 비밀번호 암호화
    UserEntity userEntity = userMapper.toEntity(userInDto);
    userEntity.setPassword(passwordEncoder.encode(userInDto.getRawPassword()));

    // 역할 설정: 입력값이 있으면 해당 역할을 사용하고, 없으면 ROLE_USER를 기본값으로 설정
    USER_ROLE userRole = (userInDto.getRole() != null) ? userInDto.getRole() : USER_ROLE.ROLE_USER;
    userEntity.setRole(userRole);

    userEntity.setDiscordId(userInDto.getDiscordId());

    // 4. 저장
    UserEntity savedUser = userRepository.save(userEntity);

    return userMapper.toDto(savedUser);
  }

  @Override
  public UserOutDto getMyInfo() {
    Long currentUserId = CurrentUserProvider.getCurrentUserId()
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.UNAUTHORIZED));

    return getUserById(currentUserId);
  }

  @Override
  public UserOutDto getUserById(Long id) {
    UserEntity userEntity = userRepository.findById(id)
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND));

    return userMapper.toDto(userEntity);
  }

  @Override
  public ListOutDto<UserOutDto> getUserList(UserInDto userInDto) {
    return userRepository.findAll(userInDto);
  }

  @Override
  @Transactional
  public UserOutDto updateUser(Long id, UserInDto userInDto) {
    UserEntity userEntity = userRepository.findById(id)
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND));

    if (userInDto.getName() != null)
      userEntity.setName(userInDto.getName());
    if (userInDto.getRawPassword() != null) {
      userEntity.setPassword(passwordEncoder.encode(userInDto.getRawPassword()));
    }

    return userMapper.toDto(userEntity);
  }

  @Override
  @Transactional
  public void deleteUser(Long id) {
    UserEntity userEntity = userRepository.findById(id)
        .orElseThrow(() -> new CustomException(RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND));

    // CommonEntity의 delete() 메소드를 사용하여 소프트 삭제 필드(useYn, deleteYn, deleterId,
    // deleteDate)를 일괄 갱신합니다.
    userEntity.delete(CurrentUserProvider.getCurrentUserId().orElse(null));
  }
}
