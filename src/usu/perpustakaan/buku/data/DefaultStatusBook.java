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
 * 
 */
package usu.perpustakaan.buku.data;

import usu.perpustakaan.buku.data.template.StatusBook;

/**
 *
 * @author usu
 */
public class DefaultStatusBook implements StatusBook {

    private static final long serialVersionUID = -1;
    private String bookId;
    private int totalBorrow;
    private boolean beingBorror;

    /**
     * 
     */
    public DefaultStatusBook() {
        this(null, 0, false);
    }

    /**
     * 
     * @param bookId
     * @param totalBorrow
     * @param beingBorror
     */
    public DefaultStatusBook(String bookId, int totalBorrow, boolean beingBorror) {
        this.bookId = bookId;
        this.totalBorrow = totalBorrow;
        this.beingBorror = beingBorror;
    }

    @Override
    public boolean isBeingBorror() {
        return beingBorror;
    }

    @Override
    public void setBeingBorror(boolean beingBorror) {
        this.beingBorror = beingBorror;
    }

    @Override
    public String getBookId() {
        return bookId;
    }

    @Override
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public int getTotalBorrow() {
        return totalBorrow;
    }

    @Override
    public void setTotalBorrow(int totalBorrow) {
        this.totalBorrow = totalBorrow;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultStatusBook other = (DefaultStatusBook) obj;
        if (this.bookId != other.bookId && (this.bookId == null || !this.bookId.equals(other.bookId))) {
            return false;
        }
        if (this.totalBorrow != other.totalBorrow) {
            return false;
        }
        if (this.beingBorror != other.beingBorror) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.bookId != null ? this.bookId.hashCode() : 0);
        hash = 73 * hash + this.totalBorrow;
        hash = 73 * hash + (this.beingBorror ? 1 : 0);
        return hash;
    }
}
