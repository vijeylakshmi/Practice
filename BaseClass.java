package org.BAse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	public static void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\KiMNanA\\eclipse-workspace\\SEcondLearn\\TestNG\\src\\main\\resources\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	public static void windowMaximize() {
		driver.manage().window().maximize();
	}

	public static void launchUrl(String url) {
		driver.get(url);
	}

	public static void pageTitle() {
		String title = driver.getTitle();
		System.out.println(title);
	}

	public static void pageUrl() {
		String currenturl = driver.getCurrentUrl();
		System.out.println(currenturl);
	}

	public static void passtText(String txt, WebElement ele) {
		ele.sendKeys(txt);
	}

	public static void closeEntireBrowser() {
		driver.quit();
	}

	public static void clickBtn(WebElement ele) {
		ele.click();
	}

	public static void screenShot(String imgName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File image = ts.getScreenshotAs(OutputType.FILE);
		File f = new File("location+ imgName.png");
		FileUtils.copyFile(image, f);
	}

	public static Actions a;

	public void moveTheCursor(WebElement targetWebElement) {
		a = new Actions(driver);
		a.moveToElement(targetWebElement).perform();
	}

	public void dragandDrop(WebElement dragWebElement) {
		a = new Actions(driver);
		a.dragAndDrop(dragWebElement, dragWebElement);
	}

	public void rightClick(WebElement ContextWebElement) {
		a = new Actions(driver);
		a.contextClick(ContextWebElement);
	}

	public void doubleClick(WebElement doubleWebElement) {
		a = new Actions(driver);
		a.doubleClick(doubleWebElement);
	}

	public static Robot r;

	protected void pressKey(int keyEvent) throws AWTException {
		r = new Robot();
		 r.keyPress(keyEvent);
	}

	public static JavascriptExecutor js;

	private void scrollThePage(WebElement tarWeb) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", tarWeb);
	}

	private void scroll(WebElement tarPage) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", tarPage);
	}

	private void excelRead(String sheetName, int rownum, int cellnum) throws IOException {
		File f = new File("excellocation.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet mySheet = wb.getSheet("Data");
		Row r = mySheet.getRow(rownum);
		Cell c = r.getCell(cellnum);
		int cellType = c.getCellType();

		String value = " ";
		if (cellType == 1) {
			String value2 = c.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(c)) {
			Date dd = c.getDateCellValue();
			SimpleDateFormat s = new SimpleDateFormat(value);
			String format = s.format(dd);
		} else {
			double d = c.getNumericCellValue();
			long l = (long) d;
			String valueOf = String.valueOf(l);
		}
	}

	private void createNewExcelFile(String writeData, int rownum, int cellnum) throws IOException {
		File f = new File("Excel location.xlsx");
		Workbook wb = new XSSFWorkbook();
		Sheet newSheet = wb.createSheet("Datas");
		Row newRow = newSheet.createRow(rownum);
		Cell newCell = newRow.createCell(cellnum);
		newCell.setCellValue(writeData);
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
	}

	private void createCell(int getrow, int createcell, String newData) throws IOException {
		File f = new File("Excel location.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook();
		Sheet s = wb.getSheet("Datas");
		Row r = s.getRow(getrow);
		Cell c = r.getCell(createcell);
		c.setCellValue(newData);
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
	}

	private void updateDateToParticularcell(int gettheRow, int gettheCell, String existingData, String writenewData)
			throws IOException {
		File f = new File("Excel location.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook();
		Sheet s = wb.getSheet("Datas");
		Row r = s.getRow(gettheRow);
		Cell c = r.getCell(gettheCell);
		String stringCellValue = c.getStringCellValue();
		if (stringCellValue.equals(existingData)) {
			c.setCellValue(writenewData);
		}
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
	}

}
