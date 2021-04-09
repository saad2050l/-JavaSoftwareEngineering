package eu.telecomsudparis.csc4102.bebiloc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import eu.telecomsudparis.csc4102.bebiloc.exception.BureauDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauInexistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.CapaciteBureauErronee;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateEmbaucheNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinContratNonApresDateEmbauche;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinContratNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeInexistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeMaxPlaces;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionInconnue;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionNonAdaptee;
import eu.telecomsudparis.csc4102.bebiloc.exception.LocalisationNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.MauvaisTypeOccupantBureau;
import eu.telecomsudparis.csc4102.bebiloc.exception.PlaceManquante;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.Datutil;

/**
 * Cette classe définit la façade du système.
 * 
 * @author Denis Conan
 */
public class BeBiloc {
	/**
	 * maximum de permanents dans un bureau de permanents. Cf. Les trois mois dans
	 * le cahier des charges.
	 */
	public static final int MAX_PERMANENTS_PAR_BUREAU = 2;
	/**
	 * maximum de non-permanents dans un bureau de non-permanents.
	 */
	public static final int MAX_NON_PERMANENTS_PAR_BUREAU = 6;
	/**
	 * le nombre de jours maximum sans avoir besoin de validation de l'affectation
	 * pour les places de passage et pour les non-permanents.
	 */
	public static final int MAX_NB_JOURS_SANS_VALIDATION_NON_PERMANENTS = 7;
	/**
	 * le nombre de jours maximum sans avoir besoin de validation de l'affectation
	 * pour les places de passage et pour les fonctions assistance gestion et
	 * enseignement recherche.
	 */
	public static final int MAX_NB_JOURS_SANS_VALIDATION_AG_ER = 90;
	/**
	 * la collection des employés.
	 */
	private Map<String, Employe> employes;

	/**
	 * collection listeBureaux
	 */
	private Map<String, Bureau> listeBureaux;
	
	/**
	 * construit la façade.
	 */
	public BeBiloc() {
		employes = new HashMap<>();
		assert invariant();
	}

	/**
	 * teste si l'invariant est vérifié.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return employes != null;
	}

	/**
	 * ajouter un employé permanent.
	 * 
	 * @param id           l'identifiant.
	 * @param nom          le nom.
	 * @param prenom       le prénom.
	 * @param dateEmbauche la date d'embauche.
	 * @param fonction     la fonction de permanent.
	 * @throws ChaineDeCaracteresNullOuVide l'identifiant du permanent ou le nom de la
	 *                                      fonction est null ou vide.
	 * @throws DateEmbaucheNull             la date d'embauche est null.
	 * @throws FonctionInconnue             la fonction est null.
	 * @throws EmployeDejaExistant          l'employé avec cet identifiant existe
	 *                                      déjà.
	 * @throws FonctionNonAdaptee           la fonction n'est pas une fonction de
	 *                                      permanent.
	 */
	public void ajouterPermanent(final String id, final String nom, final String prenom, final LocalDate dateEmbauche,
			final String fonction) throws ChaineDeCaracteresNullOuVide, DateEmbaucheNull, FonctionInconnue,
			EmployeDejaExistant, FonctionNonAdaptee {
		if (id == null || id.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("l'identifiant du permanent ne peut pas être null ou vide");
		}
		if (nom == null || nom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le prénom ne peut pas être null ou vide");
		}
		if (dateEmbauche == null) {
			throw new DateEmbaucheNull("la date d'embauche ne peut pas être null");
		}
		if (fonction == null || fonction.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("la fonction du permanent ne peut pas être null ou vide");
		}
		Fonction f = Fonction.getFonction(fonction);
		if (!f.estPermanent()) {
			throw new FonctionNonAdaptee("erreur de fonction pour un permanent");
		}
		if (employes.containsKey(id)) {
			throw new EmployeDejaExistant("employé avec identifiant '" + id + "'");
		}
		Employe nouveau = new Employe(id, nom, prenom, dateEmbauche, f);
		employes.put(id, nouveau);
		assert invariant();
	}

