// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc;

import java.time.LocalDate;
import java.util.ArrayList;
import eu.telecomsudparis.csc4102.util.Datutil;
import eu.telecomsudparis.csc4102.util.IntervalleDates;

public class PlaceDePassage extends Place{
	/**
	 * les occupations de cette place de passage.
	 */
	private final ArrayList<OccupationDePassage> occupations;
	
	public PlaceDePassage(final String idPlace, final Bureau bureau) {
		super(idPlace, bureau);
		occupations = new ArrayList<>();
		assert invariant();
	}
	
	/**
	 * retourne vrai si l'invariant est respecté.
	 * 
	 * @return vrai si l'invariant est respecté.
	 */
	public boolean invariant() {
		return this.getIdPlace() != null && !this.getIdPlace().equals("");
	}

	/**
	 * retourne les occupations.
	 * 
	 * @return la liste des occupations.
	 */
	public ArrayList<OccupationDePassage> getOccupations() {
		return occupations;
	}

	/**
	 * retourne vrai si cette place de passage est occupée ou le sera.
	 * 
	 * @param date la date donnée.
	 * @return vrai si la place est occupée ou le sera.
	 */
	public boolean estOccupee() {
		boolean returnBool = false;
		for (OccupationDePassage occupation : occupations) {
			if (Datutil.dateEstApresOuAujourdhui(occupation.getIntervalle().getDateFin())){
				returnBool = true;
			}
		}
		return returnBool;
	}

	/**
	 * ajoute la nouvelle occupation.
	 * 
	 * @param nouvelleOccupation la nouvelle occupation.
	 */
	public void ajouterOccupationDePassage(final OccupationDePassage nouvelleOccupation) {
		if (nouvelleOccupation == null) {
			throw new IllegalArgumentException("la nouvelle occupation ne peut pas être null");
		}
		occupations.add(nouvelleOccupation);
	}

	/**
	 * retirer toutes les affectations de la place de passage. 
	 * 
	 */
	public void retirerOccupations() {
		occupations.clear();
	}

	/**
	 * retourne l'identifiant de la place s'il n'y a pas d'intersections de dates.
	 * 
	 * @param dateDebutAffectation
	 * @param dateFinAffectation
	 * @return l'identifiant de la place s'il n'y a pas d'intersections de dates
	 */
	public String dejaOccupeeACetteDate(final LocalDate dateDebutAffectation, final LocalDate dateFinAffectation) {
        if (dateDebutAffectation == null) {
            throw new IllegalArgumentException("la dateDebutAffectation ne peut pas être libre");
        }
        if (dateFinAffectation == null) {
            throw new IllegalArgumentException("la dateFinAffectation ne peut pas être libre");
        }
        IntervalleDates intervalleDemande = new IntervalleDates(dateDebutAffectation, dateFinAffectation);
        for (OccupationDePassage occupation : occupations) {
            if (occupation.getIntervalle().intervalleDatesSIntersectent(intervalleDemande)) {
                    return null;
            }
        }
        return this.getIdPlace();
    }

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "PlaceDePassage [toString()=" + super.toString() + ", occupations=" + occupations + "]";
	}

}
