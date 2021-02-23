package model;

import utils.ApplicationConstants;

import java.math.BigDecimal;

public class Receipt {
	private String kind;
	private String id;
	private String date;
	private double amount;
	private Company company;

	public Receipt() {}

	public Receipt(String kind, String id, String date, String amount,
				   String name, String country, String city, String street, String number) {
		switch (kind.toLowerCase()) {
			case ApplicationConstants.BASIC_RECEIPT:
				this.kind = ApplicationConstants.BASIC_RECEIPT;
				break;
			case ApplicationConstants.HEALTH_RECEIPT:
				this.kind = ApplicationConstants.HEALTH_RECEIPT;
				break;
			case ApplicationConstants.TRAVEL_RECEIPT:
				this.kind = ApplicationConstants.TRAVEL_RECEIPT;
				break;
			case ApplicationConstants.ENTERTAINMENT_RECEIPT:
				this.kind = ApplicationConstants.ENTERTAINMENT_RECEIPT;
				break;
			case ApplicationConstants.OTHER_RECEIPT:
				this.kind = ApplicationConstants.OTHER_RECEIPT;
				break;
		}
		this.id = id;
		this.date = date;
		this.amount = Double.parseDouble(amount);
		this.company = new Company(name, country, city, street, number);
	}
	
	public String getId() {
		return id;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getKind() {
		return kind;
	}
	
	public double getAmount() {
		return (new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public Company getCompany() {
		return company;
	}

	@Override
	public String toString() {
		return "ID: " + id
				+ "\nDate: " + date
				+ "\nKind: " + kind
				+ "\nAmount: " + String.format("%.2f", amount)
				+ company.toString();

	}
}