	/**
	 * ajouter un employé non-permanent.
	 * 
	 * @param id             l'identifiant.
	 * @param nom            le nom.
	 * @param prenom         le prénom.
	 * @param dateEmbauche   la date d'embauche.
	 * @param dateFinContrat la date de fin de contrat.
	 * @param fonction       la fonction de permanent.
	 * @throws ChaineDeCaracteresNullOuVide       l'identifiant du non-permanent ou le nom
	 *                                            de la fonction est null ou vide.
	 * @throws DateEmbaucheNull                   la date d'embauche est null.
	 * @throws DateFinContratNull                 la date de fin de contrat est
	 *                                            null.
	 * @throws DateFinContratNonApresDateEmbauche la date de fin de contrat n'est
	 *                                            pas après la date d'embauche.
	 * @throws FonctionInconnue                   la fonction est null.
	 * @throws EmployeDejaExistant                l'employé avec cet identifiant
	 *                                            existe déjà.
	 * @throws FonctionNonAdaptee                 la fonction n'est pas adaptée à un
	 *                                            non-permanent.
	 */
	public void ajouterNonPermanent(final String id, final String nom, final String prenom,
			final LocalDate dateEmbauche, final LocalDate dateFinContrat, final String fonction)
			throws ChaineDeCaracteresNullOuVide, DateEmbaucheNull, DateFinContratNull,
			DateFinContratNonApresDateEmbauche, FonctionInconnue, EmployeDejaExistant, FonctionNonAdaptee {
		if (id == null || id.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("l'identifiant du non-permanent ne peut pas être null ou vide");
		}
		if (nom == null || nom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le prénom ne peut pas être null ou vide");
		}
		if (dateEmbauche == null) {
			throw new DateEmbaucheNull("la date d'embauche ne peut pas être null");
		}
		if (dateFinContrat == null) {
			throw new DateFinContratNull("la date de fin de contrat ne peut pas être null");
		}
		if (Datutil.dateEstAvant(dateFinContrat, dateEmbauche)) {
			throw new DateFinContratNonApresDateEmbauche(
					"la date de fin de contrat doit être après la date d'embauche");
		}
		if (fonction == null || fonction.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("la fonction du permanent ne peut pas être null ou vide");
		}
		Fonction f = Fonction.getFonction(fonction);
		if (f.estPermanent()) {
			throw new FonctionNonAdaptee("erreur de fonction pour un non-permanent");
		}
		if (employes.containsKey(id)) {
			throw new EmployeDejaExistant("employé avec identifiant '" + id + "'");
		}
		Employe nouveau = new Employe(id, nom, prenom, dateEmbauche, dateFinContrat, f);
		employes.put(id, nouveau);
		assert invariant();
	}
	
	/**
	 * ajouter un bureau permanent 
	 * 
	 * @param idBureau          l'identifiant du bureau.
	 * @param localisation      la localisation du bureau.
	 * @param type		        le type du bureau du bureau.
	 * @param fixe				nb de places fixes
	 * @param passage			nb de places de passage
	 * 
	 * 
	 * @throws ChaineDeCaracteresNullOuVide idBureau null ou vide
	 * @throws LocalisationNull             localisation null
	 * @throws CapaciteBureauErronee        probleme avec les variables fixe/passage
	 * @throws BureauDejaExistant           le bureau existe deja
	 */
	
	public void ajouterBureauPermanent(final String idBureau, final Localisation localisation, final int fixe, final int passage) 
			throws ChaineDeCaracteresNullOuVide, LocalisationNull, CapaciteBureauErronee, BureauDejaExistant {
		if (idBureau == null || idBureau.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le champ idBureau ne doit pas être null ou vide ");
		}
		if (localisation == null) {
			throw new ChaineDeCaracteresNullOuVide("la localisation ne doit être null");
		}
		if((fixe + passage) < 1 || (fixe + passage) > BeBiloc.MAX_PERMANENTS_PAR_BUREAU) {
			throw new CapaciteBureauErronee("la capacité (fixe + passage) du bureau n'est pas valide");
		}
		
		Bureau nouveauBureau = new Bureau(idBureau, localisation, fixe, passage);
		listeBureaux.put(idBureau, nouveauBureau);
		assert invariant();
	}
	
	
	/**
	 * ajouter un bureau non permanent (en fonction de type)
	 * 
	 * @param idBureau          l'identifiant du bureau.
	 * @param localisation      la localisation du bureau.
	 * @param type		        le type du bureau du bureau.
	 * @param fixe				nb de places fixes
	 * @param passage			nb de places de passage
	 * 
	 * 
	 * @throws ChaineDeCaracteresNullOuVide idBureau null ou vide
	 * @throws LocalisationNull             localisation null
	 * @throws CapaciteBureauErronee        probleme avec les variables fixe/passage
	 * @throws BureauDejaExistant           le bureau existe deja
	 */
	
