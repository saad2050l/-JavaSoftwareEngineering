package eu.telecomsudparis.csc4102.bebiloc.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour un bureau qui n'a pas de place
 * fixe occupée par un employé.
 * 
 * @author Denis Conan
 */
public class PasPlaceFixeDansBureauPourEmploye extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public PasPlaceFixeDansBureauPourEmploye(final String message) {
		super(message);
	}
}
