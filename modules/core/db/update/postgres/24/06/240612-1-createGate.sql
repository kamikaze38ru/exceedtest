create table EXCEEDTEST_GATE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    GATE_NUMBER varchar(5) not null,
    WORKING_TIME_FROM time not null,
    WORKING_TIME_TO time not null,
    --
    primary key (ID)
);