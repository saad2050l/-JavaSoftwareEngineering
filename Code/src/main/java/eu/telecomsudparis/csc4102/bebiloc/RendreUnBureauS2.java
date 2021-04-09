// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc;

import java.util.List;

public class RendreUnBureauS2 implements StrategieRendreBureau {
	public boolean rendreBureau(List<PlaceFixe> pFO, List<PlaceDePassage> pdPO) {
		if (!(pFO.isEmpty())) {
			return false;
		}
		System.out.println("strategie2 rendre un bureau");
		return true;
	}
}
