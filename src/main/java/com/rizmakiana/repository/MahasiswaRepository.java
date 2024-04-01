package com.rizmakiana.repository;

import java.io.IOException;

import com.rizmakiana.entity.Mahasiswa;

public interface MahasiswaRepository {

    Mahasiswa[] getAll();

    Mahasiswa[] findData(String keyword);
    
    void addData(Mahasiswa mahasiswa);

    void editData(int value, Mahasiswa mahasiswa) throws IOException;

    void deleteData(int value) throws IOException;

}
