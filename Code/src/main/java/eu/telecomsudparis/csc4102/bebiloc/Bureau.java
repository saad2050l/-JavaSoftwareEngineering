package eu.telecomsudparis.csc4102.bebiloc;

import java.util.Objects;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bureau {
	/**
	 * idBureau.
	 */
	private final String idBureau;
	/**
	 * Localization du bureau.
	 */
	private final Localisation localisation;
	/**
	 * nb de places fixes.
	 */
	private final int fixe;
	/**
	 * nb de places de passage.
	 */
	private final int passage;
	/**
	 * collection de places.
	 */
	private final List<Place> places;

	/**
	 * type du bureau.
	 */
	private final TypeFonction type;
	
	/**
	 * strategie rendre bureau.
	 */
	private StrategieRendreBureau strategieRendreBureau;
	
	/**
	 * Créer un bureau non permanent.
	 * 
	 * @param idBureau     id du bureau
	 * @param localisation la localisation du bureau
	 * @param type         le type du bureau
	 * @param fixe         nb de places fixe
	 * @param passage      nb de places de passage
	 */
	public Bureau(final String idBureau, final Localisation localisation, final TypeFonction type, final int fixe, final int passage) {
		if (idBureau == null || idBureau.equals("")) {
			throw new IllegalArgumentException("le champ idBureau ne doit pas être null ou vide ");
		}
		if (localisation == null) {
			throw new IllegalArgumentException("la localisation ne doit être null");
		}
		if ((fixe + passage) < 1 || (fixe + passage) > BeBiloc.MAX_NON_PERMANENTS_PAR_BUREAU || passage < 0 || fixe < 0) {
			throw new IllegalArgumentException("la capacité (fixe + passage) du bureau n'est pas valide");
		}

		int totalPlaces = fixe + passage;
		this.places = new ArrayList<>(totalPlaces);
		this.idBureau = idBureau;
		this.localisation = localisation;
		this.fixe = fixe;
		this.passage = passage;
		this.type = TypeFonction.NON_PERMANENT;
		this.strategieRendreBureau = new RendreUnBureauS1();
		
		creerPlaces();

		assert invariant();
	}

	/**
	 * Créer un bureau permanent.
	 * 
	 * @param idBureau
	 * @param localisation
	 * @param fixe
	 * @param passage
	 */
	public Bureau(final String idBureau, final Localisation localisation, final int fixe, final int passage) {
		if (idBureau == null || idBureau.equals("")) {
			throw new IllegalArgumentException("le champ idBureau ne doit pas être null ou vide ");
		}
		if (localisation == null) {
			throw new IllegalArgumentException("la localisation ne doit être null");
		}
		if ((fixe + passage) > BeBiloc.MAX_PERMANENTS_PAR_BUREAU || passage < 0 || passage >= 2 || fixe > 1) {
			throw new IllegalArgumentException("la capacité (fixe + passage) du bureau n'est pas valide");
		}

		int totalPlaces = fixe + passage;
		this.places = new ArrayList<>(totalPlaces);
		this.idBureau = idBureau;
		this.localisation = localisation;
		this.fixe = fixe;
		this.passage = passage;
		this.type = TypeFonction.PERMANENT;
		this.strategieRendreBureau = new RendreUnBureauS1();
		
		creerPlaces();

		assert invariant();
	}


	/**
	 * Creer une liste de place.
	 */
	public void creerPlaces() {
		for (int i = 0; i < fixe; i++) {
			places.add(new PlaceFixe(idBureau + "_" + i, this));
		}
		for (int i = 0; i < (passage); i++) { 
			places.add(new PlaceDePassage(idBureau + "_" + (i + fixe), this)); 
		}
	}

	/**
	 * Trouver une place fixe libre dans le bureau.
	 * 
	 * @return une place libre.
	 */
	public PlaceFixe getUnePlaceFixeLibre() {
		for (Place p : places) {
			if (p instanceof PlaceFixe) {
				if (!placesFixeOccupee().contains(p)) {
					return (PlaceFixe) p;
				}
			}
		}
		return null;
	}

	/**
	 * @return si l'invariant est validé ou non.
	 */
	public boolean invariant() {
		return idBureau != null && !idBureau.equals("") && localisation != null && type != null && (fixe + passage) <= 2
				&& (fixe + passage) >= 1;
	}
	
	/**
	 * @return l'id du bureau.
	 */
	public String getId() {
		return idBureau;
	}

	/**
	 * @return la localisation du bureau.
	 */
	public Localisation getLocalisation() {
		return localisation;
	}

	/**
	 * @return le TypeFonction du bureau.
	 */
	public TypeFonction getTypeFonction() {
		return type;
	}

	/**
	 * @return le nb de places fixes.
	 */
	public int getNbFixe() {
		return fixe;
	}

	/**
	 * @return le nb de places de passage.
	 */
	public int getNbPassage() {
		return passage;
	}

	/**
	 * @return la collection des places.
	 */
	public Collection<Place> getPlaces() {
		return places;
	}

	/**
	 * obtenir la liste des places fixes occupées.
	 * revoir boucle for
	 * @return les places fixes occupées.
	 */
	public List<PlaceFixe> placesFixeOccupee() {
		List<Place> places = (List<Place>) getPlaces();
		List<PlaceFixe> returnList = new ArrayList<>();
		for (Place place : places) {
			if (place instanceof PlaceFixe) {
				PlaceFixe placeFixe = (PlaceFixe) place;
				if (!placeFixe.estLibre()) {
					returnList.add(placeFixe);
				}
			}
		}
		return returnList;
	}


	/**
	 * obtient les places de passage qui sont ou seront occupées.
	 * revoir
	 * @return la liste des places de passage qui sont ou seront occupées.
	 */
	public List<PlaceDePassage> placesDePassageOccupeeEnCoursOuFutures() {
		List<PlaceDePassage> returnList = new ArrayList<>();
		List<Place> places = (List<Place>) getPlaces();
		for (Place place : places) {
			if (place instanceof PlaceDePassage) {
				PlaceDePassage placeDePassage = (PlaceDePassage) place;
				if (placeDePassage.estOccupee()) {
					returnList.add(placeDePassage);
				}
			}
		}
		return returnList;
	}
	
	/**
	 * obtenir une liste des identifiants des places de passage qui sont libres dans ce bureau.
	 * @param dateDebutAffectation
	 * @param dateFinAffectation
	 * @return une liste des identifiants des places de passage qui sont libres dans ce bureau.
	 */
	public List<String> placesDePassageLibreDate(final LocalDate dateDebutAffectation, final LocalDate dateFinAffectation) {
        if (dateDebutAffectation == null) {
            throw new IllegalArgumentException("la dateDebutAffectation ne peut pas être libre");
        }
        if (dateFinAffectation == null) {
            throw new IllegalArgumentException("la dateFinAffectation ne peut pas être libre");
        }
        List<String> placesLibres = new ArrayList<>();
        for (Place p : places) {
            if (p instanceof PlaceDePassage) {
                PlaceDePassage placeDePassage = (PlaceDePassage) p;
                if (!(placeDePassage.dejaOccupeeACetteDate(dateDebutAffectation, dateFinAffectation) == null)) {
                    placesLibres.add(placeDePassage.dejaOccupeeACetteDate(dateDebutAffectation, dateFinAffectation));
                }
            }
        }
        return placesLibres;
    }

	/**
	 * StrategieRendreBureau.
	 * @param s strategieChoisie
	 */
	public void setStrategieRendreBureau(final StrategieRendreBureau s) {
		this.strategieRendreBureau = s;
	}
	
	/**
	 * Methode rendre bureau.
	 * @param pFO
	 * @param pdPO
	 * @return boolean si l'action s'est passée
	 */
	public boolean rendre(final List<PlaceFixe> pFO, final List<PlaceDePassage> pdPO) {
		return strategieRendreBureau.rendreBureau(pFO, pdPO);
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
		if (!(obj instanceof Bureau)) {
			return false;
		}
		Bureau other = (Bureau) obj;
		return Objects.equals(idBureau, other.idBureau);
	}

	@Override
	public String toString() {
		return "Bureau [id=" + idBureau + ", localisation=" + localisation + ", typeOccupant=" + type + ", fixe="
				+ fixe + ", passage=" + passage + ", places=" + places + "]";
	}
}
