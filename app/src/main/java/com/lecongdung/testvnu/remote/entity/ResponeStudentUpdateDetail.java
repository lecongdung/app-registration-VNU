package com.lecongdung.testvnu.remote.entity;

import java.io.Serializable;

public class ResponeStudentUpdateDetail implements Serializable {
    private int id;
    private String tendangnhap;
    private String Email;
    private String Hoten;
    private String Sodienthoai;
    private String Ngaysinh;
    private String Gioitinh;
    private String Noisinh;
    private String Dienthoaicodinh;
    private String Dantoc;
    private String SoCMND;
    private String Ngaycap;
    private String Noicap;
    private String TinhTT;
    private String HuyenTT;
    private String Anhhoso;
    private String Nguoinhanthu;
    private String Diachinhanthu;
    private String doituonguutien;
    private String Khuvuc;
    private String Truonglop10;
    private String Tinhlop10;
    private String Truonglop11;
    private String Tinhlop11;
    private String Truonglop12;
    private String Tinhlop12;
    private String Huyenlop10;
    private String Huyenlop11;
    private String Huyenlop12;
    private String L10K1;
    private String L10K2;
    private String L10CN;
    private String L11K1;
    private String L11K2;
    private String L11CN;
    private String L12K1;
    private String L12K2;
    private String L12CN;
    private String NamTotnghiep;
    private String dToan;
    private String dVan;
    private String dNgoaingu;
    private String DLy;
    private String dHoa;
    private String dSinh;
    private String dSu;
    private String dDia;
    private String dGDCD;
    private String createdAt;
    private String updatedAt;

    public ResponeStudentUpdateDetail() {
        this.id = 0;
        this.tendangnhap = "";
        Email = "";
        Hoten = "";
        Sodienthoai = "";
        Ngaysinh = "";
        Gioitinh = "";
        Noisinh = "";
        Dienthoaicodinh = "";
        Dantoc = "";
        SoCMND = "";
        Ngaycap = "";
        Noicap = "";
        TinhTT = "";
        HuyenTT = "";
        Anhhoso = "";
        Nguoinhanthu = "";
        Diachinhanthu = "";
        this.doituonguutien = "";
        Khuvuc = "";
        Truonglop10 = "";
        Tinhlop10 = "";
        Truonglop11 = "";
        Tinhlop11 = "";
        Truonglop12 = "";
        Tinhlop12 = "";
        Huyenlop10 = "";
        Huyenlop11 = "";
        Huyenlop12 = "";
        L10K1 = "";
        L10K2 = "";
        L10CN = "";
        L11K1 = "";
        L11K2 = "";
        L11CN = "";
        L12K1 = "";
        L12K2 = "";
        L12CN = "";
        NamTotnghiep = "";
        this.dToan = "";
        this.dVan = "";
        this.dNgoaingu = "";
        this.DLy = "";
        this.dHoa = "";
        this.dSinh = "";
        this.dSu = "";
        this.dDia = "";
        this.dGDCD = "";
        this.createdAt = "";
        this.updatedAt = "";
    }

