create database hotelmanagementsystem;
use hotelmanagementsystem;
create table login (username varchar(25), password varchar(25));
insert into login values("admin", "1234");
select * from login;

CREATE TABLE NhanVien(
	CCCD VARCHAR(50) ,
	HoTen NVARCHAR(100),
	Tuoi INT,
	GioiTinh NVARCHAR(10),
	ChucVu NVARCHAR(50),
    Luong INT,
	SDT_NV VARCHAR(15),
	Email_NV VARCHAR(100)
 );

INSERT INTO NhanVien (CCCD, HoTen, Tuoi, GioiTinh, ChucVu, SDT_NV, Email_NV, Luong)
VALUES
    ('112233445566', N'Nguyễn Ngọc An', 25, N'Nam', N'Quản lý', '0911223344', 'nguyenngocan@hotel.com', 15000000),
    ('223344556677', N'Trần Văn Bảo', 23, N'Nam', N'Lễ tân', '0922334455', 'tranvanbao@hotel.com', 12000000),
    ('334455667788', N'Lê Thị Cẩm', 25, N'Nữ', N'Lễ tân', '0933445566', 'lethicam@hotel.com', 12000000),
    ('445566778899', N'Phạm Đức Dũng', 27, N'Nam', N'Buồng phòng', '0944556677', 'phamducdung@hotel.com', 10000000),
    ('556677889900', N'Vũ Thị Em', 27, N'Nữ', N'Buồng phòng', '0955667788', 'vuthiem@hotel.com', 10000000),
    ('667788990011', N'Hoàng Văn Phúc', 29, N'Nam', N'Bếp', '0966778899', 'hoangvanphuc@hotel.com', 18000000),
    ('778899001122', N'Nguyễn Thị Hương', 27, N'Nữ', N'Bếp', '0977889900', 'nguyenthihuong@hotel.com', 18000000),
    ('889900112233', N'Trần Minh Khôi', 35, N'Nam', N'Bảo vệ', '0988990011', 'tranminhkhoi@hotel.com', 9000000),
    ('990011223344', N'Lý Thị Mai', 24, N'Nữ', N'Kế toán', '0999001122', 'lythimai@hotel.com', 15000000),
    ('001122334455', N'Đặng Văn Nam', 40, N'Nam', N'Quản lý', '0900112233', 'dangvannam@hotel.com', 20000000);
select * from NhanVien;

 CREATE TABLE Phong(
	SoPhong NVARCHAR(20) NOT NULL,
    SoGiuong INT NOT NULL,
	TienNghi NVARCHAR(100) NOT NULL,
	TrangThai NVARCHAR(50) NOT NULL,
	GiaMacDinh DECIMAL(10, 2) NOT NULL
 );

INSERT INTO Phong (SoPhong, SoGiuong, TienNghi, TrangThai, GiaMacDinh)
VALUES
    ('P101', 2, N'Điều hòa, Wifi, Tivi', N'Trống', 500000.00),
    ('P102', 1, N'Điều hòa, Wifi', N'Trống', 300000.00),
    ('P103', 3, N'Điều hòa, Wifi, Tivi, Tủ lạnh', N'Trống', 700000.00),
    ('P104', 2, N'Wifi, Tivi', N'Đã đặt', 600000.00),
    ('P105', 1, N'Điều hòa', N'Trống', 400000.00),
    ('P106', 2, N'Điều hòa, Wifi', N'Trống', 450000.00),
    ('P107', 3, N'Điều hòa, Wifi, Tivi', N'Trống', 750000.00),
    ('P108', 1, N'Wifi', N'Đã đặt', 350000.00),
    ('P109', 2, N'Điều hòa, Tủ lạnh', N'Trống', 550000.00),
    ('P110', 1, N'Điều hòa, Wifi, Tivi', N'Trống', 600000.00);
    
select * from Phong;

CREATE TABLE KhachHang(
	CCCD VARCHAR(50) NOT NULL,
	HoTen NVARCHAR(100) NOT NULL,
	GioiTinh NVARCHAR(10) NOT NULL,
	QuocTich NVARCHAR(50),
	Email_KH VARCHAR(100),
    SDT_KH VARCHAR(15), 
    DuaTruoc DECIMAL(10, 2) 
 );
INSERT INTO KhachHang (CCCD, SDT_KH, GioiTinh, HoTen, QuocTich, Email_KH)
VALUES
	('123456789012', '0912345678', N'Nam', N'Nguyễn Văn An',N'Việt Nam', 'nguyenvanan@gmail.com'),
	('234567890123', '0923456789', N'Nữ', N'Trần Thị Bình',N'Việt Nam', 'tranthibinh@gmail.com'),
	('345678901234', '0934567890', N'Nam', N'Lê Hoàng Cường',  N'Việt Nam', 'lehoangcuong@gmail.com'),
	('456789012345', '0945678901', N'Nữ', N'Phạm Ngọc Diệp',  N'Việt Nam', 'phamngocdiep@gmail.com'),
	('567890123456', '0956789012', N'Nam', N'Vũ Minh Đức',N'Việt Nam', 'vuminhduc@gmail.com'),
	('678901234567', '0967890123', N'Nữ', N'Hoàng Thị Lan',  N'Việt Nam', 'hoangthilan@gmail.com'),
	('789012345678', '0978901234', N'Nam', N'Nguyễn Tuấn Hùng', N'Việt Nam', 'nguyentuanhung@gmail.com'),
	('890123456789', '0989012345', N'Nữ', N'Lý Thị Mai', N'Việt Nam', 'lythimai@gmail.com'),
	('901234567890', '0990123456', N'Nam', N'Trương Văn Phúc',  N'Việt Nam', 'truongvanphuc@gmail.com'),
	('012345678901', '0901234567', N'Nữ', N'Đặng Kim Ngân',N'Việt Nam', 'dangkimngan@gmail.com');
select * from KhachHang;

 CREATE TABLE HoaDon(
	CCCD VARCHAR(50) NOT NULL,
	SoPhong NVARCHAR(20),
	NgayNhan DATETIME,
	NgayTra DATETIME,
	TrangThai NVARCHAR(50) NOT NULL DEFAULT N'Chưa thanh toán'
);

INSERT INTO HoaDon (CCCD, SoPhong, NgayNhan, NgayTra, TrangThai)
VALUES
    ('123456789012', 'P101', '2025-05-01', '2025-05-05', N'Đã thanh toán'),
    ('234567890123', 'P102', '2025-05-02', '2025-05-03', N'Đã thanh toán'),
    ('345678901234', 'P103', '2025-05-01', '2025-05-04', N'Chưa thanh toán'),
    ('456789012345', 'P104', '2025-05-02', '2025-05-06', N'Đã thanh toán'),
    ('567890123456', 'P105', '2025-05-01', '2025-05-07', N'Chưa thanh toán'),
    ('678901234567', 'P106', '2025-05-03', '2025-05-06', N'Đã thanh toán'),
    ('789012345678', 'P107', '2025-05-04', '2025-05-08', N'Chưa thanh toán'),
    ('890123456789', 'P108', '2025-05-02', '2025-05-05', N'Đã thanh toán'),
    ('901234567890', 'P109', '2025-05-01', '2025-05-09', N'Chưa thanh toán'),
    ('012345678901', 'P110', '2025-05-03', '2025-05-07', N'Đã thanh toán');

SELECT * FROM HoaDon;
