/*
 * DILARANG MENGHAPUS ATAU MENGEDIT COPYRIGHT INI.
 * 
 * Copyright 2008 echo.khannedy@gmail.com. 
 * All rights reserved.
 * 
 * Semua isi dalam file ini adalah hak milik dari echo.khannedy@gmail.com
 * Anda tak diperkenankan untuk menggunakan file atau mengubah file
 * ini kecuali anda tidak menghapus atau merubah lisence ini.
 * 
 * File ini dibuat menggunakan :
 * IDE        : NetBeans
 * NoteBook   : Acer Aspire 5920G
 * OS         : Windows Vista
 * Java       : Java 1.6
 * 
 */
package usu.perpustakaan.buku.widget;

import com.mysql.jdbc.Driver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import usu.perpustakaan.buku.data.MySQLAdmin;
import usu.perpustakaan.buku.util.StringUtil;
import usu.widget.GlassPane;

/**
 *
 * @author  usu
 */
public class AdminLogIn extends usu.widget.Form implements DocumentListener {

    private static final long serialVersionUID = -1;
    private Properties prop;
    private Connection connection;
    private AdminForm form;

    /** Creates new form AdminLogIn
     * @throws java.io.IOException 
     */
    public AdminLogIn() throws IOException {
        super();
        prop = new Properties();
        prop.loadFromXML(new FileInputStream("setting\\database.xml"));
        setIconImage(new ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Admin2.png")).getImage());
        initComponents();
        textUsername.getDocument().addDocumentListener(this);
        textPassword.getDocument().addDocumentListener(this);

        form = new AdminForm();
    }

    /**
     * mengecek password dan username
     * @return
     */
    private boolean cek() {
        try {
            new Driver();
            String host = prop.getProperty("host");
            String port = prop.getProperty("port");
            String database = prop.getProperty("database");
            connection = DriverManager.getConnection("jdbc:mysql://" +
                    host + ":" + port + "/" + database,
                    textUsername.getText(), getStringFromCharArray(textPassword.getPassword()));
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param array
     * @return
     */
    private static String getStringFromCharArray(char[] array) {
        return StringUtil.getStringFromArrayChar(array);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usu.widget.GlassPane glassPane = new usu.widget.GlassPane();
        about = new usu.perpustakaan.buku.widget.About();
        traceArea = new javax.swing.JTextArea();
        usu.perpustakaan.buku.widget.template.ViewPortTransparan viewPort = new usu.perpustakaan.buku.widget.template.ViewPortTransparan();
        usu.perpustakaan.buku.widget.template.PanelBlackGreen panelUtama = new usu.perpustakaan.buku.widget.template.PanelBlackGreen();
        javax.swing.JLabel labelAdminLogIn = new javax.swing.JLabel();
        javax.swing.JLabel labelGua = new javax.swing.JLabel();
        usu.perpustakaan.buku.widget.template.PanelTranparan panelTranparan = new usu.perpustakaan.buku.widget.template.PanelTranparan();
        usu.widget.Label label1 = new usu.widget.Label();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        textUsername = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        textPassword = new usu.perpustakaan.buku.widget.template.PasswordBox();
        buttonLogIn = new usu.widget.ButtonGlass();
        usu.widget.ButtonGlass buttonReset = new usu.widget.ButtonGlass();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        usu.widget.ButtonGlass buttonAbout = new usu.widget.ButtonGlass();

        setGlassPane(glassPane);
        glassPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 25));

        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });

        traceArea.setColumns(20);
        traceArea.setEditable(false);
        traceArea.setFont(new java.awt.Font("Tahoma", 1, 11));
        traceArea.setLineWrap(true);
        traceArea.setRows(5);
        traceArea.setText("Masukkan username/id dan password yang sama dengan MySQL...");
        traceArea.setWrapStyleWord(true);
        traceArea.setFocusable(false);
        traceArea.setOpaque(false);

        viewPort.setFocusable(false);
        viewPort.setView(traceArea);

        setTitle("e'BookLibrary - Admin LogIn");
        setAnimationHide(usu.widget.constan.Animation.HIDE_TO_BOTTOM);
        setAnimationShow(usu.widget.constan.Animation.SHOW_FROM_LEFT);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setDefaultButton(buttonLogIn);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelAdminLogIn.setFont(new java.awt.Font("Tahoma", 1, 24));
        labelAdminLogIn.setForeground(new java.awt.Color(255, 255, 255));
        labelAdminLogIn.setText("e'BookLibrary");

        labelGua.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelGua.setText("By Eko Kurniawan Khannedy");

        panelTranparan.setAlpha(0.0F);

