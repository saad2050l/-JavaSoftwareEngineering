package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.Localisation;
import eu.telecomsudparis.csc4102.bebiloc.TypeFonction;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.CapaciteBureauErronee;
import eu.telecomsudparis.csc4102.bebiloc.exception.LocalisationNull;
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
		systeme.ajouterBureauNonPermanent(null, Localisation.EVRY, TypeFonction.NON_PERMANENT, 1, 1);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void ajouterBureauNonPermanentTest1Jeu2() throws Exception {
		systeme.ajouterBureauNonPermanent("", Localisation.EVRY, TypeFonction.NON_PERMANENT, 1, 1);
	}

	@Test(expected = LocalisationNull.class)
	public void ajouterBureauNonPermanentTest2Jeu1() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", null, TypeFonction.NON_PERMANENT, 1, 1);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void ajouterBureauNonPermanentTest3Jeu1() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", Localisation.EVRY, TypeFonction.NON_PERMANENT, 0, 0);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void ajouterBureauNonPermanentTest3Jeu2() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", Localisation.EVRY, TypeFonction.NON_PERMANENT, 4, 3);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void ajouterBureauNonPermanentTest3Jeu3() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", Localisation.EVRY, TypeFonction.NON_PERMANENT, 1, -1);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void ajouterBureauNonPermanentTest3Jeu4() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", Localisation.EVRY, TypeFonction.NON_PERMANENT, 3, 4);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void ajouterBureauNonPermanentTest3Jeu5() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", Localisation.EVRY, TypeFonction.NON_PERMANENT, -1, 1);
	}

	@Test(expected = BureauDejaExistant.class)
	public void ajouterBureauNonPermanentTest4() throws Exception {
		try {
			systeme.ajouterBureauNonPermanent("id1", Localisation.EVRY, TypeFonction.NON_PERMANENT, 4, 2);
		} catch (BureauDejaExistant e) {
			Assert.fail();
		}
		Assert.assertEquals(4, systeme.getNbFixe("id1"));
		Assert.assertEquals(2, systeme.getNbPassage("id1"));
		systeme.ajouterBureauNonPermanent("id1", Localisation.EVRY, TypeFonction.NON_PERMANENT, 4, 3);
	}

	@Test
	public void ajouterBureauNonPermanentTest5Jeu1() throws Exception {
		systeme.ajouterBureauNonPermanent("id1", Localisation.EVRY, TypeFonction.NON_PERMANENT, 3, 2);
		Assert.assertEquals(3, systeme.getNbFixe("id1"));
		Assert.assertEquals(2, systeme.getNbPassage("id1"));
	}

	@Test
	public void ajouterBureauNonPermanentTest5Jeu2() throws Exception {
		systeme.ajouterBureauNonPermanent("b1", Localisation.EVRY, TypeFonction.NON_PERMANENT, 3, 0);
		Assert.assertEquals(3, systeme.getNbFixe("id1"));
		Assert.assertEquals(0, systeme.getNbPassage("id1"));
	}
}
