package dev.nihilncunia.fa_campaign_manager.domains.docs;

import dev.nihilncunia.fa_campaign_manager.common.entity.CommonEntity;
import dev.nihilncunia.fa_campaign_manager.domains.docs.constant.DOC_CATEGORY;
import dev.nihilncunia.fa_campaign_manager.domains.users.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuperBuilder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
  name = "docs",
  indexes = {
    @Index(name = "idx_docs_title", columnList = "title"),
    @Index(name = "idx_docs_category", columnList = "category"),
    @Index(name = "idx_docs_user_id", columnList = "user_id")
  }
)
@SQLRestriction(value = "delete_yn = 'N'")
public class DocEntity extends CommonEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_docs_users"))
  @Schema(description = "작성자 유저 정보")
  private UserEntity user;

  @Column(nullable = false, length = 200)
  @Schema(description = "제목", example = "제국 건국사")
  private String title;

  @Column(length = 500)
  @Schema(description = "설명", example = "제국의 기원과 초기 역사에 대한 기록")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 50)
  @Schema(description = "카테고리", example = "HISTORY")
  private DOC_CATEGORY category;

  @Column(columnDefinition = "TEXT")
  @Schema(description = "문서 본문 (JSON 형식)", example = "{\"pages\": []}")
  private String content;
}
