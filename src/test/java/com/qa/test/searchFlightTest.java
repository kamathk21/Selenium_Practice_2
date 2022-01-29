package com.qa.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.testBase;
import com.qa.pages.searchFlightPage;

public class searchFlightTest extends testBase {

	searchFlightPage searchflight;
	
	public searchFlightTest() {
		super();
	}
	
	@BeforeTest
	public void setup() {
		initialize();
		searchflight= new searchFlightPage();
	}
	
	@Test
	public void flightsearchTest() {
		searchflight.searchFlight();
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
