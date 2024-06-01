package game.bugtracker.views;

import game.bugtracker.DataBase;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.SQLIntegrityConstraintViolationException;




public class UserSignUp extends javax.swing.JFrame {


 public UserSignUp() {
        initComponents();
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false);

        // Criar uma instância de Combobox
        Combobox<String> accessTypeComboBox = new Combobox<>();

        // Adicionar o JComboBox ao painel de conteúdo da classe UserSignUp
        getContentPane().add(accessTypeComboBox); // ou outro contêiner apropriado
    

        // Adicione um MouseListener à JLabel lblFoto
        lblFoto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Chame o método carregarFoto() quando a JLabel for clicada
                carregarFoto();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Mude o cursor para a mão quando o mouse entrar na JLabel
                lblFoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaure o cursor padrão quando o mouse sair da JLabel
                lblFoto.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

       // Add a FocusListener to text fields
        addFocusListenerToTextField(jTextFieldName, "  Name");
        addFocusListenerToTextField(jTextFieldEmail, "  Email");
        addFocusListenerToTextField(jTextFieldGender, "  Gender");
        addFocusListenerToTextField(jTextFieldNationality, "  Nationality");
        addFocusListenerToTextField(jTextFieldPassword, "  Password");
        addFocusListenerToTextField(jTextFieldNumberPhone, "  Phone Number");
        addFocusListenerToTextField(jTextFieldUser, "  Username");
        addFocusListenerToTextField(jTextFieldAge, "  Age");

        // Configure jTextPaneBio
        jTextPaneBio.setText(" Biography");
        jTextPaneBio.setForeground(new Color(169, 169, 169)); // Almost opaque gray
        jTextPaneBio.setFont(new Font("Arial", Font.ITALIC, 14));

