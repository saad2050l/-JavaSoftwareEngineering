package eu.telecomsudparis.csc4102.bebiloc.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour un employé qui est déjà au
 * maximum de ses places fixes.
 * 
 * @author Denis Conan
 */
public class EmployeMaxPlaces extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public EmployeMaxPlaces(final String message) {
		super(message);
	}
}
