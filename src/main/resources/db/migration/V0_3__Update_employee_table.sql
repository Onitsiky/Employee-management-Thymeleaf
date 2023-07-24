do
$$
    begin
        if not exists(select from pg_type where typname = 'gender') then
            create type gender as enum ('M', 'F');
        end if;
    end
$$;

alter table employee add column sex gender;
alter table employee add column address varchar;
alter table employee add column personal_email varchar;
alter table employee add column professional_email varchar;
alter table employee add column id_card_number varchar;
alter table employee add column id_card_delivered_date date;
alter table employee add column id_card_delivered_place varchar;
alter table employee add column function varchar;
alter table employee add column children_in_charge integer default 0;
alter table employee add column hiring_date date;
alter table employee add column departure_date date;
alter table employee add column cnaps_number varchar;