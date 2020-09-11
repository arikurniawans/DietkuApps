package com.example.dietkuapps.Kelas;

public class ClassDiet {
    String IdDiet;
    String NamaDiet;
    String Panduan;
    String Manfaat;
    String Makanan;

    public ClassDiet(String idDiet, String namaDiet, String panduan, String manfaat, String makanan) {
        IdDiet = idDiet;
        NamaDiet = namaDiet;
        Panduan = panduan;
        Manfaat = manfaat;
        Makanan = makanan;
    }

    public String getIdDiet() {
        return IdDiet;
    }

    public void setIdDiet(String idDiet) {
        IdDiet = idDiet;
    }

    public String getNamaDiet() {
        return NamaDiet;
    }

    public void setNamaDiet(String namaDiet) {
        NamaDiet = namaDiet;
    }

    public String getPanduan() {
        return Panduan;
    }

    public void setPanduan(String panduan) {
        Panduan = panduan;
    }

    public String getManfaat() {
        return Manfaat;
    }

    public void setManfaat(String manfaat) {
        Manfaat = manfaat;
    }

    public String getMakanan() {
        return Makanan;
    }

    public void setMakanan(String makanan) {
        Makanan = makanan;
    }

}
