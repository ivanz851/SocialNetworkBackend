CREATE TABLE IF NOT EXISTS users (
    id          BIGSERIAL PRIMARY KEY,
    login       VARCHAR(50)  NOT NULL UNIQUE,
    email       VARCHAR(120) NOT NULL UNIQUE,
    password    VARCHAR(120) NOT NULL,

    first_name  VARCHAR(100),
    last_name   VARCHAR(100),
    birth_date  DATE,
    phone       VARCHAR(25),

    created_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT now()
);

COMMENT ON TABLE  users IS 'Registered users';
COMMENT ON COLUMN users.password IS 'BCrypt hash';
