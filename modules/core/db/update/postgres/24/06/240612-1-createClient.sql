create table EXCEEDTEST_CLIENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CLIENT_CODE integer not null,
    NAME varchar(200) not null,
    ADDRESS varchar(200),
    CONTACT_PERSON varchar(100),
    --
    primary key (ID)
);