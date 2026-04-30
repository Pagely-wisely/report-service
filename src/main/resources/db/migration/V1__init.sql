-- p_report
CREATE TABLE IF NOT EXISTS p_report
(
    id                  UUID         PRIMARY KEY,
    title               VARCHAR(255) NOT NULL,
    content             TEXT         NOT NULL,
    read_scope          VARCHAR(20)  NOT NULL,
    book_id             VARCHAR(20)  NOT NULL,
    user_id             UUID         NOT NULL,
    meeting_id          UUID,
    meeting_schedule_id UUID,
    created_at          TIMESTAMP    NOT NULL DEFAULT NOW(),
    created_by          UUID         NOT NULL,
    updated_at          TIMESTAMP,
    updated_by          UUID,
    deleted_at          TIMESTAMP,
    deleted_by          UUID,

    CONSTRAINT chk_read_scope CHECK (read_scope IN ('PUBLIC', 'PRIVATE', 'MEETING'))
);