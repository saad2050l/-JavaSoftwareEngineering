package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeInexistant;

public class TestAffecterPlaceFixe {
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
	public void affecterPlaceFixeTest1Jeu1() throws Exception {
		systeme.affecterPlaceFixe(null, "b1");
	}


	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void affecterPlaceFixeTest1Jeu2() throws Exception {
		systeme.affecterPlaceFixe("", "b1");
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
		systeme.affecterPlaceFixe("inexistant", "b1");
	}

}