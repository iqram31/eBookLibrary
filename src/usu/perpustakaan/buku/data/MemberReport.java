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

import java.util.Date;

/**
 *
 * @author usu
 */
public interface MemberReport {

    String getAddress();

    Date getBorn();

    String getContact();

    String getId();

    String getName();

    Date getTerdaftar();

    int getTotalMeminjam();

    void setAddress(String address);

    void setBorn(Date born);

    void setContact(String contact);

    void setId(String id);

    void setName(String name);

    void setTerdaftar(Date terdaftar);

    void setTotalMeminjam(int totalMeminjam);

}
