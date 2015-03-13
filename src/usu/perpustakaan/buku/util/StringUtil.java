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
package usu.perpustakaan.buku.util;

/**
 * @author usu
 */
public class StringUtil {

    /**
     * @param teks
     * @return
     */
    public static boolean containDigit(final String teks) {
        return usu.util.StringUtil.containDigit(teks);
    }

    /**
     * @param teks
     * @return
     */
    public static boolean containSemiColomn(final String teks) {
        return usu.util.StringUtil.containQuote(teks);
    }

    /**
     * @param c
     * @return
     */
    public static String getStringFromArrayChar(final char[] c) {
        return usu.util.StringUtil.convertToString(c);
    }

    /**
     * @deprecated
     */
    @Deprecated
    private StringUtil() {
    }
}
