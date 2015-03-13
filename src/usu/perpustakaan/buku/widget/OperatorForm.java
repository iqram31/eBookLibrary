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
package usu.perpustakaan.buku.widget;

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import usu.perpustakaan.buku.constan.FieldConstan;
import usu.perpustakaan.buku.data.template.Borrow;
import usu.perpustakaan.buku.data.DefaultAuthor;
import usu.perpustakaan.buku.data.DefaultBook;
import usu.perpustakaan.buku.data.DefaultBookGuest;
import usu.perpustakaan.buku.data.DefaultBorrow;
import usu.perpustakaan.buku.data.DefaultKindBook;
import usu.perpustakaan.buku.data.DefaultMember;
import usu.perpustakaan.buku.data.DefaultMemberReport;
import usu.perpustakaan.buku.data.DefaultMessage;
import usu.perpustakaan.buku.data.DefaultPublisher;
import usu.perpustakaan.buku.data.DefaultRegristration;
import usu.perpustakaan.buku.data.DefaultReturn;
import usu.perpustakaan.buku.data.template.Author;
import usu.perpustakaan.buku.data.template.Book;
import usu.perpustakaan.buku.data.template.KindBook;
import usu.perpustakaan.buku.data.template.Member;
import usu.perpustakaan.buku.data.template.Message;
import usu.perpustakaan.buku.data.template.Operator;
import usu.perpustakaan.buku.data.template.Publisher;
import usu.perpustakaan.buku.data.template.Regristration;
import usu.perpustakaan.buku.data.template.Return;
import usu.perpustakaan.buku.sql.BookLibrarySQL;
import usu.widget.Form;
import usu.widget.GlassPane;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author  usu
 */
public class OperatorForm extends Form {

    private static final long serialVersionUID = -1;
    private Connection connection;
    private ExecutorService executor;
    private Operator operator;
    private String id;
    private int index;
    private Borrow borrowTemp;
    private DefaultMessage message;

    /**
     * make new operator form
     */
    public OperatorForm() {

        executor = Executors.newCachedThreadPool();

        setIconImage(new ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Operator2.png")).getImage());

        initComponents();

        getRootPane().getInputMap().put(KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE), "CLOSE");
        getRootPane().getActionMap().put("CLOSE", new AbstractAction() {

            private static final long serialVersionUID = -1;

            @Override
            public void actionPerformed(ActionEvent e) {
                ((GlassPane) getGlassPane()).hideComponent();
            }
        });

        initActions();
    }

