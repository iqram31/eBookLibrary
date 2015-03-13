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
package usu.perpustakaan.buku.widget.template;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import usu.perpustakaan.buku.util.StringUtil;
import usu.widget.text.DefaultDocument;

/**
 *
 * @author usu
 */
public class PasswordBox extends JPasswordField {

    private static final long serialVersionUID = -1;

    /**
     * 
     */
    public PasswordBox() {
        super();
        setDocument(new DefaultDocument() {

            private static final long serialVersionUID = -1;

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containSemiColomn(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        setOpaque(false);
        setBorder(new EmptyBorder(5, 10, 5, 10));
        setHorizontalAlignment(CENTER);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isEnabled()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            super.paintComponent(g);

            Color dark = new Color(1F, 1F, 1F, 0F);
            Color light = new Color(1F, 1F, 1F, 0.3F);
            GradientPaint paint = new GradientPaint(0, 0, light, 0, getHeight() / 2, dark);
            g2.setPaint(paint);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());

            g2.dispose();
        } else {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            super.paintComponent(g);

            Color light = new Color(1F, 1F, 1F, 0.3F);
            g2.setColor(light);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());

            g2.dispose();
        }
    }
}
