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
 */
package usu.perpustakaan.buku.widget;

import com.mysql.jdbc.Driver;
import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import usu.perpustakaan.buku.constan.FieldConstan;
import usu.perpustakaan.buku.data.DefaultBookGuest;
import usu.perpustakaan.buku.data.MySQLAdmin;
import usu.perpustakaan.buku.sql.BookLibrarySQL;
import usu.perpustakaan.buku.util.StringUtil;
import usu.widget.Form;
import usu.widget.GlassPane;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author  usu
 */
public class GuestForm extends Form {

    private static final long serialVersionUID = -1;
    private Connection connection;
    private ExecutorService executor;
    private Properties prop;
    private MySQLAdmin admin;
    private GraphicsDevice gd;

    /**
     * 
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public GuestForm() throws IOException, ClassNotFoundException, SQLException {

        super();

        prop = new Properties();
        prop.loadFromXML(new FileInputStream("setting\\database.xml"));

        ObjectInputStream stream = new ObjectInputStream(new FileInputStream("setting\\admin.ebl"));
        admin = (MySQLAdmin) stream.readObject();
        stream.close();

        new Driver();
        connection = DriverManager.getConnection("jdbc:mysql://" +
                prop.getProperty("host") + ":" + prop.getProperty("port") + "/" + prop.getProperty("database"),
                StringUtil.getStringFromArrayChar(admin.getUsername("NESIAOKTIANA")),
                StringUtil.getStringFromArrayChar(admin.getPassword("NESIAOKTIANA")));

        executor = Executors.newCachedThreadPool();

        setIconImage(new ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Operator2.png")).getImage());

        initComponents();

        getRootPane().getInputMap().put(KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE), "CLOSE");
        getRootPane().getActionMap().put("CLOSE", new AbstractAction() {

            private static final long serialVersionUID = -1;

            @Override
            public void actionPerformed(ActionEvent e) {
                ((GlassPane) getGlassPane()).hideComponent();
            }
        });

        initActions();
        new LoadBook(null).execute();
        gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    }

    @Override
    public void exit() {
        if (showConfirm("Anda yakin?") != JOptionPane.OK_OPTION) {
            return;
        }
        executor.shutdown();
        super.exit();
    }

    private void initActions() {
        manageBook.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadBook(null).execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageBook.getButtonFilter().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadBook(manageBook.getBookGuest()).execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
    }

    /**
     * 
     * @param message
     * @return
     */
    public int showConfirm(String message) {
        return JOptionPane.showConfirmDialog(this, message, "Konfirmasi", JOptionPane.YES_NO_OPTION);
    }

    /**
     * show error message
     * @param meesage
     */
    public void showErrorMessage(String meesage) {
        WidgetUtilities.showErrorMessage(this, meesage);
    }

    /**
     * 
     * @param meesage
     */
    public void showErrorMessage(Throwable meesage) {
        WidgetUtilities.showErrorMessage(this, meesage.getMessage());
    }

    /**
     * 
     * @param text
     */
    public void showMessage(String text) {
        JOptionPane.showMessageDialog(this, text);
    }

    private void showTransition(JComponent com) {
        try {
            ((GlassPane) getGlassPane()).startTransition(com);
        } catch (AWTException ex) {
        }
    }

    private void hideComponentFromGlasspane() {
        ((GlassPane) getGlassPane()).hideComponent();
    }

    private void showComponentInGlasspane(JComponent comp) {
        ((GlassPane) getGlassPane()).showComponent(comp);
    }

    private void showPanel(JPanel panel, String layout) {
        if (panel.isVisible()) {
            return;
        } else {
            showTransition(panelUtama);
            ((CardLayout) panelTengah.getLayout()).show(panelTengah, layout);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        glassPane = new usu.widget.GlassPane();
        about = new usu.perpustakaan.buku.widget.About();
        panelUtama = new usu.perpustakaan.buku.widget.template.PanelBlackGreen();
        labelTitleBody = new javax.swing.JLabel();
        panelTengah = new javax.swing.JPanel();
        manageBook = new usu.perpustakaan.buku.widget.guest.ManageBook();
        toolBar = new usu.perpustakaan.buku.widget.template.ToolbarBlackGreen();
        buttonFullScreen = new usu.widget.ButtonGlass();
        buttonAbout = new usu.widget.ButtonGlass();
        usu.perpustakaan.buku.widget.template.ToolbarBlackGreen statusBar = new usu.perpustakaan.buku.widget.template.ToolbarBlackGreen();
        labelStatusBar = new javax.swing.JLabel();
        labelProgress = new javax.swing.JLabel();
        progressBar = new usu.perpustakaan.buku.widget.template.ProgressBarBlackGreen();

        setGlassPane(glassPane);
        glassPane.setAlphaDisable(10);
        glassPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 75, 75));

        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });

        setTitle("e'BookLibrary - Guest");
        setAnimationHide(usu.widget.constan.Animation.HIDE_TO_LEFT);
        setAnimationShow(usu.widget.constan.Animation.SHOW_FROM_BOTTOM);
        setLocationRelativeTo(null);
        setMinimumSize(new java.awt.Dimension(800, 640));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelTitleBody.setFont(new java.awt.Font("Tahoma", 1, 24));
        labelTitleBody.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleBody.setText("e'BookLibrary");

        panelTengah.setOpaque(false);
        panelTengah.setLayout(new java.awt.CardLayout());
        panelTengah.add(manageBook, "card2");

        javax.swing.GroupLayout panelUtamaLayout = new javax.swing.GroupLayout(panelUtama);
        panelUtama.setLayout(panelUtamaLayout);
        panelUtamaLayout.setHorizontalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTengah, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addComponent(labelTitleBody))
                .addContainerGap())
        );
        panelUtamaLayout.setVerticalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleBody)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTengah, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panelUtama, java.awt.BorderLayout.CENTER);

        toolBar.setRollover(true);
        toolBar.setToolTipText("Tool Bar");

        buttonFullScreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Home 2.png"))); // NOI18N
        buttonFullScreen.setMnemonic('L');
        buttonFullScreen.setText("Layar Penuh");
        buttonFullScreen.setToolTipText("Klik untuk merubah ukuran window menjadi full screen");
        buttonFullScreen.setFocusable(false);
        buttonFullScreen.setRoundRect(true);
        buttonFullScreen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonFullScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFullScreenActionPerformed(evt);
            }
        });
        toolBar.add(buttonFullScreen);

        buttonAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Pics 1.png"))); // NOI18N
        buttonAbout.setMnemonic('T');
        buttonAbout.setText("Tentang");
        buttonAbout.setToolTipText("Klik untuk menampilkan pane tentang saya");
        buttonAbout.setFocusable(false);
        buttonAbout.setRoundRect(true);
        buttonAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAboutActionPerformed(evt);
            }
        });
        toolBar.add(javax.swing.Box.createHorizontalGlue());
        toolBar.add(buttonAbout);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        statusBar.setRollover(true);
        statusBar.setToolTipText("Status Bar");

        labelStatusBar.setText("e'BookLibrary");
        labelStatusBar.setToolTipText("Status Bar Text");
        statusBar.add(labelStatusBar);

        labelProgress.setToolTipText("Status Progress Text");
        statusBar.add(javax.swing.Box.createHorizontalGlue());
        statusBar.add(labelProgress);

        progressBar.setToolTipText("Progress Bar");
        progressBar.setMaximumSize(new java.awt.Dimension(100, 14));
        statusBar.add(progressBar);

        getContentPane().add(statusBar, java.awt.BorderLayout.PAGE_END);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    exit();
}//GEN-LAST:event_formWindowClosing

