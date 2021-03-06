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
package usu.perpustakaan.buku.widget;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import usu.perpustakaan.buku.widget.template.PanelBlackGreen;

/**
 *
 * @author  usu
 */
public class About extends PanelBlackGreen {

    private static final long serialVersionUID = -1;
    private Desktop desktop;

    /** Creates new form BeanForm */
    public About() {
        initComponents();
    }

    /**
     * 
     * @param l
     */
    public void removeActionListener(ActionListener l) {
        buttonClose.removeActionListener(l);
    }

    /**
     * 
     * @param l
     */
    public void addActionListener(ActionListener l) {
        buttonClose.addActionListener(l);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usu.widget.Label label1 = new usu.widget.Label();
        buttonClose = new usu.widget.ButtonGlass();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel labelCopyright = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        setMinimumSize(new java.awt.Dimension(268, 342));

        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setIconReflec(new javax.swing.ImageIcon(getClass().getResource("/usu/perpustakaan/buku/resource/MyHead.png"))); // NOI18N

        buttonClose.setMnemonic('C');
        buttonClose.setText("Close");
        buttonClose.setFocusPainted(false);
        buttonClose.setRoundRect(true);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("e'BookLibrary 1.0 Beta");

        labelCopyright.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCopyright.setText("<html><center> Copyright 2008 <u>echo.khannedy@gmail.com</u><br> All Rights Reserved</center> </html>");
        labelCopyright.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCopyrightMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCopyrightMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCopyrightMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCopyright, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCopyright)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void labelCopyrightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCopyrightMouseClicked
// TODO add your handling code here:
    if (Desktop.isDesktopSupported()) {
        try {
            desktop = Desktop.getDesktop();
            desktop.mail(new URI("mailto:echo.khannedy@gmail.com"));
        } catch (IOException ex) {
        } catch (URISyntaxException ex) {
        }
    }
}//GEN-LAST:event_labelCopyrightMouseClicked

private void labelCopyrightMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCopyrightMouseEntered
// TODO add your handling code here:
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
}//GEN-LAST:event_labelCopyrightMouseEntered

private void labelCopyrightMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCopyrightMouseExited
// TODO add your handling code here:
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
}//GEN-LAST:event_labelCopyrightMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.widget.ButtonGlass buttonClose;
    // End of variables declaration//GEN-END:variables
}
