// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.CapaciteBureauErronee;
import eu.telecomsudparis.csc4102.bebiloc.exception.LocalisationNullOuVide;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;

public class TestAjouterBureauPourPermanents {
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
	public void ajouterBureauPermanentTest1Jeu1() throws Exception {
		systeme.ajouterBureauPermanent(null, "EVRY", 1, 0);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void ajouterBureauPermanentTest1Jeu2() throws Exception {
		systeme.ajouterBureauPermanent("", "EVRY", 1, 0);
	}

	@Test(expected = LocalisationNullOuVide.class)
	public void constructeurPermanentTest2Jeu1() throws Exception {
		systeme.ajouterBureauPermanent("id1", null, 1, 0);
	}
	
	@Test(expected = LocalisationNullOuVide.class)
	public void constructeurPermanentTest2Jeu2() throws Exception {
		systeme.ajouterBureauPermanent("id1", "", 1, 0);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void constructeurPermanentTest4Jeu1() throws Exception {
		systeme.ajouterBureauPermanent("id1", "PALAISEAU", 2, 0);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void constructeurPermanentTest4Jeu2() throws Exception {
		systeme.ajouterBureauPermanent("id1", "PALAISEAU", 1, 2);
	}
	
	@Test(expected = CapaciteBureauErronee.class)
	public void constructeurPermanentTest4Jeu3() throws Exception {
		systeme.ajouterBureauPermanent("id1", "PALAISEAU", 0, 2);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void constructeurPermanentTest4Jeu4() throws Exception {
		systeme.ajouterBureauPermanent("id1", "PALAISEAU", 0, -1);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void constructeurPermanentTest4Jeu5() throws Exception {
		systeme.ajouterBureauPermanent("id1", "PALAISEAU", -1, 2);
	}

	@Test(expected = BureauDejaExistant.class)
	public void ajouterBureauPermanentTest5() throws Exception {
		try {
			systeme.ajouterBureauPermanent("id1", "EVRY", 1, 1);
		} catch (BureauDejaExistant e) {
			Assert.fail();
		}
		systeme.ajouterBureauPermanent("id1", "EVRY", 1, 1);
	}

	@Test
	public void ajouterBureauPermanentTest6() throws Exception {
		systeme.ajouterBureauPermanent("id1", "EVRY", 1, 1);
		Assert.assertEquals(true, systeme.getListeBureaux().containsKey("id1"));
		Assert.assertEquals(1, systeme.getListeBureaux().get("id1").getNbFixe());
		Assert.assertEquals(1, systeme.getListeBureaux().get("id1").getNbPassage());
	}
}
