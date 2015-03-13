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
import usu.perpustakaan.buku.data.template.Publisher;

/**
 *
 * @author usu
 */
public class TableModelPublisher extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> column;
    private Vector<Publisher> row;

    /**
     * 
     */
    public TableModelPublisher() {
        super();
        column = new Vector<String>();
        column.add("Id Penerbit");
        column.add("Nama Penerbit");
        row = new Vector<Publisher>();
    }

    /**
     * 
     * @param p
     */
    public void add(Publisher[] p) {
        for (Publisher i : p) {
            row.add(i);
        }
        fireTableDataChanged();
    }

    public void add(List<Publisher> ilist) {
        for (Publisher p : ilist) {
            row.add(p);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param obj
     * @param index
     */
    public synchronized void setElementAt(Publisher obj, int index) {
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
    public synchronized Publisher remove(int index) {
        Publisher p = row.remove(index);
        fireTableRowsDeleted(index, index);
        return p;
    }

    /**
     * 
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        boolean b = row.remove(o);
        fireTableDataChanged();
        return b;
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
    public synchronized Publisher get(int index) {
        return row.get(index);
    }

    /**
     * 
     * @param index
     * @param element
     */
    public void add(int index, Publisher element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    /**
     * 
     * @param e
     * @return
     */
    public synchronized boolean add(Publisher e) {
        int index = row.size();
        boolean b = row.add(e);
        fireTableRowsInserted(index, index);
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return row.get(rowIndex).getId();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getName();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }
}
