package energy_net;

public class EnergyOffer extends EnergyOperation implements Comparable<Object> {
	private double pricePerKilowatt;

	public EnergyOffer(Consumer owner, int energyAmount, double pricePerKilowatt) {
		super(owner, energyAmount);
		this.pricePerKilowatt = pricePerKilowatt;
	}

	public double getPricePerKilowatt() {
		return pricePerKilowatt;
	}

	public void setPricePerKilowatt(double pricePerKilowatt) {
		this.pricePerKilowatt = pricePerKilowatt;
	}
	
    public String toString() {
        return super.toString() + "Price: " + pricePerKilowatt + "\n";
    }

    public int compareTo(Object obj) {
		EnergyOffer offer = (EnergyOffer) obj;
		Prosumer otherProsumer = (Prosumer) offer.getOwner();
		Prosumer thisProsumer = (Prosumer) getOwner();
		if (this.getPricePerKilowatt() == offer.getPricePerKilowatt()) {
			if (otherProsumer.getDistanceToCenter() < thisProsumer.getDistanceToCenter()) {
				return -1;
			}
			else if (otherProsumer.getDistanceToCenter() == thisProsumer.getDistanceToCenter()){
				return 0;
			}
			else {
				return 1;
			}
		}
		if (this.getPricePerKilowatt() > offer.getPricePerKilowatt()) {
			return -1;
		}
		return 0;
	}


}


