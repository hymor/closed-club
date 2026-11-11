package com.club.closed_club.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "qr_codes")
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_qr")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private Member member;

    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    protected QrCode() {}

    public QrCode(Member member) {
        this.member = member;
        this.uuid = UUID.randomUUID();
        this.createdAt = OffsetDateTime.now();
        this.status = true;
    }

    public Integer getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

