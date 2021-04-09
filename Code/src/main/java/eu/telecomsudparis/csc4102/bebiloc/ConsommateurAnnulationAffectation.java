// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.bebiloc;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * Cette classe définit le consommateur qui reçoit des
 * notifications à propos de la perte de l'affectation d'une place.
 * 
 */
public class ConsommateurAnnulationAffectation implements Subscriber<Notification> {
	/**
	 * l'identifiant de l'employé qui consomme.
	 */
	private final String idEmploye;
	/**
	 * la souscription.
	 */
	private Subscription souscription;

	/**
	 * construit le consommateur.
	 * 
	 * @param idEmploye l'identifiant de l'employé.
	 */
	public ConsommateurAnnulationAffectation(final String idEmploye) {
		this.idEmploye = idEmploye;
	}

	public Subscription getSouscription() {
		return souscription;
	}

	public void setSouscription(Subscription souscription) {
		this.souscription = souscription;
	}

	@Override
	public void onSubscribe(final Subscription souscription) {
		this.setSouscription(souscription);
		souscription.request(1);
		System.out.println("Consommateur "+idEmploye+" pret à recevoir la premiere annulation d'affectation");
	}

	@Override
	public void onNext(final Notification notification) {
		System.out.println(notification.toString());
		souscription.request(1);
	}

	@Override
	public void onError(final Throwable throwable) {
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		System.out.println(idEmploye + " n'occupe plus la place.");
		
	}

}
