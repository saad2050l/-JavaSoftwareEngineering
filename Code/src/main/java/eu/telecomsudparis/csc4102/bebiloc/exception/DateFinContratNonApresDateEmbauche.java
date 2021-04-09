package eu.telecomsudparis.csc4102.bebiloc.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour indiquer que la date de fin de contrat,
 * par erreur, n'est par après la date d'embauche.
 * 
 * @author Denis Conan
 */
public class DateFinContratNonApresDateEmbauche extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public DateFinContratNonApresDateEmbauche(final String message) {
		super(message);
	}

}
