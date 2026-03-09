package dev.nihilncunia.fa_campaign_manager.domains.characters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 캐릭터 리포지토리
 * 
 * 캠페인에 소속될 캐릭터 엔티티(CharacterEntity)를 DB에 보존하고 관리합니다.
 */
@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
}
