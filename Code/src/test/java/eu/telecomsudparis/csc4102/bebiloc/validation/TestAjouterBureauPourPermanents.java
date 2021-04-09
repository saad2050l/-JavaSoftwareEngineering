package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.Localisation;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.CapaciteBureauErronee;
import eu.telecomsudparis.csc4102.bebiloc.exception.LocalisationNull;
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
		systeme.ajouterBureauPermanent(null, Localisation.EVRY, 1, 0);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void ajouterBureauPermanentTest1Jeu2() throws Exception {
		systeme.ajouterBureauPermanent("", Localisation.EVRY, 1, 0);
	}

	@Test(expected = LocalisationNull.class)
	public void constructeurNonPermanentTest2Jeu1() throws Exception {
		systeme.ajouterBureauPermanent("id1", null, 1, 0);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void constructeurNonPermanentTest4Jeu1() throws Exception {
		systeme.ajouterBureauPermanent("id1", Localisation.PALAISEAU, 2, 0);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void constructeurNonPermanentTest4Jeu2() throws Exception {
		systeme.ajouterBureauPermanent("id1", Localisation.PALAISEAU, 1, 2);
	}
	
	@Test(expected = CapaciteBureauErronee.class)
	public void constructeurNonPermanentTest4Jeu3() throws Exception {
		systeme.ajouterBureauPermanent("id1", Localisation.PALAISEAU, 0, 2);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void constructeurNonPermanentTest4Jeu4() throws Exception {
		systeme.ajouterBureauPermanent("id1", Localisation.PALAISEAU, 0, -1);
	}

	@Test(expected = CapaciteBureauErronee.class)
	public void constructeurNonPermanentTest4Jeu5() throws Exception {
		systeme.ajouterBureauPermanent("id1", Localisation.PALAISEAU, -1, 1);
	}


	@Test(expected = BureauDejaExistant.class)
	public void ajouterBureauPermanentTest5() throws Exception {
		try {
			systeme.ajouterBureauPermanent("id1", Localisation.PALAISEAU, 1, 1);
		} catch (BureauDejaExistant e) {
			Assert.fail();
		}
		Assert.assertEquals(1, systeme.getNbFixe("id1")); 
		Assert.assertEquals(1, systeme.getNbPassage("id1"));						
		systeme.ajouterBureauPermanent("id1", Localisation.PALAISEAU, 1, 1);
	}

	@Test
	public void ajouterBureauPermanentTest6Jeu1() throws Exception {
		systeme.ajouterBureauPermanent("id1", Localisation.PALAISEAU, 1, 0);
		Assert.assertEquals(1, systeme.getNbFixe("id1"));
		Assert.assertEquals(0, systeme.getNbPassage("id1"));
	}

	@Test
	public void ajouterBureauPermanentTest6Jeu2() throws Exception {
		systeme.ajouterBureauPermanent("id1", Localisation.PALAISEAU, 1, 1);
		Assert.assertEquals(1, systeme.getNbFixe("id1"));
		Assert.assertEquals(1, systeme.getNbPassage("id1"));
	}
}