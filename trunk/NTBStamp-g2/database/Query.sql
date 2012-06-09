Create database dbNTB

go

use dbNTB

go

Create table Accounts
(
	AccountId int identity primary key,
 	AccountName nvarchar(100),
	Username nvarchar(200),
	[Password] nvarchar(200),
	Email nvarchar(100),
	Sex nvarchar(50),
	[Phone] nvarchar(15),
	[Address] nvarchar(500),
	Accountrole nvarchar(100),
	[Status] bit
)

go

--Insert into Accounts values('Truong','admin','123','iubaxanhatnha@yahoo.com','male','0983379149','Ha Noi','Admin',1)

go

Create table ChargeType
(
	ChargeId int identity primary key,
	Charge nvarchar(100)
)

go

Create table LandTypes(
	LandTypeID int identity primary key,
	LandName nvarchar(200)
)

go

Create table Lands
(
	LandId int identity primary key,
	LandName nvarchar(200),
	LandTypeID int foreign key references LandTypes(LandTypeID),
	[Address] nvarchar(500),
	NearBy nvarchar(500),
	[Image] nvarchar(1000),
	[Date] datetime,
	Price money,
	[Description] nvarchar(max),
	[Status] bit
)
go
select * from Lands
go

Create table Documents
(
	DocID int primary key references Lands(LandId),
	Title nvarchar(200),
	[Image] nvarchar(1000),
	[Description] nvarchar(500),
	PermitDate datetime,
	[Signature] nvarchar(max),
	ChargeId int foreign key references ChargeType(ChargeId),
	[Status] bit
)

go

Create table Buildings
(
	BuildingId int identity primary key,
	BuildingName nvarchar(200),
	BuildingType nvarchar(200),
	TotalFloors int,
	TotalRooms int,
	[Image] nvarchar(1000),
	[Date] datetime,
	Price money,
	LandId int foreign key references Lands(LandId),
	[Description] nvarchar(max),
	[Status] bit
)

go

Create table RoomTypes
(
	TypeId int identity primary key,
	TypeName nvarchar(500),
	Price money
)

go

Create table Rooms
(
	RoomId int identity primary key,
	RoomNo int,
	TotalSquare int,
	TotalPrice money,
	[Image] nvarchar(1000),
	TypeId int foreign key references RoomTypes(TypeId),
	BuildingId int foreign key references Buildings(BuildingId),
	[Description] nvarchar(max),
	[Status] bit
)

go

Create table Customers
(
	CustomerId int identity primary key,
	CustomerName nvarchar(200),
	Sex nvarchar(50),
	[Address] nvarchar(500),
	[Phone] nvarchar(15),
	Email nvarchar(100),
	[Status] bit
)

go

Create table PaymentCharge
(
	PayChargeID int identity primary key,
	Chargename nvarchar(200),
	Interest float,
	PayChargeTime int
)

go

Create table Payments
(
	PaymentID int identity primary key,
	RoomID int foreign key references Rooms(RoomId),
	CustomerID int foreign key references Customers(CustomerId),
	Payment float,
	Paydate datetime,
	PayChargeID int foreign key references PaymentCharge(PayChargeID),
	[Status] int
)




