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
import usu.perpustakaan.buku.data.template.Message;

/**
 *
 * @author usu
 */
public class TableModelMessage extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> column;
    private Vector<Message> row;

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }

    /**
     * 
     */
    public TableModelMessage() {
        super();
        column = new Vector<String>();
        column.add("Untuk Operator");
        column.add("Dari Operator");
        column.add("Judul Pesan");
        column.add("Isi Pesan");
        column.add("Waktu Pengiriman");
        row = new Vector<Message>();
    }

    public void add(List<Message> list) {
        for (Message m : list) {
            row.add(m);
        }
        fireTableDataChanged();
    }

    public synchronized void setElementAt(Message obj, int index) {
        row.setElementAt(obj, index);
        fireTableRowsUpdated(index, index);
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized Message remove(int index) {
        Message m = row.remove(index);
        fireTableRowsDeleted(index, index);
        return m;
    }

    public boolean remove(Object o) {
        boolean b = row.remove(o);
        fireTableDataChanged();
        return b;
    }

    public synchronized boolean isEmpty() {
        return row.isEmpty();
    }

    public synchronized Message get(int index) {
        return row.get(index);
    }

    public void add(int index, Message element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    public synchronized boolean add(Message e) {
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
            return row.get(rowIndex).getOperatorTo();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getOperatorFrom();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getTitle();
        } else if (columnIndex == 3) {
            return row.get(rowIndex).getMessage();
        } else if (columnIndex == 4) {
            return row.get(rowIndex).getTime();
        } else {
            return null;
        }
    }
}
