package energy_net;

public class EnergyRequest extends EnergyOperation {
    private String preferredIPAddress;
    private EnergyPreference preference;
    
    public EnergyRequest(Consumer owner, int energyAmount, String preferredIPAddress ) {
        super(owner, energyAmount);
        this.preferredIPAddress = preferredIPAddress;
    }

    public EnergyRequest(Consumer owner, int energyAmount, EnergyPreference preference ) {
        super(owner, energyAmount);
        this.preference = preference;
    }
    
    public EnergyRequest(Consumer owner, int energyAmount, String preferredIPAddress, EnergyPreference preference ) {
        super(owner, energyAmount);
        this.preferredIPAddress = preferredIPAddress;
        this.preference = preference;
    }

    public String getPreferredIPAddress() {
        return preferredIPAddress;
    }

    public void setPreferredIPAddress(String preferredIPAddress) {
        this.preferredIPAddress = preferredIPAddress;
    }
    
    public String toString() {
        return super.toString() + "Preferred IP: " + preferredIPAddress + "\n";
    }
}

