// CHECKSTYLE:OFF

package eu.telecomsudparis.csc4102.bebiloc;

import eu.telecomsudparis.csc4102.util.IntervalleDates;

public class OccupationDePassage {
	/**
	 * intervalle de dates.
	 */
	private final IntervalleDates intervalle;
	/**
	 * place de passage correspondante.
	 */
	private final PlaceDePassage place;
	/**
	 * employé occupant.
	 */
	private final Employe employe;


	/**
	 * construit une occupation de passage.
	 * 
	 * @param intervalle        intervalle de dates.
	 * @param place             place de passage correspondante.
	 * @param employe           employé occupant.
	 */
	public OccupationDePassage(final IntervalleDates intervalle, final PlaceDePassage place, final Employe employe) {
		if (intervalle == null) {
			throw new IllegalArgumentException("l'intervalle d'occupation ne peut pas être null");
		}
		if (place == null) {
			throw new IllegalArgumentException("la place de passage ne peut pas être null");
		}
		if (employe == null) {
			throw new IllegalArgumentException("l'employé ne peut pas être null");
		}
		this.intervalle = intervalle;
		this.place = place;
		this.employe = employe;
		assert invariant();
	}

	/**
	 * vérifie l'invariant.
	 * 
	 * @return vrai si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return intervalle != null && place != null;
	}

	/**
	 * obtient l'intervalle de dates.
	 * 
	 * @return l'intervalle.
	 */
	public IntervalleDates getIntervalle() {
		return intervalle;
	}

	/**
	 * obtient la place de passage.
	 * 
	 * @return la place de passage.
	 */
	public PlaceDePassage getPlace() {
		return place;
	}

	/**
	 * retourne vrai si la date donnée est dans l'intervalle de l'occupation.
	 * 
	 * @param date la date donnée.
	 * @return vrai si la date est dans l'intervalle de l'occupation.
	 */

	/**
	 * 
	 * @return l'employé qui a reservé l'occupation
	 */
	public Employe getEmploye() {
		return this.employe;
	}

	/**
	 * retire l'occupation.
	 */
	public void retirerOccupation() {
		
	}


	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OccupationDePassage other = (OccupationDePassage) obj;
		if (employe == null) {
			if (other.employe != null) {
				return false;
			}
		} else if (!employe.equals(other.employe)) {
			return false;
		}
		if (intervalle == null) {
			if (other.intervalle != null) {
				return false;
			}
		} else if (!intervalle.equals(other.intervalle)) {
			return false;
		}
		if (place == null) {
			if (other.place != null) {
				return false;
			}
		} else if (!place.equals(other.place)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (employe != null) {
			result = prime * result + employe.hashCode();
		}
		if (intervalle != null) {
			result = prime * result + intervalle.hashCode();
		}
		if (place != null) {
			result = prime * result + place.hashCode();
		}
		return result;
	}

	@Override
	public String toString() {
		return "OccupationDePassage [intervalle=" + intervalle + ", place=" + place.getIdPlace() + ", employe="
				+ employe.getId() + "]";
	}
}
