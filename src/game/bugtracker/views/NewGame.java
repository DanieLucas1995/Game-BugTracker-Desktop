package game.bugtracker.views;

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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class NewGame extends javax.swing.JFrame {
        
private Connection conexao;


    public NewGame() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        addFocusListenersToNewFields();
        jButtonNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
 // Chame conectar para inicializar a conexão
        conectar();
        
        // Adicione um MouseListener à JLabel lblFoto
        jLabelImageGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Chame o método carregarFoto() quando a JLabel for clicada
                carregarFoto();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Mude o cursor para a mão quando o mouse entrar na JLabel
                jLabelImageGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaure o cursor padrão quando o mouse sair da JLabel
                jLabelImageGame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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

            // Tamanho desejado
            int larguraDesejada = 366;
            int alturaDesejada = 214;

            // Redimensionamento da imagem
            Image foto = icon.getImage().getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);

            jLabelImageGame.setIcon(new ImageIcon(foto));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


    // Método para adicionar um FocusListener aos campos de texto
    private void addFocusListenerToTextField(javax.swing.JTextField textField, String hint) {
        textField.setText(hint);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(hint)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(hint);
                }
            }
        });
    }

// Chamada para adicionar FocusListener aos novos campos
private void addFocusListenersToNewFields() {
    addFocusListenerToTextField(jTextFieldGameName, "  Game Name");
    addFocusListenerToTextField(jTextFieldGenre, "  Genre");
    addFocusListenerToTextField(jTextFieldProjectName, "  Project Name");
    addFocusListenerToTextField(jTextFieldReleaseDate, "  Release Date");
    
    // Configure jTextPaneDescriptionGame
    jTextPaneDescriptionGame.setText(" Game Description");
    jTextPaneDescriptionGame.setForeground(new Color(169, 169, 169)); // Almost opaque gray
    jTextPaneDescriptionGame.setFont(new Font("Arial", Font.PLAIN, 14));

    // Add FocusListener to jTextPaneDescriptionGame
    jTextPaneDescriptionGame.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            // Clear the text when the focus is gained
            if (jTextPaneDescriptionGame.getText().equals(" Game Description")) {
                jTextPaneDescriptionGame.setText("");
            }
            jTextPaneDescriptionGame.setForeground(Color.BLACK);
        }

        @Override
        public void focusLost(FocusEvent e) {
            // If the field is empty, display the description again
            if (jTextPaneDescriptionGame.getText().isEmpty()) {
                jTextPaneDescriptionGame.setText(" Game Description");
                jTextPaneDescriptionGame.setForeground(new Color(169, 169, 169));
            }
        }
    });

    // Set the same color and font for other text fields
    jTextFieldGameName.setForeground(new Color(169, 169, 169));
    jTextFieldGameName.setFont(new Font("Arial", Font.PLAIN, 14));

    jTextFieldGenre.setForeground(new Color(169, 169, 169));
    jTextFieldGenre.setFont(new Font("Arial", Font.PLAIN, 14));

    jTextFieldProjectName.setForeground(new Color(169, 169, 169));
    jTextFieldProjectName.setFont(new Font("Arial", Font.PLAIN, 14));

    jTextFieldReleaseDate.setForeground(new Color(169, 169, 169));
    jTextFieldReleaseDate.setFont(new Font("Arial", Font.PLAIN, 14));
}

 public void conectar() {
        try {
            System.out.println("Connecting: Initializing connection to the database...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/game_bug_tracker", "root", "Daniel");
            System.out.println("Connected!");
        } catch (SQLException e) {
            System.out.println("Fail: Cannot connect");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Fail to find the connection class");
            e.printStackTrace();
        }
    }