    public ResponeStudentUpdateDetail(int id, String tendangnhap, String email, String hoten, String sodienthoai, String ngaysinh, String gioitinh, String noisinh, String dienthoaicodinh, String dantoc, String soCMND, String ngaycap, String noicap, String tinhTT, String huyenTT, String anhhoso, String nguoinhanthu, String diachinhanthu, String doituonguutien, String khuvuc, String truonglop10, String tinhlop10, String truonglop11, String tinhlop11, String truonglop12, String tinhlop12, String huyenlop10, String huyenlop11, String huyenlop12, String l10K1, String l10K2, String l10CN, String l11K1, String l11K2, String l11CN, String l12K1, String l12K2, String l12CN, String namTotnghiep, String dToan, String dVan, String dNgoaingu, String DLy, String dHoa, String dSinh, String dSu, String dDia, String dGDCD, String createdAt, String updatedAt) {
        this.id = id;
        this.tendangnhap = tendangnhap;
        Email = email;
        Hoten = hoten;
        Sodienthoai = sodienthoai;
        Ngaysinh = ngaysinh;
        Gioitinh = gioitinh;
        Noisinh = noisinh;
        Dienthoaicodinh = dienthoaicodinh;
        Dantoc = dantoc;
        SoCMND = soCMND;
        Ngaycap = ngaycap;
        Noicap = noicap;
        TinhTT = tinhTT;
        HuyenTT = huyenTT;
        Anhhoso = anhhoso;
        Nguoinhanthu = nguoinhanthu;
        Diachinhanthu = diachinhanthu;
        this.doituonguutien = doituonguutien;
        Khuvuc = khuvuc;
        Truonglop10 = truonglop10;
        Tinhlop10 = tinhlop10;
        Truonglop11 = truonglop11;
        Tinhlop11 = tinhlop11;
        Truonglop12 = truonglop12;
        Tinhlop12 = tinhlop12;
        Huyenlop10 = huyenlop10;
        Huyenlop11 = huyenlop11;
        Huyenlop12 = huyenlop12;
        L10K1 = l10K1;
        L10K2 = l10K2;
        L10CN = l10CN;
        L11K1 = l11K1;
        L11K2 = l11K2;
        L11CN = l11CN;
        L12K1 = l12K1;
        L12K2 = l12K2;
        L12CN = l12CN;
        NamTotnghiep = namTotnghiep;
        this.dToan = dToan;
        this.dVan = dVan;
        this.dNgoaingu = dNgoaingu;
        this.DLy = DLy;
        this.dHoa = dHoa;
        this.dSinh = dSinh;
        this.dSu = dSu;
        this.dDia = dDia;
        this.dGDCD = dGDCD;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getSodienthoai() {
        return Sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        Sodienthoai = sodienthoai;
    }

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        Ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        Gioitinh = gioitinh;
    }

    public String getNoisinh() {
        return Noisinh;
    }

    public void setNoisinh(String noisinh) {
        Noisinh = noisinh;
    }

    public String getDienthoaicodinh() {
        return Dienthoaicodinh;
    }

    public void setDienthoaicodinh(String dienthoaicodinh) {
        Dienthoaicodinh = dienthoaicodinh;
    }

    public String getDantoc() {
        return Dantoc;
    }

    public void setDantoc(String dantoc) {
        Dantoc = dantoc;
    }

    public String getSoCMND() {
        return SoCMND;
    }

    public void setSoCMND(String soCMND) {
        SoCMND = soCMND;
    }

    public String getNgaycap() {
        return Ngaycap;
    }

    public void setNgaycap(String ngaycap) {
        Ngaycap = ngaycap;
    }

    public String getNoicap() {
        return Noicap;
    }

    public void setNoicap(String noicap) {
        Noicap = noicap;
    }

    public String getTinhTT() {
        return TinhTT;
    }

    public void setTinhTT(String tinhTT) {
        TinhTT = tinhTT;
    }

    public String getHuyenTT() {
        return HuyenTT;
    }

    public void setHuyenTT(String huyenTT) {
        HuyenTT = huyenTT;
    }

    public String getAnhhoso() {
        return Anhhoso;
    }

    public void setAnhhoso(String anhhoso) {
        Anhhoso = anhhoso;
    }

    public String getNguoinhanthu() {
        return Nguoinhanthu;
    }

    public void setNguoinhanthu(String nguoinhanthu) {
        Nguoinhanthu = nguoinhanthu;
    }

    public String getDiachinhanthu() {
        return Diachinhanthu;
    }

    public void setDiachinhanthu(String diachinhanthu) {
        Diachinhanthu = diachinhanthu;
    }

    public String getDoituonguutien() {
        return doituonguutien;
    }

    public void setDoituonguutien(String doituonguutien) {
        this.doituonguutien = doituonguutien;
    }

    public String getKhuvuc() {
        return Khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        Khuvuc = khuvuc;
    }

    public String getTruonglop10() {
        return Truonglop10;
    }

    public void setTruonglop10(String truonglop10) {
        Truonglop10 = truonglop10;
    }

    public String getTinhlop10() {
        return Tinhlop10;
    }

    public void setTinhlop10(String tinhlop10) {
        Tinhlop10 = tinhlop10;
    }

    public String getTruonglop11() {
        return Truonglop11;
    }

    public void setTruonglop11(String truonglop11) {
        Truonglop11 = truonglop11;
    }

    public String getTinhlop11() {
        return Tinhlop11;
    }

    public void setTinhlop11(String tinhlop11) {
        Tinhlop11 = tinhlop11;
    }

