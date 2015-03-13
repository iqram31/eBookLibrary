/*
 * ManageAuthor.java
 *
 * Created on 26 Maret 2008, 16:05
 */
package usu.perpustakaan.buku.widget.operator;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import usu.perpustakaan.buku.data.DefaultAuthor;
import usu.perpustakaan.buku.widget.admin.ManageOperator;
import usu.perpustakaan.buku.widget.template.TableModelAuthor;
import usu.perpustakaan.buku.widget.template.TableModelBook;
import usu.perpustakaan.buku.widget.template.TextBoxGlass;
import usu.widget.ButtonGlass;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author  usu
 */
public class ManageBook extends javax.swing.JPanel {

    private static final long serialVersionUID = -1;
    private TableModelBook model;
    private DefaultAuthor author;
    private int index;
    private boolean freeze;

    public boolean isFreeze() {
        return freeze;
    }

    /**
     * 
     * @param freeze
     */
    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
        textSearch.setEnabled(!freeze);
        textSearch.setText("");
        buttonAdd.setEnabled(!freeze);
        buttonDelete.setEnabled(!freeze);
        buttonEdit.setEnabled(!freeze);
        buttonRefresh.setEnabled(!freeze);
        table.setEnabled(!freeze);
    }

    /** Creates new form ManageAuthor */
    public ManageBook() {
        initVariables();
        initComponents();
        initFinal();
        initActions();
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

    @SuppressWarnings("unchecked")
    private void filterTable(String teks) {
        if (table.getRowSorter() != null) {
            if (teks.trim().equals("")) {
                ((TableRowSorter<TableModelAuthor>) table.getRowSorter()).setRowFilter(null);
            } else {
                ((TableRowSorter<TableModelAuthor>) table.getRowSorter()).setRowFilter(RowFilter.regexFilter(teks));
            }
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

        usu.perpustakaan.buku.widget.template.ViewPortGlass viewPortGlass1 = new usu.perpustakaan.buku.widget.template.ViewPortGlass();
        table = new javax.swing.JTable();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        textSearch = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        buttonDelete = new usu.widget.ButtonGlass();
        buttonEdit = new usu.widget.ButtonGlass();
        buttonAdd = new usu.widget.ButtonGlass();
        buttonRefresh = new usu.widget.ButtonGlass();

        viewPortGlass1.setView(table);

        table.setAutoCreateRowSorter(true);
        table.setForeground(new java.awt.Color(255, 255, 255));
        table.setToolTipText("Tabel buku");
        table.setFillsViewportHeight(true);
        table.setOpaque(false);
        table.setSelectionBackground(new java.awt.Color(51, 255, 52));
        table.setSelectionForeground(new java.awt.Color(0, 0, 0));

        setOpaque(false);

        jLabel1.setDisplayedMnemonic('C');
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cari (case sensitive) ");

        textSearch.setForeground(new java.awt.Color(255, 255, 255));
        textSearch.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textSearch.setToolTipText("Masukkan kata yang akan dicari");
        textSearch.setFocusAccelerator('C');
        textSearch.setFont(new java.awt.Font("Tahoma", 1, 11));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setOpaque(false);
        jScrollPane1.setViewport(viewPortGlass1);

        buttonDelete.setMnemonic('H');
        buttonDelete.setText("Hapus");
        buttonDelete.setToolTipText("klik untuk menghapus");
        buttonDelete.setRoundRect(true);

        buttonEdit.setMnemonic('U');
        buttonEdit.setText("Ubah");
        buttonEdit.setToolTipText("Klik untuk mengubah buku");
        buttonEdit.setRoundRect(true);

        buttonAdd.setMnemonic('A');
        buttonAdd.setText("Tambah");
        buttonAdd.setToolTipText("Klik untuk menambah buku");
        buttonAdd.setRoundRect(true);

        buttonRefresh.setMnemonic('S');
        buttonRefresh.setText("Segarkan");
        buttonRefresh.setToolTipText("Klik untuk meload ulang database");
        buttonRefresh.setRoundRect(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.widget.ButtonGlass buttonAdd;
    usu.widget.ButtonGlass buttonDelete;
    usu.widget.ButtonGlass buttonEdit;
    usu.widget.ButtonGlass buttonRefresh;
    javax.swing.JTable table;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textSearch;
    // End of variables declaration//GEN-END:variables

    public ButtonGlass getButtonAdd() {
        return buttonAdd;
    }

    public ButtonGlass getButtonDelete() {
        return buttonDelete;
    }

    public ButtonGlass getButtonEdit() {
        return buttonEdit;
    }

    public ButtonGlass getButtonRefresh() {
        return buttonRefresh;
    }

    public JTable getTable() {
        return table;
    }

    public TextBoxGlass getTextSearch() {
        return textSearch;
    }

    public TableModelBook getModel() {
        return model;
    }

    private void initFinal() {
        table.setModel(model);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new ManageOperator.RenderTable());
        }
        WidgetUtilities.setAutomaticPopUpMenu(table);
    }

    private void initVariables() {
        model = new TableModelBook();
    }
}
