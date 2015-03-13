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

import usu.perpustakaan.buku.data.template.Regristration;
import java.util.Date;

/**
 *
 * @author usu
 */
public class DefaultRegristration implements Regristration {

    private String member;
    private String operator;
    private Date time;

    public DefaultRegristration() {
    }

    public DefaultRegristration(String member, String operator, Date time) {
        this.member = member;
        this.operator = operator;
        this.time = time;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultRegristration other = (DefaultRegristration) obj;
        if (this.member != other.member && (this.member == null || !this.member.equals(other.member))) {
            return false;
        }
        if (this.operator != other.operator && (this.operator == null || !this.operator.equals(other.operator))) {
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
        hash = 71 * hash + (this.member != null ? this.member.hashCode() : 0);
        hash = 71 * hash + (this.operator != null ? this.operator.hashCode() : 0);
        hash = 71 * hash + (this.time != null ? this.time.hashCode() : 0);
        return hash;
    }
}
