package eu.telecomsudparis.csc4102.bebiloc.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour indiquer que la capacité d'un
 * bureau est erronée.
 * 
 * @author Denis Conan
 */
public class CapaciteBureauErronee extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public CapaciteBureauErronee(final String message) {
		super(message);
	}

}
