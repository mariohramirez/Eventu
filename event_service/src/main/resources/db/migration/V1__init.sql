CREATE TABLE locations(
    id BIGSERIAL PRIMARY KEY,
    location_number VARCHAR(255),
    name VARCHAR(255),
    description TEXT,
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    latitude DECIMAL(10, 7),
    longitude DECIMAL(10, 7),
    is_online BOOLEAN,
    online_url VARCHAR(512),
    timezone VARCHAR(50) NOT NULL,
    capacity INT,
    facilities TEXT[]
);

CREATE TABLE events(
    id BIGSERIAL PRIMARY KEY,
    event_number VARCHAR(255),
    title VARCHAR(255) NOT NULL,
    description TEXT,
    short_description VARCHAR(255),
    start_time TIMESTAMPTZ NOT NULL,
    end_time TIMESTAMPTZ NOT NULL,
    location_id BIGINT REFERENCES locations(id),
    organizer_id BIGINT NOT NULL,
    status VARCHAR(30),
    visibility VARCHAR(30),
    max_attendees INT,
    min_attendees INT,
    current_attendees INT,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    published_at TIMESTAMPTZ
);

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    icon VARCHAR(50)
);

CREATE TABLE event_categories (
    event_id BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    category_id INT NOT NULL REFERENCES categories(id) ON DELETE CASCADE,
    PRIMARY KEY (event_id, category_id)
);

CREATE TABLE tags (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    color VARCHAR(7)
);

CREATE TABLE event_tags (
    event_id BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    tag_id INT NOT NULL REFERENCES tags(id) ON DELETE CASCADE,
    PRIMARY KEY (event_id, tag_id)
);

CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    comment_number VARCHAR(255),
    event_id BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE event_feed (
    id BIGSERIAL PRIMARY KEY,
    post_number VARCHAR(255),
    event_id BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    author_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    post_type VARCHAR(20) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE attendees (
    id SERIAL PRIMARY KEY,
    event_id BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    user_id BIGINT,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    status VARCHAR(20) NOT NULL,
    registration_date TIMESTAMPTZ NOT NULL
);