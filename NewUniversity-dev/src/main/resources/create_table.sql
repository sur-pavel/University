CREATE TABLE chairs (     
    id SERIAL PRIMARY KEY,     
    name VARCHAR(20) NOT NULL     
);

CREATE TABLE groups (     
    id SERIAL PRIMARY KEY,     
    name VARCHAR(20) NOT NULL, 
    chair_id int,     
    FOREIGN KEY (chair_id) REFERENCES chairs(id) 
);


CREATE TABLE students (     
    id SERIAL PRIMARY KEY,     
    first_name VARCHAR(20) NOT NULL, 
    last_name VARCHAR(20) NOT NULL, 
    group_id int,     
    FOREIGN KEY (group_id) REFERENCES groups(id)
);
    