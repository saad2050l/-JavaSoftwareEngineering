package eu.telecomsudparis.csc4102.bebiloc.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour une occupation de passage avec
 * place fixe déjà existante sur le même lieu.
 * 
 * @author Denis Conan
 */
public class OccupationDePassageAvecPlaceFixeMemeLieu extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public OccupationDePassageAvecPlaceFixeMemeLieu(final String message) {
		super(message);
	}
}
