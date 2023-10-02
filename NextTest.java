package org.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.BAse.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NextTest extends BaseClass {

	@BeforeClass
	public void launchtheBrowser() {
		launchBrowser();
		windowMaximize();
		launchUrl("https://www.amazon.in/");

	}

	@BeforeMethod
	public void amazon() {
	}

	@Test
	private void search() throws InterruptedException {

		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("iphone 13");
		driver.findElement(By.id("nav-search-submit-button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[5]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);

		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));

		System.out.println(driver.getCurrentUrl());
		Thread.sleep(2500);
		driver.findElement(By.xpath("//*[@id='contextualIngressPtLabel_deliveryShortLine']")).click();
		Thread.sleep(5000);
		// enter pincode for delivery
		driver.findElement(By.id("GLUXZipUpdateInput")).sendKeys("560100");

		driver.findElement(By.xpath("//*[@id=\"GLUXZipUpdate\"]/span/input")).click();
		Thread.sleep(3000);

		/*
		 * driver.findElement(By.xpath(
		 * "//*[@id='contextualIngressPtLabel_deliveryShortLine']")).click();
		 * driver.findElement(By.xpath("//*[@id=\"GLUXZipUpdateInput\"]")).clear();
		 * Thread.sleep(2500);
		 * driver.findElement(By.id("GLUXZipUpdateInput")).sendKeys("222222");
		 * 
		 * driver.findElement(By.xpath("//*[@id=\"GLUXZipUpdate\"]/span/input")).click()
		 * ;
		 */
		//Buynow
		driver.findElement(By.id("buy-now-button")).click();
		//Login details
		driver.findElement(By.id("ap_email")).sendKeys("vijikanmani18@gmail.com");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("Mydearhus");
		driver.findElement(By.id("signInSubmit")).click();
		
		//Checkout page
		driver.findElement(By.xpath("//input[@data-testid='Address_selectShipToThisAddress']")).click();
		Thread.sleep(2500);
		driver.findElement(By.id("addressChangeLinkId")).click();
		driver.findElement(By.xpath("(//input[@type='radio'])[3]")).click();
	}

	@Test(enabled = false)
	private void selectdelivery() throws InterruptedException {
		Thread.sleep(3000);

	}

	@Test(enabled = false)

	private void login() throws InterruptedException {
		driver.findElement(By.name("email")).sendKeys("9500935550");
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2500);
		driver.findElement(By.id("ap_password")).sendKeys("Mydearhus");
		driver.findElement(By.id("signInSubmit")).click();

	}

	@AfterClass
	public void closeBrowser() {
		// closeEntireBrowser();
	}
}
