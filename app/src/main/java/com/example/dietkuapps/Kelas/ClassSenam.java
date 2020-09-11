package com.example.dietkuapps.Kelas;

public class ClassSenam {
    String IdSenam;
    String NamaSenam;
    String FotoSenam;
    String Deskripsi;

    public ClassSenam(String idSenam, String namaSenam, String fotoSenam, String deskripsi) {
        IdSenam = idSenam;
        NamaSenam = namaSenam;
        FotoSenam = fotoSenam;
        Deskripsi = deskripsi;
    }

    public String getIdSenam() {
        return IdSenam;
    }

    public void setIdSenam(String idSenam) {
        IdSenam = idSenam;
    }

    public String getNamaSenam() {
        return NamaSenam;
    }

    public void setNamaSenam(String namaSenam) {
        NamaSenam = namaSenam;
    }

    public String getFotoSenam() {
        return FotoSenam;
    }

    public void setFotoSenam(String fotoSenam) {
        FotoSenam = fotoSenam;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }
}
