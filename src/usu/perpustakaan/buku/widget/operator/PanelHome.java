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
package usu.perpustakaan.buku.widget.operator;

import usu.perpustakaan.buku.widget.template.ButtonImageReflection;

/**
 *
 * @author  usu
 */
public class PanelHome extends javax.swing.JPanel {

    private static final long serialVersionUID = -1;

    /** Creates new form PanelHome */
    public PanelHome() {
        initComponents();
    }

    /**
     * 
     * @return
     */
    public ButtonImageReflection getButtonActivities() {
        return buttonActivities;
    }

    /**
     * 
     * @return
     */
    public ButtonImageReflection getButtonBook() {
        return buttonBook;
    }

    /**
     * 
     * @return
     */
    public ButtonImageReflection getButtonMember() {
        return buttonMember;
    }

    /**
     * 
     * @return
     */
    public ButtonImageReflection getButtonReport() {
        return buttonReport;
    }

    /**
     * 
     * @return
     */
    public ButtonImageReflection getButtonWizard() {
        return buttonMessage;
    }

    /**
     * 
     * @return
     */
    public ButtonImageReflection getButtonYourselft() {
        return buttonYourselft;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonActivities = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();
        buttonBook = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();
        buttonMember = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();
        buttonReport = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();
        buttonYourselft = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();
        buttonMessage = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();

        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        buttonActivities.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Refresh.png"))); // NOI18N
        buttonActivities.setMnemonic('A');
        buttonActivities.setText("Aktivitas");
        buttonActivities.setToolTipText("Klik untuk masuk kebagian aktivitas peminjaman dan pengembalian");
        add(buttonActivities, new java.awt.GridBagConstraints());

        buttonBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/book.png"))); // NOI18N
        buttonBook.setMnemonic('B');
        buttonBook.setText("Buku");
        buttonBook.setToolTipText("Klik untuk masuk ke manajemen buku");
        add(buttonBook, new java.awt.GridBagConstraints());

        buttonMember.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/user.png"))); // NOI18N
        buttonMember.setMnemonic('O');
        buttonMember.setText("Anggota");
        buttonMember.setToolTipText("Klik untuk masuk ke manajemen anggota");
        add(buttonMember, new java.awt.GridBagConstraints());

        buttonReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Notepad2.png"))); // NOI18N
        buttonReport.setMnemonic('L');
        buttonReport.setText("Laporan");
        buttonReport.setToolTipText("Klik untuk masuk ke manajemen laporan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(buttonReport, gridBagConstraints);

        buttonYourselft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/operator.png"))); // NOI18N
        buttonYourselft.setMnemonic('P');
        buttonYourselft.setText("Pribadi");
        buttonYourselft.setToolTipText("Klik untuk merubah identitas pribadi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(buttonYourselft, gridBagConstraints);

        buttonMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/report.png"))); // NOI18N
        buttonMessage.setMnemonic('N');
        buttonMessage.setText("Pesan");
        buttonMessage.setToolTipText("Klik untuk masuk ke manajemen pesan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        add(buttonMessage, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonActivities;
    usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonBook;
    usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonMember;
    usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonMessage;
    usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonReport;
    usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonYourselft;
    // End of variables declaration//GEN-END:variables
}
