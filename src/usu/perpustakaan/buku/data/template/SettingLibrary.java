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

/**
 *
 * @author usu
 */
public interface SettingLibrary {

    /**
     * 
     * @return
     */
    int getMaxBorrowBook();

    /**
     * 
     * @return
     */
    int getMaxBorrowDay();

    /**
     * 
     * @return
     */
    int getPayOfLate();

    /**
     * 
     * @param maxBorrowBook
     */
    void setMaxBorrowBook(int maxBorrowBook);

    /**
     * 
     * @param maxBorrowDay
     */
    void setMaxBorrowDay(int maxBorrowDay);

    /**
     * 
     * @param payOfLate
     */
    void setPayOfLate(int payOfLate);
}
