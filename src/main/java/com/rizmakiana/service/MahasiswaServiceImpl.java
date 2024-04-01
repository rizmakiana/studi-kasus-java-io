package com.rizmakiana.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import com.rizmakiana.entity.Mahasiswa;
import com.rizmakiana.repository.MahasiswaRepository;
import com.rizmakiana.util.Clear;
import com.rizmakiana.util.ConnectionUtil;
import com.rizmakiana.util.Input;

public class MahasiswaServiceImpl implements MahasiswaService {

    private MahasiswaRepository mahasiswaRepository;
    private static Scanner scanner = new Scanner(System.in);

    public MahasiswaServiceImpl (MahasiswaRepository mahasiswaRepository){
        this.mahasiswaRepository = mahasiswaRepository;
    }

    @Override
    public void showMahasiswa() {
        Mahasiswa[] mahasiswa = mahasiswaRepository.getAll();

        printData(mahasiswa);
    }

    @Override
    public void findData(String keyword) {
        Mahasiswa[] mahasiswa = mahasiswaRepository.findData(keyword);

        boolean isExist = mahasiswa.length != 0;

        if (isExist) {
            printData(mahasiswa);
        } else {
            System.err.println("Tidak ada data yang sesuai dengan '" + keyword +"'");
        }
    }

    @Override
    public void addData(Mahasiswa mahasiswa) throws IOException {
        Clear.Screen();
        String idTemp = getId();
        boolean isAdd;

        System.out.println("Data yang anda masukan adalah\n");

        System.out.println("NPM             : " + idTemp);
        System.out.println("Nama            : " + mahasiswa.getName());
        System.out.println("Nilai Tugas     : " + mahasiswa.getTaskScore());
        System.out.println("Nilai UTS       : " + mahasiswa.getMidTestScore());
        System.out.println("Nilai UAS       : " + mahasiswa.getLastTestScore());

        isAdd = Input.getYesOrNo("\nApakah data yang anda masukan sesuai [Y/N] ? ");

        if (isAdd){
            mahasiswa.setId(idTemp);
            mahasiswaRepository.addData(mahasiswa);
            System.err.println("Berhasil ditambahkan");
        } else {
            System.err.println("Berhasil dibatalkan");
        }

    }

    @Override
    public void deleteData(int x) throws IOException {
        Mahasiswa[] mahasiswa = mahasiswaRepository.getAll();
        Clear.Screen();
        printDataToDelete(mahasiswa, x);

        boolean isDelete = Input.getYesOrNo("\nApakah anda yakin ingin menghapus data tersebut [Y/N] : ");

        if (isDelete) {
            mahasiswaRepository.deleteData(x);
            System.out.println("Berhasil menghapus data");
        }else {
            System.out.println("Gagal menghapus data");
        }
        
        
    }

    @Override
    public void editData(int x) throws IOException {
        Mahasiswa[] mahasiswas = mahasiswaRepository.getAll();
        
        Clear.Screen();
        printDataToEdit(mahasiswas, x);

        boolean isEdit, isUpdate;

        isEdit = Input.getYesOrNo("\nApakah Anda yakin ingin mengubah data tersebut [Y/N] : ");

        if (isEdit){
            Clear.Screen();
            System.out.print("Masukan Nama Baru           : ");
            String name = scanner.nextLine();
            int taskScore = Input.number("Masukan Nilai Tugas Baru  : ");
            int midScore = Input.number("Masukan Nilai UTS Baru    : ");
            int lastScore = Input.number("Masukan Nilai UAS Baru    : ");
            double lastTestScore = ((0.2 * taskScore) + (0.3 * midScore) + (0.5 * lastScore));

            Clear.Screen();
            System.out.println("NPM         : " + mahasiswas[x-1].getId());
            System.out.println("Nama        : " + mahasiswas[x-1].getName() + " -> " + name);
            System.out.println("Nilai Tugas : " + mahasiswas[x-1].getTaskScore() + " -> " + taskScore);
            System.out.println("Nilai UTS   : " + mahasiswas[x-1].getMidTestScore() + " -> " + midScore);
            System.out.println("Nilai UAS   : " + mahasiswas[x-1].getLastTestScore() + " -> " + lastScore);
            System.out.println("Nilai Akhir : " + lastTestScore);
            
            isUpdate = Input.getYesOrNo("Data tersebut akan berubah seperti di atas. Apakah anda yakin [Y/N] : ");
            Mahasiswa mahasiswa = new Mahasiswa(mahasiswas[x-1].getId(), name, taskScore, lastScore, lastScore);

            if (isUpdate) {
                mahasiswaRepository.editData(x, mahasiswa);
            } else {
                System.err.println("Batal ubah data");
            }

        } else {
            System.err.println("Batal mengubah data");
        }
        
        
    }

    @Override
    public int getDataLength() {
        Mahasiswa[] mahasiswa = mahasiswaRepository.getAll();
        return mahasiswa.length;
    }

    public void printData(Mahasiswa[] mahasiswa) {
        header();
        
        int i = 0;
        
        for (Mahasiswa d : mahasiswa) {
            i++;
            System.out.printf("| %2d | %s | %25s ", i, d.getId(), d.getName());
            System.out.printf("| %11d | %9d | %9d |%12.2f |\n",d.getTaskScore(), d.getMidTestScore(), d.getLastTestScore(),d.getLastScore());
        }
        footer();
    }

    public void printDataToDelete(Mahasiswa[] mahasiswa, int x) {        
        int i = 0;
        for (Mahasiswa d : mahasiswa) {
            i++;
            if (i == x) {
                System.out.println("Data yang ingin dihapus adalah\n");

                System.out.println("NPM             : " + d.getId());
                System.out.println("Nama            : " + d.getName());
                System.out.println("Nilai Tugas     : " + d.getTaskScore());
                System.out.println("Nilai UTS       : " + d.getMidTestScore());
                System.out.println("Nilai UAS       : " + d.getLastTestScore());
            }
        }
    }

    public void printDataToEdit(Mahasiswa[] mahasiswa, int x) {        
        int i = 1;
        for (Mahasiswa d : mahasiswa) {
            if (i == x) {
                System.out.println("Data yang ingin diubah adalah\n");

                System.out.println("NPM             : " + d.getId());
                System.out.println("Nama            : " + d.getName());
                System.out.println("Nilai Tugas     : " + d.getTaskScore());
                System.out.println("Nilai UTS       : " + d.getMidTestScore());
                System.out.println("Nilai UAS       : " + d.getLastTestScore());
            }
            i++;
        }
    }

    public void header() {
        System.out.println("+===================================================================================================+");
        System.out.println("| No | NPM          | Nama Mahasiswa            | Nilai Tugas | Nilai UTS | Nilai UAS | Nilai Akhir |");
        System.out.println("+===================================================================================================+");
    }

    public void footer() {
        System.out.println("+===================================================================================================+");        
    }

    public String getId() throws IOException {
        BufferedReader bufferedReader = ConnectionUtil.getConnectionShow();

        String data = bufferedReader.readLine();
        int value = 0;
        while (data != null) {
            String temp = data.substring(8,12);
            value = Integer.parseInt(temp);
            data = bufferedReader.readLine();
        }
        value++;
        return "20224350".concat(String.valueOf(value));
    }

    
}
