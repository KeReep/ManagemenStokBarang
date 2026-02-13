package storage;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame(new javax.swing.JFrame(), true);
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
}
