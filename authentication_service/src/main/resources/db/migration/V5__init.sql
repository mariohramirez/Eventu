ALTER TABLE user_roles DROP CONSTRAINT user_roles_pkey;

ALTER TABLE user_roles ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);

ALTER TABLE user_roles ALTER COLUMN organization_id DROP NOT NULL;