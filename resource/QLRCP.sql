--1
CREATE TABLE Phim (
    maPhim INT IDENTITY(1,1) PRIMARY KEY,
    tenPhim NVARCHAR(100),
    thoiLuong INT,
    ngayKhoiChieu DATE,
    ngonNgu NVARCHAR(50),
    dinhDang NVARCHAR(20),
    moTa NVARCHAR(255),
    daoDien NVARCHAR(100),
    dienVien NVARCHAR(255),
    gioiHanTuoi INT
);
--2
CREATE TABLE TheLoaiPhim (
    maTheLoai INT IDENTITY(1,1) PRIMARY KEY,
    tenTheLoai NVARCHAR(50)
);
--3
CREATE TABLE PhongChieu (
    maPhong INT IDENTITY(1,1) PRIMARY KEY,
    soLuongGhe INT,
    loaiPhong NVARCHAR(50)
);
--4
CREATE TABLE SuatChieu (
    maSuatChieu INT IDENTITY(1,1) PRIMARY KEY,
    maPhim INT,
    maPhong INT,
    ngayGioChieu DATETIME,
    FOREIGN KEY (maPhim) REFERENCES Phim(maPhim),
    FOREIGN KEY (maPhong) REFERENCES PhongChieu(maPhong)
);
--5
CREATE TABLE KhachHang (
    maKhachHang INT IDENTITY(1,1) PRIMARY KEY,
    hoTen NVARCHAR(100),
    soDienThoai VARCHAR(15),
    email NVARCHAR(100),
    diemTichLuy INT DEFAULT 0
);
--6
CREATE TABLE Ve (
    maVe INT IDENTITY(1,1) PRIMARY KEY,
    maSuatChieu INT,
    maKhachHang INT NULL,
    soGhe VARCHAR(5),
    giaVe DECIMAL(10,2),
    FOREIGN KEY (maSuatChieu) REFERENCES SuatChieu(maSuatChieu),
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang)
);

-- Bảng Nhân viên
CREATE TABLE NhanVien (
    maNhanVien INT IDENTITY(1,1) PRIMARY KEY,
    hoTen NVARCHAR(100),
    soDienThoai VARCHAR(15),
    email NVARCHAR(100),
    chucVu NVARCHAR(50),
    luong DECIMAL(10,2)
);

CREATE TABLE HoaDon (
    maHoaDon INT IDENTITY(1,1) PRIMARY KEY,
    maNhanVien INT,
    maKhachHang INT,
    ngayLap DATE,
    tongTien DECIMAL(10,2),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien),
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang)
);

-- Bảng Quầy (nơi bán vé)
CREATE TABLE Quay (
    maQuay INT IDENTITY(1,1) PRIMARY KEY,
    viTri NVARCHAR(100),
    moTa NVARCHAR(255)
);

-- Bảng Chi tiết hóa đơn
CREATE TABLE ChiTietHoaDon (
    maHoaDon INT,
    maVe INT,
    soLuong INT,
    thanhTien DECIMAL(10,2),
    PRIMARY KEY (maHoaDon, maVe),
    FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
    FOREIGN KEY (maVe) REFERENCES Ve(maVe)
);

CREATE TABLE DatVe (
    maDatVe INT IDENTITY(1,1) PRIMARY KEY,
    maKhachHang INT,
    maSuatChieu INT,
    maVe INT NULL,
    soLuong INT,
    tongTien DECIMAL(10,2),
    ngayDat DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang),
    FOREIGN KEY (maSuatChieu) REFERENCES SuatChieu(maSuatChieu),
    FOREIGN KEY (maVe) REFERENCES Ve(maVe)
);

CREATE TABLE TaiKhoan (
    tenDangNhap VARCHAR(50) PRIMARY KEY,
    matKhau VARCHAR(255),
    loaiTaiKhoan NVARCHAR(10) CHECK (loaiTaiKhoan IN ('admin', 'user'))
);

-- Chèn dữ liệu vào bảng TheLoaiPhim
INSERT INTO TheLoaiPhim (tenTheLoai) VALUES 
(N'Hành động'),
(N'Kinh dị'),
(N'Hài'),
(N'Tình cảm'),
(N'Viễn tưởng');

-- Chèn dữ liệu vào bảng Phim
INSERT INTO Phim (tenPhim, thoiLuong, ngayKhoiChieu, ngonNgu, dinhDang, moTa, daoDien, dienVien, gioiHanTuoi) VALUES 
(N'Avengers: Endgame', 181, '2019-04-26', N'Anh', N'IMAX', N'Cuộc chiến cuối cùng của Avengers', N'Anthony Russo', N'Robert Downey Jr.', 13),
(N'Titanic', 195, '1997-12-19', N'Anh', N'2D', N'Chuyện tình trên con tàu định mệnh', N'James Cameron', N'Leonardo DiCaprio', 13),
(N'Joker', 122, '2019-10-04', N'Anh', N'4DX', N'Nguồn gốc nhân vật phản diện Joker', N'Todd Phillips', N'Joaquin Phoenix', 18),
(N'Parasite', 132, '2019-05-30', N'Hàn Quốc', N'2D', N'Cuộc sống hai tầng lớp', N'Bong Joon-ho', N'Song Kang-ho', 16),
(N'Inception', 148, '2010-07-16', N'Anh', N'IMAX', N'Xâm nhập giấc mơ', N'Christopher Nolan', N'Leonardo DiCaprio', 13);

