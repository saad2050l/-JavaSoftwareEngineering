package eu.telecomsudparis.csc4102.bebiloc;

import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionInconnue;

/**
 * Cette énumération définit les fonctions des permanents.
 * 
 * @author Denis Conan
 */
public enum Fonction {
	/**
	 * direction de département.
	 */
	DIRECTION_DEPARTEMENT("direction de département", TypeFonction.PERMANENT),
	/**
	 * direction adjointe de département.
	 */
	DIRECTION_ADJOINTE_DEPARTEMENT("direction adjointe de département", TypeFonction.PERMANENT),
	/**
	 * assistance de gestion.
	 */
	ASSISTANCE_GESTION("assistance de gestion", TypeFonction.PERMANENT),
	/**
	 * enseignement et recherche.
	 */
	ENSEIGNEMENT_RECHERCHE("enseignement recherche", TypeFonction.PERMANENT),
	/**
	 * post-doctorat, c'est-à-dire en contrat à durée déterminée après la thèse.
	 */
	POST_DOCTORAT("post-doctorat", TypeFonction.NON_PERMANENT),
	/**
	 * doctorat, c'est-à-dire en thèse.
	 */
	DOCTORAT("doctorat", TypeFonction.NON_PERMANENT),
	/**
	 * ingénierie recherche.
	 */
	INGENIERIE_RECHERCHE("ingénierie recherche", TypeFonction.NON_PERMANENT),
	/**
	 * en stage.
	 */
	STAGE("stage", TypeFonction.NON_PERMANENT);

	/**
	 * le nom à afficher.
	 */
	private final String nom;
	/**
	 * le type de fonction.
	 */
	private final TypeFonction typeFonction;

	/**
	 * constructeur avec le nom.
	 * 
	 * @param nom          le nom de la fonction.
	 * @param typeFonction le type de la fonction.
	 */
	Fonction(final String nom, final TypeFonction typeFonction) {
		if (nom == null || nom.equals("")) {
			throw new IllegalArgumentException("le nom ne peut pas être null ou vide");
		}
		if (typeFonction == null) {
			throw new IllegalArgumentException("le type de la fonction ne peut pas être null ou vide");
		}
		this.nom = nom;
		this.typeFonction = typeFonction;
	}

	/**
	 * obtient le nom de l'énumérateur, c'est-à-dire le nom de la fonction.
	 * 
	 * @return le nom de la fonction.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * obtient le type de la fonction.
	 * 
	 * @return de type de la fonction.
	 */
	public TypeFonction getTypeFonction() {
		return typeFonction;
	}

	/**
	 * obtient l'énumérateur à partir de la chaîne de caractères.
	 * 
	 * @param nom le nom de la fonction.
	 * @return l'énumérateur correspondant.
	 * @throws FonctionInconnue la fonction est inconnue.
	 */
	public static Fonction getFonction(final String nom) throws FonctionInconnue {
		if (DIRECTION_DEPARTEMENT.nom.equals(nom)) {
			return DIRECTION_DEPARTEMENT;
		} else if (DIRECTION_ADJOINTE_DEPARTEMENT.nom.equals(nom)) {
			return DIRECTION_ADJOINTE_DEPARTEMENT;
		} else if (ASSISTANCE_GESTION.nom.equals(nom)) {
			return ASSISTANCE_GESTION;
		} else if (ENSEIGNEMENT_RECHERCHE.nom.equals(nom)) {
			return ENSEIGNEMENT_RECHERCHE;
		} else if (POST_DOCTORAT.nom.equals(nom)) {
			return POST_DOCTORAT;
		} else if (DOCTORAT.nom.equals(nom)) {
			return DOCTORAT;
		} else if (INGENIERIE_RECHERCHE.nom.equals(nom)) {
			return INGENIERIE_RECHERCHE;
		} else if (STAGE.nom.equals(nom)) {
			return STAGE;
		} else {
			throw new FonctionInconnue("le nom ne correspond pas à une fonction (" + nom + ")");
		}
	}

	/**
	 * retourne vrai si manager, c.-à-d. {@link #DIRECTION_DEPARTEMENT} ou
	 * {@link DIRECTION_ADJOINTE_DEPARTEMENT}.
	 * 
	 * @return vrai si manager
	 */
	public boolean estManager() {
		return this.equals(DIRECTION_DEPARTEMENT) || this.equals(DIRECTION_ADJOINTE_DEPARTEMENT);
	}

	/**
	 * retourne vrai si permanent, c.-à-d. {@link #DIRECTION_DEPARTEMENT},
	 * {@link DIRECTION_ADJOINTE_DEPARTEMENT}, {@link #ASSISTANCE_GESTION}, ou
	 * {@link #ENSEIGNEMENT_RECHERCHE}.
	 * 
	 * @return vrai si permanent.
	 */
	public boolean estPermanent() {
		return this.equals(DIRECTION_DEPARTEMENT) || this.equals(DIRECTION_ADJOINTE_DEPARTEMENT)
				|| this.equals(ASSISTANCE_GESTION) || this.equals(ENSEIGNEMENT_RECHERCHE);
	}

	/**
	 * retourne vrai si manager, c.-à-d. {@link #DIRECTION_DEPARTEMENT} ou
	 * {@link DIRECTION_ADJOINTE_DEPARTEMENT}.
	 * 
	 * @param nom le nom de la fonction.
	 * @return vrai si manager
	 */
	public static boolean estManager(final String nom) {
		return DIRECTION_DEPARTEMENT.nom.equals(nom) || DIRECTION_ADJOINTE_DEPARTEMENT.nom.equals(nom);
	}

	/**
	 * retourne vrai si permanent, c.-à-d. {@link #DIRECTION_DEPARTEMENT},
	 * {@link DIRECTION_ADJOINTE_DEPARTEMENT}, {@link #ASSISTANCE_GESTION}, ou
	 * {@link #ENSEIGNEMENT_RECHERCHE}.
	 * 
	 * @param nom le nom de la fonction.
	 * @return vrai si permanent.
	 */
	public static boolean estPermanent(final String nom) {
		return DIRECTION_DEPARTEMENT.nom.equals(nom) || DIRECTION_ADJOINTE_DEPARTEMENT.nom.equals(nom)
				|| ASSISTANCE_GESTION.nom.equals(nom) || ENSEIGNEMENT_RECHERCHE.nom.equals(nom);
	}

	@Override
	public String toString() {
		return nom + " (" + typeFonction + ")";
	}
}