    public String getTruonglop12() {
        return Truonglop12;
    }

    public void setTruonglop12(String truonglop12) {
        Truonglop12 = truonglop12;
    }

    public String getTinhlop12() {
        return Tinhlop12;
    }

    public void setTinhlop12(String tinhlop12) {
        Tinhlop12 = tinhlop12;
    }

    public String getHuyenlop10() {
        return Huyenlop10;
    }

    public void setHuyenlop10(String huyenlop10) {
        Huyenlop10 = huyenlop10;
    }

    public String getHuyenlop11() {
        return Huyenlop11;
    }

    public void setHuyenlop11(String huyenlop11) {
        Huyenlop11 = huyenlop11;
    }

    public String getHuyenlop12() {
        return Huyenlop12;
    }

    public void setHuyenlop12(String huyenlop12) {
        Huyenlop12 = huyenlop12;
    }

    public String getL10K1() {
        return L10K1;
    }

    public void setL10K1(String l10K1) {
        L10K1 = l10K1;
    }

    public String getL10K2() {
        return L10K2;
    }

    public void setL10K2(String l10K2) {
        L10K2 = l10K2;
    }

    public String getL10CN() {
        return L10CN;
    }

    public void setL10CN(String l10CN) {
        L10CN = l10CN;
    }

    public String getL11K1() {
        return L11K1;
    }

    public void setL11K1(String l11K1) {
        L11K1 = l11K1;
    }

    public String getL11K2() {
        return L11K2;
    }

    public void setL11K2(String l11K2) {
        L11K2 = l11K2;
    }

    public String getL11CN() {
        return L11CN;
    }

    public void setL11CN(String l11CN) {
        L11CN = l11CN;
    }

    public String getL12K1() {
        return L12K1;
    }

    public void setL12K1(String l12K1) {
        L12K1 = l12K1;
    }

    public String getL12K2() {
        return L12K2;
    }

    public void setL12K2(String l12K2) {
        L12K2 = l12K2;
    }

    public String getL12CN() {
        return L12CN;
    }

    public void setL12CN(String l12CN) {
        L12CN = l12CN;
    }

    public String getNamTotnghiep() {
        return NamTotnghiep;
    }

    public void setNamTotnghiep(String namTotnghiep) {
        NamTotnghiep = namTotnghiep;
    }

    public String getdToan() {
        return dToan;
    }

    public void setdToan(String dToan) {
        this.dToan = dToan;
    }

    public String getdVan() {
        return dVan;
    }

    public void setdVan(String dVan) {
        this.dVan = dVan;
    }

    public String getdNgoaingu() {
        return dNgoaingu;
    }

    public void setdNgoaingu(String dNgoaingu) {
        this.dNgoaingu = dNgoaingu;
    }

    public String getDLy() {
        return DLy;
    }

    public void setDLy(String DLy) {
        this.DLy = DLy;
    }

    public String getdHoa() {
        return dHoa;
    }

    public void setdHoa(String dHoa) {
        this.dHoa = dHoa;
    }

    public String getdSinh() {
        return dSinh;
    }

    public void setdSinh(String dSinh) {
        this.dSinh = dSinh;
    }

    public String getdSu() {
        return dSu;
    }

    public void setdSu(String dSu) {
        this.dSu = dSu;
    }

    public String getdDia() {
        return dDia;
    }

    public void setdDia(String dDia) {
        this.dDia = dDia;
    }

    public String getdGDCD() {
        return dGDCD;
    }

