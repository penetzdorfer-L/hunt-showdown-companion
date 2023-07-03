create table if not exists weapons(
                      id int not null generated by default as identity ,
                      weaponid varchar(100) not null,
                      name varchar(100) not null,
                      slots int not null,
                      bloodline_rank int not null,
                      ammo_slots int not null,
                      dualwieldable boolean not null,
                      price int not null,
                      PRIMARY KEY ( id )
);

create table if not exists tools(
                        id int not null generated by default as identity ,
                        toolid varchar(100) not null,
                        name varchar(100) not null,
                        bloodline_rank int not null,
                        price int not null,
                        type varchar(100) not null,
                        PRIMARY KEY ( id )
);

create table if not exists consumables(
                      id int not null generated by default as identity ,
                      consumableid varchar(100) not null,
                      name varchar(100) not null,
                      bloodline_rank int not null,
                      price int not null,
                      type varchar(100) not null,
                      PRIMARY KEY ( id )
);

create table if not exists ammunitions(
                            id int not null generated by default as identity ,
                            ammoid varchar(100) not null,
                            name varchar(100) not null,
                            type_of_ammo varchar(100) not null,
                            price int not null,
                            PRIMARY KEY ( id )
);

create table if not exists weapons_ammunition_connection(
    weapons_weaponid varchar(100) not null,
    ammunitions_ammoid varchar(100) not null
);