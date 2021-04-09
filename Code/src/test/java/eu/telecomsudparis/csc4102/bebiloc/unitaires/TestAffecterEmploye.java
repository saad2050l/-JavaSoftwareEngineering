package eu.telecomsudparis.csc4102.bebiloc.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.Bureau;
import eu.telecomsudparis.csc4102.bebiloc.Localisation;
import eu.telecomsudparis.csc4102.bebiloc.PlaceFixe;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeInexistant;

public class TestAffecterEmploye {
    private BeBiloc systeme;

    @Before
    public void setUp() {
        systeme = new BeBiloc();
    }

    @After
    public void tearDown() {
        systeme = null;
    }

	@Test(expected = EmployeInexistant.class)
	public void affecterEmploye1Jeu1() throws Exception {
		Bureau bureau = new Bureau ("bur1", Localisation.EVRY, 2, 3);
		PlaceFixe placeFixe = new PlaceFixe("id1", bureau);
		placeFixe.affecterEmploye(null);
	}
}