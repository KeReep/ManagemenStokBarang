package storage.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.table.TableModel;

// halaman utama — manajemen stok barang
public class MainFrame extends javax.swing.JFrame {
    
    private String selectedUsername = "";
     private final String userRole;
    
    public MainFrame(String role) {
        this.userRole = role;
        initComponents();
        styleTable(table);
        styleTable(userTable);
        
        
        if (!userRole.equalsIgnoreCase("Admin")) {
            btnManageUser.setVisible(false);
        }
    }
    
    private void styleTable (javax.swing.JTable t) {
        // styling header tabel
        //Mengubah warna background dan font dari table
        t.getTableHeader().setBackground(new Color(0, 102, 102)); 
        t.getTableHeader().setForeground(Color.WHITE); 
        t.getTableHeader().setPreferredSize(
                new Dimension(t.getTableHeader().getWidth(), 25)
        );
        t.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        t.getTableHeader().setBackground(new Color(0,102,102));
        t.getTableHeader().setForeground(Color.WHITE);
        
        t.setRowHeight(25); 
        t.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        t.getTableHeader().setReorderingAllowed(false);  // disable drag kolom
        t.setDefaultEditor(Object.class, null);           // disable edit cell
    }
    
    // cek semua field sudah diisi dan stok berisi angka
    private boolean validasiFields (String formType) {
        boolean namaIsi = !txtNama.getText().trim().isEmpty();
        boolean kodeIsi = !txtKode.getText().trim().isEmpty();
        boolean infoIsi = !txtInfo.getText().trim().isEmpty();
        boolean stokIsi = !txtStok.getText().trim().isEmpty();

        boolean stokAngka = false;
        if (stokIsi) {
            try {
                int stokVal = Integer.parseInt(txtStok.getText().trim());
                stokAngka = stokVal >= 0; 
            } catch (NumberFormatException e) {
                stokAngka = false;
            }
        }

        if (formType.equals("edit") || formType.equals("tambah")) {
            return namaIsi && kodeIsi && infoIsi && stokAngka;
        }

        return false;
               
    }
    
    private void loadTableUser() {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0);
        
