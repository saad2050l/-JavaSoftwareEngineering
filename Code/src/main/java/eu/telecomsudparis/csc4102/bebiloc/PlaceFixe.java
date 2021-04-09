package eu.telecomsudparis.csc4102.bebiloc;

public class PlaceFixe extends Place{
	private boolean estLibre; 
	
	public PlaceFixe(final String idPlace, final Bureau bureau) {
		super(idPlace, bureau);
		estLibre = true;
		assert invariant();
	}
	
	public boolean invariant() {
		return this.getIdPlace() != null && !this.getIdPlace().equals("");
	}

	public boolean estLibre() {
		return estLibre;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj);
	}

	/**
	 * affecter un employé à une place fixe.
	 * 
	 * @param employe           l'employé.
	 */
	public void affecterEmploye(final Employe employe) {
		if (employe == null) {
			throw new IllegalArgumentException("l'employe ne peut pas être null");
		}
		if (!estLibre) {
			throw new IllegalStateException("la place est déjà prise !");
		}
		estLibre = false;
		assert invariant();
	}
}
