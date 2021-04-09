// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc;

/**
 * Cette énumération définit les types de fonctions des occupants des bureaux.
 * 
 * @author Denis Conan
 */
public enum TypeFonction {
	/**
	 * permanent.
	 */
	PERMANENT("permanent"),
	/**
	 * non-permanent.
	 */
	NON_PERMANENT("non-permanent");

	/**
	 * le nom à afficher.
	 */
	private final String nom;

	/**
	 * constructeur avec le nom.
	 *
	 * @param nom le nom du type de la fonction.
	 */
	TypeFonction(final String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}
}
