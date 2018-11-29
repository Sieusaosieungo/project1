create database qltv;
use qltv;
create table infor_book (
idBook 			varchar(6) primary key not null,
Book_name		nvarchar(50) not null,
Author			nvarchar(50) not null,
Publisher		nvarchar(50) not null,
Kind			nvarchar(50) not null,
Cost			Int	not null,
Number_books	Int not null
)engine InnoDB;

create table customer (
idcustomer		varchar	(6) primary key not null,
name			nvarchar(100) not null,
Sex				nvarchar(10) not null,
Address			nvarchar(100) not null,
Phone_number	Int(15) not null,
ID				Int(15) not null
) engine InnoDB;

create table employee (
idEmployee		varchar(6) primary key not null,
Name			nvarchar(50) not null,
BOD				Date not null,	
Address			nvarchar(100) not null ,
Sex				nvarchar(10) not null,
Email			varchar(100) not null,
Phone_number	Int(15) not null
)engine InnoDB;

create table loan_payment (
idloan			varchar	(6) primary key not null ,
idcustomer		varchar	(6) not null,
idEmployee		varchar	(6) not null,
foreign key (idcustomer) references customer(idcustomer),
foreign key (idEmployee) references employee(idEmployee),
Borrow_day		datetime not null,
Deposits		int not null,
Expire_day		Date not null,
Note			Text(255) not null

)engine InnoDB;


create table loan_payment_detail(
idDetail 		varchar(6) primary key not null,
idloan			varchar(6) not null,
Book_name		nvarchar(50) not null, 
idbook			varchar(6) not null,
foreign key (idloan) references loan_payment(idloan),
foreign key (idbook) references infor_book(idbook),
Pay_day			Date not null,	
Mulct			int not null,
quantity		int not null
)engine InnoDB;

SELECT * FROM qltv.infor_book;
INSERT INTO `qltv`.`infor_book` (`idBook`, `Book_name`, `Author`, `Publisher`, `Kind`, `Cost`, `Number_books`) VALUES ('B0001', 'Tuổi trẻ đáng giá bao nhiêu?', 'Rosie Nguyễn', 'NXB Hội Nhà Văn', 'Phiêu lưu', '70000', '50');
INSERT INTO `qltv`.`infor_book` (`idBook`, `Book_name`, `Author`, `Publisher`, `Kind`, `Cost`, `Number_books`) VALUES ('B0002', 'Cho tôi xin một vé đi tuổi thơ', 'Nguyễn Nhật Ánh', 'NXB Trẻ', 'Phiêu lưu, tình cảm', '63000', '40');
INSERT INTO `qltv`.`infor_book` (`idBook`, `Book_name`, `Author`, `Publisher`, `Kind`, `Cost`, `Number_books`) VALUES ('B0003', 'Tôi tài giỏi, bạn cũng thế!', 'Adam Khoo', 'NXB Phụ Nữ', 'Giáo dục', '110000', '60');
INSERT INTO `qltv`.`infor_book` (`idBook`, `Book_name`, `Author`, `Publisher`, `Kind`, `Cost`, `Number_books`) VALUES ('B0004', 'Harry Potter và Hòn đá phù thuỷ', 'J.K.Rowling', 'NXB Trẻ', 'Giả tưởng, Phiêu lưu, Tình cảm', '135000', '20');
INSERT INTO `qltv`.`infor_book` (`idBook`, `Book_name`, `Author`, `Publisher`, `Kind`, `Cost`, `Number_books`) VALUES ('B0005', 'Quá trẻ để chết: Hành trình nước Mỹ', 'Đinh Hằng', 'NXB Hội Nhà Văn', 'Phiêu lưu, Khám phá', '65000', '29');

