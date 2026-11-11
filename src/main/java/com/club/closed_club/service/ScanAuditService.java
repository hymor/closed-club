package com.club.closed_club.service;

import com.club.closed_club.entity.ScanEvent;
import com.club.closed_club.repository.ScanEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ScanAuditService {
    private final ScanEventRepository repository;

    public ScanAuditService(ScanEventRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void recordDenied(UUID uuid, Integer memberId, String scannerId, String reason) {
        repository.save(new ScanEvent(
                uuid, memberId, scannerId, "DENIED", reason
        ));
    }
}
