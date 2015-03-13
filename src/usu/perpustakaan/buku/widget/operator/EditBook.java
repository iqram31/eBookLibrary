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
package usu.perpustakaan.buku.widget.operator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import usu.perpustakaan.buku.constan.FieldConstan;
import usu.perpustakaan.buku.data.DefaultAuthor;
import usu.perpustakaan.buku.data.DefaultBook;
import usu.perpustakaan.buku.data.DefaultKindBook;
import usu.perpustakaan.buku.data.DefaultPublisher;
import usu.perpustakaan.buku.data.template.Book;
import usu.perpustakaan.buku.sql.BookLibrarySQL;
import usu.perpustakaan.buku.util.StringUtil;
import usu.perpustakaan.buku.widget.admin.ManageOperator;
import usu.perpustakaan.buku.widget.template.PanelBlackGreen;
import usu.perpustakaan.buku.widget.template.TableModelAuthor;
import usu.perpustakaan.buku.widget.template.TableModelKindBook;
import usu.perpustakaan.buku.widget.template.TableModelPublisher;
import usu.widget.ButtonGlass;
import usu.widget.text.DefaultDocument;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author  usu
 */
public class EditBook extends PanelBlackGreen {

    private static final long serialVersionUID = -1;
    private Connection connection;
    private TableModelAuthor modelAuthor;
    private TableModelKindBook modelKindBook;
    private TableModelPublisher modelPublisher;
    private ExecutorService executor;
    private String id;
    private int index;
    private String errorMessage;
    private Book book;
    private String bookId;

    /** Creates new form AddBook */
    public EditBook() {
        initVariables();
        initComponents();
        initFinals();
        initActions();
    }

    /**
     * error message
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @return
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 
     * @return
     */
    public Book getBook() {
        if (textId.getText().trim().equals("")) {
            errorMessage = "ID buku kosongg";
            return null;
        }
        if (textTitle.getText().trim().equals("")) {
            errorMessage = "Judul buku kosong";
            return null;
        }
        if (textAuthor.getText().trim().equals("")) {
            errorMessage = "Pengarang buku kosong";
            return null;
        }
        if (textPublisher.getText().trim().equals("")) {
            errorMessage = "Penerbit buku kosong";
            return null;
        }
        if (textCategory.getText().trim().equals("")) {
            errorMessage = "Kategori buku kosong";
            return null;
        }
        errorMessage = "";
        book = new DefaultBook();
        book.setAuthor(textAuthor.getText());
        book.setId(textId.getText());
        book.setKind(textCategory.getText());
        book.setPublisher(textPublisher.getText());
        book.setTitle(textTitle.getText());
        return book;
    }

    /**
     * 
     * @param book
     */
    public void setBook(Book book) {
        this.book = book;
        bookId = book.getId();
        textAuthor.setText(book.getAuthor());
        textCategory.setText(book.getKind());
        textId.setText(book.getId());
        textPublisher.setText(book.getPublisher());
        textTitle.setText(book.getTitle());
    }

    /**
     * 
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * 
     * @return
     */
    public ButtonGlass getButtonCancel() {
        return buttonCancel;
    }

    /**
     * 
     * @return
     */
    public ButtonGlass getButtonSubmit() {
        return buttonSubmit;
    }

