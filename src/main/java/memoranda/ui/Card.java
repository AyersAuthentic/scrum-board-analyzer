package main.java.memoranda.ui;
import java.util.LinkedHashMap;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;

import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Card extends JPanel {
  JPanel listPane;
  boolean selected;
  String title;
  
  public Card(String title, LinkedHashMap<String, String> map){
    this.title = title;
    Border innerBorder = new EmptyBorder(50, 25, 50, 25);
    Border titleBorder = BorderFactory.createTitledBorder(title);
    Border outerBorder = new EmptyBorder(25, 25, 25, 25);
    Border border = BorderFactory.createCompoundBorder(titleBorder, innerBorder);
    setBorder(BorderFactory.createCompoundBorder(outerBorder, border));
    setLayout(new BorderLayout());
    listPane = new JPanel();
    listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
    listPane.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    //buildTitle(title);
    buildItems(map);
  }
  
  public void buildTitle(String title){
    JLabel label = new JLabel(title);
    label.setFont(new Font("Times New Roman", Font.BOLD, 30));
    add(label, BorderLayout.NORTH);    
  }
  
  public void buildItems(LinkedHashMap<String, String> map){
    for(String key : map.keySet()){
      addItem(key, map.get(key)); 
    }
    add(listPane, BorderLayout.CENTER);
  }

  public void addItem(String key, String value){
    JLabel label = new JLabel("<html><div style='text-align: center;'>" + key + ": " + value  + "</div></html>");
    listPane.add(label);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    listPane.add(Box.createVerticalStrut(15));
  }

  public void select(){
    Border innerBorder = new EmptyBorder(50, 25, 50, 25);
    TitledBorder titleBorder = BorderFactory.createTitledBorder(title);
    Border outerBorder = new EmptyBorder(25, 25, 25, 25);
    titleBorder.setTitleColor(Color.ORANGE);
    Border border = BorderFactory.createCompoundBorder(titleBorder, innerBorder);
    setBorder(BorderFactory.createCompoundBorder(outerBorder, border));
    repaint();   
  }

  public void deselect(){
    Border innerBorder = new EmptyBorder(50, 25, 50, 25);
    Border titleBorder = BorderFactory.createTitledBorder(title);
    Border outerBorder = new EmptyBorder(25, 25, 25, 25);
    Border border = BorderFactory.createCompoundBorder(titleBorder, innerBorder);
    setBorder(BorderFactory.createCompoundBorder(outerBorder, border));
    repaint();   
  }

}
