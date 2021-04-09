package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.TypeFonction;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.CapaciteBureauErronee;
import eu.telecomsudparis.csc4102.bebiloc.exception.LocalisationNullOuVide;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;

public class TestAjouterBureauPourNonPermanents {

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
	public void ajouterBureauNonPermanentTest1Jeu1() throws Exception {
		systeme.ajouterBureauNonPermanent(null, "EVRY", TypeFonction.NON_PERMANENT, 1, 1);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void ajouterBureauNonPermanentTest1Jeu2() throws Exception {
		systeme.ajouterBureauNonPermanent("", "EVRY", TypeFonction.NON_PERMANENT, 1, 1);
	}

	@Test(expected = LocalisationNullOuVide.class)
	public void ajouterBureauNonPermanentTest2Jeu1() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", null, TypeFonction.NON_PERMANENT, 1, 1);
	}
	
	@Test(expected = LocalisationNullOuVide.class)
	public void ajouterBureauNonPermanentTest2Jeu2() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", "", TypeFonction.NON_PERMANENT, 1, 1);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void ajouterBureauNonPermanentTest3Jeu1() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", "EVRY", TypeFonction.NON_PERMANENT, 0, 0);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void ajouterBureauNonPermanentTest3Jeu2() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", "EVRY", TypeFonction.NON_PERMANENT, 4, 3);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void ajouterBureauNonPermanentTest3Jeu3() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", "EVRY", TypeFonction.NON_PERMANENT, 1, -1);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void ajouterBureauNonPermanentTest3Jeu4() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", "EVRY", TypeFonction.NON_PERMANENT, 3, 4);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void ajouterBureauNonPermanentTest3Jeu5() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", "EVRY", TypeFonction.NON_PERMANENT, -1, 1);
	}

	@Test(expected = BureauDejaExistant.class)
	public void ajouterBureauNonPermanentTest4() throws Exception {
		try {
			systeme.ajouterBureauNonPermanent("id1", "EVRY", TypeFonction.NON_PERMANENT, 1, 1);
		} catch (BureauDejaExistant e) {
			Assert.fail();
		}
		systeme.ajouterBureauNonPermanent("id1", "EVRY", TypeFonction.NON_PERMANENT, 1, 1);
	}

	@Test
	public void ajouterBureauNonPermanentTest5Jeu1() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", "EVRY", TypeFonction.NON_PERMANENT, 1, 1);
		Assert.assertEquals(true, systeme.getListeBureaux().containsKey("id1"));
		Assert.assertEquals(1, systeme.getListeBureaux().get("id1").getNbFixe());
		Assert.assertEquals(1, systeme.getListeBureaux().get("id1").getNbPassage());
	}
}
