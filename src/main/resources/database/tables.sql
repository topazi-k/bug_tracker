CREATE TABLE projects(
	id           SERIAL         PRIMARY KEY,
	name         VARCHAR(50)    NOT NULL UNIQUE,
	descript     VARCHAR
);

CREATE TABLE roles(
	id           SERIAL         PRIMARY KEY,
	role_type    VARCHAR(50)
);

CREATE TABLE users(
	id           SERIAL         PRIMARY KEY,
	first_name   VARCHAR(20)    NOT NULL,
	last_name    VARCHAR(20)    NOT NULL,
	email        VARCHAR(50)    NOT NULL UNIQUE,
	created_at   DATE,
	role         BIGINT         NOT NULL,
	CONSTRAINT fk_users_roles FOREIGN KEY (role) REFERENCES 
			roles(id)
);

