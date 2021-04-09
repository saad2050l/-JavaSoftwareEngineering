// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.Employe;
import eu.telecomsudparis.csc4102.bebiloc.Fonction;
import eu.telecomsudparis.csc4102.util.Datutil;

public class TestEmploye {

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest1Jeu1() throws Exception {
		new Employe(null, "nom1", "prenom1", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest1Jeu2() throws Exception {
		new Employe("", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest2Jeu1() throws Exception {
		new Employe("id1", null, "prenom1", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest2Jeu2() throws Exception {
		new Employe("id1", "", "prenom1", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest3Jeu1() throws Exception {
		new Employe("id1", "nom1", null, Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest3Jeu2() throws Exception {
		new Employe("id1", "nom1", "", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest4() throws Exception {
		new Employe("id1", "nom1", "prenom1", null, Fonction.ENSEIGNEMENT_RECHERCHE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest5() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest6Jeu1() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest6Jeu2() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.POST_DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest6Jeu3() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.INGENIERIE_RECHERCHE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurPermanentTest6Jeu4() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.STAGE);
	}

	@Test
	public void constructeurPermanentTest7() throws Exception {
		Employe nouveau = new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(), Fonction.ENSEIGNEMENT_RECHERCHE);
		Assert.assertNotNull(nouveau);
		Assert.assertEquals("id1", nouveau.getId());
		Assert.assertEquals("nom1", nouveau.getNom());
		Assert.assertEquals("prenom1", nouveau.getPrenom());
		Assert.assertEquals(Datutil.aujourdhui(), nouveau.getDateEmbauche());
		Assert.assertEquals(Fonction.ENSEIGNEMENT_RECHERCHE, nouveau.getFonction());
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest1Jeu1() throws Exception {
		new Employe(null, "nom1", "prenom1", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365),
				Fonction.DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest1Jeu2() throws Exception {
		new Employe("", "nom1", "prenom1", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365),
				Fonction.DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest2Jeu1() throws Exception {
		new Employe("id1", null, "prenom1", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365),
				Fonction.DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest2Jeu2() throws Exception {
		new Employe("id1", "", "prenom1", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365),
				Fonction.DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest3Jeu1() throws Exception {
		new Employe("id1", "nom1", null, Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365),
				Fonction.DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest3Jeu2() throws Exception {
		new Employe("id1", "nom1", "", Datutil.aujourdhui(), Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365),
				Fonction.DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest4() throws Exception {
		new Employe("id1", "nom1", "prenom1", null, Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365),
				Fonction.DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest5() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(), null, Fonction.DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest6() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(), Datutil.retirerJoursADate(Datutil.aujourdhui(), 5),
				Fonction.DOCTORAT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest7() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest8Jeu1() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DIRECTION_DEPARTEMENT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest8Jeu2() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DIRECTION_ADJOINTE_DEPARTEMENT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest8Jeu3() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.ASSISTANCE_GESTION);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurNonPermanentTest8Jeu4() throws Exception {
		new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.ENSEIGNEMENT_RECHERCHE);
	}

	@Test
	public void constructeurNonPermanentTest9() throws Exception {
		Employe nouveau = new Employe("id1", "nom1", "prenom1", Datutil.aujourdhui(),
				Datutil.ajouterJoursADate(Datutil.aujourdhui(), 365), Fonction.DOCTORAT);
		Assert.assertNotNull(nouveau);
		Assert.assertEquals("id1", nouveau.getId());
		Assert.assertEquals("nom1", nouveau.getNom());
		Assert.assertEquals("prenom1", nouveau.getPrenom());
		Assert.assertEquals(Datutil.aujourdhui(), nouveau.getDateEmbauche());
		Assert.assertEquals(Fonction.DOCTORAT, nouveau.getFonction());
	}

}
