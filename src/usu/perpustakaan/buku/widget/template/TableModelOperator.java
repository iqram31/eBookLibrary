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

import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import usu.perpustakaan.buku.data.template.Operator;

/**
 *
 * @author usu
 */
public class TableModelOperator extends AbstractTableModel {

    private static final long serialVersionUID = -1;
    private Vector<Operator> row;
    private Vector<String> column;

    /**
     * tabel model untuk operator
     */
    public TableModelOperator() {
        column = new Vector<String>();
        column.add("Id Operator");
        column.add("Password Operator");
        column.add("Nama Operator");
        column.add("Tanggal Lahir Operator");
        column.add("Kontak Operator");
        column.add("Alamat Operator");

        row = new Vector<Operator>();
    }

    public synchronized Operator get(int index) {
        return row.get(index);
    }

    /**
     * 
     * @param index
     * @return
     */
    public Operator getOperator(int index) {
        return row.get(index);
    }

    public void add(List<Operator> list) {
        for (Operator o : list) {
            row.add(o);
        }
        fireTableDataChanged();
    }

    /**
     * remove all row
     */
    public void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    /**
     * 
     * @param o
     */
    public void addOperator(Operator o) {
        row.add(o);
        fireTableDataChanged();
    }

    /**
     * 
     * @param array
     */
    public void addOperator(Operator[] array) {
        for (Operator o : array) {
            row.add(o);
        }
        fireTableDataChanged();
    }

    /**
     * 
     * @param o
     * @return
     */
    public boolean removeOperator(Operator o) {
        boolean b = row.remove(o);
        fireTableDataChanged();
        return b;
    }

    /**
     * 
     * @param index
     * @return 
     */
    public Operator removeOperator(int index) {
        Operator b = row.remove(index);
        fireTableRowsDeleted(index, index);
        return b;
    }

    /**
     * 
     * @param obj
     * @param index
     */
    public void setOperatorAt(Operator obj, int index) {
        row.setElementAt(obj, index);
        fireTableRowsUpdated(index, index);
    }

    /**
     * 
     * @return
     */
    public boolean isEmpty() {
        return row.isEmpty();
    }

    /**
     * 
     * @param o
     * @return
     */
    public boolean contains(Object o) {
        return row.contains(o);
    }

    /**
     * 
     * @param index
     * @param element
     */
    public void addOperator(int index, Operator element) {
        row.add(index, element);
        fireTableRowsInserted(index, index);
    }

    @Override
    public int getRowCount() {
        return row.size();
    }

    @Override
    public int getColumnCount() {
        return column.size();
    }

    /**
     * 
     * @param rowIndex
     * @param columnIndex
     * @param o
     */
    public void setValueAt(int rowIndex, int columnIndex, Object o) {
        try {
            if (columnIndex == 0) {
                row.get(rowIndex).setId(o.toString());
            } else if (columnIndex == 1) {
                row.get(rowIndex).setPassword(o.toString());
            } else if (columnIndex == 2) {
                row.get(rowIndex).setName(o.toString());
            } else if (columnIndex == 3) {
                row.get(rowIndex).setBorn((Date) o);
            } else if (columnIndex == 4) {
                row.get(rowIndex).setContact(o.toString());
            } else if (columnIndex == 5) {
                row.get(rowIndex).setAddress(o.toString());
            }
        } finally {
            fireTableDataChanged();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return row.get(rowIndex).getId();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getPassword();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getName();
        } else if (columnIndex == 3) {
            return new java.sql.Date(row.get(rowIndex).getBorn().getTime());
        } else if (columnIndex == 4) {
            return row.get(rowIndex).getContact();
        } else if (columnIndex == 5) {
            return row.get(rowIndex).getAddress();
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }
}
