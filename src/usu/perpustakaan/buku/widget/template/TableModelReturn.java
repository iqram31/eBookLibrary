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
import usu.perpustakaan.buku.data.template.Return;

/**
 *
 * @author usu
 */
public class TableModelReturn extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> column;
    private Vector<Return> row;

    /**
     * 
     */
    public TableModelReturn() {
        super();
        column = new Vector<String>();
        column.add("Id Transaksi");
        column.add("Id Anggota");
        column.add("Id Operator");
        column.add("Id Buku");
        column.add("Tanggal Pengembalian");
        column.add("Total Hari Terlambat");
        column.add("Denda Keterlambatan");
        row = new Vector<Return>();
    }

    public void add(Return[] ret) {
        for (Return r : ret) {
            row.add(r);
        }
        fireTableDataChanged();
    }
    
    public void add(List<Return> list){
        for(Return r : list){
            row.add(r);
        }
        fireTableDataChanged();
    }

    public synchronized void setElementAt(Return obj, int index) {
        row.setElementAt(obj, index);
        fireTableRowsUpdated(index, index);
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized Return remove(int index) {
        Return r = row.remove(index);
        fireTableRowsDeleted(index, index);
        return r;
    }

    public boolean remove(Object o) {
        boolean b = row.remove(o);
        fireTableDataChanged();
        return b;
    }

    public synchronized boolean isEmpty() {
        return row.isEmpty();
    }

    public synchronized Return get(int index) {
        return row.get(index);
    }

    public void add(int index, Return element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    public synchronized boolean add(Return e) {
        int index = row.size();
        boolean b = row.add(e);
        fireTableRowsInserted(index, index);
        return b;
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
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
            return row.get(rowIndex).getDateReturn();
        } else if (columnIndex == 5) {
            return row.get(rowIndex).getTerlambar();
        } else if (columnIndex == 6) {
            return row.get(rowIndex).getDenda();
        } else {
            return null;
        }
    }
}
