package eu.telecomsudparis.csc4102.bebiloc;
import java.util.Objects;


public class Place {
	private final String idPlace;
	private final Bureau bureau;
	
	public Place(final String idPlace, final Bureau bureau) {
		this.idPlace = idPlace;
		this.bureau = bureau;
	}
	
	public String getIdPlace() {
		return idPlace;
	}
	
	public Bureau getBureau() {
		return bureau;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idPlace);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj instanceof Place) == false) {
			return false;
		}
		Place other = (Place) obj;
		return Objects.equals(idPlace, other.idPlace);
	}
}
