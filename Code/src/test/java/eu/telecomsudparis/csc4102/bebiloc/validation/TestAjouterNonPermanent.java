// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.Fonction;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateEmbaucheNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinContratNonApresDateEmbauche;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinContratNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionInconnue;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionNonAdaptee;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.Datutil;

public class TestAjouterNonPermanent {

	private BeBiloc systeme;

	@Before
	public void setUp() {
		systeme = new BeBiloc();
	}

	@After
	public void tearDown() {
		systeme = null;
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurNonPermanentTest1Jeu1() throws Exception {
		systeme.ajouterNonPermanent(null, "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurNonPermanentTest1Jeu2() throws Exception {
		systeme.ajouterNonPermanent("", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurNonPermanentTest2Jeu1() throws Exception {
		systeme.ajouterNonPermanent("id1", null, "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurNonPermanentTest2Jeu2() throws Exception {
		systeme.ajouterNonPermanent("id1", "", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurNonPermanentTest3Jeu1() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", null, Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurNonPermanentTest3Jeu2() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT.getNom());
	}

	@Test(expected = DateEmbaucheNull.class)
	public void constructeurNonPermanentTest4() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", null,
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT.getNom());
	}

	@Test(expected = DateFinContratNull.class)
	public void constructeurNonPermanentTest5() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(), null, Fonction.DOCTORAT.getNom());
	}

	@Test(expected = DateFinContratNonApresDateEmbauche.class)
	public void constructeurNonPermanentTest6() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.retirerJoursADate(Datutil.aujourdhui(), 5), Fonction.DOCTORAT.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurNonPermanentTest7Jeu1() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), null);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurNonPermanentTest7Jeu2() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), "");
	}

	@Test(expected = FonctionNonAdaptee.class)
	public void constructeurNonPermanentTest8Jeu1() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DIRECTION_DEPARTEMENT.getNom());
	}

	@Test(expected = FonctionNonAdaptee.class)
	public void constructeurNonPermanentTest8Jeu2() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DIRECTION_ADJOINTE_DEPARTEMENT.getNom());
	}

	@Test(expected = FonctionNonAdaptee.class)
	public void constructeurNonPermanentTest8Jeu3() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.ASSISTANCE_GESTION.getNom());
	}

	@Test(expected = FonctionNonAdaptee.class)
	public void constructeurNonPermanentTest8Jeu4() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
	}

	@Test(expected = FonctionInconnue.class)
	public void constructeurNonPermanentTest8Jeu5() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), "inconnue");
	}

	@Test(expected = EmployeDejaExistant.class)
	public void constructeurNonPermanentTest10Puis9() throws Exception {
		try {
			systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
					Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT.getNom());
			Assert.assertEquals(1,systeme.listerEmployesNonPermanents().size());
		} catch (EmployeDejaExistant e) {
			Assert.fail();
		}
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT.getNom());
	}

	@Test
	public void constructeurNonPermanentTest10Jeu2() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.POST_DOCTORAT.getNom());
		Assert.assertEquals(1,systeme.listerEmployesNonPermanents().size());
	}

	@Test
	public void constructeurNonPermanentTest10Jeu3() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.INGENIERIE_RECHERCHE.getNom());
		Assert.assertEquals(1,systeme.listerEmployesNonPermanents().size());
	}

	@Test
	public void constructeurNonPermanentTest10Jeu4() throws Exception {
		systeme.ajouterNonPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.STAGE.getNom());
		Assert.assertEquals(1,systeme.listerEmployesNonPermanents().size());
	}

}
