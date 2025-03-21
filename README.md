query
create table students(
    student_id serial primary key,
    student_name varchar(50) not null,
    email varchar(50) unique not null,
    phone_number varchar(20) unique not null
);
create table instructors(
    instructor_id serial primary key ,
    instructor_name varchar(50) not null,
    email varchar(50) unique not null
);
create table courses(
    course_id serial primary key ,
    course_name varchar(50) not null ,
    description varchar(250),
    instructor_id int ,
    foreign key (instructor_id) references instructors(instructor_id) on UPDATE cascade on DELETE cascade
);
create table student_course(
    id serial primary key,
    student_id int not null ,
    course_id int not null ,
    foreign key (student_id) references students(student_id) on UPDATE cascade on DELETE cascade,
    foreign key (course_id) references courses(course_id) on update cascade on delete cascade
);


INSERT INTO courses (course_name, description, instructor_id) VALUES
('Introduction to Computer Science', 'A beginner-friendly course covering the basics of computer science.', 1),
('Advanced Mathematics', 'An in-depth course on advanced mathematical concepts.', 2),
('Data Structures and Algorithms', 'Learn about various data structures and algorithms.', 3),
('Web Development', 'A comprehensive course on building modern web applications.', 1),
('Machine Learning', 'An introductory course on machine learning techniques.', 3);
