create table patients(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    specialty varchar(100) not null,
    doctor_id bigint,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id),

    primary key(id)

);