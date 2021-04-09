// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.Fonction;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateEmbaucheNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionInconnue;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionNonAdaptee;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.Datutil;

public class TestAjouterPermanent {

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
	public void constructeurPermanentTest1Jeu1() throws Exception {
		systeme.ajouterPermanent(null, "nom1", "prenom1", Datutil.aujourdhui(),
				Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurPermanentTest1Jeu2() throws Exception {
		systeme.ajouterPermanent("", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurPermanentTest2Jeu1() throws Exception {
		systeme.ajouterPermanent("id1", null, "prenom1", Datutil.aujourdhui(),
				Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurPermanentTest2Jeu2() throws Exception {
		systeme.ajouterPermanent("id1", "", "prenom1", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurPermanentTest3Jeu1() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", null, Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurPermanentTest3Jeu2() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
	}

	@Test(expected = DateEmbaucheNull.class)
	public void constructeurPermanentTest4() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", null, Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurPermanentTest5Jeu1() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(), null);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurPermanentTest5Jeu2() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(), "");
	}

	@Test(expected = FonctionNonAdaptee.class)
	public void constructeurPermanentTest6Jeu1() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.DOCTORAT.getNom());
	}

	@Test(expected = FonctionNonAdaptee.class)
	public void constructeurPermanentTest6Jeu2() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.POST_DOCTORAT.getNom());
	}

	@Test(expected = FonctionNonAdaptee.class)
	public void constructeurPermanentTest6Jeu3() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.INGENIERIE_RECHERCHE.getNom());
	}

	@Test(expected = FonctionNonAdaptee.class)
	public void constructeurPermanentTest6Jeu4() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.STAGE.getNom());
	}

	@Test(expected = FonctionInconnue.class)
	public void constructeurPermanentTest6Jeu5() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(), "inconnue");
	}

	@Test(expected = EmployeDejaExistant.class)
	public void constructeurPermanentTest8Puis7() throws Exception {
		try {
			systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
					Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
			Assert.assertEquals(1,systeme.listerEmployesPermanents().size());
		} catch (EmployeDejaExistant e) {
			Assert.fail();
		}
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Fonction.ENSEIGNEMENT_RECHERCHE.getNom());
	}

	@Test
	public void constructeurPermanentTest8Jeu2() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Fonction.DIRECTION_DEPARTEMENT.getNom());
		Assert.assertEquals(1,systeme.listerEmployesPermanents().size());
	}

	@Test
	public void constructeurPermanentTest8Jeu3() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Fonction.DIRECTION_ADJOINTE_DEPARTEMENT.getNom());
		Assert.assertEquals(1,systeme.listerEmployesPermanents().size());
	}

	@Test
	public void constructeurPermanentTest8Jeu4() throws Exception {
		systeme.ajouterPermanent("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Fonction.ASSISTANCE_GESTION.getNom());
		Assert.assertEquals(1,systeme.listerEmployesPermanents().size());
	}

}
