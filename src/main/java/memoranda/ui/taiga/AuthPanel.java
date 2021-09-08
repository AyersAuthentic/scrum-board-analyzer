package main.java.memoranda.ui.taiga;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.ui.TaigaPanel;
import main.java.memoranda.api.MainConnection;
import main.java.memoranda.api.User;

public class AuthPanel extends JPanel {
  private JTextField email;
  private JPasswordField password;
  private JButton submit;
  private TaigaPanel nav;
  public AuthPanel(TaigaPanel nav) {
    this.nav = nav;
    try {
      jbInit();
    }
    catch (Exception ex) {
      new ExceptionDialog(ex);
    }
  }
  public void jbInit() throws Exception {
    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setLayout(layout);
    this.add(createTitleLabel());
    this.add(createEmailField());
    this.add(createPasswordField());
    this.add(createSubmitButton());
  }
  private JPanel createEmailField() {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Email:");
    panel.setLayout(new FlowLayout());
    email = new JTextField(20);
    panel.add(label);
    panel.add(email);
    return panel;
  }
  private JPanel createPasswordField() {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Password:");
    panel.setLayout(new FlowLayout());
    password = new JPasswordField(20);
    panel.add(label);
    panel.add(password);
    return panel;
  }
  private JPanel createTitleLabel() {
    JPanel title = new JPanel();
    title.setLayout(new FlowLayout());
    JLabel label = new JLabel("Taiga Login");
    title.add(label);
    return title;
  }
  private JPanel createSubmitButton() {
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    submit = new JButton("Submit");
    panel.add(submit);
    JPanel self = this;
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        String u = email.getText();
        String p = String.copyValueOf(password.getPassword());
        try {
          MainConnection.connect(u, p);
          nav.goTo("Sprint");
        } catch(IOException e) {
          System.out.println(e); //unauthorized, others use 400. It's supposed to be 401! In this case everything will be "invalid credentials"
          JOptionPane.showMessageDialog(self, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    return panel;
  }
}