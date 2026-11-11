package com.club.closed_club.controller;

import com.club.closed_club.dto.QrScanResponse;
import com.club.closed_club.dto.QrScanRequest;
import com.club.closed_club.service.QrService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/qr")
public class QrController {
    private final QrService qrService;

    public QrController(QrService qrService) {
        this.qrService = qrService;
    }

    @PostMapping("/scan")
    public QrScanResponse scan(
            @Valid @RequestBody QrScanRequest request) {
        return qrService.scan(
                request.uuid(),
                "local-test-scanner"
        );
    }
}
