// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc;

import java.util.List;

public class RendreUnBureauS1 implements StrategieRendreBureau {
	public boolean rendreBureau(List<PlaceFixe> pFO, List<PlaceDePassage> pdPO) {
		System.out.println("La strategie 1 pour rendre un bureau est prise par défaut.");
		return true;
	}
}