    private void initActions() {
        tableCategory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    index = tableCategory.convertRowIndexToModel(tableCategory.getSelectedRow());
                    id = modelKindBook.get(index).getId();
                    textCategory.setText(id);
                } catch (Throwable t) {
                }
            }
        });

        tableAuthor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    index = tableAuthor.convertRowIndexToModel(tableAuthor.getSelectedRow());
                    id = modelAuthor.get(index).getId();
                    textAuthor.setText(id);
                } catch (Throwable t) {
                }
            }
        });

        tablePublisher.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    index = tablePublisher.convertRowIndexToModel(tablePublisher.getSelectedRow());
                    id = modelPublisher.get(index).getId();
                    textPublisher.setText(id);
                } catch (Throwable t) {
                }
            }
        });

        textSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(textSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(textSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable(textSearch.getText());
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void filterTable(String text) {
        if (tableAuthor.getRowSorter() != null) {
            if (text.trim().equals("")) {
                ((TableRowSorter<TableModel>) tableAuthor.getRowSorter()).setRowFilter(null);
            } else {
                ((TableRowSorter<TableModel>) tableAuthor.getRowSorter()).setRowFilter(RowFilter.regexFilter(text));
            }
        }
        if (tableCategory.getRowSorter() != null) {
            if (text.trim().equals("")) {
                ((TableRowSorter<TableModel>) tableCategory.getRowSorter()).setRowFilter(null);
            } else {
                ((TableRowSorter<TableModel>) tableCategory.getRowSorter()).setRowFilter(RowFilter.regexFilter(text));
            }
        }
        if (tablePublisher.getRowSorter() != null) {
            if (text.trim().equals("")) {
                ((TableRowSorter<TableModel>) tablePublisher.getRowSorter()).setRowFilter(null);
            } else {
                ((TableRowSorter<TableModel>) tablePublisher.getRowSorter()).setRowFilter(RowFilter.regexFilter(text));
            }
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

        viewPort = new usu.perpustakaan.buku.widget.template.ViewPortGlass();
        tableAuthor = new javax.swing.JTable();
        tablePublisher = new javax.swing.JTable();
        tableCategory = new javax.swing.JTable();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        textTitle = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        textId = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        textCategory = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        textPublisher = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        textAuthor = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        buttonReset = new usu.widget.ButtonGlass();
        buttonCancel = new usu.widget.ButtonGlass();
        buttonSubmit = new usu.widget.ButtonGlass();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        textSearch = new usu.perpustakaan.buku.widget.template.TextBoxGlass();

        tableAuthor.setAutoCreateRowSorter(true);
        tableAuthor.setForeground(new java.awt.Color(255, 255, 255));
        tableAuthor.setOpaque(false);
        tableAuthor.setSelectionBackground(new java.awt.Color(51, 255, 52));
        tableAuthor.setSelectionForeground(new java.awt.Color(0, 0, 0));

        tablePublisher.setAutoCreateRowSorter(true);
        tablePublisher.setForeground(new java.awt.Color(255, 255, 255));
        tablePublisher.setOpaque(false);
        tablePublisher.setSelectionBackground(new java.awt.Color(51, 255, 52));
        tablePublisher.setSelectionForeground(new java.awt.Color(0, 0, 0));

        tableCategory.setAutoCreateRowSorter(true);
        tableCategory.setForeground(new java.awt.Color(255, 255, 255));
        tableCategory.setOpaque(false);
        tableCategory.setSelectionBackground(new java.awt.Color(51, 255, 52));
        tableCategory.setSelectionForeground(new java.awt.Color(0, 0, 0));

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        setMinimumSize(new java.awt.Dimension(483, 448));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ubah Buku");

        jLabel2.setDisplayedMnemonic('I');
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Id");

        jLabel3.setDisplayedMnemonic('J');
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Judul");

        textTitle.setForeground(new java.awt.Color(255, 255, 255));
        textTitle.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textTitle.setFocusAccelerator('J');
        textTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTitleActionPerformed(evt);
            }
        });
        textTitle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textTitleFocusGained(evt);
            }
        });

        textId.setForeground(new java.awt.Color(255, 255, 255));
        textId.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textId.setFocusAccelerator('I');
        textId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIdActionPerformed(evt);
            }
        });
        textId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textIdFocusGained(evt);
            }
        });

        jLabel4.setDisplayedMnemonic('P');
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pengarang");

        jLabel5.setDisplayedMnemonic('E');
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Penerbit");

        jLabel6.setDisplayedMnemonic('B');
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jenis Buku");

        textCategory.setForeground(new java.awt.Color(255, 255, 255));
        textCategory.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textCategory.setFocusAccelerator('B');
        textCategory.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCategoryActionPerformed(evt);
            }
        });
        textCategory.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textCategoryFocusGained(evt);
            }
        });

        textPublisher.setForeground(new java.awt.Color(255, 255, 255));
        textPublisher.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textPublisher.setFocusAccelerator('E');
        textPublisher.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textPublisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPublisherActionPerformed(evt);
            }
        });
        textPublisher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textPublisherFocusGained(evt);
            }
        });

        textAuthor.setForeground(new java.awt.Color(255, 255, 255));
        textAuthor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textAuthor.setFocusAccelerator('P');
        textAuthor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAuthorActionPerformed(evt);
            }
        });
        textAuthor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textAuthorFocusGained(evt);
            }
        });

        buttonReset.setMnemonic('R');
        buttonReset.setText("Reset");
        buttonReset.setRoundRect(true);
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        buttonCancel.setMnemonic('A');
        buttonCancel.setText("Batal");
        buttonCancel.setRoundRect(true);

        buttonSubmit.setMnemonic('U');
        buttonSubmit.setText("Ubah");
        buttonSubmit.setRoundRect(true);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setOpaque(false);
        jScrollPane1.setViewport(viewPort);

        jLabel7.setDisplayedMnemonic('C');
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cari (case sensitive) ");

        textSearch.setForeground(new java.awt.Color(255, 255, 255));
        textSearch.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textSearch.setFocusAccelerator('C');
        textSearch.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addComponent(textCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addComponent(textPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addComponent(textAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textId, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initFinals() {
        tableAuthor.setModel(modelAuthor);
        tableCategory.setModel(modelKindBook);
        tablePublisher.setModel(modelPublisher);
        for (int i = 0; i < 2; i++) {
            tableAuthor.getColumnModel().getColumn(i).setCellRenderer(new ManageOperator.RenderTable());
            tableCategory.getColumnModel().getColumn(i).setCellRenderer(new ManageOperator.RenderTable());
            tablePublisher.getColumnModel().getColumn(i).setCellRenderer(new ManageOperator.RenderTable());
        }

        textAuthor.setDocument(new DefaultDocument(10) {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        textCategory.setDocument(new DefaultDocument(10) {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        textId.setDocument(new DefaultDocument(10) {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        textPublisher.setDocument(new DefaultDocument(10) {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        textTitle.setDocument(new DefaultDocument(64) {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
    }

    private void initVariables() {
        modelAuthor = new TableModelAuthor();
        modelKindBook = new TableModelKindBook();
        modelPublisher = new TableModelPublisher();
        executor = Executors.newCachedThreadPool();
    }

    private void loadAuthor() {
        try {
            new LoadAuthor().execute();
        } catch (SQLException ex) {
            WidgetUtilities.showErrorMessage(this, ex.getMessage());
        }
    }

    private void loadKindBook() {
        try {
            new LoadKindBook().execute();
        } catch (SQLException ex) {
            WidgetUtilities.showErrorMessage(this, ex.getMessage());
        }
    }

    /**
     * 
     */
    public void reset() {
        textAuthor.setText(book.getAuthor());
        textCategory.setText(book.getKind());
        textId.setText(book.getId());
        textPublisher.setText(book.getPublisher());
        textTitle.setText(book.getTitle());
        viewPort.setView(null);
    }

    private void loadPublisher() {
        try {
            new LoadPublisher().execute();
        } catch (SQLException ex) {
            WidgetUtilities.showErrorMessage(this, ex.getMessage());
        }
    }

private void textIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIdActionPerformed
    textId.transferFocus();
}//GEN-LAST:event_textIdActionPerformed

private void textTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTitleActionPerformed
    textTitle.transferFocus();
}//GEN-LAST:event_textTitleActionPerformed

private void textAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAuthorActionPerformed
    textAuthor.transferFocus();
}//GEN-LAST:event_textAuthorActionPerformed

private void textPublisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPublisherActionPerformed
    textPublisher.transferFocus();
}//GEN-LAST:event_textPublisherActionPerformed

private void textCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCategoryActionPerformed
    textCategory.transferFocus();
}//GEN-LAST:event_textCategoryActionPerformed

private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
    reset();
}//GEN-LAST:event_buttonResetActionPerformed

private void textCategoryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textCategoryFocusGained
    textSearch.setText("");
    viewPort.setView(tableCategory);
    loadKindBook();
}//GEN-LAST:event_textCategoryFocusGained

private void textPublisherFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textPublisherFocusGained
    textSearch.setText("");
    viewPort.setView(tablePublisher);
    loadPublisher();
}//GEN-LAST:event_textPublisherFocusGained

private void textAuthorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textAuthorFocusGained
    textSearch.setText("");
    viewPort.setView(tableAuthor);
    loadAuthor();
}//GEN-LAST:event_textAuthorFocusGained

private void textIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textIdFocusGained
    textSearch.setText("");
    viewPort.setView(null);
}//GEN-LAST:event_textIdFocusGained

private void textTitleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textTitleFocusGained
    textSearch.setText("");
    viewPort.setView(null);
}//GEN-LAST:event_textTitleFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.widget.ButtonGlass buttonCancel;
    usu.widget.ButtonGlass buttonReset;
    usu.widget.ButtonGlass buttonSubmit;
    javax.swing.JTable tableAuthor;
    javax.swing.JTable tableCategory;
    javax.swing.JTable tablePublisher;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textAuthor;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textCategory;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textId;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textPublisher;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textSearch;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textTitle;
    usu.perpustakaan.buku.widget.template.ViewPortGlass viewPort;
    // End of variables declaration//GEN-END:variables

    /**
     * load author
     */
    public class LoadAuthor extends SwingWorker<DefaultAuthor[], String> {

        private ResultSet result;
        private ArrayList<DefaultAuthor> array;

        /**
         * create new load author
         * @throws java.sql.SQLException 
         */
        public LoadAuthor() throws SQLException {
            super();
            result = BookLibrarySQL.getResultAuthor(connection);
            array = new ArrayList<DefaultAuthor>();

            modelAuthor.removeAllElements();
        }

        @Override
        protected DefaultAuthor[] doInBackground() throws Exception {
            while (result.next()) {
                DefaultAuthor author = new DefaultAuthor();
                author.setId(result.getString(FieldConstan.TABEL_PENGARANG.ID_PENGARANG));
                author.setName(result.getString(FieldConstan.TABEL_PENGARANG.NAMA_PENGARANG));
                array.add(author);
                publish(result.getString(FieldConstan.TABEL_PENGARANG.NAMA_PENGARANG));
            }
            return array.toArray(new DefaultAuthor[array.size()]);
        }

        @Override
        protected void done() {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        modelAuthor.add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EditBook.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(EditBook.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            array = null;
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(EditBook.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }
    }

    /**
     * load publisher
     */
    public class LoadPublisher extends SwingWorker<DefaultPublisher[], String> {

        private ResultSet result;
        private ArrayList<DefaultPublisher> array;

        /**
         * 
         * @throws java.sql.SQLException
         */
        public LoadPublisher() throws SQLException {
            super();
            result = BookLibrarySQL.getResultPublisher(connection);
            array = new ArrayList<DefaultPublisher>();

            modelPublisher.removeAllElements();
        }

        @Override
        protected DefaultPublisher[] doInBackground() throws Exception {
            while (result.next()) {
                DefaultPublisher p = new DefaultPublisher();
                p.setId(result.getString(FieldConstan.TABEL_PENERBIT.ID_PENERBIT));
                p.setName(result.getString(FieldConstan.TABEL_PENERBIT.NAMA_PENERBIT));
                array.add(p);
                publish(result.getString(FieldConstan.TABEL_PENERBIT.NAMA_PENERBIT));
            }
            return array.toArray(new DefaultPublisher[array.size()]);
        }

        @Override
        protected void done() {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        modelPublisher.add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EditBook.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(EditBook.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            array = null;
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(EditBook.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }
    }

    /**
     * load kind book
     */
    public class LoadKindBook extends SwingWorker<DefaultKindBook[], String> {

        private ResultSet result;
        private ArrayList<DefaultKindBook> array;

        /**
         * 
         * @throws java.sql.SQLException
         */
        public LoadKindBook() throws SQLException {
            super();
            result = BookLibrarySQL.getResultKindBook(connection);
            array = new ArrayList<DefaultKindBook>();

            modelKindBook.removeAllElements();
        }

        @Override
        protected DefaultKindBook[] doInBackground() throws Exception {
            while (result.next()) {
                DefaultKindBook kind = new DefaultKindBook();
                kind.setId(result.getString(FieldConstan.TABEL_JENIS_BUKU.ID_JENIS));
                kind.setKind(result.getString(FieldConstan.TABEL_JENIS_BUKU.JENIS_BUKU));
                array.add(kind);
            }
            return array.toArray(new DefaultKindBook[array.size()]);
        }

        @Override
        protected void done() {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        modelKindBook.add(get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EditBook.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(EditBook.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            array = null;
                            result.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(EditBook.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }
    }
}
