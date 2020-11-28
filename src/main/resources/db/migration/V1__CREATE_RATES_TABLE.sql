create sequence price_list_id_seq start with 1 increment by 1;
create table prices (BRAND_ID int8 not null, START_DATE timestamp, END_DATE timestamp, PRICE_LIST int8 not null DEFAULT (NEXT VALUE FOR price_list_id_seq), PRODUCT_ID int8 not null, PRIORITY int8 not null, PRICE float8 not null, CURRENCY varchar(20) not null, primary key (PRICE_LIST));
