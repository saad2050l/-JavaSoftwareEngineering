// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.BeBiloc;
import eu.telecomsudparis.csc4102.bebiloc.Fonction;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauInexistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateEmbaucheNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeInexistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionInconnue;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionNonAdaptee;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.Datutil;
import org.junit.Assert;

public class TestRendreBureau {

	private BeBiloc systeme;

	@Before
	public void setUp() throws ChaineDeCaracteresNullOuVide, EmployeInexistant, DateEmbaucheNull, FonctionInconnue, EmployeDejaExistant, FonctionNonAdaptee {
		systeme = new BeBiloc();
		systeme.ajouterPermanent("id2", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.DIRECTION_ADJOINTE_DEPARTEMENT.getNom());
		
	}

	@After
	public void tearDown() {
		systeme = null;
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void rendreBureauTest1Jeu1() throws Exception {
		systeme.rendreBureau(null);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void rendreBureauTest1Jeu2() throws Exception {
		systeme.rendreBureau("");
	}

	@Test(expected = BureauInexistant.class)
	public void rendreBureauTest2Jeu1() throws Exception {
		systeme.rendreBureau("idBP1");
	}
    
    @Test
	public void rendreBureauTest() throws Exception {
		systeme.ajouterBureauPermanent("idBP1", "PALAISEAU", 1, 1);
		systeme.affecterPlaceFixe("id2", "idBP1");
		systeme.ajouterPermanent("id3", "nom1", "prenom1", Datutil.aujourdhui(), 
				Fonction.DIRECTION_ADJOINTE_DEPARTEMENT.getNom());
		systeme.affecterPlaceDePassage("id3", "idBP1", 
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 2), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 12));
		
		systeme.rendreBureau("idBP1");
		Assert.assertEquals(null, systeme.getListeBureaux().get("idBP1"));
	}

}
