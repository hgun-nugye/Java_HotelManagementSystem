create database hotelmanagementsystem;
use hotelmanagementsystem;
create table login (username varchar(25), password varchar(25));
insert into login values("reception", "1234");

create table administrator (username varchar(25), password varchar(25));
insert into administrator values("admin", "1234");
select * from login;

CREATE TABLE NhanVien(
	CCCD VARCHAR(50) primary key,
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
	SoPhong NVARCHAR(20) NOT NULL primary key,
    SoGiuong INT NOT NULL,
	TienNghi NVARCHAR(100) NOT NULL,
	TrangThai NVARCHAR(50) NOT NULL,
	GiaMacDinh INT NOT NULL
 );
                   
INSERT INTO Phong (SoPhong, SoGiuong, TienNghi, TrangThai, GiaMacDinh)
VALUES
    ('101', 2, N'Điều hòa, Wifi, Tivi', N'Đã đặt', 500000),
    ('102', 1, N'Điều hòa, Wifi', N'Trống', 300000),
    ('103', 3, N'Điều hòa, Wifi, Tivi, Tủ lạnh', N'Trống', 700000),
    ('104', 2, N'Wifi, Tivi', N'Đã đặt', 600000),
    ('105', 1, N'Điều hòa', N'Trống', 400000),
    ('106', 2, N'Điều hòa, Wifi', N'Trống', 450000),
    ('107', 3, N'Điều hòa, Wifi, Tivi', N'Đã đặt', 750000),
    ('108', 1, N'Wifi', N'Đã đặt', 350000),
    ('109', 2, N'Điều hòa, Tủ lạnh', N'Đã đặt', 550000),
    ('110', 1, N'Điều hòa, Wifi, Tivi', N'Trống', 600000);
    
select * from Phong where TrangThai=N'Trống';

CREATE TABLE KhachHang(
	CCCD VARCHAR(50) NOT NULL primary key,
	HoTen NVARCHAR(100) NOT NULL,
	GioiTinh NVARCHAR(10) NOT NULL,
	QuocTich NVARCHAR(50),
	Email_KH VARCHAR(100),
    SDT_KH VARCHAR(15), 
    DuaTruoc INT  default (0)
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

 CREATE TABLE HoaDon(
	MaHD VARCHAR(10) NOT NULL,
	CCCD VARCHAR(50) NOT NULL ,
	SoPhong NVARCHAR(20) NOT NULL,
	NgayNhan DATETIME,
	NgayTra DATETIME,
	TrangThai NVARCHAR(50) NOT NULL DEFAULT N'Chưa thanh toán'
);
ALTER TABLE HoaDon
ADD CONSTRAINT FK_HoaDon_KhachHang 
FOREIGN KEY (CCCD) REFERENCES KhachHang(CCCD) ON DELETE CASCADE;

ALTER TABLE HoaDon
ADD CONSTRAINT FK_HoaDon_Phong 
FOREIGN KEY (SoPhong) REFERENCES Phong(SoPhong) ON DELETE CASCADE;

INSERT INTO HoaDon (MaHD, CCCD, SoPhong, NgayNhan, NgayTra, TrangThai)
VALUES
    ('HD001', '123456789012', '101', '2025-05-01 14:00:00', '2025-05-03 12:00:00', N'Chưa thanh toán'),
    ('HD002', '234567890123', '102', '2025-05-02 15:00:00', '2025-05-04 11:00:00', N'Đã thanh toán'),
    ('HD003', '345678901234', '103', '2025-05-03 13:30:00', '2025-05-05 10:30:00', N'Chưa thanh toán'),
    ('HD004', '456789012345', '104', '2025-05-04 16:00:00', '2025-05-06 12:00:00', N'Đã thanh toán'),
    ('HD005', '567890123456', '105', '2025-05-05 14:30:00', '2025-05-07 11:30:00', N'Chưa thanh toán'),
    ('HD006', '678901234567', '106', '2025-05-06 17:00:00', '2025-05-08 12:30:00', N'Đã thanh toán'),
    ('HD007', '789012345678', '107', '2025-05-07 15:00:00', '2025-05-09 10:00:00', N'Chưa thanh toán'),
    ('HD008', '890123456789', '108', '2025-05-08 14:00:00', '2025-05-10 12:00:00', N'Đã thanh toán'),
    ('HD009', '901234567890', '109', '2025-05-09 16:30:00', '2025-05-11 11:00:00', N'Chưa thanh toán'),
    ('HD010', '012345678901', '110', '2025-05-10 13:00:00', '2025-05-12 10:30:00', N'Đã thanh toán');
    
SELECT * FROM HoaDon;

-- hàm tạo MaHD tự động
DELIMITER //

CREATE FUNCTION GenerateMaHD() RETURNS VARCHAR(10) 
DETERMINISTIC
BEGIN
    DECLARE new_id INT;
    DECLARE new_mahd VARCHAR(10);
    
    -- Lấy số lượng hóa đơn hiện có
    SELECT COUNT(*) + 1 INTO new_id FROM HoaDon;
    
    -- Tạo mã hóa đơn mới bằng cách ghép tiền tố 'HD' với số lượng hóa đơn
    SET new_mahd = CONCAT('HD', LPAD(new_id, 3, '0'));
    
    RETURN new_mahd;
END //

DELIMITER ;

