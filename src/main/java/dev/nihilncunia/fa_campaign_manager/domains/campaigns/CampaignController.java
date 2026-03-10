package dev.nihilncunia.fa_campaign_manager.domains.campaigns;

import dev.nihilncunia.fa_campaign_manager.common.annotation.IsUser;

import dev.nihilncunia.fa_campaign_manager.common.constant.RESPONSE_CODE;
import dev.nihilncunia.fa_campaign_manager.common.constant.RESPONSE_MESSAGE;
import dev.nihilncunia.fa_campaign_manager.common.dto.ListOutDto;
import dev.nihilncunia.fa_campaign_manager.common.response.BaseResponse;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignInDto;
import dev.nihilncunia.fa_campaign_manager.domains.campaigns.dto.CampaignOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.characters.dto.CharacterOutDto;
import dev.nihilncunia.fa_campaign_manager.domains.users.dto.UserOutDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
@Tag(name = "캠페인 관리")
public class CampaignController {
  private final CampaignService campaignService;

  @GetMapping
  @Operation(
      summary = "캠페인 목록 조회",
      description = "캠페인 목록을 조회합니다."
  )
  @ApiResponse(
      responseCode = "200",
      description = "성공",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = BaseResponse.class),
          examples = {
              @ExampleObject(name = "성공", ref = "#/components/examples/campaignGetListSuccess")
          }
      )
  )
  public BaseResponse<?> getCampaignList(@Validated CampaignInDto campaignInDto) {
    ListOutDto<CampaignOutDto> campaignList = campaignService.getCampaignList(campaignInDto);

    return BaseResponse.ok(
        campaignList,
        RESPONSE_CODE.OK,
        RESPONSE_MESSAGE.CAMPAIGN_GET_LIST_SUCCESS);
  }

  @GetMapping(
      value = "{id}"
  )
  @Operation(
      summary = "캠페인 정보 조회",
      description = "캠페인 정보를 조회합니다."
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "성공",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BaseResponse.class),
                  examples = {
                      @ExampleObject(name = "성공", ref = "#/components/examples/campaignGetDetailSuccess"),
                      @ExampleObject(name = "캠페인 미존재", ref = "#/components/examples/campaignNotFound")
                  }
              )
          )
      }
  )
  public BaseResponse<?> getCampaignById(@PathVariable Long id) {
    CampaignOutDto campaign = campaignService.getCampaignById(id);

    return BaseResponse.ok(campaign, RESPONSE_CODE.OK, RESPONSE_MESSAGE.CAMPAIGN_GET_DETAIL_SUCCESS);
  }

  @IsUser
  @PostMapping
  @Operation(
      summary = "캠페인 생성",
      description = "신규 캠페인을 생성합니다."
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "성공",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BaseResponse.class),
                  examples = {
                      @ExampleObject(name = "성공", ref = "#/components/examples/campaignCreateSuccess"),
                      @ExampleObject(name = "캠페인 명 중복", ref = "#/components/examples/campaignNameConflict")
                  }
              )
          )
      }
  )
  public BaseResponse<?> createCampaign(@RequestBody @Validated CampaignInDto campaignInDto) {
    CampaignOutDto campaign = campaignService.createCampaign(campaignInDto);

    return BaseResponse.ok(campaign, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.CAMPAIGN_CREATE_SUCCESS);
  }

  @IsUser
  @PutMapping(
      value = "{id}"
  )
  @Operation(
      summary = "캠페인 정보 수정",
      description = "캠페인 정보를 수정합니다."
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "성공",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BaseResponse.class),
                  examples = {
                      @ExampleObject(name = "성공", ref = "#/components/examples/campaignUpdateSuccess"),
                      @ExampleObject(name = "캠페인 미존재", ref = "#/components/examples/campaignNotFound"),
                      @ExampleObject(name = "캠페인 명 중복", ref = "#/components/examples/campaignNameConflict")
                  }
              )
          )
      }
  )
  public BaseResponse<?> updateCampaign(@PathVariable Long id, @RequestBody @Validated CampaignInDto campaignInDto) {
    CampaignOutDto campaign = campaignService.updateCampaign(id, campaignInDto);

    return BaseResponse.ok(campaign, RESPONSE_CODE.OK, RESPONSE_MESSAGE.CAMPAIGN_UPDATE_SUCCESS);
  }

  @IsUser
  @DeleteMapping(
      value = "{id}"
  )
  @Operation(
      summary = "캠페인 삭제",
      description = "캠페인을 삭제합니다."
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "성공",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BaseResponse.class),
                  examples = {
                      @ExampleObject(name = "성공", ref = "#/components/examples/campaignDeleteSuccess"),
                      @ExampleObject(name = "캠페인 미존재", ref = "#/components/examples/campaignNotFound")
                  }
              )
          )
      }
  )
  public BaseResponse<?> deleteCampaign(@PathVariable Long id) {
    campaignService.deleteCampaign(id);

    return BaseResponse.ok(null, RESPONSE_CODE.OK, RESPONSE_MESSAGE.CAMPAIGN_DELETE_SUCCESS);
  }

  @IsUser
  @PostMapping(
      value = "{campaignId}/members/{userId}"
  )
  @Operation(
      summary = "캠페인 플레이어 등록",
      description = "캠페인에 플레이어를 등록합니다."
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "성공",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BaseResponse.class),
                  examples = {
                      @ExampleObject(name = "성공", ref = "#/components/examples/campaignMemberAddSuccess"),
                      @ExampleObject(name = "이미 등록된 멤버", ref = "#/components/examples/campaignMemberConflict"),
                      @ExampleObject(name = "캠페인 미존재", ref = "#/components/examples/campaignNotFound"),
                      @ExampleObject(name = "사용자 미존재", ref = "#/components/examples/userNotFound")
                  }
              )
          )
      }
  )
  public BaseResponse<?> addCampaignMember(
      @PathVariable Long campaignId,
      @PathVariable Long userId) {
    UserOutDto campaignMember = campaignService.addCampaignMember(campaignId, userId);

    return BaseResponse.ok(campaignMember, RESPONSE_CODE.OK, RESPONSE_MESSAGE.CAMPAIGN_MEMBER_ADD_SUCCESS);
  }

  @IsUser
  @DeleteMapping(
      value = "{campaignId}/members/{userId}"
  )
  @Operation(
      summary = "캠페인 플레이어 삭제",
      description = "캠페인에서 등록된 플레이어를 삭제합니다."
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "성공",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BaseResponse.class),
                  examples = {
                      @ExampleObject(name = "성공", ref = "#/components/examples/userDeleteSuccess"),
                      @ExampleObject(name = "멤버 미존재", ref = "#/components/examples/campaignMemberConflict")
                  }
              )
          )
      }
  )
  public BaseResponse<?> removeCampaignMember(
      @PathVariable Long campaignId,
      @PathVariable Long userId) {
    UserOutDto user = campaignService.removeCampaignMember(campaignId, userId);

    return BaseResponse.ok(user, RESPONSE_CODE.OK, RESPONSE_MESSAGE.CAMPAIGN_MEMBER_REMOVE_SUCCESS);
  }

  @IsUser
  @PostMapping(
      value = "{campaignId}/characters/{characterId}"
  )
  @Operation(
      summary = "캠페인 캐릭터 등록",
      description = "캠페인에 캐릭터를 등록합니다."
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "성공",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BaseResponse.class),
                  examples = {
                      @ExampleObject(name = "성공", ref = "#/components/examples/campaignCharacterAddSuccess"),
                      @ExampleObject(name = "이미 소속된 캐릭터", ref = "#/components/examples/campaignCharacterConflict"),
                      @ExampleObject(name = "캠페인 미존재", ref = "#/components/examples/campaignNotFound"),
                      @ExampleObject(name = "캐릭터 미존재", ref = "#/components/examples/characterNotFound")
                  }
              )
          )
      }
  )
  public BaseResponse<?> addCampaignCharacter(
      @PathVariable Long campaignId,
      @PathVariable Long characterId) {
    CharacterOutDto character = campaignService.addCampaignCharacter(campaignId, characterId);

    return BaseResponse.ok(character, RESPONSE_CODE.OK, RESPONSE_MESSAGE.CAMPAIGN_CHARACTER_ADD_SUCCESS);
  }

  @IsUser
  @DeleteMapping(
      value = "{campaignId}/characters/{characterId}"
  )
  @Operation(
      summary = "캠페인 캐릭터 삭제",
      description = "캠페인에서 캐릭터를 삭제합니다."
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "성공",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BaseResponse.class),
                  examples = {
                      @ExampleObject(name = "성공", ref = "#/components/examples/campaignDeleteSuccess"),
                      @ExampleObject(name = "캐릭터 미소속", ref = "#/components/examples/campaignCharacterConflict")
                  }
              )
          )
      }
  )
  public BaseResponse<?> removeCampaignCharacter(
      @PathVariable Long campaignId,
      @PathVariable Long characterId) {
    CharacterOutDto character = campaignService.removeCampaignCharacter(campaignId, characterId);

    return BaseResponse.ok(character, RESPONSE_CODE.OK, RESPONSE_MESSAGE.CAMPAIGN_CHARACTER_REMOVE_SUCCESS);
  }
}
