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
package usu.perpustakaan.buku.widget.operator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import usu.perpustakaan.buku.constan.FieldConstan;
import usu.perpustakaan.buku.data.template.Borrow;
import usu.perpustakaan.buku.data.DefaultBook;
import usu.perpustakaan.buku.data.DefaultBorrow;
import usu.perpustakaan.buku.data.template.Member;
import usu.perpustakaan.buku.sql.BookLibrarySQL;
import usu.perpustakaan.buku.util.StringUtil;
import usu.perpustakaan.buku.widget.admin.ManageOperator;
import usu.perpustakaan.buku.widget.template.PanelBlackGreen;
import usu.perpustakaan.buku.widget.template.TableModelBook;
import usu.widget.ButtonGlass;
import usu.widget.text.DefaultDocument;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author  usu
 */
public class AddBorrow extends PanelBlackGreen {

    private static final long serialVersionUID = -1;
    private Connection connection;
    private TableModelBook model;
    private DefaultBorrow borrow;
    private int index;
    private String errorMessage;
    private ExecutorService executor;

    /** Creates new form AddBorrow */
    public AddBorrow() {
        initVariables();
        initComponents();
        initFinal();
        initActions();
    }

    /**
     * 
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @return
     */
    public ButtonGlass getButtonTambah() {
        return buttonTambah;
    }

    /**
     * 
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private void initActions() {
        textSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(textSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(textSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable(textSearch.getText());
            }
        });
    }

    /**
     * 
     * @return
     */
    public Borrow getBorrow() {
        if (textMember.getText().trim().equals("")) {
            errorMessage = "Anggota kosong";
            return null;
        }
        if (table.getSelectedRowCount() == 0) {
            errorMessage = "Tak ada buku yang terseleksi";
            return null;
        }
        try {
            if (!BookLibrarySQL.containMember(connection, textMember.getText())) {
                errorMessage = "Tak ada anggota dengan id tersebut";
                return null;
            }
        } catch (SQLException ex) {
            errorMessage = ex.getMessage();
            return null;
        }
        index = table.convertRowIndexToModel(table.getSelectedRow());
        borrow = new DefaultBorrow();
        borrow.setBook(model.get(index).getId());
        borrow.setMember(textMember.getText());
        return borrow;
    }

    /**
     * 
     * @param texxt
     */
    @SuppressWarnings("unchecked")
    public void filterTable(String texxt) {
        if (texxt.trim().equals("")) {
            ((TableRowSorter) table.getRowSorter()).setRowFilter(null);
        } else {
            ((TableRowSorter) table.getRowSorter()).setRowFilter(RowFilter.regexFilter(texxt));
        }
    }

    private void setMember(Member m) {
        textAlamat.setText(m.getAddress());
        textId.setText(m.getId());
        textKontak.setText(m.getContact());
        textTanggalLahir.setValue(m.getBorn());
        textnama.setText(m.getName());
    }

