package memoranda.ui;

import main.java.memoranda.api.*;
import main.java.memoranda.model.*;
import main.java.memoranda.ui.data.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;

public class CollaboratorPanel extends JPanel
{
    JTabbedPane tbpTabbedPane0;
    JPanel pnPanel0;

    JPanel pnPanel2;
    JTable tbTable0;

    JPanel pnPanel3;
    JTable tbTable2;

    JPanel pnPanel4;
    JTable tbTable3;

    JPanel pnPanel5;
    JTable tbTable5;

    JPanel pnPanel6;
    JTable tbTable6;

    JPanel pnPanel7;
    JTable tbTable4;

    Project project;
    HashMap<String, Collaborator> myCollaborators;



    public void getTaigaData() throws IOException {
        MainConnection.connect("kaayers1", "myPassword");
        project = DataStore.getProject("Lebkuchen");
        myCollaborators = new HashMap<>();

        // Iterate over collaborator names and add them to myCollaborators if they're not null
        for (String collabName : project.getCollaboratorNames()) {
            System.out.println(collabName);
            Collaborator collaborator = project.getCollaborator(collabName);
            if (collaborator != null) { // Check if the collaborator is not null before proceeding
                myCollaborators.put(collaborator.getFirstName(), collaborator);
            }
        }

        // If project.getCollaborators() is guaranteed to not contain null values, this part is fine.
        // Otherwise, consider adding a null check here as well.
        project.getCollaborators().forEach((key, value) -> {
            if (value != null) { // Ensure the value is not null
                myCollaborators.put(value.getFirstName(), value);
            }
        });

        /*
        String imageUrl1 = "http://www.avajava.com/images/avajavalogo.jpg";
        String destinationFile1 = "image1.jpg";
        String destinationFile2 = "image1.jpg";
        String destinationFile3 = "image1.jpg";
        String destinationFile4 = "image1.jpg";
        String destinationFile5 = "image1.jpg";

        saveImage(imageUrl, destinationFile);
        */
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
    }

    /*
    public static void main(String[] args) {
        JFrame frame = new JFrame ("Collaborator Panel Example");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.add(new Scratch());
        frame.pack();
        frame.setVisible (true);
    }*/

