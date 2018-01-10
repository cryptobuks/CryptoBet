package model;

public class EsitoPartita {

	private String stato;
	private Esito esito;
	private float quota;
	private Partita partita;
	private boolean disponibile;

	public EsitoPartita(boolean disponibile, Esito esito, float quota, Partita partita, String stato) {
		this.esito = esito;
		this.quota = quota;
		this.partita = partita;
		this.disponibile = disponibile;
		this.stato = stato;
	}
	
	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public Esito getEsito() {
		return esito;
	}

	public void setEsito(Esito esito) {
		this.esito = esito;
	}

	public float getQuota() {
		return quota;
	}

	public void setQuota(float quota) {
		this.quota = quota;
	}

	public Partita getPartita() {
		return partita;
	}

	public void setPartita(Partita partita) {
		this.partita = partita;
	}
	
	public boolean isDisponibile() {
		return disponibile;
	}
	
	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}

}
