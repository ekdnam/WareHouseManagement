create database WAREHOUSE;
use WAREHOUSE;

create table User (
  id int not null auto_increment primary key,
  username varchar (20),
  password varchar (20)
);
create table AdminItem(
  id int not null auto_increment primary key,
  name varchar(30),
  costp int,
  sellp int,
  qty int,
  type varchar(20)
);
create table ShopItem(
  shop_id int,
  id int,
  name varchar(30),
  costp int,
  sellp int,
  qty int,
  type varchar(20),
  foreign key(id) references AdminItem(id) on delete cascade,
  foreign key(shop_id) references User(id) on delete cascade
);
create table Bills(
  bill_no int not null auto_increment primary key,
  shop_id int,
  bill_date date,
  total int,
  foreign key(shop_id) references User(id) on delete cascade
);
create table BillItem(
  bill_no int,
  item_no int,
  costp int,
  qty int,
  foreign key(bill_no) references Bills(bill_no) on delete cascade
);
create table Requests(
  req_no int not null auto_increment primary key,
  shop_id int,
  item_name varchar(30),
  qty int,
  shopname varchar(30),
  foreign key(shop_id) references User(id) on delete cascade
); 

select * from AdminItem;
select * from ShopItem;
select * from User;
select * from Requests;
select * from BillItem;
select * from Bills;