package eu.telecomsudparis.csc4102.bebiloc.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.Bureau;
import eu.telecomsudparis.csc4102.bebiloc.Localisation;
import eu.telecomsudparis.csc4102.bebiloc.PlaceFixe;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauInexistant;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;

public class TestPlaceFixe {
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
	public void premierConstructeurPlaceFixeTest1Jeu1() throws Exception {
		Bureau bureau = new Bureau ("bur1", Localisation.EVRY, 2, 3);
		new PlaceFixe(null, bureau);
	}


	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void premierConstructeurPlaceFixeTest1Jeu2() throws Exception {
		Bureau bureau = new Bureau ("bur1", Localisation.EVRY, 2, 3);
		new PlaceFixe("", bureau);
	}

	@Test(expected = BureauInexistant.class)
	public void premierConstructeurPlaceFixeTest2() throws Exception {
		new PlaceFixe("id1", null);
	}
}