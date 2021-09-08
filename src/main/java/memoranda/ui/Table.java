package main.java.memoranda.ui;

import java.util.List;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;

import javax.swing.table.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Table extends JPanel {
  protected DefaultTableModel model;
  protected JTable table;
  protected JScrollPane scroll;

  public Table(List<String> columnLabels){
    Border border = new EmptyBorder(50, 25, 50, 25);
    setBorder(border);
   
    model = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column){
        return false;
      }
    };
    for(String column : columnLabels){
      model.addColumn(column);
    }
    table = new JTable(model) {
       //following is pulled from https://stackoverflow.com/questions/17858132/automatically-adjust-jtable-column-to-fit-content to
       //auto resize columns according to content width
       @Override
       public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
           Component component = super.prepareRenderer(renderer, row, column);
           int rendererWidth = component.getPreferredSize().width;
           TableColumn tableColumn = getColumnModel().getColumn(column);
           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
           return component;
        }
    };
    table.setFillsViewportHeight( true );
    //table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    scroll = new JScrollPane(table);
    add(scroll);
  }
  
  public void addRow(Object[] row){
    model.addRow(row);
    revalidate();
    repaint();
  }
}
