CREATE TABLE roles
(
  role_id INTEGER PRIMARY KEY,
  name VARCHAR(30) NOT NULL
);

CREATE TABLE users
(
   user_id SERIAL PRIMARY KEY,
   first_name VARCHAR(50),
   last_name VARCHAR(50),
   phone VARCHAR(15) NOT NULL UNIQUE,
   email VARCHAR(100) NOT NULL UNIQUE,
   username VARCHAR(50) NOT NULL UNIQUE,
   password CHAR(144) NOT NULL,
   created_at TIMESTAMP NOT NULL DEFAULT NOW(),
   created_by INTEGER,
   is_active BOOLEAN NOT NULL DEFAULT FALSE,
   is_locked BOOLEAN NOT NULL DEFAULT FALSE,
   role_id INTEGER NOT NULL,
   CONSTRAINT users_created_by_fk FOREIGN KEY (created_by) REFERENCES users (user_id) ON DELETE SET NULL
)