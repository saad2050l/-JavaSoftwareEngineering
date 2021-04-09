// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.Fonction;
import eu.telecomsudparis.csc4102.bebiloc.TypeFonction;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.Datutil;
import org.junit.Assert;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauInexistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.CapaciteBureauErronee;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateEmbaucheNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeInexistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.MauvaisTypeOccupantBureau;
import eu.telecomsudparis.csc4102.bebiloc.exception.PlaceManquante;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeMaxPlaces;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionInconnue;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionNonAdaptee;
import eu.telecomsudparis.csc4102.bebiloc.exception.LocalisationNullOuVide;

public class TestAffecterPlaceFixe {
    private BeBiloc systeme;
	@Before
    public void setUp() throws ChaineDeCaracteresNullOuVide, DateEmbaucheNull, FonctionInconnue, EmployeDejaExistant, FonctionNonAdaptee, EmployeInexistant, LocalisationNullOuVide, CapaciteBureauErronee, BureauDejaExistant {
        systeme = new BeBiloc();
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.ASSISTANCE_GESTION.getNom());
		systeme.ajouterBureauPermanent("idBP1", "PALAISEAU", 1, 0);
    }

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void affecterPlaceFixeTest1Jeu1() throws Exception {
		systeme.affecterPlaceFixe(null, "idBP1");
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void affecterPlaceFixeTest1Jeu2() throws Exception {
		systeme.affecterPlaceFixe("", "idBP1");
	}


	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void affecterPlaceFixeTest2Jeu1() throws Exception {
		systeme.affecterPlaceFixe("id1", null);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void affecterPlaceFixeTest2Jeu2() throws Exception {
		systeme.affecterPlaceFixe("id1", "");
	}

	@Test(expected = EmployeInexistant.class)
	public void affecterPlaceFixeTest3() throws Exception {
		systeme.affecterPlaceFixe("inexistant", "idBP1");
	}
	
	@Test(expected = BureauInexistant.class)
	public void affecterPlaceFixeTest4() throws Exception {
		systeme.affecterPlaceFixe("id1", "inexistant");
	}
	
	@Test(expected = PlaceManquante.class)
	public void affecterPlaceFixeTest5Jeu1() throws Exception {
		systeme.ajouterBureauNonPermanent("idBNP1", "PALAISEAU", TypeFonction.NON_PERMANENT, 0, 1);
		systeme.ajouterNonPermanent("idENP1", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.DOCTORAT.getNom());
		systeme.affecterPlaceFixe("idENP1", "idBNP1");
	}
	
	@Test(expected = MauvaisTypeOccupantBureau.class)
	public void affecterPlaceFixeTest6Jeu1() throws Exception {
		systeme.ajouterNonPermanent("idENP1", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.DOCTORAT.getNom());
		systeme.affecterPlaceFixe("idENP1", "idBP1");
	}
	
	@Test(expected = MauvaisTypeOccupantBureau.class)
	public void affecterPlaceFixeTest6Jeu2() throws Exception {
		systeme.ajouterBureauNonPermanent("idBNP1", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 1);
		systeme.affecterPlaceFixe("id1", "idBNP1");
	}
	

	@Test(expected = EmployeMaxPlaces.class)
	public void affecterPlaceFixeTest7Jeu1() throws Exception {
		systeme.ajouterNonPermanent("id5", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.DOCTORAT.getNom());
		systeme.ajouterBureauNonPermanent("idBNP2", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP2");
		systeme.ajouterBureauNonPermanent("idBNP3", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP3");
	}
	@Test(expected = EmployeMaxPlaces.class)
	public void affecterPlaceFixeTest7Jeu2() throws Exception {
		systeme.ajouterNonPermanent("id5", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.POST_DOCTORAT.getNom());
		systeme.ajouterBureauNonPermanent("idBNP2", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP2");
		systeme.ajouterBureauNonPermanent("idBNP3", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP3");
	}
	@Test(expected = EmployeMaxPlaces.class)
	public void affecterPlaceFixeTest7Jeu3() throws Exception {
		systeme.ajouterNonPermanent("id5", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.INGENIERIE_RECHERCHE.getNom());
		systeme.ajouterBureauNonPermanent("idBNP2", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP2");
		systeme.ajouterBureauNonPermanent("idBNP3", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP3");
	}
	@Test(expected = EmployeMaxPlaces.class)
	public void affecterPlaceFixeTest7Jeu4() throws Exception {
		systeme.ajouterNonPermanent("id5", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.STAGE.getNom());
		systeme.ajouterBureauNonPermanent("idBNP2", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP2");
		systeme.ajouterBureauNonPermanent("idBNP3", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP3");
	}
	@Test(expected = EmployeMaxPlaces.class)
	public void affecterPlaceFixeTest7Jeu5() throws Exception {
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.ASSISTANCE_GESTION.getNom());
		systeme.affecterPlaceFixe("id2", "idBP1");
		systeme.ajouterBureauPermanent("idBP2", "EVRY", 1, 1);
		systeme.affecterPlaceFixe("id2", "idBP2");
	}
	@Test(expected = EmployeMaxPlaces.class)
	public void affecterPlaceFixeTest7Jeu6() throws Exception {
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
		systeme.affecterPlaceFixe("id2", "idBP1");
		systeme.ajouterBureauPermanent("idBP2", "EVRY", 1, 1);
		systeme.affecterPlaceFixe("id2", "idBP2");
	}
	@Test(expected = EmployeMaxPlaces.class)
	public void affecterPlaceFixeTest7Jeu7() throws Exception {
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.DIRECTION_DEPARTEMENT.getNom());
		systeme.affecterPlaceFixe("id2", "idBP1");
		systeme.ajouterBureauPermanent("idBP2", "PALAISEAU", 1, 1);
		systeme.affecterPlaceFixe("id2", "idBP2");
	}
	@Test(expected = EmployeMaxPlaces.class)
	public void affecterPlaceFixeTest7Jeu8() throws Exception {
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.DIRECTION_ADJOINTE_DEPARTEMENT.getNom());
		systeme.affecterPlaceFixe("id2", "idBP1");
		systeme.ajouterBureauPermanent("idBP2", "PALAISEAU", 1, 1);
		systeme.affecterPlaceFixe("id2", "idBP2");
	}


	@Test
	public void affecterPlaceFixeTest8Jeu1() throws Exception {
		systeme.ajouterNonPermanent("id5", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.DOCTORAT.getNom());
		systeme.ajouterBureauNonPermanent("idBNP2", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP2");
		assertion("idBNP2");
	}
	@Test
	public void affecterPlaceFixeTest8Jeu2() throws Exception {
		systeme.ajouterNonPermanent("id5", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.POST_DOCTORAT.getNom());
		systeme.ajouterBureauNonPermanent("idBNP2", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP2");
		assertion("idBNP2");
	}
	@Test
	public void affecterPlaceFixeTest8Jeu3() throws Exception {
		systeme.ajouterNonPermanent("id5", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.INGENIERIE_RECHERCHE.getNom());
		systeme.ajouterBureauNonPermanent("idBNP2", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP2");
		assertion("idBNP2");
	}
	@Test
	public void affecterPlaceFixeTest8Jeu4() throws Exception {
		systeme.ajouterNonPermanent("id5", "nom1", "prenom1", Datutil.ajouterJoursADate(Datutil.aujourdhui(), 17) ,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 3000), Fonction.STAGE.getNom());
		systeme.ajouterBureauNonPermanent("idBNP2", "PALAISEAU", TypeFonction.NON_PERMANENT, 1, 0);
		systeme.affecterPlaceFixe("id5", "idBNP2");
		assertion("idBNP2");
	}
	@Test
	public void affecterPlaceFixeTest8Jeu5() throws Exception {
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.ASSISTANCE_GESTION.getNom());
		systeme.affecterPlaceFixe("id2", "idBP1");
		assertion("idBP1");
	}
	@Test
	public void affecterPlaceFixeTest8Jeu6() throws Exception {
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
		systeme.affecterPlaceFixe("id2", "idBP1");
		assertion("idBP1");
	}
	@Test
	public void affecterPlaceFixeTest8Jeu7() throws Exception {
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.DIRECTION_DEPARTEMENT.getNom());
		systeme.affecterPlaceFixe("id2", "idBP1");
		assertion("idBP1");
	}
	@Test
	public void affecterPlaceFixeTest8Jeu8() throws Exception {
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.DIRECTION_ADJOINTE_DEPARTEMENT.getNom());
		systeme.affecterPlaceFixe("id2", "idBP1");
		assertion("idBP1");
	}
	private void assertion(String idB) {
		Assert.assertTrue(! systeme.getListeBureaux().get(idB).placesFixeOccupee().isEmpty());
	}


	@After
    public void tearDown() {
        systeme = null;
    }
	
}
