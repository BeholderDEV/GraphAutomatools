/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;
import com.alee.laf.WebLookAndFeel;
import core.algoritmos.BreadthFirstSearch;
import core.algoritmos.DeepFirstSearch;
import core.algoritmos.SearchAlgorithm;
import core.model.Aresta;
import core.model.Grafo;
import core.model.Vertice;
import core.util.VerticeUtils;
import core.web.XMLReader;
import java.awt.Image;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UnsupportedLookAndFeelException;
import ui.graph.GraphDrawer;
import ui.utils.ColorController;
import ui.webLaf.WeblafUtils;

/**
 *
 * @author lite
 */
public class MainWindow extends javax.swing.JFrame {
    private Grafo grafo;
    GraphDrawer drawer;
    /**
     * Creates new form MainWIndow
     */
    public MainWindow() {
        initComponents();
        configura();
    }
    
    private void configura(){
        WeblafUtils.instalaWeblaf();
        WeblafUtils.configuraWebLaf(jTextField1);
        WeblafUtils.configuraWebLaf(jTextField2);
        WeblafUtils.configurarBotao(webButton1);
        WeblafUtils.configurarBotao(webButton2);
        WeblafUtils.configurarBotao(webButton3);
        jPanel1.setBackground(ColorController.COR_PRINCIPAL);
        jLabel1.setForeground(ColorController.COR_LETRA);
        jLabel2.setForeground(ColorController.COR_LETRA);
    }
    private void drawGraph(){
        jPanel4.removeAll();
        Image image = drawer.drawGraph();
        WebImage webImage = new WebImage(image);
        webImage.setDisplayType(DisplayType.fitComponent);
        jPanel4.add(webImage);
//        jPanel4.invalidate();
        jPanel4.revalidate();
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
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        webButton3 = new com.alee.laf.button.WebButton();
        webButton2 = new com.alee.laf.button.WebButton();
        webButton1 = new com.alee.laf.button.WebButton();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(73, 40));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 25, 0));

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel1.setText("Vertice Inicial");
        jPanel5.add(jLabel1, java.awt.BorderLayout.WEST);

        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.BorderLayout(10, 0));

        jTextField2.setText("8");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField2, java.awt.BorderLayout.CENTER);

        jLabel2.setText("Vertice Procurado");
        jPanel6.add(jLabel2, java.awt.BorderLayout.WEST);

        jPanel2.add(jPanel6);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        webButton3.setText("Breadth First");
        webButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(webButton3);

        webButton2.setText("Deep First");
        webButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(webButton2);

        webButton1.setText("Carregar");
        webButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(webButton1);

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void webButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1ActionPerformed
        grafo = XMLReader.grafoFromXML();
        drawer = new GraphDrawer(grafo);
        drawGraph();
    }//GEN-LAST:event_webButton1ActionPerformed

    private void webButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton2ActionPerformed
        if(grafo!=null){
            grafo.resetVisitedsandHinteds();
            SearchAlgorithm sa = new DeepFirstSearch();
            Vertice vertice = sa.search(grafo, Integer.parseInt(jTextField1.getText()), Integer.parseInt(jTextField2.getText()));
            Aresta aresta;
            while(vertice.getAnterior()!=null)
            {
                aresta = grafo.getAresta(vertice.getAnterior().getId() ,vertice.getId());
                aresta.setHinted(true);
                vertice = vertice.getAnterior();
            }
            drawGraph();
            
//            jTextArea1.setText(VerticeUtils.getPath(vertice));
        }
        else{
//            jTextArea1.setText("Carregue um Grafo");
        }
    }//GEN-LAST:event_webButton2ActionPerformed

    private void webButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton3ActionPerformed
        if(grafo!=null){
            grafo.resetVisitedsandHinteds();
            SearchAlgorithm sa = new BreadthFirstSearch();
            Vertice vertice = sa.search(grafo, Integer.parseInt(jTextField1.getText()), Integer.parseInt(jTextField2.getText()));
            Aresta aresta;
            while(vertice.getAnterior()!=null)
            {
                aresta = grafo.getAresta(vertice.getAnterior().getId() ,vertice.getId());
                aresta.setHinted(true);
                vertice = vertice.getAnterior();
            }
            drawGraph();
//            jTextArea1.setText(VerticeUtils.getPath(vertice));
        }
        else{
//            jTextArea1.setText("Carregue um Grafo");
        }
    }//GEN-LAST:event_webButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        try {
            javax.swing.UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private com.alee.laf.button.WebButton webButton1;
    private com.alee.laf.button.WebButton webButton2;
    private com.alee.laf.button.WebButton webButton3;
    // End of variables declaration//GEN-END:variables
}
