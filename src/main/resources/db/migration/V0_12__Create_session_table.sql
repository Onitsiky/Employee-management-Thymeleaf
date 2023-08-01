create table if not exists session (
  id varchar constraint session_pk primary key default uuid_generate_v4(),
  session_id varchar not null,
  timeout timestamp with time zone not null,
  user_id varchar not null ,
  constraint session_user_fk foreign key (user_id) references "user"(id)
);