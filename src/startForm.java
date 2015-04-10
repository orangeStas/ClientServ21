

import java.awt.*;

public class startForm extends javax.swing.JFrame {

    public static startForm form;
    public static boolean isPanelAdded = false;

    public startForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        rbgChooseGame = new javax.swing.ButtonGroup();
        buttonPanel = new javax.swing.JPanel();
        buttonCreateGame = new javax.swing.JRadioButton();
        buttonJoinGame = new javax.swing.JRadioButton();
        buttonChooseGame = new javax.swing.JButton();
        panelTypeIP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        paneIPString = new javax.swing.JTextPane();
        buttonConnect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("21Game");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        rbgChooseGame.add(buttonCreateGame);
        buttonCreateGame.setText("Create Game");
        buttonCreateGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateGameActionPerformed(evt);
            }
        });

        rbgChooseGame.add(buttonJoinGame);
        buttonJoinGame.setText("Join Game");

        buttonChooseGame.setText("OK");
        buttonChooseGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChooseGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
                buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonCreateGame)
                                        .addComponent(buttonJoinGame))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonChooseGame)
                                .addContainerGap())
        );
        buttonPanelLayout.setVerticalGroup(
                buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(buttonCreateGame)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(buttonJoinGame))
                                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buttonChooseGame)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTypeIP.setBorder(javax.swing.BorderFactory.createTitledBorder("Type server IP"));

        jScrollPane1.setViewportView(paneIPString);

        buttonConnect.setText("Connect");
        buttonConnect.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        buttonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTypeIPLayout = new javax.swing.GroupLayout(panelTypeIP);
        panelTypeIP.setLayout(panelTypeIPLayout);
        panelTypeIPLayout.setHorizontalGroup(
                panelTypeIPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTypeIPLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
                        .addGroup(panelTypeIPLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonConnect))
        );
        panelTypeIPLayout.setVerticalGroup(
                panelTypeIPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTypeIPLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonConnect))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelTypeIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelTypeIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void buttonChooseGameActionPerformed(java.awt.event.ActionEvent evt) {
        if (buttonCreateGame.isSelected())
        {
            Thread thread = new Thread(new Dealer());
            thread.start();
            form.dispose();
        }
        else if (buttonJoinGame.isSelected() && !isPanelAdded){
            form.setSize(new Dimension(form.getSize().width, form.getSize().height + 120));
            panelTypeIP.setVisible(true);
            isPanelAdded = true;
        }
    }

    private void buttonCreateGameActionPerformed(java.awt.event.ActionEvent evt) {
        if (isPanelAdded)
        {
            form.panelTypeIP.setVisible(false);
            form.setSize(new Dimension(form.getSize().width, form.getSize().height-120));
            isPanelAdded = false;
        }
    }

    private void buttonConnectActionPerformed(java.awt.event.ActionEvent evt) {
        Thread thread = new Thread(new Player(paneIPString.getText()));
        thread.start();
        form.dispose();
    }

    public static String getIP(){
        return form.paneIPString.getText();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(startForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(startForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(startForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(startForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                form = new startForm();
                form.setVisible(true);
                form.panelTypeIP.setVisible(false);
                form.setSize(new Dimension(form.getSize().width, form.getSize().height-120));
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton buttonChooseGame;
    private javax.swing.JButton buttonConnect;
    private javax.swing.JRadioButton buttonCreateGame;
    private javax.swing.JRadioButton buttonJoinGame;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane paneIPString;
    private javax.swing.JPanel panelTypeIP;
    private javax.swing.ButtonGroup rbgChooseGame;
    // End of variables declaration
}
