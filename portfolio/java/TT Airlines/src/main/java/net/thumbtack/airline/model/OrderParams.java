package net.thumbtack.airline.model;

public class OrderParams {
	private int totalPrice;

	public OrderParams(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
