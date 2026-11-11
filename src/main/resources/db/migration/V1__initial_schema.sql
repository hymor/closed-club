CREATE TABLE members (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE qr_codes (
                          id_qr SERIAL PRIMARY KEY,
                          id_user INTEGER NOT NULL
                              REFERENCES members(id) ON DELETE CASCADE,
                          uuid UUID NOT NULL UNIQUE,
                          created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE UNIQUE INDEX idx_one_active_qr_per_user
    ON qr_codes (id_user)
    WHERE status = TRUE;

CREATE TABLE scan_events (
                             id BIGSERIAL PRIMARY KEY,
                             qr_uuid UUID,
                             member_id INTEGER,
                             scanner_id VARCHAR(100) NOT NULL,
                             result VARCHAR(20) NOT NULL
                                 CHECK (result IN ('ALLOWED', 'DENIED')),
                             reason VARCHAR(100),
                             created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);