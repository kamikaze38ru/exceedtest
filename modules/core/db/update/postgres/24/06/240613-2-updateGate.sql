alter table EXCEEDTEST_GATE rename column working_time_to to working_time_to__u59296 ;
alter table EXCEEDTEST_GATE alter column working_time_to__u59296 drop not null ;
alter table EXCEEDTEST_GATE rename column working_time_from to working_time_from__u00618 ;
alter table EXCEEDTEST_GATE alter column working_time_from__u00618 drop not null ;
alter table EXCEEDTEST_GATE add column WORKING_TIME_FROM timestamp ^
update EXCEEDTEST_GATE set WORKING_TIME_FROM = current_timestamp where WORKING_TIME_FROM is null ;
alter table EXCEEDTEST_GATE alter column WORKING_TIME_FROM set not null ;
alter table EXCEEDTEST_GATE add column WORKING_TIME_TO timestamp ^
update EXCEEDTEST_GATE set WORKING_TIME_TO = current_timestamp where WORKING_TIME_TO is null ;
alter table EXCEEDTEST_GATE alter column WORKING_TIME_TO set not null ;
