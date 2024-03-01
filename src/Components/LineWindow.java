/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Components;

import Charts.Line.ModelChart;
import java.util.ArrayList;

/**
 *
 * @author mesoi
 */
public class LineWindow extends javax.swing.JPanel {

    /**
     * Creates new form LineWindow
     */
    public LineWindow() {
        initComponents();
    }

    public void createLineData(String title, String bottom, String right, ArrayList<Double> ejey, ArrayList<String> ejex) {
        lineChart.setTitle(title);
        lineChart.addLegend(bottom, new java.awt.Color(245, 189, 135), new java.awt.Color(255, 255, 255));
        lineChart.addLegendLeft(right, new java.awt.Color(245, 189, 135),new java.awt.Color(245, 189, 135));
        for (int i = 0; i < ejey.size(); i++) {
            lineChart.addData(new ModelChart(ejex.get(i), new double[]{ejey.get(i)}));
        }
        lineChart.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lineChart = new Charts.Line.LineChart();

        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());
        add(lineChart, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Charts.Line.LineChart lineChart;
    // End of variables declaration//GEN-END:variables
}
