/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     31/03/2008 15:28:23                          */
/*==============================================================*/


drop table if exists AKTIFITAS_OPERATOR;

drop table if exists ANGGOTA;

drop table if exists BUKU;

drop table if exists JENIS_BUKU;

drop table if exists OPERATOR;

drop table if exists PEMINJAMAN;

drop table if exists PENDAFTARAN;

drop table if exists PENERBIT;

drop table if exists PENGARANG;

drop table if exists PENGEMBALIAN;

drop table if exists PESAN;

drop table if exists SETTING;

drop table if exists STATUS_ANGGOTA;

drop table if exists STATUS_BUKU;

/*==============================================================*/
/* Table: AKTIFITAS_OPERATOR                                    */
/*==============================================================*/
create table AKTIFITAS_OPERATOR
(
   ID_OPERATOR          varchar(10) not null,
   KEGIATAN             text not null,
   WAKTU                timestamp not null
)
type = InnoDB;

/*==============================================================*/
/* Table: ANGGOTA                                               */
/*==============================================================*/
create table ANGGOTA
(
   ID_ANGGOTA           varchar(10) not null,
   NAMA_ANGGOTA         varchar(64) not null,
   ALAMAT_ANGGOTA       text not null,
   KONTAK_ANGGOTA       varchar(64) not null,
   TANGGAL_LAHIR_ANGGOTA date not null,
   primary key (ID_ANGGOTA)
)
type = InnoDB;

/*==============================================================*/
/* Table: BUKU                                                  */
/*==============================================================*/
create table BUKU
(
   ID_BUKU              varchar(10) not null,
   ID_PENERBIT          varchar(10) not null,
   ID_JENIS             varchar(10) not null,
   ID_PENGARANG         varchar(10) not null,
   JUDUL_BUKU           varchar(64) not null,
   primary key (ID_BUKU)
)
type = InnoDB;

/*==============================================================*/
/* Table: JENIS_BUKU                                            */
/*==============================================================*/
create table JENIS_BUKU
(
   ID_JENIS             varchar(10) not null,
   JENIS_BUKU           varchar(50) not null,
   primary key (ID_JENIS)
)
type = InnoDB;

/*==============================================================*/
/* Table: OPERATOR                                              */
/*==============================================================*/
create table OPERATOR
(
   ID_OPERATOR          varchar(10) not null,
   PASSWORD_OPERATOR    varchar(64) not null,
   NAMA_OPERATOR        varchar(64) not null,
   ALAMAT_OPERATOR      text not null,
   KONTAK_OPERATOR      varchar(64) not null,
   TANGGAL_LAHIR_OPERATOR date not null,
   primary key (ID_OPERATOR)
)
type = InnoDB;

/*==============================================================*/
/* Table: PEMINJAMAN                                            */
/*==============================================================*/
create table PEMINJAMAN
(
   ID_PEMINJAMAN        integer not null auto_increment,
   ID_ANGGOTA           varchar(10) not null,
   ID_OPERATOR          varchar(10) not null,
   ID_BUKU              varchar(10) not null,
   TANGGAL_PEMINJAMAN   date not null,
   TANGGAL_PENGEMBALIAN date not null,
   TELAH_DIKEMBALIKAN   boolean not null,
   primary key (ID_PEMINJAMAN)
)
type = InnoDB;

/*==============================================================*/
/* Table: PENDAFTARAN                                           */
/*==============================================================*/
create table PENDAFTARAN
(
   ID_ANGGOTA           varchar(10) not null,
   ID_OPERATOR          varchar(10) not null,
   TANGGAL_PENDAFTARAN  date not null,
   primary key (ID_ANGGOTA)
)
type = InnoDB;

/*==============================================================*/
/* Table: PENERBIT                                              */
/*==============================================================*/
create table PENERBIT
(
   ID_PENERBIT          varchar(10) not null,
   NAMA_PENERBIT        varchar(50) not null,
   primary key (ID_PENERBIT)
)
type = InnoDB;

/*==============================================================*/
/* Table: PENGARANG                                             */
/*==============================================================*/
create table PENGARANG
(
   ID_PENGARANG         varchar(10) not null,
   NAMA_PENGARANG       varchar(50) not null,
   primary key (ID_PENGARANG)
)
type = InnoDB;

