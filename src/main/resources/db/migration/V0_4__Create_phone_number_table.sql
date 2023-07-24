create table if not exists phone_number(
                                           id varchar primary key default uuid_generate_v4(),
                                           number varchar not null,
                                           employee_id varchar,
                                           constraint user_fk foreign key (employee_id) references employee(id)
);