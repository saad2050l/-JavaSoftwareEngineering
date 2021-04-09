package eu.telecomsudparis.csc4102.bebiloc.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour un conflit d'occupation de
 * passage sur le même lieu.
 * 
 * @author Denis Conan
 */
public class ConflitAvecOccupationDePassageMemeLieu extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public ConflitAvecOccupationDePassageMemeLieu(final String message) {
		super(message);
	}
}
