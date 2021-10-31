package com.iter.accommodation.dto;

import java.util.List;

import lombok.Data;

@Data
public class HotelsSearchDto {

	private String count;
	private List<Hotel> hotels;
}
