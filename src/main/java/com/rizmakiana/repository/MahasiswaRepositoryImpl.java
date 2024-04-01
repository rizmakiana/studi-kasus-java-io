package com.rizmakiana.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import com.rizmakiana.entity.Mahasiswa;
import com.rizmakiana.util.ConnectionUtil;

public class MahasiswaRepositoryImpl implements MahasiswaRepository {

    @SuppressWarnings("unused")
    private File file;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;


    public MahasiswaRepositoryImpl(File file) {
        this.file = file;
    }

    @Override
    public Mahasiswa[] getAll() {
        try {
            bufferedReader = ConnectionUtil.getConnectionShow();
            List<Mahasiswa> list = new ArrayList<>();
            String data = bufferedReader.readLine();

            while (data != null) {
                Mahasiswa mahasiswa = new Mahasiswa();
                StringTokenizer stringTokenizer = new StringTokenizer(data, ",");

                mahasiswa.setId(stringTokenizer.nextToken());
                mahasiswa.setName(stringTokenizer.nextToken());
                mahasiswa.setTaskScore(Integer.parseInt(stringTokenizer.nextToken()));
                mahasiswa.setMidTestScore(Integer.parseInt(stringTokenizer.nextToken()));
                mahasiswa.setLastTestScore(Integer.parseInt(stringTokenizer.nextToken()));
                mahasiswa.setLastScore();
                list.add(mahasiswa);

                data = bufferedReader.readLine();
            }

            return list.toArray(new Mahasiswa[] {});
        } catch (Exception e) {

            System.err.println("Database tidak ditemukan\nSilahkan buat database terlebih dahulu");
            throw new RuntimeException(e);
        }

    }

    @Override
    public Mahasiswa[] findData(String keyword) {
        try {
            bufferedReader = ConnectionUtil.getConnectionShow();

            List<Mahasiswa> list = new ArrayList<>();
            String data = bufferedReader.readLine();

            while (data != null) {
                Mahasiswa mahasiswa = new Mahasiswa();
                StringTokenizer stringTokenizer = new StringTokenizer(data, ",");

                stringTokenizer.nextToken();
                String temp = stringTokenizer.nextToken();

                if (temp.toLowerCase().contains(keyword.toLowerCase())) {

                    StringTokenizer stringTokenizer2 = new StringTokenizer(data, ",");
                    mahasiswa.setId(stringTokenizer2.nextToken());
                    mahasiswa.setName(stringTokenizer2.nextToken());
                    mahasiswa.setTaskScore(Integer.parseInt(stringTokenizer2.nextToken()));
                    mahasiswa.setMidTestScore(Integer.parseInt(stringTokenizer2.nextToken()));
                    mahasiswa.setLastTestScore(Integer.parseInt(stringTokenizer2.nextToken()));
                    mahasiswa.setLastScore();

                    list.add(mahasiswa);
                }
                data = bufferedReader.readLine();
            }

            return list.toArray(new Mahasiswa[] {});
        } catch (Exception e) {
            System.err.println("Database tidak ditemukan");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addData(Mahasiswa m) {
        try {
            bufferedWriter = ConnectionUtil.getConnectionAdd();
            
            String value = m.getId()+","+m.getName()+","+m.getTaskScore()+","+m.getLastTestScore()+","+m.getLastTestScore();
            bufferedWriter.write(value);
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (Exception e) {
            System.err.println("Gagal menambah data");
        }

    }

    @Override
    public void editData(int value, Mahasiswa n) throws IOException{
        Mahasiswa[] mahasiswas = getAll();

        fileWriter = ConnectionUtil.getConnectionToDeleteWithReset();
        int entry = 1;
        
        for (Mahasiswa m : mahasiswas) {
            String data;
            if (entry != value){
                data = m.getId()+","+m.getName()+","+m.getTaskScore()+","+m.getLastTestScore()+","+m.getLastTestScore();
                fileWriter.write(data + "\n");
            } else {
                data = n.getId()+","+n.getName()+","+n.getTaskScore()+","+n.getLastTestScore()+","+n.getLastTestScore();
                fileWriter.write(data + "\n");
            }
            entry++;
        }
        fileWriter.flush();
    }

    @Override
    public void deleteData(int value) throws IOException {
        Mahasiswa[] mahasiswas = getAll();

        fileWriter = ConnectionUtil.getConnectionToDeleteWithReset();
        int entry = 1;
        
        for (Mahasiswa m : mahasiswas) {
            String data = m.getId()+","+m.getName()+","+m.getTaskScore()+","+m.getLastTestScore()+","+m.getLastTestScore();
            if (entry != value){
                fileWriter.write(data + "\n");
            }
            entry++;
        }
        fileWriter.flush();
    }


}
