package com.club.closed_club.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name = "scan_events")
public class ScanEvent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qr_uuid")
    private UUID qrUuid;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "scanner_id", nullable = false, length = 100)
    private String scannerId;

    @Column(name = "result", nullable = false, length = 20)
    private String result;

    @Column(name = "reason", nullable = false, length = 100)
    private String reason;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    protected ScanEvent() {}

    public ScanEvent( UUID qrUuid, Integer memberId, String scannerId, String result, String reason) {
        this.qrUuid = qrUuid;
        this.memberId = memberId;
        this.scannerId = scannerId;
        this.result = result;
        this.reason = reason;
        this.createdAt = OffsetDateTime.now(ZoneOffset.UTC);
    }
}
