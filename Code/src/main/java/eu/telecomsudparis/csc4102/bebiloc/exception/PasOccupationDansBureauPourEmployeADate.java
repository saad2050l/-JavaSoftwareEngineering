package eu.telecomsudparis.csc4102.bebiloc.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour un bureau qui n'a pas de place
 * de passage occupée par un employé à une date donnée.
 * 
 * @author Denis Conan
 */
public class PasOccupationDansBureauPourEmployeADate extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public PasOccupationDansBureauPourEmployeADate(final String message) {
		super(message);
	}
}
