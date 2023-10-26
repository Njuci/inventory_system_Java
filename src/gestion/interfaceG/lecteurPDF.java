/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.interfaceG;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.adobe.acrobat.Viewer;
import javax.swing.ImageIcon;
/*
 * www.codeurjava.com
 */
public class lecteurPDF extends JPanel{

 private Viewer viewer;
 
 public lecteurPDF(String nomfichier) throws Exception{
 this.setLayout(new BorderLayout());
  ImageIcon icon = new ImageIcon("photos/logo.jpg");
     
 //créer le viewer qui va servir à afficher le contenu du pdf
 viewer = new Viewer();
 this.add(viewer, BorderLayout.CENTER);
 FileInputStream fis = new FileInputStream(nomfichier);
 viewer.setDocumentInputStream(fis);
 viewer.activate();
 
 }
}