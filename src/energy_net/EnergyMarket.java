package energy_net;

import java.util.*;

public class EnergyMarket {
	private HashMap<String, Consumer> consumersAndProsumers;
	private List<EnergyRequest> energyRequests;
	private List<EnergyOffer> energyOffers;
	private HashSet<Transaction> transactions;
	private double marketBalance = 0;
	private double profitPercentage;

	public EnergyMarket(double percentageEarned) {
		this.consumersAndProsumers = new HashMap<String, Consumer>();
		this.energyRequests = new ArrayList<EnergyRequest>();
		this.energyOffers = new ArrayList<EnergyOffer>();
		this.transactions = new HashSet<Transaction>();
		this.profitPercentage = percentageEarned;
	}

	public void addConsumerOrProsumer(Consumer consumer) {
		consumersAndProsumers.put(consumer.getIpAddress(), consumer);
	}

	public Consumer getConsumer(String ipAddress) {
		Consumer consumer = consumersAndProsumers.get(ipAddress);
		return consumer;
	}

	public EnergyOffer getOffer(String ipAddress) {
		for (EnergyOffer offer : energyOffers) {
			if (offer.getOwner().getIpAddress().equals(ipAddress)) {
				return offer;
			}
		}
		return null;
	}

	public void addEnergyRequest(EnergyRequest energyRequest) {
		energyRequests.add(energyRequest);
		trade();
	}

	public void addEnergyOffer(EnergyOffer energyOffer) {
		energyOffers.add(energyOffer);
		trade();
	}

	public void trade() {
		for (int count = 0; count < energyRequests.size(); count++) {
			EnergyRequest energyRequest = energyRequests.get(count);

			while (energyRequest.getEnergyAmount() > 0) {
				EnergyOffer energyOffer = null;
				if (energyRequest.getPreferredIPAddress() != null) {
					energyOffer = getOffer(energyRequest.getPreferredIPAddress());
				}

				if (energyOffer == null && energyOffers.size() > 0) {
					for (EnergyOffer offer : energyOffers) {
						if (offer.getEnergyAmount() > 0) {
							energyOffer = offer;
							break;
						}
					}
				}

				if (energyOffer == null) {
					break;
				}

				int tradedAmount = Math.min(energyRequest.getEnergyAmount(), energyOffer.getEnergyAmount());

				if (createTransaction(energyRequest, energyOffer)) {
					energyRequest.decreaseEnergyAmount(tradedAmount);
					energyOffer.decreaseEnergyAmount(tradedAmount);
					if (energyOffer.getEnergyAmount() == 0) {
						energyOffers.remove(energyOffer);
					}
					if (energyRequest.getEnergyAmount() == 0) {
						energyRequests.remove(energyRequest);
						count--;
					}
				} else {
					break;
				}

			}
		}
	}

	public boolean createTransaction(EnergyRequest energyRequest, EnergyOffer energyOffer) {
		Consumer consumer = energyRequest.getOwner();
		Consumer prosumer = energyOffer.getOwner();
		int tradedEnergyAmount = Math.min(energyRequest.getEnergyAmount(), energyOffer.getEnergyAmount());
		double price = energyOffer.getPricePerKilowatt();
		double totalPrice = tradedEnergyAmount * price;

		if (consumer.getBalance() >= tradedEnergyAmount * price) {
			consumer.deductBalance(tradedEnergyAmount*price);
			Transaction transaction = new Transaction(energyRequest, energyOffer, tradedEnergyAmount, price);
			transactions.add(transaction);
			marketBalance += profitPercentage * totalPrice;
			prosumer.addBalance(totalPrice - (profitPercentage * totalPrice));
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		String out = "---------------------------------------------\nProsumers and Consumers:";
		for (Consumer consumer : consumersAndProsumers.values()) {
			out += consumer;
		}
		
		out += "\n\nCurrent Energy Requests:\n";
        for (EnergyRequest request : energyRequests) {
        	out += request + "\n";
        }

        out += "\nCurrent Energy Offers:\n";
        for (EnergyOffer offer : energyOffers) {
        	out += offer + "\n";
        }
		
		out += "\nTransactions:\n\n";
		for (Transaction transaction : transactions) {
			out += transaction;
		}
		
		out += "Energy Market Balance: " + marketBalance + "\n";

		out += "---------------------------------------------\nEnd of simulation";

		return out;

	}

}