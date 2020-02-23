CREATE TABLE projects(
	id           SERIAL         PRIMARY KEY,
	name         VARCHAR(50)    NOT NULL UNIQUE,
	descript     VARCHAR
);

CREATE TABLE users_roles(
	id           SERIAL         PRIMARY KEY,
	role         VARCHAR(20)    
);

CREATE TABLE users(
	id           SERIAL         PRIMARY KEY,
	first_name   VARCHAR(20)    NOT NULL,
	last_name    VARCHAR(20)    NOT NULL,
	email        VARCHAR(50)    NOT NULL UNIQUE,
	created_at   DATE,
	role         BIGINT,
	password     VARCHAR        NOT NULL	
);

CREATE TABLE projects_users(
	project_id   BIGINT         NOT NULL,
	user_id      BIGINT         NOT NULL,
	CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES 
			users(id),
	CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES
			projects(id)
);

CREATE TABLE tickets(
	id              SERIAL         PRIMARY KEY,
	project_id      BIGINT         NOT NULL,
	title           VARCHAR(30)    NOT NULL,
	description     VARCHAR,
	created_at      TIMESTAMP      NOT NULL,
	created_by      BIGINT         NOT NULL,
	target_date     DATE,
	actual_date     DATE,
	ticket_type     VARCHAR        NOT NULL,
	ticket_priority VARCHAR        NOT NULL,
	ticket_status   VARCHAR        NOT NULL,
	CONSTRAINT fk_createdBy_user FOREIGN KEY (created_by) 
		REFERENCES users(id),
	CONSTRAINT fk_tickets_projects FOREIGN KEY (project_id)
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
	created_at  TIMESTAMP       NOT NULL,
	ticket_id   BIGINT,
	created_by  BIGINT          NOT NULL,
	CONSTRAINT fk_ticket_comment_user FOREIGN KEY (created_by)
		REFERENCES users(id),
	CONSTRAINT fk_ticket_comment_ticket FOREIGN KEY (ticket_id)
		REFERENCES tickets(id)
);

CREATE TABLE ticket_log(
	id          SERIAL          PRIMARY KEY,
	message     VARCHAR         NOT NULL,
	created_at  TIMESTAMP       NOT NULL,
	ticket_id   BIGINT          NOT NULL,
	CONSTRAINT fk_tiket_log_ticket FOREIGN KEY (ticket_id)
		REFERENCES tickets(id)
);

ALTER SEQUENCE projects_id_seq INCREMENT BY 50;
ALTER SEQUENCE ticket_comments_id_seq INCREMENT BY 50;
ALTER SEQUENCE ticket_log_id_seq INCREMENT BY 50;
ALTER SEQUENCE tickets_id_seq INCREMENT BY 50;
ALTER SEQUENCE users_id_seq INCREMENT BY 50;
ALTER SEQUENCE users_roles_id_seq INCREMENT BY 50;


