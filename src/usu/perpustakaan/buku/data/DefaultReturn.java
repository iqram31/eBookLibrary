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

import usu.perpustakaan.buku.data.template.Return;
import java.util.Date;

/**
 *
 * @author usu
 */
public class DefaultReturn implements Return {

    private String id;
    private String operator;
    private String member;
    private String book;
    private Date dateReturn;
    private int terlambar;
    private int denda;

    public DefaultReturn() {
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public int getDenda() {
        return denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getTerlambar() {
        return terlambar;
    }

    public void setTerlambar(int terlambar) {
        this.terlambar = terlambar;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultReturn other = (DefaultReturn) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.operator != other.operator && (this.operator == null || !this.operator.equals(other.operator))) {
            return false;
        }
        if (this.dateReturn != other.dateReturn && (this.dateReturn == null || !this.dateReturn.equals(other.dateReturn))) {
            return false;
        }
        if (this.terlambar != other.terlambar) {
            return false;
        }
        if (this.denda != other.denda) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 89 * hash + (this.operator != null ? this.operator.hashCode() : 0);
        hash = 89 * hash + (this.dateReturn != null ? this.dateReturn.hashCode() : 0);
        hash = 89 * hash + this.terlambar;
        hash = 89 * hash + this.denda;
        return hash;
    }
}
