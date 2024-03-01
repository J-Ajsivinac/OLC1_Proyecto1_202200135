package Interface;

import Analyzers.Parser;
import Analyzers.ParserSym;
import Analyzers.Scanner;
import Components.MReport;
import Components.glasspanel.DefaultOption;
import Components.glasspanel.GlassPanePopup;
import Components.tabs.ButtonTabComponent;
import TableSymb.TableSymb;
import Tools.CreateReports;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

public class Principal extends javax.swing.JFrame {

    private JFileChooser fileChooser;
    private JTextPane textPane;
    private ArrayList<String> rutes = new ArrayList<>();

    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        GlassPanePopup.install(this);
        FlatSVGIcon SVGFile = new FlatSVGIcon("img/table2.svg", 24, 24);
        FlatSVGIcon SVGHome = new FlatSVGIcon("img/HomeF.svg", 22, 22);
        FlatSVGIcon SVGDown = new FlatSVGIcon("img/d.svg", 15, 15);
        FlatSVGIcon SVGPlay = new FlatSVGIcon("img/play2.svg", 24, 24);
        FlatSVGIcon SVGAdd = new FlatSVGIcon("img/add1.svg", 24, 24);
        FlatSVGIcon SVGOpen = new FlatSVGIcon("img/open.svg", 24, 24);
        FlatSVGIcon SVGSave = new FlatSVGIcon("img/save.svg", 24, 24);

