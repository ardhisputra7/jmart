package com.ArdhiJmartBO;

/**
 /**
 * Class untuk mendefinisikan top up
 * extends Invoice
 * @author Ardhi Putra
 */
public class PhoneTopUp extends Invoice{
	public String phoneNumber;
	public Status status;

	/**
	 * Constructor dari phoneTopUp
	 * @param buyerId id dari buyer
	 * @param productId id dari product
	 * @param phoneNumber nomor telpon yang ingin di topup
	 */
	public PhoneTopUp (int buyerId, int productId, String phoneNumber) {
		super(buyerId,productId);
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Method untuk mendapatkan harga dari top up
	 * @param product object dari product
	 * @return mereturn harga dari topup
	 */
	@Override
	public double getTotalPay (Product product) {
		return product.price - (product.price*product.discount);
	}
}
