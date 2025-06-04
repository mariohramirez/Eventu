CREATE TABLE user_profile (
    user_id BIGINT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone VARCHAR(20)
);

CREATE TABLE organization(
    id BIGSERIAL PRIMARY KEY,
    organization_number VARCHAR(255),
    name VARCHAR(255),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    is_active BOOLEAN DEFAULT TRUE,
    is_verified BOOLEAN DEFAULT FALSE
);

ALTER TABLE users ADD COLUMN organization_id BIGINT REFERENCES organization(id);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE  user_roles(
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    role_id INT REFERENCES roles(id) ON DELETE CASCADE,
    organization_id BIGINT REFERENCES organization(id) ON DELETE CASCADE,
    PRIMARY KEY(user_id, role_id, organization_id)
);