CREATE DATABASE university;

CREATE USER test_user WITH password 'qwerty';

GRANT ALL ON DATABASE university TO test_user;
    