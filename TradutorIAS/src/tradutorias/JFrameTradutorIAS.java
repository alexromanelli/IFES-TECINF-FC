/*
 * Se for usar este código, cite o autor.
 */
package tradutorias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Alexandre Romanelli <alexandre.romanelli@ifes.edu.br>
 */
public class JFrameTradutorIAS extends javax.swing.JFrame {

    /**
     * Creates new form JFrameTradutorIAS
     */
    public JFrameTradutorIAS() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButtonAbrir = new javax.swing.JButton();
        jButtonSalvarCodigoFonte = new javax.swing.JButton();
        jButtonTraduzir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaCodigoFonte = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButtonSalvarCodigoIasModificado = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaCodigoIasModificado = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButtonSalvarCodigoIasOriginal = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaCodigoIasOriginal = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButtonSalvarCodigoIasHexadecimal = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaCodigoIasHexadecimal = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tradutor de linguagem de alto nível para IAS");

        jButtonAbrir.setText("Abrir...");
        jButtonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirActionPerformed(evt);
            }
        });

        jButtonSalvarCodigoFonte.setText("Salvar...");
        jButtonSalvarCodigoFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarCodigoFonteActionPerformed(evt);
            }
        });

        jButtonTraduzir.setText("Traduzir");
        jButtonTraduzir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTraduzirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSalvarCodigoFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButtonTraduzir, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(443, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAbrir)
                    .addComponent(jButtonSalvarCodigoFonte)
                    .addComponent(jButtonTraduzir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextAreaCodigoFonte.setColumns(20);
        jTextAreaCodigoFonte.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        jTextAreaCodigoFonte.setRows(5);
        jScrollPane1.setViewportView(jTextAreaCodigoFonte);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Código-fonte", jPanel2);

        jButtonSalvarCodigoIasModificado.setText("Salvar...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSalvarCodigoIasModificado, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSalvarCodigoIasModificado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextAreaCodigoIasModificado.setEditable(false);
        jTextAreaCodigoIasModificado.setColumns(20);
        jTextAreaCodigoIasModificado.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        jTextAreaCodigoIasModificado.setRows(5);
        jScrollPane2.setViewportView(jTextAreaCodigoIasModificado);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Código IAS - modificado", jPanel3);

        jButtonSalvarCodigoIasOriginal.setText("Salvar...");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSalvarCodigoIasOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSalvarCodigoIasOriginal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextAreaCodigoIasOriginal.setEditable(false);
        jTextAreaCodigoIasOriginal.setColumns(20);
        jTextAreaCodigoIasOriginal.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        jTextAreaCodigoIasOriginal.setRows(5);
        jScrollPane3.setViewportView(jTextAreaCodigoIasOriginal);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Código IAS - original", jPanel4);

        jButtonSalvarCodigoIasHexadecimal.setText("Salvar...");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSalvarCodigoIasHexadecimal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSalvarCodigoIasHexadecimal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextAreaCodigoIasHexadecimal.setEditable(false);
        jTextAreaCodigoIasHexadecimal.setColumns(20);
        jTextAreaCodigoIasHexadecimal.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        jTextAreaCodigoIasHexadecimal.setRows(5);
        jScrollPane4.setViewportView(jTextAreaCodigoIasHexadecimal);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Código IAS - linguagem de máquina", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirActionPerformed
        JFileChooser jFileChooser = new JFileChooser();
        File diretorioAtual = new File(System.getProperty("user.dir"));
        jFileChooser.setCurrentDirectory(diretorioAtual);
        
        int resultado = jFileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            BufferedReader input = null;
            try {
                File f = jFileChooser.getSelectedFile();
                input = new BufferedReader(new FileReader(f));
                
                jTextAreaCodigoFonte.setText("");
                String linha;
                boolean novaLinha = false;
                while ((linha = input.readLine()) != null) {
                    if (novaLinha) {
                        jTextAreaCodigoFonte.append("\n");
                    }
                    jTextAreaCodigoFonte.append(linha);
                    novaLinha = true;
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro: arquivo não encontrado ou não pode ser aberto.", "ERRO", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro de leitura do arquivo.", "ERRO", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    input.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo.", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButtonAbrirActionPerformed

    private void jButtonSalvarCodigoFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarCodigoFonteActionPerformed
        JFileChooser jFileChooser = new JFileChooser();
        File diretorioAtual = new File(System.getProperty("user.dir"));
        jFileChooser.setCurrentDirectory(diretorioAtual);

        int resultado = jFileChooser.showSaveDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            BufferedWriter output = null;
            try {
                File f = jFileChooser.getSelectedFile();
                output = new BufferedWriter(new FileWriter(f));
                
                String codigoFonte = jTextAreaCodigoFonte.getText();
                output.write(codigoFonte);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro de escrita do arquivo.", "ERRO", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    output.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo.", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButtonSalvarCodigoFonteActionPerformed

    private void jButtonTraduzirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTraduzirActionPerformed
        ArrayList<ArrayList<String>> codigos = TradutorIAS.traduzirCodigoFonte();
        
        jTextAreaCodigoIasModificado.setText("");
        boolean novaLinha = false;
        for (String s : codigos.get(0)) {
            if (novaLinha) {
                jTextAreaCodigoIasModificado.append("\n");
            }
            jTextAreaCodigoIasModificado.append(s);
            novaLinha = true;
        }
        jTextAreaCodigoIasModificado.getCaret().setDot(0);
        
        jTextAreaCodigoIasOriginal.setText("");
        novaLinha = false;
        for (String s : codigos.get(1)) {
            if (novaLinha) {
                jTextAreaCodigoIasOriginal.append("\n");
            }
            jTextAreaCodigoIasOriginal.append(s);
            novaLinha = true;
        }
        jTextAreaCodigoIasOriginal.getCaret().setDot(0);
        
        jTextAreaCodigoIasHexadecimal.setText("");
        novaLinha = false;
        for (String s : codigos.get(2)) {
            if (novaLinha) {
                jTextAreaCodigoIasHexadecimal.append("\n");
            }
            jTextAreaCodigoIasHexadecimal.append(s);
            novaLinha = true;
        }
        jTextAreaCodigoIasHexadecimal.getCaret().setDot(0);
        
        JOptionPane.showMessageDialog(null, "Tradução concluída!", "Processamento", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonTraduzirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbrir;
    private javax.swing.JButton jButtonSalvarCodigoFonte;
    private javax.swing.JButton jButtonSalvarCodigoIasHexadecimal;
    private javax.swing.JButton jButtonSalvarCodigoIasModificado;
    private javax.swing.JButton jButtonSalvarCodigoIasOriginal;
    private javax.swing.JButton jButtonTraduzir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaCodigoFonte;
    private javax.swing.JTextArea jTextAreaCodigoIasHexadecimal;
    private javax.swing.JTextArea jTextAreaCodigoIasModificado;
    private javax.swing.JTextArea jTextAreaCodigoIasOriginal;
    // End of variables declaration//GEN-END:variables

    JTextArea getTextAreaCodigoFonte() {
        return jTextAreaCodigoFonte;
    }
}
