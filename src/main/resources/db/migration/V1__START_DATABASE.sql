-- 1. USUARIO (DDL)
CREATE TABLE usuario (
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username          VARCHAR(255)                            NOT NULL,
    password          VARCHAR(255)                            NOT NULL,
    email             VARCHAR(255)                            NOT NULL,
    registration_date DATE                                        NULL,
    url_image         VARCHAR(500)                                NULL,
    enabled           BOOLEAN                                 NOT NULL,
	CONSTRAINT pk_usuario          PRIMARY KEY (id),
    CONSTRAINT uc_usuario_username UNIQUE (username)
);

CREATE TABLE roles (
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE usuario_roles (
    role_id    BIGINT       NOT NULL,
    usuario_id BIGINT       NOT NULL,
	CONSTRAINT pk_usuario_roles PRIMARY KEY (usuario_id, role_id),
	CONSTRAINT fk_usuario_roles_on_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id),
	CONSTRAINT fk_usuario_roles_on_roles FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- 2. BAND E SINGER (DDL)
CREATE TABLE singer (
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    usuario_id        BIGINT  NOT NULL,
    enabled           BOOLEAN     NULL,
    registration_date DATE        NULL,
	CONSTRAINT pk_singer PRIMARY KEY (id),
    CONSTRAINT fk_singer_on_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

CREATE TABLE band (
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    image_band        VARCHAR(500)     NULL,
    enabled           BOOLEAN          NULL,
    registration_date DATE             NULL,
	CONSTRAINT pk_band PRIMARY KEY (id)
);

-- 2.1 BAND PODE TER MUITOS SINGERS, ASSIM COMO MUITOS SINGERS PODEM PERTENCER A MUITAS BANDS
CREATE TABLE band_singer (
	band_id   BIGINT NOT NULL,
	singer_id BIGINT NOT NULL,
	CONSTRAINT pk_band_singer PRIMARY KEY (band_id, singer_id),
	CONSTRAINT fk_band_singer_on_band FOREIGN KEY (band_id) REFERENCES band (id),
    CONSTRAINT fk_band_singer_on_singer FOREIGN KEY (singer_id) REFERENCES singer (id)
);

-- 3. BAND PRESENTATION E SINGER PRESENTATION (DDL)
CREATE TABLE singer_presentation (
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY,
    singer_id          BIGINT    NOT NULL,
    enabled            BOOLEAN,
    show_image         VARCHAR(500)  NULL,
    start_presentation TIME NULL,
    end_presentation   TIME NULL,
	CONSTRAINT pk_singer_presentation PRIMARY KEY (id),
    CONSTRAINT fk_singer_presentation_on_singer FOREIGN KEY (singer_id) REFERENCES singer (id)
);

CREATE TABLE band_presentation (
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY,
    enabled            BOOLEAN       NULL,
	band_id            BIGINT    NOT NULL,
    show_image         VARCHAR(500)  NULL,
    start_presentation TIME NULL,
    end_presentation   TIME NULL,
	CONSTRAINT pk_band_presentation PRIMARY KEY (id),
	CONSTRAINT fk_band_presentation_on_band FOREIGN KEY (band_id) REFERENCES band (id)
);

-- 4. SHOW (DDL)
CREATE TABLE show (
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY,
    date    DATE    NOT NULL,
    enabled BOOLEAN     NULL,
    CONSTRAINT uc_show_date UNIQUE (date),
	CONSTRAINT pk_show PRIMARY KEY (id)
);

-- 4.1 UM SHOW PODE TER MUITOS SINGERS
CREATE TABLE show_singer_presentation (
    show_id                BIGINT NOT NULL,
    singer_presentation_id BIGINT NOT NULL,
    CONSTRAINT pk_show_singer_presentation PRIMARY KEY (show_id, singer_presentation_id),
    CONSTRAINT fk_show_singer_presentation_on_show FOREIGN KEY (show_id) REFERENCES show (id),
    CONSTRAINT fk_show_singer_presentation_on_singer_presentation FOREIGN KEY (singer_presentation_id) REFERENCES singer_presentation (id)
);

-- 4.2 UM SHOW PODE TER MUITAS BANDAS
CREATE TABLE show_band_presentation (
    show_id              BIGINT NOT NULL,
    band_presentation_id BIGINT NOT NULL,
    CONSTRAINT pk_show_band_presentation PRIMARY KEY (show_id, band_presentation_id),
    CONSTRAINT fk_show_band_presentation_on_show FOREIGN KEY (show_id) REFERENCES show (id),
    CONSTRAINT fk_show_band_presentation_on_band_presentation FOREIGN KEY (band_presentation_id) REFERENCES band_presentation (id)
);

-- 5. SingerPresentationVote E BandPresentationVote
CREATE TABLE singer_presentation_vote (
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    singer_presentation_id  BIGINT                                  NOT NULL,
    votes                   INTEGER                                     NULL,
    enabled                 BOOLEAN                                     NULL,
    CONSTRAINT pk_singer_presentation_vote PRIMARY KEY (id),
    CONSTRAINT fk_singer_presentation_vote_on_singer_presentation FOREIGN KEY (singer_presentation_id) REFERENCES singer_presentation (id)
);

CREATE TABLE band_presentation_vote (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    band_presentation_id      BIGINT           NOT NULL,
    votes                     INTEGER          NOT NULL,
    enabled                   BOOLEAN              NULL,
    CONSTRAINT pk_band_presentation_vote PRIMARY KEY (id),
    CONSTRAINT fk_band_presentation_vote_on_band_presentation FOREIGN KEY (band_presentation_id) REFERENCES band_presentation (id)
);

-- 6. SHOW VOTE
CREATE TABLE show_vote (
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    start_voting TIMESTAMP     DEFAULT CURRENT_TIMESTAMP NOT NULL,
    end_voting   TIMESTAMP                               NOT NULL,
    enabled      BOOLEAN                                     NULL,
    CONSTRAINT pk_show_vote PRIMARY KEY (id)
);

-- 6.1 SHOW VOTE PODE TER MUITOS SingerPresentationVote
CREATE TABLE show_vote_singer_presentation_vote (
    show_vote_id                BIGINT NOT NULL,
    singer_presentation_vote_id BIGINT NOT NULL,
    CONSTRAINT pk_show_vote_singer_presentation_vote           PRIMARY KEY (show_vote_id, singer_presentation_vote_id),
    CONSTRAINT fk_show_vote_singer_presentation_vote_on_show_vote FOREIGN KEY (show_vote_id) REFERENCES show_vote (id),
    CONSTRAINT fk_show_vote_singer_presentation_vote_on_singer_presentation_vo FOREIGN KEY (singer_presentation_vote_id) REFERENCES singer_presentation_vote (id)
);

-- 6.2 SHOW VOTE PODE TER MUITOS BandPresentationVote
CREATE TABLE show_vote_band_presentation_vote (
    show_vote_id              BIGINT NOT NULL,
    band_presentation_vote_id BIGINT NOT NULL,
    CONSTRAINT pk_show_vote_band_presentation_vote             PRIMARY KEY (show_vote_id, band_presentation_vote_id),
    CONSTRAINT fk_show_vote_band_presentation_vote_on_show_vote FOREIGN KEY (show_vote_id) REFERENCES show_vote (id),
    CONSTRAINT fk_show_vote_band_presentation_vote_on_band_presentation_vote FOREIGN KEY (band_presentation_vote_id) REFERENCES band_presentation_vote (id)
);



