-- add column personal number
alter table employee add column personal_number varchar(7);

-- create sequence
create sequence if not exists employee_seq start 1;

-- Function to generate custom personal number
create or replace function generate_personal_number()
    returns trigger as
$$
begin
    new.personal_number := 'EMP-' || lpad(nextval('employee_seq')::text, 3, '0');
    return new;
end;
$$
    language plpgsql;

-- Creating the trigger to generate the personal number on insert
create trigger insert_new_employee
    before insert
    on employee
    for each row
execute function generate_personal_number();

-- add image attribute to employee table
alter table employee add column image bytea;