    /**
     * 
     * @return
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * 
     * @param operator
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * setting koneksi mysql
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
        addBook.setConnection(connection);
        editBook.setConnection(connection);
        addBorrow.setConnection(connection);
        addMessage.setConnection(connection);
        detailMessage.setConnection(connection);
    }

    @Override
    public void exit() {
        if (showConfirm("Anda yakin?") != JOptionPane.OK_OPTION) {
            return;
        }
        executor.shutdown();
        super.exit();
    }

    private void initActions() {
        home.getButtonBook().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(book, "BOOK");
                setTitleBody("Menu Buku");
            }
        });
        home.getButtonActivities().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(activities, "ACTIVITIES");
                setTitleBody("Menu Aktivitas");
            }
        });
        home.getButtonReport().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(report, "REPORT");
                setTitleBody("Menu Laporan");
            }
        });
        home.getButtonMember().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadMember();
                    showPanel(manageMember, "MANAGE_MEMBER");
                    setTitleBody("Anggota");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        home.getButtonWizard().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        home.getButtonYourselft().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                manageMe.setOperator(getOperator());
                showPanel(manageMe, "MANAGE_ME");
                setTitleBody("Pribadi");
            }
        });
        home.getButtonWizard().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadMessage(getOperator().getId()).execute();
                    showPanel(manageMessage, "MANAGE_MESSAGE");
                    setTitleBody("Pesan");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });

        manageMessage.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadMessage(getOperator().getId()).execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageMessage.getButtonSend().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addMessage.reset();
                    showComponentInGlasspane(addMessage);
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageMessage.getButtonDetailMessage().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (manageMessage.getTable().getSelectedRow() == -1) {
                    showErrorMessage("Silahkan seleksi pesan yang akan dilihat");
                    return;
                }
                index = manageMessage.getTable().convertRowIndexToModel(manageMessage.getTable().getSelectedRow());
                detailMessage.setMessage(manageMessage.getModel().get(index));
                showComponentInGlasspane(detailMessage);
            }
        });
        manageMessage.getButtonReply().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (manageMessage.getTable().getSelectedRow() == -1) {
                    showErrorMessage("Silahkan seleksi pesan yang akan dibalas");
                    return;
                }
                index = manageMessage.getTable().convertRowIndexToModel(manageMessage.getTable().getSelectedRow());
                try {
                    addMessage.reset();
                    addMessage.getTextSearch().setText(manageMessage.getModel().get(index).getOperatorFrom());
                    addMessage.getTextTitle().setText("Balas : " + manageMessage.getModel().get(index).getTitle());
                    addMessage.getTextMessage().setText(
                            "Anda Menulis : " +
                            "\n-----------------------------\n" +
                            manageMessage.getModel().get(index).getMessage() +
                            "\n-----------------------------\n");
                    showComponentInGlasspane(addMessage);
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageMessage.getButtonDelete().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMessage();
            }
        });

        detailMessage.getButtonClose().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hideComponentFromGlasspane();
            }
        });

        addMessage.getButtonSend().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addMessage();
            }
        });
        addMessage.getButtonCancel().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hideComponentFromGlasspane();
            }
        });

        activities.getButtonBorrow().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadTransaction().execute();
                    showPanel(manageBorrow, "MANAGE_BORROW");
                    setTitleBody("Peminjaman");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        activities.getButtonReturn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadTransaction().execute();
                    showPanel(manageReturn, "MANAGE_RETURN");
                    setTitleBody("Pengembalian");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });

        manageMe.getButtonSave().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveYourSelft();
            }
        });

        book.getButtonBook().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadBook();
                    showPanel(manageBook, "MANAGE_BOOK");
                    setTitleBody("Buku");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        book.getButtonPublisher().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadPublisher();
                    showPanel(managePublisher, "MANAGE_PUBLISHER");
                    setTitleBody("Penerbit");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        book.getButtonAuthor().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadAuthor();
                    showPanel(manageAuthor, "MANAGE_AUTHOR");
                    setTitleBody("Pengarang");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        book.getButtonKind().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadKindBook();
                    showPanel(manageKindBook, "MANAGE_BOOK_KIND");
                    setTitleBody("Jenis Buku");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });

        report.getButtonJoin().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadRegristration(null, null).execute();
                    showPanel(manageRegristration, "MANAGE_REGRISTRATION");
                    setTitleBody("Laporan Pendaftaran");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        report.getButtonBorrow().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadBorrowReport(null).execute();
                    showPanel(manageBorrowReport, "MANAGE_BORROW_REPORT");
                    setTitleBody("Laporan Peminjaman");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        report.getButtonReturn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadReturnReport(null).execute();
                    showPanel(manageReturnReport, "MANAGE_RETURN_REPORT");
                    setTitleBody("Laporan Pengembalian");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        report.getButtonMember().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadMemberReport(null).execute();
                    showPanel(manageMemberReport, "MANAGE_MEMBER_REPORT");
                    setTitleBody("Laporan Anggota");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        report.getButtonBook().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadBookReport(null).execute();
                    showPanel(manageBookReport, "MANAGE_BOOK_REPORT");
                    setTitleBody("Laporan Buku");
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageMemberReport.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadMemberReport(null).execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageMemberReport.getButtonFilter().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadMemberReport("EKO KURNIAWAN KHANNEDY").execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });

        manageBookReport.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadBookReport(null).execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageBookReport.getButtonFilter().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadBookReport(manageBookReport.getBookGuest()).execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });

        manageReturnReport.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadReturnReport(null).execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageReturnReport.getButtonFilter().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadReturnReport("EKO KURNIAWAN KHANNEDY").execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });

        manageBorrowReport.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadBorrowReport(null).execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageBorrowReport.getButtonFilter().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadBorrowReport("EKO KURNIAWAN KHANNEDY").execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });

        manageRegristration.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadRegristration(null, null).execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageRegristration.getButtonFilter().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadRegristration(manageRegristration.getDateFrom(), manageRegristration.getDateUntil()).execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });

        manageBook.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadBook();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageBook.getButtonAdd().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showComponentInGlasspane(addBook);
            }
        });
        manageBook.getButtonEdit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (manageBook.getTable().getSelectedRowCount() == 0) {
                    showErrorMessage("Silahkan seleksi baris tabel yang akan diubah");
                    return;
                }
                try {
                    index = manageBook.getTable().convertRowIndexToModel(manageBook.getTable().getSelectedRow());
                    editBook.setBook(BookLibrarySQL.getBook(connection, manageBook.getModel().get(index).getId()));
                    showComponentInGlasspane(editBook);
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageBook.getButtonDelete().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        editBook.getButtonCancel().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hideComponentFromGlasspane();
            }
        });
        editBook.getButtonSubmit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editBook();
            }
        });

        addBook.getButtonCancel().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addBook.reset();
                hideComponentFromGlasspane();
            }
        });
        addBook.getButtonSubmit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        managePublisher.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadPublisher();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        managePublisher.getButtonAdd().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addPublisher();
            }
        });
        managePublisher.getButtonEdit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editPublisher();
            }
        });
        managePublisher.getButtonDelete().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deletePublisher();
            }
        });

        manageAuthor.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadAuthor();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageAuthor.getButtonAdd().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addAuthor();
            }
        });
        manageAuthor.getButtonEdit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editAuthor();
            }
        });
        manageAuthor.getButtonDelete().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAuthor();
            }
        });

        manageKindBook.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadKindBook();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageKindBook.getButtonAdd().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addKindBook();
            }
        });
        manageKindBook.getButtonEdit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editKindBook();
            }
        });
        manageKindBook.getButtonDelete().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteKindBook();
            }
        });

        manageMember.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadMember();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageMember.getButtonAdd().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showComponentInGlasspane(addMember);
            }
        });
        manageMember.getButtonEdit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (manageMember.getTable().getSelectedRowCount() == 0) {
                    showMessage("Silahkan seleksi baris tabel yang akan diubah");
                    return;
                }
                index = manageMember.getTable().convertRowIndexToModel(manageMember.getTable().getSelectedRow());
                editMember.setMember(manageMember.getModel().get(index));
                showComponentInGlasspane(editMember);
            }
        });
        manageMember.getButtonDelete().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMember();
            }
        });

        addMember.getButtonCancel().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addMember.reset();
                hideComponentFromGlasspane();
            }
        });
        addMember.getButtonSubmit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addMember();
            }
        });
        editMember.getButtonCancel().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hideComponentFromGlasspane();
            }
        });
        editMember.getButtonSubmit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editMember();
            }
        });

        manageBorrow.getButtonBorrow().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addBorrow.reset();
                showComponentInGlasspane(addBorrow);
            }
        });
        manageBorrow.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadTransaction().execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });

        addBorrow.getButtonCancel().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hideComponentFromGlasspane();
            }
        });
        addBorrow.getButtonTambah().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addBorrow();
            }
        });

        manageReturn.getButtonRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadTransaction().execute();
                } catch (SQLException ex) {
                    showErrorMessage(ex);
                }
            }
        });
        manageReturn.getButtonReturn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addReturn();
            }
        });

    }

    private void deleteMessage() {
        if (manageMessage.getTable().getSelectedRowCount() == 0) {
            showErrorMessage("Silahkan seleksi pesan yang akan dihapus");
            return;
        }
        if (showConfirm("Anda yakin?") != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            while (manageMessage.getTable().getSelectedRowCount() > 0) {
                try {
                    index = manageMessage.getTable().convertRowIndexToModel(manageMessage.getTable().getSelectionModel().getMaxSelectionIndex());
                    id = manageMessage.getModel().get(index).getID();
                    BookLibrarySQL.deleteMessage(connection, id);
                    BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                            "Menghapus pesan dengan id " + id + " yang berjudul " +
                            manageMessage.getModel().get(index).getTitle());
                    manageMessage.getModel().remove(index);
                } catch (IndexOutOfBoundsException e) {
                }
            }
        } catch (SQLException e) {
            showErrorMessage(e);
        }
    }

    private void addMessage() {
        if (!addMessage.isValid()) {
            showErrorMessage(addMessage.getErrorMessage());
            return;
        }
        int[] temp = addMessage.getTable().getSelectedRows();
        try {
            for (int i : temp) {
                try {
                    message = new DefaultMessage();
                    message.setMessage(addMessage.getTextMessage().getText());
                    message.setOperatorFrom(getOperator().getId());
                    message.setOperatorTo(addMessage.getModel().get(addMessage.getTable().convertRowIndexToModel(i)).getId());
                    message.setTitle(addMessage.getTextTitle().getText());
                    BookLibrarySQL.insertMessage(connection, message);
                    BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                            "Mengirim pesan ke " + message.getOperatorTo());
                } catch (IndexOutOfBoundsException e) {
                }
            }
            hideComponentFromGlasspane();
            showMessage("Prose pengiriman pesan berhasil");
        } catch (SQLException e) {
            showErrorMessage(e);
        }
    }

    private void addReturn() {
        if (manageReturn.getTable().getSelectedRowCount() == 0) {
            showErrorMessage("Silahkan seleksi salah satu transaksi dalam tabel");
            return;
        }
        try {
            while (manageReturn.getTable().getSelectedRowCount() > 0) {
                try {
                    index = manageReturn.getTable().convertRowIndexToModel(manageReturn.getTable().getSelectionModel().getMaxSelectionIndex());
                    id = manageReturn.getModel().get(index).getId();
                    BookLibrarySQL.insertReturnTransaction(connection, id, getOperator().getId());
                    BookLibrarySQL.updateStatusBook(connection, manageReturn.getModel().get(index).getBook(), false);
                    BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                            "Mengembalikan buku dengan nomor transaksi " + id);
                    manageReturn.getModel().remove(index);
                } catch (IndexOutOfBoundsException e) {
                }
            }
            hideComponentFromGlasspane();
            new LoadTransaction().execute();
            showMessage("Proses pengembalian berhasil");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addBorrow() {
        if (addBorrow.getBorrow() == null) {
            showErrorMessage(addBorrow.getErrorMessage());
            return;
        }
        borrowTemp = addBorrow.getBorrow();
        borrowTemp.setOperator(getOperator().getId());
        try {
            int borrow = BookLibrarySQL.getTotalBorrowBookMember(connection, borrowTemp.getMember());
            int max = BookLibrarySQL.getMaxBorrow(connection);
            if (borrow >= max) {
                showErrorMessage("Anggota tak bisa meminjam lebih dari " + max + " buku");
                return;
            }
            BookLibrarySQL.insertBorowTransaction(connection, borrowTemp);
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Menambah transaksi peminjaman buku " + borrowTemp.getBook() +
                    " yang dipinjam oleh anggota " + borrowTemp.getMember());
            hideComponentFromGlasspane();
            new LoadTransaction().execute();
            showMessage("Proses peminjaman berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void saveYourSelft() {
        if (manageMe.getOperator() == null) {
            showErrorMessage(manageMe.getErrorMessage());
            return;
        }
        try {
            BookLibrarySQL.updateOperator(connection, manageMe.getOperator(), getOperator().getId());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Merubah identitas pribadi");
            setOperator(manageMe.getOperator());
            showMessage("Proses penyimpanan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void deleteMember() {
        if (manageMember.getTable().getSelectedRowCount() == 0) {
            showErrorMessage("Silahkan seleksi baris tabel yang akan dihapus");
            return;
        }
        if (showConfirm("Anda yakin?") != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            while (manageMember.getTable().getSelectedRowCount() > 0) {
                try {
                    index = manageMember.getTable().convertRowIndexToModel(manageMember.getTable().getSelectionModel().getMaxSelectionIndex());
                    id = manageMember.getModel().get(index).getId();
                    BookLibrarySQL.deleteMember(connection, id);
                    BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                            "Menghapus anggota dengan id " + id);
                    manageMember.getModel().remove(index);
                } catch (IndexOutOfBoundsException e) {
                }
            }
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void editMember() {
        if (editMember.getMember() == null) {
            showErrorMessage(editMember.getErrorMessage());
            return;
        }
        try {
            index = manageMember.getTable().convertRowIndexToModel(manageMember.getTable().getSelectedRow());
            BookLibrarySQL.updateMember(connection, editMember.getID(), editMember.getMember());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Merubah identitas anggota " + editMember.getMember().getId());
            manageMember.getModel().setElementAt(editMember.getMember(), index);
            hideComponentFromGlasspane();
            showMessage("Proses perubahan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void addMember() {
        if (addMember.getMember() == null) {
            showErrorMessage(addMember.getErrorMessage());
            return;
        }
        try {
            BookLibrarySQL.insertMember(connection, addMember.getMember());
            BookLibrarySQL.insertRegistration(connection, addMember.getMember().getId(), getOperator().getId());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Menambah anggota baru dengan id " + addMember.getMember().getId());
            manageMember.getModel().add(addMember.getMember());
            addMember.reset();
            hideComponentFromGlasspane();
            showMessage("Proses penambahan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void deleteBook() {
        if (manageBook.getTable().getSelectedRowCount() == 0) {
            showErrorMessage("Silahkan seleksi baris tabel yang akan dihapus");
            return;
        }
        if (showConfirm("Anda yakin?") != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            while (manageBook.getTable().getSelectedRowCount() > 0) {
                try {
                    index = manageBook.getTable().convertRowIndexToModel(manageBook.getTable().getSelectionModel().getMaxSelectionIndex());
                    BookLibrarySQL.deleteBook(connection, manageBook.getModel().get(index).getId());
                    BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                            "Menghapus buku " + manageBook.getModel().get(index).getId());
                    manageBook.getModel().remove(index);
                } catch (IndexOutOfBoundsException e) {
                }
            }
            showMessage("Proses penghapusan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void editBook() {
        if (editBook.getBook() == null) {
            showErrorMessage(editBook.getErrorMessage());
            return;
        }
        try {
            index = manageBook.getTable().convertRowIndexToModel(manageBook.getTable().getSelectedRow());
            BookLibrarySQL.updateBook(connection, editBook.getBookId(), editBook.getBook());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Mengubah identitas buku " + editBook.getBook().getId());
            manageBook.getModel().setElementAt(BookLibrarySQL.getBook(connection, editBook.getBook()), index);
            hideComponentFromGlasspane();
            editBook.reset();
            showMessage("Proses berubahan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void addBook() {
        if (addBook.getBook() == null) {
            showErrorMessage(addBook.getErrorMessage());
            return;
        }
        try {
            BookLibrarySQL.insertBook(connection, addBook.getBook());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Menambah buku baru dengan id " + addBook.getBook().getId());
            manageBook.getModel().add(BookLibrarySQL.getBook(connection, addBook.getBook()));
            hideComponentFromGlasspane();
            addBook.reset();
            showMessage("Proses penambahan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }

    }

    private void deletePublisher() {
        if (managePublisher.getTable().getSelectedRowCount() == 0) {
            showErrorMessage("Silahkan seleksi baris tabel yang akan dihapus");
            return;
        }
        if (showConfirm("Anda yakin?") != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            while (managePublisher.getTable().getSelectedRowCount() > 0) {
                try {
                    index = managePublisher.getTable().convertRowIndexToModel(managePublisher.getTable().getSelectionModel().getMaxSelectionIndex());
                    id = managePublisher.getModel().get(index).getId();
                    BookLibrarySQL.deletePublisher(connection, id);
                    BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                            "Menghapus penerbit " + id);
                    managePublisher.getModel().remove(index);
                    managePublisher.reset();
                } catch (IndexOutOfBoundsException e) {
                }
            }
            showMessage("Proses penghapusan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void editPublisher() {
        if (managePublisher.getTable().getSelectedRowCount() == 0) {
            showErrorMessage("Silahkan seleksi baris tabel yang akan diubah");
            return;
        }
        if (managePublisher.getPublisher() == null) {
            showErrorMessage("Data penerbit belum lengkap");
            return;
        }
        try {
            index = managePublisher.getTable().convertRowIndexToModel(managePublisher.getTable().getSelectedRow());
            id = managePublisher.getModel().get(index).getId();
            BookLibrarySQL.updatePublisher(connection, id, managePublisher.getPublisher());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Mengubah identitas penerbit " + managePublisher.getPublisher().getId());
            managePublisher.getModel().setElementAt(managePublisher.getPublisher(), index);
            managePublisher.reset();
            showMessage("Proses perubahan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void addPublisher() {
        if (managePublisher.getPublisher() == null) {
            showErrorMessage("Data penerbit belum lengkap");
            return;
        }
        try {
            BookLibrarySQL.insertPublisher(connection, managePublisher.getPublisher());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Menambah penerbit " + managePublisher.getPublisher().getId());
            managePublisher.getModel().add(managePublisher.getPublisher());
            managePublisher.reset();
            showMessage("Proses penambahan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void deleteAuthor() {
        if (manageAuthor.getTable().getSelectedRowCount() == 0) {
            showErrorMessage("Silahkan seleksi baris tabel yang akan dihapus");
            return;
        }
        if (showConfirm("Anda yakin?") != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            while (manageAuthor.getTable().getSelectedRowCount() > 0) {
                try {
                    index = manageAuthor.getTable().convertRowIndexToModel(manageAuthor.getTable().getSelectionModel().getMaxSelectionIndex());
                    id = manageAuthor.getModel().get(index).getId();
                    BookLibrarySQL.deleteAuthor(connection, id);
                    BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                            "Menghapus pengarang " + manageAuthor.getModel().get(index).getId());
                    manageAuthor.getModel().remove(index);
                    manageAuthor.reset();
                } catch (IndexOutOfBoundsException e) {
                }
            }
            showMessage("Proses penghapusan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void editAuthor() {
        if (manageAuthor.getTable().getSelectedRowCount() == 0) {
            showErrorMessage("Silahkan seleksi baris tabel yang akan diubah");
            return;
        }
        if (manageAuthor.getAuthor() == null) {
            showErrorMessage("Data pengarang belum lengkap");
            return;
        }
        try {
            index = manageAuthor.getTable().convertRowIndexToModel(manageAuthor.getTable().getSelectedRow());
            id = manageAuthor.getModel().get(index).getId();
            BookLibrarySQL.updateAuthor(connection, id, manageAuthor.getAuthor());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Mengubah identitas pengarang " + manageAuthor.getAuthor().getId());
            manageAuthor.getModel().setElementAt(manageAuthor.getAuthor(), index);
            manageAuthor.reset();
            showMessage("Proses perubahan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void addAuthor() {
        if (manageAuthor.getAuthor() == null) {
            showErrorMessage("Data pengarang belum lengkap");
            return;
        }
        try {
            BookLibrarySQL.insertAuthor(connection, manageAuthor.getAuthor());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Menambah pengarang baru dengan id " + manageAuthor.getAuthor().getId());
            manageAuthor.getModel().add(manageAuthor.getAuthor());
            manageAuthor.reset();
            showMessage("Proses penambahan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(id);
        }
    }

    private void deleteKindBook() {
        if (manageKindBook.getTable().getSelectedRowCount() == 0) {
            showErrorMessage("Silahkan menseleksi baris tabel yang akan dihapus");
            return;
        }
        if (showConfirm("Anda yakin?") != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            while (manageKindBook.getTable().getSelectedRowCount() > 0) {
                try {
                    index = manageKindBook.getTable().convertRowIndexToModel(manageKindBook.getTable().getSelectionModel().getMaxSelectionIndex());
                    id = manageKindBook.getModel().get(index).getId();
                    BookLibrarySQL.deleteKindBook(connection, id);
                    BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                            "Menghapus jenis buku " + id);
                    manageKindBook.getModel().remove(index);
                    manageKindBook.reset();
                } catch (IndexOutOfBoundsException e) {
                }
            }
            showMessage("Proses penghapusan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void editKindBook() {
        if (manageKindBook.getTable().getSelectedRowCount() == 0) {
            showErrorMessage("Silahkan seleksi baris tabel yang akan diubah");
            return;
        }
        if (manageKindBook.getBookKind() == null) {
            showErrorMessage("Data jenis buku belum lengkap");
            return;
        }
        try {
            index = manageKindBook.getTable().convertRowIndexToModel(manageKindBook.getTable().getSelectedRow());
            id = manageKindBook.getModel().get(index).getId();
            BookLibrarySQL.updateKindBook(connection, id, manageKindBook.getBookKind());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Mengubah jenis buku " + manageKindBook.getBookKind().getId());
            manageKindBook.getModel().setElementAt(manageKindBook.getBookKind(), index);
            manageKindBook.reset();
            showMessage("Proses perubahan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void addKindBook() {
        if (manageKindBook.getBookKind() == null) {
            showErrorMessage("Data jenis buku belum lengkap");
            return;
        }
        try {
            BookLibrarySQL.insertKindBook(connection, manageKindBook.getBookKind());
            BookLibrarySQL.insertOperatorActivities(connection, getOperator().getId(),
                    "Menambah jenis buku baru dengan id " + manageKindBook.getBookKind().getId());
            manageKindBook.getModel().add(manageKindBook.getBookKind());
            manageKindBook.reset();
            showMessage("Proses penambahan berhasil");
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void loadKindBook() throws SQLException {
        new LoadKindBook().execute();
    }

    private void loadAuthor() throws SQLException {
        new LoadAuthor().execute();
    }

    private void loadPublisher() throws SQLException {
        new LoadPublisher().execute();
    }

    private void loadBook() throws SQLException {
        new LoadBook().execute();
    }

    private void loadMember() throws SQLException {
        new LoadMember().execute();
    }

    /**
     * 
     * @param message
     * @return
     */
    public int showConfirm(String message) {
        return JOptionPane.showConfirmDialog(this, message, "Konfirmasi", JOptionPane.YES_NO_OPTION);
    }

