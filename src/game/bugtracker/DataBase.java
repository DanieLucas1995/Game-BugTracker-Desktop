package game.bugtracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {
    
    private Connection conexao;
    
    public void conectar() {
        try {
            System.out.println("Conectando: Inicializando conexão com o banco de dados...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/game_bug_tracker", "root", "Daniel");
            System.out.println("Conectado!");
        } catch (SQLException e) {
            System.out.println("Falha: Não foi possível conectar");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Falha ao encontrar a classe de conexão");
            e.printStackTrace();
        }
    }
    
    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Desconectado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar");
            e.printStackTrace();
        }
    }
    
    public Connection getConexao() {
        return conexao;
    }
    
    public void insertUser(String name, String userName, String gender, int age, String email, String password, String phoneNumber, String city, String nationality, String dateJoined, String bio, String accessType, int companyId) {
        conectar(); // Conecta ao banco de dados
        try {
            // Prepara a query de inserção
            String query = "INSERT INTO user (Name, user_Name, Gender, Age, Email, Password, Phone_Number, City, Nationality, Date_Joined, Bio, Access_Type, Company_idCompany) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, gender);
            preparedStatement.setInt(4, age);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, phoneNumber);
            preparedStatement.setString(8, city);
            preparedStatement.setString(9, nationality);
            preparedStatement.setString(10, dateJoined);
            preparedStatement.setString(11, bio);
            preparedStatement.setString(12, accessType);
            preparedStatement.setInt(13, companyId);
            
            // Executa a query de inserção
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Um novo usuário foi inserido com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
            e.printStackTrace();
        } finally {
            desconectar(); // Desconecta do banco de dados
        }
    }
    
    @WebServlet("/saveGame")
public class SaveGameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Coletar os dados do formulário
        String gameName = request.getParameter("name");
        String genre = request.getParameter("genre");
        String releaseDate = request.getParameter("releaseDate");
        String platform = request.getParameter("platforms");
        String priorityRate = request.getParameter("priorityRate");
        String user = request.getParameter("user");
        String description = request.getParameter("description");

        // Inserir os dados no banco de dados (coloque seu código JDBC aqui)
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/game_bug_tracker", "root", "Daniel");
            String query = "INSERT INTO game (GameName, Genre, ReleaseDate, Platform, PriorityRate, User, Description) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gameName);
            preparedStatement.setString(2, genre);
            preparedStatement.setString(3, releaseDate);
            preparedStatement.setString(4, platform);
            preparedStatement.setString(5, priorityRate);
            preparedStatement.setString(6, user);
            preparedStatement.setString(7, description);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Responder ao cliente (pode ser um JSON, uma página HTML, etc.)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{ \"success\": true }");
    }
}

}


