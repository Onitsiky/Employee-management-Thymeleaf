create table if not exists socio_professional_category(
                                                          id varchar primary key default uuid_generate_v4(),
                                                          category_name varchar not null
);