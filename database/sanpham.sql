drop schema if exists sanpham;
CREATE SCHEMA `sanpham` DEFAULT CHARACTER SET utf8mb4 ;
use sanpham;

drop table if exists sanpham;
create table sanpham(
OrderID Integer primary key auto_increment,
Name varchar(50) not null,
Price decimal(10,2) not null,
Quantity int not null,
Datee datetime not null,
Total decimal(10,2)
);

insert into sanpham(Datee, OrderID, Name, Price, Quantity, Total) values
('2017-09-25 23:06:00', 200392 ,'USB 3.0 Cable' ,10.00, 3,30.00),
('2017-09-24 05:57:00', 200391 ,'Smartwatch 4.0 LTE Wifi' ,199.00 ,6 ,1494.00),
('2017-09-23 05:57:00', 200390 ,'Camera C430W 4k' ,699.00 ,1 ,699.00);