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
public interface Message {

    String getID();

    void setID(String id);

    String getMessage();

    String getOperatorTo();

    Date getTime();

    String getTitle();

    void setMessage(String message);

    void setOperatorTo(String operator);

    void setTime(Date time);

    void setTitle(String title);

    String getOperatorFrom();

    void setOperatorFrom(String ope);
}
