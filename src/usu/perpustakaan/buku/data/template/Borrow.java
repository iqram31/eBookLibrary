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

import java.util.Date;

/**
 *
 * @author usu
 */
public interface Borrow {

    String getBook();

    Date getDate();

    String getId();

    String getMember();

    String getOperator();

    Date getReturnDate();

    void setBook(String book);

    void setDate(Date date);

    void setId(String Id);

    void setMember(String member);

    void setOperator(String operator);

    void setReturnDate(Date returnDate);

}
