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

import usu.perpustakaan.buku.data.template.Publisher;

/**
 *
 * @author usu
 */
public class DefaultPublisher implements Publisher {

    private static final long serialVersionUID = -1;
    private String id;
    private String name;

    /**
     * 
     */
    public DefaultPublisher() {
        this(null, null);
    }

    /**
     * 
     * @param id
     * @param name
     */
    public DefaultPublisher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultPublisher other = (DefaultPublisher) obj;
        if (this.getId() != other.getId() && (this.getId() == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.getName() != other.getName() && (this.getName() == null || !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        hash = 53 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
        return hash;
    }
}
