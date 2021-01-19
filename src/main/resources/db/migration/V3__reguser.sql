-- CREATE TABLE IF NOT EXISTS user_reg(
--     email VARCHAR (50) PRIMARY KEY,
--     first_name   VARCHAR(20)    NOT NULL,
--     last_name    VARCHAR(20)    NOT NULL,
--     password     VARCHAR        NOT NULL,
--     expiry_date   DATE           NOT NULL,
--     token        VARCHAR        NOT NULL
-- );
ALTER TABLE users ADD COLUMN
    enabled BOOLEAN NOT NULL default false;

CREATE TABLE IF NOT EXISTS reg_token(
    token         VARCHAR(200)   PRIMARY KEY,
    expiry_date   DATE           NOT NULL,
    user_id       BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES
			users(id)
);

