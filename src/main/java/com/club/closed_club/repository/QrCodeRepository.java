package com.club.closed_club.repository;

import com.club.closed_club.entity.QrCode;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface QrCodeRepository extends JpaRepository<QrCode, Integer> {
    Optional<QrCode> findByUuid(UUID uuid);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT q FROM QrCode q WHERE q.uuid = :uuid")
    Optional<QrCode> findByUuidForUpdate(@Param("uuid") UUID uuid);

}
