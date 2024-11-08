/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slr1.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;

/**
 *
 * @author Usama Sarwar
 */
class LabelBorder implements Border {

    Color color, color2;

    public LabelBorder(Color color, Color color2) {
        this.color = color;
        this.color2 = color2;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        g.drawRect(x + 1, y + 1, width - 6, height - 6);
        g.setColor(color2);
        g.fillRect(x + width - 4, y + 4, 4, height - 4);
        g.fillRect(x + 4, y + height - 4, width - 4, 4);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(3, 3, 6, 6);
    }

    public boolean isBorderOpaque() {
        return true;
    }
}
