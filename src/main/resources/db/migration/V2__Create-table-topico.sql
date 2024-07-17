CREATE TABLE topico (
    id BIGSERIAL NOT NULL,
    title VARCHAR(100) NOT NULL,
    message VARCHAR(100) NOT NULL,
    course VARCHAR(30) NOT NULL,
    creation TIMESTAMPTZ NOT NULL,
    estado VARCHAR(100) NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topico_usuario_id FOREIGN KEY (author_id) REFERENCES usuario(id)
);