/*
 * 
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
package usu.perpustakaan.buku.widget.template;

import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import usu.perpustakaan.buku.data.template.Author;

/**
 *
 * @author usu
 */
public class TableModelAuthor extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> column;
    private Vector<Author> row;

    /**
     * 
     */
    public TableModelAuthor() {
        super();
        column = new Vector<String>();
        column.add("Id Pengarang");
        column.add("Nama Pengarang");
        row = new Vector<Author>();
    }

    /**
     * 
     * @param obj
     */
    public void add(Author[] obj) {
        for (Author a : obj) {
            row.add(a);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param list
     */
    public void add(List<Author> list) {
        for (Author a : list) {
            row.add(a);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param obj
     * @param index
     */
    public synchronized void setElementAt(Author obj, int index) {
        row.setElementAt(obj, index);
        fireTableRowsUpdated(index, index);
    }

    /**
     * 
     */
    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    /**
     * 
     * @param index
     * @return
     */
    public synchronized Author remove(int index) {
        Author a = row.remove(index);
        fireTableRowsDeleted(index, index);
        return a;
    }

    /**
     * 
     * @return
     */
    public synchronized boolean isEmpty() {
        return row.isEmpty();
    }

    /**
     * 
     * @param index
     * @return
     */
    public synchronized Author get(int index) {
        return row.get(index);
    }

    /**
     * 
     * @param index
     * @param element
     */
    public void add(int index, Author element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    /**
     * 
     * @param e
     * @return
     */
    public synchronized boolean add(Author e) {
        boolean b = row.add(e);
        fireTableDataChanged();
        return b;
    }

    @Override
    public int getRowCount() {
        return row.size();
    }

    @Override
    public int getColumnCount() {
        return column.size();
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return row.get(rowIndex).getId();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getName();
        }
        return null;
    }
}
