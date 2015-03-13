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

import usu.perpustakaan.buku.data.template.Message;
import java.util.Date;

/**
 *
 * @author usu
 */
public class DefaultMessage implements Message {

    private String operatorTo,  operatorFrom;
    private String title;
    private String message;
    private Date time;
    private String id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DefaultMessage() {
    }

    @Override
    public String getOperatorTo() {
        return operatorTo;
    }

    @Override
    public void setOperatorTo(String operator) {
        operatorTo = operator;
    }

    @Override
    public String getOperatorFrom() {
        return operatorFrom;
    }

    @Override
    public void setOperatorFrom(String ope) {
        operatorFrom = ope;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultMessage other = (DefaultMessage) obj;
        if (this.operatorTo != other.operatorTo && (this.operatorTo == null || !this.operatorTo.equals(other.operatorTo))) {
            return false;
        }
        if (this.operatorFrom != other.operatorFrom && (this.operatorFrom == null || !this.operatorFrom.equals(other.operatorFrom))) {
            return false;
        }
        if (this.title != other.title && (this.title == null || !this.title.equals(other.title))) {
            return false;
        }
        if (this.message != other.message && (this.message == null || !this.message.equals(other.message))) {
            return false;
        }
        if (this.time != other.time && (this.time == null || !this.time.equals(other.time))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.operatorTo != null ? this.operatorTo.hashCode() : 0);
        hash = 41 * hash + (this.operatorFrom != null ? this.operatorFrom.hashCode() : 0);
        hash = 41 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 41 * hash + (this.message != null ? this.message.hashCode() : 0);
        hash = 41 * hash + (this.time != null ? this.time.hashCode() : 0);
        return hash;
    }

    public DefaultMessage(String operatorTo, String operatorFrom, String title, String message, Date time) {
        this.operatorTo = operatorTo;
        this.operatorFrom = operatorFrom;
        this.title = title;
        this.message = message;
        this.time = time;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void setID(String id) {
        this.id = id;
    }
}
