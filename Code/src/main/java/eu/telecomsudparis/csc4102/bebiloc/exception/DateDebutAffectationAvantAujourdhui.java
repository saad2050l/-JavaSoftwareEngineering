package eu.telecomsudparis.csc4102.bebiloc.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour indiquer que la date de début
 * d'affectation est par erreur {@code null}.
 * 
 * @author Denis Conan
 */
public class DateDebutAffectationAvantAujourdhui extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public DateDebutAffectationAvantAujourdhui(final String message) {
		super(message);
	}

}