        FlatSVGIcon.ColorFilter whiteF = new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return new Color(255, 255, 255);
            }

        });

        FlatSVGIcon.ColorFilter greenF = new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return new Color(7, 139, 55);
            }

        });
        SVGFile.setColorFilter(whiteF);
        SVGHome.setColorFilter(whiteF);
        SVGDown.setColorFilter(whiteF);
        SVGPlay.setColorFilter(greenF);
        SVGAdd.setColorFilter(whiteF);
        SVGOpen.setColorFilter(whiteF);
        SVGSave.setColorFilter(whiteF);

        lblReport.setIcon(SVGFile);
        lblDown2.setIcon(SVGDown);
        btnPlay.setIcon(SVGPlay);
        btnNew.setIcon(SVGAdd);
        btnOpen.setIcon(SVGOpen);
        btnSave.setIcon(SVGSave);

        fileChooser = new JFileChooser();

    }

    public void pressReport() {
        GlassPanePopup.showPopup(new MReport(), new DefaultOption() {
            @Override
            public float opacity() {
                return 0;
            }

            @Override
            public String getLayout(Component parent, float animate) {
                return "pos 0.98al " + 0.15f + "al";
            }
        });
    }

    private JTextPane getTextPaneAt(int index) {
        JScrollPane scrollPane = (JScrollPane) tabbedPane.getComponentAt(index);
        return (JTextPane) scrollPane.getViewport().getView();
    }

    public void print() {
        try {
            int selectedIndex = tabbedPane.getSelectedIndex();
            JTextPane textPaneTemp = getTextPaneAt(selectedIndex);
            String text = textPaneTemp.getText();
            Scanner scan = new Scanner(new StringReader(text));
            Parser sintax = new Parser(scan);
            sintax.parse();
            sintax.getInstructions();
            Symbol token = null;
            CreateReports.saveReportTok(CreateReports.TokenReport(text, token, "name.xd"), "name.xd");
//            Symbol symbol = scan.next_token();
//            while (symbol.sym != ParserSym.EOF) {
//                System.out.println("Token: " + symbol.sym + ", Value: " + symbol.value+" col= "+symbol.left +" fila= "+symbol.toString());
//                symbol = scan.next_token();
//            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void saveCurrentTabContentAs() {
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex != -1) {
            JTextPane textPane = getTextPaneAt(selectedIndex);
            String content = textPane.getText();

            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showSaveDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    FileWriter fileWriter = new FileWriter(selectedFile);
                    fileWriter.write(content);
                    fileWriter.close();
                    tabbedPane.setTitleAt(selectedIndex, selectedFile.getName()); // Update tab title
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void saveCurrentTabContent() {
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex != -1 && tabbedPane.getTabCount() > 0 && rutes.size() > 0) {
//            System.out.println(tabbedPane.getTitleAt(selectedIndex));
            String filename = rutes.get(selectedIndex);
            JTextPane textPane = (JTextPane) (((JScrollPane) tabbedPane.getComponentAt(selectedIndex)).getViewport()).getComponent(0);
            if (filename.contains("\\") || filename.contains("/")) {
                File f = new File(filename);
                if (f.exists()) {
                    try {
                        DataOutputStream d = new DataOutputStream(new FileOutputStream(filename));
                        String line = textPane.getText();
                        d.writeBytes(line);
                        d.close();

                    } catch (Exception ex) {
                        System.out.println("File not found");
                    }
                    textPane.requestFocus();
                }
            }

        } else {
            saveCurrentTabContentAs();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnPlay = new Components.ButtonRound();
        btnReport = new Components.PanelRound();
        lblHome1 = new javax.swing.JLabel();
        lblDown2 = new javax.swing.JLabel();
        lblReport = new javax.swing.JLabel();
        panelCode = new Components.PanelRound();
        tabbedPane = new javax.swing.JTabbedPane();
        panelRound2 = new Components.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        paneConsole = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        btnNew = new Components.ButtonRound();
        btnOpen = new Components.ButtonRound();
        btnSave = new Components.ButtonRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DataForge");

        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 670));

        btnPlay.setBackground(new java.awt.Color(68, 174, 110));
        btnPlay.setBorder(null);
        btnPlay.setBorderColor(new java.awt.Color(68, 174, 110));
        btnPlay.setColor(new java.awt.Color(68, 174, 110));
        btnPlay.setColorClick(new java.awt.Color(60, 158, 99));
        btnPlay.setColorOver(new java.awt.Color(60, 146, 94));
        btnPlay.setRadius(10);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnReport.setBackground(new java.awt.Color(51, 51, 51));
        btnReport.setRoundBottomLeft(10);
        btnReport.setRoundBottomRight(10);
        btnReport.setRoundTopLeft(10);
        btnReport.setRoundTopRight(10);
        btnReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReportMousePressed(evt);
            }
        });

        lblHome1.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblHome1.setText("Reportes");

        javax.swing.GroupLayout btnReportLayout = new javax.swing.GroupLayout(btnReport);
        btnReport.setLayout(btnReportLayout);
        btnReportLayout.setHorizontalGroup(
            btnReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnReportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblReport, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHome1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDown2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        btnReportLayout.setVerticalGroup(
            btnReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnReportLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(btnReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReport, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btnReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDown2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblHome1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        panelCode.setBackground(new java.awt.Color(51, 51, 51));
        panelCode.setRoundBottomLeft(10);
        panelCode.setRoundBottomRight(10);
        panelCode.setRoundTopLeft(10);
        panelCode.setRoundTopRight(10);

        tabbedPane.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelCodeLayout = new javax.swing.GroupLayout(panelCode);
        panelCode.setLayout(panelCodeLayout);
        panelCodeLayout.setHorizontalGroup(
            panelCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelCodeLayout.setVerticalGroup(
            panelCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(51, 51, 51));
        panelRound2.setRoundBottomLeft(10);
        panelRound2.setRoundBottomRight(10);
        panelRound2.setRoundTopLeft(10);
        panelRound2.setRoundTopRight(10);

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel2.setText("Consola");

        jScrollPane1.setViewportView(paneConsole);

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Montserrat", 2, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Joab Ajsivinac - 202200135");

        btnNew.setBackground(new java.awt.Color(51, 51, 51));
        btnNew.setBorder(null);
        btnNew.setBorderColor(new java.awt.Color(51, 51, 51));
        btnNew.setColor(new java.awt.Color(51, 51, 51));
        btnNew.setColorClick(new java.awt.Color(102, 102, 102));
        btnNew.setColorOver(new java.awt.Color(102, 102, 102));
        btnNew.setRadius(10);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnOpen.setBackground(new java.awt.Color(51, 51, 51));
        btnOpen.setBorder(null);
        btnOpen.setBorderColor(new java.awt.Color(51, 51, 51));
        btnOpen.setColor(new java.awt.Color(51, 51, 51));
        btnOpen.setColorClick(new java.awt.Color(102, 102, 102));
        btnOpen.setColorOver(new java.awt.Color(102, 102, 102));
        btnOpen.setRadius(10);
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(51, 51, 51));
        btnSave.setBorder(null);
        btnSave.setBorderColor(new java.awt.Color(51, 51, 51));
        btnSave.setColor(new java.awt.Color(51, 51, 51));
        btnSave.setColorClick(new java.awt.Color(102, 102, 102));
        btnSave.setColorOver(new java.awt.Color(102, 102, 102));
        btnSave.setRadius(10);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(327, 327, 327)
                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(panelCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportMouseClicked
        // TODO add your handling code here:
        pressReport();
    }//GEN-LAST:event_btnReportMouseClicked

    private void btnReportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportMousePressed
        // TODO add your handling code here:
        pressReport();
    }//GEN-LAST:event_btnReportMousePressed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            textPane = new JTextPane();
            JScrollPane scrollPane = new JScrollPane(textPane);

            // Crear un ButtonTabComponent para la nueva pestaña
            ButtonTabComponent tabComponent = new ButtonTabComponent(tabbedPane);

            // Agregar la pestaña con el componente personalizado
            tabbedPane.addTab(selectedFile.getName(), scrollPane);
            int index = tabbedPane.indexOfComponent(scrollPane);
            tabbedPane.setTabComponentAt(index, tabComponent);
            rutes.add(index, selectedFile.getAbsolutePath());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                StyledDocument doc = textPane.getStyledDocument();
                String line;
                while ((line = reader.readLine()) != null) {
                    doc.insertString(doc.getLength(), line + "\n", null);
                }
                reader.close();

                // Apply syntax highlighting based on file extension
                String fileName = selectedFile.getName();

            } catch (IOException | BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        saveCurrentTabContent();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        int tabSize = 16; // Tamaño en píxeles

        // Crear una instancia de TabStop con el tamaño deseado
        TabStop tabStop = new TabStop(tabSize);

        // Crear una instancia de TabSet con la TabStop personalizada
        TabSet tabSet = new TabSet(new TabStop[]{tabStop});

        // Obtener el StyledDocument del JTextPane
        StyledDocument doc = textPane.getStyledDocument();

        // Obtener el atributo de tabulación del documento
        AttributeSet attr = SimpleAttributeSet.EMPTY;
        MutableAttributeSet paraAttributes = new SimpleAttributeSet(attr);
        StyleConstants.setTabSet(paraAttributes, tabSet);

        // Aplicar el nuevo atributo de tabulación al documento
        doc.setParagraphAttributes(0, doc.getLength(), paraAttributes, false);
        String untitledFileName = "Untitled";
        ButtonTabComponent tabComponent = new ButtonTabComponent(tabbedPane);
        tabbedPane.addTab(untitledFileName, scrollPane);
        int index = tabbedPane.indexOfComponent(scrollPane);
        tabbedPane.setTabComponentAt(index, tabComponent);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // TODO add your handling code here:
        print();
    }//GEN-LAST:event_btnPlayActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.ButtonRound btnNew;
    private Components.ButtonRound btnOpen;
    private Components.ButtonRound btnPlay;
    private Components.PanelRound btnReport;
    private Components.ButtonRound btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDown2;
    private javax.swing.JLabel lblHome1;
    private javax.swing.JLabel lblReport;
    public static javax.swing.JTextPane paneConsole;
    private Components.PanelRound panelCode;
    private Components.PanelRound panelRound2;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

}
