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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import usu.util.GraphicsUtilities;
import usu.widget.Panel;

/**
 *
 * @author usu
 */
public class PanelImageEfect extends Panel {

    private static final long serialVersionUID = -1;

    public PanelImageEfect() {
        super();
    }

    @Override
    public void setBackgroundImage(Icon backgroundImage) throws IllegalArgumentException {
        if (backgroundImage == null) {
            super.setBackgroundImage(backgroundImage);
        } else {
            ImageIcon icon = (ImageIcon) backgroundImage;
            icon.setImage(GraphicsUtilities.convertToBufferedImageReflection(icon.getImage()));
            super.setBackgroundImage(icon);
        }

    }
}