    public void setdGDCD(String dGDCD) {
        this.dGDCD = dGDCD;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void responeContent(ResponeStudentUpdateDetail respone) {
        if(respone.getId() == 0) {
            this.id = respone.getId();
        }
        if(respone.getTendangnhap() != null) {
            this.tendangnhap = respone.getTendangnhap();
        }
        if(respone.getEmail() != null) {
            this.Email = respone.getEmail();
        }
        if(respone.getHoten() != null) {
            this.Hoten = respone.getHoten();
        }
        if(respone.getSodienthoai() != null) {
            this.Sodienthoai = respone.getSodienthoai();
        }
        if(respone.getNgaysinh() != null) {
            this.Ngaysinh = respone.getNgaysinh();
        }
        if(respone.getGioitinh() != null) {
            this.Gioitinh = respone.getGioitinh();
        }
        if(respone.getNoisinh() != null) {
            this.Noisinh = respone.getNoisinh();
        }
        if(respone.getDienthoaicodinh() != null) {
            this.Dienthoaicodinh = respone.getDienthoaicodinh();
        }
        if(respone.getDantoc() != null) {
            this.Dantoc = respone.getDantoc();
        }
        if(respone.getSoCMND() != null) {
            this.SoCMND = respone.getSoCMND();
        }
        if(respone.getNgaycap() != null) {
            this.Ngaycap = respone.getNgaycap();
        }
        if(respone.getNoicap() != null) {
            this.Noicap = respone.getNoicap();
        }
        if(respone.getTinhTT() != null) {
            this.TinhTT = respone.getTinhTT();
        }
        if(respone.getHuyenTT() != null) {
            this.HuyenTT = respone.getHuyenTT();
        }
        if(respone.getAnhhoso() != null) {
            this.Anhhoso = respone.getAnhhoso();
        }
        if(respone.getNguoinhanthu() != null) {
            this.Nguoinhanthu = respone.getNguoinhanthu();
        }
        if(respone.getDiachinhanthu() != null) {
            this.Diachinhanthu = respone.getDiachinhanthu();
        }
        if(respone.getDoituonguutien() != null) {
            this.doituonguutien = respone.getDoituonguutien();
        }
        if(respone.getKhuvuc() != null) {
            this.Khuvuc = respone.getKhuvuc();
        }
        if(respone.getTruonglop10() != null) {
            this.Truonglop10 = respone.getTruonglop10();
        }
        if(respone.getTinhlop10() != null) {
            this.Tinhlop10 = respone.getTinhlop10();
        }
        if(respone.getHuyenlop10() != null) {
            this.Huyenlop10 = respone.getHuyenlop10();
        }
        if(respone.getL10K1() != null) {
            this.L10K1 = respone.getL10K1();
        }
        if(respone.getL10K2() != null) {
            this.L10K2 = respone.getL10K2();
        }
        if(respone.getL10CN() != null) {
            this.L10CN = respone.getL10CN();
        }

        if(respone.getTruonglop11() != null) {
            this.Truonglop11 = respone.getTruonglop11();
        }
        if(respone.getTinhlop11() != null) {
            this.Tinhlop11 = respone.getTinhlop11();
        }
        if(respone.getHuyenlop11() != null) {
            this.Huyenlop11 = respone.getHuyenlop11();
        }
        if(respone.getL11K1() != null) {
            this.L11K1 = respone.getL11K1();
        }
        if(respone.getL11K2() != null) {
            this.L11K2 = respone.getL11K2();
        }
        if(respone.getL11CN() != null) {
            this.L11CN = respone.getL11CN();
        }


        if(respone.getTruonglop12() != null) {
            this.Truonglop12 = respone.getTruonglop12();
        }
        if(respone.getTinhlop12() != null) {
            this.Tinhlop12 = respone.getTinhlop12();
        }
        if(respone.getHuyenlop12() != null) {
            this.Huyenlop12 = respone.getHuyenlop12();
        }
        if(respone.getL12K1() != null) {
            this.L12K1 = respone.getL12K1();
        }
        if(respone.getL12K2() != null) {
            this.L12K2 = respone.getL12K2();
        }
        if(respone.getL12CN() != null) {
            this.L12CN = respone.getL12CN();
        }

        if(respone.getNamTotnghiep() != null) {
            this.NamTotnghiep = respone.getNamTotnghiep();
        }
        if(respone.getdToan() != null) {
            this.dToan = respone.getdToan();
        }
        if(respone.getdVan() != null) {
            this.dVan = respone.getdVan();
        }
        if(respone.getdNgoaingu() != null) {
            this.dNgoaingu = respone.getdNgoaingu();
        }
        if(respone.getDLy() != null) {
            this.DLy = respone.getDLy();
        }
        if(respone.getdHoa() != null) {
            this.dHoa = respone.getdHoa();
        }
        if(respone.getdSinh() != null) {
            this.dSinh = respone.getdSinh();
        }
        if(respone.getdSu() != null) {
            this.dSu = respone.getdSu();
        }
        if(respone.getdDia() != null) {
            this.dDia = respone.getdDia();
        }
        if(respone.getdGDCD() != null) {
            this.dGDCD = respone.getdGDCD();
        }

    }
}
