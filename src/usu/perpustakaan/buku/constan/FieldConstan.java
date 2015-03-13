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
package usu.perpustakaan.buku.constan;

/**
 * @author usu
 * @version 1.0.0
 * @since e'BookLibrary 1.0.0
 */
public interface FieldConstan {

   /**
    * kumpulan field tabel aktifitas operator
    * 
    * @author usu
    * @version 1.0.0
    * @since e'BookLibrary 1.0.0
    */
   public static interface TABEL_AKTIVITAS_OPERATOR {

      /**
       * nama field id operator
       */
      public static final String ID_OPERATOR = TableName.TABEL_AKTIVITAS_OPERATOR
            + ".ID_OPERATOR";
      /**
       * nama field kegiatan
       */
      public static final String KEGIATAN = TableName.TABEL_AKTIVITAS_OPERATOR
            + ".KEGIATAN";
      /**
       * nama field waktu
       */
      public static final String WAKTU = TableName.TABEL_AKTIVITAS_OPERATOR
            + ".WAKTU";
   }

   /**
    * kumpulan field tabel anggota
    * 
    * @author usu
    * @version 1.0.0
    * @since e'BookLibrary 1.0.0
    */
   public static interface TABEL_ANGGOTA {

      /**
       * nama field alamat anggota
       */
      public static final String ALAMAT_ANGGOTA = TableName.TABEL_ANGGOTA
            + ".ALAMAT_ANGGOTA";
      /**
       * nama field id anggota
       */
      public static final String ID_ANGGOTA = TableName.TABEL_ANGGOTA
            + ".ID_ANGGOTA";
      /**
       * nama field kontak anggota
       */
      public static final String KONTAK_ANGGOTA = TableName.TABEL_ANGGOTA
            + ".KONTAK_ANGGOTA";
      /**
       * nama field nama anggota
       */
      public static final String NAMA_ANGGOTA = TableName.TABEL_ANGGOTA
            + ".NAMA_ANGGOTA";
      /**
       * nama field tanggal lahir anggota
       */
      public static final String TANGGAL_LAHIR_ANGGOTA = TableName.TABEL_ANGGOTA
            + ".TANGGAL_LAHIR_ANGGOTA";
   }

