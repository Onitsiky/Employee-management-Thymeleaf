create table if not exists employee_category(
                                                id varchar primary key default  uuid_generate_v4(),
                                                category_id varchar,
                                                employee_id varchar,
                                                constraint category_fk foreign key (category_id) references socio_professional_category(id),
                                                constraint employee_fk foreign key (employee_id) references employee(id)
);