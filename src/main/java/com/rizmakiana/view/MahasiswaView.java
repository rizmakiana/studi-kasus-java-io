package com.rizmakiana.view;

import java.io.IOException;
import java.util.Scanner;

import com.rizmakiana.entity.Mahasiswa;
import com.rizmakiana.service.MahasiswaService;
import com.rizmakiana.util.*;

public class MahasiswaView {

    private MahasiswaService mahasiswaService;
    private Scanner scanner;

    public MahasiswaView (MahasiswaService mahasiswaService){
        this.mahasiswaService = mahasiswaService;
    }

    public void showMenu() throws IOException {

        String menu;
        boolean isNext;

        do {

            Clear.Screen();
            System.out.println("List Nilai Mahasiswa Unindra Kelas S3S\n");
            System.out.println("1. Tampilkan Data Mahasiswa");
            System.out.println("2. Cari Data Mahasiswa");
            System.out.println("3. Tambah Data Mahasiswa");
            System.out.println("4. Edit Data Mahasiswa");
            System.out.println("5. Hapus Data Mahasiswa\n");

            menu = Input.string("Silahkan pilih menu : ");
            System.out.println(menu);

            switch (menu) {
                case "1":
                    showData();
                    break;
                case "2":
                    findData();
                    break;
                case "3":
                    addData();
                    break;
                case "4":
                    editData();
                    break;
                case "5":
                    deleteData();
                    break;
                default:
                    System.err.println("Pilihan tidak ditemukan");
                    break;
            }

            isNext = Input.getYesOrNo("Apakah anda ingin melanjutkan [Y/N] : ");

        } while (isNext);

    }

    public void showData() {
        Clear.Screen();
        System.out.println("List Seluruh Mahasiswa");
        mahasiswaService.showMahasiswa();
    }

    public void findData() {
        Clear.Screen();
        String keyword = Input.string("Masukan kata kunci : ");
        mahasiswaService.findData(keyword);
    }

    public void addData() throws IOException {
        Clear.Screen();
        scanner = new Scanner(System.in);

        System.out.println("Menambah Data");

        System.out.print("Masukan Nama Mahasiswa    : ");
        String name = scanner.nextLine();

        int taskScore = Input.number     ("Masukan Nilai Tugas       : ");
        int midTestScore = Input.number  ("Masukan Nilai UTS         : ");
        int lastTestScore = Input.number ("Masukan Nilai UAS         : ");

        Mahasiswa mahasiswa = new Mahasiswa(name,taskScore,midTestScore,lastTestScore);

        mahasiswaService.addData(mahasiswa);

    }

    public void deleteData() throws IOException {
        Clear.Screen();
        mahasiswaService.showMahasiswa();

        int value = Input.number("Masukan no mahasiswa yang ingin dihapus : ");

        if (value <= 0 || value > mahasiswaService.getDataLength()) {
            System.err.println("Data Tidak Ditemukan");
        } else {
            mahasiswaService.deleteData(value);
        }
    }
    
    public void editData() throws IOException {
        Clear.Screen();
        mahasiswaService.showMahasiswa();
        
        int value = Input.number("Masukan no mahasiswa yang ingin diubah : ");
    
        if (value <= 0 || value > mahasiswaService.getDataLength()) {
            System.err.println("Data Tidak Ditemukan");
        } else {
            mahasiswaService.editData(value);
        }
        
    }
}