	public void ajouterBureauNonPermanent(final String idBureau, final Localisation localisation, final TypeFonction type, final int fixe, final int passage) 
			throws ChaineDeCaracteresNullOuVide, LocalisationNull, CapaciteBureauErronee, BureauDejaExistant {
		if (idBureau == null || idBureau.equals("")) {
			throw new IllegalArgumentException("le champ idBureau ne doit pas être null ou vide ");
		}
		if (localisation == null) {
			throw new IllegalArgumentException("la localisation ne doit être null");
		}
		if ((fixe + passage) <= 0 || (fixe + passage) > BeBiloc.MAX_NON_PERMANENTS_PAR_BUREAU || passage < 0 || fixe < 0) {
			throw new IllegalArgumentException("la capacité (fixe + passage) du bureau n'est pas valide");
		}
		
		Bureau nouveauBureau = new Bureau(idBureau, localisation, type, fixe, passage);
		listeBureaux.put(idBureau, nouveauBureau);
		assert invariant();
	}
	
	/**
	 * affecte une place fixe à un employé.
	 * 
	 * @param idEmp : id de l'employé
	 * @param idBureau : id du bureau.
	 * @throws ChaineDeCaracteresNullOuVide les identifiants du bureau ou de
	 *                                      l'employé sont null ou vide.
	 * @throws EmployeInexistant            l'employé n'existe pas.
	 * @throws EmployeMaxPlaces             l'employé est déjà au maximum de ses
	 *                                      places fixes.
	 * @throws BureauInexistant             le bureau n'existe pas.
	 * @throws MauvaisTypeOccupantBureau    pas le bon type d'occupant.
	 * @throws PlaceManquante               place fixe manquante.
	 */
	public void affecterPlaceFixe(final String idEmp, final String idBureau) throws ChaineDeCaracteresNullOuVide,
			EmployeInexistant, EmployeMaxPlaces, BureauInexistant, MauvaisTypeOccupantBureau, PlaceManquante {
		if (!employes.containsKey(idEmp)) {
			throw new EmployeInexistant("l'employé n'existe pas dans le système (" + idEmp + ")");
		}
		if (!listeBureaux.containsKey(idBureau)) {
			throw new BureauInexistant("le bureau n'existe pas dans le système (" + idBureau + ")");
		}
		if (idEmp == null || idEmp.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("l'identifiant de l'employé ne peut pas être null ou vide");
		}
		if (idBureau == null || idBureau.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("l'identifiant du bureau ne peut pas être null ou vide");
		}
		Bureau bureau = listeBureaux.get(idBureau);
		Employe employe = employes.get(idEmp);


		if (!employe.getTypeFonction().equals(bureau.getTypeFonction())) {
			throw new MauvaisTypeOccupantBureau(
					"mauvais type d'occupant (" + employe.getClass().getName() + ", " + bureau.getTypeFonction() + ")");
		}


		Place place = bureau.getUnePlaceFixeLibre();
		if (!((PlaceFixe) place).estLibre()) {
			throw new PlaceManquante("il n'y a plus de place fixe pour " + bureau.getTypeFonction());
		}

		// effectuer l'affectation
		
		assert invariant();
	}
	
	
	// Methode temporaire car pas très opti (pas opti du tout même ^^')
	public int getNbFixe(String id) {
		Bureau bureau = null;
		int i = 0;
		bureau = listeBureaux.get(i);
		while (bureau instanceof Bureau && ((Bureau) bureau).getId() != id){
            bureau = listeBureaux.get(i);
            i++;
        }
		return bureau.getNbFixe();
	}
	
	public int getNbPassage(String id) {
		Bureau bureau = null;
		int i = 0;
		bureau = listeBureaux.get(i);
		while (bureau instanceof Bureau && ((Bureau) bureau).getId() != id){
            bureau = listeBureaux.get(i);
            i++;
        }
		return bureau.getNbPassage();
	}
	
	
	/**
	 * liste les employés dans une liste de chaînes de caractères.
	 * 
	 * @return la liste de chaînes de caractères.
	 */
	public List<String> listerEmployes() {
		return employes.values().stream().map(Employe::toString).collect(Collectors.toList());
	}

	/**
	 * liste les employés permanents dans une liste de chaînes de caractères.
	 * 
	 * @return la liste de chaînes de caractères à afficher.
	 */
	public List<String> listerEmployesPermanents() {
		return employes.values().stream().filter(e -> e.getFonction().estPermanent()).map(Employe::toString)
				.collect(Collectors.toList());
	}

	/**
	 * liste les employés non-permanents dans une liste de chaînes de caractères.
	 * 
	 * @return la chaîne de caractères à afficher.
	 */
	public List<String> listerEmployesNonPermanents() {
		return employes.values().stream().filter(e -> !e.getFonction().estPermanent()).map(Employe::toString)
				.collect(Collectors.toList());
	}
}
