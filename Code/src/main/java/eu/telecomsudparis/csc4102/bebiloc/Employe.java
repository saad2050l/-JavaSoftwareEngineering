// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

import eu.telecomsudparis.csc4102.bebiloc.exception.DateDebutAffectationNull;
import eu.telecomsudparis.csc4102.bebiloc.exception.DateFinAffectationNull;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.Datutil;
import eu.telecomsudparis.csc4102.util.IntervalleDates;

/**
 * Cette classe définit le concept métier d'employé.
 * 
 * @author Denis Conan
 */
public class Employe {
	/**
	 * l'identifiant unique de l'employé.
	 */
	private final String id;
	/**
	 * le nom de l'employé.
	 */
	private String nom;
	/**
	 * le prénom de l'employé.
	 */
	private String prenom;
	/**
	 * la date d'embauche.
	 */
	private LocalDate dateEmbauche;
	/**
	 * la date de fin de contrat. {@code null} pour un permanent.
	 */
	private Optional<LocalDate> dateFinContrat = Optional.empty();
	/**
	 * la fonction.
	 */
	private Fonction fonction;
	/**
	 * liste des places fixes.
	 */
	private List<PlaceFixe> placesFixesReservees;
	/**
	 * les occupations de places de passages.
	 */
	private final List<OccupationDePassage> occupationsDePassage;
	/**
	 * producteur pour annulation bureau.
	 */
	private SubmissionPublisher<Notification> producteurPourAnnulationBureau;
	/**
	 * la collection des consommateurs.
	 */
	private ConsommateurAnnulationAffectation consommateur;
	
