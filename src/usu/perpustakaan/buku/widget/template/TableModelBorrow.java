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
package usu.perpustakaan.buku.widget.template;

import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import usu.perpustakaan.buku.data.template.Borrow;

/**
 * @author usu
 */
public class TableModelBorrow extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> column;
    private Vector<Borrow> row;

    /**
     * membut tabel model baru
     */
    public TableModelBorrow() {
        super();
        column = new Vector<String>();
        column.add("No Transaksi");
        column.add("Id Anggota");
        column.add("Id Operator");
        column.add("Id Buku");
        column.add("Tanggal Peminjaman");
        column.add("Tanggal Pengembalian");
        row = new Vector<Borrow>();
    }

    /**
     * 
     * @param get
     */
    public void add(Borrow[] get) {
        for (Borrow b : get) {
            row.add(b);
        }
        fireTableDataChanged();
    }
    
    public void add(List<Borrow> list){
        for(Borrow b : list){
            row.add(b);
        }
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }

    public synchronized Borrow set(int index, Borrow element) {
        Borrow b = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return b;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized Borrow remove(int index) {
        Borrow b = row.remove(index);
        fireTableRowsDeleted(index, index);
        return b;
    }

    public boolean remove(Object o) {
        boolean b = row.remove(o);
        fireTableDataChanged();
        return b;
    }

    public synchronized boolean isEmpty() {
        return row.isEmpty();
    }

    public synchronized Borrow get(int index) {
        return row.get(index);
    }

    public void add(int index, Borrow element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    public synchronized boolean add(Borrow e) {
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return row.get(rowIndex).getId();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getMember();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getOperator();
        } else if (columnIndex == 3) {
            return row.get(rowIndex).getBook();
        } else if (columnIndex == 4) {
            return row.get(rowIndex).getDate();
        } else if (columnIndex == 5) {
            return row.get(rowIndex).getReturnDate();
        } else {
            return null;
        }
    }
}
