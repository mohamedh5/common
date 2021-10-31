package com.iter.accommodation.dto;

import lombok.Data;

@Data
public class Hotel {

	private String hotelId;
	private String hotelName;
	private String currencycode;
	private String accommodationTypeName;
	private String reviewScoreWord;
	private String url;
	private TimePeriod checkin;
	private TimePeriod checkout;
	private String mainPhotoUrl;
	private String address;
	private String allInclusivePrice;
}
