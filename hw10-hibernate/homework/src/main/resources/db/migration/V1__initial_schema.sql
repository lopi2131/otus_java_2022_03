create table client
(
    id   bigserial not null primary key,
    client_name varchar(50),
    address_id bigint
);
create table address
(
    id   bigserial not null primary key,
    street varchar(50)

);
create table phone
(
    id   bigserial primary key,
    phone_number varchar(50),
    client_id bigint
);