private void saveToDatabase(String gameName, String genre, String releaseDate, String platform, String priorityRate, String description) {
    try {
        // Preparar a declaração SQL para inserir dados na tabela "game"
        String sql = "INSERT INTO game (game_name, genre, release_date, platform, priority_rate, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            // Configurar os parâmetros da declaração SQL
            statement.setString(1, gameName);
            statement.setString(2, genre);
            statement.setString(3, releaseDate);
            statement.setString(4, platform);
            statement.setString(5, priorityRate);
            statement.setString(6, description);
            
            // Executar a inserção no banco de dados
            statement.executeUpdate();
        }
    } catch (SQLException e) {
        System.out.println("Error saving to the database");
        e.printStackTrace();
    }
}




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldGameName = new javax.swing.JTextField();
        jTextFieldGenre = new javax.swing.JTextField();
        jTextFieldReleaseDate = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneDescriptionGame = new javax.swing.JTextPane();
        jTextFieldProjectName = new javax.swing.JTextField();
        jComboBoxPlatform = new javax.swing.JComboBox<>();
        jLabelImageGame = new javax.swing.JLabel();
        jButtonNext = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jComboBoxPriorityRate = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new java.awt.Dimension(1285, 761));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1285, 839));
        jPanel1.setMinimumSize(new java.awt.Dimension(1285, 839));

        jLabel1.setFont(new java.awt.Font("Malgun Gothic", 1, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(251, 143, 100));
        jLabel1.setText("Let's start");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/line2.png"))); // NOI18N

        jTextFieldGameName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextFieldGenre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextFieldReleaseDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextPaneDescriptionGame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.setViewportView(jTextPaneDescriptionGame);

        jTextFieldProjectName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTextFieldProjectName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBoxPlatform.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBoxPlatform.setForeground(new java.awt.Color(51, 51, 51));
        jComboBoxPlatform.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Platforms", " PlayStation 4", " PlayStation 5", " Switch", " Xbox One", " Xbox Series", " PC (Personal Computer)", " Mobile (iOS e Android)" }));

        jLabelImageGame.setBackground(new java.awt.Color(255, 255, 255));
        jLabelImageGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/image.JPG"))); // NOI18N

        jButtonNext.setFont(new java.awt.Font("Tw Cen MT", 1, 29)); // NOI18N
        jButtonNext.setForeground(new java.awt.Color(255, 255, 255));
        jButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/next (1).png"))); // NOI18N
        jButtonNext.setBorder(null);
        jButtonNext.setBorderPainted(false);
        jButtonNext.setContentAreaFilled(false);
        jButtonNext.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jButtonCancel.setFont(new java.awt.Font("Tw Cen MT", 1, 29)); // NOI18N
        jButtonCancel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cancel.jpg"))); // NOI18N
        jButtonCancel.setBorder(null);
        jButtonCancel.setOpaque(true);
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jComboBoxPriorityRate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBoxPriorityRate.setForeground(new java.awt.Color(153, 153, 153));
        jComboBoxPriorityRate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Priority Rate", " 0 Star(s) - Low Priority", " 1 Star(s) - Low-Medium Priority", " 2 Star(s) - Medium Priority", " 3 Star(s) - Medium-High Priority", " 4 Star(s) - High Priority", " 5 Star(s) - Critical Priority" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxPriorityRate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldGenre)
                            .addComponent(jTextFieldGameName)
                            .addComponent(jTextFieldReleaseDate))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelImageGame, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(110, 110, 110)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonCancel)
                            .addComponent(jButtonNext)
                            .addComponent(jComboBoxPlatform, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jTextFieldGameName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(78, 78, 78)
                        .addComponent(jTextFieldGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jTextFieldReleaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jTextFieldProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jLabelImageGame, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jComboBoxPlatform, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154)
                        .addComponent(jButtonNext)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancel)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxPriorityRate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
       // Ao clicar no botão, redirecione para a tela UserSignUp
        UserSignUp userSignUpFrame = new UserSignUp();
        userSignUpFrame.setVisible(true);
        
        // Feche a tela atual (se necessário)
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
                                           
    // Certifique-se de que a conexão está inicializada
if (conexao == null) {
    System.out.println("Connection is null. Trying to connect...");
    conectar();
}

// Verifique se um item foi selecionado para a Plataforma
Object selectedPlatformObject = jComboBoxPlatform.getSelectedItem();

if (selectedPlatformObject == null || !(selectedPlatformObject instanceof String)) {
    JOptionPane.showMessageDialog(this, "Please choose a valid platform.", "Error", JOptionPane.ERROR_MESSAGE);
    return; // Evita que o código continue e salve no banco de dados
}

// Obtenha os valores dos campos de texto
String gameName = jTextFieldGameName.getText();
String genre = jTextFieldGenre.getText();
String releaseDate = jTextFieldReleaseDate.getText();
String description = jTextPaneDescriptionGame.getText();
String platform = (String) selectedPlatformObject;

// Verifique se um item foi selecionado para a PriorityRate
Object selectedPriorityRateObject = jComboBoxPriorityRate.getSelectedItem();

if (selectedPriorityRateObject == null || !(selectedPriorityRateObject instanceof String)) {
    JOptionPane.showMessageDialog(this, "Please choose a valid priority rate.", "Error", JOptionPane.ERROR_MESSAGE);
    return; // Evita que o código continue e salve no banco de dados
}

String priorityRate = (String) selectedPriorityRateObject;

try {
    // Salve os dados no banco de dados
    saveToDatabase(gameName, genre, releaseDate, platform, priorityRate, description);

    // Execute a consulta
   String query = "SELECT * FROM game WHERE game_name = ? AND genre = ? AND release_date = ? AND platform = ? AND priority_rate = ? AND description = ?";
    try (PreparedStatement statement = conexao.prepareStatement(query)) {
        statement.setString(1, gameName);
        statement.setString(2, genre);
        statement.setString(3, releaseDate);
        statement.setString(4, platform);
        statement.setString(5, priorityRate);
        statement.setString(6, description);

        // Execute a consulta usando executeQuery() para obter um conjunto de resultados
        try (ResultSet resultSet = statement.executeQuery()) {
            // Se um jogo for encontrado, abra a nova janela
            if (resultSet.next()) {
                // Adicione os JTextFields à nova janela
                JTextField textFieldGameName = new JTextField(resultSet.getString("game_name"));
                JTextField textFieldGenre = new JTextField(resultSet.getString("genre"));
                JTextField textFieldReleaseDate = new JTextField(resultSet.getString("release_date"));
                JTextField textFieldPlatform = new JTextField(resultSet.getString("platform"));
                JTextField textFieldPriorityRate = new JTextField(resultSet.getString("priority_rate"));
                JTextField textFieldDescription = new JTextField(resultSet.getString("description"));

                // Mostre a mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso no banco de dados", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Adicione aqui o tratamento para o caso em que não há resultados no resultSet
                // Por exemplo, uma mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao salvar o jogo no banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
} catch (SQLException e) {
    // Lide com a exceção de maneira apropriada (exiba mensagem de erro, registre em log, etc.)
    e.printStackTrace();
}



    }//GEN-LAST:event_jButtonNextActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      NewGame newGame = new NewGame();
        newGame.conectar();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JComboBox<String> jComboBoxPlatform;
    private javax.swing.JComboBox<String> jComboBoxPriorityRate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelImageGame;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldGameName;
    private javax.swing.JTextField jTextFieldGenre;
    private javax.swing.JTextField jTextFieldProjectName;
    private javax.swing.JTextField jTextFieldReleaseDate;
    private javax.swing.JTextPane jTextPaneDescriptionGame;
    // End of variables declaration//GEN-END:variables
}
