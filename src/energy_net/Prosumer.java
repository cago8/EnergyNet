package energy_net;

public class Prosumer extends Consumer {
	private int distanceToCenter = 10;
	
	public Prosumer(String ipAddress, String name, double balance) {
		super(ipAddress, name, balance);
	}
	
	public Prosumer(String ipAddress, String name, double balance,int distanceToCenter) {
		super(ipAddress, name, balance);
		this.distanceToCenter = distanceToCenter;
	}

	public int getDistanceToCenter() {
		return distanceToCenter;
	}

	public void setDistanceToCenter(int distanceToCenter) {
		this.distanceToCenter = distanceToCenter;
	}

	public void deposit(double amount) {
		addBalance(amount);
	}
	
	public String toString() {
        String out = String.format("%s%nDistance to center: %d", 
        		super.toString(),distanceToCenter);
        return out;
    }
}