    /**
     * show error message
     * @param meesage
     */
    public void showErrorMessage(String meesage) {
        WidgetUtilities.showErrorMessage(this, meesage);
    }

    /**
     * 
     * @param meesage
     */
    public void showErrorMessage(Throwable meesage) {
        WidgetUtilities.showErrorMessage(this, meesage.getMessage());
    }

    /**
     * 
     * @param text
     */
    public void showMessage(String text) {
        JOptionPane.showMessageDialog(this, text);
    }

    private void showTransition(JComponent com) {
        try {
            ((GlassPane) getGlassPane()).startTransition(com);
        } catch (AWTException ex) {
        }
    }

    private void hideComponentFromGlasspane() {
        ((GlassPane) getGlassPane()).hideComponent();
    }

    private void showComponentInGlasspane(JComponent comp) {
        ((GlassPane) getGlassPane()).showComponent(comp);
    }

    private void showPanel(JPanel panel, String layout) {
        if (panel.isVisible()) {
            return;
        } else {
            showTransition(panelUtama);
            ((CardLayout) panelTengah.getLayout()).show(panelTengah, layout);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        glassPane = new usu.widget.GlassPane();
        about = new usu.perpustakaan.buku.widget.About();
        addBook = new usu.perpustakaan.buku.widget.operator.AddBook();
        editBook = new usu.perpustakaan.buku.widget.operator.EditBook();
        addMember = new usu.perpustakaan.buku.widget.operator.AddMember();
        editMember = new usu.perpustakaan.buku.widget.operator.EditMember();
        addBorrow = new usu.perpustakaan.buku.widget.operator.AddBorrow();
        addMessage = new usu.perpustakaan.buku.widget.operator.AddMessage();
        detailMessage = new usu.perpustakaan.buku.widget.operator.DetailMessage();
        panelUtama = new usu.perpustakaan.buku.widget.template.PanelBlackGreen();
        labelTitleBody = new javax.swing.JLabel();
        panelTengah = new javax.swing.JPanel();
        home = new usu.perpustakaan.buku.widget.operator.PanelHome();
        book = new usu.perpustakaan.buku.widget.operator.PanelBook();
        activities = new usu.perpustakaan.buku.widget.operator.PanelActivities();
        report = new usu.perpustakaan.buku.widget.operator.PanelReport();
        manageBook = new usu.perpustakaan.buku.widget.operator.ManageBook();
        managePublisher = new usu.perpustakaan.buku.widget.operator.ManagePublisher();
        manageAuthor = new usu.perpustakaan.buku.widget.operator.ManageAuthor();
        manageKindBook = new usu.perpustakaan.buku.widget.operator.ManageBookKind();
        manageMember = new usu.perpustakaan.buku.widget.operator.ManageMember();
        manageMe = new usu.perpustakaan.buku.widget.operator.ManageMe();
        manageBorrow = new usu.perpustakaan.buku.widget.operator.ManageBorrow();
        manageReturn = new usu.perpustakaan.buku.widget.operator.ManageReturn();
        manageRegristration = new usu.perpustakaan.buku.widget.operator.ManageRegristration();
        manageBorrowReport = new usu.perpustakaan.buku.widget.operator.ManageBorrowReport();
        manageReturnReport = new usu.perpustakaan.buku.widget.operator.ManageReturnReport();
        manageMemberReport = new usu.perpustakaan.buku.widget.operator.ManageMemberReport();
        manageBookReport = new usu.perpustakaan.buku.widget.operator.ManageBookReport();
        manageMessage = new usu.perpustakaan.buku.widget.operator.ManageMessage();
        toolBar = new usu.perpustakaan.buku.widget.template.ToolbarBlackGreen();
        buttonHome = new usu.widget.ButtonGlass();
        buttonAbout = new usu.widget.ButtonGlass();
        usu.perpustakaan.buku.widget.template.ToolbarBlackGreen statusBar = new usu.perpustakaan.buku.widget.template.ToolbarBlackGreen();
        labelStatusBar = new javax.swing.JLabel();
        labelProgress = new javax.swing.JLabel();
        progressBar = new usu.perpustakaan.buku.widget.template.ProgressBarBlackGreen();

        setGlassPane(glassPane);
        glassPane.setAlphaDisable(10);
        glassPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 50));

        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });

        setTitle("e'BookLibrary - Operator");
        setAnimationHide(usu.widget.constan.Animation.HIDE_TO_LEFT);
        setAnimationShow(usu.widget.constan.Animation.SHOW_FROM_BOTTOM);
        setLocationRelativeTo(null);
        setMinimumSize(new java.awt.Dimension(800, 640));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelTitleBody.setFont(new java.awt.Font("Tahoma", 1, 24));
        labelTitleBody.setForeground(new java.awt.Color(255, 255, 255));
        labelTitleBody.setText("e'BookLibrary");

        panelTengah.setOpaque(false);
        panelTengah.setLayout(new java.awt.CardLayout());
        panelTengah.add(home, "HOME");
        panelTengah.add(book, "BOOK");
        panelTengah.add(activities, "ACTIVITIES");
        panelTengah.add(report, "REPORT");
        panelTengah.add(manageBook, "MANAGE_BOOK");
        panelTengah.add(managePublisher, "MANAGE_PUBLISHER");
        panelTengah.add(manageAuthor, "MANAGE_AUTHOR");
        panelTengah.add(manageKindBook, "MANAGE_BOOK_KIND");
        panelTengah.add(manageMember, "MANAGE_MEMBER");
        panelTengah.add(manageMe, "MANAGE_ME");
        panelTengah.add(manageBorrow, "MANAGE_BORROW");
        panelTengah.add(manageReturn, "MANAGE_RETURN");
        panelTengah.add(manageRegristration, "MANAGE_REGRISTRATION");
        panelTengah.add(manageBorrowReport, "MANAGE_BORROW_REPORT");
        panelTengah.add(manageReturnReport, "MANAGE_RETURN_REPORT");
        panelTengah.add(manageMemberReport, "MANAGE_MEMBER_REPORT");
        panelTengah.add(manageBookReport, "MANAGE_BOOK_REPORT");
        panelTengah.add(manageMessage, "MANAGE_MESSAGE");

        javax.swing.GroupLayout panelUtamaLayout = new javax.swing.GroupLayout(panelUtama);
        panelUtama.setLayout(panelUtamaLayout);
        panelUtamaLayout.setHorizontalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTengah, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addComponent(labelTitleBody))
                .addContainerGap())
        );
        panelUtamaLayout.setVerticalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitleBody)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTengah, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panelUtama, java.awt.BorderLayout.CENTER);

        toolBar.setRollover(true);
        toolBar.setToolTipText("Tool Bar");

        buttonHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Home 2.png"))); // NOI18N
        buttonHome.setMnemonic('M');
        buttonHome.setText("Menu");
        buttonHome.setToolTipText("Klik untuk masuk ke menu utama");
        buttonHome.setFocusable(false);
        buttonHome.setRoundRect(true);
        buttonHome.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHomeActionPerformed(evt);
            }
        });
        toolBar.add(buttonHome);

        buttonAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/Pics 1.png"))); // NOI18N
        buttonAbout.setMnemonic('T');
        buttonAbout.setText("Tentang");
        buttonAbout.setToolTipText("Klik untuk menampilkan pane tentang saya");
        buttonAbout.setFocusable(false);
        buttonAbout.setRoundRect(true);
        buttonAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAboutActionPerformed(evt);
            }
        });
        toolBar.add(javax.swing.Box.createHorizontalGlue());
        toolBar.add(buttonAbout);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        statusBar.setRollover(true);
        statusBar.setToolTipText("Status Bar");

        labelStatusBar.setText("e'BookLibrary");
        labelStatusBar.setToolTipText("Status Bar Text");
        statusBar.add(labelStatusBar);

        labelProgress.setToolTipText("Status Progress Text");
        statusBar.add(javax.swing.Box.createHorizontalGlue());
        statusBar.add(labelProgress);

        progressBar.setToolTipText("Progress Bar");
        progressBar.setMaximumSize(new java.awt.Dimension(100, 14));
        statusBar.add(progressBar);

        getContentPane().add(statusBar, java.awt.BorderLayout.PAGE_END);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    exit();
}//GEN-LAST:event_formWindowClosing

