CREATE TABLE enterprises
(
    id         BIGSERIAL PRIMARY KEY         NOT NULL,
    name       character varying(255) UNIQUE NOT NULL,
    nit        character varying(255) UNIQUE NOT NULL,
    reported   bool                          NOT NULL,
    black_list bool                          NOT NULL,
    created_at timestamp(0) without time zone NOT NULL
);