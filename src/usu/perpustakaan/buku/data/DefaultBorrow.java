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
 */
package usu.perpustakaan.buku.data;

import usu.perpustakaan.buku.data.template.Borrow;
import java.util.Date;

/**
 *
 * @author usu
 */
public class DefaultBorrow implements Borrow {

    private String member;
    private String operator;
    private String book;
    private Date date,  returnDate;
    private String Id;

    public DefaultBorrow() {
        // do nothing
    }

    public DefaultBorrow(String member, String operator, String book, Date date, Date returnDate, String Id) {
        this.member = member;
        this.operator = operator;
        this.book = book;
        this.date = date;
        this.returnDate = returnDate;
        this.Id = Id;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultBorrow other = (DefaultBorrow) obj;
        if (this.member != other.member && (this.member == null || !this.member.equals(other.member))) {
            return false;
        }
        if (this.operator != other.operator && (this.operator == null || !this.operator.equals(other.operator))) {
            return false;
        }
        if (this.book != other.book && (this.book == null || !this.book.equals(other.book))) {
            return false;
        }
        if (this.date != other.date && (this.date == null || !this.date.equals(other.date))) {
            return false;
        }
        if (this.returnDate != other.returnDate && (this.returnDate == null || !this.returnDate.equals(other.returnDate))) {
            return false;
        }
        if (this.Id != other.Id && (this.Id == null || !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.member != null ? this.member.hashCode() : 0);
        hash = 47 * hash + (this.operator != null ? this.operator.hashCode() : 0);
        hash = 47 * hash + (this.book != null ? this.book.hashCode() : 0);
        hash = 47 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 47 * hash + (this.returnDate != null ? this.returnDate.hashCode() : 0);
        hash = 47 * hash + (this.Id != null ? this.Id.hashCode() : 0);
        return hash;
    }
}
