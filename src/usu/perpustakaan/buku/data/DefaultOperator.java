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

import usu.perpustakaan.buku.data.template.Operator;
import java.util.Date;

/**
 *
 * @author usu
 */
public class DefaultOperator implements Operator {

    private static final long serialVersionUID = -1;
    private String id,  name,  address,  contact;
    private Date born;
    private String password;

    /**
     * 
     */
    public DefaultOperator() {
        this(null, null, null, null, null, null);
    }

    /**
     * 
     * @param id
     * @param name
     * @param address
     * @param contact
     * @param born
     * @param password
     */
    public DefaultOperator(String id, String password, String name, String address, String contact, Date born) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.born = born;
        this.password = password;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Date getBorn() {
        return born;
    }

    @Override
    public void setBorn(Date born) {
        this.born = born;
    }

    @Override
    public String getContact() {
        return contact;
    }

    @Override
    public void setContact(String contact) {
        this.contact = contact;
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
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultOperator other = (DefaultOperator) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        if (this.address != other.address && (this.address == null || !this.address.equals(other.address))) {
            return false;
        }
        if (this.contact != other.contact && (this.contact == null || !this.contact.equals(other.contact))) {
            return false;
        }
        if (this.born != other.born && (this.born == null || !this.born.equals(other.born))) {
            return false;
        }
        if (this.password != other.password && (this.password == null || !this.password.equals(other.password))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 47 * hash + (this.address != null ? this.address.hashCode() : 0);
        hash = 47 * hash + (this.contact != null ? this.contact.hashCode() : 0);
        hash = 47 * hash + (this.born != null ? this.born.hashCode() : 0);
        hash = 47 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }
}