    public CollaboratorPanel ()
    {
        super();
        try {
            getTaigaData();
        }
        catch(IOException e) {
            System.out.println("IOException logging in");
        }
        //System.out.println(project.getCollaborators());

        pnPanel0 = new JPanel();
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        setLayout( gbPanel0 );

        tbpTabbedPane0 = new JTabbedPane( );

        pnPanel2 = new JPanel();
        pnPanel2.setBorder(BorderFactory.createTitledBorder("Jemimah Thomas"));
        pnPanel2.setAutoscrolls(true);
        GridBagLayout gbPanel2 = new GridBagLayout();
        GridBagConstraints gbcPanel2 = new GridBagConstraints();
        pnPanel2.setLayout(gbPanel2);
        String [][]dataTable0 = new String[][] { new String[] {"First Name", myCollaborators.get("Jemimah").getFirstName()},
                new String[] {"Last Name", myCollaborators.get("Jemimah").getLastName()},
                new String[] {"Created At", myCollaborators.get("Jemimah").getCreatedAt()},
                new String[] {"Project", myCollaborators.get("Jemimah").getProject()},
                new String[] {"Is User Active", myCollaborators.get("Jemimah").getIs_user_active()},
                //new String[] {"User Name", myCollaborators.get("Jemimah").getUsername()},
                new String[] {"ID", myCollaborators.get("Jemimah").getId()} };
        String []colsTable0 = new String[] { "", "" };
        tbTable0 = new JTable( dataTable0, colsTable0 );
        gbcPanel2.gridx = 0;
        gbcPanel2.gridy = 0;
        gbcPanel2.gridwidth = 1;
        gbcPanel2.gridheight = 1;
        gbcPanel2.fill = GridBagConstraints.BOTH;
        gbcPanel2.weightx = 1;
        gbcPanel2.weighty = 1;
        gbcPanel2.anchor = GridBagConstraints.NORTH;
        gbPanel2.setConstraints( tbTable0, gbcPanel2 );
        pnPanel2.add(tbTable0);
        tbpTabbedPane0.addTab("Jemimah Thomas",pnPanel2);

        pnPanel3 = new JPanel();
        pnPanel3.setBorder(BorderFactory.createTitledBorder("Maija Kingston"));
        GridBagLayout gbPanel3 = new GridBagLayout();
        GridBagConstraints gbcPanel3 = new GridBagConstraints();
        pnPanel3.setLayout(gbPanel3);
        String [][]dataTable2 = new String[][] { new String[] {"First Name", "Maija"},
                new String[] {"Last Name", "Kingston"},
                new String[] {"Created At", "..."},
                new String[] {"Project", "..."},
                new String[] {"Is User Active", "No"},
                //new String[] {"User Name", myCollaborators.get("Maija").getUsername()},
                new String[] {"ID", "No longer active"} };
        String []colsTable2 = new String[] { "", "" };
        tbTable2 = new JTable( dataTable2, colsTable2 );
        gbcPanel3.gridx = 0;
        gbcPanel3.gridy = 0;
        gbcPanel3.gridwidth = 1;
        gbcPanel3.gridheight = 1;
        gbcPanel3.fill = GridBagConstraints.BOTH;
        gbcPanel3.weightx = 1;
        gbcPanel3.weighty = 1;
        gbcPanel3.anchor = GridBagConstraints.NORTH;
        gbPanel3.setConstraints( tbTable2, gbcPanel3 );
        pnPanel3.add( tbTable2 );
        tbpTabbedPane0.addTab("Maija Kingston",pnPanel3);

        pnPanel4 = new JPanel();
        pnPanel4.setBorder(BorderFactory.createTitledBorder("Trent Engleman"));
        GridBagLayout gbPanel4 = new GridBagLayout();
        GridBagConstraints gbcPanel4 = new GridBagConstraints();
        pnPanel4.setLayout( gbPanel4 );
        String [][]dataTable3 = new String[][] { new String[] {"First Name", myCollaborators.get("Trent").getFirstName()},
                new String[] {"Last Name", myCollaborators.get("Trent").getLastName()},
                new String[] {"Created At", myCollaborators.get("Trent").getCreatedAt()},
                new String[] {"Project", myCollaborators.get("Trent").getProject()},
                new String[] {"Is User Active", myCollaborators.get("Trent").getIs_user_active()},
                //new String[] {"User Name", myCollaborators.get("Trent").getUsername()},
                new String[] {"ID", myCollaborators.get("Trent").getId()} };
        String []colsTable3 = new String[] { "", "" };
        tbTable3 = new JTable( dataTable3, colsTable3 );
        gbcPanel4.gridx = 0;
        gbcPanel4.gridy = 0;
        gbcPanel4.gridwidth = 1;
        gbcPanel4.gridheight = 1;
        gbcPanel4.fill = GridBagConstraints.BOTH;
        gbcPanel4.weightx = 1;
        gbcPanel4.weighty = 1;
        gbcPanel4.anchor = GridBagConstraints.NORTH;
        gbPanel4.setConstraints( tbTable3, gbcPanel4 );
        pnPanel4.add( tbTable3 );
        tbpTabbedPane0.addTab("Trent Engleman",pnPanel4);

        pnPanel5 = new JPanel();
        pnPanel5.setBorder( BorderFactory.createTitledBorder("Michael Schnapp"));
        GridBagLayout gbPanel5 = new GridBagLayout();
        GridBagConstraints gbcPanel5 = new GridBagConstraints();
        pnPanel5.setLayout( gbPanel5 );
        String [][]dataTable5 = new String[][] { new String[] {"First Name", myCollaborators.get("Michael").getFirstName()},
                new String[] {"Last Name", myCollaborators.get("Michael").getLastName()},
                new String[] {"Created At", myCollaborators.get("Michael").getCreatedAt()},
                new String[] {"Project", myCollaborators.get("Michael").getProject()},
                new String[] {"Is User Active", myCollaborators.get("Michael").getIs_user_active()},
                //new String[] {"User Name", myCollaborators.get("Michael").getUsername()},
                new String[] {"ID", myCollaborators.get("Michael").getId()} };
        String []colsTable5 = new String[] { "", "" };
        tbTable5 = new JTable( dataTable5, colsTable5 );
        gbcPanel5.gridx = 0;
        gbcPanel5.gridy = 0;
        gbcPanel5.gridwidth = 1;
        gbcPanel5.gridheight = 1;
        gbcPanel5.fill = GridBagConstraints.BOTH;
        gbcPanel5.weightx = 1;
        gbcPanel5.weighty = 1;
        gbcPanel5.anchor = GridBagConstraints.NORTH;
        gbPanel5.setConstraints( tbTable5, gbcPanel5 );
        pnPanel5.add( tbTable5 );
        tbpTabbedPane0.addTab("Michael Schnapp",pnPanel5);

        pnPanel6 = new JPanel();
        pnPanel6.setBorder(BorderFactory.createTitledBorder("Jessica Kuksuk"));
        GridBagLayout gbPanel6 = new GridBagLayout();
        GridBagConstraints gbcPanel6 = new GridBagConstraints();
        pnPanel6.setLayout(gbPanel6);
        String [][]dataTable6 = new String[][] { new String[] {"First Name", myCollaborators.get("Jessica").getFirstName()},
                new String[] {"Last Name", myCollaborators.get("Jessica").getLastName()},
                new String[] {"Created At", myCollaborators.get("Jessica").getCreatedAt()},
                new String[] {"Project", myCollaborators.get("Jessica").getProject()},
                new String[] {"Is User Active", myCollaborators.get("Jessica").getIs_user_active()},
                //new String[] {"User Name", myCollaborators.get("Jemimah").getUsername()},
                new String[] {"ID", myCollaborators.get("Jessica").getId()} };
        String []colsTable6 = new String[] { "", "" };
        tbTable6 = new JTable( dataTable6, colsTable6 );
        gbcPanel6.gridx = 0;
        gbcPanel6.gridy = 0;
        gbcPanel6.gridwidth = 1;
        gbcPanel6.gridheight = 1;
        gbcPanel6.fill = GridBagConstraints.BOTH;
        gbcPanel6.weightx = 1;
        gbcPanel6.weighty = 1;
        gbcPanel6.anchor = GridBagConstraints.NORTH;
        gbPanel6.setConstraints(tbTable6, gbcPanel6);
        pnPanel6.add(tbTable6);
        tbpTabbedPane0.addTab("Jessica Kuksuk",pnPanel6);

        pnPanel7 = new JPanel();
        pnPanel7.setBorder( BorderFactory.createTitledBorder("Kevin Ayers"));
        GridBagLayout gbPanel7 = new GridBagLayout();
        GridBagConstraints gbcPanel7 = new GridBagConstraints();
        pnPanel7.setLayout( gbPanel7 );
        String [][]dataTable4 = new String[][] { new String[] {"First Name", myCollaborators.get("Kevin").getFirstName()},
                new String[] {"Last Name", myCollaborators.get("Kevin").getLastName()},
                new String[] {"Created At", myCollaborators.get("Kevin").getCreatedAt()},
                new String[] {"Project", myCollaborators.get("Kevin").getProject()},
                new String[] {"Is User Active", myCollaborators.get("Kevin").getIs_user_active()},
                //new String[] {"User Name", myCollaborators.get("Kevin").getUsername()},
                new String[] {"ID", myCollaborators.get("Kevin").getId()} };
        String []colsTable4 = new String[] { "", "" };
        tbTable4 = new JTable( dataTable4, colsTable4 );
        gbcPanel7.gridx = 0;
        gbcPanel7.gridy = 0;
        gbcPanel7.gridwidth = 1;
        gbcPanel7.gridheight = 1;
        gbcPanel7.fill = GridBagConstraints.BOTH;
        gbcPanel7.weightx = 1;
        gbcPanel7.weighty = 1;
        gbcPanel7.anchor = GridBagConstraints.NORTH;
        gbPanel7.setConstraints(tbTable4, gbcPanel7);
        pnPanel7.add(tbTable4);
        tbpTabbedPane0.addTab("Kevin Ayers",pnPanel7);

        gbcPanel0.gridx = 2;
        gbcPanel0.gridy = 2;
        gbcPanel0.gridwidth = 16;
        gbcPanel0.gridheight = 16;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(tbpTabbedPane0, gbcPanel0);
        add(tbpTabbedPane0);

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                //System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
            }
        };
        tbpTabbedPane0.addChangeListener(changeListener);
        //pnPanel0.add(tbpTabbedPane0, BorderLayout.CENTER);
    }

}

//Scratch pnPanel0 =  new Panel0();