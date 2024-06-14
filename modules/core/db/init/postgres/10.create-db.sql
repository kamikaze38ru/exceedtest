-- begin EXCEEDTEST_PLANIN
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
    STATE integer,
    ORDER_NUMBER varchar(50),
    PLANNED_ARRIVAL_DATE timestamp,
    VEHICLE_NUMBER varchar(20),
    CAPACITY varchar(50),
    FULL_NAME varchar(100),
    PHONE varchar(20),
    STATUS integer,
    GATE_NUMBER varchar(5),
    REGISTRATION_DATE timestamp,
    GATE_ASSIGNMENT_DATE timestamp,
    DEPARTURE_APPROVED_DATE timestamp,
    DEPARTURE_DATE timestamp,
    --
    primary key (ID)
)^
-- end EXCEEDTEST_PLANIN
-- begin EXCEEDTEST_GATE
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
    WORKING_TIME_FROM timestamp not null,
    WORKING_TIME_TO timestamp not null,
    --
    primary key (ID)
)^
-- end EXCEEDTEST_GATE
-- begin EXCEEDTEST_CLIENT
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
)^
-- end EXCEEDTEST_CLIENT
-- begin SAMPLE_PLANIN_CLIENT_LINK
create table SAMPLE_PLANIN_CLIENT_LINK (
    PLANIN_ID uuid,
    CLIENT_ID uuid,
    primary key (PLANIN_ID, CLIENT_ID)
)^
-- end SAMPLE_PLANIN_CLIENT_LINK
-- begin EXCEETEST_APP_SETTING
create table EXCEETEST_APP_SETTING (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    VALUE_ varchar(255) not null,
    --
    primary key (ID)
)^
-- end EXCEETEST_APP_SETTING
-- begin PLANINLOG
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
)^
-- end PLANINLOG
