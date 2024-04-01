package com.rizmakiana.service;

import java.io.IOException;

import com.rizmakiana.entity.Mahasiswa;

public interface MahasiswaService {
    
    void showMahasiswa();

    void addData(Mahasiswa mahasiswa) throws IOException;

    void findData(String keyword);

    void deleteData(int x) throws IOException;

    void editData(int x) throws IOException;

    int getDataLength();


}
