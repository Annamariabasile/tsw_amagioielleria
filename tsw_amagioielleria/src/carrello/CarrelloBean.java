package carrello;

public class CarrelloBean {

	int IDProdotto;
	int IDOrdine;
	int Quantita;
	int IVA_Effettiva;
	float prezzo_EffettivoUnitario;
	float totale_prodotto;

	public CarrelloBean() {
		IDProdotto = -1;
		IDOrdine = -1;
		Quantita = 0;
		IVA_Effettiva = 0;
		prezzo_EffettivoUnitario = 0;
		totale_prodotto = 0;
	}

	public int getIDProdotto() {
		return IDProdotto;
	}

	public void setIDProdotto(int prodotto) {
		this.IDProdotto = prodotto;
	}

	public double getTotale_prodotto(String _prezzo_EffettivoUnitario, String _Quantita) {
		return (prezzo_EffettivoUnitario * Quantita);
	}

	public void setTotale_prodotto(float totale_prodotto) {
		this.totale_prodotto = totale_prodotto;
	}

	public int getIDOrdine() {
		return IDOrdine;
	}

	public void setIDOrdine(int IDOrdine) {
		this.IDOrdine = IDOrdine;
	}

	public int getQuantita() {
		return Quantita;
	}

	public void setQuantita(int Quantita) {
		this.Quantita = Quantita;
	}

	public int getIVA_Effettiva() {
		return IVA_Effettiva;
	}

	public void setIVA_Effettiva(int IVA_Effettiva) {
		this.IVA_Effettiva = IVA_Effettiva;
	}

	public double getprezzo_EffettivoUnitario() {
		return prezzo_EffettivoUnitario;
	}

	public void setprezzo_EffettivoUnitario(float prezzo_EffettivoUnitario) {
		this.prezzo_EffettivoUnitario = prezzo_EffettivoUnitario;
	}

}
