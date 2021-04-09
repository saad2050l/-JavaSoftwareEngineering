// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.bebiloc.Bureau;
import eu.telecomsudparis.csc4102.bebiloc.Localisation;
import eu.telecomsudparis.csc4102.bebiloc.PlaceFixe;

public class TestPlaceFixe {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

	@Test(expected = IllegalArgumentException.class)
	public void premierConstructeurPlaceFixeTest1Jeu1() throws Exception {
		Bureau bureau = new Bureau ("bur1", Localisation.EVRY, 1, 0);
		new PlaceFixe(null, bureau);
	}


	@Test(expected = IllegalArgumentException.class)
	public void premierConstructeurPlaceFixeTest1Jeu2() throws Exception {
		Bureau bureau = new Bureau ("bur1", Localisation.EVRY, 1, 0);
		new PlaceFixe("", bureau);
	}

	@Test(expected = IllegalArgumentException.class)
	public void premierConstructeurPlaceFixeTest2() throws Exception {
		new PlaceFixe("id1", null);
	}

	@Test
	public void premierConstructeurPlaceFixeTest3() throws Exception {
		Bureau bureau = new Bureau ("bur1", Localisation.EVRY, 1, 0);
		PlaceFixe place = new PlaceFixe("id1", bureau);
		Assert.assertNotNull(place);
		Assert.assertEquals("id1", place.getIdPlace());
		Assert.assertEquals("bur1", place.getBureau().getId());
	}

}
