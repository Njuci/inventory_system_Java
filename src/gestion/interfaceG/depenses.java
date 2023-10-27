/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.interfaceG;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.draw.LineSeparator;
import gestion.stock.ResultSetTableModel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.adobe.acrobat.Viewer;
import java.io.IOException;
import javax.swing.ImageIcon;
/**
 *
 * @author Administrator
 */
public class depenses extends javax.swing.JFrame {

    /**
     * Creates new form Facture
     */
    ResultSet rslt=null;
    BDD base_donne;
    
    public depenses() throws ClassNotFoundException, SQLException {
        initComponents();
        
         ImageIcon icon = new ImageIcon("photos/logo.jpg");
        setIconImage(icon.getImage());
        this.setBounds(0, 0, 1440, 670);
        base_donne=new BDD();
        this.setResizable(false);
       
        date_set();
       combox_remplissage();
       combox_mois_remplissage();
        afficherTable();
        afficherTable_fc();
        
        
    }
    public void total_table(){
        int lastRow = table_depense.getRowCount() - 1;
    int lastColumn = table_depense.getColumnCount() - 1;
    if (table_depense.getValueAt(lastRow, lastColumn)!=null){
    total_lbl.setText( String.valueOf( table_depense.getValueAt(lastRow, lastColumn)));}else{ total_lbl.setText("0");}
    
         lastRow = table_depense_fc.getRowCount() - 1;
    lastColumn = table_depense_fc.getColumnCount() - 1;
    if (table_depense_fc.getValueAt(lastRow, lastColumn)!=null){
    total_lbl_fc.setText( String.valueOf( table_depense_fc.getValueAt(lastRow, lastColumn)));}else{ total_lbl_fc.setText("0");}
    
    
    }
    public void impression(){
        File folder=new File ("infor_gestion/bilan");
        if (!folder.exists()){
        folder.mkdir();
        }
        Date d1=new Date();
        String nom_fichier="/*infor_gestion/bilan/Bilan_du_"+d1.toString()+".pdf";
        Font fontTitre;
        fontTitre = new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD,BaseColor.BLUE);
        
        Font Invoice_font=new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD,BaseColor.BLUE);
        LineSeparator ls =new LineSeparator();
        ls.setLineColor(BaseColor.WHITE);
        
        
        
    
    }
    
    
    
    public void afficherTable() throws ClassNotFoundException, SQLException {
    String[] colonnes = {"id","code_produit","ref","designation","fournisseur","remise","prix_unitaire","stock","date"};
    rslt = base_donne.RecupererDonne("SELECT id, libele,DATE_FORMAT(date, '%Y-%m-%d')As date , montant,\n" +
"       @cumul := @cumul + montant AS cumul\n" +
"FROM depenses, (SELECT @cumul := 0) c\n" +
"WHERE devise='$' ORDER BY date");
  
    
    
    // Créer un modèle de tableau avec le ResultSet
    
    // Définir le modèle de tableau sur le composant table_user
    table_depense.setModel(new ResultSetTableModel(rslt));
    total_table();
      combox_remplissage();}
    
      public void afficherTable_fc() throws ClassNotFoundException, SQLException {
    String[] colonnes = {"id","code_produit","ref","designation","fournisseur","remise","prix_unitaire","stock","date"};
    rslt = base_donne.RecupererDonne("SELECT id, libele,DATE_FORMAT(date, '%Y-%m-%d')As date , montant,\n" +
"       @cumul := @cumul + montant AS cumul\n" +
"FROM depenses, (SELECT @cumul := 0) c\n" +
"WHERE devise='fc' ORDER BY date");
    
    
    // Créer un modèle de tableau avec le ResultSet
    
    // Définir le modèle de tableau sur le composant table_user
    table_depense_fc.setModel(new ResultSetTableModel(rslt));
    total_table();
        combox_remplissage();}
    public void combox_remplissage() throws SQLException, ClassNotFoundException {
        rslt = base_donne.RecupererDonne("SELECT DISTINCT DATE_FORMAT(date, '%Y-%m-%d')As date FROM depenses order by -date");
        date_search_field.removeAllItems();
        while (rslt.next()) {

            date_search_field.addItem(rslt.getString("date"));

        }
        rslt.close();

    }
   public void combox_mois_remplissage() throws SQLException, ClassNotFoundException {
        rslt = base_donne.RecupererDonne("SELECT DISTINCT DATE_FORMAT(date, '%Y-%m') AS mois_annee FROM depenses order by -date");
        moi_field.removeAllItems();
        while (rslt.next()) {
           moi_field.addItem(rslt.getString("mois_annee"));

        }
        rslt.close();

    }
   