    /**
     * 
     * @return
     */
    public ButtonGlass getButtonCancel() {
        return buttonCancel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usu.perpustakaan.buku.widget.template.ViewPortGlass viewPortGlass1 = new usu.perpustakaan.buku.widget.template.ViewPortGlass();
        table = new javax.swing.JTable();
        RincianMember = new javax.swing.JPanel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        textnama = new javax.swing.JTextField();
        textId = new javax.swing.JTextField();
        textKontak = new javax.swing.JTextField();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        textAlamat = new javax.swing.JTextArea();
        textTanggalLahir = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        buttonTambah = new usu.widget.ButtonGlass();
        textMember = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        buttonRincian = new usu.widget.ButtonGlass();
        textSearch = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        buttonCancel = new usu.widget.ButtonGlass();

        viewPortGlass1.setView(table);

        table.setAutoCreateRowSorter(true);
        table.setForeground(new java.awt.Color(255, 255, 255));
        table.setFillsViewportHeight(true);
        table.setOpaque(false);
        table.setSelectionBackground(new java.awt.Color(51, 255, 52));
        table.setSelectionForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setText("Id");

        jLabel6.setText("Nama");

        jLabel7.setText("Tanggal Lahir");

        jLabel8.setText("Kontak");

        jLabel9.setText("Alamat");

        textnama.setEditable(false);

        textId.setEditable(false);

        textKontak.setEditable(false);

        textAlamat.setColumns(20);
        textAlamat.setEditable(false);
        textAlamat.setRows(5);
        jScrollPane1.setViewportView(textAlamat);

        textTanggalLahir.setEditable(false);
        textTanggalLahir.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG))));

        javax.swing.GroupLayout RincianMemberLayout = new javax.swing.GroupLayout(RincianMember);
        RincianMember.setLayout(RincianMemberLayout);
        RincianMemberLayout.setHorizontalGroup(
            RincianMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RincianMemberLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RincianMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RincianMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textnama, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(textId, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(textKontak, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(textTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                .addContainerGap())
        );
        RincianMemberLayout.setVerticalGroup(
            RincianMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RincianMemberLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RincianMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RincianMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RincianMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RincianMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textKontak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RincianMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pinjam Buku");

        jLabel2.setDisplayedMnemonic('A');
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Anggota");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Buku");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setOpaque(false);
        jScrollPane2.setViewport(viewPortGlass1);

        buttonTambah.setMnemonic('T');
        buttonTambah.setText("Tambah");
        buttonTambah.setRoundRect(true);

        textMember.setForeground(new java.awt.Color(255, 255, 255));
        textMember.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textMember.setFocusAccelerator('A');
        textMember.setFont(new java.awt.Font("Tahoma", 1, 11));

        buttonRincian.setMnemonic('L');
        buttonRincian.setText("Lihar Rincian");
        buttonRincian.setRoundRect(true);
        buttonRincian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRincianActionPerformed(evt);
            }
        });

        textSearch.setForeground(new java.awt.Color(255, 255, 255));
        textSearch.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textSearch.setFocusAccelerator('C');
        textSearch.setFont(new java.awt.Font("Tahoma", 1, 11));

        jLabel4.setDisplayedMnemonic('C');
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cari (case sensitive) ");

        buttonCancel.setMnemonic('B');
        buttonCancel.setText("Batal");
        buttonCancel.setRoundRect(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textMember, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonRincian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRincian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void buttonRincianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRincianActionPerformed
    try {
        Member m = BookLibrarySQL.getMember(connection, textMember.getText());
        if (m == null) {
            WidgetUtilities.showErrorMessage(this, "Tak ada anggota dengan id tersebut");
            return;
        }
        setMember(m);//GEN-LAST:event_buttonRincianActionPerformed
        JOptionPane.showMessageDialog(this, RincianMember, "Rincian Anggota", JOptionPane.PLAIN_MESSAGE, null);
    } catch (SQLException ex) {
        Logger.getLogger(AddBorrow.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void initFinal() {
        table.setModel(model);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new ManageOperator.RenderTable());
        }

        textMember.setDocument(new DefaultDocument(10) {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JPanel RincianMember;
    usu.widget.ButtonGlass buttonCancel;
    usu.widget.ButtonGlass buttonRincian;
    usu.widget.ButtonGlass buttonTambah;
    javax.swing.JTable table;
    javax.swing.JTextArea textAlamat;
    javax.swing.JTextField textId;
    javax.swing.JTextField textKontak;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textMember;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textSearch;
    javax.swing.JFormattedTextField textTanggalLahir;
    javax.swing.JTextField textnama;
    // End of variables declaration//GEN-END:variables

    private void initVariables() {
        model = new TableModelBook();
        executor = Executors.newCachedThreadPool();
    }

    /**
     * 
     */
    public void reset() {
        textMember.setText("");
        textSearch.setText("");
        try {
            new LoadBook().execute();
        } catch (SQLException ex) {
            Logger.getLogger(AddBorrow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * load book
     */
    public class LoadBook extends SwingWorker<DefaultBook[], String> {

        private ResultSet result;
        private ArrayList<DefaultBook> array;

        /**
         * 
         * @throws java.sql.SQLException 
         */
        public LoadBook() throws SQLException {
            super();
            result = BookLibrarySQL.getResultBook(connection, false);
            array = new ArrayList<DefaultBook>();

            model.removeAllElements();
        }

        @Override
        protected DefaultBook[] doInBackground() throws Exception {
            while (result.next()) {
                DefaultBook book = new DefaultBook();
                book.setAuthor(BookLibrarySQL.getBookAuthor(connection, result.getString(FieldConstan.TABEL_BUKU.ID_PENGARANG)));
                book.setId(result.getString(FieldConstan.TABEL_BUKU.ID_BUKU));
                book.setKind(BookLibrarySQL.getBookKind(connection, result.getString(FieldConstan.TABEL_BUKU.ID_JENIS)));
                book.setPublisher(BookLibrarySQL.getBookPublisher(connection, result.getString(FieldConstan.TABEL_BUKU.ID_PENERBIT)));
                book.setTitle(result.getString(FieldConstan.TABEL_BUKU.JUDUL_BUKU));
                array.add(book);
            }
            return array.toArray(new DefaultBook[array.size()]);
        }

        @Override
        protected void done() {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        model.add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AddBorrow.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(AddBorrow.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            array = null;
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(AddBorrow.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            });
        }
    }
}