   /**
    * kumpulan field tabel buku
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_BUKU {

      /**
       * nama field id buku
       */
      public static final String ID_BUKU = TableName.TABEL_BUKU + ".ID_BUKU";
      /**
       * nama field id jenis
       */
      public static final String ID_JENIS = TableName.TABEL_BUKU + ".ID_JENIS";
      /**
       * nama field id penerbit
       */
      public static final String ID_PENERBIT = TableName.TABEL_BUKU
            + ".ID_PENERBIT";
      /**
       * nama field id pengarang
       */
      public static final String ID_PENGARANG = TableName.TABEL_BUKU
            + ".ID_PENGARANG";
      /**
       * nama field judul buku
       */
      public static final String JUDUL_BUKU = TableName.TABEL_BUKU
            + ".JUDUL_BUKU";
   }

   /**
    * kumpulan nama field tabel jenis buku
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_JENIS_BUKU {

      /**
       * nama field id jenis
       */
      public static final String ID_JENIS = TableName.TABEL_JENIS_BUKU
            + ".ID_JENIS";
      /**
       * nama field jenis buku
       */
      public static final String JENIS_BUKU = TableName.TABEL_JENIS_BUKU
            + ".JENIS_BUKU";
   }

   /**
    * kumpulan nama field tabel operator
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_OPERATOR {

      /**
       * nama field alamat operator
       */
      public static final String ALAMAT_OPERATOR = TableName.TABEL_OPERATOR
            + ".ALAMAT_OPERATOR";
      /**
       * nama field id operator
       */
      public static final String ID_OPERATOR = TableName.TABEL_OPERATOR
            + ".ID_OPERATOR";
      /**
       * nama field kontak operator
       */
      public static final String KONTAK_OPERATOR = TableName.TABEL_OPERATOR
            + ".KONTAK_OPERATOR";
      /**
       * nama field nama operator
       */
      public static final String NAMA_OPERATOR = TableName.TABEL_OPERATOR
            + ".NAMA_OPERATOR";
      /**
       * nama field password operator
       */
      public static final String PASSWORD_OPERATOR = TableName.TABEL_OPERATOR
            + ".PASSWORD_OPERATOR";
      /**
       * nama field tabggal lahir operator
       */
      public static final String TANGGAL_LAHIR_OPERATOR = TableName.TABEL_OPERATOR
            + ".TANGGAL_LAHIR_OPERATOR";
   }

   /**
    * kumpulan nama field tabel peminjaman
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_PEMINJAMAN {

      /**
       * nama field id anggota
       */
      public static final String ID_ANGGOTA = TableName.TABEL_PEMINJAMAN
            + ".ID_ANGGOTA";
      /**
       * nama field id buku
       */
      public static final String ID_BUKU = TableName.TABEL_PEMINJAMAN
            + ".ID_BUKU";
      /**
       * nama field id anggota
       */
      public static final String ID_OPERATOR = TableName.TABEL_PEMINJAMAN
            + ".ID_OPERATOR";
      /**
       * nama field id peminjaman
       */
      public static final String ID_PEMINJAMAN = TableName.TABEL_PEMINJAMAN
            + ".ID_PEMINJAMAN";
      /**
       * nama field tanggal peminjaman
       */
      public static final String TANGGAL_PEMINJAMAN = TableName.TABEL_PEMINJAMAN
            + ".TANGGAL_PEMINJAMAN";
      /**
       * nama field tanggal pengembalian
       */
      public static final String TANGGAL_PENGEMBALIAN = TableName.TABEL_PEMINJAMAN
            + ".TANGGAL_PENGEMBALIAN";
      /**
       * nama field telah dikembalikan
       */
      public static final String TELAH_DIKEMBALIKAN = TableName.TABEL_PEMINJAMAN
            + ".TELAH_DIKEMBALIKAN";
   }

   /**
    * kumpulan nama field tabel pendaftaran
    */
   public static interface TABEL_PENDAFTARAN {

      /**
       * nama field id anggota
       * 
       * @author usu
       * @since e'BookLibrary 1.0.0
       * @version 1.0.0
       */
      public static final String ID_ANGGOTA = TableName.TABEL_PENDAFTARAN
            + ".ID_ANGGOTA";
      /**
       * nama field id operator
       */
      public static final String ID_OPERATOR = TableName.TABEL_PENDAFTARAN
            + ".ID_OPERATOR";
      /**
       * nama field tanggal pendaftaran
       */
      public static final String TANGGAL_PENDAFTARAN = TableName.TABEL_PENDAFTARAN
            + ".TANGGAL_PENDAFTARAN";
   }

   /**
    * kumpulan nama field tabel penerbit
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_PENERBIT {

      /**
       * nama field id penerbit
       */
      public static final String ID_PENERBIT = TableName.TABEL_PENERBIT
            + ".ID_PENERBIT";
      /**
       * nama field nama penerbit
       */
      public static final String NAMA_PENERBIT = TableName.TABEL_PENERBIT
            + ".NAMA_PENERBIT";
   }

   /**
    * kumpulan nama field tabel pengarang
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_PENGARANG {

      /**
       * nama field id pengarang
       */
      public static final String ID_PENGARANG = TableName.TABEL_PENGARANG
            + ".ID_PENGARANG";
      /**
       * nama field nama pengarang
       */
      public static final String NAMA_PENGARANG = TableName.TABEL_PENGARANG
            + ".NAMA_PENGARANG";
   }

   /**
    * kumpulan nama field tabel pengembalian
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_PENGEMBALIAN {

      /**
       * nama field denda
       */
      public static String DENDA = TableName.TABEL_PENGEMBALIAN + ".DENDA";
      /**
       * nama field id operator
       */
      public static final String ID_OPERATOR = TableName.TABEL_PENGEMBALIAN
            + ".ID_OPERATOR";
      /**
       * nama field id peminjaman
       */
      public static final String ID_PEMINJAMAN = TableName.TABEL_PENGEMBALIAN
            + ".ID_PEMINJAMAN";
      /**
       * nama field tanggal pengembalian
       */
      public static final String TANGGAL_PENGEMBALIAN = TableName.TABEL_PENGEMBALIAN
            + ".TANGGAL_PENGEMBALIAN";
      /**
       * nama field terlambat
       */
      public static final String TERLAMBAT = TableName.TABEL_PENGEMBALIAN
            + ".TERLAMBAT";
   }

   /**
    * kumpulan field tabel pesan
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_PESAN {

      /**
       * nama field tabel operator from
       */
      public static final String ID_OPERATOR_FROM = TableName.TABEL_PESAN
            + ".ID_OPERATOR_FROM";
      /**
       * nama field id operator to
       */
      public static final String ID_OPERATOR_TO = TableName.TABEL_PESAN
            + ".ID_OPERATOR_TO";
      /**
       * nama field id pesan
       */
      public static String ID_PESAN = TableName.TABEL_PESAN + ".ID_PESAN";
      /**
       * nama field judul
       */
      public static final String JUDUL = TableName.TABEL_PESAN + ".JUDUL";
      /**
       * nama field pesan
       */
      public static final String PESAN = TableName.TABEL_PESAN + ".PESAN";
      /**
       * nama field waktu
       */
      public static final String WAKTU = TableName.TABEL_PESAN + ".WAKTU";
   }

   /**
    * kumpulan field tabel setting
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_SETTING {

      /**
       * nama field denda keterlambatan
       */
      public static final String DENDA_KETERLAMBATAN = TableName.TABEL_SETTING
            + ".DENDA_KETERLAMBATAN";
      /**
       * nama field maksimal peminjaman
       */
      public static final String MAKSIMAL_PEMINJAMAN = TableName.TABEL_SETTING
            + ".MAKSIMAL_PEMINJAMAN";
      /**
       * nama field waktu peminjaman
       */
      public static final String WAKTU_PEMINJAMAN = TableName.TABEL_SETTING
            + ".WAKTU_PEMINJAMAN";
   }

   /**
    * kumpulan nama field tabel status anggota
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_STATUS_ANGGOTA {

      /**
       * nama field id anggota
       */
      public static final String ID_ANGGOTA = TableName.TABEL_STATUS_ANGGOTA
            + ".ID_ANGGOTA";
      /**
       * nama field total meminjam
       */
      public static final String TOTAL_MEMINJAM = TableName.TABEL_STATUS_ANGGOTA
            + ".TOTAL_MEMINJAM";
   }

   /**
    * kumpulan nama field tabel status buku
    * 
    * @author usu
    * @since e'BookLibrary 1.0.0
    * @version 1.0.0
    */
   public static interface TABEL_STATUS_BUKU {

      /**
       * nama field id buku
       */
      public static final String ID_BUKU = TableName.TABEL_STATUS_BUKU
            + ".ID_BUKU";
      /**
       * nama field sedang dipinjam
       */
      public static final String SEDANG_DIPINJAM = TableName.TABEL_STATUS_BUKU
            + ".SEDANG_DIPINJAM";
      /**
       * nama field total dipinjam
       */
      public static final String TOTAL_DIPINJAM = TableName.TABEL_STATUS_BUKU
            + ".TOTAL_DIPINJAM";
   }
}