        // Add FocusListener to jTextPaneBio
        jTextPaneBio.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the text when the focus is gained
                if (jTextPaneBio.getText().equals("Biography")) {
                    jTextPaneBio.setText("");
                    jTextPaneBio.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // If the field is empty, display the description again
                if (jTextPaneBio.getText().isEmpty()) {
                    jTextPaneBio.setText("Biography");
                    jTextPaneBio.setForeground(new Color(169, 169, 169));
                }
            }
        });
    }

    private void addFocusListenerToTextField(javax.swing.JTextField textField, String description) {
        textField.setText(description);
        textField.setForeground(new Color(169, 169, 169)); // Almost opaque gray

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the text when the focus is gained
                if (textField.getText().equals(description)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // If the field is empty, display the description again
                if (textField.getText().isEmpty()) {
                    textField.setText(description);
                    textField.setForeground(new Color(169, 169, 169));
                }
            }
        });
    }

    
    
    private void carregarFoto() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Select photo");
        jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens (*.PNG, *.JPG, *.JPEG)", "png", "jpg", "jpeg"));
        int resultado = jfc.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fis = new FileInputStream(jfc.getSelectedFile());

                // Leitura da imagem sem redimensionamento
                ImageIcon icon = new ImageIcon(ImageIO.read(jfc.getSelectedFile()));

                // Ajuste do tamanho da JLabel mantendo a proporção da imagem original
                int larguraDesejada = 170;  // Ajuste conforme necessário
                int alturaDesejada = -1;    // -1 indica que a altura deve ser ajustada proporcionalmente

                if (alturaDesejada == -1) {
                    alturaDesejada = (larguraDesejada * icon.getIconHeight()) / icon.getIconWidth();
                }

                Image foto = icon.getImage().getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);

                lblFoto.setIcon(new ImageIcon(foto));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    
}

 

        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButtonToClean = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneBio = new javax.swing.JTextPane();
        jTextFieldNationality = new javax.swing.JTextField();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldNumberPhone = new javax.swing.JTextField();
        jTextFieldGender = new javax.swing.JTextField();
        jTextFieldUser = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        jTextFieldAge = new javax.swing.JTextField();
        jTextFieldCity = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldPassword = new javax.swing.JTextField();
        combobox2 = new game.bugtracker.views.Combobox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(1085, 864));

        jPanel2.setBackground(new java.awt.Color(239, 198, 192));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 3));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 105, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(183, 183, 183));
        jLabel1.setText("User Sing-Up");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Photo");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Full Name / Company");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("User Name");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Gender");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("E-mail");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Number");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Bio");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Nationality");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("City");

        jButtonToClean.setBackground(new java.awt.Color(247, 218, 38));
        jButtonToClean.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jButtonToClean.setForeground(new java.awt.Color(255, 255, 255));
        jButtonToClean.setText("TO CLEAN");
        jButtonToClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonToCleanActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(84, 195, 67));
        jButton2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/large-orange-square_1f7e7 (1).png"))); // NOI18N
        jButton2.setText("CONFIRM  ");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextPaneBio.setBorder(null);
        jTextPaneBio.setCaretColor(new java.awt.Color(204, 204, 204));
        jTextPaneBio.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(jTextPaneBio);

        jTextFieldNationality.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTextFieldNationality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNationalityActionPerformed(evt);
            }
        });

        jTextFieldName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTextFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNameActionPerformed(evt);
            }
        });

        jTextFieldNumberPhone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextFieldGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextFieldUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextFieldEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/images (1).png"))); // NOI18N
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(232, 232, 232));
        jLabel13.setText("Your Details");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(232, 232, 232));
        jLabel14.setText("Contact");

        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/perfil.jpg"))); // NOI18N

        jTextFieldAge.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldAge.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldAge.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldAge.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextFieldCity.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextFieldCity.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldCity.setText("São Paulo");
        jTextFieldCity.setBorder(null);
        jTextFieldCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCityActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Password");

        jTextFieldPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTextFieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPasswordActionPerformed(evt);
            }
        });

        combobox2.setFocusable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(705, 705, 705)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(combobox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldNationality))
                                .addComponent(jLabel11)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldGender, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(41, 41, 41)
                                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldAge, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel14)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldNumberPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel13)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                                                        .addComponent(jTextFieldUser)))))))
                                .addGap(36, 36, 36)
                                .addComponent(jLabel12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonToClean, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combobox2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(40, 40, 40)
                        .addComponent(jTextFieldAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNationality, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(4, 4, 4)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldGender, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNumberPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonToClean, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextFieldAge.getAccessibleContext().setAccessibleName("Age");
        jTextFieldAge.getAccessibleContext().setAccessibleDescription("Age");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 839, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CustomJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CustomJComboBoxActionPerformed

    private void jTextFieldCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCityActionPerformed

    private void jTextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNameActionPerformed

    }//GEN-LAST:event_jTextFieldNameActionPerformed

    private void jTextFieldNationalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNationalityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNationalityActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Verifique se um item foi selecionado
        Object selectedObject = combobox2.getSelectedItem();

        if (selectedObject != null) {
            // Converta o objeto selecionado para uma String (ou o tipo apropriado)
            String accessType = selectedObject.toString();

            // Verifique se algum campo obrigatório está em branco ou é nulo
            if (accessType.equals("Select User Type")) {
                JOptionPane.showMessageDialog(this, "Please choose a valid user type.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Evita que o código continue e salve no banco de dados
            }

            // Recupere os valores dos outros campos
            String name = jTextFieldName.getText();
            String userName = jTextFieldUser.getText();
            String gender = jTextFieldGender.getText();
            String ageText = jTextFieldAge.getText();
            String email = jTextFieldEmail.getText();
            String password = jTextFieldPassword.getText();
            String phoneNumber = jTextFieldNumberPhone.getText();
            String city = jTextFieldCity.getText();
            String nationality = jTextFieldNationality.getText();
            Date dateJoined = new Date(System.currentTimeMillis());
            String bio = jTextPaneBio.getText();

            // Verifique se algum campo obrigatório está vazio ou contém descrições específicas
            if (name.isEmpty() || userName.isEmpty() || gender.isEmpty() || ageText.isEmpty() ||
                email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() ||
                city.isEmpty() || nationality.isEmpty() || bio.isEmpty() ||
                isDefaultDescription(name, "Name") || isDefaultDescription(userName, "Username") ||
                isDefaultDescription(gender, "Gender") || isDefaultDescription(ageText, "Age") ||
                isDefaultDescription(email, "Email") || isDefaultDescription(password, "Password") ||
                isDefaultDescription(phoneNumber, "Phone Number") ||
                isDefaultDescription(nationality, "Nationality") || isDefaultDescription(bio, "Biography")) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields with valid information.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Evita que o código continue e salve no banco de dados
            }

            // Converta a idade para um número
            int age;
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException e) {
                // Exiba uma mensagem de erro em uma nova janela
                JOptionPane.showMessageDialog(this, "Age must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Evita que o código continue e salve no banco de dados
            }

            // Crie uma instância de sua classe DataBase
            DataBase db = new DataBase();
            db.conectar(); // Chame o método conectar para obter a conexão

            // Crie a instrução SQL de inserção
            String sql = "INSERT INTO user (Name, user_Name, Gender, Age, Email, Password, Phone_Number, City, Nationality, Date_Joined, Bio, Access_Type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection connection = db.getConexao();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                // Passe os valores para os parâmetros da instrução SQL
                statement.setString(1, name);
                statement.setString(2, userName);
                statement.setString(3, gender);
                statement.setInt(4, age);
                statement.setString(5, email);
                statement.setString(6, password);
                statement.setString(7, phoneNumber);
                statement.setString(8, city);
                statement.setString(9, nationality);
                statement.setDate(10, dateJoined);
                statement.setString(11, bio);
                statement.setString(12, accessType);

                // Execute a atualização no banco de dados
                statement.executeUpdate();

                // Exiba uma mensagem de sucesso em uma nova janela
                JOptionPane.showMessageDialog(this, "User successfully created!", "Success", JOptionPane.INFORMATION_MESSAGE);
                NewGame newGame = new NewGame();
                newGame.setVisible(true);
                this.dispose(); // Close the current screen if necessary
            } catch (SQLIntegrityConstraintViolationException e) {
                // Captura a exceção específica para e-mail duplicado
                JOptionPane.showMessageDialog(this, "Email is already in use. Please choose a different email.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                // Lide com outras exceções SQL
                JOptionPane.showMessageDialog(this, "Error inserting data into the database. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // Exiba uma mensagem de erro em uma nova janela
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        }

        // Função para verificar se um campo contém a descrição padrão
        private boolean isDefaultDescription(String fieldValue, String defaultDescription) {
            return fieldValue.trim().equalsIgnoreCase(defaultDescription.trim());

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonToCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonToCleanActionPerformed
        // Limpar os campos de texto
        jTextFieldName.setText("");
        jTextFieldUser.setText("");
        jTextFieldGender.setText("");
        jTextFieldEmail.setText("");
        jTextFieldNumberPhone.setText("");
        jTextFieldNationality.setText("");
        jTextFieldAge.setText("");
        jTextPaneBio.setText("Biography");
        jTextPaneBio.setForeground(new Color(169, 169, 169));

        // Resetar a imagem padrão
        lblFoto.setIcon(new ImageIcon(getClass().getResource("/Imagens/perfil.jpg")));
    }//GEN-LAST:event_jButtonToCleanActionPerformed

    private void jTextFieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserSignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private game.bugtracker.views.Combobox combobox2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonToClean;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldAge;
    private javax.swing.JTextField jTextFieldCity;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldGender;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldNationality;
    private javax.swing.JTextField jTextFieldNumberPhone;
    private javax.swing.JTextField jTextFieldPassword;
    private javax.swing.JTextField jTextFieldUser;
    private javax.swing.JTextPane jTextPaneBio;
    private javax.swing.JLabel lblFoto;
    // End of variables declaration//GEN-END:variables
}