public void date_set() {
        Date d = new Date();
        SimpleDateFormat dat1 = new SimpleDateFormat("EEEE-dd-MMM-yyy");
        SimpleDateFormat dat2 = new SimpleDateFormat("HH:mm");
        date_field.setText(dat1.format(d));
        heure_field.setText(dat2.format(d));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        montant_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        libele_field = new javax.swing.JTextField();
        heure_field = new javax.swing.JLabel();
        date_field = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        date_search_field = new javax.swing.JComboBox<>();
        add_btn = new javax.swing.JButton();
        modifier_btn = new javax.swing.JButton();
        supprimer_btn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        devise_field = new javax.swing.JComboBox<>();
        chercher = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        moi_field = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_depense = new javax.swing.JTable();
        total_lbl = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        imprimer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_depense_fc = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        total_lbl_fc = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        voir_les_autres = new javax.swing.JButton();
        retour = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        vendre_e = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        Retour = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(java.awt.Color.orange);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Depenses");

        jLabel2.setText("Libele");

        montant_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montant_fieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Montant");

        libele_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libele_fieldActionPerformed(evt);
            }
        });

        heure_field.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        heure_field.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        date_field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        date_field.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Date");

        date_search_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        add_btn.setText("Ajouter");
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        modifier_btn.setText("Modifier");
        modifier_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifier_btnActionPerformed(evt);
            }
        });

        supprimer_btn.setText("Supprimer");
        supprimer_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimer_btnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Journée du");

        jLabel8.setText("Devise");

        devise_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "$", "fc" }));

        chercher.setText("Chercher");
        chercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chercherActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Par mois");

        moi_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        moi_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moi_fieldActionPerformed(evt);
            }
        });

        jButton1.setText("Rechercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(libele_field, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(montant_field, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(devise_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(modifier_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(supprimer_btn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(moi_field, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(date_search_field, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(240, 240, 240)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(chercher, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(heure_field, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(275, 275, 275))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(date_field, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_field, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(montant_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(devise_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(libele_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(date_search_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chercher)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(heure_field, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(moi_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_btn)
                    .addComponent(modifier_btn)
                    .addComponent(supprimer_btn))
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1440, 251);

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setLayout(null);

        table_depense.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Libele", "Date", "Montant ", "Cumul"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_depense.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_depenseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_depense);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(800, 80, 610, 120);

        total_lbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_lbl.setText("0");
        jPanel2.add(total_lbl);
        total_lbl.setBounds(1160, 230, 270, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Total");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(130, 210, 90, 40);

        imprimer.setText("Imprimer");
        imprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimerActionPerformed(evt);
            }
        });
        jPanel2.add(imprimer);
        imprimer.setBounds(810, 240, 130, 50);

        table_depense_fc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Libele", "Date", "Montant ", "Cumul"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_depense_fc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_depense_fcMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_depense_fc);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 610, 120);

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel9.setText("Depenses en Dollars Américains");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(920, 20, 420, 50);

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel10.setText("Depenses en Francs Congolais");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(60, 20, 370, 50);

        total_lbl_fc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total_lbl_fc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_lbl_fc.setText("0");
        jPanel2.add(total_lbl_fc);
        total_lbl_fc.setBounds(210, 210, 270, 40);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Total");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(1070, 230, 90, 40);

        voir_les_autres.setText("Voir les depenses");
        voir_les_autres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voir_les_autresActionPerformed(evt);
            }
        });
        jPanel2.add(voir_les_autres);
        voir_les_autres.setBounds(410, 240, 160, 50);

        retour.setText("Retour");
        retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retourActionPerformed(evt);
            }
        });
        jPanel2.add(retour);
        retour.setBounds(610, 300, 130, 50);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 240, 1440, 370);

        jMenuBar1.setBackground(java.awt.Color.orange);

        jMenu2.setText("Menu");

        vendre_e.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        vendre_e.setText("Vendre");
        vendre_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendre_eActionPerformed(evt);
            }
        });
        jMenu2.add(vendre_e);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Voir le bilan");
        jMenu2.add(jMenuItem5);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Voir les vendeurs");
        jMenu2.add(jMenuItem4);

        jMenuItem6.setText("Voir");
        jMenu2.add(jMenuItem6);

        Retour.setText("Retour");
        jMenu2.add(Retour);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Quiter");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
        // TODO add your handling code here:
        if (libele_field.getText().equals("")|| montant_field.getText().equals("")){
             JOptionPane.showMessageDialog(this,"veuillez renseigner tous les champs");
        
        }else {
            if(JOptionPane.showConfirmDialog(this,"êtes-vous Sûr d'avoir depenser cet argent","warning",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                try {
                     String[] colonne ={"libele","montant","devise"};
                    String[] donne={libele_field.getText(),montant_field.getText(),devise_field.getSelectedItem().toString()};
                    
                    base_donne.InsererParColns("depenses", colonne, donne);
                    afficherTable();
                    afficherTable_fc();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                
        
        }else{
        return;
        }
        
        
        
        
        
        }
        
        
    }//GEN-LAST:event_add_btnActionPerformed

    String date=null;
    
    
    private void modifier_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifier_btnActionPerformed
        // TODO add your handling code here:
        if (libele_field.getText().equals("")|| montant_field.getText().equals("")){
             JOptionPane.showMessageDialog(this,"veuillez renseigner tous les champs");
               }else{
                if(JOptionPane.showConfirmDialog(this,"êtes-vous Sûr d'avoir depenser cet argent","warning",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    String[] colonne ={"libele","montant","devise"};
                    String[] donne={libele_field.getText(),montant_field.getText(),devise_field.getSelectedItem().toString()};
                    String id;
                    
                    if (table_depense.getValueAt(table_depense.getSelectedRow(),0)==null){
                   id=String.valueOf(table_depense_fc.getValueAt(table_depense_fc.getSelectedRow(),0));
                           }else{id=String.valueOf(table_depense.getValueAt(table_depense.getSelectedRow(),0));}
                    base_donne.updateDonneTable("depenses", colonne,donne,"id='"+id+"'");
                    try {
                        afficherTable();
                        afficherTable_fc();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
             
        
        
        }}
    }//GEN-LAST:event_modifier_btnActionPerformed

    private void table_depenseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_depenseMouseClicked
        // TODO add your handling code here:
        
        libele_field.setText(String.valueOf(table_depense.getValueAt(table_depense.getSelectedRow(),1)));
        montant_field.setText(String.valueOf(table_depense.getValueAt(table_depense.getSelectedRow(),3)));
        date=String.valueOf(table_depense.getValueAt(table_depense.getSelectedRow(),2));
        
    }//GEN-LAST:event_table_depenseMouseClicked

    private void chercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chercherActionPerformed
        try {
            // TODO add your handling code here:
            rslt = base_donne.RecupererDonne("SELECT id, libele,DATE_FORMAT(date, '%Y-%m-%d')As date, montant, @cumul := @cumul + montant AS cumul\n" +
                    "FROM depenses, (SELECT @cumul := 0) c\n" +
                    " WHERE date ='"+date_search_field.getSelectedItem().toString()+"' and devise='$' ORDER BY date");
            
               table_depense.setModel(new ResultSetTableModel(rslt));
               
            rslt = base_donne.RecupererDonne("SELECT id, libele,date, montant,\n" +
                    "       @cumul := @cumul + montant AS cumul\n" +
                    "FROM depenses, (SELECT @cumul := 0) c\n" +
                    " WHERE date LIKE '%"+date_search_field.getSelectedItem().toString()+"%' and devise='fc' ORDER BY date");
            table_depense_fc.setModel(new ResultSetTableModel(rslt));
    total_table();
            
        } catch (SQLException ex) {
            Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    // Créer un modèle de tableau avec le ResultSet
    
    // Définir le modèle de tableau sur le composant table_user]
        
        
    }//GEN-LAST:event_chercherActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            try {
            // TODO add your handling code here:
            rslt = base_donne.RecupererDonne("SELECT id, libele,DATE_FORMAT(date, '%Y-%m-%d')As date , montant,\n" +
                    "       @cumul := @cumul + montant AS cumul\n" +
                    "FROM depenses, (SELECT @cumul := 0) c\n" +
                    " WHERE date LIKE '%"+moi_field.getSelectedItem().toString()+"%'and devise='$' ORDER BY date");
               table_depense.setModel(new ResultSetTableModel(rslt));
               
            rslt = base_donne.RecupererDonne("SELECT id, libele,date, montant,\n" +
                    "       @cumul := @cumul + montant AS cumul\n" +
                    "FROM depenses, (SELECT @cumul := 0) c\n" +
                    " WHERE date LIKE '%"+moi_field.getSelectedItem().toString()+"%' and devise='fc' ORDER BY date");
            table_depense_fc.setModel(new ResultSetTableModel(rslt));
    total_table();
            
        } catch (SQLException ex) {
            Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    // Créer un modèle de tableau avec le ResultSet
    
    // Définir le modèle de tableau
    }//GEN-LAST:event_jButton1ActionPerformed

    private void supprimer_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimer_btnActionPerformed
       String id;
                    
                    if (table_depense.getValueAt(table_depense.getSelectedRow(),0)==null){
                   id=String.valueOf(table_depense_fc.getValueAt(table_depense_fc.getSelectedRow(),0));
                           }else{id=String.valueOf(table_depense.getValueAt(table_depense.getSelectedRow(),0));}
                     if(JOptionPane.showConfirmDialog(this,"êtes-vous Sûr de supprimer","warning",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
            try {
                base_donne.Delete("depenses","id ='"+id+"'");
                
                afficherTable();
                afficherTable_fc();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        
        }else{
        return;
        }
        try {
                
                
                afficherTable();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }//GEN-LAST:event_supprimer_btnActionPerformed

    private void vendre_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendre_eActionPerformed
        try {
            // TODO add your handling code here:
            Cahier e=new Cahier();
        } catch (SQLException ex) {
            Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_vendre_eActionPerformed

    private void moi_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moi_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moi_fieldActionPerformed
 public static void previewPDF(String filename) throws Exception {
         
 lecteurPDF lecteur = new lecteurPDF(filename);
 //créer le JFrame
 JFrame f = new JFrame("Lecteur PDF");
 f.setSize(1024,768);
 f.setLocationRelativeTo(null);
 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 f.setVisible(true);
 f.getContentPane().add(lecteur);
    }
    private void imprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimerActionPerformed
         SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyHHmmss");
                Date date = new Date();
                String nom="pdf/depenses/table_depeses"+s.format(date).toString()+".pdf";
                
        try {                                         
            Document document = new Document();
            
            try {
                System.out.println(nom);
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(nom));
                    
                } catch (DocumentException ex) {
                    Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.open();
            document.addTitle("depense du"+date_field.getText());
            Paragraph paragraph = new Paragraph("Situation des depenses en $ du"+date_field.getText()+"\n"+"\n");
            paragraph.setAlignment(Element.ALIGN_CENTER);
             document.add(paragraph);
             Paragraph espace = new Paragraph("\n");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(espace);
            document.add(espace);
            
            
            PdfPTable pdfTable = new PdfPTable(table_depense.getColumnCount());
            
// Ajouter les en-têtes de colonne
        for (int i = 0; i < table_depense.getColumnCount(); i++) {
            pdfTable.addCell(table_depense.getColumnName(i));
            }

// Ajouter les données de la JTable
        for (int rows = 0; rows < table_depense.getRowCount(); rows++) {
        for (int cols = 0; cols < table_depense.getColumnCount(); cols++) {
        pdfTable.addCell(table_depense.getModel().getValueAt(rows, cols).toString());
        }
        }

        document.add(pdfTable);
        document.add(espace);
        document.add(espace);
        
        

        Paragraph p=new Paragraph("Situation des depenses en fc du"+date_field.getText()+"\n"+"\n");
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        document.add(espace);
        document.add(espace);
        pdfTable = new PdfPTable(table_depense_fc.getColumnCount());
          for (int i = 0; i < table_depense_fc.getColumnCount(); i++) {
            pdfTable.addCell(table_depense_fc.getColumnName(i));
            }

// Ajouter les données de la JTable
        for (int rows = 0; rows < table_depense_fc.getRowCount(); rows++) {
        for (int cols = 0; cols < table_depense_fc.getColumnCount(); cols++) {
        pdfTable.addCell(table_depense_fc.getModel().getValueAt(rows, cols).toString());
        }
        }

        document.add(pdfTable);
        
        


document.close();
            try {
                previewPDF(nom);
            } catch (Exception ex) {
                Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DocumentException ex) {
            Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imprimerActionPerformed

    private void libele_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libele_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_libele_fieldActionPerformed

    private void montant_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montant_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montant_fieldActionPerformed

    private void table_depense_fcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_depense_fcMouseClicked
        // TODO add your handling code here:
        libele_field.setText(String.valueOf(table_depense_fc.getValueAt(table_depense_fc.getSelectedRow(),1)));
        montant_field.setText(String.valueOf(table_depense_fc.getValueAt(table_depense_fc.getSelectedRow(),3)));
        devise_field.setSelectedItem("fc");
        date=String.valueOf(table_depense_fc.getValueAt(table_depense_fc.getSelectedRow(),2));
        
    }//GEN-LAST:event_table_depense_fcMouseClicked

    private void voir_les_autresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voir_les_autresActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        File currentDir = new File(System.getProperty("user.dir"));
File subDir = new File(currentDir, "pdf/depenses");

        chooser.setCurrentDirectory(subDir);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String selectedFile="";
                try {
                    selectedFile = chooser.getSelectedFile().getCanonicalPath();
                } catch (IOException ex) {
                    Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(selectedFile);
                previewPDF(selectedFile);
            } catch (Exception ex) {
                Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_voir_les_autresActionPerformed


}

    private void retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourActionPerformed
        // TODO add your handling code here:
         DIG n= new DIG();n.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_retourActionPerformed




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
            java.util.logging.Logger.getLogger(depenses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(depenses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(depenses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(depenses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new depenses().setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(depenses.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Retour;
    private javax.swing.JButton add_btn;
    private javax.swing.JButton chercher;
    private javax.swing.JLabel date_field;
    private javax.swing.JComboBox<String> date_search_field;
    private javax.swing.JComboBox<String> devise_field;
    private javax.swing.JLabel heure_field;
    private javax.swing.JButton imprimer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField libele_field;
    private javax.swing.JButton modifier_btn;
    private javax.swing.JComboBox<String> moi_field;
    private javax.swing.JTextField montant_field;
    private javax.swing.JButton retour;
    private javax.swing.JButton supprimer_btn;
    private javax.swing.JTable table_depense;
    private javax.swing.JTable table_depense_fc;
    private javax.swing.JLabel total_lbl;
    private javax.swing.JLabel total_lbl_fc;
    private javax.swing.JMenuItem vendre_e;
    private javax.swing.JButton voir_les_autres;
    // End of variables declaration//GEN-END:variables
}
