  create extension if not exists "uuid-ossp";

  create table if not exists employee
  (
      id              varchar primary key default uuid_generate_v4(),
      first_name      varchar    not null,
      last_name       varchar    not null,
      birth_date      date       not null
  );