alter table SAMPLE_PLANIN_CLIENT_LINK add constraint FK_SAMPLACLI_ON_PLANIN foreign key (PLANIN_ID) references EXCEEDTEST_PLANIN(ID);
alter table SAMPLE_PLANIN_CLIENT_LINK add constraint FK_SAMPLACLI_ON_CLIENT foreign key (CLIENT_ID) references EXCEEDTEST_CLIENT(ID);