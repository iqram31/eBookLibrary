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
import usu.perpustakaan.buku.data.template.Regristration;

/**
 *
 * @author usu
 */
public class TableModelRegristration extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> column;
    private Vector<Regristration> row;

    public TableModelRegristration() {
        super();
        column = new Vector<String>();
        column.add("Id Anggota");
        column.add("Id Operator");
        column.add("Waktu Pendaftaran");
        row = new Vector<Regristration>();
    }

    public void add(Regristration[] get) {
        for(Regristration g : get){
            row.add(g);
        }
        fireTableDataChanged();
    }
    
    public void add(List<Regristration> list){
        for(Regristration r : list){
            row.add(r);
        }
        fireTableDataChanged();
    }

    public synchronized void setElementAt(Regristration obj, int index) {
        row.setElementAt(obj, index);
        fireTableRowsInserted(index, index);
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized Regristration remove(int index) {
        Regristration r = row.remove(index);
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

    public synchronized Regristration get(int index) {
        return row.get(index);
    }

    public void add(int index, Regristration element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    public synchronized boolean add(Regristration e) {
        boolean b = row.add(e);
        fireTableDataChanged();
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
            return row.get(rowIndex).getMember();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getOperator();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getTime();
        } else {
            return null;
        }
    }
}
