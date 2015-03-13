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

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import usu.perpustakaan.buku.data.DefaultOperatorActivity;
import usu.perpustakaan.buku.data.DefaultSettingLibrary;
import usu.perpustakaan.buku.data.template.Operator;
import usu.perpustakaan.buku.data.template.OperatorActivity;
import usu.perpustakaan.buku.data.template.SettingLibrary;
import usu.perpustakaan.buku.sql.BookLibrarySQL;
import usu.widget.Form;
import usu.widget.GlassPane;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author  usu
 */
public class AdminForm extends Form {

    private static final long serialVersionUID = -1;
    private Connection connection;
    private ExecutorService executor;
    private Toolkit toolkit;
    private int tempInteger;
    private String tempString;
    private boolean tempBoolean;
    private SettingLibrary settingLibrary;

    /** Creates new form BeanForm */
    public AdminForm() {

        executor = Executors.newCachedThreadPool();
        toolkit = Toolkit.getDefaultToolkit();

        setIconImage(new ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Admin2.png")).getImage());

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
    }

    /**
     * setting koneksi mysql
     * @param connection
     */
    void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void exit() {
        if (showConfirm("Anda Yakin?") != JOptionPane.OK_OPTION) {
            return;
        }
        executor.shutdown();
        super.exit();
    }

    /**
     * deklarasi aksi
     */
    private void initActions() {
        manageOperator.getButtonAdd().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showComponentInGlasspane(addOperator);
            }
        });
        manageOperator.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadOperator(e);
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageOperator.getButtonEdit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEditManageOperator(e);
            }
        });
        manageOperator.getButtonDelete().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOperator(e);
            }
        });

        addOperator.getButtonCancel().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hideComponentFromGlasspane(e);
                addOperator.reset();
            }
        });
        addOperator.getButtonSubmit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSubmitAddOperator(e);
            }
        });

        editOperator.getButtonCancel().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hideComponentFromGlasspane(e);
            }
        });
        editOperator.getButtonSubmit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSubmitEditOperator(e);
            }
        });

        manageMySQL.getButtonSave().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getConnectionFromManageMySQL(e);
                manageMySQL.getButtonSave().setEnabled(false);
            }
        });
        manageLibrary.getButtonReset().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetManageLibrary();
            }
        });
        manageLibrary.getButtonSave().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveManageLibrary();
            }
        });

        panelHome.getButtonPerpustakaan().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (manageLibrary.isVisible()) {
                    return;
                }
                resetManageLibrary();
                showPanel(manageLibrary, "LIBRARY");
                setTitleBody("Perpustakaan");
            }
        });
        panelHome.getButtonDatabase().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (manageOperator.isVisible()) {
                    return;
                }
                manageMySQL.refresh();
                showPanel(manageMySQL, "MYSQL");
                setTitleBody("Database");
            }
        });
        panelHome.getButtonOperator().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (manageOperator.isVisible()) {
                    return;
                }
                try {
                    loadOperator(e);
                    showPanel(manageOperator, "OPERATOR");
                    setTitleBody("Operator");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        panelHome.getButtonAktivitas().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadOperatorActivity().execute();
                    showPanel(manageOperatorActivity, "MANAGE_OPERATOR_ACTIVITY");
                    setTitleBody("Aktivitas Operator");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });

        manageOperatorActivity.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadOperatorActivity().execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageOperatorActivity.getButtonDelete().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOperatorActivity();
            }
        });
    }

    private void deleteOperatorActivity() {
        if (showConfirm("Anda yakin akan menghapus seluruh aktivitas operator?") != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            BookLibrarySQL.deleteOperatorActivity(connection);
            manageOperatorActivity.getModel().removeAllElements();
            showMessage("Database aktivitas operator telah dikosongkan");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    /**
     * 
     * @param meesage
     */
    public void showErrorMessage(Throwable meesage) {
        WidgetUtilities.showErrorMessage(this, meesage.getMessage());
    }

    /**
     * save manage library setting
     */
    private void saveManageLibrary() {
        try {
            if (manageLibrary.getTextMaksBorrorBook().getValue() == null ||
                    manageLibrary.getTextMaxBorrowDay().getValue() == null ||
                    manageLibrary.getTextPayforLate().getValue() == null) {
                showErrorMessage("Data belum lengkap");
                return;
            }
            settingLibrary = new DefaultSettingLibrary();
            settingLibrary.setMaxBorrowBook(Integer.valueOf(manageLibrary.getTextMaksBorrorBook().getValue().toString()).intValue());
            settingLibrary.setMaxBorrowDay(Integer.valueOf(manageLibrary.getTextMaxBorrowDay().getValue().toString()).intValue());
            settingLibrary.setPayOfLate(Integer.valueOf(manageLibrary.getTextPayforLate().getValue().toString()).intValue());
            if (settingLibrary.getMaxBorrowBook() < 1 || settingLibrary.getMaxBorrowDay() < 1 || settingLibrary.getPayOfLate() < 1) {
                showErrorMessage("Nilai semua textfield tak boleh kurang dari 1");
                return;
            } else {
                BookLibrarySQL.saveSetting(connection, settingLibrary);
                showMessage("Proses penyimpanan berhasil");
            }
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    /**
     * reset manage library
     */
    private void resetManageLibrary() {
        try {
            settingLibrary = BookLibrarySQL.getSettingLibrary(connection);
            if (settingLibrary.getMaxBorrowBook() == 0) {
                showErrorMessage("Tak ada setting dalam database, silahkan buat yang baru");
            } else {
                manageLibrary.getTextMaksBorrorBook().setValue(settingLibrary.getMaxBorrowBook());
                manageLibrary.getTextMaxBorrowDay().setValue(settingLibrary.getMaxBorrowDay());
                manageLibrary.getTextPayforLate().setValue(settingLibrary.getPayOfLate());
            }
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }

    }

    private int showConfirm(String message) {
        return JOptionPane.showConfirmDialog(this, message, "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);
    }

    private void showErrorMessage(String string) {
        WidgetUtilities.showErrorMessage(this, string);
    }

    private void showMessage(String teks) {
        JOptionPane.showMessageDialog(this, teks);
    }

    /**
     * 
     * @param panel
     * @param card
     */
    public void showPanel(JPanel panel, String card) {
        if (panel.isVisible()) {
            return;
        } else {
            showTransition(panelUtama);
            ((CardLayout) panelTengah.getLayout()).show(panelTengah, card);
        }
    }

    /**
     * 
     * @param com
     */
    private void showTransition(JComponent com) {
        try {
            ((GlassPane) getGlassPane()).startTransition(com);
        } catch (AWTException ex) {
        }
    }

    /**
     * 
     * @param e
     */
    private void deleteOperator(ActionEvent e) {
        tempInteger = manageOperator.getTable().getSelectedRowCount();
        if (tempInteger == 0) {
            return;
        }
        if (tempInteger > 1) {
            if (JOptionPane.showConfirmDialog(this, "Anda yakin akan menghapus " +
                    tempInteger + " operator?", "Hapus Operator", JOptionPane.OK_CANCEL_OPTION) ==
                    JOptionPane.OK_OPTION) {
                tempBoolean = true;
            } else {
                tempBoolean = false;
            }
        } else {
            if (JOptionPane.showConfirmDialog(this, "Anda yakin akan menghapus operator yang terseleksi?",
                    "Hapus Operator", JOptionPane.OK_CANCEL_OPTION) ==
                    JOptionPane.OK_OPTION) {
                tempBoolean = true;
            } else {
                tempBoolean = false;
            }
        }
        if (tempBoolean) {
            WidgetUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    while (manageOperator.getTable().getSelectedRowCount() > 0) {
                        try {
                            tempInteger = manageOperator.getTable().convertRowIndexToModel(
                                    manageOperator.getTable().getSelectionModel().getMaxSelectionIndex());
                            tempString = manageOperator.getModelTable().getOperator(tempInteger).getId();
                            BookLibrarySQL.deleteOperator(connection, tempString);
                            manageOperator.getModelTable().removeOperator(tempInteger);
                        } catch (SQLException ex) {
                            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
                        } catch (IndexOutOfBoundsException e) {
                        }
                    }
                    manageOperator.getModelTable().fireTableDataChanged();
                    manageOperator.getTable().revalidate();
                }
            });
        }
    }

    /**
     * 
     * @param e
     */
    private void buttonSubmitEditOperator(ActionEvent e) {
        if (editOperator.isOperatorValid()) {
            Operator o = editOperator.getOperator();
            try {
                BookLibrarySQL.updateOperator(connection, o, editOperator.getOperatorId());
                tempInteger = manageOperator.getTable().convertRowIndexToModel(manageOperator.getTable().getSelectedRow());
                manageOperator.getModelTable().setOperatorAt(o, tempInteger);
                hideComponentFromGlasspane(e);
            } catch (SQLException ex) {
                WidgetUtilities.showErrorMessage(this, ex.getMessage());
            }
        } else {
            toolkit.beep();
            WidgetUtilities.showErrorMessage(this, editOperator.getInvalidMessage());
        }
    }

    /**
     * 
     * @param e
     */
    private void hideComponentFromGlasspane(ActionEvent e) {
        ((GlassPane) getGlassPane()).hideComponent();
    }

    /**
     * 
     * @param comp
     */
    private void showComponentInGlasspane(JComponent comp) {
        ((GlassPane) getGlassPane()).showComponent(comp);
    }

    /**
     * 
     * @param e
     */
    private void buttonEditManageOperator(ActionEvent e) {
        if (manageOperator.getTable().getSelectedRowCount() == 0) {
            return;
        }
        tempInteger = manageOperator.getTable().convertRowIndexToModel(manageOperator.getTable().getSelectedRow());
        Operator o = manageOperator.getModelTable().getOperator(tempInteger);
        editOperator.setOperator(o);
        showComponentInGlasspane(editOperator);
    }

    /**
     * 
     * @param e
     */
    private void buttonSubmitAddOperator(ActionEvent e) {
        if (addOperator.isOperatorValid()) {
            Operator o = addOperator.getOperator();
            try {
                BookLibrarySQL.insertOperator(connection, o);
                manageOperator.getModelTable().addOperator(o);
                ((GlassPane) getGlassPane()).hideComponent();
                addOperator.reset();
            } catch (SQLException ex) {
                WidgetUtilities.showErrorMessage(this, ex.getMessage());
            }
        } else {
            toolkit.beep();
            WidgetUtilities.showErrorMessage(this, addOperator.getInvalidMessage());
        }
    }

    /**
     * 
     * @param e
     */
    private void getConnectionFromManageMySQL(ActionEvent e) {
        try {
            Connection c = manageMySQL.getConnection();
            connection = c;
        } catch (NullPointerException ex) {
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
        addOperator = new usu.perpustakaan.buku.widget.admin.AddOperator();
        editOperator = new usu.perpustakaan.buku.widget.admin.EditOperator();
        usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonLibrarySetting = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();
        usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonMySQLSetting = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();
        panelUtama = new usu.perpustakaan.buku.widget.template.PanelBlackGreen();
        labelTitleBody = new javax.swing.JLabel();
        panelTengah = new javax.swing.JPanel();
        panelHome = new usu.perpustakaan.buku.widget.admin.PanelHome();
        manageOperator = new usu.perpustakaan.buku.widget.admin.ManageOperator();
        manageMySQL = new usu.perpustakaan.buku.widget.admin.ManageMySQL();
        manageLibrary = new usu.perpustakaan.buku.widget.admin.ManageLibrary();
        manageOperatorActivity = new usu.perpustakaan.buku.widget.admin.ManageOperatorActivity();
        toolBar = new usu.perpustakaan.buku.widget.template.ToolbarBlackGreen();
        buttonHome = new usu.widget.ButtonGlass();
        buttonAbout = new usu.widget.ButtonGlass();
        usu.perpustakaan.buku.widget.template.ToolbarBlackGreen statusBar = new usu.perpustakaan.buku.widget.template.ToolbarBlackGreen();
        labelStatusBar = new javax.swing.JLabel();
        labelProgress = new javax.swing.JLabel();
        progressBar = new usu.perpustakaan.buku.widget.template.ProgressBarBlackGreen();

        setGlassPane(glassPane);
        glassPane.setAlphaDisable(10);
        glassPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 100));

        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });

        buttonLibrarySetting.setMnemonic('L');
        buttonLibrarySetting.setText("Perpustakaan");
        buttonLibrarySetting.setToolTipText("Manage e'BookLibrary Setting");
        buttonLibrarySetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLibrarySettingActionPerformed(evt);
            }
        });

        buttonMySQLSetting.setMnemonic('Q');
        buttonMySQLSetting.setText("Database");
        buttonMySQLSetting.setToolTipText("Manage MySQL Setting");
        buttonMySQLSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMySQLSettingActionPerformed(evt);
            }
        });

        setTitle("e'BookLibrary - Admin");
        setAnimationHide(usu.widget.constan.Animation.HIDE_TO_LEFT);
        setAnimationShow(usu.widget.constan.Animation.SHOW_FROM_BOTTOM);
        setLocationRelativeTo(null);
        setMinimumSize(new java.awt.Dimension(800, 640));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelTitleBody.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTitleBody.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleBody.setText("e'BookLibrary");

        panelTengah.setOpaque(false);
        panelTengah.setLayout(new java.awt.CardLayout());
        panelTengah.add(panelHome, "HOME");
        panelTengah.add(manageOperator, "OPERATOR");
        panelTengah.add(manageMySQL, "MYSQL");
        panelTengah.add(manageLibrary, "LIBRARY");
        panelTengah.add(manageOperatorActivity, "MANAGE_OPERATOR_ACTIVITY");

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

        buttonHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Home 2.png"))); // NOI18N
        buttonHome.setMnemonic('M');
        buttonHome.setText("Menu");
        buttonHome.setToolTipText("Klik untuk kembali ke menu utama");
        buttonHome.setFocusable(false);
        buttonHome.setRoundRect(true);
        buttonHome.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHomeActionPerformed(evt);
            }
        });
        toolBar.add(buttonHome);

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

