create table PlaninLog (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REGISTRATION_NUMBER varchar(10),
    VEHICLE_NUMBER varchar(20),
    STATUS integer,
    STATE integer,
    CHANGE_DATE timestamp,
    --
    primary key (ID)
);