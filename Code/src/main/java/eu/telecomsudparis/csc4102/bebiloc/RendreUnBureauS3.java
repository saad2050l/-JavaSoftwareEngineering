// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc;

import java.util.List;

public class RendreUnBureauS3 implements StrategieRendreBureau {
	public boolean rendreBureau(List<PlaceFixe> pFO, List<PlaceDePassage> pdPO) {
		if (!(pFO.isEmpty()) || !(pdPO.isEmpty())) {
			return false;
		}
		System.out.println("strategie3 rendre un bureau");
		return true;
	}
}
