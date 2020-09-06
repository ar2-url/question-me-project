CREATE DATABASE IF NOT EXISTS question_me;
CREATE USER IF NOT EXISTS 'question_me'@'%' IDENTIFIED BY 'question_me4all';
GRANT ALL PRIVILEGES ON question_me.* TO 'question_me'@'%';