private void buttonAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAboutActionPerformed
    showComponentInGlasspane(about);
}//GEN-LAST:event_buttonAboutActionPerformed

private void buttonFullScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFullScreenActionPerformed
//GEN-LAST:event_buttonFullScreenActionPerformed
        if (gd.isFullScreenSupported()) {
            if (gd.getFullScreenWindow() != this) {
                gd.setFullScreenWindow(this);
                buttonFullScreen.setText("Keluar Layar Penuh");
                buttonFullScreen.setToolTipText("Klik untuk keluar dari mode FullScreen");
            } else {
                gd.setFullScreenWindow(null);
                buttonFullScreen.setText("Layar Penuh");
                buttonFullScreen.setToolTipText("Klik untuk masuk ke mode FullScreen");
            }
        } else {
            showErrorMessage("Layar penuh tak didukung oleh sistem operasi snda");
        }

    }

private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
    hideComponentFromGlasspane();
}//GEN-LAST:event_aboutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.perpustakaan.buku.widget.About about;
    usu.widget.ButtonGlass buttonAbout;
    usu.widget.ButtonGlass buttonFullScreen;
    usu.widget.GlassPane glassPane;
    javax.swing.JLabel labelProgress;
    javax.swing.JLabel labelStatusBar;
    javax.swing.JLabel labelTitleBody;
    usu.perpustakaan.buku.widget.guest.ManageBook manageBook;
    javax.swing.JPanel panelTengah;
    usu.perpustakaan.buku.widget.template.PanelBlackGreen panelUtama;
    usu.perpustakaan.buku.widget.template.ProgressBarBlackGreen progressBar;
    usu.perpustakaan.buku.widget.template.ToolbarBlackGreen toolBar;
    // End of variables declaration//GEN-END:variables

    /**
     * loading buku
     */
    public class LoadBook extends SwingWorker<List<DefaultBookGuest>, String> {

        private ArrayList<DefaultBookGuest> array;
        private ResultSet result;

        private LoadBook(DefaultBookGuest bookGuest) throws SQLException {
            super();
            if (bookGuest == null) {
                result = BookLibrarySQL.getResultBookGuest(connection);
            } else {
                result = BookLibrarySQL.getResultBookGuest(connection, bookGuest);
            }
            array = new ArrayList<DefaultBookGuest>();

            manageBook.getModel().removeAllElements();
            manageBook.setFreeze(true);
            labelProgress.setText("");
            labelStatusBar.setText("Loading Book");
            progressBar.setIndeterminate(true);
        }

        @Override
        protected List<DefaultBookGuest> doInBackground() throws Exception {
            while (result.next()) {
                DefaultBookGuest book = new DefaultBookGuest();
                book.setAuthor(BookLibrarySQL.getBookAuthor(connection, result.getString(FieldConstan.TABEL_BUKU.ID_PENGARANG)));
                book.setKind(BookLibrarySQL.getBookKind(connection, result.getString(FieldConstan.TABEL_BUKU.ID_JENIS)));
                book.setPublisher(BookLibrarySQL.getBookPublisher(connection, result.getString(FieldConstan.TABEL_BUKU.ID_PENERBIT)));
                book.setId(result.getString(FieldConstan.TABEL_BUKU.ID_BUKU));
                book.setTitle(result.getString(FieldConstan.TABEL_BUKU.JUDUL_BUKU));
                book.setBorrow(result.getBoolean(FieldConstan.TABEL_STATUS_BUKU.SEDANG_DIPINJAM));
                book.setTotalBorrow(result.getInt(FieldConstan.TABEL_STATUS_BUKU.TOTAL_DIPINJAM));
                publish(book.getId());
                array.add(book);
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        manageBook.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GuestForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(GuestForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        manageBook.setFreeze(false);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        progressBar.setIndeterminate(false);
                        try {
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(GuestForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load buku " + s);
            }
        }
    }
}
