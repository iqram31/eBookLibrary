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
public class PanelBook extends javax.swing.JPanel {

    private static final long serialVersionUID = -1;

    /** Creates new form PanelBook */
    public PanelBook() {
        initComponents();
    }

    /**
     * 
     * @return
     */
    public ButtonImageReflection getButtonAuthor() {
        return buttonAuthor;
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
    public ButtonImageReflection getButtonPublisher() {
        return buttonPublisher;
    }

    /**
     * 
     * @return
     */
    public ButtonImageReflection getButtonKind() {
        return buttonKind;
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

        buttonAuthor = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();
        buttonBook = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();
        buttonPublisher = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();
        buttonKind = new usu.perpustakaan.buku.widget.template.ButtonImageReflection();

        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        buttonAuthor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/author.png"))); // NOI18N
        buttonAuthor.setMnemonic('P');
        buttonAuthor.setText("Pengarang Buku");
        buttonAuthor.setToolTipText("Klik untuk masuk ke bagian pengarang buku");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(buttonAuthor, gridBagConstraints);

        buttonBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/book.png"))); // NOI18N
        buttonBook.setMnemonic('B');
        buttonBook.setText("Buku");
        buttonBook.setToolTipText("Klik untuk masuk ke bagian buku");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(buttonBook, gridBagConstraints);

        buttonPublisher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/publisher.png"))); // NOI18N
        buttonPublisher.setMnemonic('E');
        buttonPublisher.setText("Penerbit Buku");
        buttonPublisher.setToolTipText("Klik untuk masuk ke bagian penerbit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(buttonPublisher, gridBagConstraints);

        buttonKind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/kind.png"))); // NOI18N
        buttonKind.setMnemonic('J');
        buttonKind.setText("Jenis Buku");
        buttonKind.setToolTipText("Klik untuk masuk ke bagian jenis buku");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        add(buttonKind, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonAuthor;
    usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonBook;
    usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonKind;
    usu.perpustakaan.buku.widget.template.ButtonImageReflection buttonPublisher;
    // End of variables declaration//GEN-END:variables
}
