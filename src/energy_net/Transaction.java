package energy_net;


public class Transaction {
    private String transactionId;
    private EnergyRequest energyRequest;
    private EnergyOffer energyOffer;
    private int tradedEnergyAmount;
    private double price;
    
	public Transaction(EnergyRequest energyRequest, EnergyOffer energyOffer, int tradedEnergyAmount, double price) {
		this.energyRequest = energyRequest;
		this.energyOffer = energyOffer;
		this.tradedEnergyAmount = tradedEnergyAmount;
		this.price = price;
		this.transactionId = energyRequest.getId() + "_" + energyOffer.getId();
	}
    
    
    public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public EnergyRequest getEnergyRequest() {
		return energyRequest;
	}

	public void setEnergyRequest(EnergyRequest energyRequest) {
		this.energyRequest = energyRequest;
	}

	public EnergyOffer getEnergyOffer() {
		return energyOffer;
	}

	public void setEnergyOffer(EnergyOffer energyOffer) {
		this.energyOffer = energyOffer;
	}

	public int getTradedEnergyAmount() {
		return tradedEnergyAmount;
	}

	public void setTradedEnergyAmount(int tradedEnergyAmount) {
		this.tradedEnergyAmount = tradedEnergyAmount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
    public boolean equals(Transaction transaction) {
            return this.getTransactionId().equals(transaction.getTransactionId());
    }
    
    public String toString() {
        String out = String.format("ID: %s%nBuyer IP Address: %s%nSeller IP Address: %s%nEnergy Amount: %d%nPrice: %.2f%n%n",
        		transactionId,
        		energyRequest.getOwner().getIpAddress(),
        		energyOffer.getOwner().getIpAddress(),
        		tradedEnergyAmount,
        		price*tradedEnergyAmount);
        return out;
    }
    

}
	

