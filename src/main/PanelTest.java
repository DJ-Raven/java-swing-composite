package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class PanelTest extends javax.swing.JPanel {

    public void setBackground(boolean background) {
        this.background = background;
        repaint();
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
        repaint();
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint();
    }

    public void setAlpcom(int alpcom) {
        this.alpcom = alpcom;
        repaint();
    }

    private final Rectangle testing1;
    private final Image image;
    private Rectangle selected;
    private Point pointPress;
    private Point selectedLocation;
    private int alpcom = AlphaComposite.SRC_OVER;
    private float alpha = 1f;
    private boolean transparent = false;
    private boolean background = false;

    public PanelTest() {
        initComponents();
        setBackground(new Color(46, 46, 46));
        setOpaque(false);
        image = new ImageIcon(getClass().getResource("/main/image.jpg")).getImage();
        testing1 = new Rectangle(5, 5, 300, 300);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (testing1.contains(me.getPoint())) {
                    selected = testing1;
                }
                pointPress = me.getPoint();
                if (selected != null) {
                    selectedLocation = new Point(selected.x, selected.y);
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                selected = null;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                if (selected != null) {
                    selected.x = selectedLocation.x + me.getPoint().x - pointPress.x;
                    selected.y = selectedLocation.y + me.getPoint().y - pointPress.y;
                    repaint();
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBackground(g2);
        g2.drawImage(createBuffer(), 0, 0, null);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    private BufferedImage createBuffer() {
        int size = 300;
        int width = getWidth();
        int height = getHeight();
        int x = getWidth() / 2 - (size / 2);
        int y = getHeight() / 2 - (size / 2);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(240, 240, 240));
        g2.drawImage(image, x, y, size, size, null);
        g2.setColor(new Color(60, 60, 60));
        g2.drawString("Rectangle 1", x + 5, y + 15);
        //  Composite
        if (alpcom != 0) {
            g2.setComposite(AlphaComposite.getInstance(alpcom, alpha));
        }
        //  Create Sample 1
        GradientPaint gra = new GradientPaint(0, testing1.y, new Color(228, 52, 52), 0, testing1.y + testing1.height, transparent ? new Color(228, 52, 52, 0) : new Color(228, 52, 52));
        g2.setPaint(gra);
        g2.fillRect(testing1.x, testing1.y, testing1.width, testing1.height);
        g2.setComposite(AlphaComposite.SrcOver);
        g2.setColor(Color.WHITE);
        float[] dash = {2f, 0f, 2f};
        BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
        g2.setStroke(stroke);
        g2.drawRect(testing1.x, testing1.y, testing1.width, testing1.height);
        g2.drawString("Rectangle 2", testing1.x + 5, testing1.y + 15);
        g2.dispose();
        return img;
    }

    private void paintBackground(Graphics2D g2) {
        int width = getWidth();
        int height = getHeight();
        int size = 8;
        int x = 0;
        int y = 0;
        boolean row = true;
        boolean column = true;
        while (y < height) {
            while (x < width) {
                if (column) {
                    g2.setColor(getBackground());
                } else {
                    if (background) {
                        g2.setColor(getBackground());
                    } else {
                        g2.setColor(new Color(100, 100, 100));
                    }
                }
                g2.fillRect(x, y, size, size);
                x += size;
                column = !column;
            }
            row = !row;
            column = row;
            x = 0;
            y += size;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
