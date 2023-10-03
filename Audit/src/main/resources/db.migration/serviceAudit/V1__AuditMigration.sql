CREATE SCHEMA IF NOT EXISTS appAudit;
CREATE TABLE IF NOT EXISTS appAudit.audit_fitness(
    id uuid NOT NULL,
    dt_create bigint,
    dt_update bigint,
    text character varying(255),
    type character varying(255),
    uid character varying(255),
    user_id uuid,
    CONSTRAINT audit_fitness_pkey PRIMARY KEY (id),
    CONSTRAINT fkuw1bpygmwuo2lijig5qid718 FOREIGN KEY (user_id)
        REFERENCES appUser.user_fitness (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS appAudit.audit_fitness
    OWNER to postgres;