	/**
	 * construit un employé de type permanent, c'est-à-dire sans date de fin de
	 * contrat.
	 * 
	 * @param id           l'identifiant.
	 * @param nom          le nom.
	 * @param prenom       le prénom.
	 * @param dateEmbauche la date d'embauche.
	 * @param fonction     la fonction.
	 */
	public Employe(final String id, final String nom, final String prenom, final LocalDate dateEmbauche,
			final Fonction fonction) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("l'identifiant ne peut pas être null ou vide");
		}
		if (nom == null || nom.equals("")) {
			throw new IllegalArgumentException("le nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.equals("")) {
			throw new IllegalArgumentException("le prénom ne peut pas être null ou vide");
		}
		if (dateEmbauche == null) {
			throw new IllegalArgumentException("la date d'embauche ne peut pas être null");
		}
		if (fonction == null) {
			throw new IllegalArgumentException("la fonction ne peut pas être null");
		}
		if (!fonction.equals(Fonction.DIRECTION_DEPARTEMENT)
				&& !fonction.equals(Fonction.DIRECTION_ADJOINTE_DEPARTEMENT)
				&& !fonction.equals(Fonction.ASSISTANCE_GESTION) && !fonction.equals(Fonction.ENSEIGNEMENT_RECHERCHE)) {
			throw new IllegalArgumentException("erreur de fonction pour un permanent");
		}
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateEmbauche = dateEmbauche;
		this.dateFinContrat = Optional.empty();
		this.fonction = fonction;
		
		this.placesFixesReservees = new ArrayList<>();
		this.occupationsDePassage = new ArrayList<>();
		
		this.producteurPourAnnulationBureau = new SubmissionPublisher<>();
		this.setConsommateur(null);
		
		assert invariant();
	}

	/**
	 * construit un employé de type non-permanent, c'est-à-dire avec une date de fin
	 * de contrat.
	 * 
	 * @param id             l'identifiant.
	 * @param nom            le nom.
	 * @param prenom         le prénom.
	 * @param dateEmbauche   la date d'embauche.
	 * @param dateFinContrat la date de fin du contrat.
	 * @param fonction       la fonction.
	 */
	public Employe(final String id, final String nom, final String prenom, final LocalDate dateEmbauche,
			final LocalDate dateFinContrat, final Fonction fonction) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("l'identifiant ne peut pas être null ou vide");
		}
		if (nom == null || nom.equals("")) {
			throw new IllegalArgumentException("le nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.equals("")) {
			throw new IllegalArgumentException("le prénom ne peut pas être null ou vide");
		}
		if (dateEmbauche == null) {
			throw new IllegalArgumentException("la date d'embauche ne peut pas être null");
		}
		if (dateFinContrat == null) {
			throw new IllegalArgumentException("la date de fin de contrat ne peut pas être null");
		}
		if (fonction == null) {
			throw new IllegalArgumentException("la fonction ne peut pas être null");
		}
		if (!fonction.equals(Fonction.DOCTORAT) && !fonction.equals(Fonction.POST_DOCTORAT)
				&& !fonction.equals(Fonction.INGENIERIE_RECHERCHE) && !fonction.equals(Fonction.STAGE)) {
			throw new IllegalArgumentException("erreur de fonction pour un non-permanent");
		}
		if (Datutil.dateEstAvant(dateFinContrat, dateEmbauche)) {
			throw new IllegalArgumentException("la date de fin de contrat ne peut pas être avant la date d'embauche");
		}
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateEmbauche = dateEmbauche;
		this.fonction = fonction;
		this.dateFinContrat = Optional.of(dateFinContrat);
		
		this.placesFixesReservees = new ArrayList<>();
		this.occupationsDePassage = new ArrayList<>();
		
		this.producteurPourAnnulationBureau = new SubmissionPublisher<>();
		this.setConsommateur(null);
		
		assert invariant();
	}
	
	/**
	 * Est ce que l’employé à un bureau sur la localisation?.
	 * @param localisation
	 * @return true si l’employé à un bureau sur la localisation du bureau et false sinon.
	 */
	public boolean aDejaBureauSurLocalisation(final Localisation localisation) {
		if (localisation == null) {
			throw new IllegalArgumentException("la localisation ne peut pas être libre");
		}
	
		for(PlaceFixe place : placesFixesReservees) {
			if(place.getLocalisation().equals(localisation)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Est ce que l'employé a atteint le maximum de places
	 * qu'il peut occuper compte tenu de sa fonction?
	 * 
	 * @param localisation la localisation du bureau.
	 * @return vrai si au maximum.
	 */
	public boolean aDejaAtteintMaxPlaces(final Localisation localisation) {
		if (localisation == null) {
			throw new IllegalArgumentException("la localisation ne peut pas être libre");
		}
		long nbPlacesFixesOccupees = placesFixesReservees.size();
		if ((fonction.getTypeFonction().equals(TypeFonction.NON_PERMANENT)
				|| fonction.equals(Fonction.ASSISTANCE_GESTION) || fonction.equals(Fonction.ENSEIGNEMENT_RECHERCHE))
				&& nbPlacesFixesOccupees >= 1) {
			return true;
		}
		return ((fonction.equals(Fonction.DIRECTION_DEPARTEMENT)
				|| fonction.equals(Fonction.DIRECTION_ADJOINTE_DEPARTEMENT)) && !placesFixesReservees.isEmpty()
				&& (nbPlacesFixesOccupees >= 2 || placesFixesReservees.get(0).getLocalisation().equals(localisation)));
	}
	
	/**
	 * Est ce que l’employé à deja une place de passage sur la localisation du bureau
	 * et dans le meme intervalle de temps ? .
	 * @param localisation
	 * @param dateDebutAffectation
	 * @param dateFinAffectation
	 * @return true si l’employé à deja une place de passage sur la localisation du bureau et false sinon.
	 */
	public boolean aDejaPlaceDePassageSurLocalisation(final Localisation localisation, 
			final LocalDate dateDebutAffectation, final LocalDate dateFinAffectation) {
		if (localisation == null) {
			throw new IllegalArgumentException("la localisation ne peut pas être libre");
		}		
		if (dateDebutAffectation == null) {
			throw new IllegalArgumentException("la dateDebutAffectation ne peut pas être libre");
		}	
		if (dateFinAffectation == null) {
			throw new IllegalArgumentException("la dateFinAffectation ne peut pas être libre");
		}	
		for (OccupationDePassage occupation : occupationsDePassage) {
			if ( (new IntervalleDates(dateDebutAffectation, dateFinAffectation)).intervalleDatesSIntersectent(occupation.getIntervalle())) {
				if (localisation.equals(occupation.getPlace().getBureau().getLocalisation())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * affecter une place fixe à un employé.
	 * 
	 * @param placeFixe         la place fixe.
	 */
	public void affecterPlaceLibre(final PlaceFixe placeFixe) {
		if (placeFixe == null) {
			throw new IllegalArgumentException("La place ne peut pas valoir : null");
		}
		placeFixe.affecterEmploye(this);
		placesFixesReservees.add(placeFixe);
		assert invariant();
	}
	
	/**
	 * @return producteurPourAnnulationBureau.
	 */
	public SubmissionPublisher<Notification> getProducteurPourAnnulationBureau() {
		return producteurPourAnnulationBureau;
	}

	/**
	 * Setter du producteurPourAnnulationBureau.
	 * @param producteurPourAnnulationBureau
	 */
	public void setProducteurPourAnnulationBureau(SubmissionPublisher<Notification> producteurPourAnnulationBureau) {
		this.producteurPourAnnulationBureau = producteurPourAnnulationBureau;
	}

	/**
	 * @return ConsommateurAnnulationAffectation.
	 */
	public ConsommateurAnnulationAffectation getConsommateur() {
		return consommateur;
	}

	/**
	 * @param consommateur
	 */
	public void setConsommateur(ConsommateurAnnulationAffectation consommateur) {
		this.consommateur = consommateur;
	}
	
	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return vrai si l'invariant est vérifié.
	 */
	public boolean invariant() {
		boolean invAttributSimple = id != null && !id.equals("") && nom != null && !nom.equals("") && prenom != null
				&& !prenom.equals("") && dateEmbauche != null && fonction != null;
		boolean invDateFinContrat = (fonction.getTypeFonction().equals(TypeFonction.PERMANENT)
				? dateFinContrat.isEmpty()
				: dateFinContrat.isPresent());
		return invAttributSimple && invDateFinContrat;
	}

	/**
	 * obtient l'identifiant.
	 * 
	 * @return l'identifiant.
	 */
	public String getId() {
		return id;
	}

	/**
	 * obtient le nom.
	 * 
	 * @return le nom.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * obtient le prénom.
	 * 
	 * @return le prénom.
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * obtient la date d'embauche.
	 * 
	 * @return la date d'embauche.
	 */
	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	/**
	 * obtient la date de fin de contrat, qui peut être {@code null}.
	 * 
	 * @return le {@link Optional} de la date de fin de contrat.
	 */
	public Optional<LocalDate> getDateFinContrat() {
		return dateFinContrat;
	}

	/**
	 * obtient la fonction.
	 * 
	 * @return la fonction.
	 */
	public Fonction getFonction() {
		return fonction;
	}

	/**
	 * obtient le type de la fonction.
	 * 
	 * @return le type de la fonction.
	 */
	public TypeFonction getTypeFonction() {
		return fonction.getTypeFonction();
	}

	/**
	 * obtient la liste des places fixes. 
	 *  
	 * @return les places fixes.
	 */
	public List<PlaceFixe> getPlacesFixesReservees() {
		return placesFixesReservees;
	}
		
	/**
	 * obtient la liste des occupations De Passage.
	 *  
	 * @return les occupations De Passage.
	 */
	public List<OccupationDePassage> getOccupationsDePassage() {
		return occupationsDePassage;
	}

	/**
	 * retire une place fixe à l'employé.
	 * 
	 * @param placeFixe la place fixe à retirer.
	 */
	public void retirerAffectationPlaceFixe(final PlaceFixe placeFixe) {
		List<String> message = new ArrayList<>();
		message.add(placeFixe.getBureau().getId());
		message.add(placeFixe.getIdPlace());
		this.producteurPourAnnulationBureau.submit(new Notification(message));
		this.placesFixesReservees.remove(placeFixe);
	}

	/**
	 * retire une occupation de place de passage.
	 * 
	 * @param occupation l'occupation à retirer.
	 */
	public void retirerUneOccupation(final OccupationDePassage occupation) {
		List<String> message = new ArrayList<>();
		message.add(occupation.getPlace().getBureau().getId());
		message.add(occupation.getPlace().getIdPlace());
		this.producteurPourAnnulationBureau.submit(new Notification(message));
		this.occupationsDePassage.remove(occupation);
	}

	/**
	 * affecter une place de passage à un employé.
	 * 
	 * @param placeDePassage    la place de passage.
	 * @param dateDebut         la date de début d'occupation.
	 * @param dateFin           la date de fin d'occupation.
	 * @param statutAffectation le statut de l'affectation.
	 * @return l'occupation de passage créée.
	 * @throws DateDebutAffectationNull 
	 * @throws DateFinAffectationNull 
	 * @throws ChaineDeCaracteresNullOuVide 
	 */
	public OccupationDePassage affecterPlaceDePassage(final PlaceDePassage placeDePassage, final LocalDate dateDebut,
			final LocalDate dateFin) throws DateDebutAffectationNull, DateFinAffectationNull, ChaineDeCaracteresNullOuVide {
		if (placeDePassage == null) {
			throw new ChaineDeCaracteresNullOuVide("la place de passage ne doit pas être null");
		}
		if (dateDebut == null) {
			throw new DateDebutAffectationNull("la date de début ne doit pas être null");
		}
		if (dateFin == null) {
			throw new DateFinAffectationNull("la date de fin ne doit pas être null");
		}
		OccupationDePassage nouvelle = new OccupationDePassage(new IntervalleDates(dateDebut, dateFin), placeDePassage,
				this);
		occupationsDePassage.add(nouvelle);
		assert invariant();
		return nouvelle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Employe)) {
			return false;
		}
		Employe other = (Employe) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Employe [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateEmbauche=" + dateEmbauche
				+ ", dateFinContrat=" + dateFinContrat + ", fonction=" + fonction + "]";
	}
}
