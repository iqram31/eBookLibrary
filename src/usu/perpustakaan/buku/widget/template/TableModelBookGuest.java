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
import java.util.Stack;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import usu.perpustakaan.buku.data.DefaultBookGuest;
import usu.perpustakaan.buku.data.template.Book;

/**
 *
 * @author usu
 */
public class TableModelBookGuest extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> column;
    private Vector<DefaultBookGuest> row;

    /**
     * make new table model for book
     */
    public TableModelBookGuest() {
        column = new Vector<String>();
        column.add("Id Buku");
        column.add("Judul Buku");
        column.add("Pengarang Buku");
        column.add("Penerbit Buku");
        column.add("Jenis Buku");
        column.add("Total Dipinjam");
        column.add("Status");
        row = new Stack<DefaultBookGuest>();
    }

    /**
     * 
     * @param list
     */
    public void add(List<DefaultBookGuest> list) {
        for (DefaultBookGuest d : list) {
            row.add(d);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param index
     * @return
     */
    public synchronized DefaultBookGuest get(int index) {
        return row.get(index);
    }

    /**
     * 
     * @param get
     */
    public void add(DefaultBookGuest[] get) {
        for (DefaultBookGuest b : get) {
            row.add(b);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param obj
     * @param index
     */
    public synchronized void setElementAt(DefaultBookGuest obj, int index) {
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
     * @param obj
     * @return
     */
    public synchronized boolean removeElement(Object obj) {
        boolean o = row.removeElement(obj);
        fireTableDataChanged();
        return o;
    }

    /**
     * 
     * @param index
     * @return
     */
    public synchronized DefaultBookGuest remove(int index) {
        DefaultBookGuest book = row.remove(index);
        fireTableRowsDeleted(index, index);
        return book;
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
     * @param element
     */
    public void add(int index, DefaultBookGuest element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    /**
     * 
     * @param e
     * @return
     */
    public synchronized boolean add(DefaultBookGuest e) {
        int index = row.size();
        boolean a = row.add(e);
        fireTableRowsInserted(index, index);
        return a;
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
            return row.get(rowIndex).getTitle();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getAuthor();
        } else if (columnIndex == 3) {
            return row.get(rowIndex).getPublisher();
        } else if (columnIndex == 4) {
            return row.get(rowIndex).getKind();
        } else if (columnIndex == 6) {
            return row.get(rowIndex).isBorrow();
        } else if (columnIndex == 5) {
            return row.get(rowIndex).getTotalBorrow();
        }
        return null;
    }
}
