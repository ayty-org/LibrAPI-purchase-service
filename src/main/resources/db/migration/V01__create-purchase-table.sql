create table purchase (id  bigserial not null, amount_to_pay float8 not null, books varchar(500), specificid varchar(255), status varchar(255), user_id varchar(255), primary key (id))