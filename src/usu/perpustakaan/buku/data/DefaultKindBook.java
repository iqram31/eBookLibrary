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

import usu.perpustakaan.buku.data.template.KindBook;

/**
 *
 * @author usu
 */
public class DefaultKindBook implements KindBook {

    private static final long serialVersionUID = -1;
    private String id;
    private String kind;

    /**
     * 
     */
    public DefaultKindBook() {
        this(null, null);
    }

    /**
     * 
     * @param id
     * @param kind
     */
    public DefaultKindBook(String id, String kind) {
        this.id = id;
        this.kind = kind;
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
    public String getKind() {
        return kind;
    }

    @Override
    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultKindBook other = (DefaultKindBook) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.kind != other.kind && (this.kind == null || !this.kind.equals(other.kind))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 83 * hash + (this.kind != null ? this.kind.hashCode() : 0);
        return hash;
    }
}
