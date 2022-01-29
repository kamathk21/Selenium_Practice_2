package com.qa.test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.testBase;
import com.qa.pages.flightResultPage;
import com.qa.pages.searchFlightPage;

public class flightResultTest extends testBase {

	flightResultPage flights;
	searchFlightPage search;

	public flightResultTest() {
		super();
	}

	@BeforeTest
	public void setup() {
		initialize();
		search = new searchFlightPage();
		flights = new flightResultPage();
		search.searchFlight();
	}

	@Test(priority = 1)
	public void countTotalFlightsTest() {
		flights.countAvailableFlights();
	}

	@Test(priority = 2)
	public void countFilteredFlightsTest() {
		flights.selectFilters();
		System.out.println("-------------After Filtering-----------");
		flights.countAvailableFlights();
	}

	@Test(priority = 3)
	public void selectRandomFlightsTest() {
		flights.selectFilters();
		flights.selectFlights();
		ArrayList<String> flightprice = new ArrayList<String>();
		ArrayList<String> botflightprice = new ArrayList<String>();
		for (int i = 0; i < flights.getFlightPrice().size() - 1; i++) {
			flightprice.add(flights.getFlightPrice().get(i));
			botflightprice.add(flights.getBottomFlightPrice().get(i));
		}
		Assert.assertTrue(flightprice.equals(botflightprice));
	}

	@Test(priority = 4)
	public void checkTotalPriceTest() {
		flights.selectFilters();
		flights.selectFlights();
		String depflightprice = flights.getBottomFlightPrice().get(0);
		String arrflightprice = flights.getBottomFlightPrice().get(1);
		int totalpricebtm = flights.getPriceValue(flights.getTotalPrice());
		int totalPrice = flights.getPriceValue(depflightprice) + flights.getPriceValue(arrflightprice);
		System.out.println("dept flight + arr flight:" + totalPrice);
		System.out.println("bottom total price:" + totalPrice);
		Assert.assertEquals(totalPrice, totalpricebtm);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
