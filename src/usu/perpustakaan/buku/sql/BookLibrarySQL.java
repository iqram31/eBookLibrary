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
package usu.perpustakaan.buku.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Timestamp;
import usu.perpustakaan.buku.constan.FieldConstan;
import usu.perpustakaan.buku.constan.TableName;
import usu.perpustakaan.buku.data.DefaultBook;
import usu.perpustakaan.buku.data.DefaultBookGuest;
import usu.perpustakaan.buku.data.DefaultMember;
import usu.perpustakaan.buku.data.DefaultMessage;
import usu.perpustakaan.buku.data.DefaultOperator;
import usu.perpustakaan.buku.data.DefaultSettingLibrary;
import usu.perpustakaan.buku.data.template.Author;
import usu.perpustakaan.buku.data.template.Book;
import usu.perpustakaan.buku.data.template.Borrow;
import usu.perpustakaan.buku.data.template.KindBook;
import usu.perpustakaan.buku.data.template.Member;
import usu.perpustakaan.buku.data.template.Operator;
import usu.perpustakaan.buku.data.template.Publisher;
import usu.perpustakaan.buku.data.template.SettingLibrary;
import usu.widget.util.WidgetUtilities;

/**
 * @author usu
 * @version 1.0.0
 * @category JDBC MySQl for e'BookLibrary
 */
public class BookLibrarySQL {

   private static DefaultBook book;
   private static DefaultOperator operator;
   private static PreparedStatement prepareStatement;
   private static ResultSet resultSet;
   private static DefaultSettingLibrary settingLibrary;
   private static Statement statement;
   private static boolean tempBoolean;
   private static Date tempDate;
   private static int tempInteger;
   private static DefaultMember tempMember;
   private static String tempString;

