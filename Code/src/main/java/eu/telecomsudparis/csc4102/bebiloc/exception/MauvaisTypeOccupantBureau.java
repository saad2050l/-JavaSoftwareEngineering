package eu.telecomsudparis.csc4102.bebiloc.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour un employé qui ne correspond
 * pas au type de l'occupant d'un bureau.
 * 
 * @author Denis Conan
 */
public class MauvaisTypeOccupantBureau extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public MauvaisTypeOccupantBureau(final String message) {
		super(message);
	}
}
