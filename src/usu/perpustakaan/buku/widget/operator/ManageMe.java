/*
 * ManageMe.java
 *
 * Created on 28 Maret 2008, 15:39
 */
package usu.perpustakaan.buku.widget.operator;

import java.util.Calendar;
import java.util.Date;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import usu.perpustakaan.buku.data.DefaultOperator;
import usu.perpustakaan.buku.data.template.Operator;
import usu.perpustakaan.buku.util.StringUtil;
import usu.widget.ButtonGlass;
import usu.widget.text.DefaultDocument;

/**
 *
 * @author  usu
 */
public class ManageMe extends javax.swing.JPanel {

    private static final long serialVersionUID = -1;
    private Operator operator;
    private String errorMessage;
    private Date date;
    private Calendar cal;
    private Calendar now;

    /** Creates new form ManageMe */
    public ManageMe() {
        initComponents();
        initFinal();
    }

    private void initFinal() {
        textID.setDocument(new DefaultDocument(10) {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        textAddress.setDocument(new DefaultDocument() {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        textContact.setDocument(new DefaultDocument(64) {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        textName.setDocument(new DefaultDocument(64) {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                if (StringUtil.containDigit(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        textPassword.setDocument(new DefaultDocument(64) {

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

    /**
     * 
     * @return
     */
    public Operator getOperator() {
        if (textID.getText().trim().equals("")) {
            errorMessage = "Id operator kosong";
            return null;
        }
        if (StringUtil.getStringFromArrayChar(textPassword.getPassword()).trim().equals("")) {
            errorMessage = "Password operator kosong";
            return null;
        }
        if (textName.getText().trim().equals("")) {
            errorMessage = "Nama operator kosong";
            return null;
        }
        date = (Date) textBorn.getValue();
        cal = Calendar.getInstance();
        cal.setTime(date);
        now = Calendar.getInstance();
        if (cal.get(Calendar.YEAR) > now.get(Calendar.YEAR)) {
            errorMessage = "Tahun lahir operator lebih besar dari sekarang";
            return null;
        } else if (cal.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
            if (cal.get(Calendar.MONTH) > now.get(Calendar.MONTH)) {
                errorMessage = "Bulan lahir operator lebih besar dari sekarang";
                return null;
            } else if (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)) {
                if (cal.get(Calendar.DATE) > now.get(Calendar.DATE)) {
                    errorMessage = "Tanggal lahir operator lebih besar dari sekarang";
                    return null;
                } else if (cal.get(Calendar.DATE) == now.get(Calendar.DATE)) {
                    errorMessage = "Tanggal lahir operator tak boleh sama dengan sekarang";
                    return null;
                }
            }
        }
        if (textContact.getText().trim().equals("")) {
            errorMessage = "Kontak operator kosong";
            return null;
        }
        if (textAddress.getText().trim().equals("")) {
            errorMessage = "Alamat operator kosong";
            return null;
        }
        operator = new DefaultOperator();
        operator.setAddress(textAddress.getText());
        operator.setBorn(new java.sql.Date(cal.getTimeInMillis()));
        operator.setContact(textContact.getText());
        operator.setId(textID.getText());
        operator.setName(textName.getText());
        operator.setPassword(StringUtil.getStringFromArrayChar(textPassword.getPassword()));
        return operator;
    }

    /**
     * 
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @return
     */
    public ButtonGlass getButtonSave() {
        return buttonSave;
    }

    /**
     * 
     * @param operator
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
        reset();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usu.perpustakaan.buku.widget.template.ViewPortGlass viewPortGlass1 = new usu.perpustakaan.buku.widget.template.ViewPortGlass();
        textAddress = new javax.swing.JTextArea();
        usu.perpustakaan.buku.widget.template.PanelTranparan panelTranparan1 = new usu.perpustakaan.buku.widget.template.PanelTranparan();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        textID = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        textName = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        textContact = new usu.perpustakaan.buku.widget.template.TextBoxGlass();
        textBorn = new usu.perpustakaan.buku.widget.template.FormatedTextBox();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        buttonSave = new usu.widget.ButtonGlass();
        buttonReset = new usu.widget.ButtonGlass();
        textPassword = new usu.perpustakaan.buku.widget.template.PasswordBox();

        viewPortGlass1.setView(textAddress);

        textAddress.setColumns(20);
        textAddress.setFont(new java.awt.Font("Tahoma", 1, 11));
        textAddress.setForeground(new java.awt.Color(255, 255, 255));
        textAddress.setRows(5);
        textAddress.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textAddress.setOpaque(false);

        setOpaque(false);

        panelTranparan1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelTranparan1.setOpaqueImage(false);
        panelTranparan1.setRound(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Id");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tangal Lahir");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kontak");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Alamat");

        textID.setForeground(new java.awt.Color(255, 255, 255));
        textID.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textID.setFont(new java.awt.Font("Tahoma", 1, 11));

        textName.setForeground(new java.awt.Color(255, 255, 255));
        textName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textName.setFont(new java.awt.Font("Tahoma", 1, 11));

        textContact.setForeground(new java.awt.Color(255, 255, 255));
        textContact.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textContact.setFont(new java.awt.Font("Tahoma", 1, 11));

        textBorn.setForeground(new java.awt.Color(255, 255, 255));
        textBorn.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textBorn.setFont(new java.awt.Font("Tahoma", 1, 11));
        textBorn.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG))));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setOpaque(false);
        jScrollPane1.setViewport(viewPortGlass1);

        buttonSave.setText("Simpan");
        buttonSave.setRoundRect(true);

        buttonReset.setText("Reset");
        buttonReset.setRoundRect(true);
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        textPassword.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        javax.swing.GroupLayout panelTranparan1Layout = new javax.swing.GroupLayout(panelTranparan1);
        panelTranparan1.setLayout(panelTranparan1Layout);
        panelTranparan1Layout.setHorizontalGroup(
            panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranparan1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranparan1Layout.createSequentialGroup()
                        .addGroup(panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(textID, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(textName, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(textContact, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(textBorn, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(textPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranparan1Layout.createSequentialGroup()
                        .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTranparan1Layout.setVerticalGroup(
            panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranparan1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textBorn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranparan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranparan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
    try {
        reset();
    } catch (NullPointerException e) {
        textAddress.setText("");
        textBorn.setValue(new Date());
        textContact.setText("");
        textID.setText("");
        textName.setText("");
        textPassword.setText("");
    }
}//GEN-LAST:event_buttonResetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.widget.ButtonGlass buttonReset;
    usu.widget.ButtonGlass buttonSave;
    javax.swing.JTextArea textAddress;
    usu.perpustakaan.buku.widget.template.FormatedTextBox textBorn;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textContact;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textID;
    usu.perpustakaan.buku.widget.template.TextBoxGlass textName;
    usu.perpustakaan.buku.widget.template.PasswordBox textPassword;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        textAddress.setText(operator.getAddress());
        textBorn.setValue(operator.getBorn());
        textContact.setText(operator.getContact());
        textID.setText(operator.getId());
        textName.setText(operator.getName());
        textPassword.setText(operator.getPassword());
    }
}
