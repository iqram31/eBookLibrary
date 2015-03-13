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
import usu.perpustakaan.buku.data.template.Book;

/**
 *
 * @author usu
 */
public class TableModelBook extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<String> column;
    private Vector<Book> row;

    /**
     * make new table model for book
     */
    public TableModelBook() {
        column = new Vector<String>();
        column.add("Id Buku");
        column.add("Judul Buku");
        column.add("Pengarang Buku");
        column.add("Penerbit Buku");
        column.add("Jenis Buku");
        row = new Vector<Book>();
    }

    public void add(List<Book> list) {
        for (Book b : list) {
            row.add(b);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param index
     * @return
     */
    public synchronized Book get(int index) {
        return row.get(index);
    }

    /**
     * 
     * @param get
     */
    public void add(Book[] get) {
        for (Book b : get) {
            row.add(b);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param obj
     * @param index
     */
    public synchronized void setElementAt(Book obj, int index) {
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
    public synchronized Book remove(int index) {
        Book book = row.remove(index);
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
    public void add(int index, Book element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    /**
     * 
     * @param e
     * @return
     */
    public synchronized boolean add(Book e) {
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
        }
        return null;
    }
}
