query
create database spring_homework002db;

create table students(
    student_id serial primary key,
    student_name varchar(50) not null,
    email varchar(50) not null,
    phone_number varchar(20) not null
);
create table instructors(
    instructor_id serial primary key ,
    instructor_name varchar(50) not null,
    email varchar(50) not null
);
create table courses(
    course_id serial primary key ,
    course_name varchar(50) not null ,
    description varchar(250),
    instructor_id int ,
    foreign key (instructor_id) references instructors(instructor_id)
);

create table student_course(
    id serial primary key,
    student_id int not null ,
    course_id int not null ,
    foreign key (student_id) references students(student_id),
    foreign key (course_id) references courses(course_id) on delete cascade
);

INSERT INTO instructors(instructor_name, email)
VALUES
    ('LyNa', 'lyna@gmail.com'),
    ('Dara', 'Dara@gmail.com'),
    ('Hong Meng', 'hongmeng@gmail.com');

INSERT INTO courses (course_name, description, instructor_id) VALUES
('Introduction to Computer Science', 'A beginner-friendly course covering the basics of computer science.', 1),
('Advanced Mathematics', 'An in-depth course on advanced mathematical concepts.', 2),
('Data Structures and Algorithms', 'Learn about various data structures and algorithms.', 3),
('Web Development', 'A comprehensive course on building modern web applications.', 1),
('Machine Learning', 'An introductory course on machine learning techniques.', 3);

INSERT INTO students(student_name, email, phone_number)
VALUES
    ('Jack Lockly', 'jack@gmail.com', '09234242'),
    ('Reach', 'reach@gmail.com', '03543453'),
    ('No name', 'ananymouse@gmal.com', '04255325243'),
    ('Iron man', 'javis@gmail.com','01431411');

INSERT INTO student_course (student_id, course_id)
values
 (1, 3),
 (1, 2),
 (2, 2),
 (3, 4),
 (4, 2);