SELECT * FROM qltv.employee;
INSERT INTO `qltv`.`employee` (`idEmployee`, `Name`, `BOD`, `Address`, `Sex`, `Email`, `Phone_number`) VALUES ('E0001', 'Phạm Hải Anh', '1994-02-05', 'Hà Nội', 'Nam', 'anh94@gmail.com', '0349582957');
INSERT INTO `qltv`.`employee` (`idEmployee`, `Name`, `BOD`, `Address`, `Sex`, `Email`, `Phone_number`) VALUES ('E0002', 'Phạm Văn Đức', '1994-02-05', 'Hà Nội', 'Nam', 'ducpv@gmail.com','09340130435' );
INSERT INTO `qltv`.`employee` (`idEmployee`, `Name`, `BOD`, `Address`, `Sex`, `Email`, `Phone_number`) VALUES ('E0003', 'Trần Thiên Đức', '1994-05-02', 'Hà Nội', 'Nam','duc05@gmail.com', '0946036823');
INSERT INTO `qltv`.`employee` (`idEmployee`, `Name`, `BOD`, `Address`, `Sex`, `Email`, `Phone_number`) VALUES ('E0004', 'Nguyễn Thị Oanh', '1992-08-08', 'Hà Nội', 'Nữ', 'oanhnt92@gmail.com','0947068933' );
INSERT INTO `qltv`.`employee` (`idEmployee`, `Name`, `BOD`, `Address`, `Sex`, `Email`, `Phone_number`) VALUES ('E0005', 'Hoàng Minh Sơn', '1990-04-12', 'Hà Nội', 'Nam', 'paint90@gmail.com', '0943055687');
INSERT INTO `qltv`.`employee` (`idEmployee`, `Name`, `BOD`, `Address`, `Sex`, `Email`, `Phone_number`) VALUES ('E0006', 'Nguyễn Hải Hậu', '1993-02-19', 'Hà Nội', 'Nữ','haucute93@gmail.com','0948346713' );

SELECT *FROM qltv.customer;
INSERT INTO `qltv`.`customer` (`idcustomer`, `name`, `Sex`, `Address`, `Phone_number`, `ID`) VALUES ('C0001', 'Nguyễn Thành Trung', 'Nam', 'Hà Nội', '0948593058', '0405069389');
INSERT INTO `qltv`.`customer` (`idcustomer`, `name`, `Sex`, `Address`, `Phone_number`, `ID`) VALUES ('C0002', 'Trần Văn Thái', 'Nam', 'Hà Nội', '0964937598', '020506049');
INSERT INTO `qltv`.`customer` (`idcustomer`, `name`, `Sex`, `Address`, `Phone_number`, `ID`) VALUES ('C0003', 'Nguyễn Thuỳ Linh', 'Nữ', 'Thanh Hoá', '0965938586', '060483948');
INSERT INTO `qltv`.`customer` (`idcustomer`, `name`, `Sex`, `Address`, `Phone_number`, `ID`) VALUES ('C0004', 'Ngô Hải Yến', 'Nữ', 'Bắc Ninh', '0956892458', '1485939509');
INSERT INTO `qltv`.`customer` (`idcustomer`, `name`, `Sex`, `Address`, `Phone_number`, `ID`) VALUES ('C0005', 'Vũ Văn Thanh', 'Nam', 'Hưng Yên', '0946982485', '040826489');


SELECT * FROM qltv.loan_payment;
INSERT INTO `qltv`.`loan_payment` (`idloan`, `idcustomer`, `idEmployee`, `Borrow_day`, `Deposits`, `Expire_day`, `Note`) VALUES ('L001', 'C0001', 'E0001', '2018-10-21 15:00:23', '50000', '2018-12-21', 'Mượn');
INSERT INTO `qltv`.`loan_payment` (`idloan`, `idcustomer`, `idEmployee`, `Borrow_day`, `Deposits`, `Expire_day`, `Note`) VALUES ('L002', 'C0002', 'E0001', '2018-10-21 16:30:56', '60000', '2018-12-21', 'Mượn');
INSERT INTO `qltv`.`loan_payment` (`idloan`, `idcustomer`, `idEmployee`, `Borrow_day`, `Deposits`, `Expire_day`, `Note`) VALUES ('L003', 'C0003', 'E0002', '2018-10-21 16:31:23', '30000', '2018-12-21', 'Mượn');
INSERT INTO `qltv`.`loan_payment` (`idloan`, `idcustomer`, `idEmployee`, `Borrow_day`, `Deposits`, `Expire_day`, `Note`) VALUES ('L004', 'C0004', 'E0002', '2018-10-21 17:00:03', '100000', '2018-12-21', 'Mượn');
INSERT INTO `qltv`.`loan_payment` (`idloan`, `idcustomer`, `idEmployee`, `Borrow_day`, `Deposits`, `Expire_day`, `Note`) VALUES ('L005', 'C0005', 'E0003', '2018-10-21 17:05:16', '40000', '2018-12-21', 'Mượn');

