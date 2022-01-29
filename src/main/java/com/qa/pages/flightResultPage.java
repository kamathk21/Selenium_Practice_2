package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.testBase;

public class flightResultPage extends testBase {

	@FindBy(xpath = "//div[@class='paneView'][1]//descendant::div[@class='listingCard ']")
	List<WebElement> departureflights;

	@FindBy(xpath = "//div[@class='paneView'][2]//descendant::div[@class='listingCard ']")
	List<WebElement> returnflights;

	@FindBy(xpath = "//span[text()='Non Stop']//ancestor::span//preceding-sibling::span//span[@class='box']")
	List<WebElement> nonstopflight;

	@FindBy(xpath = "//span[text()='1 Stop']//ancestor::span//preceding-sibling::span//span[@class='box']")
	List<WebElement> onestopflight;

	@FindBy(xpath = "//span[@class='whiteText blackFont fontSize16']")
	List<WebElement> bottomflightprice;

	@FindBy(xpath = "//div[@class='paneView'][1]//descendant::span[@class='outer']")
	List<WebElement> departflightbtn;

	@FindBy(xpath = "//div[@class='paneView'][2]//descendant::span[@class='outer']")
	List<WebElement> arrivalflightbtn;

	@FindBy(xpath = "//div[@class='paneView']//descendant::p[@class='blackText fontSize16 blackFont appendRight12']")
	List<WebElement> flightprice;

	@FindBy(xpath = "//span[@class='customRadioBtn']")
	List<WebElement> selectedflight;

	@FindBy(xpath = "//span[@class='whiteText fontSize22 boldFont']")
	WebElement totalprice;

	public flightResultPage() {
		PageFactory.initElements(driver, this);
	}

	public void countAvailableFlights() {
		long initheight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
		while (true) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long newheight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
			if (newheight == initheight) {
				break;
			}
			initheight = newheight;
		}
		System.out.println("Total departure flights available are: " + departureflights.size());
		System.out.println("Total Arrival flights available are: " + returnflights.size());
		return;
	}

	public void selectFilters() {
		driver.navigate().refresh();
		if (!(nonstopflight.size() == 0)) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", nonstopflight.get(0));
			for (int j = 0; j <= onestopflight.size() - 1; j++) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", onestopflight.get(j));
				if (!(onestopflight.get(j).isSelected())) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", onestopflight.get(j));
				}
			}
		} else {
			System.out.println("There are no non stop options available");
		}
		return;
	}

	public int generateRandomValue() {
		int min = 0;
		int max = 9;
		int randomvalue = (int) (Math.random() * (max - min + 1) + min);
		return randomvalue;
	}

	public void selectFlights() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				departflightbtn.get(generateRandomValue()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",departflightbtn.get(generateRandomValue()));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				arrivalflightbtn.get(generateRandomValue()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",arrivalflightbtn.get(generateRandomValue()));
		return;
	}

	public ArrayList<String> getFlightPrice() {
		ArrayList<String> flightprices = new ArrayList<String>();
		for (int i = 0; i <= selectedflight.size() - 1; i++) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedflight.get(i));
			if (selectedflight.get(i).isSelected()) {
				flightprices.add(flightprice.get(i).getText());
			}
		}
		for (int j = 0; j <= flightprices.size() - 1; j++)
			System.out.println(flightprices.get(j));
		return flightprices;
	}

	public ArrayList<String> getBottomFlightPrice() {
		ArrayList<String> bottomflightprices = new ArrayList<String>();
		for (int i = 0; i <= bottomflightprice.size() - 1; i++)
			bottomflightprices.add(bottomflightprice.get(i).getText());
		return bottomflightprices;
	}

	public String getTotalPrice() {
		String btmtotalprice = totalprice.getText();
		return btmtotalprice;
	}

	public int getPriceValue(String price) {
		String newprice = price.replaceAll("\\D", "");
		return Integer.parseInt(newprice);
	}
}
