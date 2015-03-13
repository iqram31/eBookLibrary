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
import usu.perpustakaan.buku.data.template.KindBook;

/**
 *
 * @author usu
 */
public class TableModelKindBook extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> column;
    private Vector<KindBook> row;

    /**
     * 
     */
    public TableModelKindBook() {
        super();
        column = new Vector<String>();
        column.add("Id Jenis Buku");
        column.add("Jenis Buku");
        row = new Vector<KindBook>();
    }
    
    /**
     * 
     * @param list
     */
    public void add(List<KindBook> list){
        for(KindBook b : list){
            row.add(b);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param get
     */
    public void add(KindBook[] get) {
        for (KindBook k : get) {
            row.add(k);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param obj
     * @param index
     */
    public synchronized void setElementAt(KindBook obj, int index) {
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
    public synchronized KindBook remove(int index) {
        KindBook k = row.remove(index);
        fireTableRowsDeleted(index, index);
        return k;
    }

    /**
     * 
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        boolean a = row.remove(o);
        fireTableDataChanged();
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
    public synchronized KindBook get(int index) {
        return row.get(index);
    }

    /**
     * 
     * @param index
     * @param element
     */
    public void add(int index, KindBook element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    /**
     * 
     * @param e
     * @return
     */
    public synchronized boolean add(KindBook e) {
        boolean a = row.add(e);
        fireTableDataChanged();
        return a;
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
            return row.get(rowIndex).getKind();
        }
        return null;
    }
}
