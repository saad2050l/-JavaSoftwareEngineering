// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.Fonction;
import eu.telecomsudparis.csc4102.bebiloc.TypeFonction;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauInexistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.CapaciteBureauErronee;
import eu.telecomsudparis.csc4102.bebiloc.exception.CapaciteDePassageErronee;
import eu.telecomsudparis.csc4102.bebiloc.exception.ConflitAvecOccupationDePassageMemeLieu;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateDebutAffectationAvantAujourdhui;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateDebutAffectationAvantDateEmbauche;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateDebutAffectationNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateEmbaucheNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinAffectationApresDateFinContrat;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinAffectationAvantDateDebutAffectation;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinAffectationNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeInexistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionInconnue;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionNonAdaptee;
import eu.telecomsudparis.csc4102.bebiloc.exception.LocalisationNullOuVide;
import eu.telecomsudparis.csc4102.bebiloc.exception.MauvaisTypeOccupantBureau;
import eu.telecomsudparis.csc4102.bebiloc.exception.OccupationDePassageAvecPlaceFixeMemeLieu;
import eu.telecomsudparis.csc4102.bebiloc.exception.PlaceManquante;
import eu.telecomsudparis.csc4102.util.Datutil;


public class TestAffecterPlaceDePassage {
    private BeBiloc systeme;

    @Before
    public void setUp() throws ChaineDeCaracteresNullOuVide, DateEmbaucheNull, FonctionInconnue, EmployeDejaExistant, FonctionNonAdaptee, EmployeInexistant, LocalisationNullOuVide, CapaciteBureauErronee, BureauDejaExistant, CapaciteDePassageErronee {
        systeme = new BeBiloc();
		systeme.ajouterBureauPermanent("idBP1", "PALAISEAU", 1, 1);
		systeme.ajouterBureauNonPermanent("idBNP1", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 1);
		systeme.ajouterPermanent("idEP1", "nom1", "prenom1", Datutil.aujourdhui(),
				Fonction.ASSISTANCE_GESTION.getNom());
    }

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void affecterPlaceDePassageTest1Jeu1() throws Exception {
		systeme.affecterPlaceDePassage(null, "idBP1", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void affecterPlaceDePassageTest1Jeu2() throws Exception {
		systeme.affecterPlaceDePassage("", "idBP1", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

    @Test(expected = EmployeInexistant.class)
	public void affecterPlaceDePassageTest2() throws Exception {
		systeme.affecterPlaceDePassage("inexistant", "idBP1", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void affecterPlaceDePassageTest3Jeu1() throws Exception {
		systeme.affecterPlaceDePassage("idEP1", null, Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void affecterPlaceDePassageTest3Jeu2() throws Exception {
		systeme.affecterPlaceDePassage("idEP1", "", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

    @Test(expected = BureauInexistant.class)
	public void affecterPlaceDePassageTest4Jeu1() throws Exception {
		systeme.affecterPlaceDePassage("idEP1", "inexistant", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

	@Test(expected = MauvaisTypeOccupantBureau.class)
	public void affecterPlaceDePassageTest5Jeu1() throws Exception {
        systeme.ajouterNonPermanent("idENP1", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.DOCTORAT.getNom());
		systeme.affecterPlaceDePassage("idENP1", "idBP1", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

    @Test(expected = MauvaisTypeOccupantBureau.class)
	public void affecterPlaceDePassageTest5Jeu2() throws Exception {
		systeme.affecterPlaceDePassage("idEP1", "idBNP1", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

    @Test(expected = DateDebutAffectationNull.class)
	public void affecterPlaceDePassageTest6Jeu1() throws Exception {
		systeme.affecterPlaceDePassage("idEP1", "idBP1", null, Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

    @Test(expected = DateDebutAffectationAvantAujourdhui.class)
	public void affecterPlaceDePassageTest7Jeu1() throws Exception {
		systeme.affecterPlaceDePassage("idEP1", "idBP1", Datutil.retirerJoursADate(Datutil.aujourdhui(),2), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

    @Test(expected = DateFinAffectationNull.class)
	public void affecterPlaceDePassageTest8Jeu1() throws Exception {
		systeme.affecterPlaceDePassage("idEP1", "idBP1", Datutil.aujourdhui(), null);
	}

    @Test(expected = DateFinAffectationAvantDateDebutAffectation.class)
	public void affecterPlaceDePassageTest9Jeu1() throws Exception {
		systeme.affecterPlaceDePassage("idEP1", "idBP1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 7));
	}

    @Test(expected = DateDebutAffectationAvantDateEmbauche.class)
	public void affecterPlaceDePassageTest10Jeu1() throws Exception {
    	systeme.ajouterNonPermanent("idEP2", "nom2", "prenom2", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 4),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 60), Fonction.DOCTORAT.getNom());
		systeme.affecterPlaceDePassage("idEP2", "idBNP1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 2), 
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 12));
	}

    @Test(expected = DateFinAffectationApresDateFinContrat.class)
	public void affecterPlaceDePassageTest11Jeu1() throws Exception {
    	systeme.ajouterNonPermanent("idEP2", "nom2", "prenom2", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 4),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 60), Fonction.DOCTORAT.getNom());
		systeme.affecterPlaceDePassage("idEP2", "idBNP1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 6), 
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 80));
	}

    @Test(expected = ConflitAvecOccupationDePassageMemeLieu.class)
	public void affecterPlaceDePassageTest12Jeu1() throws Exception {
		systeme.ajouterBureauNonPermanent("idBNP2", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 1);
		systeme.ajouterNonPermanent("idEP2", "nom2", "prenom2", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 4),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 400), Fonction.DOCTORAT.getNom());
    	systeme.affecterPlaceDePassage("idEP2", "idBNP2", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 6), 
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 360));
				systeme.affecterPlaceDePassage("idEP2", "idBNP2", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 6), 
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 360));
	}

    @Test(expected = OccupationDePassageAvecPlaceFixeMemeLieu.class)
	public void affecterPlaceDePassageTest13Jeu1() throws Exception {
		systeme.ajouterBureauPermanent("idBP2", "PALAISEAU", 1, 0);
		systeme.ajouterPermanent("idEP2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.ASSISTANCE_GESTION.getNom());

		systeme.affecterPlaceFixe("idEP2", "idBP2");
        systeme.ajouterBureauPermanent("idB3", "PALAISEAU", 1, 1);
        systeme.affecterPlaceDePassage("idEP2", "idBP2", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 10), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 22));
	}
	
	@Test(expected = PlaceManquante.class)
	public void affecterPlaceDePassageTest14Jeu1() throws Exception {
		systeme.ajouterBureauPermanent("idBP2", "PALAISEAU", 1, 0);
		systeme.ajouterPermanent("idEP2", "nom1", "prenom1", Datutil.aujourdhui(),
				Fonction.ASSISTANCE_GESTION.getNom());
		systeme.affecterPlaceDePassage("idEP2", "idBP2", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 2),
				 Datutil.ajouterJoursADate(Datutil.aujourdhui(), 12));
	}
	
	@Test
	public void affecterPlaceDePassageTest15() throws Exception {
		systeme.affecterPlaceDePassage("idEP1", "idBP1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 2), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 12));
		Assert.assertTrue( systeme.getListeBureaux().get("idBP1").placesDePassageLibreDate(Datutil.ajouterJoursADate(Datutil.aujourdhui(), 2), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 12)).isEmpty());
	}

	@After
    public void tearDown() {
        systeme = null;
    }
}