        String SUrl = "jdbc:mysql://localhost:3306/java_user_database";
        String SUser = "root";
        String SPass = "";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from users");
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("created_at"),
                    rs.getString("status")
                
                });
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // ambil semua data dari DB dan tampilkan ke tabel
    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // tabel di kosongkan dahulu

        String SUrl = "jdbc:mysql://localhost:3306/java_user_database";
        String SUser = "root";
        String SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from barang");

            int totalItem = 0;
            int lowStock = 0;

            while (rs.next()) {
                int stok = rs.getInt("stok");
                model.addRow(new Object[]{
                    rs.getString("nama"),
                    rs.getString("kode"),
                    rs.getString("jenis"),
                    stok,
                    rs.getString("infoTambah")
                });
                totalItem++;
                if (stok < 5) lowStock++; // hitung barang yang stoknya mau habis
            }

            totalItemCount.setText(String.valueOf(totalItem)); 
            lowStockCount.setText(String.valueOf(lowStock));

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        sidebar = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnDashboard = new javax.swing.JButton();
        btnListBarang = new javax.swing.JButton();
        btnManageUser = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        Parent = new javax.swing.JPanel();
        panelDashboard = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        panelListBarang = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        panelLowStok = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lowStockCount = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelTotalItem = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        totalItemCount = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        PanelInput = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtInfo = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        lblStok = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        cbxJenis = new javax.swing.JComboBox<>();
        btnBatal = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        panelManageUser = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        panelUserDetails = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        checkPassword = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        boxRole = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        boxStatus = new javax.swing.JComboBox<>();
        txtUsername = new storage.component.TextField();
        txtEmail = new storage.component.TextField();
        txtPassword = new storage.component.PasswordField();
        txtConPassword = new storage.component.PasswordField();
        btnDeleteUser = new storage.component.RoundedButton();
        btnAddUser = new storage.component.RoundedButton();
        btnUpdateUser = new storage.component.RoundedButton();
        btnClearUser = new storage.component.RoundedButton();
        jLabel31 = new javax.swing.JLabel();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Managemen Stok Barang");
        setBackground(new java.awt.Color(250, 250, 250));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1200, 710));
        getContentPane().setLayout(null);

        sidebar.setBackground(new java.awt.Color(0, 102, 102));
        sidebar.setLayout(null);

        btnLogout.setBackground(new java.awt.Color(0, 102, 102));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(250, 250, 250));
        btnLogout.setText("Logout");
        btnLogout.setFocusable(false);
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        sidebar.add(btnLogout);
        btnLogout.setBounds(60, 380, 120, 27);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INVENTORY");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sidebar.add(jLabel2);
        jLabel2.setBounds(70, 130, 104, 25);
        sidebar.add(jSeparator2);
        jSeparator2.setBounds(40, 350, 160, 19);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("MANAGEMENT");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sidebar.add(jLabel7);
        jLabel7.setBounds(60, 160, 132, 25);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("SYSTEM");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sidebar.add(jLabel8);
        jLabel8.setBounds(80, 190, 80, 25);
        sidebar.add(jSeparator3);
        jSeparator3.setBounds(40, 220, 160, 20);

        btnDashboard.setBackground(new java.awt.Color(0, 102, 102));
        btnDashboard.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(250, 250, 250));
        btnDashboard.setText("Home");
        btnDashboard.setFocusable(false);
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);
        sidebar.add(btnDashboard);
        btnDashboard.setBounds(60, 240, 120, 27);

        btnListBarang.setBackground(new java.awt.Color(0, 102, 102));
        btnListBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnListBarang.setForeground(new java.awt.Color(250, 250, 250));
        btnListBarang.setText("List Barang");
        btnListBarang.setFocusable(false);
        btnListBarang.addActionListener(this::btnListBarangActionPerformed);
        sidebar.add(btnListBarang);
        btnListBarang.setBounds(60, 310, 120, 27);

        btnManageUser.setBackground(new java.awt.Color(0, 102, 102));
        btnManageUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnManageUser.setForeground(new java.awt.Color(250, 250, 250));
        btnManageUser.setText("Manage User");
        btnManageUser.setFocusable(false);
        btnManageUser.addActionListener(this::btnManageUserActionPerformed);
        sidebar.add(btnManageUser);
        btnManageUser.setBounds(60, 440, 120, 27);

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/storage/icon/icons8-truck-weight-max-loading-100.png"))); // NOI18N
        sidebar.add(jLabel32);
        jLabel32.setBounds(70, 30, 110, 80);

        getContentPane().add(sidebar);
        sidebar.setBounds(-10, 0, 248, 1275);

        Parent.setBackground(new java.awt.Color(255, 255, 255));
        Parent.setLayout(new java.awt.CardLayout());

        panelDashboard.setBackground(Color.WHITE);
        panelDashboard.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(80, 160, 80));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 110));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Total Item");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel15)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        panelDashboard.add(jPanel1);
        jPanel1.setBounds(710, 20, 200, 110);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 110));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Total Item");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel17)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        panelDashboard.add(jPanel2);
        jPanel2.setBounds(20, 20, 200, 110);

        jPanel4.setBackground(new java.awt.Color(230, 81, 81));
        jPanel4.setPreferredSize(new java.awt.Dimension(200, 110));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Total Item");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel18)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        panelDashboard.add(jPanel4);
        jPanel4.setBounds(250, 20, 200, 110);

        jPanel6.setBackground(new java.awt.Color(60, 120, 200));
        jPanel6.setPreferredSize(new java.awt.Dimension(200, 110));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Total Item");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel19)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        panelDashboard.add(jPanel6);
        jPanel6.setBounds(480, 20, 200, 110);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 102));
        jLabel20.setText("Stok Jenis Barang");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel20)
                .addContainerGap(187, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        panelDashboard.add(jPanel7);
        jPanel7.setBounds(30, 290, 320, 260);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama", "Kode", "Stok"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        panelDashboard.add(jPanel8);
        jPanel8.setBounds(410, 290, 320, 260);

        Parent.add(panelDashboard, "card3");

        panelListBarang.setBackground(Color.WHITE);
        panelListBarang.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("List Barang");
        panelListBarang.add(jLabel1);
        jLabel1.setBounds(120, 30, 127, 32);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Kode", "Jenis", "Stok", "Info Tambahan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFocusable(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tableComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
        }

        panelListBarang.add(jScrollPane1);
        jScrollPane1.setBounds(30, 80, 870, 320);

        panelLowStok.setBackground(new java.awt.Color(255, 255, 255));
        panelLowStok.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        panelLowStok.setLayout(null);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Low Stock");
        panelLowStok.add(jLabel3);
        jLabel3.setBounds(10, 0, 60, 16);

        lowStockCount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lowStockCount.setText("0");
        panelLowStok.add(lowStockCount);
        lowStockCount.setBounds(10, 20, 37, 10);

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        jPanel3.setLayout(null);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Total Item");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(10, 0, 60, 16);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("0");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(10, 20, 37, 10);

        panelLowStok.add(jPanel3);
        jPanel3.setBounds(670, 30, 0, 0);

        panelListBarang.add(panelLowStok);
        panelLowStok.setBounds(790, 30, 110, 39);

        panelTotalItem.setBackground(new java.awt.Color(255, 255, 255));
        panelTotalItem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        panelTotalItem.setLayout(null);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Total Item");
        panelTotalItem.add(jLabel9);
        jLabel9.setBounds(10, 0, 60, 16);

        totalItemCount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalItemCount.setText("0");
        panelTotalItem.add(totalItemCount);
        totalItemCount.setBounds(10, 20, 37, 10);

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        jPanel5.setLayout(null);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Total Item");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(10, 0, 60, 16);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("0");
        jPanel5.add(jLabel12);
        jLabel12.setBounds(10, 20, 37, 10);

        panelTotalItem.add(jPanel5);
        jPanel5.setBounds(670, 30, 0, 0);

        panelListBarang.add(panelTotalItem);
        panelTotalItem.setBounds(640, 30, 110, 39);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Input Data Barang");
        jLabel4.setOpaque(true);
        panelListBarang.add(jLabel4);
        jLabel4.setBounds(40, 410, 210, 30);

        PanelInput.setBackground(new java.awt.Color(255, 255, 255));
        PanelInput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        PanelInput.setLayout(null);
        PanelInput.add(jSeparator1);
        jSeparator1.setBounds(10, 180, 850, 10);

        btnTambah.setBackground(new java.awt.Color(0, 102, 102));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("Tambah");
        btnTambah.setFocusable(false);
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        PanelInput.add(btnTambah);
        btnTambah.setBounds(100, 190, 150, 30);

        btnHapus.setBackground(new java.awt.Color(255, 102, 102));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Hapus");
        btnHapus.setFocusable(false);
        btnHapus.addActionListener(this::btnHapusActionPerformed);
        PanelInput.add(btnHapus);
        btnHapus.setBounds(650, 190, 150, 30);

        btnEdit.setBackground(new java.awt.Color(102, 102, 102));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.setFocusable(false);
        btnEdit.addActionListener(this::btnEditActionPerformed);
        PanelInput.add(btnEdit);
        btnEdit.setBounds(290, 190, 150, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Nama Barang");
        PanelInput.add(jLabel10);
        jLabel10.setBounds(20, 50, 90, 16);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Kode Barang");
        PanelInput.add(jLabel13);
        jLabel13.setBounds(20, 100, 80, 16);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Jenis Barang");
        PanelInput.add(jLabel14);
        jLabel14.setBounds(20, 150, 80, 16);
        PanelInput.add(txtInfo);
        txtInfo.setBounds(570, 90, 270, 80);
        PanelInput.add(txtNama);
        txtNama.setBounds(120, 40, 280, 30);

        lblStok.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblStok.setText("Stok");
        PanelInput.add(lblStok);
        lblStok.setBounds(470, 50, 90, 16);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Info Tambahan");
        PanelInput.add(jLabel16);
        jLabel16.setBounds(470, 100, 90, 16);
        PanelInput.add(txtKode);
        txtKode.setBounds(120, 90, 280, 30);
        PanelInput.add(txtStok);
        txtStok.setBounds(570, 40, 270, 30);

        cbxJenis.setBackground(Color.WHITE);
        cbxJenis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elektronik", "Makanan", "Obat", "Pakaian", " " }));
        cbxJenis.setFocusable(false);
        cbxJenis.setOpaque(true);
        PanelInput.add(cbxJenis);
        cbxJenis.setBounds(120, 140, 280, 30);

        btnBatal.setBackground(new java.awt.Color(153, 153, 153));
        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setText("Batal");
        btnBatal.addActionListener(this::btnBatalActionPerformed);
        PanelInput.add(btnBatal);
        btnBatal.setBounds(470, 190, 150, 30);

        panelListBarang.add(PanelInput);
        PanelInput.setBounds(30, 420, 870, 230);

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/storage/icon/store.png"))); // NOI18N
        panelListBarang.add(jLabel30);
        jLabel30.setBounds(40, 10, 70, 70);

        Parent.add(panelListBarang, "card2");

        panelManageUser.setBackground(Color.WHITE);
        panelManageUser.setLayout(null);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 102));
        jLabel21.setText("MANAGE USER");
        panelManageUser.add(jLabel21);
        jLabel21.setBounds(100, 30, 180, 32);

        userTable.setBackground(Color.WHITE);
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Email", "Password", "Role", "Created At", "Status"
            }
        ));
        userTable.setFocusable(false);
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        userTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                userTableComponentShown(evt);
            }
        });
        jScrollPane2.setViewportView(userTable);

        panelManageUser.add(jScrollPane2);
        jScrollPane2.setBounds(24, 118, 490, 510);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("User Table");
        panelManageUser.add(jLabel22);
        jLabel22.setBounds(24, 86, 80, 20);

        jLabel23.setBackground(Color.WHITE);
        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 102));
        jLabel23.setText("User Details");
        jLabel23.setOpaque(true);
        panelManageUser.add(jLabel23);
        jLabel23.setBounds(570, 90, 90, 20);

        panelUserDetails.setBackground(Color.WHITE);
        panelUserDetails.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("USERNAME");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("EMAIL");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("PASSWORD");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("CONFIRM PASSWORD");

        checkPassword.setBackground(Color.WHITE);
        checkPassword.setText(" View Password");
        checkPassword.setFocusPainted(false);
        checkPassword.setFocusable(false);
        checkPassword.addActionListener(this::checkPasswordActionPerformed);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("ROLE");

        boxRole.setBackground(Color.WHITE);
        boxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Admin" }));
        boxRole.setFocusable(false);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("STATUS");

        boxStatus.setBackground(Color.WHITE);
        boxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        boxStatus.setFocusable(false);

        txtEmail.setLabelText("Email");
        txtEmail.addActionListener(this::txtEmailActionPerformed);

        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        txtConPassword.setLabelText("Confirm Password");

        javax.swing.GroupLayout panelUserDetailsLayout = new javax.swing.GroupLayout(panelUserDetails);
        panelUserDetails.setLayout(panelUserDetailsLayout);
        panelUserDetailsLayout.setHorizontalGroup(
            panelUserDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUserDetailsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelUserDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26)
                    .addGroup(panelUserDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)
                        .addGroup(panelUserDetailsLayout.createSequentialGroup()
                            .addGroup(panelUserDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29))
                            .addGap(33, 33, 33)
                            .addGroup(panelUserDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(boxRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(boxStatus, 0, 250, Short.MAX_VALUE)))
                        .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtConPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkPassword, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelUserDetailsLayout.setVerticalGroup(
            panelUserDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUserDetailsLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkPassword)
                .addGap(22, 22, 22)
                .addGroup(panelUserDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxRole, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(panelUserDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(boxStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        panelManageUser.add(panelUserDetails);
        panelUserDetails.setBounds(542, 98, 370, 460);

        btnDeleteUser.setText("Delete");
        btnDeleteUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteUser.setRadius(25);
        btnDeleteUser.addActionListener(this::btnDeleteUserActionPerformed);
        panelManageUser.add(btnDeleteUser);
        btnDeleteUser.setBounds(830, 570, 90, 20);

        btnAddUser.setText("Add User");
        btnAddUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddUser.setRadius(25);
        btnAddUser.addActionListener(this::btnAddUserActionPerformed);
        panelManageUser.add(btnAddUser);
        btnAddUser.setBounds(530, 570, 90, 20);

        btnUpdateUser.setText("Update");
        btnUpdateUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateUser.setRadius(25);
        btnUpdateUser.addActionListener(this::btnUpdateUserActionPerformed);
        panelManageUser.add(btnUpdateUser);
        btnUpdateUser.setBounds(630, 570, 90, 20);

        btnClearUser.setText("Clear");
        btnClearUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClearUser.setRadius(25);
        btnClearUser.addActionListener(this::btnClearUserActionPerformed);
        panelManageUser.add(btnClearUser);
        btnClearUser.setBounds(730, 570, 90, 20);

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/storage/icon/group.png"))); // NOI18N
        panelManageUser.add(jLabel31);
        jLabel31.setBounds(20, 20, 70, 64);

        Parent.add(panelManageUser, "card4");

        getContentPane().add(Parent);
        Parent.setBounds(250, 0, 940, 710);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // konfirmasi logout sebelum balik ke login
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Yakin mau logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            LoginPage login = new LoginPage();
            login.setSize(810, 610);
            login.setLocationRelativeTo(null);
            login.setResizable(false);
            login.setVisible(true);
            this.dispose();
            
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    // tambah data baru ke DB
    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        String SUrl = "jdbc:mysql://localhost:3306/java_user_database";
        String SUser = "root";
        String SPass = "";

        String nama = txtNama.getText().trim();
        String kode = txtKode.getText().trim();
        String jenis = cbxJenis.getSelectedItem().toString();
        String stok = txtStok.getText().trim();
        String info = txtInfo.getText().trim();
        
        if (!validasiFields("tambah")) {
             JOptionPane.showMessageDialog(null, "Semua field harus diisi! Pastikan Stok berisi angka.");
            
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
                
                // cek duplikat kode(unique) sebelum insert
                String cekQuery = "SELECT COUNT(*) FROM barang WHERE kode=?";
                PreparedStatement cekPst = con.prepareStatement(cekQuery);
                cekPst.setString(1, kode);
                ResultSet rs = cekPst.executeQuery();
                rs.next();
                if (rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "Kode barang sudah digunakan!");
                    return;
                }
                
                String query = "INSERT INTO barang (nama, kode, jenis, stok, infoTambah) values (?,? ,? ,?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, nama);
                pst.setString(2, kode);
                pst.setString(3, jenis);
                pst.setInt(4, Integer.parseInt(stok));
                pst.setString(5, info);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
                
                loadTableData();

                txtNama.setText("");
                txtKode.setText("");
                txtStok.setText("");
                txtInfo.setText("");
                
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void tableComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tableComponentShown
        loadTableData();
    }//GEN-LAST:event_tableComponentShown

    // klik row tabel → isi field input dengan data row tersebut (select row)
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
       int index = table.getSelectedRow();
       
       TableModel model = table.getModel();
       
       String nama = model.getValueAt(index, 0).toString();
       txtNama.setText(nama);
       
       String kode = model.getValueAt(index, 1).toString();
       txtKode.setText(kode);
       txtKode.setEditable(false);  // kode tidak boleh diubah saat edit
       
       String jenis = model.getValueAt(index, 2).toString();
       cbxJenis.setSelectedItem(jenis);
       
       String stok = model.getValueAt(index, 3).toString();
       txtStok.setText(stok);
       lblStok.setText("Tambah Stok");
       
       String info = model.getValueAt(index, 4).toString();
       txtInfo.setText(info);
       
       btnEdit.setEnabled(true);
       btnTambah.setEnabled(false);
    }//GEN-LAST:event_tableMouseClicked

    // update data di DB berdasarkan kode
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String SUrl = "jdbc:mysql://localhost:3306/java_user_database";
        String SUser = "root";
        String SPass = "";

        String nama = txtNama.getText().trim();
        String kode = txtKode.getText().trim();
        String jenis = cbxJenis.getSelectedItem().toString();
        String stok = txtStok.getText().trim();
        String info = txtInfo.getText().trim();
        
        if (!validasiFields("edit")) {
             JOptionPane.showMessageDialog(null, "Semua field harus diisi! Pastikan Stok berisi angka.");
            
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
                
                String query = "UPDATE barang SET nama=?, jenis=?, stok=?, infoTambah=? WHERE kode=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, nama);
                pst.setString(2, jenis);
                pst.setInt(3, Integer.parseInt(stok));
                pst.setString(4, info);
                pst.setString(5, kode); //pakai kode sebagai identifier karena unique
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data berhasil diedit!");
                
                loadTableData();
                txtNama.setText("");
                txtKode.setText("");
                txtStok.setText("");
                txtInfo.setText("");
                
                txtKode.setEditable(true);
                btnEdit.setEnabled(false);
                btnTambah.setEnabled(true);
                
            
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    //btn batal untuk reset semua field dan keadaan tombol
    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        txtNama.setText("");
        txtKode.setText("");
        txtStok.setText("");
        txtInfo.setText("");
        cbxJenis.setSelectedIndex(0);
        
        txtKode.setEditable(true);
        table.clearSelection();
        btnEdit.setEnabled(false);
        btnTambah.setEnabled(true);
    }//GEN-LAST:event_btnBatalActionPerformed

    // hapus data dari DB berdasarkan kode
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Yakin mau hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        String SUrl = "jdbc:mysql://localhost:3306/java_user_database";
        String SUser = "root";
        String SPass = "";
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
                
                String query = "delete from barang where kode=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, txtKode.getText().trim());
                pst.executeUpdate();
               
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                loadTableData();
                
                txtNama.setText("");
                txtKode.setText("");
                txtStok.setText("");
                txtInfo.setText("");
                
                btnEdit.setEnabled(false);
                btnTambah.setEnabled(true);
                txtKode.setEditable(true);
                
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        Parent.removeAll();
        Parent.add(panelDashboard);
        Parent.repaint();
        Parent.revalidate();
         
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnListBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListBarangActionPerformed
        Parent.removeAll();
        Parent.add(panelListBarang);
        Parent.repaint();
        Parent.revalidate();
        
        loadTableData();
        btnEdit.setEnabled(false); //edit hanya aktif jika ada suatu row yang di pilih
    }//GEN-LAST:event_btnListBarangActionPerformed

    private void btnManageUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageUserActionPerformed
        Parent.removeAll();
        Parent.add(panelManageUser);
        Parent.repaint();
        Parent.revalidate();
        
        loadTableUser();
        btnUpdateUser.setEnabled(false);
    }//GEN-LAST:event_btnManageUserActionPerformed

    private void checkPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPasswordActionPerformed
        if (checkPassword.isSelected()) {
            txtPassword.setEchoChar((char) 0);
            txtConPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('*');
            txtConPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_checkPasswordActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        String SUrl = "jdbc:mysql://localhost:3306/java_user_database";
        String SUser = "root";
        String SPass = "";
        
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String (txtPassword.getPassword()).trim();
        String conPassword = new String (txtConPassword.getPassword()).trim();
        String role = boxRole.getSelectedItem().toString();
        String status = boxStatus.getSelectedItem().toString();
        
        if (username.isEmpty() || email.isEmpty() ||  password.isEmpty() || conPassword.isEmpty() || role.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua Field Harus Diisi!");
            return;
        }
        
        if (!password.equals(conPassword)) {
            JOptionPane.showMessageDialog(null, "Password Harus Sama!");
            return;
        }
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            
            PreparedStatement cek = con.prepareStatement("SELECT COUNT(*) FROM users WHERE username=? OR email=?");
            cek.setString(1, username);
            cek.setString(2, email);
            ResultSet rs = cek.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Username atau email sudah digunakan!");
                return;
            }
            
            String query = "INSERT INTO users (username, email, password, role, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4,role);
            pst.setString(5, status);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "User berhasil ditambahkan!");
            loadTableUser();
            
            txtUsername.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtConPassword.setText("");
            boxRole.setSelectedIndex(0);
            boxStatus.setSelectedIndex(0);
               
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed

    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
 
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void userTableComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_userTableComponentShown
        loadTableUser();
    }//GEN-LAST:event_userTableComponentShown

    private void btnClearUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearUserActionPerformed
        txtUsername.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtConPassword.setText("");

        boxRole.setSelectedIndex(0);
        boxStatus.setSelectedIndex(0);
        
        userTable.clearSelection();
        btnAddUser.setEnabled(true);
        btnUpdateUser.setEnabled(false);
        
        txtConPassword.setEnabled(true);
        txtConPassword.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnClearUserActionPerformed

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        int index = userTable.getSelectedRow();
        TableModel model = userTable.getModel();
        if (index < 0) return;
        
        String username = model.getValueAt(index, 0).toString();
        txtUsername.setText(username);
        selectedUsername = username;
        
        String email = model.getValueAt(index, 1).toString();
        txtEmail.setText(email);
        
        String password = model.getValueAt(index, 2).toString();
        txtPassword.setText(password);
        
        String role = model.getValueAt(index, 3).toString();
        boxRole.setSelectedItem(role);
        
        String status = model.getValueAt(index, 5).toString();
        boxStatus.setSelectedItem(status);
        
        btnAddUser.setEnabled(false);
        btnUpdateUser.setEnabled(true);
        
        txtConPassword.setEnabled(false);
        txtConPassword.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_userTableMouseClicked

    private void btnUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUserActionPerformed
        String SUrl = "jdbc:mysql://localhost:3306/java_user_database";
        String SUser = "root";
        String SPass = "";
        
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String (txtPassword.getPassword()).trim();
        String role = boxRole.getSelectedItem().toString();
        String status = boxStatus.getSelectedItem().toString();
        
        if (username.isEmpty() || email.isEmpty() ||  password.isEmpty() || role.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua Field Harus Diisi!");
            return;
        }
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            
            String query = "UPDATE users SET username=?, email=?, password=?, role=?, status=? WHERE username=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, role);
            pst.setString(5, status);
            pst.setString(6, selectedUsername);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
            loadTableUser();
           
            txtUsername.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtConPassword.setText("");
            boxRole.setSelectedIndex(0);
            boxStatus.setSelectedIndex(0);     
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnUpdateUserActionPerformed

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Yakin mau hapus user ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        String SUrl = "jdbc:mysql://localhost:3306/java_user_database";
        String SUser = "root";
        String SPass = "";
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
                
                String query = "DELETE FROM users WHERE username =?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, txtUsername.getText().trim());
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                loadTableUser();
                
                txtUsername.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                txtConPassword.setText("");
                boxRole.setSelectedIndex(0);
                boxStatus.setSelectedIndex(0);
                
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnDeleteUserActionPerformed

    public static void main(String args[]) {}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel Parent;
    private javax.swing.JComboBox<String> boxRole;
    private javax.swing.JComboBox<String> boxStatus;
    private storage.component.RoundedButton btnAddUser;
    private javax.swing.JButton btnBatal;
    private storage.component.RoundedButton btnClearUser;
    private javax.swing.JButton btnDashboard;
    private storage.component.RoundedButton btnDeleteUser;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnListBarang;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageUser;
    private javax.swing.JButton btnTambah;
    private storage.component.RoundedButton btnUpdateUser;
    private javax.swing.JComboBox<String> cbxJenis;
    private javax.swing.JCheckBox checkPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblStok;
    private javax.swing.JLabel lowStockCount;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelListBarang;
    private javax.swing.JPanel panelLowStok;
    private javax.swing.JPanel panelManageUser;
    private javax.swing.JPanel panelTotalItem;
    private javax.swing.JPanel panelUserDetails;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalItemCount;
    private storage.component.PasswordField txtConPassword;
    private storage.component.TextField txtEmail;
    private javax.swing.JTextField txtInfo;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    private storage.component.PasswordField txtPassword;
    private javax.swing.JTextField txtStok;
    private storage.component.TextField txtUsername;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
