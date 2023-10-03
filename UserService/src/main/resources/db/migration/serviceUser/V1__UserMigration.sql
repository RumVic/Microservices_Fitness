CREATE SCHEMA IF NOT EXISTS appUser;

CREATE TABLE IF NOT EXISTS appUser.user_fitness
(
    id uuid NOT NULL,
    activation_code character varying(255),
    dt_create bigint,
    dt_update bigint,
    login character varying(255) ,
    password character varying(255) ,
    role character varying(255),
    status character varying(255),
    username character varying(255),
    CONSTRAINT user_fitness_pkey PRIMARY KEY (id),
    CONSTRAINT uk_iexs7prkdjij3n0b8csfpm32t UNIQUE (login)
);
