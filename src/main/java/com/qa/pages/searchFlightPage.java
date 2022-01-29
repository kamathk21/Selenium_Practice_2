package com.qa.pages;

import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.testBase;

public class searchFlightPage extends testBase {

	@FindBy(xpath = "//li[@class='menu_Flights']//a")
	WebElement flights;

	@FindBy(xpath = "//li[@data-cy='roundTrip']")
	WebElement roundtripbtn;

	@FindBy(xpath = "//input[@id='fromCity']")
	WebElement fromcity;

	@FindBy(xpath = "//input[@id='toCity']")
	WebElement tocity;

	@FindBy(xpath = "//a[contains(text(),'Search')]")
	WebElement searchbtn;

	public searchFlightPage() {
		PageFactory.initElements(driver, this);
	}

	// function to get current day in today's date
	public int getcurrentDay() {
		Calendar calendar = Calendar.getInstance();
		int currentday = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today's date: " + currentday);
		return currentday;
	}

	// function to get additional day w.r.t current day
	public int getadditionalDay(int count) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, count);
		int adddate = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println("After adding 7 day: " + adddate);
		return adddate;
	}

	public flightResultPage searchFlight() {
		driver.findElement(By.xpath("//div[@class='loginModal displayBlock modalLogin dynHeight personal ']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", flights);
		roundtripbtn.click();
		fromcity.click();
		driver.findElement(By.xpath(
				"//div[@id='react-autowhatever-1']//descendant::div[@class='calc60']//p[contains(text(),'Delhi')]"))
				.click();
		js.executeScript("arguments[0].click();", tocity);
		driver.findElement(By.xpath(
				"//div[@id='react-autowhatever-1']//descendant::div[@class='calc60']//p[contains(text(),'Bangalore')]"))
				.click();
		driver.findElement(By.xpath(
				"//div[@class='DayPicker-Month'][1]//descendant::div[@aria-disabled='false']//p[contains(text(),'"
						+ getcurrentDay() + "')]"))
				.click();
		driver.findElement(By.xpath(
				"//div[@class='DayPicker-Month'][2]//descendant::div[@aria-disabled='false']//p[contains(text(),'"
						+ getadditionalDay(7) + "')]"))
				.click();
		searchbtn.click();
		return new flightResultPage();
	}
}
