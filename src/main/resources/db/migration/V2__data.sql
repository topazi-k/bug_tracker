INSERT INTO projects 
	(id, name, descript)
VALUES 
 	('1', 'best project', 'crm system for university'),
 	('2', 'second', 'online toys shop');
 	
INSERT INTO users_roles
	(id, role)
VALUES
	('1','ROLE_ADMIN'),
	('2','ROLE_MANAGER'),
	('3','ROLE_DEV'),
    ('4','ROLE_GUEST');
	
INSERT INTO users
	(id, first_name, last_name, email, created_at, role, password)
VALUES
	('1', 'Ivan', 'Petrov', 'manager@gmai.com', '2001-09-28','2', '{noop}1111'),
	('2', 'Oleg', 'Ivanov', 'developer@gmail.com', '2001-09-28', '3', '{noop}1111'),
	('3', 'Jeck', 'Sparrow', 'tester@gmail.com', '2001-09-28','3', '{noop}1111'),
	('4', 'Anton', 'Prokopov', 'admin@gmail.com', '2001-09-28','1', '{noop}admin'),
	('5', 'Ihor', 'Lvov', 'dev@gmail.com', '2001-09-28', '3', '{noop}1111');
	
INSERT INTO projects_users
	(project_id, user_id)
VALUES
	('1','1'),
	('1','2'),
	('1','3'),
	('1','4');
	
	
INSERT INTO tickets
	(id, project_id,title, description, created_at, created_by, target_date,
		actual_date, ticket_type, ticket_priority, ticket_status)
VALUES
	('1','1' ,'spring security','add authorization & authentication functionality', '2001-09-28', '3', '2001-10-28','2001-11-28', 
		'BUG', 'TRIVIAL', 'ASSIGNED' ),
	('2','1', 'db migration','db migration with swagger', '2001-09-28', '1', '2001-10-28','2001-11-28', 
		'BUG', 'TRIVIAL', 'ASSIGNED' );
		
INSERT INTO ticket_assigned_users
	(user_id, ticket_id)
VALUES
	('2','1'),
	('5','2');
	
INSERT INTO ticket_comments
	(id, comment, created_at,ticket_id, created_by)
VALUES 
	('1', 'Hello! I am a new comment' ,'2001-09-28', '1', '1'),
	('2', 'Hello! I am a second comment','2001-09-28','1','2'),
	('3', 'Hello! I am a third comment', '2001-09-28', '2','3');
		