SELECT * FROM qltv.loan_payment_detail;
INSERT INTO `qltv`.`loan_payment_detail` (`idDetail`, `idloan`, `Book_name`, `idbook`, `Pay_day`, `Mulct`, `quantity`) VALUES ('DT001', 'L001', 'Tuổi trẻ đáng giá bao nhiêu?', 'B0001', '2018-12-21', '0', '1');
INSERT INTO `qltv`.`loan_payment_detail` (`idDetail`, `idloan`, `Book_name`, `idbook`, `Pay_day`, `Mulct`, `quantity`) VALUES ('DT002', 'L002', 'Cho tôi xin một vé đi tuổi thơ', 'B0002', '2018-12-21', '0', '1');
INSERT INTO `qltv`.`loan_payment_detail` (`idDetail`, `idloan`, `Book_name`, `idbook`, `Pay_day`, `Mulct`, `quantity`) VALUES ('DT003', 'L003', 'Tôi tài giỏi, bạn cũng thế!', 'B0003', '2018-12-21', '15000', '1');
INSERT INTO `qltv`.`loan_payment_detail` (`idDetail`, `idloan`, `Book_name`, `idbook`, `Pay_day`, `Mulct`, `quantity`) VALUES ('DT004', 'L004', 'Harry Potter và Hòn đá phù thuỷ', 'B0004', '2018-12-21', '10000', '1');
INSERT INTO `qltv`.`loan_payment_detail` (`idDetail`, `idloan`, `Book_name`, `idbook`, `Pay_day`, `Mulct`, `quantity`) VALUES ('DT005', 'L005', 'Quá trẻ để chết: Hành trình nước Mỹ', 'B0005', '2018-12-21', '0', '1');



UPDATE `qltv`.`customer` SET `Address` = 'Quảng Ninh' WHERE (`idcustomer` = 'C0005');
UPDATE `qltv`.`customer` SET `ID` = '14859395' WHERE (`idcustomer` = 'C0004');
UPDATE `qltv`.`customer` SET `ID` = '40506938' WHERE (`idcustomer` = 'C0001');
DELETE FROM `qltv`.`customer` WHERE (`idcustomer` = 'C0005');
SELECT * FROM qlkh.customer WHERE (`Address` = 'Hà Nội');

UPDATE `qltv`.`employee` SET `Address` = 'Thái Bình' WHERE (`idEmployee` = 'E0005');
UPDATE `qltv`.`employee` SET `BOD` = '1992-08-09' WHERE (`idEmployee` = 'E0004');
DELETE FROM `qltv`.`employee` WHERE (`idEmployee` = 'E0006');
SELECT * FROM qlkh.employee WHERE (`Address` = 'Hà Nội');

DELETE FROM `qltv`.`infor_book` WHERE (`idBook` = 'B0005');
UPDATE `qltv`.`infor_book` SET `Cost` = '100000' WHERE (`idBook` = 'B0003');
SELECT * FROM qlkh.infor_book WHERE (`Publisher` = 'NXB Trẻ');

UPDATE `qltv`.`loan_payment` SET `Deposits` = '40000' WHERE (`idloan` = 'L001');
DELETE FROM `qltv`.`loan_payment` WHERE (`idloan` = 'L005');
UPDATE `qltv`.`loan_payment` SET `Deposits` = '65000' WHERE (`idloan` = 'L002');
SELECT * FROM qlkh.loan_payment WHERE (`idEmployee` = 'E0001');

update `qltv`.loan_payment set `Borrow_day` = '2018-11-25 7:30:00' where (`idloan` = 'L007');

UPDATE `qltv`.`loan_payment_detail` SET `Mulct` = '20000' WHERE (`idDetail` = 'DT001');
DELETE FROM `qltv`.`loan_payment_detail` WHERE (`idDetail` = 'DT005');
UPDATE `qltv`.`loan_payment_detail` SET `Mulct` = '15000' WHERE (`idDetail` = 'DT004');
SELECT * FROM qlkh.loan_payment_detail WHERE (`Mulct` = '150000');



















