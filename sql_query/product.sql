INSERT INTO appproduct.product_fitness (
    id, calories, carbohydrates, created_by_role, dt_create, dt_update, fats, proteins, title, weight
) VALUES (
    '753e6294-466b-42ec-9621-9e97ab6feb29', -- UUID
    random() * 1000, -- Random double precision value
    random() * 100, -- Random double precision value
    'Role_' || floor(random() * 10 + 1)::int, -- Random character varying (role)
    floor(random() * 1000000)::bigint, -- Random bigint (dt_create)
    floor(random() * 1000000)::bigint, -- Random bigint (dt_update)
    random() * 100, -- Random double precision value (fats)
    random() * 100, -- Random double precision value (proteins)
    'Title_' || floor(random() * 100 + 1)::int, -- Random character varying (title)
    random() * 1000 -- Random double precision value (weight)
);
