package storage;

import storage.view.LoginPage;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        
        //Menampilkan frame loginPage
        SwingUtilities.invokeLater(() -> {
            LoginPage login = new LoginPage();
            login.setSize(810, 610);
            login.setLocationRelativeTo(null); //meletakkan posisi dari framenya
            login.setResizable(false);
            login.setVisible(true);
        });

    }

}
