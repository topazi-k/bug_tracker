CREATE USER new_user PASSWORD '12345';
GRANT ALL PRIVILEGES ON DATABASE bug_tracker TO new_user;
ALTER USER new_user WITH SUPERUSER;                /*temporary*/