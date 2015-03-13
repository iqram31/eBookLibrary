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

import usu.perpustakaan.buku.data.template.Book;

/**
 *
 * @author usu
 */
public class DefaultBook implements Book {

    private static final long serialVersionUID = -1;
    private String id,  publisher,  author,  title,  kind;

    /**
     * 
     */
    public DefaultBook() {
        this(null, null, null, null, null);
    }

    /**
     * 
     * @param id
     * @param publisher
     * @param author
     * @param title
     * @param kind
     */
    public DefaultBook(String id, String publisher, String author, String title, String kind) {
        this.id = id;
        this.publisher = publisher;
        this.author = author;
        this.title = title;
        this.kind = kind;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
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
    public String getPublisher() {
        return publisher;
    }

    @Override
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultBook other = (DefaultBook) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.publisher != other.publisher && (this.publisher == null || !this.publisher.equals(other.publisher))) {
            return false;
        }
        if (this.author != other.author && (this.author == null || !this.author.equals(other.author))) {
            return false;
        }
        if (this.title != other.title && (this.title == null || !this.title.equals(other.title))) {
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
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.publisher != null ? this.publisher.hashCode() : 0);
        hash = 53 * hash + (this.author != null ? this.author.hashCode() : 0);
        hash = 53 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 53 * hash + (this.kind != null ? this.kind.hashCode() : 0);
        return hash;
    }
}
