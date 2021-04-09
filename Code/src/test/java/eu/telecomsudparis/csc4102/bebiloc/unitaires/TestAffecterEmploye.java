// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc.unitaires;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.Bureau;
import eu.telecomsudparis.csc4102.bebiloc.Employe;
import eu.telecomsudparis.csc4102.bebiloc.Fonction;
import eu.telecomsudparis.csc4102.bebiloc.Localisation;
import eu.telecomsudparis.csc4102.bebiloc.PlaceFixe;
import eu.telecomsudparis.csc4102.util.Datutil;
import org.junit.Assert;

public class TestAffecterEmploye {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
	public void affecterEmploye1Jeu1() throws Exception {
		Bureau bureau = new Bureau ("bur1", Localisation.EVRY, 2, 3);
		PlaceFixe placeFixe = new PlaceFixe("id1", bureau);
		placeFixe.affecterEmploye(null);
	}

    @Test(expected = IllegalArgumentException.class)
	public void affecterEmploye2Jeu1() throws Exception {
		Bureau bureau = new Bureau ("bur1", Localisation.EVRY, 2, 3);
		PlaceFixe placeFixe = new PlaceFixe("id1", bureau);
        Employe nouveau = new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT);
		placeFixe.affecterEmploye(nouveau);
        Assert.assertEquals("id1", placeFixe.getidEmp());
        Assert.assertEquals(false, placeFixe.estLibre());
	}



}

