use zoined;

create table sales
(
    id       char(36) ,
    store    varchar(256)  not null,
    category varchar(256)  not null,
    product  varchar(256)  not null,
    amount   decimal  not null,
    currency char(3)  not null,
    date     datetime not null,
        primary key (id)
);