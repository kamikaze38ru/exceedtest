create table EXCEEDTEST_PLANIN (
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
    ORDER_NUMBER varchar(50),
    PLANNED_ARRIVAL_DATE timestamp,
    VEHICLE_NUMBER varchar(20),
    CAPACITY varchar(50),
    FULL_NAME varchar(100),
    PHONE varchar(20),
    STATUS varchar(50),
    STATE varchar(50),
    GATE_NUMBER varchar(5),
    REGISTRATION_DATE timestamp,
    GATE_ASSIGNMENT_DATE timestamp,
    DEPARTURE_APPROVED_DATE timestamp,
    DEPARTURE_DATE timestamp,
    --
    primary key (ID)
);