package energy_net;

public abstract class EnergyOperation {
	private static int idNumber = 1;
    private final int id;
    private final Consumer owner;
    public int energyAmount;
    
    public EnergyOperation(Consumer owner, int energyAmount) {
        this.id = idNumber++;
        this.owner = owner;
        this.energyAmount = energyAmount;
    }
    
    public static int getIdNumber() {
		return idNumber;
	}

	public void decreaseEnergyAmount(int amount) {
		if (energyAmount > amount) {
			energyAmount -= amount;
		}
		else {
			energyAmount = 0;
		}
    }
    
    public int getId() {
        return id;
    }
    
    public Consumer getOwner() {
        return owner;
    }
    
    public int getEnergyAmount() {
        return energyAmount;
    }
    
    public String toString() {
        return "Owner IP Address: " + owner.getIpAddress() + "\nEnergy Amount: " + energyAmount + "\n";
    }
}
