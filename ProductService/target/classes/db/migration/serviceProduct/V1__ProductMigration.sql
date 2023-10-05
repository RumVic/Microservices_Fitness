-- In flyaway schemas it is mater which entity goes at first
-- because as constrains come in it need according table
-- but PSQL can not find existing table
CREATE SCHEMA IF NOT EXISTS appProduct;

CREATE TABLE IF NOT EXISTS appProduct.product_fitness(
    id uuid NOT NULL,
    calories double precision NOT NULL,
    carbohydrates double precision NOT NULL,
    created_by_role character varying(255) COLLATE pg_catalog."default",
    dt_create bigint,
    dt_update bigint,
    fats double precision NOT NULL,
    proteins double precision NOT NULL,
    title character varying(255) COLLATE pg_catalog."default",
    weight double precision NOT NULL,
    CONSTRAINT product_fitness_pkey PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS appProduct.product_fitness
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS appProduct.dish_fitness(
    id uuid NOT NULL,
    create_by_role character varying(255) COLLATE pg_catalog."default",
    dt_create bigint,
    dt_update bigint,
    title character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT dish_fitness_pkey PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS appProduct.dish_fitness
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS appProduct.composition_dish_fitness
(
    id uuid NOT NULL,
    dish uuid,
    dt_create bigint,
    dt_update bigint,
    title character varying(255),
    weight double precision NOT NULL,
    product_id uuid,
    CONSTRAINT composition_dish_fitness_pkey PRIMARY KEY (id),
    CONSTRAINT fkddr4sj2enb8n3hodsma7pt64h FOREIGN KEY (product_id)
        REFERENCES appProduct.product_fitness (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS appProduct.composition_dish_fitness
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS appProduct.diary_food_fitness(
    id uuid NOT NULL,
    dt_create bigint,
    dt_supply bigint,
    dt_update bigint,
    profile uuid,
    weight_dish double precision,
    weight_product double precision,
    dish_id uuid,
    product_id uuid,
    CONSTRAINT diary_food_fitness_pkey PRIMARY KEY (id),
    CONSTRAINT fk8a6o1a0cymmd0hqgfxjtbp3vg FOREIGN KEY (product_id)
        REFERENCES appProduct.product_fitness (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fka98nproi6misbreau5x98m0lt FOREIGN KEY (dish_id)
        REFERENCES appProduct.dish_fitness (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS appProduct.diary_food_fitness
    OWNER to postgres;