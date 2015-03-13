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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import javax.swing.JViewport;

/**
 *
 * @author usu
 */
public class ViewPortGlass extends JViewport {

    private static final long serialVersionUID = -1;
    private float alpha;

    /**
     * 
     */
    public ViewPortGlass() {
        super();
        setOpaque(false);
        setAlpha(0.2F);
    }

    /**
     * 
     * @return
     */
    public float getAlpha() {
        return alpha;
    }

    /**
     * 
     * @param alpha
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        GeneralPath s = new GeneralPath();
        s.moveTo(0, 0);
        s.lineTo(0, getHeight());
        s.curveTo(0, getHeight(), getWidth() / 5, getHeight() / 5, getWidth(), 0);
        s.closePath();

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.SrcOver.derive(getAlpha()));
        g2.setColor(Color.WHITE);
        g2.fill(s);
    }
}
