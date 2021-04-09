package eu.telecomsudparis.csc4102.bebiloc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import eu.telecomsudparis.csc4102.bebiloc.exception.BureauDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.BureauInexistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.CapaciteBureauErronee;
import eu.telecomsudparis.csc4102.bebiloc.exception.CapaciteDePassageErronee;
import eu.telecomsudparis.csc4102.bebiloc.exception.ConflitAvecOccupationDePassageMemeLieu;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateDebutAffectationAvantAujourdhui;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateDebutAffectationAvantDateEmbauche;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateDebutAffectationNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateEmbaucheNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinAffectationApresDateFinContrat;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinAffectationAvantDateDebutAffectation;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinAffectationNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinContratNonApresDateEmbauche;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinContratNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeDejaExistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeInexistant;
import eu.telecomsudparis.csc4102.bebiloc.exception.EmployeMaxPlaces;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionInconnue;
import eu.telecomsudparis.csc4102.bebiloc.exception.FonctionNonAdaptee;
import eu.telecomsudparis.csc4102.bebiloc.exception.LocalisationNullOuVide;
import eu.telecomsudparis.csc4102.bebiloc.exception.MauvaisTypeOccupantBureau;
import eu.telecomsudparis.csc4102.bebiloc.exception.OccupationDePassageAvecPlaceFixeMemeLieu;
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
	 * collection listeBureaux.
	 */
	private Map<String, Bureau> listeBureaux;
	
	/**
	 * construit la façade.
	 */
	public BeBiloc() {
		employes = new HashMap<>();
		listeBureaux = new HashMap<>();

		assert invariant();
	}

	/**
	 * teste si l'invariant est vérifié.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return employes != null && listeBureaux != null;

	}

	/**
	 * ajouter un employé permanent.
	 * 
	 * @param id           l'identifiant.
	 * @param nom          le nom.
	 * @param prenom       le prénom.
	 * @param dateEmbauche la date d'embauche.
	 * @param fonction     la fonction de permanent.
	 * @throws ChaineDeCaracteresNullOuVide l'identifiant du permanent ou le nom de
	 *                                      la fonction est null ou vide.
	 * @throws DateEmbaucheNull             la date d'embauche est null.
	 * @throws FonctionInconnue             la fonction est null.
	 * @throws EmployeDejaExistant          l'employé avec cet identifiant existe
	 *                                      déjà.
	 * @throws FonctionNonAdaptee           la fonction n'est pas une fonction de
	 *                                      permanent.
	 * @throws EmployeInexistant 
	 */
	public void ajouterPermanent(final String id, final String nom, final String prenom, final LocalDate dateEmbauche,
			final String fonction) throws ChaineDeCaracteresNullOuVide, DateEmbaucheNull, FonctionInconnue,
			EmployeDejaExistant, FonctionNonAdaptee, EmployeInexistant {
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
		ajouterConsommateurPourNotificationAnnulationPlace(id);
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
	 * @throws ChaineDeCaracteresNullOuVide       l'identifiant du non-permanent ou
	 *                                            le nom de la fonction est null ou
	 *                                            vide.
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
	 * @throws EmployeInexistant 
	 */
	public void ajouterNonPermanent(final String id, final String nom, final String prenom,
			final LocalDate dateEmbauche, final LocalDate dateFinContrat, final String fonction)
			throws ChaineDeCaracteresNullOuVide, DateEmbaucheNull, DateFinContratNull,
			DateFinContratNonApresDateEmbauche, FonctionInconnue, EmployeDejaExistant, FonctionNonAdaptee, EmployeInexistant {
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
		ajouterConsommateurPourNotificationAnnulationPlace(id);
		assert invariant();
	}

	/**
	 * ajouter un bureau permanent.
	 * 
	 * @param idBureau     l'identifiant du bureau.
	 * @param localisation la localisation du bureau.
	 * @param fixe         nb de places fixes
	 * @param passage      nb de places de passage
	 * 
	 * 
	 * @throws ChaineDeCaracteresNullOuVide idBureau null ou vide
	 * @throws LocalisationNullOuVide             localisation null
	 * @throws CapaciteBureauErronee        probleme avec les variables fixe/passage
	 * @throws BureauDejaExistant           le bureau existe deja
	 */

	public void ajouterBureauPermanent(final String idBureau, final String localisation, final int fixe,
			final int passage)
			throws ChaineDeCaracteresNullOuVide, LocalisationNullOuVide, CapaciteBureauErronee, BureauDejaExistant {
		if (idBureau == null || idBureau.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le champ idBureau ne doit pas être null ou vide ");
		}
		if (localisation == null || localisation.equals("")) {
			throw new LocalisationNullOuVide("la localisation ne doit être null ou vide");
		}
		Localisation loc = Localisation.valueOf(localisation); 
		if ((fixe + passage) > BeBiloc.MAX_PERMANENTS_PAR_BUREAU || passage < 0 || passage >= 2 || fixe > 1) {
			throw new CapaciteBureauErronee("la capacité (fixe + passage) du bureau n'est pas valide");
		}
		if (listeBureaux.containsKey(idBureau)) { 
			throw new BureauDejaExistant("bureau avec identifiant '" + idBureau + "'");
		}

		Bureau nouveauBureau = new Bureau(idBureau, loc, fixe, passage);
		listeBureaux.put(idBureau, nouveauBureau);
		assert invariant();
	}

	/**
	 * ajouter un bureau non permanent (en fonction de type).
	 * 
	 * @param idBureau     l'identifiant du bureau.
	 * @param localisation la localisation du bureau.
	 * @param type         le type du bureau du bureau.
	 * @param fixe         nb de places fixes
	 * @param passage      nb de places de passage
	 * 
	 * 
	 * @throws ChaineDeCaracteresNullOuVide idBureau null ou vide
	 * @throws LocalisationNullOuVide             localisation null
	 * @throws CapaciteBureauErronee        probleme avec les variables fixe/passage
	 * @throws BureauDejaExistant           le bureau existe deja
	 * @throws CapaciteDePassageErronee
	 */

	public void ajouterBureauNonPermanent(final String idBureau, final String localisation,
			final TypeFonction type, final int fixe, final int passage)
			throws ChaineDeCaracteresNullOuVide, LocalisationNullOuVide, CapaciteBureauErronee, BureauDejaExistant, CapaciteDePassageErronee {
		if (idBureau == null || idBureau.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le champ idBureau ne doit pas être null ou vide ");
		}
		if (localisation == null || localisation.equals("")) {
			throw new LocalisationNullOuVide("la localisation ne doit être null ou vide");
		}
		Localisation loc = Localisation.valueOf(localisation); 
		if ((fixe + passage) < 1 || (fixe + passage) > BeBiloc.MAX_NON_PERMANENTS_PAR_BUREAU || passage < 0 || fixe < 0) {
			throw new CapaciteBureauErronee("la capacité (fixe + passage) du bureau n'est pas valide");
		}
		if (listeBureaux.containsKey(idBureau)) {
			throw new BureauDejaExistant("bureau déjà existant avec cet id : " + idBureau);
		}
		Bureau nouveauBureau = new Bureau(idBureau, loc, type, fixe, passage);
		listeBureaux.put(idBureau, nouveauBureau);
		assert invariant();
	}

	/**
	 * affecte une place fixe à un employé.
	 * 
	 * @param idEmp    : id de l'employé
	 * @param idBureau : id du bureau.
	 * @throws ChaineDeCaracteresNullOuVide les identifiants du bureau ou de
	 *                                      l'employé sont null ou vide.
	 * @throws EmployeInexistant            l'employé n'existe pas.
	 * @throws EmployeMaxPlaces             l'employé est déjà au maximum de ses
	 *                                      places fixes.
	 * @throws BureauInexistant             le bureau n'existe pas.
	 * @throws MauvaisTypeOccupantBureau    pas le bon type d'occupant.
	 * @throws PlaceManquante               place fixe manquante.
	 * @throws CapaciteBureauErronee 
	 */
	public void affecterPlaceFixe(final String idEmp, final String idBureau) throws ChaineDeCaracteresNullOuVide,
			EmployeInexistant, EmployeMaxPlaces, BureauInexistant, MauvaisTypeOccupantBureau, PlaceManquante  {
		if (idEmp == null || idEmp.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("l'identifiant de l'employé ne peut pas être null ou vide");
		}
		if (idBureau == null || idBureau.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("l'identifiant du bureau ne peut pas être null ou vide");
		}
		Bureau bureau = listeBureaux.get(idBureau);
		Employe employe = employes.get(idEmp);
		// chercher employé avec le bon id + verifier qu'il existe
		if (!employes.containsKey(idEmp)) {
			throw new EmployeInexistant("l'employé n'existe pas dans le système (" + idEmp + ")");
		}
		// chercher bureau avec le bon id + verifier qu'il existe
		if (!(listeBureaux.containsKey(idBureau))) {
			throw new BureauInexistant("le bureau n'existe pas dans le système (" + idBureau + ")");
		}
		// verifie si le TypeFonction de l'employé = celui de bureau
		if (!employe.getTypeFonction().equals(bureau.getTypeFonction())) {
			throw new MauvaisTypeOccupantBureau(
			"mauvais type d'occupant (" + employe.getClass().getName() + ", " + bureau.getTypeFonction() + ")");
		}
		if ((bureau.getNbFixe() - bureau.placesFixeOccupee().size()) < 1) {
			throw new PlaceManquante("le bureau n'a pas assez de place fixe libre.");
		}
				
		if (employe.aDejaAtteintMaxPlaces(bureau.getLocalisation())) {
			throw new EmployeMaxPlaces("employé déjà au maximum de ses places (" + employe.getId() + ", "
					+ employe.getPlacesFixesReservees().size());
		}
		PlaceFixe place = (PlaceFixe) bureau.getUnePlaceFixeLibre();
		place.affecterEmploye(employe);
		employe.affecterPlaceLibre(place);
		assert invariant();
	}
	
	/**
	 * affecter un bureau de passage à un employé.
	 * 
	 * @param idEmploye            l'employé.
	 * @param idBureau             le bureau.
	 * @param dateDebutAffectation la date de début de l'affectation.
	 * @param dateFinAffectation   la date de fin de l'affectation.
	 * @throws ChaineDeCaracteresNullOuVide                les identifiants du
	 *                                                     bureau ou de l'employé
	 *                                                     sont null ou vide.
	 * @throws EmployeInexistant                           l'employé n'existe pas.
	 * @throws EmployeMaxPlaces                            l'employé est déjà au
	 *                                                     maximum de ses places.
	 * @throws BureauInexistant                            le bureau n'existe pas.
	 * @throws MauvaisTypeOccupantBureau                   pas le bon type
	 *                                                     d'occupant.
	 * @throws PlaceManquante                              place fixe manquante.
	 * @throws DateDebutAffectationNull                    date de début
	 *                                                     d'affectation null.
	 * @throws DateDebutAffectationAvantAujourdhui         date de début
	 *                                                     d'affectation avant
	 *                                                     aujourd'hui.
	 * @throws DateFinAffectationNull                      date de fin d'affectation
	 *                                                     null.
	 * @throws DateFinAffectationAvantDateDebutAffectation date de fin d'affectation
	 *                                                     avant date de début
	 *                                                     d'affectation.
	 * @throws DateDebutAffectationAvantDateEmbauche       date de début
	 *                                                     d'affectation avant la
	 *                                                     date d'embauche.
	 * @throws DateFinAffectationApresDateFinContrat       date de fin d'affectation
	 *                                                     après la date de fin de
	 *                                                     contrat.
	 * @throws ConflitAvecOccupationDePassageMemeLieu      existance conflit
	 *                                                     d'occupation de passage
	 *                                                     sur le même lieu.
	 */
	public void affecterPlaceDePassage(final String idEmploye, final String idBureau,
			final LocalDate dateDebutAffectation, final LocalDate dateFinAffectation)
			throws ChaineDeCaracteresNullOuVide, EmployeInexistant, EmployeMaxPlaces, BureauInexistant,
			MauvaisTypeOccupantBureau, PlaceManquante, DateDebutAffectationNull, DateDebutAffectationAvantAujourdhui,
			DateFinAffectationNull, DateFinAffectationAvantDateDebutAffectation, DateDebutAffectationAvantDateEmbauche,
			DateFinAffectationApresDateFinContrat, OccupationDePassageAvecPlaceFixeMemeLieu, ConflitAvecOccupationDePassageMemeLieu, 
			PlaceManquante {
		if (idEmploye == null || idEmploye.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("l'identifiant de l'employé ne peut pas être null ou vide");
		}
		if (!employes.containsKey(idEmploye)) {
			throw new EmployeInexistant("l'employé n'existe pas dans le système (" + idEmploye + ")");
		}
		if (idBureau == null || idBureau.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("l'identifiant du bureau ne peut pas être null ou vide");
		}
		if (!listeBureaux.containsKey(idBureau)) {
			throw new BureauInexistant("le bureau n'existe pas dans le système (" + idBureau + ")");
		}
		Bureau bureau = listeBureaux.get(idBureau);
		Employe employe = employes.get(idEmploye);
		if (!employe.getTypeFonction().equals(bureau.getTypeFonction())) {
			throw new MauvaisTypeOccupantBureau(
					"mauvais type d'occupant (" + employe.getClass().getName() + ", " + bureau.getTypeFonction() + ")");
		}
		if (dateDebutAffectation == null) {
			throw new DateDebutAffectationNull("la date de début d'affectation ne peut pas être null");
		}
		if (Datutil.dateEstAvantAujourdhui(dateDebutAffectation)) {
			throw new DateDebutAffectationAvantAujourdhui("la date de début d'affectation ne peut pas être passé");
		}
		if (dateFinAffectation == null) {
			throw new DateFinAffectationNull("la date de fin d'affectation ne peut pas être null");
		}
		if (Datutil.dateEstAvant(dateFinAffectation, dateDebutAffectation)) {
			throw new DateFinAffectationAvantDateDebutAffectation(
					"la date de fin d'affectation ne peut pas être avant la date de début d'affectation");
		}
		if (!employe.getFonction().estPermanent()) {
			if (Datutil.dateEstAvant(dateDebutAffectation, employe.getDateEmbauche())) {
				throw new DateDebutAffectationAvantDateEmbauche(
						"la date de début d'affectation est avant la date d'embauche");
			}
			if (Datutil.dateEstAvant(employe.getDateFinContrat().orElseThrow(IllegalStateException::new),
					dateFinAffectation)) {
				throw new DateFinAffectationApresDateFinContrat(
						"la date de fin d'affectation est après la date de fin du contrat");
			}
		}
		if (employe.aDejaPlaceDePassageSurLocalisation(bureau.getLocalisation(), dateDebutAffectation,
				dateFinAffectation)) {
			throw new ConflitAvecOccupationDePassageMemeLieu(
					"il y a une intersection non vide avec une occupation sur le même lieu");
		}

		if (employe.aDejaBureauSurLocalisation(bureau.getLocalisation())) {
			throw new OccupationDePassageAvecPlaceFixeMemeLieu("il y a déjà une place fixe sur le même lieu");
		}

		List<String> placesLibres = bureau.placesDePassageLibreDate(dateDebutAffectation, dateFinAffectation);
		PlaceDePassage place = null;
		for (Place p : bureau.getPlaces()) {
			if (p instanceof PlaceDePassage && p.getIdPlace().equals(placesLibres.get(0))) {
				place = (PlaceDePassage) p;
			}
		}
		
		if (place == null) {
			throw new PlaceManquante("il n'y a plus de place de passage dans ce bureau");
		}
		
		
		OccupationDePassage nouvelle = employe.affecterPlaceDePassage(place, dateDebutAffectation,
				dateFinAffectation);
		place.ajouterOccupationDePassage(nouvelle);
		assert invariant();
	}
	
	/**
	 * methode rendre bureau.
	 * @param idBureau
	 * @throws ChaineDeCaracteresNullOuVide
	 * @throws BureauInexistant
	 * @throws InterruptedException 
	 * @throws EmployeInexistant 
	 */
	public void rendreBureau(final String idBureau) throws ChaineDeCaracteresNullOuVide, BureauInexistant, InterruptedException, EmployeInexistant {
		if (idBureau == null || idBureau.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le champ idBureau ne doit pas être null ou vide ");
		}
		if (!listeBureaux.containsKey(idBureau)) {
			throw new BureauInexistant("ce bureau n'existe pas");
		}
		Bureau bureau = listeBureaux.get(idBureau);
		
		List<PlaceFixe> pFO = listeBureaux.get(idBureau).placesFixeOccupee();
		List<PlaceDePassage> pdPO = listeBureaux.get(idBureau).placesDePassageOccupeeEnCoursOuFutures();
		boolean flagSuppression = bureau.rendre(pFO, pdPO);
		if (flagSuppression) {
			for (PlaceFixe p : pFO) {
				String idEmp = p.getidEmp();
				Employe e =  employes.get(idEmp);
				e.retirerAffectationPlaceFixe(p);
			}
			
			for (PlaceDePassage p : pdPO) {
				List<OccupationDePassage> odP =  p.getOccupations();
				for (OccupationDePassage o : odP) {
					Employe e =  o.getEmploye();
					e.retirerUneOccupation(o);
				}
			}
			
			listeBureaux.remove(idBureau);
		}
	}
	
	/**
	 * methode rendre bureau avec la stratégie 1.
	 * @param idBureau
	 * @throws ChaineDeCaracteresNullOuVide
	 * @throws BureauInexistant
	 * @throws InterruptedException 
	 * @throws EmployeInexistant 
	 */
	public void rendreBureauS1(final String idBureau) throws ChaineDeCaracteresNullOuVide, BureauInexistant, InterruptedException, EmployeInexistant {
		if (idBureau == null || idBureau.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le champ idBureau ne doit pas être null ou vide ");
		}
		if (!listeBureaux.containsKey(idBureau)) {
			throw new BureauInexistant("ce bureau n'existe pas");
		}
		Bureau bureau = listeBureaux.get(idBureau);
		List<PlaceFixe> pFO = bureau.placesFixeOccupee();
		List<PlaceDePassage> pdPO = bureau.placesDePassageOccupeeEnCoursOuFutures();
		
		if (true) {
			for (PlaceFixe p : pFO) {
				String idEmp = p.getidEmp();
				Employe e =  employes.get(idEmp);
				e.retirerAffectationPlaceFixe(p);
			}
			
			for (PlaceDePassage p : pdPO) {
				List<OccupationDePassage> odP =  p.getOccupations();
				for (OccupationDePassage o : odP) {
					Employe e =  o.getEmploye();
					e.retirerUneOccupation(o);
				}
			}
			
			listeBureaux.remove(idBureau);
		}
	}
	
	/**
	 * methode rendre bureau avec la stratégie 2.
	 * @param idBureau
	 * @throws ChaineDeCaracteresNullOuVide
	 * @throws BureauInexistant
	 * @throws InterruptedException 
	 * @throws EmployeInexistant 
	 */
	public void rendreBureauS2(final String idBureau) throws ChaineDeCaracteresNullOuVide, BureauInexistant, InterruptedException, EmployeInexistant {
		if (idBureau == null || idBureau.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le champ idBureau ne doit pas être null ou vide ");
		}
		if (!listeBureaux.containsKey(idBureau)) {
			throw new BureauInexistant("ce bureau n'existe pas");
		}
		Bureau bureau = listeBureaux.get(idBureau);
		List<PlaceFixe> pFO = bureau.placesFixeOccupee();
		List<PlaceDePassage> pdPO = bureau.placesDePassageOccupeeEnCoursOuFutures();
		
		if (pFO.isEmpty()) {			
			for (PlaceDePassage p : pdPO) {
				List<OccupationDePassage> odP =  p.getOccupations();
				for (OccupationDePassage o : odP) {
					Employe e =  o.getEmploye();
					e.retirerUneOccupation(o);
				}
			}
			
			listeBureaux.remove(idBureau);
		}
	}

	/**
	 * methode rendre bureau avec la stratégie 3.
	 * @param idBureau
	 * @throws ChaineDeCaracteresNullOuVide
	 * @throws BureauInexistant
	 * @throws InterruptedException 
	 * @throws EmployeInexistant 
	 */
	public void rendreBureauS3(final String idBureau) throws ChaineDeCaracteresNullOuVide, BureauInexistant, InterruptedException, EmployeInexistant {
		if (idBureau == null || idBureau.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("le champ idBureau ne doit pas être null ou vide ");
		}
		if (!listeBureaux.containsKey(idBureau)) {
			throw new BureauInexistant("ce bureau n'existe pas");
		}
		Bureau bureau = listeBureaux.get(idBureau);
		List<PlaceFixe> pFO = bureau.placesFixeOccupee();
		List<PlaceDePassage> pdPO = bureau.placesDePassageOccupeeEnCoursOuFutures();
		
		if ((pFO.isEmpty()) || (pdPO.isEmpty())) {			
			listeBureaux.remove(idBureau);
		}
	}

	/**
	 * ajoute un consommateur pour les notification de suppression de bureaux.
	 * 
	 * @param idEmploye    identifiant de l'employé qui ajoute ce consommateur.
	 * @throws ChaineDeCaracteresNullOuVide      l'identifiant du permanent est null
	 *                                           ou vide.
	 * @throws EmployeInexistant                 employé qui n'existe pas.
	 * @throws EmployePasLaFonctionPourConsommer pas le bon type d'employé pour
	 *                                           recevoir ces notifications.
	 */
	public void ajouterConsommateurPourNotificationAnnulationPlace(final String idEmploye)
			throws ChaineDeCaracteresNullOuVide, EmployeInexistant {
		if (idEmploye == null || idEmploye.equals("")) {
			throw new ChaineDeCaracteresNullOuVide(
					"l'identifiant du bureau ne peut pas être null ou vide");
		}
		if (!employes.containsKey(idEmploye)) {
			throw new EmployeInexistant("l'employé n'existe pas dans le système (" + idEmploye + ")");
		}
		
		Employe e = employes.get(idEmploye);
		ConsommateurAnnulationAffectation consommateur = new ConsommateurAnnulationAffectation(idEmploye);
		e.setConsommateur(consommateur);
		e.getProducteurPourAnnulationBureau().subscribe(e.getConsommateur());
			
	}

	/**
	 * liste des bureaux.
	 * 
	 * @return nb de places de passage.
	 */
	public Map<String, Bureau> getListeBureaux() {
		return listeBureaux;
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