        label1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label1.setIconReflec(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/admin.png"))); // NOI18N

        jLabel1.setDisplayedMnemonic('U');
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username");

        jLabel2.setDisplayedMnemonic('P');
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");

        usu.widget.util.WidgetUtilities.setAutomaticPopUpMenu(textUsername);
        textUsername.setForeground(new java.awt.Color(255, 255, 255));
        textUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textUsername.setToolTipText("Masukkan username");
        textUsername.setFocusAccelerator('U');
        textUsername.setFont(new java.awt.Font("Tahoma", 1, 11));
        textUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textUsernameActionPerformed(evt);
            }
        });

        textPassword.setForeground(new java.awt.Color(255, 255, 255));
        textPassword.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textPassword.setToolTipText("Masukkan password");
        textPassword.setFocusAccelerator('P');
        textPassword.setFont(new java.awt.Font("Tahoma", 1, 11));
        textPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPasswordActionPerformed(evt);
            }
        });

        buttonLogIn.setMnemonic('M');
        buttonLogIn.setText("Masuk");
        buttonLogIn.setToolTipText("Klik untuk masuk ke form utama");
        buttonLogIn.setEnabled(false);
        buttonLogIn.setRoundRect(true);
        buttonLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogInActionPerformed(evt);
            }
        });

        buttonReset.setMnemonic('R');
        buttonReset.setText("Reset");
        buttonReset.setToolTipText("Klik untuk mereset textbox username dan password");
        buttonReset.setRoundRect(true);
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setFocusable(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setViewport(viewPort);

        javax.swing.GroupLayout panelTranparanLayout = new javax.swing.GroupLayout(panelTranparan);
        panelTranparan.setLayout(panelTranparanLayout);
        panelTranparanLayout.setHorizontalGroup(
            panelTranparanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranparanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranparanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addGroup(panelTranparanLayout.createSequentialGroup()
                        .addGroup(panelTranparanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTranparanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                            .addComponent(textPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))
                    .addGroup(panelTranparanLayout.createSequentialGroup()
                        .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTranparanLayout.setVerticalGroup(
            panelTranparanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranparanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranparanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                    .addGroup(panelTranparanLayout.createSequentialGroup()
                        .addGroup(panelTranparanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTranparanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTranparanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
                .addContainerGap())
        );

        buttonAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Pics 1.png"))); // NOI18N
        buttonAbout.setMnemonic('T');
        buttonAbout.setText("Tentang");
        buttonAbout.setToolTipText("Klik untuk menampilkan pane tentang saya");
        buttonAbout.setFocusPainted(false);
        buttonAbout.setRoundRect(true);
        buttonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAboutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUtamaLayout = new javax.swing.GroupLayout(panelUtama);
        panelUtama.setLayout(panelUtamaLayout);
        panelUtamaLayout.setHorizontalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUtamaLayout.createSequentialGroup()
                        .addComponent(labelAdminLogIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                        .addComponent(buttonAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelGua, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelTranparan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelUtamaLayout.setVerticalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAdminLogIn)
                    .addComponent(buttonAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTranparan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelGua)
                .addContainerGap())
        );

        getContentPane().add(panelUtama, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-560)/2, (screenSize.height-418)/2, 560, 418);
    }// </editor-fold>//GEN-END:initComponents

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
// TODO add your handling code here:
    exit();
}//GEN-LAST:event_formWindowClosing

private void buttonAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAboutActionPerformed
// TODO add your handling code here:
    ((GlassPane) getGlassPane()).showComponent(about);
}//GEN-LAST:event_buttonAboutActionPerformed

private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
// TODO add your handling code here:
    ((GlassPane) getGlassPane()).hideComponent();
}//GEN-LAST:event_aboutActionPerformed

private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
// TODO add your handling code here:
    reset();
}//GEN-LAST:event_buttonResetActionPerformed

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
// TODO add your handling code here:
    textUsername.requestFocusInWindow();
}//GEN-LAST:event_formWindowActivated

private void textUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textUsernameActionPerformed
// TODO add your handling code here:
    textUsername.transferFocus();
}//GEN-LAST:event_textUsernameActionPerformed

private void buttonLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogInActionPerformed
    form.setConnection(connection);
    form.setVisible(true);
    setVisible(false);
    {
        ObjectOutputStream stream = null;
        try {
            stream = new ObjectOutputStream(new FileOutputStream("setting/admin.ebl"));
            stream.writeObject(new MySQLAdmin(textUsername.getText().toCharArray(),
                    textPassword.getPassword(), "NESIAOKTIANA"));
        } catch (IOException ex) {
            Logger.getLogger(AdminLogIn.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ex) {
                    Logger.getLogger(AdminLogIn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}//GEN-LAST:event_buttonLogInActionPerformed

private void textPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPasswordActionPerformed
// TODO add your handling code here:
    if (buttonLogIn.isEnabled()) {
        buttonLogInActionPerformed(evt);
    }
}//GEN-LAST:event_textPasswordActionPerformed

    /**
     * reset teks
     */
    public void reset() {
        textPassword.setText("");
        textUsername.setText("");
        textUsername.requestFocusInWindow();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.perpustakaan.buku.widget.About about;
    usu.widget.ButtonGlass buttonLogIn;
    usu.perpustakaan.buku.widget.template.PasswordBox textPassword;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textUsername;
    javax.swing.JTextArea traceArea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (!cek()) {
            traceArea.setText("Username/id atau password salah\n\n" +
                    "Anda harus memasukkan username dan password " +
                    "yang sama dengan username dan password MySQL\n\n" +
                    "Jika anda lupa username dan passwordnya maka anda tak akan bisa menggunakan " +
                    "e'BookLibrary lagi");
            buttonLogIn.setEnabled(false);
        } else {
            traceArea.setText("Username dan password BENAR\n\nKlik tombol masuk untuk masuk ke form utama\n");
            buttonLogIn.setEnabled(true);
            buttonLogIn.requestFocusInWindow();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        insertUpdate(e);
    }
}
