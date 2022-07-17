
create table clients
(
    id   bigserial not null primary key,
    client_name varchar(50),
    address_id bigint
);
create table addresses
(
    id   bigserial not null primary key,
    street varchar(50)
);
create table phones
(
    id   bigserial primary key,
    phone_number varchar(50),
    client_id bigint
);

