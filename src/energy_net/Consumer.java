package energy_net;

public class Consumer {
	private String ipAddress;
	private String name;
	private double balance;

	public Consumer(String ipAddress, String name, double balance) {
		this.ipAddress = ipAddress;
		this.name = name;
		this.balance = balance;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAdress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void addBalance(double amount) {
		balance += amount;
	}

	public boolean deductBalance(double amount) {
		if (balance >= amount) {
			balance -= amount;
			return true;
		}
		return false;
	}

	public boolean equals(Consumer consumer) {
		return this.ipAddress.equals(consumer.ipAddress);
	}
	
	public String toString() {
        String out = String.format("%n%nName: %s%nIP Address: %s%nBalance: %.2f", 
        		name,ipAddress,balance);
        return out;
    }
	
}
