INSERT INTO projects 
	(id, name, descript)
VALUES 
 	('1', 'best project', 'crm system for university'),
 	('2', 'second', 'online toys shop');
 	
INSERT INTO roles
	(id, role_type)
VALUES
	('1', 'PROJECT_MANAGER'),
	('2', 'DEVELOPER'),
	('3', 'TEST_ENGINEER'),
	('4', 'ADMINISTRATOR');
	
INSERT INTO users
	(id, first_name, last_name, email, created_at, role)
VALUES
	('1', 'Ivan', 'Petrov', 'ivanpetrov@gmai.com', '2001-09-28','1'),
	('2', 'Oleg', 'Ivanov', 'olegoleg@gmail.com', '2001-09-28', '2'),
	('3', 'Jeck', 'Sparrow', 'karibianpirates@gmail.com', '2001-09-28','3'),
	('4', 'Anton', 'Prokopov', 'antohaanton@gmail.com', '2001-09-28','4'),
	('5', 'Ihor', 'Lvov', 'igoryan@gmail.com', '2001-09-28', '2');
	
INSERT INTO projects_users
	(project_id, user_id)
VALUES
	('1','1'),
	('1','2'),
	('1','3'),
	('1','4');
	
INSERT INTO ticket_type
	(id, ticket_type)
VALUES
	('1','BUG'),
	('2','TASK');
	
INSERT INTO ticket_priority
	(id, ticket_priority)
VALUES
	('1', 'TRIVIAL'),
	('2', 'MINOR'),
	('3', 'MAJOR'),
	('4', 'CRITICAL');
	
INSERT INTO ticket_status
	(id, ticket_status)
VALUES
	('1', 'UNASSIGNED'),
	('2', 'ASSIGNED'),
	('3', 'RESOLVED'),
	('4', 'CLOSED'),
	('5','REOPENED');
	
INSERT INTO tickets
	(id, title, description, created_at, created_by, target_date,
		actual_date, ticket_type, ticket_priority, ticket_status)
VALUES
	('1', 'spring security','add authorization & authentication functionality', '2001-09-28', '3', '2001-10-28','2001-11-28', 
		'2', '3', '2' ),
	('2', 'db migration','db migration with swagger', '2001-09-28', '1', '2001-10-28','2001-11-28', 
		'2', '2', '2' );
		
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
		