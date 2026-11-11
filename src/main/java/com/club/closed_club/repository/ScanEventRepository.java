package com.club.closed_club.repository;

import com.club.closed_club.entity.ScanEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanEventRepository extends JpaRepository<ScanEvent, Long> {
}
