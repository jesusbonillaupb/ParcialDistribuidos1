/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package NFS.UI.vistasUsuario;

import NFS.Client;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class SubirArchivo extends javax.swing.JFrame {
    String rutaCarpeta;
    int id;
    String nombreUsuario;
    String rol;
    
    /**
     * Creates new form SubirArchivo
     */
    public SubirArchivo(String rutaCarpeta, int id,String nombreUsuario, String rol) {
        initComponents();
        this.rutaCarpeta=rutaCarpeta;
        this.id=id;
        this.nombreUsuario=nombreUsuario;
        this.rol=rol;
        lblRuta.setText(rutaCarpeta);
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblRuta = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbVisibilidad = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        btnSubir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Subir archivo");

        lblRuta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblRuta.setText("Ruta");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Subir en:");

        cmbVisibilidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Publico", "Privado" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Visibilidad");

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnSubir.setText("Elegir archivo");
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbVisibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(btnSubir)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnVolver)))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRuta)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbVisibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnSubir)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        InicioUsuario inicioUsuario = new InicioUsuario(id, nombreUsuario, rol, rutaCarpeta);
        this.setVisible(false);
        inicioUsuario.listarArchivos();
        inicioUsuario.setLocationRelativeTo(null); 
        inicioUsuario  .setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
       String visibilidad =cmbVisibilidad.getSelectedItem().toString();
        SubirArchivo(this.rutaCarpeta,this.nombreUsuario,visibilidad);
       
    }//GEN-LAST:event_btnSubirActionPerformed

    private  void SubirArchivo(String rutaCarpeta,String nombreUsuario,String visibilidad) {
        
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            byte[] fileData = new byte[(int) file.length()];            
             try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(fileData);
            } catch (IOException e) {
                e.printStackTrace();
                return ;
            }
             
            Client client = new Client("localhost", "1099", "FileService");
            boolean resultado= client.uploadFlie(fileData, "Archivo", file.getName(),nombreUsuario, rutaCarpeta+"\\"+file.getName(),rutaCarpeta, visibilidad);
            if(resultado){
                System.out.println("Archivo subido exitosamente"); 
                InicioUsuario inicioUsuario = new InicioUsuario(id, nombreUsuario, rol, rutaCarpeta);
                this.setVisible(false);
                inicioUsuario.listarArchivos();
                inicioUsuario.setLocationRelativeTo(null); 
                inicioUsuario.setVisible(true);
           }else{
                System.out.println("Algo fallo al subir el archivo"); 
            }
        }  
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbVisibilidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblRuta;
    // End of variables declaration//GEN-END:variables
}
