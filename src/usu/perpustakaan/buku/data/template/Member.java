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
import java.util.Date;

/**
 *
 * @author usu
 */
public interface Member extends Serializable {

    /**
     * 
     * @return
     */
    String getAddress();

    /**
     * 
     * @return
     */
    Date getBorn();

    /**
     * 
     * @return
     */
    String getContact();

    /**
     * 
     * @return
     */
    String getId();

    /**
     * 
     * @return
     */
    String getName();

    /**
     * 
     * @param address
     */
    void setAddress(String address);

    /**
     * 
     * @param born
     */
    void setBorn(Date born);

    /**
     * 
     * @param contact
     */
    void setContact(String contact);

    /**
     * 
     * @param id
     */
    void setId(String id);

    /**
     * 
     * @param name
     */
    void setName(String name);
}
