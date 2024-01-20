CREATE DATABASE IF NOT EXISTS `mydatabase` ;
CREATE USER 'tester'@'%' IDENTIFIED BY 'root' ;
GRANT ALL PRIVILEGES ON `mydatabase`.* TO 'tester'@'%' ;
FLUSH PRIVILEGES ;
