

import memoranda.ui.CollaboratorPanel;

import javax.swing.*;

public class CollaboratorExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame ("Collaborator Panel Example");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.add(new CollaboratorPanel());
        frame.pack();
        frame.setVisible (true);
    }
    }
