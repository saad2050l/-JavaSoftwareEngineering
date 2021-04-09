// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.Fonction;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauInexistant;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.Datutil;

public class TestRendreUnBureauS1{

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
	public void rendreUnBureauS1Test1Jeu1() throws Exception {
		systeme.rendreBureauS1(null);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void rendreUnBureauS1Test1Jeu2() throws Exception {
		systeme.rendreBureauS1("");
	}

	@Test(expected = BureauInexistant.class)
	public void rendreUnBureauS1Test2Jeu1() throws Exception {
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.DIRECTION_ADJOINTE_DEPARTEMENT.getNom());
		systeme.rendreBureauS1("idBP1");
	}
	
	@Test
	public void rendreUnBureauS1Test() throws Exception {
		systeme.ajouterBureauPermanent("idBP1", "PALAISEAU", 1, 1);
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.DIRECTION_ADJOINTE_DEPARTEMENT.getNom());
		systeme.affecterPlaceFixe("id2", "idBP1");
		systeme.ajouterPermanent("id3", "nom1", "prenom1", Datutil.aujourdhui(), 
				Fonction.DIRECTION_ADJOINTE_DEPARTEMENT.getNom());
		systeme.affecterPlaceDePassage("id3", "idBP1", 
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 2), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 12));
		
		systeme.rendreBureauS1("idBP1");
		Assert.assertEquals(null, systeme.getListeBureaux().get("idBP1"));
	}

}
