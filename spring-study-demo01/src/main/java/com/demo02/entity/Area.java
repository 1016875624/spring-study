package com.demo02.entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Area {
	private String country;
	private String city;
	private String street;
}
