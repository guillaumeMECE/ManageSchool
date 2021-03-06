/*
 * contient la logique concernant les actions effectuées par l'utilisateur
 */
package controller;



import java.util.ArrayList;
import java.util.Scanner;
import model.DAO;
import model.bulletin;
import model.*;
import view.Fen;
import view.TESTfen;

/**
 * classe principal qui fait le lien entre utilisateur et machine
 * @author helen, guillaume, clara
 */
public class App {
    //private People session = null;//variable de session

    /**
     * connection en tant que personne 
     * @param user le pseudo de la personne qui essaye de se connecter
     * @param mdp son mot de passe
     * @return true si l'user existe et qu'il a le bon mot de passe, false sinon
     */
    public static Boolean connection(String user, String mdp) {
        Boolean isAUTH = false;
        if (user.equals("gui")) {//if user&mdp in database fct
            isAUTH = true;
            System.out.println("Connection allowed");
        } else {//if not then connection not allowed
            //error msg
            System.out.println("Connection not allowed");
        }
        return isAUTH;
    }

    /**
     * le main du projet
     * @param args 
     */
    public static void main(String[] args) {
        // write your code here
        
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        //On récupère un objet faisant le lien entre la base et nos objets 
        
        ///TEST BULLETIN -------------------------------------------------------------------------------------------
        DAO<bulletin> bulletinDAO = adf.getBulletinDAO();
        //bulletin newBu = new bulletin(0,"AB",0,0);
       // bulletinDAO.create(newBu);
        
        bulletin bubu = bulletinDAO.find(1);
        System.out.println("ID BULLETIN: " + bubu.getID());
        System.out.println("Appreciation : " + bubu.getAppreciation());
        System.out.println("ID inscription : " + bubu.getIDinscription());
        System.out.println("ID trimestre " + bubu.getIDtrimestre());
        
        
        //bulletinDAO.delete(bubu);
        
        //find all
        ArrayList<bulletin> alBulletin = bulletinDAO.findAll();
        /*for(int i =0; i<alBulletin.size(); i++)
        {
            System.out.println("ID BULLETIN ARRAY LIST: " + alBulletin.get(i).getID());
            System.out.println("Appreciation: " + alBulletin.get(i).getAppreciation());
            System.out.println("ID inscription: " + alBulletin.get(i).getIDinscription());
            System.out.println("ID trimestre: " + alBulletin.get(i).getIDtrimestre());
        }*/
        
        ArrayList<bulletin> RechercheBulletin = bulletinDAO.rechercher("appreciation", "Felicitation");
        /*for(int i = 0; i<RechercheBulletin.size(); i++)
        {
            System.out.println("ID BULLETIN RECHERCHE ==================================: " + RechercheBulletin.get(i).getID());
            System.out.println("Appreciation: " + RechercheBulletin.get(i).getAppreciation());
            System.out.println("ID inscription: " + RechercheBulletin.get(i).getIDinscription());
            System.out.println("ID trimestre: " + RechercheBulletin.get(i).getIDtrimestre());
        }*/
        
        //bulletin bulletinM = new bulletin(5,"",-1,1);
        //bulletinDAO.update(bulletinM);
        
        ///TEST ANNEESCOLAIRE -------------------------------------------------------------------------------------------
        
        ///PB ca n'increment pas tout seul et ca s'est embetant ... A rectifier
        
        DAO<anneescolaire> anneescolaireDAO = adf.getAnneeScolaireDAO();
        anneescolaire newAS = new anneescolaire(1);
        
        //anneescolaireDAO.create(newAS);
        
        anneescolaire anan = anneescolaireDAO.find(1);
        System.out.println("ID ANNEE SCOLAIRE: " + anan.getID());
        
        
        //anneescolaireDAO.delete(anan);
        
        //find all
        ArrayList<anneescolaire> aScolaire = anneescolaireDAO.findAll();
        /*for(int i =0; i<aScolaire.size(); i++)
        {
            System.out.println("ID ANNEE SCOLAIRE ARRAY LIST: " + aScolaire.get(i).getID());
        }*/
        
        ///TEST CLASSE -------------------------------------------------------------------------------------------
        
        DAO<classe> classeDAO = adf.getClasseDAO();
        
        //creation
        //classe newClass = new classe(2, "CE1a", 0,0,0);
        //classeDAO.create(newClass);
        
        //find
        classe clacla = classeDAO.find(2);
        System.out.println("ID CLASSE: " + clacla.getID());
        System.out.println("Nom : " + clacla.getNom());
        System.out.println("ID annee scolaire : " + clacla.getAnneeScolaire());
        System.out.println("ID ecole " + clacla.getEcole());
        System.out.println("ID niveau " + clacla.getNiveau());
        
        //delete
        //classeDAO.delete(clacla);
        
        //findALL
        ArrayList<classe> alClasse = classeDAO.findAll();
        /*for(int i =0; i<alClasse.size(); i++)
        {
            System.out.println("ID CLASSE ARRAY LIST: " + alClasse.get(i).getID());
            System.out.println("Nom: " + alClasse.get(i).getNom());
            System.out.println("ID annee scolaire: " + alClasse.get(i).getAnneeScolaire());
            System.out.println("ID ecole: " + alClasse.get(i).getEcole());
            System.out.println("ID niveau " + alClasse.get(i).getNiveau());
        }*/
        
        ArrayList<classe> RechercheClasse = classeDAO.rechercher("nom", "CE1a");
        /*for(int i = 0; i<RechercheClasse.size(); i++)
        {
            System.out.println("ID CLASSE RECHERCHE ====================================: " + RechercheClasse.get(i).getID());
            System.out.println("Nom: " + RechercheClasse.get(i).getNom());
            System.out.println("ID annee scolaire: " + RechercheClasse.get(i).getAnneeScolaire());
            System.out.println("ID ecole: " + RechercheClasse.get(i).getEcole());
            System.out.println("ID niveau " + RechercheClasse.get(i).getNiveau());
        }*/
        
        classe newClasse = new classe(8, "CM2b", 2,1,1);
        classeDAO.update(newClasse);
        
        ///TEST DETAIL BULLETIN -------------------------------------------------------------------------------------------
        
        DAO<detailbulletin> detailBulletinDAO = adf.getDetailBulletinDAO();
        
        //creation
        detailbulletin newDbubu = new detailbulletin(0,"Bon travail",0,0);
        detailBulletinDAO.create(newDbubu);
        
        //find
        detailbulletin dbubu = detailBulletinDAO.find(3);
        System.out.println("ID DETAIL BULLETIN: " + dbubu.getID());
        System.out.println("Appreciation : " + dbubu.getAppreciation());
        System.out.println("ID bulletin : " + dbubu.getBulletin());
        System.out.println("ID enseignement " + dbubu.getEnseignement());
        
        //delete
        //detailBulletinDAO.delete(dbubu);
        
        //findALL
        ArrayList<detailbulletin> alDbulletin = detailBulletinDAO.findAll();
        
        /*for(int i =0; i<alDbulletin.size(); i++)
        {
            System.out.println("ID DETAIL BULLETIN ARRAY LIST: " + alDbulletin.get(i).getID());
            System.out.println("Appreciation: " + alDbulletin.get(i).getAppreciation());
            System.out.println("ID bulletin: " + alDbulletin.get(i).getBulletin());
            System.out.println("ID enseignement: " + alDbulletin.get(i).getEnseignement());
        }*/
        
        ArrayList<detailbulletin> RechercheDetailBulletin = detailBulletinDAO.rechercher("id_enseignement", 1);
        /*for(int i = 0; i<RechercheDetailBulletin.size(); i++)
        {
            System.out.println("ID DETAIL BULLETIN RECHERCHE ==========================: " + RechercheDetailBulletin.get(i).getID());
            System.out.println("Appreciation: " + RechercheDetailBulletin.get(i).getAppreciation());
            System.out.println("ID bulletin: " + RechercheDetailBulletin.get(i).getBulletin());
            System.out.println("ID enseignement: " + RechercheDetailBulletin.get(i).getEnseignement());
        }*/
        
        detailbulletin newDbulletin = new detailbulletin(49,"Il faut encore poussser les efforts", 1, 3);
        detailBulletinDAO.update(newDbulletin);
        
        
        ///TEST DISCIPLINE -------------------------------------------------------------------------------------------
        
        DAO<discipline> disciplineDAO = adf.getDisciplineDAO();
        
        //creation
        discipline newdidi = new discipline(0,"art");
        disciplineDAO.create(newdidi);
        
        //find
        discipline didi = disciplineDAO.find(1);
        System.out.println("ID DISCIPLINE: " + didi.getID());
        System.out.println("Nom : " + didi.getNom());
        
        //delete
        //disciplineDAO.delete(didi);
        
        //findALL
        ArrayList<discipline> alDiscipline = disciplineDAO.findAll();
        
        /*for(int i =0; i<alDiscipline.size(); i++)
        {
            System.out.println("ID DISCIPLINE ARRAY LIST: " + alDiscipline.get(i).getID());
            System.out.println("nom: " + alDiscipline.get(i).getNom());
        }*/
        
        ArrayList<discipline> RechercheDiscipline = disciplineDAO.rechercher("nom", "art");
        /*for(int i = 0; i<RechercheDiscipline.size(); i++)
        {
            System.out.println("ID DISCIPLINE RECHERCHE ==========================: " + RechercheDiscipline.get(i).getID());
            System.out.println("nom: " + RechercheDiscipline.get(i).getNom());
        }*/
        
        discipline newDi = new discipline(57,"littérature");
        disciplineDAO.update(newDi);
        
        ///TEST ECOLE -------------------------------------------------------------------------------------------
        
        DAO<ecole> ecoleDAO = adf.getEcoleDAO();
        
        //creation
        //ecole newecole = new ecole(0,"Lycee Richelieu", "rue Gorges Sand, Rueil Malmaison");
        //ecoleDAO.create(newecole);
        
        //find
        ecole eco = ecoleDAO.find(2);
        System.out.println("ID ECOLE: " + eco.getID());
        System.out.println("Nom : " + eco.getNom());
        System.out.println("Adresse : " + eco.getAdresse());
        
        //delete
        //ecoleDAO.delete(eco);
        
         //findALL
        ArrayList<ecole> alEcole = ecoleDAO.findAll();
        
        /*for(int i =0; i<alEcole.size(); i++)
        {
            System.out.println("ID ECOLE ARRAY LIST: " + alEcole.get(i).getID());
            System.out.println("nom: " + alEcole.get(i).getNom());
            System.out.println("nom: " + alEcole.get(i).getAdresse());
        }*/
        
        ArrayList<ecole> RechercheEcole = ecoleDAO.rechercher("nom_ecole", "Lycee Richelieu");
        /*for(int i = 0; i<RechercheEcole.size(); i++)
        {
            System.out.println("ID ECOLE RECHERCHE ====================================: " + RechercheEcole.get(i).getID());
            System.out.println("nom: " + RechercheEcole.get(i).getNom());
            System.out.println("nom: " + RechercheEcole.get(i).getAdresse());
        }*/
        
        ecole newEc = new ecole(47,"Tuck Stell", "Rueil Malmaison");
        ecoleDAO.update(newEc);
        
        
        ///TEST ENSEIGNEMENT -------------------------------------------------------------------------------------------
        DAO<enseignement> enseignementDAO = adf.getEnseignementDAO();
              
        //creation
        enseignement newEns = new enseignement(0,0,0,0);
        enseignementDAO.create(newEns);
        
        //find 
        enseignement ens = enseignementDAO.find(1);
        System.out.println("ID ENSEIGNEMENT: " + ens.getID());
        System.out.println("Classe : " + ens.getClasse());
        System.out.println("Discipline : " + ens.getDiscipline());
        System.out.println("Personne : " + ens.getPersonne());
        
        //delete
        //enseignement.delete(ens);
        
        ArrayList<enseignement> alEnseignement = enseignementDAO.findAll();
        
        /*for(int i =0; i<alEnseignement.size(); i++)
        {
            System.out.println("ID ENSEINGEMENT ARRAY LIST: " + alEnseignement.get(i).getID());
            System.out.println("Classe: " + alEnseignement.get(i).getClasse());
            System.out.println("Discipline: " + alEnseignement.get(i).getDiscipline());
            System.out.println("Personne : " + alEnseignement.get(i).getPersonne());
        }*/
        
        enseignement newEnse = new enseignement(2,1,1,1);
        enseignementDAO.update(newEnse);
        
        ///TEST EVALUATION -------------------------------------------------------------------------------------------
        DAO<evaluation> evaluationDAO = adf.getEvaluationDAO();
              
        //creation
        //evaluation newEv = new evaluation(0,"Attention aux fautes de calculs",13,0);
        //evaluationDAO.create(newEv);
        
        //find 
        evaluation ev = evaluationDAO.find(1);
        System.out.println("ID EVALUATION: " + ev.getID());
        System.out.println("Appreciation : " + ev.getAppreciation());
        System.out.println("Note : " + ev.getNote());
        System.out.println("DetailBulletin : " + ev.getDetailBulletin());
        
        //delete
        //evaluationDAO.delete(ev);
        
        ArrayList<evaluation> alEval = evaluationDAO.findAll();
        
        /*for(int i =0; i<alEval.size(); i++)
        {
            System.out.println("ID EVALUTION ARRAY LIST: " + alEval.get(i).getID());
            System.out.println("Appreciation: " + alEval.get(i).getAppreciation());
            System.out.println("Note: " + alEval.get(i).getNote());
            System.out.println("DetailBulletin : " + alEval.get(i).getDetailBulletin());
        }*/
        
        ArrayList<evaluation> RechercheEval = evaluationDAO.rechercher("note", 17);
        /*
        for(int i = 0; i<RechercheEval.size(); i++)
        {
            System.out.println("ID EVALUTION RECHERCHE =====================================: " + RechercheEval.get(i).getID());
            System.out.println("Appreciation: " + RechercheEval.get(i).getAppreciation());
            System.out.println("Note: " + RechercheEval.get(i).getNote());
            System.out.println("DetailBulletin : " + RechercheEval.get(i).getDetailBulletin());
        }
        */
        
       // evaluation NewEva = new evaluation(6,"TB", 5,2);
        //evaluationDAO.update(NewEva);
        
        ///TEST INSCRIPTION -------------------------------------------------------------------------------------------
        DAO<inscription> inscriptionDAO = adf.getInscriptionDAO();
              
        //creation
        //inscription newIns = new inscription(0,1,3);
        //inscriptionDAO.create(newIns);
        
        //find 
        inscription ins = inscriptionDAO.find(1);
        System.out.println("ID INSCRIPTION: " + ins.getID());
        System.out.println("classe : " + ins.getClasse());
        System.out.println("personne : " + ins.getPersonne());
        
        //delete
        //inscriptionDAO.delete(ins);
        
        ArrayList<inscription> alIns = inscriptionDAO.findAll();
        
        /*for(int i =0; i<alIns.size(); i++)
        {
            System.out.println("ID INSCRIPTION ARRAY LIST: " + alIns.get(i).getID());
            System.out.println("classe: " + alIns.get(i).getClasse());
            System.out.println("personne: " + alIns.get(i).getPersonne());
        }*/
        
        ArrayList<inscription> RechercheInscription = inscriptionDAO.rechercher("id_personne", 4);
        /*for(int i = 0; i<RechercheInscription.size(); i++)
        {
            System.out.println("ID INSCRIPTION RECHERCHE =================================: " + RechercheInscription.get(i).getID());
            System.out.println("classe: " + RechercheInscription.get(i).getClasse());
            System.out.println("personne: " + RechercheInscription.get(i).getPersonne());
        }*/
        
        //inscription newInscri = new inscription(5,2,2);
        //inscriptionDAO.update(newInscri);
        
        
        ///TEST NIVEAU -------------------------------------------------------------------------------------------
        DAO<niveau> niveauDAO = adf.getNiveauDAO();
              
        //creation
        //niveau newNiv = new niveau(0,"CE1");
        //niveauDAO.create(newNiv);
        
        //find 
        niveau niv = niveauDAO.find(2);
        System.out.println("ID NIVEAU: " + niv.getID());
        System.out.println("nom : " + niv.getNom());
        
        //delete
        //niveauDAO.delete(niv);
        
        ArrayList<niveau> Alevel = niveauDAO.findAll();
        
        /*for(int i =0; i<Alevel.size(); i++)
        {
            System.out.println("ID NIVEAU ARRAY LIST: " + Alevel.get(i).getID());
            System.out.println("nom: " + Alevel.get(i).getNom());
        }*/
        
        ArrayList<niveau> RechercheLevel = niveauDAO.rechercher("nom", "CP");
        /*for(int i = 0; i<RechercheLevel.size(); i++)
        {
            System.out.println("ID NIVEAU RECHERCHE ===================================: " + RechercheLevel.get(i).getID());
            System.out.println("nom: " + RechercheLevel.get(i).getNom());
        }*/
        
        niveau newLevel = new niveau(4, "CE2");
        niveauDAO.update(newLevel);
        
        ///TEST PERSONNE -------------------------------------------------------------------------------------------
        DAO<personne> personneDAO = adf.getPersonneDAO();
              
        //creation
        //personne moi = new personne(0,"Helene", "Carlier-Gubler", "Prof", "LogNep", "coucou");
        //personneDAO.create(moi);
        
        //find 
        personne toi = personneDAO.find(2);
        System.out.println("ID PERSONNE: " + toi.getID());
        System.out.println("nom : " + toi.getName());
        System.out.println("prenom : " + toi.getFirstname());
        System.out.println("type : " + toi.getType());
        System.out.println("user : " + toi.getUser());
        System.out.println("mdp : " + toi.getmdp());
        
        //delete
        //personneDAO.delete(toi);
        
        ArrayList<personne> vous = personneDAO.findAll();
        
        /*for(int i =0; i<vous.size(); i++)
        {
            System.out.println("ID PERSONNE ARRAY LIST: " + vous.get(i).getID());
            System.out.println("nom: " + vous.get(i).getName());
            System.out.println("prenom: " + vous.get(i).getFirstname());
            System.out.println("type: " + vous.get(i).getType());
            System.out.println("user: " + vous.get(i).getUser());
            System.out.println("mdp: " + vous.get(i).getmdp());
        }*/
        
       // ArrayList<personne> il = personneDAO.rechercher("userP", "LogNep");
       /*
        for(int i = 0; i<RechercheEcole.size(); i++)
        {
            System.out.println("ID PERSONNE RECHERCHE ===========================================: " + il.get(i).getID());
            System.out.println("nom: " + il.get(i).getName());
            System.out.println("prenom: " + il.get(i).getFirstname());
            System.out.println("type: " + il.get(i).getType());
            System.out.println("user: " + il.get(i).getUser());
            System.out.println("mdp: " + il.get(i).getmdp());
        }
        */
       
       //personne elle = new personne(2,"Clara", "Sabatey", "Eleve", "Clacla", "love");
       //personneDAO.update(elle);
        
        
        ///TEST TRIMESTRE -------------------------------------------------------------------------------------------
        DAO<trimestre> trimestreDAO = adf.getTrimestreDAO();
              
        //creation
        //trimestre Newtrim = new trimestre(0,3,"01/09/2018","31/06/2019", 0);
        //trimestreDAO.create(Newtrim);
        
        //find 
        trimestre trim = trimestreDAO.find(1);
        System.out.println("ID TRIMESTRE: " + trim.getID());
        System.out.println("numero : " + trim.getNum());
        System.out.println("debut : " + trim.getDebut());
        System.out.println("fin : " + trim.getFin());
        System.out.println("annee scolaire : " + trim.getAnneeScolaire());
        
        //delete
        //personneDAO.delete(toi);
        
        ArrayList<trimestre> Alltrim = trimestreDAO.findAll();
        
        /*for(int i =0; i<Alltrim.size(); i++)
        {
            System.out.println("ID TRIMESTRE ARRAY LIST: " + Alltrim.get(i).getID());
            System.out.println("numero: " + Alltrim.get(i).getNum());
            System.out.println("debut: " + Alltrim.get(i).getDebut());
            System.out.println("fin: " + Alltrim.get(i).getFin());
            System.out.println("anneeScolaire: " + Alltrim.get(i).getAnneeScolaire());
        }*/
        
        ArrayList<trimestre> RechercheTrim = trimestreDAO.rechercher("debut", "01/09/2018");
        /*for(int i = 0; i<RechercheTrim.size(); i++)
        {
            System.out.println("ID TRIMESTRE RECHERCHE ====================================: " + RechercheTrim.get(i).getID());
            System.out.println("numero: " + RechercheTrim.get(i).getNum());
            System.out.println("debut: " + RechercheTrim.get(i).getDebut());
            System.out.println("fin: " + RechercheTrim.get(i).getFin());
            System.out.println("anneeScolaire: " + RechercheTrim.get(i).getAnneeScolaire());
        }*/
        
        //trimestre newTrime = new trimestre(2,1,"02/09/2017","15/06/2018",2);
        //trimestreDAO.update(newTrime);
        
        
         /*       
        System.out.println("SCHOOL MANAGEMENT");
        String user, mdp;
        Scanner sc = new Scanner(System.in);
        System.out.print("user : ");
        user = sc.nextLine();
        System.out.print("mdp : ");
        mdp = sc.nextLine();
        connection(user, mdp);
        */

        //find
        ArrayList<personne> allPeople = personneDAO.findAll();
        allPeople.get(0).getType();

        ArrayList<classe> ao = classeDAO.findAll();

        //RUN
        Fen dialog = new Fen();

        dialog.refreshTabClasse(ao);
        dialog.refreshTabEleve(allPeople);
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TESTfen dialog = new TESTfen(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });*/

    }
}