-- Chèn dữ liệu vào bảng PhongChieu
INSERT INTO PhongChieu (soLuongGhe, loaiPhong) VALUES 
(100, N'VIP'),
(150, N'IMAX'),
(80, N'Thường'),
(120, N'4DX'),
(90, N'3D');

-- Chèn dữ liệu vào bảng SuatChieu
INSERT INTO SuatChieu (maPhim, maPhong, ngayGioChieu) VALUES 
(1, 1, '2025-04-01 10:00:00'),
(2, 2, '2025-04-02 14:00:00'),
(3, 3, '2025-04-03 18:00:00'),
(4, 4, '2025-04-04 20:00:00'),
(5, 5, '2025-04-05 22:00:00');

-- Chèn dữ liệu vào bảng KhachHang
INSERT INTO KhachHang (hoTen, soDienThoai, email, diemTichLuy) VALUES 
(N'Nguyễn Văn A', '0987654321', 'a@gmail.com', 50),
(N'Trần Thị B', '0912345678', 'b@gmail.com', 30),
(N'Lê Văn C', '0909876543', 'c@gmail.com', 20),
(N'Hoàng Thị D', '0934567890', 'd@gmail.com', 10),
(N'Phạm Văn E', '0976543210', 'e@gmail.com', 5);

-- Chèn dữ liệu vào bảng Ve
INSERT INTO Ve (maSuatChieu, maKhachHang, soGhe, giaVe) VALUES 
(1, 1, 'A1', 100000),
(2, 2, 'B2', 120000),
(3, 3, 'C3', 90000),
(4, 4, 'D4', 150000),
(5, 5, 'E5', 130000);

-- Chèn dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (hoTen, soDienThoai, email, chucVu, luong) VALUES 
(N'Nguyễn Văn F', '0911223344', 'f@gmail.com', N'Quản lý', 20000000),
(N'Trần Thị G', '0922334455', 'g@gmail.com', N'Nhân viên bán vé', 7000000),
(N'Lê Văn H', '0933445566', 'h@gmail.com', N'Nhân viên kỹ thuật', 8000000),
(N'Hoàng Thị I', '0944556677', 'i@gmail.com', N'Nhân viên bảo trì', 6000000),
(N'Phạm Văn J', '0955667788', 'j@gmail.com', N'Nhân viên kiểm soát', 7500000);

-- Chèn dữ liệu vào bảng HoaDon
INSERT INTO HoaDon (maNhanVien, maKhachHang, ngayLap, tongTien) VALUES 
(1, 1, '2025-03-20', 100000),
(2, 2, '2025-03-21', 120000),
(3, 3, '2025-03-22', 90000),
(4, 4, '2025-03-23', 150000),
(5, 5, '2025-03-24', 130000);

-- Chèn dữ liệu vào bảng Quay
INSERT INTO Quay (viTri, moTa) VALUES 
(N'Lầu 1', N'Quầy vé chính'),
(N'Tầng trệt', N'Quầy vé nhanh'),
(N'Lầu 2', N'Quầy vé VIP'),
(N'Lầu 3', N'Quầy combo bắp nước'),
(N'Rạp IMAX', N'Quầy vé IMAX');

-- Chèn dữ liệu vào bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maHoaDon, maVe, soLuong, thanhTien) VALUES 
(1, 1, 1, 100000),
(2, 2, 1, 120000),
(3, 3, 1, 90000),
(4, 4, 1, 150000),
(5, 5, 1, 130000);

-- Chèn dữ liệu vào bảng DatVe
INSERT INTO DatVe (maKhachHang, maSuatChieu, maVe, soLuong, tongTien, ngayDat) VALUES 
(1, 1, 1, 1, 100000, '2025-03-15 10:00:00'),
(2, 2, 2, 1, 120000, '2025-03-16 11:00:00'),
(3, 3, 3, 1, 90000, '2025-03-17 12:00:00'),
(4, 4, 4, 1, 150000, '2025-03-18 13:00:00'),
(5, 5, 5, 1, 130000, '2025-03-19 14:00:00');

-- Chèn dữ liệu vào bảng TaiKhoan
INSERT INTO TaiKhoan (tenDangNhap, matKhau, loaiTaiKhoan) VALUES 
('admin1', 'adminpass1', 'admin'),
('admin2', 'adminpass2', 'admin'),
('user1', 'userpass1', 'user'),
('user2', 'userpass2', 'user'),
('user3', 'userpass3', 'user');
