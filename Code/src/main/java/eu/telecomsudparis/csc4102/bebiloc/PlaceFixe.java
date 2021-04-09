// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc;

public class PlaceFixe extends Place{

	/**
	 * True si la place est libre, False sinon.
	 */
	private boolean estLibre; 
	/**
	 * l'identifiant de l'employé à cette place.
	 */
	private String idEmp;
	
	/**
	 * construit une place.
	 * 
	 * @param idPlace     l'identifiant de la place.
	 * @param bureau 	le bureau correspondant.
	 */
	public PlaceFixe(final String idPlace, final Bureau bureau) {
		super(idPlace, bureau);
		if (idPlace == null || idPlace.equals("")) {
			throw new IllegalArgumentException("l'identifiant ne peut pas être null ou vide");
		}
		if (bureau==null) {
			throw new IllegalArgumentException("bureau n'existe pas");
		}
		estLibre = true;
		idEmp = null;
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
	 * obtenir l'identifiant de l'employé occupant cette place fixe.
	 * @return l'identifiant de l'employé occupant cette place fixe.
	 */
	public String getidEmp() {
		return this.idEmp;
	}

	/**
	 * retourne True si la place est libre, False sinon.
	 * 
	 * @return True si la place est libre, False sinon.
	 */
	public boolean estLibre() {
		return estLibre;
	}

	/**
	 * affecter un employé à une place fixe.
	 *
	 * @param employe	l'employé.
	 */
	public void affecterEmploye(final Employe employe) {
		if (employe == null) {
			throw new IllegalArgumentException("l'employe ne peut pas être null");
		}
		estLibre = false;
		idEmp = employe.getId();
		assert invariant();
	}

	/**
	 * retirer l'affectation en cours.
	 */
	public void retirerAffectation() {
		if (estLibre) {
			throw new IllegalStateException("essai de retrait des affections echoué");
		}
		estLibre = true;
		idEmp = null;
		assert invariant();
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
		return "PlaceFixe [getidPlace()=" + getIdPlace()+ ", getId()=" + getidEmp() + ", getBureau()=" + getBureau().getId() + ", getLocalisation()="
				+ getLocalisation() + ", estLibre=" + estLibre+ "]";
	}

}
