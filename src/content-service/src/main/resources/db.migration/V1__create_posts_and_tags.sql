CREATE TABLE IF NOT EXISTS posts (
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(200)    NOT NULL,
    content      TEXT            NOT NULL,
    author_id    BIGINT          NOT NULL,
    created_at   TIMESTAMPTZ     NOT NULL DEFAULT now(),
    updated_at   TIMESTAMPTZ     NOT NULL DEFAULT now(),
    is_private   BOOLEAN         NOT NULL DEFAULT FALSE
    );

CREATE TABLE IF NOT EXISTS post_tags (
    post_id BIGINT      NOT NULL,
    tag     VARCHAR(100) NOT NULL,
    PRIMARY KEY (post_id, tag),
    CONSTRAINT fk_post_tags_post
    FOREIGN KEY(post_id) REFERENCES posts(id)
    ON DELETE CASCADE
);

