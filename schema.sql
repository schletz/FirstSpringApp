create sequence hibernate_sequence start with 1 increment by 1
create table courses (id bigint not null, description varchar(255), end_date date, name varchar(255), number varchar(255), start_date date, teacher_id bigint, primary key (id))
create table courses_students (course_id bigint not null, students_id bigint not null)
create table students (id bigint not null, birth_date date, first_name varchar(100) not null, gender varchar(255), last_name varchar(100) not null, registration_ts timestamp not null, version integer, primary key (id))
create table teachers (id bigint not null, first_name varchar(100) not null, gender integer, last_name varchar(100) not null, area_code integer not null, country_code varchar(255) not null, serial_number varchar(255) not null, version integer, primary key (id))
create table teachers_courses (teacher_id bigint not null, courses_id bigint not null)
alter table teachers_courses add constraint UK_63y8pfkasap618r4tkjefsjov unique (courses_id)
alter table courses add constraint FK468oyt88pgk2a0cxrvxygadqg foreign key (teacher_id) references teachers
alter table courses_students add constraint FK8a83yyyla7ilqklkvqlqdxefe foreign key (students_id) references students
alter table courses_students add constraint FKcj1bvqj437mdtgllmwcd41f2u foreign key (course_id) references courses
alter table teachers_courses add constraint FKcibexixqyslbr0gxl7nvqxusl foreign key (courses_id) references courses
alter table teachers_courses add constraint FKgy6pqaopr15itcdokkbb3jgxv foreign key (teacher_id) references teachers
