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
package usu.perpustakaan.buku.data.template;

import java.io.Serializable;

/**
 *
 * @author usu
 */
public interface StatusBook extends Serializable {

    /**
     * 
     * @return
     */
    String getBookId();

    /**
     * 
     * @return
     */
    int getTotalBorrow();

    /**
     * 
     * @return
     */
    boolean isBeingBorror();

    /**
     * 
     * @param beingBorror
     */
    void setBeingBorror(boolean beingBorror);

    /**
     * 
     * @param bookId
     */
    void setBookId(String bookId);

    /**
     * 
     * @param totalBorrow
     */
    void setTotalBorrow(int totalBorrow);
}
