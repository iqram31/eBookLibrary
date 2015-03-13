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

import usu.perpustakaan.buku.data.template.OperatorActivity;
import java.util.Date;

/**
 *
 * @author usu
 */
public class DefaultOperatorActivity implements OperatorActivity {

    private String operator;
    private String activity;
    private Date time;

    public DefaultOperatorActivity() {
    }

    public DefaultOperatorActivity(String operator, String activity, Date time) {
        this.operator = operator;
        this.activity = activity;
        this.time = time;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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
        final DefaultOperatorActivity other = (DefaultOperatorActivity) obj;
        if (this.operator != other.operator && (this.operator == null || !this.operator.equals(other.operator))) {
            return false;
        }
        if (this.activity != other.activity && (this.activity == null || !this.activity.equals(other.activity))) {
            return false;
        }
        if (this.time != other.time && (this.time == null || !this.time.equals(other.time))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.operator != null ? this.operator.hashCode() : 0);
        hash = 73 * hash + (this.activity != null ? this.activity.hashCode() : 0);
        hash = 73 * hash + (this.time != null ? this.time.hashCode() : 0);
        return hash;
    }
}
