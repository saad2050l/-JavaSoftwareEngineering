package eu.telecomsudparis.csc4102.bebiloc;
import java.util.Objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bureau {
	private final String idBureau;
	private final Localisation localisation;
	private int fixe;
	private int passage;
	private final List<Place> places;
	private TypeFonction type;
	
	/*
	 * Bureau non permanent
	 */
	public Bureau(final String idBureau, Localisation localisation, TypeFonction type, int fixe, int passage) {
		if (idBureau == null || idBureau.equals("")) {
			throw new IllegalArgumentException("le champ idBureau ne doit pas être null ou vide ");
		}
		if (localisation == null) {
			throw new IllegalArgumentException("la localisation ne doit être null");
		}
		if ((fixe + passage) <= 0 || (fixe + passage) > BeBiloc.MAX_NON_PERMANENTS_PAR_BUREAU || passage < 0 || fixe < 0) {
			throw new IllegalArgumentException("la capacité (fixe + passage) du bureau n'est pas valide");
		}
		
		
		int totalPlaces = fixe + passage;
		this.places = new ArrayList<>(totalPlaces);
		this.idBureau = idBureau;
		this.localisation = localisation;
		this.fixe = fixe;
		this.passage = passage;
		this.type = TypeFonction.NON_PERMANENT;
		creerPlaces();
		
		assert invariant();
	}
	
	/*
	 *  Bureau permanent
	 */
	public Bureau(final String idBureau, Localisation localisation, int fixe, int passage) {
		if (idBureau == null || idBureau.equals("")) {
			throw new IllegalArgumentException("le champ idBureau ne doit pas être null ou vide ");
		}
		if (localisation == null) {
			throw new IllegalArgumentException("la localisation ne doit être null");
		}
		if((fixe + passage) < 1 || (fixe + passage) > BeBiloc.MAX_PERMANENTS_PAR_BUREAU) {
			throw new IllegalArgumentException("la capacité (fixe + passage) du bureau n'est pas valide");
		}
		
		
		int totalPlaces = fixe + passage;
		this.places = new ArrayList<>(totalPlaces);
		this.idBureau = idBureau;
		this.localisation = localisation;
		this.fixe = fixe;
		this.passage = passage;
		this.type = TypeFonction.PERMANENT;
		creerPlaces();
		
		assert invariant();
	}
	
	public void creerPlaces() {
		for (int i = 0; i < fixe; i++) {
			places.add(new PlaceFixe(idBureau + "_" + i, this));
		}
		//for (int i = 0; i < (passage); i++) {
		//	places.add(new PlaceDePassage(idBureau + "_" + (i+fixe), this));
		//}
	}
	
	public Place getUnePlaceFixeLibre() {
		Place place = null;
		int i = 0;
		place = places.get(i);
		while (place instanceof PlaceFixe && ((PlaceFixe) place).estLibre() != true){
            place = places.get(i);
            i++;
        }
		return place;
	}
	
	public boolean invariant() {
		return idBureau != null && !idBureau.equals("") && localisation != null && type != null && (fixe + passage) <= 2
				&& (fixe + passage) >= 1; 
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idBureau);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj instanceof Bureau) == false) {
			return false;
		}
		Bureau other = (Bureau) obj;
		return Objects.equals(idBureau, other.idBureau);
	}

	public String getId() {
		return idBureau;
	}

	public Localisation getLocalisation() {
		return localisation;
	}

	public TypeFonction getTypeFonction() {
		return type;
	}

	public int getNbFixe() {
		return fixe;
	}
	
	public int getNbPassage() {
		return passage;
	}

	public Collection<Place> getPlaces() {
		return places;
	}
	
	
}
