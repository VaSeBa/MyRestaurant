package ru.vaseba.myrestaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Voice;

import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
public interface VoiceRepository extends BaseEntityJpaRepository<Voice> {

    @Query("SELECT v FROM Voice v WHERE v.id = :id AND v.user.id = :userId AND v.status = 1")
    Optional<Voice> findByIdAndUserId(@Param("id") UUID id, @Param("userId") UUID userId);

    @Query("SELECT v FROM Voice v WHERE v.user.id = :userId AND v.status = 1")
    Page<Voice> findAllByUserId(@Param("userId") UUID userId, Pageable pageable);

}
