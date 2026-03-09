package dev.nihilncunia.fa_campaign_manager.domains.docs.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DOC_CATEGORY {
  NATION("NATION", "국가", "영토와 주권을 가진 국가 단위의 설정"),
  ORGANIZATION("ORGANIZATION", "단체", "길드, 기사단, 학회, 암흑가 등 특정 목적을 가진 집단"),
  REGION("REGION", "지역", "대륙, 숲, 바다, 산맥 등 광역 지리 정보"),
  CITY("CITY", "도시", "마을, 대도시, 요새 등 특정 인구 밀집 구역"),
  LEGEND("LEGEND", "전설", "진위가 불분명하거나 신화적인 과거의 이야기"),
  FOLKLORE("FOLKLORE", "설화", "민간에 떠도는 소문, 괴담, 민담"),
  HISTORY("HISTORY", "역사", "명확하게 기록된 과거의 주요 사건이나 연대기"),
  EVENT("EVENT", "사건", "현재 진행 중이거나 최근 벌어진 캠페인 내외의 주요 사건"),
  ITEM("ITEM", "아이템", "아티팩트, 고유 무기, 특수 마법 물품 등"),
  PERSON("PERSON", "인물", "주요 NPC, 역사적 영웅, 악당 등의 개별 설정"),
  RACE("RACE", "종족", "세계관 내 다양한 종족의 생태, 문화, 특징"),
  HOUSE_RULE("HOUSE_RULE", "하우스 룰", "환상서고 커뮤니티 고유의 적용 규칙 및 시스템 변형");

  private final String code;
  private final String korName;
  private final String description;

  @JsonValue
  public String getCode() {
    return this.code;
  }
}
