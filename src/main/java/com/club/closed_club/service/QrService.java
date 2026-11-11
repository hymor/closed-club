package com.club.closed_club.service;

import com.club.closed_club.dto.QrScanResponse;
import com.club.closed_club.entity.Member;
import com.club.closed_club.entity.QrCode;
import com.club.closed_club.entity.ScanEvent;
import com.club.closed_club.exception.InvalidQrException;
import com.club.closed_club.repository.QrCodeRepository;
import com.club.closed_club.repository.ScanEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QrService {
    private final QrCodeRepository qrCodeRepository;
    private final ScanEventRepository scanEventRepository;
    private final ScanAuditService scanAuditService;


    public QrService(
            QrCodeRepository qrCodeRepository,
            ScanEventRepository scanEventRepository,
            ScanAuditService scanAuditService) {
        this.qrCodeRepository = qrCodeRepository;
        this.scanEventRepository = scanEventRepository;
        this.scanAuditService = scanAuditService;
    }


    @Transactional
    public QrScanResponse scan(UUID uuid, String scannerId) {
        //Находим QR и блокируем строку
        QrCode qr = qrCodeRepository.findByUuidForUpdate(uuid)
                .orElseThrow(() -> new InvalidQrException("QR code not found"));

        Member member = qr.getMember();

        if (!Boolean.TRUE.equals(qr.getStatus())) {
            scanAuditService.recordDenied(
                    uuid,
                    member.getId(),
                    scannerId,
                    "QR_ALREADY_USDED"
            );


            throw new InvalidQrException("QR code already used");
        }



        if (!Boolean.TRUE.equals(member.getStatus())) {
            scanAuditService.recordDenied(
                    uuid,
                    member.getId(),
                    scannerId,
                    "MEMBER_INACTIVE"
            );

            throw new InvalidQrException("Member is inactive");
        }

        qr.setStatus(false);
        qrCodeRepository.saveAndFlush(qr);

        QrCode newQr = new QrCode(member);
        qrCodeRepository.save(newQr);

        scanEventRepository.save(new ScanEvent(
                uuid,
                member.getId(),
                scannerId,
                "ALLOWED",
                null
        ));

        return new QrScanResponse(
                member.getId(),
                member.getName(),
                newQr.getUuid()
        );
    }
}