   /**
    * mengecek apakah dalam database ada operator
    * 
    * @param connection
    * @param member_id
    * @return true jika terdapat operator dengan username dan password tersebut dan false jika tak
    *         ada
    * @throws java.sql.SQLException
    */
   public static boolean containMember(final Connection connection,
         final String member_id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_ANGGOTA
                  + " WHERE " + FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA + "=?");
      BookLibrarySQL.prepareStatement.setString(1, member_id);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.tempBoolean = true;
      } else {
         BookLibrarySQL.tempBoolean = false;
      }
      BookLibrarySQL.statement.close();
      BookLibrarySQL.resultSet.close();
      return BookLibrarySQL.tempBoolean;
   }

   /**
    * mengecek apakah ada operator dengan id seperti dalam parameter
    * 
    * @param connection
    * @param operator_id
    * @param operator_password
    * @return {@link Operator} dengan id tersebut
    * @throws java.sql.SQLException
    */
   public static Operator containOperator(final Connection connection,
         final String operator_id, final String operator_password)
         throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_OPERATOR
                  + " WHERE " + FieldConstan.TABEL_OPERATOR.ID_OPERATOR + "=? "
                  + " AND " + FieldConstan.TABEL_OPERATOR.PASSWORD_OPERATOR
                  + "=?");
      BookLibrarySQL.prepareStatement.setString(1, operator_id);
      BookLibrarySQL.prepareStatement.setString(2, operator_password);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         final Operator o = new DefaultOperator();
         o.setAddress(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_OPERATOR.ALAMAT_OPERATOR));
         o.setBorn(BookLibrarySQL.resultSet
               .getDate(FieldConstan.TABEL_OPERATOR.TANGGAL_LAHIR_OPERATOR));
         o.setContact(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_OPERATOR.KONTAK_OPERATOR));
         o.setId(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_OPERATOR.ID_OPERATOR));
         o.setName(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_OPERATOR.NAMA_OPERATOR));
         o.setPassword(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_OPERATOR.PASSWORD_OPERATOR));
         BookLibrarySQL.resultSet.close();
         return o;
      }
      BookLibrarySQL.resultSet.close();
      return null;
   }

   /**
    * menghapus pengarang dalam database
    * 
    * @param connection
    * @param author_id
    *           pengarang
    * @throws java.sql.SQLException
    */
   public static void deleteAuthor(final Connection connection,
         final String author_id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("DELETE FROM " + TableName.TABEL_PENGARANG
                  + " WHERE " + FieldConstan.TABEL_PENGARANG.ID_PENGARANG
                  + "=?");
      BookLibrarySQL.prepareStatement.setString(1, author_id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * menghapus buku yang ada dalam database berdasarkan id
    * 
    * @param connection
    * @param book_id
    *           buku yang akan dihapus
    * @throws java.sql.SQLException
    */
   public static void deleteBook(final Connection connection,
         final String book_id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("DELETE FROM " + TableName.TABEL_BUKU + " WHERE "
                  + FieldConstan.TABEL_BUKU.ID_BUKU + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, book_id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * menghapus jenis buku dalam database
    * 
    * @param connection
    * @param kindbook_id
    *           jenis buku yang akan dihapus
    * @throws java.sql.SQLException
    */
   public static void deleteKindBook(final Connection connection,
         final String kindbook_id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("DELETE FROM " + TableName.TABEL_JENIS_BUKU
                  + " WHERE " + FieldConstan.TABEL_JENIS_BUKU.ID_JENIS + "=?");
      BookLibrarySQL.prepareStatement.setString(1, kindbook_id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * menghapus anggota dalam database
    * 
    * @param connection
    * @param member_id
    *           anggota yang akan dihapus
    * @throws java.sql.SQLException
    */
   public static void deleteMember(final Connection connection,
         final String member_id) throws SQLException {
      connection.setAutoCommit(false);
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("DELETE FROM " + TableName.TABEL_PENDAFTARAN
                  + " WHERE " + FieldConstan.TABEL_PENDAFTARAN.ID_ANGGOTA
                  + "=?");
      BookLibrarySQL.prepareStatement.setString(1, member_id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("DELETE FROM " + TableName.TABEL_STATUS_ANGGOTA
                  + " WHERE " + FieldConstan.TABEL_STATUS_ANGGOTA.ID_ANGGOTA
                  + "=?");
      BookLibrarySQL.prepareStatement.setString(1, member_id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("DELETE FROM " + TableName.TABEL_ANGGOTA
                  + " WHERE " + FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, member_id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      connection.commit();
      connection.setAutoCommit(true);
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * menghapus pesan dalam database
    * 
    * @param connection
    * @param message_id
    *           pesan yang akan dihapus
    * @throws java.sql.SQLException
    */
   public static void deleteMessage(final Connection connection,
         final String message_id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("DELETE FROM " + TableName.TABEL_PESAN
                  + " WHERE " + FieldConstan.TABEL_PESAN.ID_PESAN + "=?");
      BookLibrarySQL.prepareStatement.setString(1, message_id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * menghapus operator dalam database
    * 
    * @param connection
    * @param operator_id
    *           operator yang akan dihapus
    * @throws java.sql.SQLException
    */
   public static void deleteOperator(final Connection connection,
         final String operator_id) throws SQLException {
      connection.setAutoCommit(false);
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("DELETE FROM "
                  + TableName.TABEL_AKTIVITAS_OPERATOR + " WHERE "
                  + FieldConstan.TABEL_AKTIVITAS_OPERATOR.ID_OPERATOR + "=?");
      BookLibrarySQL.prepareStatement.setString(1, operator_id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("DELETE FROM " + TableName.TABEL_OPERATOR
                  + " WHERE " + FieldConstan.TABEL_OPERATOR.ID_OPERATOR + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, operator_id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      connection.commit();
      connection.setAutoCommit(true);
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * menghapud semua aktivitas operator
    * 
    * @param connection
    * @throws java.sql.SQLException
    */
   public static void deleteOperatorActivity(final Connection connection)
         throws SQLException {
      BookLibrarySQL.statement = connection.createStatement();
      BookLibrarySQL.statement.executeUpdate("DELETE FROM "
            + TableName.TABEL_AKTIVITAS_OPERATOR);
      BookLibrarySQL.statement.close();
   }

   /**
    * menghapus penerbit
    * 
    * @param connection
    * @param publisher_id
    *           penerbit yang akan dihapus
    * @throws java.sql.SQLException
    */
   public static void deletePublisher(final Connection connection,
         final String publisher_id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("DELETE FROM " + TableName.TABEL_PENERBIT
                  + " WHERE " + FieldConstan.TABEL_PENERBIT.ID_PENERBIT + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, publisher_id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * mendapatkan buku lengkap dengan nama pengarang, penerbit dan jenis buku
    * 
    * @param connection
    * @param book
    *           buku yang belum lengkap
    * @return {@link Book}
    * @throws java.sql.SQLException
    */
   public static Book getBook(final Connection connection, final Book book)
         throws SQLException {
      book.setKind(BookLibrarySQL.getBookKind(connection, book.getKind()));
      book
            .setAuthor(BookLibrarySQL.getBookAuthor(connection, book
                  .getAuthor()));
      book.setPublisher(BookLibrarySQL.getBookPublisher(connection, book
            .getPublisher()));
      return book;
   }

   /**
    * mendapatkan buku berdasarkan id
    * 
    * @param connection
    * @param book_id
    *           buku
    * @return {@link Book}
    * @throws java.sql.SQLException
    */
   public static Book getBook(final Connection connection, final String book_id)
         throws SQLException {
      BookLibrarySQL.book = new DefaultBook();
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_BUKU
                  + " WHERE " + FieldConstan.TABEL_BUKU.ID_BUKU + "=?");
      BookLibrarySQL.prepareStatement.setString(1, book_id);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.book.setId(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_BUKU.ID_BUKU));
         BookLibrarySQL.book.setAuthor(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_BUKU.ID_PENGARANG));
         BookLibrarySQL.book.setKind(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_BUKU.ID_JENIS));
         BookLibrarySQL.book.setPublisher(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_BUKU.ID_PENERBIT));
         BookLibrarySQL.book.setTitle(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_BUKU.JUDUL_BUKU));
      }
      BookLibrarySQL.resultSet.close();
      BookLibrarySQL.statement.close();
      return BookLibrarySQL.book;
   }

   /**
    * mendapatkan nama pengarang berdasarkan id pengarang
    * 
    * @param connection
    * @param author_id
    * @return nama pengarang
    * @throws java.sql.SQLException
    */
   public static String getBookAuthor(final Connection connection,
         final String author_id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_PENGARANG
                  + " WHERE " + FieldConstan.TABEL_PENGARANG.ID_PENGARANG
                  + "=?");
      BookLibrarySQL.prepareStatement.setString(1, author_id);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.tempString = BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_PENGARANG.NAMA_PENGARANG);
      } else {
         BookLibrarySQL.tempString = null;
      }
      BookLibrarySQL.resultSet.close();
      return BookLibrarySQL.tempString;
   }

   /**
    * mendapatkan nama jenis buku berdasarkan id jenis buku
    * 
    * @param c
    * @param kindbook_id
    * @return nama jenis buku
    * @throws java.sql.SQLException
    */
   public static String getBookKind(final Connection c, final String kindbook_id)
         throws SQLException {
      BookLibrarySQL.prepareStatement = c.prepareStatement("SELECT * FROM "
            + TableName.TABEL_JENIS_BUKU + " WHERE "
            + FieldConstan.TABEL_JENIS_BUKU.ID_JENIS + "=?");
      BookLibrarySQL.prepareStatement.setString(1, kindbook_id);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.tempString = BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_JENIS_BUKU.JENIS_BUKU);
      } else {
         BookLibrarySQL.tempString = null;
      }
      BookLibrarySQL.resultSet.close();
      return BookLibrarySQL.tempString;
   }

   /**
    * mendapatkan nama penerbit buku
    * 
    * @param c
    * @param publisher_id
    * @return nama penerbit buku
    * @throws java.sql.SQLException
    */
   public static String getBookPublisher(final Connection c,
         final String publisher_id) throws SQLException {
      BookLibrarySQL.prepareStatement = c.prepareStatement("SELECT * FROM "
            + TableName.TABEL_PENERBIT + " WHERE "
            + FieldConstan.TABEL_PENERBIT.ID_PENERBIT + "=?");
      BookLibrarySQL.prepareStatement.setString(1, publisher_id);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.tempString = BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_PENERBIT.NAMA_PENERBIT);
      } else {
         BookLibrarySQL.tempString = null;
      }
      BookLibrarySQL.resultSet.close();
      return BookLibrarySQL.tempString;
   }

   /**
    * mendapatkan maksimal banyak peminjaman buku
    * 
    * @param c
    * @return maksimal buku yang boleh dipinjam
    * @throws java.sql.SQLException
    */
   public static int getMaxBorrow(final Connection c) throws SQLException {
      BookLibrarySQL.prepareStatement = c.prepareStatement("SELECT * FROM "
            + TableName.TABEL_SETTING);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      BookLibrarySQL.tempInteger = 0;
      if (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.tempInteger = BookLibrarySQL.resultSet
               .getInt(FieldConstan.TABEL_SETTING.MAKSIMAL_PEMINJAMAN);
      }
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static int getMaxDayBorro(final Connection connection)
         throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_SETTING);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.tempInteger = BookLibrarySQL.resultSet
               .getInt(FieldConstan.TABEL_SETTING.WAKTU_PEMINJAMAN);
      } else {
         BookLibrarySQL.tempInteger = 0;
      }
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param text
    * @return
    * @throws java.sql.SQLException
    */
   public static Member getMember(final Connection connection, final String text)
         throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_ANGGOTA
                  + " WHERE " + FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA + "=?");
      BookLibrarySQL.prepareStatement.setString(1, text);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.tempMember = new DefaultMember();
         BookLibrarySQL.tempMember.setAddress(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_ANGGOTA.ALAMAT_ANGGOTA));
         BookLibrarySQL.tempMember.setBorn(BookLibrarySQL.resultSet
               .getDate(FieldConstan.TABEL_ANGGOTA.TANGGAL_LAHIR_ANGGOTA));
         BookLibrarySQL.tempMember.setContact(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_ANGGOTA.KONTAK_ANGGOTA));
         BookLibrarySQL.tempMember.setId(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA));
         BookLibrarySQL.tempMember.setName(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_ANGGOTA.NAMA_ANGGOTA));
         return BookLibrarySQL.tempMember;
      }
      return null;
   }

   /**
    * @param connection
    * @param id
    * @return
    * @throws java.sql.SQLException
    */
   public static Operator getOperator(final Connection connection,
         final String id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_OPERATOR
                  + " WHERE " + FieldConstan.TABEL_OPERATOR.ID_OPERATOR + "=?");
      BookLibrarySQL.prepareStatement.setString(1, id);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         final DefaultOperator op = new DefaultOperator();
         op.setAddress(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_OPERATOR.ALAMAT_OPERATOR));
         op.setBorn(BookLibrarySQL.resultSet
               .getDate(FieldConstan.TABEL_OPERATOR.TANGGAL_LAHIR_OPERATOR));
         op.setContact(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_OPERATOR.KONTAK_OPERATOR));
         op.setId(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_OPERATOR.ID_OPERATOR));
         op.setName(BookLibrarySQL.resultSet
               .getString(FieldConstan.TABEL_OPERATOR.NAMA_OPERATOR));
         return op;
      }
      return null;
   }

   /**
    * @param result
    * @return
    * @throws java.sql.SQLException
    */
   public static Operator getOperatorFromResultSet(final ResultSet result)
         throws SQLException {
      BookLibrarySQL.operator = new DefaultOperator();
      BookLibrarySQL.operator.setId(result
            .getString(FieldConstan.TABEL_OPERATOR.ID_OPERATOR));
      BookLibrarySQL.operator.setName(result
            .getString(FieldConstan.TABEL_OPERATOR.NAMA_OPERATOR));
      BookLibrarySQL.operator.setAddress(result
            .getString(FieldConstan.TABEL_OPERATOR.ALAMAT_OPERATOR));
      BookLibrarySQL.operator.setContact(result
            .getString(FieldConstan.TABEL_OPERATOR.KONTAK_OPERATOR));
      BookLibrarySQL.operator.setBorn(result
            .getDate(FieldConstan.TABEL_OPERATOR.TANGGAL_LAHIR_OPERATOR));
      BookLibrarySQL.operator.setPassword(result
            .getString(FieldConstan.TABEL_OPERATOR.PASSWORD_OPERATOR));
      return BookLibrarySQL.operator;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static int getPayForLate(final Connection connection)
         throws SQLException {
      final PreparedStatement p = connection.prepareStatement("SELECT * FROM "
            + TableName.TABEL_SETTING);
      final ResultSet r = p.executeQuery();
      int temp = 0;
      if (r.next()) {
         temp = r.getInt(FieldConstan.TABEL_SETTING.DENDA_KETERLAMBATAN);
      }
      p.close();
      r.close();
      return temp;
   }

   /**
    * @param c
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultAuthor(final Connection c)
         throws SQLException {
      BookLibrarySQL.statement = c.createStatement();
      BookLibrarySQL.resultSet = BookLibrarySQL.statement
            .executeQuery("SELECT * FROM " + TableName.TABEL_PENGARANG);
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param c
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultBook(final Connection c)
         throws SQLException {
      BookLibrarySQL.prepareStatement = c.prepareStatement("SELECT * FROM "
            + TableName.TABEL_BUKU);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @param b
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultBook(final Connection connection,
         final boolean b) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_BUKU
                  + " JOIN " + TableName.TABEL_STATUS_BUKU + " WHERE "
                  + FieldConstan.TABEL_BUKU.ID_BUKU + "="
                  + FieldConstan.TABEL_STATUS_BUKU.ID_BUKU + " AND "
                  + FieldConstan.TABEL_STATUS_BUKU.SEDANG_DIPINJAM + "=?");
      BookLibrarySQL.prepareStatement.setBoolean(1, b);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultBookGuest(final Connection connection)
         throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_BUKU
                  + " JOIN " + TableName.TABEL_STATUS_BUKU + " WHERE "
                  + FieldConstan.TABEL_BUKU.ID_BUKU + "="
                  + FieldConstan.TABEL_STATUS_BUKU.ID_BUKU);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @param b
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultBookGuest(final Connection connection,
         final DefaultBookGuest b) throws SQLException {
      BookLibrarySQL.tempString = "SELECT * FROM " + TableName.TABEL_BUKU + ","
            + TableName.TABEL_PENERBIT + "," + TableName.TABEL_PENGARANG + ","
            + TableName.TABEL_JENIS_BUKU + "," + TableName.TABEL_STATUS_BUKU
            + " WHERE " + FieldConstan.TABEL_BUKU.ID_JENIS + "="
            + FieldConstan.TABEL_JENIS_BUKU.ID_JENIS + " AND "
            + FieldConstan.TABEL_BUKU.ID_PENERBIT + "="
            + FieldConstan.TABEL_PENERBIT.ID_PENERBIT + " AND "
            + FieldConstan.TABEL_BUKU.ID_PENGARANG + "="
            + FieldConstan.TABEL_PENGARANG.ID_PENGARANG + " AND "
            + FieldConstan.TABEL_BUKU.ID_BUKU + "="
            + FieldConstan.TABEL_STATUS_BUKU.ID_BUKU;
      if ((b.getAuthor() != null) || (b.getId() != null)
            || (b.getKind() != null) || (b.getPublisher() != null)
            || (b.getTitle() != null) || (b.isBorrow() != null)) {
         BookLibrarySQL.tempString += " AND ";
      } else {
         return BookLibrarySQL.getResultBookGuest(connection);
      }
      if (b.getAuthor() != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PENGARANG.NAMA_PENGARANG
               + " LIKE ? ";
         if ((b.getId() != null) || (b.getKind() != null)
               || (b.getPublisher() != null) || (b.getTitle() != null)
               || (b.isBorrow() != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (b.getId() != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_BUKU.ID_BUKU
               + " LIKE ? ";
         if ((b.getKind() != null) || (b.getPublisher() != null)
               || (b.getTitle() != null) || (b.isBorrow() != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (b.getKind() != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_JENIS_BUKU.JENIS_BUKU
               + " LIKE ? ";
         if ((b.getPublisher() != null) || (b.getTitle() != null)
               || (b.isBorrow() != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (b.getPublisher() != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PENERBIT.NAMA_PENERBIT
               + " LIKE ? ";
         if ((b.getTitle() != null) || (b.isBorrow() != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (b.getTitle() != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_BUKU.JUDUL_BUKU
               + " LIKE ? ";
         if (b.isBorrow() != null) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (b.isBorrow() != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_STATUS_BUKU.SEDANG_DIPINJAM
               + "=?";
      }
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement(BookLibrarySQL.tempString);
      BookLibrarySQL.tempInteger = 0;
      if (b.getAuthor() != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + b.getAuthor() + "%");
      }
      if (b.getId() != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + b.getId() + "%");
      }
      if (b.getKind() != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + b.getKind() + "%");
      }
      if (b.getPublisher() != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + b.getPublisher() + "%");
      }
      if (b.getTitle() != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + b.getTitle() + "%");
      }
      if (b.isBorrow() != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setBoolean(BookLibrarySQL.tempInteger,
               b.isBorrow().booleanValue());
      }
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultBorrowFalse(final Connection connection)
         throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_PEMINJAMAN
                  + " WHERE "
                  + FieldConstan.TABEL_PEMINJAMAN.TELAH_DIKEMBALIKAN + "=?;");
      BookLibrarySQL.prepareStatement.setBoolean(1, false);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultBorrowReport(final Connection connection)
         throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_PEMINJAMAN);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @param memberId
    * @param operatorId
    * @param bookId
    * @param dateFrom
    * @param dateUntil
    * @param aReturn
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultBorrowReport(final Connection connection,
         final String memberId, final String operatorId, final String bookId,
         final java.util.Date dateFrom, final java.util.Date dateUntil,
         final Boolean aReturn) throws SQLException {
      String SQL = "SELECT * FROM " + TableName.TABEL_PEMINJAMAN;
      if ((memberId != null) || (operatorId != null) || (bookId != null)
            || (dateFrom != null) || (dateUntil != null) || (aReturn != null)) {
         SQL += " WHERE ";
      }
      if (memberId != null) {
         SQL += FieldConstan.TABEL_PEMINJAMAN.ID_ANGGOTA + " LIKE ? ";
         if ((operatorId != null) || (bookId != null) || (dateFrom != null)
               || (dateUntil != null) || (aReturn != null)) {
            SQL += " AND ";
         }
      }
      if (operatorId != null) {
         SQL += FieldConstan.TABEL_PEMINJAMAN.ID_OPERATOR + " LIKE ? ";
         if ((bookId != null) || (dateFrom != null) || (dateUntil != null)
               || (aReturn != null)) {
            SQL += " AND ";
         }
      }
      if (bookId != null) {
         SQL += FieldConstan.TABEL_PEMINJAMAN.ID_BUKU + " LIKE ? ";
         if ((dateFrom != null) || (dateUntil != null) || (aReturn != null)) {
            SQL += " AND ";
         }
      }
      if (dateFrom != null) {
         SQL += FieldConstan.TABEL_PEMINJAMAN.TANGGAL_PEMINJAMAN + " >= ? ";
         if ((dateUntil != null) || (aReturn != null)) {
            SQL += " AND ";
         }
      }
      if (dateUntil != null) {
         SQL += FieldConstan.TABEL_PEMINJAMAN.TANGGAL_PEMINJAMAN + " <= ? ";
         if (aReturn != null) {
            SQL += " AND ";
         }
      }
      if (aReturn != null) {
         SQL += FieldConstan.TABEL_PEMINJAMAN.TELAH_DIKEMBALIKAN + " =? ";
      }
      BookLibrarySQL.prepareStatement = connection.prepareStatement(SQL);
      BookLibrarySQL.tempInteger = 0;
      if (memberId != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + memberId + "%");
      }
      if (operatorId != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + operatorId + "%");
      }
      if (bookId != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + bookId + "%");
      }
      if (dateFrom != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setTimestamp(
               BookLibrarySQL.tempInteger, new Timestamp(dateFrom.getTime()));
      }
      if (dateUntil != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setTimestamp(
               BookLibrarySQL.tempInteger, new Timestamp(dateUntil.getTime()));
      }
      if (aReturn != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setBoolean(BookLibrarySQL.tempInteger,
               aReturn.booleanValue());
      }
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param c
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultKindBook(final Connection c)
         throws SQLException {
      BookLibrarySQL.statement = c.createStatement();
      BookLibrarySQL.resultSet = BookLibrarySQL.statement
            .executeQuery("SELECT * FROM " + TableName.TABEL_JENIS_BUKU);
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultMember(final Connection connection)
         throws SQLException {
      BookLibrarySQL.statement = connection.createStatement();
      BookLibrarySQL.resultSet = BookLibrarySQL.statement
            .executeQuery("SELECT * FROM " + TableName.TABEL_ANGGOTA);
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultMemberReport(final Connection connection)
         throws SQLException {
      BookLibrarySQL.statement = connection.createStatement();
      BookLibrarySQL.resultSet = BookLibrarySQL.statement
            .executeQuery("SELECT * FROM " + TableName.TABEL_ANGGOTA + ","
                  + TableName.TABEL_STATUS_ANGGOTA + ","
                  + TableName.TABEL_PENDAFTARAN + " WHERE "
                  + FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA + "="
                  + FieldConstan.TABEL_STATUS_ANGGOTA.ID_ANGGOTA + " AND "
                  + FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA + "="
                  + FieldConstan.TABEL_PENDAFTARAN.ID_ANGGOTA + " AND "
                  + FieldConstan.TABEL_PENDAFTARAN.ID_ANGGOTA + "="
                  + FieldConstan.TABEL_STATUS_ANGGOTA.ID_ANGGOTA);
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @param iDAnggota
    * @param namaAnggota
    * @param lahirFrom
    * @param lahirUntil
    * @param daftarFrom
    * @param daftarUntil
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultMemberReport(final Connection connection,
         final String iDAnggota, final String namaAnggota,
         final java.util.Date lahirFrom, final java.util.Date lahirUntil,
         final java.util.Date daftarFrom, final java.util.Date daftarUntil)
         throws SQLException {
      BookLibrarySQL.tempString = "SELECT * FROM " + TableName.TABEL_ANGGOTA
            + "," + TableName.TABEL_STATUS_ANGGOTA + ","
            + TableName.TABEL_PENDAFTARAN + " WHERE "
            + FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA + "="
            + FieldConstan.TABEL_STATUS_ANGGOTA.ID_ANGGOTA + " AND "
            + FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA + "="
            + FieldConstan.TABEL_PENDAFTARAN.ID_ANGGOTA + " AND "
            + FieldConstan.TABEL_PENDAFTARAN.ID_ANGGOTA + "="
            + FieldConstan.TABEL_STATUS_ANGGOTA.ID_ANGGOTA;
      if ((iDAnggota == null) && (namaAnggota == null) && (lahirFrom == null)
            && (lahirUntil == null) && (daftarFrom == null)
            && (daftarUntil == null)) {
         return BookLibrarySQL.getResultMemberReport(connection);
      }
      BookLibrarySQL.tempString += " AND ";
      if (iDAnggota != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA
               + " LIKE ? ";
         if ((namaAnggota != null) || (lahirFrom != null)
               || (lahirUntil != null) || (daftarFrom != null)
               || (daftarUntil != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (namaAnggota != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_ANGGOTA.NAMA_ANGGOTA
               + " LIKE ? ";
         if ((lahirFrom != null) || (lahirUntil != null)
               || (daftarFrom != null) || (daftarUntil != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (lahirFrom != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_ANGGOTA.TANGGAL_LAHIR_ANGGOTA
               + " >= ? ";
         if ((lahirUntil != null) || (daftarFrom != null)
               || (daftarUntil != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (lahirUntil != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_ANGGOTA.TANGGAL_LAHIR_ANGGOTA
               + " <= ? ";
         if ((daftarFrom != null) || (daftarUntil != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (daftarFrom != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PENDAFTARAN.TANGGAL_PENDAFTARAN
               + " >= ? ";
         if (daftarUntil != null) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (daftarUntil != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PENDAFTARAN.TANGGAL_PENDAFTARAN
               + " >= ? ";
      }
      BookLibrarySQL.tempInteger = 0;
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement(BookLibrarySQL.tempString);
      if (iDAnggota != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + iDAnggota + "%");
      }
      if (namaAnggota != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + namaAnggota + "%");
      }
      if (lahirFrom != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setDate(BookLibrarySQL.tempInteger,
               new Date(lahirFrom.getTime()));
      }
      if (lahirUntil != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setDate(BookLibrarySQL.tempInteger,
               new Date(lahirUntil.getTime()));
      }
      if (daftarFrom != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setDate(BookLibrarySQL.tempInteger,
               new Date(daftarFrom.getTime()));
      }
      if (daftarUntil != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setDate(BookLibrarySQL.tempInteger,
               new Date(daftarUntil.getTime()));
      }
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @param operator
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultMessage(final Connection connection,
         final String operator) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_PESAN
                  + " WHERE " + FieldConstan.TABEL_PESAN.ID_OPERATOR_TO + "=?");
      BookLibrarySQL.prepareStatement.setString(1, operator);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultOperator(final Connection connection)
         throws SQLException {
      BookLibrarySQL.statement = connection.createStatement();
      BookLibrarySQL.resultSet = BookLibrarySQL.statement
            .executeQuery("SELECT * FROM " + TableName.TABEL_OPERATOR);
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultOperatorActivity(final Connection connection)
         throws SQLException {
      BookLibrarySQL.statement = connection.createStatement();
      BookLibrarySQL.resultSet = BookLibrarySQL.statement
            .executeQuery("SELECT * FROM " + TableName.TABEL_AKTIVITAS_OPERATOR);
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param c
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultPublisher(final Connection c)
         throws SQLException {
      BookLibrarySQL.statement = c.createStatement();
      BookLibrarySQL.resultSet = BookLibrarySQL.statement
            .executeQuery("SELECT * FROM " + TableName.TABEL_PENERBIT);
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultRegristration(final Connection connection)
         throws SQLException {
      BookLibrarySQL.statement = connection.createStatement();
      BookLibrarySQL.resultSet = BookLibrarySQL.statement
            .executeQuery("SELECT * FROM " + TableName.TABEL_PENDAFTARAN);
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @param from
    * @param until
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultRegristration(final Connection connection,
         final java.util.Date from, final java.util.Date until)
         throws SQLException {
      BookLibrarySQL.tempString = "SELECT * FROM "
            + TableName.TABEL_PENDAFTARAN;
      if ((from != null) || (until != null)) {
         BookLibrarySQL.tempString += " WHERE ";
      }
      if (from != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PENDAFTARAN.TANGGAL_PENDAFTARAN
               + ">=?";
         if (until != null) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (until != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PENDAFTARAN.TANGGAL_PENDAFTARAN
               + "<=?";
      }
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement(BookLibrarySQL.tempString);
      BookLibrarySQL.tempInteger = 0;
      if (from != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setDate(BookLibrarySQL.tempInteger,
               new Date(from.getTime()));
      }
      if (until != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setDate(BookLibrarySQL.tempInteger,
               new Date(until.getTime()));
      }
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultReturnReport(final Connection connection)
         throws SQLException {
      BookLibrarySQL.statement = connection.createStatement();
      BookLibrarySQL.resultSet = BookLibrarySQL.statement
            .executeQuery("SELECT * FROM " + TableName.TABEL_PEMINJAMAN
                  + " JOIN " + TableName.TABEL_PENGEMBALIAN + " WHERE "
                  + FieldConstan.TABEL_PEMINJAMAN.ID_PEMINJAMAN + " = "
                  + FieldConstan.TABEL_PENGEMBALIAN.ID_PEMINJAMAN);
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @param memberId
    * @param operatorId
    * @param bookId
    * @param dateFrom
    * @param dateUntil
    * @return
    * @throws java.sql.SQLException
    */
   public static ResultSet getResultReturnReport(final Connection connection,
         final String memberId, final String operatorId, final String bookId,
         final java.util.Date dateFrom, final java.util.Date dateUntil)
         throws SQLException {
      if ((memberId == null) && (operatorId == null) && (bookId == null)
            && (dateFrom == null) && (dateUntil == null)) {
         return BookLibrarySQL.getResultReturnReport(connection);
      }
      BookLibrarySQL.tempString = "SELECT * FROM " + TableName.TABEL_PEMINJAMAN
            + " JOIN " + TableName.TABEL_PENGEMBALIAN + " WHERE "
            + FieldConstan.TABEL_PEMINJAMAN.ID_PEMINJAMAN + "="
            + FieldConstan.TABEL_PENGEMBALIAN.ID_PEMINJAMAN + " AND ";
      if (memberId != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PEMINJAMAN.ID_ANGGOTA
               + " LIKE ? ";
         if ((operatorId != null) || (bookId != null) || (dateFrom != null)
               || (dateUntil != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (operatorId != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PENGEMBALIAN.ID_OPERATOR
               + " LIKE ? ";
         if ((bookId != null) || (dateFrom != null) || (dateUntil != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (bookId != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PEMINJAMAN.ID_BUKU
               + " LIKE ? ";
         if ((dateFrom != null) || (dateUntil != null)) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (dateFrom != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PENGEMBALIAN.TANGGAL_PENGEMBALIAN
               + " >= ? ";
         if (dateUntil != null) {
            BookLibrarySQL.tempString += " AND ";
         }
      }
      if (dateUntil != null) {
         BookLibrarySQL.tempString += FieldConstan.TABEL_PENGEMBALIAN.TANGGAL_PENGEMBALIAN
               + " <= ? ";
      }
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement(BookLibrarySQL.tempString);
      BookLibrarySQL.tempInteger = 0;
      if (memberId != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + memberId + "%");
      }
      if (operatorId != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + operatorId + "%");
      }
      if (bookId != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setString(BookLibrarySQL.tempInteger,
               "%" + bookId + "%");
      }
      if (dateFrom != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setDate(BookLibrarySQL.tempInteger,
               new Date(dateFrom.getTime()));
      }
      if (dateUntil != null) {
         BookLibrarySQL.tempInteger++;
         BookLibrarySQL.prepareStatement.setDate(BookLibrarySQL.tempInteger,
               new Date(dateUntil.getTime()));
      }
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      return BookLibrarySQL.resultSet;
   }

   /**
    * @param connection
    * @return
    * @throws java.sql.SQLException
    */
   public static SettingLibrary getSettingLibrary(final Connection connection)
         throws SQLException {
      BookLibrarySQL.statement = connection.createStatement();
      BookLibrarySQL.resultSet = BookLibrarySQL.statement
            .executeQuery("SELECT * FROM " + TableName.TABEL_SETTING);
      BookLibrarySQL.settingLibrary = new DefaultSettingLibrary();
      while (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.settingLibrary
               .setMaxBorrowBook(BookLibrarySQL.resultSet
                     .getInt(FieldConstan.TABEL_SETTING.MAKSIMAL_PEMINJAMAN));
         BookLibrarySQL.settingLibrary.setMaxBorrowDay(BookLibrarySQL.resultSet
               .getInt(FieldConstan.TABEL_SETTING.WAKTU_PEMINJAMAN));
         BookLibrarySQL.settingLibrary.setPayOfLate(BookLibrarySQL.resultSet
               .getInt(FieldConstan.TABEL_SETTING.DENDA_KETERLAMBATAN));
      }
      BookLibrarySQL.resultSet.close();
      return BookLibrarySQL.settingLibrary;
   }

   /**
    * @param connection
    * @param book
    * @return
    * @throws java.sql.SQLException
    */
   public static int getTotalBorrowBook(final Connection connection,
         final String book) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_STATUS_BUKU
                  + " WHERE " + FieldConstan.TABEL_STATUS_BUKU.ID_BUKU + "=?");
      BookLibrarySQL.prepareStatement.setString(1, book);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         return BookLibrarySQL.resultSet
               .getInt(FieldConstan.TABEL_STATUS_BUKU.TOTAL_DIPINJAM);
      }
      return 0;
   }

   /**
    * @param c
    * @param member
    * @return
    * @throws java.sql.SQLException
    */
   public static int getTotalBorrowBookMember(final Connection c,
         final String member) throws SQLException {
      BookLibrarySQL.prepareStatement = c.prepareStatement("SELECT COUNT("
            + FieldConstan.TABEL_PEMINJAMAN.ID_ANGGOTA + ") AS TOTAL FROM "
            + TableName.TABEL_PEMINJAMAN + " WHERE "
            + FieldConstan.TABEL_PEMINJAMAN.ID_ANGGOTA + "=? AND "
            + FieldConstan.TABEL_PEMINJAMAN.TELAH_DIKEMBALIKAN + "=?");
      BookLibrarySQL.prepareStatement.setString(1, member);
      BookLibrarySQL.prepareStatement.setBoolean(2, false);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      BookLibrarySQL.tempInteger = 0;
      if (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.tempInteger = BookLibrarySQL.resultSet.getInt("TOTAL");
      }
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param member
    * @return
    * @throws java.sql.SQLException
    */
   public static int getTotalBorrowMember(final Connection connection,
         final String member) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("SELECT * FROM " + TableName.TABEL_STATUS_ANGGOTA
                  + " WHERE " + FieldConstan.TABEL_STATUS_ANGGOTA.ID_ANGGOTA
                  + "=?");
      BookLibrarySQL.prepareStatement.setString(1, member);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      if (BookLibrarySQL.resultSet.next()) {
         return BookLibrarySQL.resultSet
               .getInt(FieldConstan.TABEL_STATUS_ANGGOTA.TOTAL_MEMINJAM);
      }
      return 0;
   }

   /**
    * @param connection
    * @param author
    * @return
    * @throws java.sql.SQLException
    */
   public static int insertAuthor(final Connection connection,
         final Author author) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO " + TableName.TABEL_PENGARANG
                  + " SET " + FieldConstan.TABEL_PENGARANG.ID_PENGARANG + "=?,"
                  + FieldConstan.TABEL_PENGARANG.NAMA_PENGARANG + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, author.getId());
      BookLibrarySQL.prepareStatement.setString(2, author.getName());
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param book
    * @return
    * @throws java.sql.SQLException
    */
   public static int insertBook(final Connection connection, final Book book)
         throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO " + TableName.TABEL_BUKU + " SET "
                  + FieldConstan.TABEL_BUKU.ID_BUKU + "=?,"
                  + FieldConstan.TABEL_BUKU.ID_JENIS + "=?,"
                  + FieldConstan.TABEL_BUKU.ID_PENERBIT + "=?,"
                  + FieldConstan.TABEL_BUKU.ID_PENGARANG + "=?,"
                  + FieldConstan.TABEL_BUKU.JUDUL_BUKU + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, book.getId());
      BookLibrarySQL.prepareStatement.setString(2, book.getKind());
      BookLibrarySQL.prepareStatement.setString(3, book.getPublisher());
      BookLibrarySQL.prepareStatement.setString(4, book.getAuthor());
      BookLibrarySQL.prepareStatement.setString(5, book.getTitle());
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      BookLibrarySQL.insertBookStatus(connection, book.getId());
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param id
    * @throws java.sql.SQLException
    */
   public static void insertBookStatus(final Connection connection,
         final String id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO " + TableName.TABEL_STATUS_BUKU
                  + " SET " + FieldConstan.TABEL_STATUS_BUKU.ID_BUKU + "=?,"
                  + FieldConstan.TABEL_STATUS_BUKU.SEDANG_DIPINJAM + "=?,"
                  + FieldConstan.TABEL_STATUS_BUKU.TOTAL_DIPINJAM + "=?");
      BookLibrarySQL.prepareStatement.setString(1, id);
      BookLibrarySQL.prepareStatement.setBoolean(2, false);
      BookLibrarySQL.prepareStatement.setInt(3, 0);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * @param connection
    * @param borrow
    * @return
    * @throws java.sql.SQLException
    */
   public static int insertBorowTransaction(final Connection connection,
         final Borrow borrow) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO " + TableName.TABEL_PEMINJAMAN
                  + " SET " + FieldConstan.TABEL_PEMINJAMAN.ID_ANGGOTA
                  + "=? , " + FieldConstan.TABEL_PEMINJAMAN.ID_BUKU + "=? , "
                  + FieldConstan.TABEL_PEMINJAMAN.ID_OPERATOR + "=? ,"
                  + FieldConstan.TABEL_PEMINJAMAN.ID_PEMINJAMAN + "=null ,"
                  + FieldConstan.TABEL_PEMINJAMAN.TANGGAL_PEMINJAMAN
                  + "=CURDATE() ,"
                  + FieldConstan.TABEL_PEMINJAMAN.TANGGAL_PENGEMBALIAN
                  + "=DATE_ADD(CURDATE(), INTERVAL "
                  + BookLibrarySQL.getMaxDayBorro(connection) + " DAY) ,"
                  + FieldConstan.TABEL_PEMINJAMAN.TELAH_DIKEMBALIKAN + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, borrow.getMember());
      BookLibrarySQL.prepareStatement.setString(2, borrow.getBook());
      BookLibrarySQL.prepareStatement.setString(3, borrow.getOperator());
      BookLibrarySQL.prepareStatement.setBoolean(4, false);
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      BookLibrarySQL.updateStatusBook(connection, borrow.getBook(), true);
      BookLibrarySQL.updateStatusMember(connection, borrow.getMember());
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param bookKind
    * @return
    * @throws java.sql.SQLException
    */
   public static int insertKindBook(final Connection connection,
         final KindBook bookKind) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO " + TableName.TABEL_JENIS_BUKU
                  + " SET " + FieldConstan.TABEL_JENIS_BUKU.ID_JENIS + "=?,"
                  + FieldConstan.TABEL_JENIS_BUKU.JENIS_BUKU + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, bookKind.getId());
      BookLibrarySQL.prepareStatement.setString(2, bookKind.getKind());
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param member
    * @return
    * @throws java.sql.SQLException
    */
   public static int insertMember(final Connection connection,
         final Member member) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO " + TableName.TABEL_ANGGOTA
                  + " SET " + FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA + "=?,"
                  + FieldConstan.TABEL_ANGGOTA.NAMA_ANGGOTA + "=?,"
                  + FieldConstan.TABEL_ANGGOTA.TANGGAL_LAHIR_ANGGOTA + "=?,"
                  + FieldConstan.TABEL_ANGGOTA.KONTAK_ANGGOTA + "=?,"
                  + FieldConstan.TABEL_ANGGOTA.ALAMAT_ANGGOTA + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, member.getId());
      BookLibrarySQL.prepareStatement.setString(2, member.getName());
      BookLibrarySQL.prepareStatement.setDate(3, new Date(member.getBorn()
            .getTime()));
      BookLibrarySQL.prepareStatement.setString(4, member.getContact());
      BookLibrarySQL.prepareStatement.setString(5, member.getAddress());
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      BookLibrarySQL.insertMemberStatus(connection, member.getId());
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param id
    * @throws java.sql.SQLException
    */
   public static void insertMemberStatus(final Connection connection,
         final String id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO " + TableName.TABEL_STATUS_ANGGOTA
                  + " SET " + FieldConstan.TABEL_STATUS_ANGGOTA.ID_ANGGOTA
                  + "=?," + FieldConstan.TABEL_STATUS_ANGGOTA.TOTAL_MEMINJAM
                  + "=?");
      BookLibrarySQL.prepareStatement.setString(1, id);
      BookLibrarySQL.prepareStatement.setInt(2, 0);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * @param connection
    * @param message
    * @throws java.sql.SQLException
    */
   public static void insertMessage(final Connection connection,
         final DefaultMessage message) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO " + TableName.TABEL_PESAN + " SET "
                  + FieldConstan.TABEL_PESAN.ID_OPERATOR_FROM + "=?,"
                  + FieldConstan.TABEL_PESAN.ID_OPERATOR_TO + "=?,"
                  + FieldConstan.TABEL_PESAN.JUDUL + "=?,"
                  + FieldConstan.TABEL_PESAN.PESAN + "=?,"
                  + FieldConstan.TABEL_PESAN.WAKTU + "=NOW(),"
                  + FieldConstan.TABEL_PESAN.ID_PESAN + "=null;");
      BookLibrarySQL.prepareStatement.setString(1, message.getOperatorFrom());
      BookLibrarySQL.prepareStatement.setString(2, message.getOperatorTo());
      BookLibrarySQL.prepareStatement.setString(3, message.getTitle());
      BookLibrarySQL.prepareStatement.setString(4, message.getMessage());
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * insert operator
    * 
    * @param c
    * @param o
    * @return
    * @throws java.sql.SQLException
    */
   public static int insertOperator(final Connection c, final Operator o)
         throws SQLException {
      BookLibrarySQL.prepareStatement = c.prepareStatement("INSERT INTO "
            + TableName.TABEL_OPERATOR + " SET "
            + FieldConstan.TABEL_OPERATOR.ID_OPERATOR + "=?,"
            + FieldConstan.TABEL_OPERATOR.PASSWORD_OPERATOR + "=?,"
            + FieldConstan.TABEL_OPERATOR.NAMA_OPERATOR + "=?,"
            + FieldConstan.TABEL_OPERATOR.ALAMAT_OPERATOR + "=?,"
            + FieldConstan.TABEL_OPERATOR.KONTAK_OPERATOR + "=?,"
            + FieldConstan.TABEL_OPERATOR.TANGGAL_LAHIR_OPERATOR + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, o.getId());
      BookLibrarySQL.prepareStatement.setString(2, o.getPassword());
      BookLibrarySQL.prepareStatement.setString(3, o.getName());
      BookLibrarySQL.prepareStatement.setString(4, o.getAddress());
      BookLibrarySQL.prepareStatement.setString(5, o.getContact());
      BookLibrarySQL.prepareStatement.setDate(6,
            new Date(o.getBorn().getTime()));
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param id
    * @param string
    * @return
    * @throws java.sql.SQLException
    */
   public static int insertOperatorActivities(final Connection connection,
         final String id, final String string) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO "
                  + TableName.TABEL_AKTIVITAS_OPERATOR + " SET "
                  + FieldConstan.TABEL_AKTIVITAS_OPERATOR.ID_OPERATOR + "=? ,"
                  + FieldConstan.TABEL_AKTIVITAS_OPERATOR.KEGIATAN + "=? ,"
                  + FieldConstan.TABEL_AKTIVITAS_OPERATOR.WAKTU + "=NOW();");
      BookLibrarySQL.prepareStatement.setString(1, id);
      BookLibrarySQL.prepareStatement.setString(2, string);
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param publisher
    * @return
    * @throws java.sql.SQLException
    */
   public static int insertPublisher(final Connection connection,
         final Publisher publisher) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO " + TableName.TABEL_PENERBIT
                  + " SET " + FieldConstan.TABEL_PENERBIT.ID_PENERBIT + "=?,"
                  + FieldConstan.TABEL_PENERBIT.NAMA_PENERBIT + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, publisher.getId());
      BookLibrarySQL.prepareStatement.setString(2, publisher.getName());
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param id
    * @param id0
    * @return
    * @throws java.sql.SQLException
    */
   public static int insertRegistration(final Connection connection,
         final String id, final String id0) throws SQLException {
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement("INSERT INTO " + TableName.TABEL_PENDAFTARAN
                  + " SET " + FieldConstan.TABEL_PENDAFTARAN.ID_ANGGOTA
                  + "=? ," + FieldConstan.TABEL_PENDAFTARAN.ID_OPERATOR
                  + "=? ," + FieldConstan.TABEL_PENDAFTARAN.TANGGAL_PENDAFTARAN
                  + "=NOW();");
      BookLibrarySQL.prepareStatement.setString(1, id);
      BookLibrarySQL.prepareStatement.setString(2, id0);
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param id
    * @param operator
    * @return
    * @throws java.sql.SQLException
    */
   public static int insertReturnTransaction(final Connection connection,
         final String id, final String operator) throws SQLException {
      BookLibrarySQL.tempString = "INSERT INTO " + TableName.TABEL_PENGEMBALIAN
            + " SET " + FieldConstan.TABEL_PENGEMBALIAN.ID_PEMINJAMAN + "=?, "
            + FieldConstan.TABEL_PENGEMBALIAN.ID_OPERATOR + "=?, "
            + FieldConstan.TABEL_PENGEMBALIAN.TANGGAL_PENGEMBALIAN
            + "=CURDATE()," + FieldConstan.TABEL_PENGEMBALIAN.TERLAMBAT + "=?,"
            + FieldConstan.TABEL_PENGEMBALIAN.DENDA + "=?;";
      final int terlambat = BookLibrarySQL.isLate(connection, id);
      final int denda = BookLibrarySQL.getPayForLate(connection) * terlambat;
      BookLibrarySQL.prepareStatement = connection
            .prepareStatement(BookLibrarySQL.tempString);
      BookLibrarySQL.prepareStatement.setString(1, id);
      BookLibrarySQL.prepareStatement.setString(2, operator);
      if (terlambat > 0) {
         BookLibrarySQL.prepareStatement.setInt(3, terlambat);
         BookLibrarySQL.prepareStatement.setInt(4, denda);
      } else {
         BookLibrarySQL.prepareStatement.setInt(3, 0);
         BookLibrarySQL.prepareStatement.setInt(4, 0);
      }
      if (terlambat > 0) {
         WidgetUtilities.showErrorMessage(null, "ANGGOTA TERLAMBAT "
               + terlambat + " HARI MENGEMBALIKAN BUKU DAN DIKENAI DENDA Rp."
               + denda);
      }
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      BookLibrarySQL.updateBorrowTransaction(connection, id);
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param id
    * @return
    * @throws java.sql.SQLException
    */
   public static int isLate(final Connection connection, final String id)
         throws SQLException {
      BookLibrarySQL.prepareStatement = connection.prepareStatement("SELECT "
            + FieldConstan.TABEL_PEMINJAMAN.TANGGAL_PENGEMBALIAN + " FROM "
            + TableName.TABEL_PEMINJAMAN + " WHERE "
            + FieldConstan.TABEL_PEMINJAMAN.ID_PEMINJAMAN + "=?");
      BookLibrarySQL.prepareStatement.setString(1, id);
      BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement.executeQuery();
      BookLibrarySQL.tempInteger = 0;
      if (BookLibrarySQL.resultSet.next()) {
         BookLibrarySQL.tempDate = BookLibrarySQL.resultSet
               .getDate(FieldConstan.TABEL_PEMINJAMAN.TANGGAL_PENGEMBALIAN);
         BookLibrarySQL.prepareStatement = connection
               .prepareStatement("SELECT DATEDIFF(CURDATE(),?) AS DIFF");
         BookLibrarySQL.prepareStatement.setDate(1, BookLibrarySQL.tempDate);
         BookLibrarySQL.resultSet = BookLibrarySQL.prepareStatement
               .executeQuery();
         if (BookLibrarySQL.resultSet.next()) {
            BookLibrarySQL.tempInteger = BookLibrarySQL.resultSet
                  .getInt("DIFF");
         }
      }
      return BookLibrarySQL.tempInteger;
   }

   /**
    * save e'booklibrary setting
    * 
    * @param c
    * @param s
    * @return
    * @throws java.sql.SQLException
    */
   public static int saveSetting(final Connection c, final SettingLibrary s)
         throws SQLException {
      BookLibrarySQL.prepareStatement = c.prepareStatement("INSERT INTO "
            + TableName.TABEL_SETTING + " SET "
            + FieldConstan.TABEL_SETTING.DENDA_KETERLAMBATAN + "=?,"
            + FieldConstan.TABEL_SETTING.MAKSIMAL_PEMINJAMAN + "=?,"
            + FieldConstan.TABEL_SETTING.WAKTU_PEMINJAMAN + "=? ;");
      BookLibrarySQL.prepareStatement.executeUpdate("DELETE FROM "
            + TableName.TABEL_SETTING);
      BookLibrarySQL.prepareStatement.setInt(1, s.getPayOfLate());
      BookLibrarySQL.prepareStatement.setInt(2, s.getMaxBorrowBook());
      BookLibrarySQL.prepareStatement.setInt(3, s.getMaxBorrowDay());
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param id
    * @param author
    * @return
    * @throws java.sql.SQLException
    */
   public static int updateAuthor(final Connection connection, final String id,
         final Author author) throws SQLException {
      BookLibrarySQL.prepareStatement = connection.prepareStatement("UPDATE "
            + TableName.TABEL_PENGARANG + " SET "
            + FieldConstan.TABEL_PENGARANG.ID_PENGARANG + "=?,"
            + FieldConstan.TABEL_PENGARANG.NAMA_PENGARANG + "=?" + " WHERE "
            + FieldConstan.TABEL_PENGARANG.ID_PENGARANG + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, author.getId());
      BookLibrarySQL.prepareStatement.setString(2, author.getName());
      BookLibrarySQL.prepareStatement.setString(3, id);
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param c
    * @param id
    * @param book
    * @return
    * @throws java.sql.SQLException
    */
   public static int updateBook(final Connection c, final String id,
         final Book book) throws SQLException {
      BookLibrarySQL.prepareStatement = c.prepareStatement("UPDATE "
            + TableName.TABEL_BUKU + " SET " + FieldConstan.TABEL_BUKU.ID_BUKU
            + "=?," + FieldConstan.TABEL_BUKU.ID_JENIS + "=?,"
            + FieldConstan.TABEL_BUKU.ID_PENERBIT + "=?,"
            + FieldConstan.TABEL_BUKU.ID_PENGARANG + "=?,"
            + FieldConstan.TABEL_BUKU.JUDUL_BUKU + "=?" + " WHERE "
            + FieldConstan.TABEL_BUKU.ID_BUKU + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, book.getId());
      BookLibrarySQL.prepareStatement.setString(2, book.getKind());
      BookLibrarySQL.prepareStatement.setString(3, book.getPublisher());
      BookLibrarySQL.prepareStatement.setString(4, book.getAuthor());
      BookLibrarySQL.prepareStatement.setString(5, book.getTitle());
      BookLibrarySQL.prepareStatement.setString(6, id);
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param id
    * @throws java.sql.SQLException
    */
   public static void updateBorrowTransaction(final Connection connection,
         final String id) throws SQLException {
      BookLibrarySQL.prepareStatement = connection.prepareStatement("UPDATE "
            + TableName.TABEL_PEMINJAMAN + " SET "
            + FieldConstan.TABEL_PEMINJAMAN.TELAH_DIKEMBALIKAN + "=?"
            + " WHERE " + FieldConstan.TABEL_PEMINJAMAN.ID_PEMINJAMAN + "=?;");
      BookLibrarySQL.prepareStatement.setBoolean(1, true);
      BookLibrarySQL.prepareStatement.setString(2, id);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * @param connection
    * @param id
    * @param bookKind
    * @return
    * @throws java.sql.SQLException
    */
   public static int updateKindBook(final Connection connection,
         final String id, final KindBook bookKind) throws SQLException {
      BookLibrarySQL.prepareStatement = connection.prepareStatement("UPDATE "
            + TableName.TABEL_JENIS_BUKU + " SET "
            + FieldConstan.TABEL_JENIS_BUKU.ID_JENIS + "=?, "
            + FieldConstan.TABEL_JENIS_BUKU.JENIS_BUKU + "=? WHERE "
            + FieldConstan.TABEL_JENIS_BUKU.ID_JENIS + "=?");
      BookLibrarySQL.prepareStatement.setString(1, bookKind.getId());
      BookLibrarySQL.prepareStatement.setString(2, bookKind.getKind());
      BookLibrarySQL.prepareStatement.setString(3, id);
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param id
    * @param get
    * @return
    * @throws java.sql.SQLException
    */
   public static int updateMember(final Connection connection, final String id,
         final Member get) throws SQLException {
      BookLibrarySQL.prepareStatement = connection.prepareStatement("UPDATE "
            + TableName.TABEL_ANGGOTA + " SET "
            + FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA + "=?,"
            + FieldConstan.TABEL_ANGGOTA.NAMA_ANGGOTA + "=?,"
            + FieldConstan.TABEL_ANGGOTA.TANGGAL_LAHIR_ANGGOTA + "=?,"
            + FieldConstan.TABEL_ANGGOTA.KONTAK_ANGGOTA + "=?,"
            + FieldConstan.TABEL_ANGGOTA.ALAMAT_ANGGOTA + "=?" + " WHERE "
            + FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, get.getId());
      BookLibrarySQL.prepareStatement.setString(2, get.getName());
      BookLibrarySQL.prepareStatement.setDate(3, new Date(get.getBorn()
            .getTime()));
      BookLibrarySQL.prepareStatement.setString(4, get.getContact());
      BookLibrarySQL.prepareStatement.setString(5, get.getAddress());
      BookLibrarySQL.prepareStatement.setString(6, id);
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * update operator
    * 
    * @param c
    * @param o
    * @param id
    * @return
    * @throws java.sql.SQLException
    */
   public static int updateOperator(final Connection c, final Operator o,
         final String id) throws SQLException {
      BookLibrarySQL.prepareStatement = c.prepareStatement("UPDATE "
            + TableName.TABEL_OPERATOR + " SET "
            + FieldConstan.TABEL_OPERATOR.ID_OPERATOR + "=?,"
            + FieldConstan.TABEL_OPERATOR.PASSWORD_OPERATOR + "=?,"
            + FieldConstan.TABEL_OPERATOR.NAMA_OPERATOR + "=?,"
            + FieldConstan.TABEL_OPERATOR.ALAMAT_OPERATOR + "=?,"
            + FieldConstan.TABEL_OPERATOR.KONTAK_OPERATOR + "=?,"
            + FieldConstan.TABEL_OPERATOR.TANGGAL_LAHIR_OPERATOR + "=? "
            + "WHERE " + FieldConstan.TABEL_OPERATOR.ID_OPERATOR + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, o.getId());
      BookLibrarySQL.prepareStatement.setString(2, o.getPassword());
      BookLibrarySQL.prepareStatement.setString(3, o.getName());
      BookLibrarySQL.prepareStatement.setString(4, o.getAddress());
      BookLibrarySQL.prepareStatement.setString(5, o.getContact());
      BookLibrarySQL.prepareStatement.setDate(6,
            new Date(o.getBorn().getTime()));
      BookLibrarySQL.prepareStatement.setString(7, id);
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param id
    * @param publisher
    * @return
    * @throws java.sql.SQLException
    */
   public static int updatePublisher(final Connection connection,
         final String id, final Publisher publisher) throws SQLException {
      BookLibrarySQL.prepareStatement = connection.prepareStatement("UPDATE "
            + TableName.TABEL_PENERBIT + " SET "
            + FieldConstan.TABEL_PENERBIT.ID_PENERBIT + "=?,"
            + FieldConstan.TABEL_PENERBIT.NAMA_PENERBIT + "=? " + " WHERE "
            + FieldConstan.TABEL_PENERBIT.ID_PENERBIT + "=?;");
      BookLibrarySQL.prepareStatement.setString(1, publisher.getId());
      BookLibrarySQL.prepareStatement.setString(2, publisher.getName());
      BookLibrarySQL.prepareStatement.setString(3, id);
      BookLibrarySQL.tempInteger = BookLibrarySQL.prepareStatement
            .executeUpdate();
      BookLibrarySQL.prepareStatement.close();
      return BookLibrarySQL.tempInteger;
   }

   /**
    * @param connection
    * @param book
    * @param b
    * @throws java.sql.SQLException
    */
   public static void updateStatusBook(final Connection connection,
         final String book, final boolean b) throws SQLException {
      if (b) {
         BookLibrarySQL.tempInteger = BookLibrarySQL.getTotalBorrowBook(
               connection, book) + 1;
         BookLibrarySQL.prepareStatement = connection
               .prepareStatement("UPDATE " + TableName.TABEL_STATUS_BUKU
                     + " SET " + FieldConstan.TABEL_STATUS_BUKU.SEDANG_DIPINJAM
                     + "=?," + FieldConstan.TABEL_STATUS_BUKU.TOTAL_DIPINJAM
                     + "=? WHERE " + FieldConstan.TABEL_STATUS_BUKU.ID_BUKU
                     + "=?");
         BookLibrarySQL.prepareStatement.setBoolean(1, b);
         BookLibrarySQL.prepareStatement.setInt(2, BookLibrarySQL.tempInteger);
         BookLibrarySQL.prepareStatement.setString(3, book);
      } else {
         BookLibrarySQL.prepareStatement = connection
               .prepareStatement("UPDATE " + TableName.TABEL_STATUS_BUKU
                     + " SET " + FieldConstan.TABEL_STATUS_BUKU.SEDANG_DIPINJAM
                     + "=? WHERE " + FieldConstan.TABEL_STATUS_BUKU.ID_BUKU
                     + "=?");
         BookLibrarySQL.prepareStatement.setBoolean(1, b);
         BookLibrarySQL.prepareStatement.setString(2, book);
      }
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * @param connection
    * @param member
    * @throws java.sql.SQLException
    */
   public static void updateStatusMember(final Connection connection,
         final String member) throws SQLException {
      BookLibrarySQL.tempInteger = BookLibrarySQL.getTotalBorrowMember(
            connection, member) + 1;
      BookLibrarySQL.prepareStatement = connection.prepareStatement("UPDATE "
            + TableName.TABEL_STATUS_ANGGOTA + " SET "
            + FieldConstan.TABEL_STATUS_ANGGOTA.TOTAL_MEMINJAM + "=? WHERE "
            + FieldConstan.TABEL_STATUS_ANGGOTA.ID_ANGGOTA + "=?");
      BookLibrarySQL.prepareStatement.setInt(1, BookLibrarySQL.tempInteger);
      BookLibrarySQL.prepareStatement.setString(2, member);
      BookLibrarySQL.prepareStatement.executeUpdate();
      BookLibrarySQL.prepareStatement.close();
   }

   /**
    * 
    * @deprecated
    */
   @Deprecated
   private BookLibrarySQL() {
      // Do nothing
   }
}
