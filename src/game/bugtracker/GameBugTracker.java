
package game.bugtracker;

import game.bugtracker.views.Login;
import java.sql.SQLException;
import java.text.ParseException;


public class GameBugTracker {

  
  public static void main(String[] args) throws SQLException, ParseException {

        // Inicializa a interface gráfica da tela de login
        Login login = new Login();

        // Torna a janela de login visível
        login.setVisible(true);

        DataBase conector = new DataBase();
        conector.conectar();

    }

}
    

