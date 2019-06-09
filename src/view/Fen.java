/**
 * contient la présentation de l'interface graphique
 */
package view;


import model.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * creation d'une fenetre
 * @author helen
 */
public class Fen {

    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable table1;
    private JTable tableClasse;
    private JTable tableStudent;
    private JButton eleveButton;
    private JButton voirClasseButton;


    /**
     * contructeur par default
     */
    public Fen() {//Object[][] content, String[] titles) {
        setBestLookAndFeelAvailable();
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!

        $$$setupUI$$$();
        init();
        //tableClasse.setModel(new JTable(content, titles).getModel());
        voirClasseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (voirClasseButton.getLabel().equals("Retour")) {
                    voirClasseButton.setLabel("Voir Classe");
                    refreshTabClasse();
                } else {
                    String[] titles = {"nom", "prenom"};
                    int indice = tableClasse.getSelectedRow();
                    if (indice != -1) {
                        voirClasseButton.setLabel("Retour");
                        refreshTabClasse((Integer) tableClasse.getValueAt(indice, 0), titles);
                    }
                }
            }
        });
        eleveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (eleveButton.getLabel().equals("Retour")) {
                    eleveButton.setLabel("Voir Eleve");
                    refreshTabEleve();
                } else {
                    eleveButton.setLabel("Retour");
                    int indice = tableStudent.getSelectedRow();
                    System.out.println("id eleve :" + (Integer) tableClasse.getValueAt(indice, 0));
                    refreshTabEleve((Integer) tableStudent.getValueAt(indice, 0));
                }
            }
        });
    }

    /**
     * je ne sais pas ce que fait cette fonction
     */
    public static void setBestLookAndFeelAvailable() {
        String system_lf = UIManager.getSystemLookAndFeelClassName().toLowerCase();
        if (system_lf.contains("metal")) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
            }
        } else {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }
        }
    }


    /**
     * update les tableau
     * @param id_classe l'id de la classe
     * @param titles 
     */
    public void refreshTabClasse(int id_classe, String[] titles) {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<personne> personneDAO = adf.getPersonneDAO();
        ArrayList<personne> ao = personneDAO.findAll();
        ArrayList<personne> ao2 = new ArrayList<>();

        for (int i = 0; i < ao.size(); i++) {
            if (ao.get(i).getType().equals("stud") || ao.get(i).getType().equals("Eleve")) {
                if (ao.get(i).getId_classe() == id_classe) {
                    ao2.add(ao.get(i));
                }
            }
        }

        Object[][] to = new Object[ao2.size()][2];
        for (int i = 0; i < ao2.size(); i++) {
            System.out.println(ao2.get(i).getType());
            to[i][0] = ao2.get(i).getName();
            to[i][1] = ao2.get(i).getFirstname();
            System.out.println(to[i][0]);
            System.out.println(to[i][1]);
        }
        tableClasse.setModel(new DefaultTableModel(to, titles));
    }

    /**
     * fonction qui update les tableau de classe
     */
    public void refreshTabClasse() {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<classe> classeDAO = adf.getClasseDAO();
        ArrayList<classe> ao = classeDAO.findAll();
        String[] titles = {"id", "nom", "école", "niveau", "annee scolaire"};
        Object[][] to = new Object[ao.size()][5];
        for (int i = 0; i < ao.size(); i++) {
            to[i][0] = ao.get(i).getID();
            to[i][1] = ao.get(i).getNom();
            to[i][2] = ao.get(i).getEcole();
            to[i][3] = ao.get(i).getNiveau();
            to[i][4] = ao.get(i).getAnneeScolaire();
            System.out.println(to[i][0]);
            System.out.println(to[i][1]);
        }
        tableClasse.setModel(new DefaultTableModel(to, titles));
    }

    /**
     * fonction qui update les tableau de classe
     * @param ao une array list de classe
     */
    public void refreshTabClasse(@NotNull ArrayList<classe> ao) {
        String[] titles = {"id", "nom", "école", "niveau", "annee scolaire"};
        Object[][] to = new Object[ao.size()][5];
        for (int i = 0; i < ao.size(); i++) {
            to[i][0] = ao.get(i).getID();
            to[i][1] = ao.get(i).getNom();
            to[i][2] = ao.get(i).getEcole();
            to[i][3] = ao.get(i).getNiveau();
            to[i][4] = ao.get(i).getAnneeScolaire();
            System.out.println(to[i][0]);
            System.out.println(to[i][1]);
        }
        tableClasse.setModel(new DefaultTableModel(to, titles));
    }

    /**
     * fonction qui update les tableaux d'eleve
     * @param indice 
     */
    public void refreshTabEleve(int indice) {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<bulletin> bulletinDAO = adf.getBulletinDAO();
        ArrayList<bulletin> ao = bulletinDAO.findAll();
        ArrayList<bulletin> ao2 = new ArrayList<>();
        for (int i = 0; i < ao.size(); i++) {
            if (ao.get(i).getId_eleve() == indice) {
                ao2.add(ao.get(i));
            }
        }
        String[] titles = {"id", "appreciation"};
        Object[][] to = new Object[ao2.size()][2];
        for (int i = 0; i < ao2.size(); i++) {
            to[i][0] = ao2.get(i).getID();
            to[i][1] = ao2.get(i).getAppreciation();
        }
        tableStudent.setModel(new DefaultTableModel(to, titles));
    }

    /**
     * fonction qui update les tables de eleves
     */
    public void refreshTabEleve() {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<personne> personneDAO = adf.getPersonneDAO();
        ArrayList<personne> ao = personneDAO.findAll();
        ArrayList<personne> ao2 = new ArrayList<>();
        for (int i = 0; i < ao.size(); i++) {
            if (ao.get(i).getType().equals("stud") || ao.get(i).getType().equals("Eleve")) {
                ao2.add(ao.get(i));
            }
        }
        String[] titles = {"id", "nom", "prenom"};
        Object[][] to = new Object[ao2.size()][3];
        for (int i = 0; i < ao2.size(); i++) {
            System.out.println(ao2.get(i).getType());
            to[i][0] = ao2.get(i).getID();
            to[i][1] = ao2.get(i).getName();
            to[i][2] = ao2.get(i).getFirstname();
        }
        tableStudent.setModel(new DefaultTableModel(to, titles));
    }

    /**
     * fonction qui update les tableau des eleves
     * @param ao une array list de personne 
     */
    public void refreshTabEleve(@NotNull ArrayList<personne> ao) {
        ArrayList<personne> ao2 = new ArrayList<>();
        for (int i = 0; i < ao.size(); i++) {
            if (ao.get(i).getType().equals("stud") || ao.get(i).getType().equals("Eleve")) {
                ao2.add(ao.get(i));
            }
        }
        String[] titles = {"id", "nom", "prenom"};
        Object[][] to = new Object[ao2.size()][3];
        for (int i = 0; i < ao2.size(); i++) {
            System.out.println(ao2.get(i).getType());
            to[i][0] = ao2.get(i).getID();
            to[i][1] = ao2.get(i).getName();
            to[i][2] = ao2.get(i).getFirstname();
        }
        tableStudent.setModel(new DefaultTableModel(to, titles));
    }

    /**
     * fonction d'initialisation
     */
    private void init() {
        JFrame fenetre = new JFrame();
        fenetre.setVisible(true);
        fenetre.add(panel1);
        //Définit un titre pour notre fenêtre
        fenetre.setTitle("School Management");
        //Définit sa taille : 400 pixels de large et 100 pixels de haut
        fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //Nous demandons maintenant à notre objet de se positionner au centre
        fenetre.setLocationRelativeTo(null);
        //Termine le processus lorsqu'on clique sur la croix rouge
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.setPreferredSize(new Dimension(452, 456));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(tabbedPane1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Classes", panel2);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel2.add(scrollPane1, gbc);
        tableClasse = new JTable();
        scrollPane1.setViewportView(tableClasse);
        voirClasseButton = new JButton();
        voirClasseButton.setText("Voir Classe");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(voirClasseButton, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer2, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Eleves", panel3);
        eleveButton = new JButton();
        eleveButton.setText("Voir Eleve");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(eleveButton, gbc);
        final JScrollPane scrollPane2 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(scrollPane2, gbc);
        tableStudent = new JTable();
        scrollPane2.setViewportView(tableStudent);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer4, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Profil", panel4);
        table1 = new JTable();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel4.add(table1, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