/*==============================================================*/
/* Table: PENGEMBALIAN                                          */
/*==============================================================*/
create table PENGEMBALIAN
(
   ID_OPERATOR          varchar(10) not null,
   ID_PEMINJAMAN        integer not null,
   TANGGAL_PENGEMBALIAN date not null,
   TERLAMBAT            integer not null,
   DENDA                integer not null,
   primary key (ID_PEMINJAMAN)
)
type = InnoDB;

/*==============================================================*/
/* Table: PESAN                                                 */
/*==============================================================*/
create table PESAN
(
   ID_PESAN             integer not null auto_increment,
   ID_OPERATOR_TO       varchar(10) not null,
   ID_OPERATOR_FROM     varchar(10) not null,
   JUDUL                varchar(64) not null,
   PESAN                text not null,
   WAKTU                timestamp not null,
   primary key (ID_PESAN)
)
type = InnoDB;

/*==============================================================*/
/* Table: SETTING                                               */
/*==============================================================*/
create table SETTING
(
   WAKTU_PEMINJAMAN     integer not null,
   DENDA_KETERLAMBATAN  integer not null,
   MAKSIMAL_PEMINJAMAN  integer not null
)
type = InnoDB;

/*==============================================================*/
/* Table: STATUS_ANGGOTA                                        */
/*==============================================================*/
create table STATUS_ANGGOTA
(
   ID_ANGGOTA           varchar(10) not null,
   TOTAL_MEMINJAM       integer not null,
   primary key (ID_ANGGOTA)
)
type = InnoDB;

/*==============================================================*/
/* Table: STATUS_BUKU                                           */
/*==============================================================*/
create table STATUS_BUKU
(
   ID_BUKU              varchar(10) not null,
   TOTAL_DIPINJAM       integer not null,
   SEDANG_DIPINJAM      boolean not null,
   primary key (ID_BUKU)
)
type = InnoDB;

alter table AKTIFITAS_OPERATOR add constraint FK_REFERENCE_15 foreign key (ID_OPERATOR)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

alter table BUKU add constraint FK_REFERENCE_1 foreign key (ID_PENERBIT)
      references PENERBIT (ID_PENERBIT) on delete restrict on update restrict;

alter table BUKU add constraint FK_REFERENCE_2 foreign key (ID_JENIS)
      references JENIS_BUKU (ID_JENIS) on delete restrict on update restrict;

alter table BUKU add constraint FK_REFERENCE_3 foreign key (ID_PENGARANG)
      references PENGARANG (ID_PENGARANG) on delete restrict on update restrict;

alter table PEMINJAMAN add constraint FK_REFERENCE_6 foreign key (ID_ANGGOTA)
      references ANGGOTA (ID_ANGGOTA) on delete restrict on update restrict;

alter table PEMINJAMAN add constraint FK_REFERENCE_7 foreign key (ID_OPERATOR)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

alter table PEMINJAMAN add constraint FK_REFERENCE_8 foreign key (ID_BUKU)
      references BUKU (ID_BUKU) on delete restrict on update restrict;

alter table PENDAFTARAN add constraint FK_REFERENCE_4 foreign key (ID_ANGGOTA)
      references ANGGOTA (ID_ANGGOTA) on delete restrict on update restrict;

alter table PENDAFTARAN add constraint FK_REFERENCE_5 foreign key (ID_OPERATOR)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

alter table PENGEMBALIAN add constraint FK_REFERENCE_16 foreign key (ID_PEMINJAMAN)
      references PEMINJAMAN (ID_PEMINJAMAN) on delete restrict on update restrict;

alter table PENGEMBALIAN add constraint FK_REFERENCE_9 foreign key (ID_OPERATOR)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

alter table PESAN add constraint FK_REFERENCE_17 foreign key (ID_OPERATOR_TO)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

alter table PESAN add constraint FK_REFERENCE_18 foreign key (ID_OPERATOR_FROM)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

alter table STATUS_ANGGOTA add constraint FK_REFERENCE_14 foreign key (ID_ANGGOTA)
      references ANGGOTA (ID_ANGGOTA) on delete restrict on update restrict;

alter table STATUS_BUKU add constraint FK_REFERENCE_13 foreign key (ID_BUKU)
      references BUKU (ID_BUKU) on delete restrict on update restrict;

