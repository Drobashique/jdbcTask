create table goods(
    name varchar(100) ,
    inner_code integer primary key
);
create table organization
(
    inn          int          primary key,
    name         varchar(100) not null,
    checking_account bigint       not null
);

create table waybill
(
    id                  int primary key,
    waybill_date        date,
    waybill_organization int references organization (inn)
);

create table waybill_position
(
    id         int primary key,
    goods_name int references goods (inner_code) ,
    price      float,
    amount     integer,
    waybill_id int not null references waybill(id)
);
