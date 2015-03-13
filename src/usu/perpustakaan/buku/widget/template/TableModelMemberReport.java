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
import usu.perpustakaan.buku.data.DefaultMemberReport;

/**
 *
 * @author usu
 */
public class TableModelMemberReport extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> column;
    private Vector<DefaultMemberReport> row;
    private int index;

    /**
     * membur table model member baru
     */
    public TableModelMemberReport() {
        super();
        column = new Vector<String>();
        column.add("Id Anggota");
        column.add("Nama Anggota");
        column.add("Tanggal Lahir Anggota");
        column.add("Kontak Anggota");
        column.add("Alamat Anggota");
        column.add("Terdaftar");
        column.add("Total Meminjam");
        row = new Vector<DefaultMemberReport>();
    }

    /**
     * 
     * @param m
     */
    public void add(DefaultMemberReport[] m) {
        for (DefaultMemberReport i : m) {
            row.add(i);
        }
        fireTableDataChanged();
    }

    public void add(List<DefaultMemberReport> list) {
        for (DefaultMemberReport d : list) {
            row.add(d);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param obj
     * @param index
     */
    public synchronized void setElementAt(DefaultMemberReport obj, int index) {
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
    public synchronized DefaultMemberReport remove(int index) {
        DefaultMemberReport m = row.remove(index);
        fireTableRowsDeleted(index, index);
        return m;
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
    public synchronized DefaultMemberReport get(int index) {
        return row.get(index);
    }

    /**
     * 
     * @param index
     * @param element
     */
    public void add(int index, DefaultMemberReport element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    /**
     * 
     * @param e
     * @return
     */
    public synchronized boolean add(DefaultMemberReport e) {
        index = row.size();
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
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getBorn();
        } else if (columnIndex == 3) {
            return row.get(rowIndex).getContact();
        } else if (columnIndex == 4) {
            return row.get(rowIndex).getAddress();
        } else if (columnIndex == 5) {
            return row.get(rowIndex).getTerdaftar();
        } else if (columnIndex == 6) {
            return row.get(rowIndex).getTotalMeminjam();
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }
}
