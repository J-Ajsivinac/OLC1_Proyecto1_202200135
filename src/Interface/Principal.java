package Interface;

import Analyzers.Parser;
import Analyzers.Scanner;
import Analyzers.token;
import Components.JavaSyntaxHighlighter;
import Components.MReport;
import Components.glasspanel.DefaultOption;
import Components.glasspanel.GlassPanePopup;
import Components.tabs.ButtonTabComponent;
import Errores.Errores;
import TableSymb.TableSymb;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class Principal extends javax.swing.JFrame {

    private JFileChooser fileChooser;
    private JTextPane textPane;
    private ArrayList<String> rutes = new ArrayList<>();
    public static ArrayList<Errores> errorS = new ArrayList<>();
    public static ArrayList<Errores> errorL = new ArrayList<>();
    public static ArrayList<token> lexemas = new ArrayList<token>();
    public static TableSymb tableS;
    final StyleContext cont = StyleContext.getDefaultStyleContext();
    final AttributeSet keyw1 = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,
            new Color(63, 162, 239));// 63, 162, 239
    final AttributeSet keyw2 = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,
            new Color(179, 146, 226));
    final AttributeSet keyw3 = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,
            new Color(255, 170, 110));
    final AttributeSet keyw4 = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,
            new Color(136, 222, 100));// new Color(0, 92, 95)
    final AttributeSet keyw5 = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,
            new Color(107, 115, 124));// new Color(0, 92, 95)
    final AttributeSet keynumber = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,
            new Color(119, 252, 255));// new Color(0, 92, 95)
    final AttributeSet attrWhite = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.WHITE);

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
                return new Color(238, 238, 241);
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos DataForge (*.df)", "df");
        fileChooser.setFileFilter(filter);
        // paneConsole.setContentType("text/plain; charset=UTF-8");
        paneConsole.setContentType("text/html;charset=UTF-8");
        paneConsole.setEditorKit(new HTMLEditorKit());
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
        // System.out.println(scrollPane);
        return (JTextPane) scrollPane.getViewport().getView();
    }

    public void analyze() {

        try {
            int selectedIndex = tabbedPane.getSelectedIndex();
            JTextPane textPaneTemp = getTextPaneAt(selectedIndex);
            String text = textPaneTemp.getText();
            Scanner scan = new Scanner(new StringReader(text));
            // System.out.println(text);
            Parser sintax = new Parser(scan);
            sintax.parse();
            lexemas = scan.getLexemas();
            errorS = sintax.getErroresSintacticos();
            errorL = scan.getErroresL();

            if (!rutes.isEmpty()) {
                if (selectedIndex >= 0 && selectedIndex < rutes.size()) {
                    String filename = rutes.get(selectedIndex);
                    paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(),
                            "EJECUTANDO : " + filename + "\n\n", null);
                } else {
                    paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(),
                            "EJECUTANDO : " + "Untitled" + "\n\n", null);
                }
            } else {
                paneConsole.getDocument().insertString(Principal.paneConsole.getDocument().getLength(),
                        "EJECUTANDO : " + "Untitled" + "\n\n", null);
            }

            sintax.getInstructions();
            tableS = sintax.getTable();

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
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Data Forge", "*.df");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showSaveDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                if (!selectedFile.getName().toLowerCase().endsWith(".df")) {
                    selectedFile = new File(selectedFile.getParentFile(), selectedFile.getName() + ".df");
                }

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
            // System.out.println(tabbedPane.getTitleAt(selectedIndex));
            String filename = rutes.get(selectedIndex);

            if (filename == null) {
                saveCurrentTabContentAs();
                return;
            }

            JTextPane textPane = (JTextPane) (((JScrollPane) tabbedPane.getComponentAt(selectedIndex)).getViewport())
                    .getComponent(0);
            if (filename.contains("\\") || filename.contains("/")) {
                File f = new File(filename);
                if (f.exists()) {
                    try {
                        DataOutputStream d = new DataOutputStream(new FileOutputStream(filename));
                        String line = textPane.getText();
                        d.writeBytes(line);
                        d.close();
                        if (selectedIndex >= rutes.size()) {
                            for (int i = rutes.size(); i < selectedIndex; i++) {
                                rutes.add(null);
                            }
                            rutes.add(selectedIndex, f.getAbsolutePath());
                        } else {
                            rutes.add(selectedIndex, f.getAbsolutePath());
                        }

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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
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

        jPanel1.setBackground(new java.awt.Color(5, 5, 5));
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

        btnReport.setBackground(new java.awt.Color(38, 40, 44));
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

        panelCode.setBackground(new java.awt.Color(19, 20, 23));
        panelCode.setRoundBottomLeft(10);
        panelCode.setRoundBottomRight(10);
        panelCode.setRoundTopLeft(10);
        panelCode.setRoundTopRight(10);

        tabbedPane.setBackground(new java.awt.Color(19, 20, 23));
        tabbedPane.setFont(new java.awt.Font("Cascadia Code PL", 0, 12)); // NOI18N

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

        panelRound2.setBackground(new java.awt.Color(19, 20, 23));
        panelRound2.setRoundBottomLeft(10);
        panelRound2.setRoundBottomRight(10);
        panelRound2.setRoundTopLeft(10);
        panelRound2.setRoundTopRight(10);

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel2.setText("Consola");

        jScrollPane1.setBorder(null);

        paneConsole.setEditable(false);
        paneConsole.setBackground(new java.awt.Color(19, 20, 23));
        paneConsole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 20, 23)));
        paneConsole.setFont(new java.awt.Font("Cascadia Code PL", 0, 12)); // NOI18N
        paneConsole.setForeground(new java.awt.Color(153, 161, 184));
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

        btnNew.setBackground(new java.awt.Color(38, 40, 44));
        btnNew.setBorder(null);
        btnNew.setBorderColor(new java.awt.Color(38, 40, 44));
        btnNew.setColor(new java.awt.Color(38, 40, 44));
        btnNew.setColorClick(new java.awt.Color(45, 47, 52));
        btnNew.setColorOver(new java.awt.Color(102, 102, 102));
        btnNew.setRadius(10);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnOpen.setBackground(new java.awt.Color(38, 40, 44));
        btnOpen.setBorder(null);
        btnOpen.setBorderColor(new java.awt.Color(38, 40, 44));
        btnOpen.setColor(new java.awt.Color(38, 40, 44));
        btnOpen.setColorClick(new java.awt.Color(45, 47, 52));
        btnOpen.setColorOver(new java.awt.Color(102, 102, 102));
        btnOpen.setRadius(10);
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(38, 40, 44));
        btnSave.setBorder(null);
        btnSave.setBorderColor(new java.awt.Color(38, 40, 44));
        btnSave.setColor(new java.awt.Color(38, 40, 44));
        btnSave.setColorClick(new java.awt.Color(45, 47, 52));
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

    private void btnReportMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnReportMouseClicked
        // TODO add your handling code here:
        pressReport();
    }// GEN-LAST:event_btnReportMouseClicked

    private void btnReportMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnReportMousePressed
        // TODO add your handling code here:
        pressReport();
    }// GEN-LAST:event_btnReportMousePressed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            textPane = new JTextPane();
            textPane.setBackground(new Color(19, 20, 23));
            textPane.setForeground(new Color(181, 191, 218));
            textPane.setFont(new java.awt.Font("Cascadia Code PL", 0, 14));
            textPane.setDocument(new DefaultStyledDocument() {
                @Override
                public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                    super.insertString(offset, str, a);

                    String text = getText(0, getLength());

                    // Patrones para cada tipo de palabra clave
                    String[] patterns = {
                            "[!]([^\\r\\n]*)?",
                            "[<][!][^!]*[!]+([^/*][^*]*[*]+)*[>]",
                            "\\b[0-9]+(\\.[0-9]+)?\\b",
                            "\\b(?i)(var|arr|=|<|>|-|graphbar|graphline|histogram|graphpie)\\b",
                            "\\b(?i)(double|char|column|print|exec|program|end|console)\\b",
                            "\\b(?i)(titulo|titulox|tituloy|ejex|ejey|label|values|sum|mul|res|div|mod|media|mediana|moda|varianza|max|min)\\b",
                            "(?i)[\\\"][^\\\"\\n]+[\\\"]",
                            "//[^\\r\\n]*" // Patrón para comentarios
                    };

                    // Atributos para cada tipo de palabra clave
                    AttributeSet[] attributes = { keyw5, keyw5, keynumber, keyw1, keyw2, keyw3, keyw4 };

                    // Resetea los atributos a blanco
                    setCharacterAttributes(0, text.length(), attrWhite, false);

                    // Aplica los atributos correspondientes a cada patrón
                    for (int i = 0; i < patterns.length; i++) {
                        Matcher matcher = Pattern.compile(patterns[i]).matcher(text);
                        while (matcher.find()) {
                            setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), attributes[i],
                                    false);
                        }
                    }
                }

                @Override
                public void remove(int offs, int len) throws BadLocationException {
                    super.remove(offs, len);
                    String text = getText(0, getLength());

                    // Patrones para cada tipo de palabra clave
                    String[] patterns = {
                            "[!]([^\\r\\n]*)?",
                            "[<][!][^!]*[!]+([^/*][^*]*[*]+)*[>]",
                            "\\b[0-9]+(\\.[0-9]+)?\\b",
                            "\\b(?i)(var|arr|=|<|>|-|graphbar|graphline|histogram|graphpie)\\b",
                            "\\b(?i)(double|char|column|print|exec|program|end|console)\\b",
                            "\\b(?i)(titulo|titulox|tituloy|ejex|ejey|label|values|sum|mul|res|div|mod|media|mediana|moda|varianza|max|min)\\b",
                            "(?i)[\\\"][^\\\"\\n]+[\\\"]",
                            "//[^\\r\\n]*" // Patrón para comentarios
                    };

                    // Atributos para cada tipo de palabra clave
                    AttributeSet[] attributes = { keyw5, keyw5, keynumber, keyw1, keyw2, keyw3, keyw4 };

                    // Resetea los atributos a blanco
                    setCharacterAttributes(0, text.length(), attrWhite, false);

                    // Aplica los atributos correspondientes a cada patrón
                    for (int i = 0; i < patterns.length; i++) {
                        Matcher matcher = Pattern.compile(patterns[i]).matcher(text);
                        while (matcher.find()) {
                            setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), attributes[i],
                                    false);
                        }
                    }
                }
            });
            JScrollPane scrollPane = new JScrollPane(textPane);

            // Crear un ButtonTabComponent para la nueva pestaña
            ButtonTabComponent tabComponent = new ButtonTabComponent(tabbedPane);

            // Agregar la pestaña con el componente personalizado
            tabbedPane.addTab(selectedFile.getName(), scrollPane);
            int index = tabbedPane.indexOfComponent(scrollPane);
            tabbedPane.setTabComponentAt(index, tabComponent);

            if (index >= rutes.size()) {
                for (int i = rutes.size(); i < index; i++) {
                    rutes.add(null);
                }
                rutes.add(index, selectedFile.getAbsolutePath());
            } else {
                rutes.add(index, selectedFile.getAbsolutePath());
            }
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
                // JavaSyntaxHighlighter.highlight(textPane, doc);

            } catch (IOException | BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }// GEN-LAST:event_btnOpenActionPerformed

    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        saveCurrentTabContent();
    }// GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        textPane = new JTextPane();
        textPane.setBackground(new Color(19, 20, 23));
        textPane.setForeground(new Color(181, 191, 218));
        textPane.setFont(new java.awt.Font("Cascadia Code PL", 0, 14));
        JScrollPane scrollPane = new JScrollPane(textPane);
        int tabSize = 16;

        textPane.setContentType("text/plain; charset=UTF-8");
        textPane.setDocument(new DefaultStyledDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());

                // Patrones para cada tipo de palabra clave
                String[] patterns = {
                        "[!]([^\\r\\n]*)?",
                        "[<][!][^!]*[!]+([^/*][^*]*[*]+)*[>]",
                        "\\b[0-9]+(\\.[0-9]+)?\\b",
                        "\\b(?i)(var|arr|=|<|>|-|graphbar|graphline|histogram|graphpie)\\b",
                        "\\b(?i)(double|char|column|print|exec|program|end|console)\\b",
                        "\\b(?i)(titulo|titulox|tituloy|ejex|ejey|label|values|sum|mul|res|div|mod|media|mediana|moda|varianza|max|min)\\b",
                        "(?i)[\\\"][^\\\"\\n]+[\\\"]",
                        "//[^\\r\\n]*" // Patrón para comentarios
                };

                // Atributos para cada tipo de palabra clave
                AttributeSet[] attributes = { keyw5, keyw5, keynumber, keyw1, keyw2, keyw3, keyw4 };

                // Resetea los atributos a blanco
                setCharacterAttributes(0, text.length(), attrWhite, false);

                // Aplica los atributos correspondientes a cada patrón
                for (int i = 0; i < patterns.length; i++) {
                    Matcher matcher = Pattern.compile(patterns[i]).matcher(text);
                    while (matcher.find()) {
                        setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), attributes[i], false);
                    }
                }
            }

            @Override
            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);
                String text = getText(0, getLength());

                // Patrones para cada tipo de palabra clave
                String[] patterns = {
                        "[!]([^\\r\\n]*)?",
                        "[<][!][^!]*[!]+([^/*][^*]*[*]+)*[>]",
                        "\\b[0-9]+(\\.[0-9]+)?\\b",
                        "\\b(?i)(var|arr|=|<|>|-|graphbar|graphline|histogram|graphpie)\\b",
                        "\\b(?i)(double|char|column|print|exec|program|end|console)\\b",
                        "\\b(?i)(titulo|titulox|tituloy|ejex|ejey|label|values|sum|mul|res|div|mod|media|mediana|moda|varianza|max|min)\\b",
                        "(?i)[\\\"][^\\\"\\n]+[\\\"]",
                        "//[^\\r\\n]*" // Patrón para comentarios
                };

                // Atributos para cada tipo de palabra clave
                AttributeSet[] attributes = { keyw5, keyw5, keynumber, keyw1, keyw2, keyw3, keyw4 };

                // Resetea los atributos a blanco
                setCharacterAttributes(0, text.length(), attrWhite, false);

                // Aplica los atributos correspondientes a cada patrón
                for (int i = 0; i < patterns.length; i++) {
                    Matcher matcher = Pattern.compile(patterns[i]).matcher(text);
                    while (matcher.find()) {
                        setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), attributes[i], false);
                    }
                }
            }
        });

        // Aplicar el nuevo atributo de tabulación al documento
        // themeCustom.setParagraphAttributes(0, themeCustom.getLength(),
        // paraAttributes, false);
        String untitledFileName = "Untitled";
        ButtonTabComponent tabComponent = new ButtonTabComponent(tabbedPane);
        tabbedPane.addTab(untitledFileName, scrollPane);
        int index = tabbedPane.indexOfComponent(scrollPane);
        tabbedPane.setTabComponentAt(index, tabComponent);
    }// GEN-LAST:event_btnNewActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPlayActionPerformed
        // TODO add your handling code here:
        analyze();
    }// GEN-LAST:event_btnPlayActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
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
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static Components.ButtonRound btnNew;
    public static Components.ButtonRound btnOpen;
    public static Components.ButtonRound btnPlay;
    public static Components.PanelRound btnReport;
    public static Components.ButtonRound btnSave;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblDown2;
    public static javax.swing.JLabel lblHome1;
    public static javax.swing.JLabel lblReport;
    public static javax.swing.JTextPane paneConsole;
    public static Components.PanelRound panelCode;
    public static Components.PanelRound panelRound2;
    public static javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

}
