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
package usu.perpustakaan.buku.data;

import usu.perpustakaan.buku.data.template.SettingLibrary;

/**
 *
 * @author usu
 */
public class DefaultSettingLibrary implements SettingLibrary {

    private int maxBorrowBook;
    private int maxBorrowDay;
    private int payOfLate;

    public DefaultSettingLibrary() {
    }

    public DefaultSettingLibrary(int maxBorrowBook, int maxBorrowDay, int payOfLate) {
        this.maxBorrowBook = maxBorrowBook;
        this.maxBorrowDay = maxBorrowDay;
        this.payOfLate = payOfLate;
    }

    public int getMaxBorrowBook() {
        return maxBorrowBook;
    }

    public void setMaxBorrowBook(int maxBorrowBook) {
        this.maxBorrowBook = maxBorrowBook;
    }

    public int getMaxBorrowDay() {
        return maxBorrowDay;
    }

    public void setMaxBorrowDay(int maxBorrowDay) {
        this.maxBorrowDay = maxBorrowDay;
    }

    public int getPayOfLate() {
        return payOfLate;
    }

    public void setPayOfLate(int payOfLate) {
        this.payOfLate = payOfLate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultSettingLibrary other = (DefaultSettingLibrary) obj;
        if (this.maxBorrowBook != other.maxBorrowBook) {
            return false;
        }
        if (this.maxBorrowDay != other.maxBorrowDay) {
            return false;
        }
        if (this.payOfLate != other.payOfLate) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.maxBorrowBook;
        hash = 37 * hash + this.maxBorrowDay;
        hash = 37 * hash + this.payOfLate;
        return hash;
    }
}
