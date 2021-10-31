package com.iter.accommodation.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Provider {

	private Map<String, String> urls;
	private String xRapidapiHost;
	private String xRapidapiKey;
}
