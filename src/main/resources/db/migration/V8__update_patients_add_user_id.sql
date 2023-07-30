alter table patients
ADD COLUMN user_id bigint;

alter table patients
ADD CONSTRAINT fk_patients_user_id
foreign key (user_id) REFERENCES users(id);