private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
    hideComponentFromGlasspane();
}//GEN-LAST:event_aboutActionPerformed

private void buttonAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAboutActionPerformed
    showComponentInGlasspane(about);
}//GEN-LAST:event_buttonAboutActionPerformed

private void buttonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHomeActionPerformed
    showPanel(home, "HOME");
    setTitleBody(null);
}//GEN-LAST:event_buttonHomeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.perpustakaan.buku.widget.About about;
    usu.perpustakaan.buku.widget.operator.PanelActivities activities;
    usu.perpustakaan.buku.widget.operator.AddBook addBook;
    usu.perpustakaan.buku.widget.operator.AddBorrow addBorrow;
    usu.perpustakaan.buku.widget.operator.AddMember addMember;
    usu.perpustakaan.buku.widget.operator.AddMessage addMessage;
    usu.perpustakaan.buku.widget.operator.PanelBook book;
    usu.widget.ButtonGlass buttonAbout;
    usu.widget.ButtonGlass buttonHome;
    usu.perpustakaan.buku.widget.operator.DetailMessage detailMessage;
    usu.perpustakaan.buku.widget.operator.EditBook editBook;
    usu.perpustakaan.buku.widget.operator.EditMember editMember;
    usu.widget.GlassPane glassPane;
    usu.perpustakaan.buku.widget.operator.PanelHome home;
    javax.swing.JLabel labelProgress;
    javax.swing.JLabel labelStatusBar;
    javax.swing.JLabel labelTitleBody;
    usu.perpustakaan.buku.widget.operator.ManageAuthor manageAuthor;
    usu.perpustakaan.buku.widget.operator.ManageBook manageBook;
    usu.perpustakaan.buku.widget.operator.ManageBookReport manageBookReport;
    usu.perpustakaan.buku.widget.operator.ManageBorrow manageBorrow;
    usu.perpustakaan.buku.widget.operator.ManageBorrowReport manageBorrowReport;
    usu.perpustakaan.buku.widget.operator.ManageBookKind manageKindBook;
    usu.perpustakaan.buku.widget.operator.ManageMe manageMe;
    usu.perpustakaan.buku.widget.operator.ManageMember manageMember;
    usu.perpustakaan.buku.widget.operator.ManageMemberReport manageMemberReport;
    usu.perpustakaan.buku.widget.operator.ManageMessage manageMessage;
    usu.perpustakaan.buku.widget.operator.ManagePublisher managePublisher;
    usu.perpustakaan.buku.widget.operator.ManageRegristration manageRegristration;
    usu.perpustakaan.buku.widget.operator.ManageReturn manageReturn;
    usu.perpustakaan.buku.widget.operator.ManageReturnReport manageReturnReport;
    javax.swing.JPanel panelTengah;
    usu.perpustakaan.buku.widget.template.PanelBlackGreen panelUtama;
    usu.perpustakaan.buku.widget.template.ProgressBarBlackGreen progressBar;
    usu.perpustakaan.buku.widget.operator.PanelReport report;
    usu.perpustakaan.buku.widget.template.ToolbarBlackGreen toolBar;
    // End of variables declaration//GEN-END:variables

    /**
     * 
     */
    public class LoadMessage extends SwingWorker<List<Message>, String> {

        private ArrayList<Message> array;
        private ResultSet result;

        /**
         * 
         * @param operator
         * @throws java.sql.SQLException
         */
        public LoadMessage(String operator) throws SQLException {
            super();
            result = BookLibrarySQL.getResultMessage(connection, operator);
            array = new ArrayList<Message>();

            labelProgress.setText("");
            labelStatusBar.setText("Loading Pesan");
            progressBar.setIndeterminate(true);
            manageMessage.getModel().removeAllElements();
            manageMessage.setFreeze(true);
            buttonHome.setEnabled(false);
        }

        @Override
        protected List<Message> doInBackground() throws Exception {
            while (result.next()) {
                DefaultMessage message = new DefaultMessage();
                message.setMessage(result.getString(FieldConstan.TABEL_PESAN.PESAN));
                message.setOperatorFrom(result.getString(FieldConstan.TABEL_PESAN.ID_OPERATOR_FROM));
                message.setOperatorTo(result.getString(FieldConstan.TABEL_PESAN.ID_OPERATOR_TO));
                message.setTime(result.getTimestamp(FieldConstan.TABEL_PESAN.WAKTU));
                message.setTitle(result.getString(FieldConstan.TABEL_PESAN.JUDUL));
                message.setID(result.getString(FieldConstan.TABEL_PESAN.ID_PESAN));
                publish(message.getTitle());
                array.add(message);
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        manageMessage.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        progressBar.setIndeterminate(false);
                        manageMessage.setFreeze(false);
                        buttonHome.setEnabled(true);
                        try {
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load pesan " + s);
            }
        }
    }

    /**
     * load book
     */
    public class LoadBookReport extends SwingWorker<List<DefaultBookGuest>, String> {

        private ArrayList<DefaultBookGuest> array;
        private ResultSet result;

        /**
         * 
         * @param bookReport 
         * @throws java.sql.SQLException
         */
        public LoadBookReport(DefaultBookGuest bookReport) throws SQLException {
            super();
            if (bookReport == null) {
                result = BookLibrarySQL.getResultBookGuest(connection);
            } else {
                result = BookLibrarySQL.getResultBookGuest(connection, bookReport);
            }
            array = new ArrayList<DefaultBookGuest>();

            buttonHome.setEnabled(false);
            manageBookReport.getModel().removeAllElements();
            manageBookReport.setFreeze(true);
            labelProgress.setText("");
            labelStatusBar.setText("Loading Book");
            progressBar.setIndeterminate(true);
        }

        @Override
        protected List<DefaultBookGuest> doInBackground() throws Exception {
            while (result.next()) {
                DefaultBookGuest book = new DefaultBookGuest();
                book.setAuthor(BookLibrarySQL.getBookAuthor(connection, result.getString(FieldConstan.TABEL_BUKU.ID_PENGARANG)));
                book.setKind(BookLibrarySQL.getBookKind(connection, result.getString(FieldConstan.TABEL_BUKU.ID_JENIS)));
                book.setPublisher(BookLibrarySQL.getBookPublisher(connection, result.getString(FieldConstan.TABEL_BUKU.ID_PENERBIT)));
                book.setId(result.getString(FieldConstan.TABEL_BUKU.ID_BUKU));
                book.setTitle(result.getString(FieldConstan.TABEL_BUKU.JUDUL_BUKU));
                book.setBorrow(result.getBoolean(FieldConstan.TABEL_STATUS_BUKU.SEDANG_DIPINJAM));
                book.setTotalBorrow(result.getInt(FieldConstan.TABEL_STATUS_BUKU.TOTAL_DIPINJAM));
                publish(book.getId());
                array.add(book);
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        manageBookReport.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GuestForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(GuestForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        buttonHome.setEnabled(true);
                        manageBookReport.setFreeze(false);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        progressBar.setIndeterminate(false);
                        try {
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(GuestForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load buku " + s);
            }
        }
    }

    /**
     * 
     */
    public class LoadMemberReport extends SwingWorker<List<DefaultMemberReport>, String> {

        private ResultSet result;
        private ArrayList<DefaultMemberReport> array;

        /**
         * 
         * @param status
         * @throws java.sql.SQLException 
         */
        public LoadMemberReport(String status) throws SQLException {
            super();
            if (status == null) {
                result = BookLibrarySQL.getResultMemberReport(connection);
            }else{
                result = BookLibrarySQL.getResultMemberReport(connection,
                        manageMemberReport.getIDAnggota(),
                        manageMemberReport.getNamaAnggota(),
                        manageMemberReport.getLahirFrom(),
                        manageMemberReport.getLahirUntil(),
                        manageMemberReport.getDaftarFrom(),
                        manageMemberReport.getDaftarUntil());
            }
            array = new ArrayList<DefaultMemberReport>();

            manageMemberReport.getModel().removeAllElements();
            manageMemberReport.setFreeze(true);
            buttonHome.setEnabled(false);
            labelProgress.setText("");
            labelStatusBar.setText("Loading Anggota");
            progressBar.setIndeterminate(true);
        }

        @Override
        protected List<DefaultMemberReport> doInBackground() throws Exception {
            while (result.next()) {
                DefaultMemberReport mem = new DefaultMemberReport();
                mem.setAddress(result.getString(FieldConstan.TABEL_ANGGOTA.ALAMAT_ANGGOTA));
                mem.setBorn(result.getDate(FieldConstan.TABEL_ANGGOTA.TANGGAL_LAHIR_ANGGOTA));
                mem.setContact(result.getString(FieldConstan.TABEL_ANGGOTA.KONTAK_ANGGOTA));
                mem.setId(result.getString(FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA));
                mem.setName(result.getString(FieldConstan.TABEL_ANGGOTA.NAMA_ANGGOTA));
                mem.setTerdaftar(result.getDate(FieldConstan.TABEL_PENDAFTARAN.TANGGAL_PENDAFTARAN));
                mem.setTotalMeminjam(result.getInt(FieldConstan.TABEL_STATUS_ANGGOTA.TOTAL_MEMINJAM));
                publish(mem.getId());
                array.add(mem);
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        manageMemberReport.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        manageMemberReport.setFreeze(false);
                        buttonHome.setEnabled(true);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        progressBar.setIndeterminate(false);
                        try {
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load anggota " + s);
            }
        }
    }

    /**
     * load return report
     */
    public class LoadReturnReport extends SwingWorker<List<Return>, String> {

        private ResultSet result;
        private ArrayList<Return> array;

        /**
         * 
         * @param status
         * @throws java.sql.SQLException 
         */
        public LoadReturnReport(String status) throws SQLException {
            super();
            if (status == null) {
                result = BookLibrarySQL.getResultReturnReport(connection);
            } else {
                result = BookLibrarySQL.getResultReturnReport(connection,
                        manageReturnReport.getMemberId(), manageReturnReport.getOperatorId(),
                        manageReturnReport.getBookId(), manageReturnReport.getDateFrom(),
                        manageReturnReport.getDateUntil());
            }
            array = new ArrayList<Return>();

            manageReturnReport.setFreeze(true);
            buttonHome.setEnabled(false);
            manageReturnReport.getModel().removeAllElements();
            labelProgress.setText("");
            labelStatusBar.setText("Loading Transaksi Pengembalian");
            progressBar.setIndeterminate(true);
        }

        @Override
        protected List<Return> doInBackground() throws Exception {
            while (result.next()) {
                DefaultReturn ret = new DefaultReturn();
                ret.setBook(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_BUKU));
                ret.setDateReturn(result.getDate(FieldConstan.TABEL_PENGEMBALIAN.TANGGAL_PENGEMBALIAN));
                ret.setDenda(result.getInt(FieldConstan.TABEL_PENGEMBALIAN.DENDA));
                ret.setId(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_PEMINJAMAN));
                ret.setMember(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_ANGGOTA));
                ret.setOperator(result.getString(FieldConstan.TABEL_PENGEMBALIAN.ID_OPERATOR));
                ret.setTerlambar(result.getInt(FieldConstan.TABEL_PENGEMBALIAN.TERLAMBAT));
                publish(ret.getId());
                array.add(ret);
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        manageReturnReport.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        manageReturnReport.setFreeze(false);
                        buttonHome.setEnabled(true);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        progressBar.setIndeterminate(false);
                        try {
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load transaksi " + s);
            }
        }
    }

    /**
     * laod borrow report
     */
    public class LoadBorrowReport extends SwingWorker<List<Borrow>, String> {

        private ResultSet result;
        private ArrayList<Borrow> array;

        /**
         * 
         * @param status
         * @throws java.sql.SQLException
         */
        public LoadBorrowReport(String status) throws SQLException {
            super();
            array = new ArrayList<Borrow>();
            if (status == null) {
                result = BookLibrarySQL.getResultBorrowReport(connection);
            } else {
                result = BookLibrarySQL.getResultBorrowReport(connection,
                        manageBorrowReport.getMemberId(), manageBorrowReport.getOperatorId(),
                        manageBorrowReport.getBookId(), manageBorrowReport.getDateFrom(),
                        manageBorrowReport.getDateUntil(), manageBorrowReport.isReturn());
            }

            labelProgress.setText("");
            labelStatusBar.setText("Loading transakti peminjaman");
            progressBar.setIndeterminate(true);
            manageBorrowReport.setFreeze(true);
            manageBorrowReport.getModel().removeAllElements();
            buttonHome.setEnabled(false);
        }

        @Override
        protected List<Borrow> doInBackground() throws Exception {
            while (result.next()) {
                DefaultBorrow b = new DefaultBorrow();
                b.setBook(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_BUKU));
                b.setDate(result.getDate(FieldConstan.TABEL_PEMINJAMAN.TANGGAL_PEMINJAMAN));
                b.setId(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_PEMINJAMAN));
                b.setMember(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_ANGGOTA));
                b.setOperator(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_OPERATOR));
                b.setReturnDate(result.getDate(FieldConstan.TABEL_PEMINJAMAN.TANGGAL_PENGEMBALIAN));
                publish(b.getId());
                array.add(b);
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        manageBorrowReport.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        progressBar.setIndeterminate(false);
                        manageBorrowReport.setFreeze(false);
                        buttonHome.setEnabled(true);
                        try {
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load transaksi " + s);
            }
        }
    }

    /**
     * load data pendaftaran
     */
    public class LoadRegristration extends SwingWorker<List<Regristration>, String> {

        private ResultSet result;
        private ArrayList<Regristration> array;

        /**
         * 
         * @param object
         * @param object0
         * @throws java.sql.SQLException
         */
        public LoadRegristration(Date object, Date object0) throws SQLException {
            super();
            if (object == null && object0 == null) {
                result = BookLibrarySQL.getResultRegristration(connection);
            } else {
                result = BookLibrarySQL.getResultRegristration(connection, object, object0);
            }
            array = new ArrayList<Regristration>();

            buttonHome.setEnabled(false);
            labelProgress.setText("");
            labelStatusBar.setText("Loading Data Pendaftaran");
            progressBar.setIndeterminate(true);
            manageRegristration.setFreeze(true);
            manageRegristration.getModel().removeAllElements();
        }

        @Override
        protected List<Regristration> doInBackground() throws Exception {
            while (result.next()) {
                DefaultRegristration reg = new DefaultRegristration();
                reg.setMember(result.getString(FieldConstan.TABEL_PENDAFTARAN.ID_ANGGOTA));
                reg.setOperator(result.getString(FieldConstan.TABEL_PENDAFTARAN.ID_OPERATOR));
                reg.setTime(result.getDate(FieldConstan.TABEL_PENDAFTARAN.TANGGAL_PENDAFTARAN));
                array.add(reg);
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        manageRegristration.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        buttonHome.setEnabled(true);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        progressBar.setIndeterminate(false);
                        manageRegristration.setFreeze(false);
                        try {
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load data pendaftaran " + s);
            }
        }
    }

    /**
     * load borrow
     */
    public class LoadTransaction extends SwingWorker<List<Borrow>, String> {

        private ResultSet result;
        private ArrayList<Borrow> array;

        /**
         * make new load borrow
         * @throws java.sql.SQLException 
         */
        public LoadTransaction() throws SQLException {
            super();
            result = BookLibrarySQL.getResultBorrowFalse(connection);
            array = new ArrayList<Borrow>();

            labelProgress.setText("");
            labelStatusBar.setText("Loading Transaksi");
            progressBar.setIndeterminate(true);
            buttonHome.setEnabled(false);
            manageBorrow.setFreeze(true);
            manageBorrow.getModel().removeAllElements();
            manageReturn.setFreeze(true);
            manageReturn.getModel().removeAllElements();
        }

        @Override
        protected List<Borrow> doInBackground() throws Exception {
            while (result.next()) {
                DefaultBorrow b = new DefaultBorrow();
                b.setBook(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_BUKU));
                b.setDate(result.getDate(FieldConstan.TABEL_PEMINJAMAN.TANGGAL_PEMINJAMAN));
                b.setId(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_PEMINJAMAN));
                b.setMember(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_ANGGOTA));
                b.setOperator(result.getString(FieldConstan.TABEL_PEMINJAMAN.ID_OPERATOR));
                b.setReturnDate(result.getDate(FieldConstan.TABEL_PEMINJAMAN.TANGGAL_PENGEMBALIAN));
                array.add(b);
                publish(b.getId());
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                    
                     
                        
                        
                        
                         @Override
                public void run() {
                    try {
                        manageBorrow.getModel().add(get());
                        manageReturn.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        progressBar.setIndeterminate(false);
                        buttonHome.setEnabled(true);
                        manageBorrow.setFreeze(false);
                        manageReturn.setFreeze(false);
                        try {
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load Transaksi " + s);
            }
        }
    }

    /**
     * load anggota
     */
    public class LoadMember extends SwingWorker<List<Member>, String> {

        private ResultSet result;
        private ArrayList<Member> array;

        /**
         * 
         * @throws java.sql.SQLException
         */
        public LoadMember() throws SQLException {
            super();
            result = BookLibrarySQL.getResultMember(connection);
            array = new ArrayList<Member>();

            buttonHome.setEnabled(false);
            labelProgress.setText("");
            labelStatusBar.setText("Loading Anggota");
            progressBar.setIndeterminate(true);
            manageMember.getModel().removeAllElements();
            manageMember.setFreeze(true);
        }

        @Override
        protected List<Member> doInBackground() throws Exception {
            while (result.next()) {
                DefaultMember member = new DefaultMember();
                member.setAddress(result.getString(FieldConstan.TABEL_ANGGOTA.ALAMAT_ANGGOTA));
                member.setBorn(result.getDate(FieldConstan.TABEL_ANGGOTA.TANGGAL_LAHIR_ANGGOTA));
                member.setContact(result.getString(FieldConstan.TABEL_ANGGOTA.KONTAK_ANGGOTA));
                member.setId(result.getString(FieldConstan.TABEL_ANGGOTA.ID_ANGGOTA));
                member.setName(result.getString(FieldConstan.TABEL_ANGGOTA.NAMA_ANGGOTA));
                array.add(member);
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        manageMember.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        buttonHome.setEnabled(true);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        progressBar.setIndeterminate(false);
                        manageMember.setFreeze(false);
                        try {
                            array = null;
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load Anggota " + s);
            }
        }
    }

    /**
     * load kind book
     */
    public class LoadKindBook extends SwingWorker<List<KindBook>, String> {

        private ResultSet result;
        private ArrayList<KindBook> array;

        /**
         * 
         * @throws java.sql.SQLException
         */
        public LoadKindBook() throws SQLException {
            super();
            result = BookLibrarySQL.getResultKindBook(connection);
            array = new ArrayList<KindBook>();

            buttonHome.setEnabled(false);
            progressBar.setIndeterminate(true);
            labelProgress.setText("");
            labelStatusBar.setText("Loading Jenis Buku");
            manageKindBook.getModel().removeAllElements();
            manageKindBook.setFreeze(true);
        }

        @Override
        protected List<KindBook> doInBackground() throws Exception {
            while (result.next()) {
                DefaultKindBook kind = new DefaultKindBook();
                kind.setId(result.getString(FieldConstan.TABEL_JENIS_BUKU.ID_JENIS));
                kind.setKind(result.getString(FieldConstan.TABEL_JENIS_BUKU.JENIS_BUKU));
                array.add(kind);
                publish(result.getString(FieldConstan.TABEL_JENIS_BUKU.JENIS_BUKU));
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        manageKindBook.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        buttonHome.setEnabled(true);
                        progressBar.setIndeterminate(false);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        manageKindBook.setFreeze(false);
                        try {
                            array = null;
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load Jenis Buku " + s);
            }
        }
    }

    /**
     * load author
     */
    public class LoadAuthor extends SwingWorker<List<Author>, String> {

        private ResultSet result;
        private ArrayList<Author> array;

        /**
         * create new load author
         * @throws java.sql.SQLException 
         */
        public LoadAuthor() throws SQLException {
            super();
            result = BookLibrarySQL.getResultAuthor(connection);
            array = new ArrayList<Author>();

            manageAuthor.setFreeze(true);
            manageAuthor.getModel().removeAllElements();
            buttonHome.setEnabled(false);
            progressBar.setIndeterminate(true);
            labelProgress.setText("");
            labelStatusBar.setText("Loading Pengarang");
        }

        @Override
        protected List<Author> doInBackground() throws Exception {
            while (result.next()) {
                DefaultAuthor author = new DefaultAuthor();
                author.setId(result.getString(FieldConstan.TABEL_PENGARANG.ID_PENGARANG));
                author.setName(result.getString(FieldConstan.TABEL_PENGARANG.NAMA_PENGARANG));
                array.add(author);
                publish(result.getString(FieldConstan.TABEL_PENGARANG.NAMA_PENGARANG));
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        manageAuthor.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        buttonHome.setEnabled(true);
                        progressBar.setIndeterminate(false);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        manageAuthor.setFreeze(false);
                        try {
                            array = null;
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            super.process(chunks);
            for (String s : chunks) {
                labelProgress.setText("Load Pengarang " + s);
            }
        }
    }

    /**
     * load publisher
     */
    public class LoadPublisher extends SwingWorker<List<Publisher>, String> {

        private ResultSet result;
        private ArrayList<Publisher> array;

        /**
         * 
         * @throws java.sql.SQLException
         */
        public LoadPublisher() throws SQLException {
            super();
            result = BookLibrarySQL.getResultPublisher(connection);
            array = new ArrayList<Publisher>();

            managePublisher.setFreeze(true);
            managePublisher.getModel().removeAllElements();
            buttonHome.setEnabled(false);
            progressBar.setIndeterminate(true);
            labelProgress.setText("");
            labelStatusBar.setText("Loading Penerbit");

        }

        @Override
        protected List<Publisher> doInBackground() throws Exception {
            while (result.next()) {
                DefaultPublisher p = new DefaultPublisher();
                p.setId(result.getString(FieldConstan.TABEL_PENERBIT.ID_PENERBIT));
                p.setName(result.getString(FieldConstan.TABEL_PENERBIT.NAMA_PENERBIT));
                array.add(p);
                publish(result.getString(FieldConstan.TABEL_PENERBIT.NAMA_PENERBIT));
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        managePublisher.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        buttonHome.setEnabled(true);
                        progressBar.setIndeterminate(false);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        managePublisher.getTextSearch().setText("");
                        managePublisher.setFreeze(false);
                        try {
                            array = null;
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String i : chunks) {
                labelProgress.setText("Loading Penerbit " + i);
            }
        }
    }

    /**
     * load book
     */
    public class LoadBook extends SwingWorker<List<Book>, String> {

        private ResultSet result;
        private ArrayList<Book> array;

        /**
         * 
         * @throws java.sql.SQLException 
         */
        public LoadBook() throws SQLException {
            super();
            result = BookLibrarySQL.getResultBook(connection);
            array = new ArrayList<Book>();

            buttonHome.setEnabled(false);
            progressBar.setIndeterminate(true);
            labelStatusBar.setText("Loading Buku");
            manageBook.getModel().removeAllElements();
            manageBook.setFreeze(true);
        }

        @Override
        protected List<Book> doInBackground() throws Exception {
            while (result.next()) {
                DefaultBook book = new DefaultBook();
                book.setAuthor(BookLibrarySQL.getBookAuthor(connection, result.getString(FieldConstan.TABEL_BUKU.ID_PENGARANG)));
                book.setId(result.getString(FieldConstan.TABEL_BUKU.ID_BUKU));
                book.setKind(BookLibrarySQL.getBookKind(connection, result.getString(FieldConstan.TABEL_BUKU.ID_JENIS)));
                book.setPublisher(BookLibrarySQL.getBookPublisher(connection, result.getString(FieldConstan.TABEL_BUKU.ID_PENERBIT)));
                book.setTitle(result.getString(FieldConstan.TABEL_BUKU.JUDUL_BUKU));
                publish(result.getString(FieldConstan.TABEL_BUKU.JUDUL_BUKU));
                array.add(book);
            }
            return array;
        }

        @Override
        protected void done() {
            executor.execute(new  

                  Runnable() {

                    
                    
                    
                     
                        
                        
                         @Override
                public void run() {
                    try {
                        manageBook.getModel().add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        buttonHome.setEnabled(true);
                        manageBook.setFreeze(false);
                        progressBar.setIndeterminate(false);
                        labelProgress.setText("");
                        labelStatusBar.setText("e'BookLibrary");
                        try {
                            array = null;
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            });
        }

        @Override
        protected void process(List<String> chunks) {
            for (String s : chunks) {
                labelProgress.setText("Load Buku " + s);
            }
        }
    }

    /**
     * 
     * @param title
     */
    public void setTitleBody(String title) {
        if (title == null) {
            labelTitleBody.setText("e'BookLibrary");
        } else {
            labelTitleBody.setText("e'BookLibrary - " + title);
        }
    }
}
