drop user if exists 'profesor'@'localhost';
create user 'profesor'@'localhost' identified by 'pestillo';
grant all privileges on instituto.* to 'profesor'@'localhost';
flush privileges ;