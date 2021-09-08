package main.java.memoranda.ui.taiga;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

import main.java.memoranda.model.Project;
import main.java.memoranda.model.Sprint;
import main.java.memoranda.ui.Card;
import main.java.memoranda.ui.Carousel;
import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.ui.ProjectCarousel;
import main.java.memoranda.ui.ProjectTable;
import main.java.memoranda.ui.SprintCarousel;
import main.java.memoranda.ui.SprintTable;
import main.java.memoranda.ui.Table;
import main.java.memoranda.ui.TaigaPanel;
import main.java.memoranda.ui.ToggleDisplayPanel;
import main.java.memoranda.ui.data.PlaceholderDataStore;

public class ProjectPanel extends ToggleDisplayPanel {
	protected Table table;
	protected Carousel carousel;
	JButton toggle;
	JPanel togglePanel;
	protected int mode = 0;

	public ProjectPanel() {
		super();
		PlaceholderDataStore.initialize();
		Project project = PlaceholderDataStore.project;
		carousel = new ProjectCarousel(project);
		table = new ProjectTable(project);
		try {
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
		buildContent();
	}

	public void jbInit() {
		setLayout(new BorderLayout());
	}

	public Sprint getSelectedSprint() {
		if (mode == 0) {
			return ((ProjectCarousel) carousel).getSelectedSprint();
		} else {
			return ((ProjectTable) table).getSelectedSprint();
		}
	}

	public void createTogglePanel() {
		toggle = new JButton("Toggle View");
		toggle.setPreferredSize(new Dimension(300, 50));
		toggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode == 0) {
					remove(table);
					add(carousel, BorderLayout.CENTER);
					mode = 1;
				} else {
					remove(carousel);
					add(table, BorderLayout.CENTER);
					mode = 0;
				}
				revalidate();
				repaint();
			}
		});
		togglePanel = new JPanel();
		togglePanel.add(toggle, BorderLayout.SOUTH);
	}

	private JPanel createTitleLabel() {
		JPanel title = new JPanel();
		title.setLayout(new FlowLayout());
		JLabel label = new JLabel("Projects");
		label.setFont(new Font("Serif", 0, 30));
		title.setLocation(1080, 270);
		title.add(label);

		return title;
	}

	protected void buildContent() {
		createTogglePanel();
		add(togglePanel, BorderLayout.SOUTH);
		add(table, BorderLayout.CENTER);
		add(createTitleLabel(), BorderLayout.NORTH);
	}
}