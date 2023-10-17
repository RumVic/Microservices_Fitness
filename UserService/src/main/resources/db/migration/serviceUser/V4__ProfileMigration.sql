CREATE SCHEMA IF NOT EXISTS appUser;
CREATE TABLE appUser.profile_fitness (
    id UUID NOT NULL,
    dt_create BIGINT,
    dt_update BIGINT,
    user_id UUID,
    height DOUBLE PRECISION,
    weight DOUBLE PRECISION,
    birthday DATE,
    gender VARCHAR(255) COLLATE pg_catalog."default",
    lifestyle VARCHAR(255) COLLATE pg_catalog."default",
    target_weight DOUBLE PRECISION,
    CONSTRAINT profile_fitness_pkey PRIMARY KEY (id),
    CONSTRAINT fkddr4sj2enb8n3hodsma7pt64h FOREIGN KEY (user_id)
            REFERENCES appUser.user_fitness (id) MATCH SIMPLE
             ON UPDATE NO ACTION
             ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS appUser.profile_fitness
    OWNER to postgres;
