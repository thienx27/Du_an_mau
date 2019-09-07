if OBJECT_ID('HocVien') is not null
drop table HocVien
go
if OBJECT_ID('KhoaHoc') is not null
drop table KhoaHoc
go
CREATE TABLE KhoaHoc(
MaKH INT IDENTITY(1,1) NOT NULL,
TenKH NVARCHAR(50) NULL
Constraint PK_KhoaHoc PRIMARY KEY(MaKH)
)
go
CREATE TABLE HocVien(
MaHV INT IDENTITY(1,1) NOT NULL,
MaKH int NOT NULL,
HoTen NVARCHAR(30) NULL,
GioiTinh BIT NULL,
NgaySinh DATE NULL,
SDT VARCHAR(11) NULL,
Email VARCHAR(30) NULL,
GhiChu NVARCHAR(50) NULL,
Constraint PK_HocVien PRIMARY KEY(MaHV),
Constraint PK_HocVien_KhoaHoc FOREIGN KEY(MaKH) REFERENCES KhoaHoc
)
go
insert into KhoaHoc values('Khoa hoc 1')
insert into KhoaHoc values('Khoa hoc 2')
insert into KhoaHoc values('Khoa hoc 3')
go
insert into HocVien values('1','1.1','true','2-9-2018','0974081997','1997nguyenvanmanh','Ghi chu')
insert into HocVien values('1','1.2',1,'4-30-2018','0974081997','1997nguyenvanmanh','Ghi chu')
insert into HocVien values('1','1.3','false','2018/5/1','0974081997','1997nguyenvanmanh','Ghi chu')
insert into HocVien values('3','3.1',0,'2018/3/2','0974081997','1997nguyenvanmanh','Ghi chu')
insert into HocVien values('3','3.2',1,'2018/3/10','0974081997','1997nguyenvanmanh','Ghi chu')
insert into HocVien values('3','3.3',0,'2018/12/24','0974081997','1997nguyenvanmanh','Ghi chu')
insert into HocVien values('3','Ghi chu',0,'2018/12/24','0974081997','1997nguyenvanmanh','GC')
go
select * from HocVien
select * from KhoaHoc