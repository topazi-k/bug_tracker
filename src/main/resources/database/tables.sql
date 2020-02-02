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

CREATE TABLE projects_users(
	project_id   BIGINT         NOT NULL,
	user_id      BIGINT         NOT NULL,
	CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES 
			users(id),
	CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES
			projects(id)
);

CREATE TABLE ticket_type(
	id           SERIAL         PRIMARY KEY,
	ticket_type  VARCHAR(20)    NOT NULL
);

CREATE TABLE ticket_priority(
	id              SERIAL      PRIMARY KEY,
	ticket_priority VARCHAR(20) NOT NULL
);

CREATE TABLE ticket_status(
	id             SERIAL       PRIMARY KEY,
	ticket_status  VARCHAR(20)  NOT NULL
);

CREATE TABLE tickets(
	id              SERIAL         PRIMARY KEY,
	project_id      BIGINT         NOT NULL,
	title           VARCHAR(30)    NOT NULL,
	description     VARCHAR,
	created_at      DATE           NOT NULL,
	created_by      BIGINT         NOT NULL,
	target_date     DATE,
	actual_date     DATE,
	ticket_type     BIGINT         NOT NULL,
	ticket_priority BIGINT         NOT NULL,
	ticket_status   BIGINT         NOT NULL,
	CONSTRAINT fk_createdBy_user FOREIGN KEY (created_by) 
		REFERENCES users(id),
	CONSTRAINT fk_ticket_type FOREIGN KEY (ticket_type) 
		REFERENCES ticket_type(id),
	CONSTRAINT fk_ticket_priority FOREIGN KEY (ticket_priority)
		REFERENCES ticket_priority(id),
	CONSTRAINT fk_ticket_status FOREIGN KEY (ticket_status) 
		REFERENCES ticket_status(id),
	CONSTRAINT fk_tickets_projects FOREIGN KEY project_id
		REFERENCES projects(id)
);

CREATE TABLE ticket_assigned_users(
	user_id 	    BIGINT         NOT NULL,
	ticket_id       BIGINT         NOT NULL,
	CONSTRAINT fk_user_id_users FOREIGN KEY (user_id)
		REFERENCES users(id),
	CONSTRAINT fk_ticket_id_tickets FOREIGN KEY (ticket_id)
		REFERENCES tickets(id)
		
);

CREATE TABLE ticket_comments(
	id          SERIAL          PRIMARY KEY,
	comment     VARCHAR         NOT NULL,
	created_at  DATE            NOT NULL,
	ticket_id   BIGINT          NOT NULL,
	created_by  BIGINT          NOT NULL,
	CONSTRAINT fk_ticket_comment_user FOREIGN KEY (created_by)
		REFERENCES users(id),
	CONSTRAINT fk_ticket_comment_ticket FOREIGN KEY (ticket_id)
		REFERENCES tickets(id)
);

