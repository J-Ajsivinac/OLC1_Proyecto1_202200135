/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Components;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import raven.chart.data.pie.DefaultPieDataset;
import raven.chart.pie.PieChart;

/**
 *
 * @author mesoi
 */
public class PieWindow extends javax.swing.JPanel {

    private PieChart pieChart1;
    
    /**
     * Creates new form PieWindow
     */
    public PieWindow() {
        initComponents();
    }

    public void createPieData(ArrayList<Double> values, ArrayList<String> labels,String title) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        for (int i = 0; i < values.size(); i++) {
            dataset.addValue(labels.get(i), values.get(i));
        }
        
        pieChart1 = new PieChart();
        JLabel header1 = new JLabel(title);
        header1.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart1.setHeader(header1);
        pieChart1.setBackground(new Color(19, 20, 23));
        pieChart1.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieChart1.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,10");
        pieChart1.setDataset(dataset);
        add(pieChart1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {10};
        layout.rowHeights = new int[] {10};
        layout.columnWeights = new double[] {10.0};
        layout.rowWeights = new double[] {10.0};
        setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
