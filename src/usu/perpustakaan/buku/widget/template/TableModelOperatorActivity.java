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
import usu.perpustakaan.buku.data.DefaultOperatorActivity;
import usu.perpustakaan.buku.data.template.OperatorActivity;

/**
 *
 * @author usu
 */
public class TableModelOperatorActivity extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> colomn;
    private Vector<OperatorActivity> row;

    /**
     * 
     */
    public TableModelOperatorActivity() {
        super();
        colomn = new Vector<String>();
        colomn.add("Id Operator");
        colomn.add("Aktivitas Operator");
        colomn.add("Waktu Kejadian");
        row = new Vector<OperatorActivity>();
    }

    public void add(List<OperatorActivity> get) {
        for (OperatorActivity o : get) {
            row.add(o);
        }
        fireTableDataChanged();
    }

    public void add(OperatorActivity[] act) {
        for (OperatorActivity o : act) {
            row.add(o);
        }
        fireTableDataChanged();
    }

    public synchronized void setElementAt(OperatorActivity obj, int index) {
        row.setElementAt(obj, index);
        fireTableRowsUpdated(index, index);
    }

    public synchronized OperatorActivity set(int index, OperatorActivity element) {
        OperatorActivity o = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return o;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized OperatorActivity remove(int index) {
        OperatorActivity o = row.remove(index);
        fireTableRowsDeleted(index, index);
        return o;
    }

    public boolean remove(Object o) {
        boolean b = row.remove(o);
        fireTableDataChanged();
        return b;
    }

    public synchronized boolean isEmpty() {
        return row.isEmpty();
    }

    public synchronized OperatorActivity get(int index) {
        return row.get(index);
    }

    public void add(int index, OperatorActivity element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    public synchronized boolean add(OperatorActivity e) {
        int index = row.size();
        boolean b = row.add(e);
        fireTableRowsInserted(index, index);
        return b;
    }

    @Override
    public String getColumnName(int column) {
        return this.colomn.get(column);
    }

    @Override
    public int getRowCount() {
        return row.size();
    }

    @Override
    public int getColumnCount() {
        return colomn.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return row.get(rowIndex).getOperator();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getActivity();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getTime();
        } else {
            return null;
        }
    }
}
