/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Components;

import Charts.Barc.ModelChart;
import java.util.ArrayList;

/**
 *
 * @author mesoi
 */
public class BarWindow extends javax.swing.JPanel {

    /**
     * Creates new form BarWindow
     */
    public BarWindow() {
        initComponents();
        
    }

    public void createBarData(String title, String bottom, String right,ArrayList<Double> ejey, ArrayList<String> ejex) {
        chart.setTitle(title);
        chart.addLegend(bottom, new java.awt.Color(245, 189, 135));
        chart.addLegendLeft(right, new java.awt.Color(245, 189, 135));

        for (int i = 0; i < ejey.size(); i++) {
            chart.addData(new ModelChart(ejex.get(i), new double[]{ejey.get(i)}));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chart = new Charts.Barc.Chart();

        setOpaque(false);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {10};
        layout.rowHeights = new int[] {10};
        layout.columnWeights = new double[] {10.0};
        layout.rowWeights = new double[] {10.0};
        setLayout(layout);
        add(chart, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Charts.Barc.Chart chart;
    // End of variables declaration//GEN-END:variables
}
