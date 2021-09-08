package main.java.memoranda.ui;


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;

import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Carousel extends JPanel {
  JScrollPane scrollPane;
  JPanel cardsPane;
  
  
  public Carousel(List<Card> cards){
    Border border = new EmptyBorder(50, 25, 50, 25);
    setBorder(border);
    setLayout(new BorderLayout());
    cardsPane = new JPanel();
    scrollPane = new JScrollPane(cardsPane, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
    cardsPane.setLayout(new GridLayout(1,0));
    cardsPane.setBorder(new EmptyBorder(25, 25, 25, 25));
    for(Card card : cards){
      cardsPane.add(card);
    }
    scrollPane.setPreferredSize(new Dimension((int)((int)cardsPane.getPreferredSize().getWidth()/cards.size()*2.5), (int)cardsPane.getPreferredSize().getHeight()));
    add(scrollPane, BorderLayout.CENTER);
  }

  public Carousel(){
    Border border = new EmptyBorder(50, 25, 50, 25);
    setBorder(border);
    setLayout(new BorderLayout());
    cardsPane = new JPanel();
    scrollPane = new JScrollPane(cardsPane, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
    cardsPane.setLayout(new GridLayout(1,0));
    cardsPane.setBorder(new EmptyBorder(25, 25, 25, 25));
    add(scrollPane, BorderLayout.CENTER);
  }
  
  public void addCard(Card card){
    cardsPane.add(card);
    revalidate();
    repaint();
  }
}

