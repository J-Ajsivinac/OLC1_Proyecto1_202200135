/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;

import Components.BarWindow;
import Components.LineWindow;
//import Components.LineWindow;
import Components.PieWindow;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Function;
import javax.swing.JFrame;

/**
 *
 * @author mesoi
 */
public class Charts extends javax.swing.JFrame {
    
    private CardLayout cl;
    
    public Charts() {
        initComponents();
        FlatSVGIcon svgLeft = new FlatSVGIcon("img/left-2.svg", 15, 15);
        FlatSVGIcon svgRight = new FlatSVGIcon("img/right.svg", 15, 15);
        FlatSVGIcon svgClose = new FlatSVGIcon("img/close.svg", 24, 24);
        
        FlatSVGIcon.ColorFilter whiteF = new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return new Color(238, 238, 241);
            }
            
        });
        
        FlatSVGIcon.ColorFilter redF = new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return new Color(235, 113, 105);
            }
            
        });
        svgLeft.setColorFilter(whiteF);
        svgRight.setColorFilter(whiteF);
        svgClose.setColorFilter(redF);
        
        btnLeft.setIcon(svgLeft);
        btnRight.setIcon(svgRight);
        btnClose.setIcon(svgClose);
        cl = new CardLayout();
        viewCharts.setLayout(cl);
    }
    
    public void addPieChart(ArrayList<Double> values, ArrayList<String> labels, String title) {
        PieWindow p = new PieWindow();
        p.createPieData(values, labels, title);
        viewCharts.add(p);
    }
    
    public void addBarChart(String title, String bottom, String right, ArrayList<Double> values, ArrayList<String> labels) {
        BarWindow b = new BarWindow();
        b.createBarData(title, bottom, right, values, labels);
        viewCharts.add(b);
    }
    
    public void addLineChart(String title, String bottom, String right, ArrayList<Double> values, ArrayList<String> labels) {
        LineWindow l = new LineWindow();
        l.createLineData(title, bottom, right, values, labels);
        viewCharts.add(l);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        viewCharts = new Components.PanelRound();
        bar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLeft = new javax.swing.JLabel();
        btnClose = new javax.swing.JLabel();
        btnRight = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(5, 5, 5));

        viewCharts.setBackground(new java.awt.Color(19, 20, 23));
        viewCharts.setRoundBottomLeft(10);
        viewCharts.setRoundBottomRight(10);
        viewCharts.setRoundTopLeft(10);
        viewCharts.setRoundTopRight(10);
        java.awt.GridBagLayout viewChartsLayout = new java.awt.GridBagLayout();
        viewChartsLayout.columnWidths = new int[] {10};
        viewChartsLayout.rowHeights = new int[] {10};
        viewChartsLayout.columnWeights = new double[] {10.0};
        viewChartsLayout.rowWeights = new double[] {10.0};
        viewCharts.setLayout(viewChartsLayout);

        bar.setBackground(new java.awt.Color(34, 34, 35));
        bar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barMouseDragged(evt);
            }
        });
        bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Visualizador de Gráficas");

        btnLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLeft.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLeftMouseClicked(evt);
            }
        });

        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        btnRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRight.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRightMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout barLayout = new javax.swing.GroupLayout(bar);
        bar.setLayout(barLayout);
        barLayout.setHorizontalGroup(
            barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 508, Short.MAX_VALUE)
                .addComponent(btnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRight, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        barLayout.setVerticalGroup(
            barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewCharts, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewCharts, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRightMouseClicked
        // TODO add your handling code here:
        cl.previous(viewCharts);
    }//GEN-LAST:event_btnRightMouseClicked

    private void btnLeftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLeftMouseClicked
        // TODO add your handling code here:
        cl.next(viewCharts);
    }//GEN-LAST:event_btnLeftMouseClicked
    
    int xy, xx;
    private void barMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_barMouseDragged

    private void barMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barMousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_barMousePressed

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Charts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Charts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Charts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Charts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Charts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bar;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnLeft;
    private javax.swing.JLabel btnRight;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private Components.PanelRound viewCharts;
    // End of variables declaration//GEN-END:variables
}