private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
    ((GlassPane) getGlassPane()).hideComponent();
}//GEN-LAST:event_aboutActionPerformed

private void buttonAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAboutActionPerformed
    ((GlassPane) getGlassPane()).showComponent(about);
}//GEN-LAST:event_buttonAboutActionPerformed

private void buttonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHomeActionPerformed
    if (panelHome.isVisible()) {
        return;
    }
    showTransition(panelUtama);
    setTitleBody(null);
    ((CardLayout) panelTengah.getLayout()).show(panelTengah, "HOME");

}//GEN-LAST:event_buttonHomeActionPerformed

private void buttonMySQLSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMySQLSettingActionPerformed
    if (manageOperator.isVisible()) {//GEN-LAST:event_buttonMySQLSettingActionPerformed
        }
    }

private void buttonLibrarySettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLibrarySettingActionPerformed
    if (manageLibrary.isVisible()) {//GEN-LAST:event_buttonLibrarySettingActionPerformed
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.perpustakaan.buku.widget.About about;
    usu.perpustakaan.buku.widget.admin.AddOperator addOperator;
    usu.widget.ButtonGlass buttonAbout;
    usu.widget.ButtonGlass buttonHome;
    usu.perpustakaan.buku.widget.admin.EditOperator editOperator;
    usu.widget.GlassPane glassPane;
    javax.swing.JLabel labelProgress;
    javax.swing.JLabel labelStatusBar;
    javax.swing.JLabel labelTitleBody;
    usu.perpustakaan.buku.widget.admin.ManageLibrary manageLibrary;
    usu.perpustakaan.buku.widget.admin.ManageMySQL manageMySQL;
    usu.perpustakaan.buku.widget.admin.ManageOperator manageOperator;
    usu.perpustakaan.buku.widget.admin.ManageOperatorActivity manageOperatorActivity;
    usu.perpustakaan.buku.widget.admin.PanelHome panelHome;
    javax.swing.JPanel panelTengah;
    usu.perpustakaan.buku.widget.template.PanelBlackGreen panelUtama;
    usu.perpustakaan.buku.widget.template.ProgressBarBlackGreen progressBar;
    usu.perpustakaan.buku.widget.template.ToolbarBlackGreen toolBar;
    // End of variables declaration//GEN-END:variables

    /**
     * load aktifitas operator
     */
    public class LoadOperatorActivity extends SwingWorker<List<OperatorActivity>, String> {

        private ArrayList<OperatorActivity> array;
        private ResultSet result;

        /**
         * 
         * @throws java.sql.SQLException 
         */
        public LoadOperatorActivity() throws SQLException {
            super();
            result = BookLibrarySQL.getResultOperatorActivity(connection);
            array = new ArrayList<OperatorActivity>();

            manageOperatorActivity.getModel().removeAllElements();
            manageOperatorActivity.setFreeze(true);
            buttonHome.setEnabled(false);
            labelProgress.setText("");
            labelStatusBar.setText("Loading Aktivitas Operator");
            progressBar.setIndeterminate(true);
        }

        @Override
        protected List<OperatorActivity> doInBackground() throws Exception {
            while (result.next()) {
                DefaultOperatorActivity def = new DefaultOperatorActivity();
                def.setActivity(result.getString(FieldConstan.TABEL_AKTIVITAS_OPERATOR.KEGIATAN));
                def.setOperator(result.getString(FieldConstan.TABEL_AKTIVITAS_OPERATOR.ID_OPERATOR));
                def.setTime(result.getTimestamp(FieldConstan.TABEL_AKTIVITAS_OPERATOR.WAKTU));
                array.add(def);
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        manageOperatorActivity.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        manageOperatorActivity.setFreeze(false);
                        buttonHome.setEnabled(true);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        progressBar.setIndeterminate(false);
                        try {
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load aktifitas " + s);
            }
        }
    }

    /**
     * 
     * @param evt
     * @throws java.sql.SQLException 
     */
    private void loadOperator(ActionEvent evt) throws SQLException {
        new LoadOperator().execute();
    }

    /**
     * load operator swing worker
     */
    public class LoadOperator extends SwingWorker<List<Operator>, String> {

        private ResultSet result;

        /**
         * 
         * @throws java.sql.SQLException
         */
        public LoadOperator() throws SQLException {
            super();
            result = BookLibrarySQL.getResultOperator(connection);

            buttonHome.setEnabled(false);

            labelStatusBar.setText("Loading Database Operator");
            progressBar.setIndeterminate(true);

            manageOperator.getButtonRefresh().setEnabled(false);
            manageOperator.getButtonAdd().setEnabled(false);
            manageOperator.getButtonDelete().setEnabled(false);
            manageOperator.getButtonEdit().setEnabled(false);
            manageOperator.getTable().setEnabled(false);
            manageOperator.getTextSearch().setEnabled(false);
            manageOperator.getModelTable().removeAllElements();
        }

        @Override
        protected List<Operator> doInBackground() throws Exception {
            ArrayList<Operator> array = new ArrayList<Operator>();
            while (result.next()) {
                array.add(BookLibrarySQL.getOperatorFromResultSet(result));
                publish("Load Operator " + result.getString(FieldConstan.TABEL_OPERATOR.ID_OPERATOR));
            }
            return array;
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText(s);
            }
        }

        @Override
        protected void done() {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        manageOperator.getModelTable().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        manageOperator.getTable().revalidate();
                        manageOperator.getTable().repaint();
                        manageOperator.getButtonRefresh().setEnabled(true);
                        manageOperator.getButtonAdd().setEnabled(true);
                        manageOperator.getButtonDelete().setEnabled(true);
                        manageOperator.getButtonEdit().setEnabled(true);
                        manageOperator.getTable().setEnabled(true);
                        manageOperator.getTextSearch().setEnabled(true);

                        labelStatusBar.setText("e'BookLibrary");
                        labelProgress.setText("");
                        progressBar.setIndeterminate(false);
                        buttonHome.setEnabled(true);
                        try {
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }
    }

    /**
     * 
     * @param title
     */
    public void setTitleBody(String title) {
        if (title == null) {
            labelTitleBody.setText("e'BookLibrary");
        } else {
            labelTitleBody.setText("e'BookLibrary - " + title);
        }
    }
}
