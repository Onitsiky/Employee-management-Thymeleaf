create table if not exists company_configuration(
    id varchar primary key default uuid_generate_v4(),
    company_name varchar not null,
    slogan varchar,
    exact_address varchar,
    contact_email varchar,
    nif varchar,
    stat varchar,
    rcs varchar,
    logo bytea
);

alter table phone_number add column company_configuration_id varchar;
alter table phone_number add constraint company_configuration_fk foreign key (company_configuration_id) references company_configuration(id);