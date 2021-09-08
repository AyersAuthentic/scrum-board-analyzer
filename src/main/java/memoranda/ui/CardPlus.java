package main.java.memoranda.ui;
import java.util.LinkedHashMap;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class CardPlus extends JPanel {
  JPanel leftPane;
  JPanel rightPane;
  JPanel grid;
  String title;
  boolean turn;
  
  public CardPlus(String title, LinkedHashMap<String, String> map){
    this.title = title;
    turn = true;
    Border innerBorder = new EmptyBorder(50, 25, 50, 25);
    Border titleBorder = BorderFactory.createTitledBorder(title);
    Border outerBorder = new EmptyBorder(25, 25, 25, 25);
    Border border = BorderFactory.createCompoundBorder(titleBorder, innerBorder); 
    setBorder(BorderFactory.createCompoundBorder(outerBorder, border));
    setLayout(new BorderLayout());

    rightPane = new JPanel();
    rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
    rightPane.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    
    leftPane = new JPanel();
    leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
    leftPane.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    
    grid = new JPanel(new GridLayout(1, 0));

    buildItems(map);
  }
  
  public void buildItems(LinkedHashMap<String, String> map){
    for(String key : map.keySet()){
      addItem(key, map.get(key)); 
    }
    grid.add(leftPane);
    grid.add(rightPane);
    add(grid, BorderLayout.CENTER);
  }

  public void addItem(String key, String value){
    JLabel label = new JLabel("<html><div style='text-align: center;'>" + key + ": " + value  + "</div></html>");
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    if( turn ){
      leftPane.add(label);
      leftPane.add(Box.createVerticalStrut(15));
      turn = !turn;
    } else {
      rightPane.add(label);
      rightPane.add(Box.createVerticalStrut(15));
      turn = !turn;
    }
  }
}
