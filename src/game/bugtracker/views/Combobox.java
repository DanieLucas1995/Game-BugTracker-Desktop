package game.bugtracker.views;

import javax.swing.*;
import java.awt.Color;


public class Combobox<E> extends JComboBox<E> {
    public Combobox() {
        // Itens do JComboBox
        String[] accessTypes = {"Select User Type", "Administrator", "Developer", "Tester/QA (Quality Assurance)", "Viewer", "Guest/Client", "Bug Reporter"};

        // Definir os itens no JComboBox
        for (String type : accessTypes) {
            addItem((E) type);
        }

        // Personalizar o fundo para branco e as letras para cinza
        setBackground(Color.WHITE);
        setForeground(Color.GRAY);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("User Access Type");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Criar uma instância de Combobox
        Combobox<String> accessTypeComboBox = new Combobox<>();

        // Adicionar o JComboBox ao painel de conteúdo do frame
        frame.add(accessTypeComboBox);

        frame.setVisible(true);
    }
}
