package com.rizmakiana;

import java.io.File;
import java.io.IOException;

import com.rizmakiana.repository.MahasiswaRepository;
import com.rizmakiana.repository.MahasiswaRepositoryImpl;
import com.rizmakiana.service.MahasiswaService;
import com.rizmakiana.service.MahasiswaServiceImpl;
import com.rizmakiana.util.Clear;
import com.rizmakiana.util.ConnectionUtil;
import com.rizmakiana.view.*;

public class App { 
    public static void main( String[] args ) throws IOException {
        Clear.Screen();

        File database = ConnectionUtil.getConnectionFile();
        MahasiswaRepository mahasiswaRepository = new MahasiswaRepositoryImpl(database);
        MahasiswaService mahasiswaService = new MahasiswaServiceImpl(mahasiswaRepository);
        MahasiswaView mahasiswaView = new MahasiswaView(mahasiswaService);

        mahasiswaView.showMenu();
        

    }

}
