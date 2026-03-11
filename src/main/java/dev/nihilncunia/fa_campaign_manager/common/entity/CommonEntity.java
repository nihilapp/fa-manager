package dev.nihilncunia.fa_campaign_manager.common.entity;

import dev.nihilncunia.fa_campaign_manager.common.constant.YN_CODE;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@SuperBuilder
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "ID", example = "1")
  private Long id;
  
  // --- 상태 플래그 (Y/N) ---
  
  @Builder.Default
  @Schema(description = "사용 여부", example = "Y", allowableValues = { "Y", "N" })
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 1, columnDefinition = "char(1) default 'Y'")
  private YN_CODE useYn = YN_CODE.Y;
  
  @Builder.Default
  @Schema(description = "삭제 여부", example = "N", allowableValues = { "Y", "N" })
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 1, columnDefinition = "char(1) default 'N'")
  private YN_CODE deleteYn = YN_CODE.N;
  
  // --- 생성 정보 (Create) ---
  
  @CreatedBy
  @Schema(description = "생성자 ID", example = "1")
  private Long creatorId;
  
  @CreatedDate
  @Column(updatable = false)
  @Schema(description = "생성일시", example = "2026-03-07T10:00:00.000+09:00")
  private OffsetDateTime createDate;
  
  // --- 수정 정보 (Update) ---
  
  @LastModifiedBy
  @Schema(description = "수정자 ID", example = "1")
  private Long updaterId;
  
  @LastModifiedDate
  @Schema(description = "수정일시", example = "2026-03-07T10:00:00.000+09:00")
  private OffsetDateTime updateDate;
  
  // --- 삭제 정보 (Delete) ---
  
  @Schema(description = "삭제자 ID", example = "1")
  private Long deleterId;
  
  @Schema(description = "삭제일시", example = "2026-03-07T10:00:00.000+09:00")
  private OffsetDateTime deleteDate;
  
  /**
   * 엔티티 소프트 삭제(Soft Delete) 처리
   * 실제 DB에서 레코드를 삭제(Hard Delete)하지 않고, 플래그를 변경하여 논리적으로 삭제된 것으로 간주합니다.
   * 이렇게 하면 데이터 복구가 쉽고, 삭제된 데이터와 연관된 이력을 추적할 수 있는 장점이 있습니다.
   *
   * @param deleterId 삭제를 수행한 사용자 ID (누가 삭제했는지 기록하여 보안 및 추적 용도로 사용)
   */
  public void delete(Long deleterId) {
    // 사용 여부를 'N'으로 변경: 애플리케이션의 일반적인 조회 로직에서 제외하기 위함
    this.useYn = YN_CODE.N;
    // 삭제 여부를 'Y'로 변경: @SQLRestriction("delete_yn = 'N'") 설정이 있는 엔티티는 쿼리 시 자동으로 걸러짐
    this.deleteYn = YN_CODE.Y;
    // 삭제 주체와 시간을 기록하여 '언제, 누가' 삭제했는지 아카이빙
    this.deleterId = deleterId;
    this.deleteDate = OffsetDateTime.now();
  }
  
  @PrePersist
  protected void onPrePersist() {
    if (this.useYn == null)
      this.useYn = YN_CODE.Y;
    if (this.deleteYn == null)
      this.deleteYn = YN_CODE.N;
  }
}
