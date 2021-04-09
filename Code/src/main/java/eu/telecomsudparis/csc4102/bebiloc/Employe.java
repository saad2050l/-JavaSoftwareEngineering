package eu.telecomsudparis.csc4102.bebiloc;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import eu.telecomsudparis.csc4102.util.Datutil;

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

	
	/*
	 * array placesFixesReservees
	 */
	private final List<Place> placesFixesReservees = null;
	
	
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
		assert invariant();
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
