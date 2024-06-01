package game.bugtracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/UserRegistrationServlet") // Especifica o URL para este servlet
public class UserRegistrationServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar parâmetros do formulário
        String name = request.getParameter("Name");
        String userName = request.getParameter("user_Name");
        String gender = request.getParameter("Gender");
        int age = Integer.parseInt(request.getParameter("Age"));
        String email = request.getParameter("Email");
        String password = request.getParameter("Password");
        String phoneNumber = request.getParameter("Phone_Number");
        String city = request.getParameter("City");
        String nationality = request.getParameter("Nationality");
        String dateJoined = request.getParameter("Date_Joined");
        String bio = request.getParameter("Bio");
        String accessType = request.getParameter("Access_Type");
        int companyId = Integer.parseInt(request.getParameter("Company_idCompany"));
        
        // Instanciar o objeto DataBase e inserir o usuário no banco de dados
        DataBase db = new DataBase();
        db.insertUser(name, userName, gender, age, email, password, phoneNumber, city, nationality, dateJoined, bio, accessType, companyId);
        
        // Redirecionar o usuário para uma página de confirmação
        response.sendRedirect("registrationConfirmation.html");
    }
    
    
}
