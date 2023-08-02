package com.java20.selenium.AUTOMATION_project2;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

public class Aj {

	@Test
	static void windowshandle() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/account/about/");
		driver.findElement(By.xpath("//a[@aria-label='About Google']")).click();
		// System.out.println(driver.getTitle());
		Set<String> ids = driver.getWindowHandles();
		for (String i : ids) {

			String printttile = driver.switchTo().window(i).getTitle();

			System.out.println(printttile);
		}
	}

	static void mtrip() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("span.ic_circularclose_grey")).click();
		driver.findElement(By.xpath("//label[@for='departure']")).click();
		int alldates = driver
				.findElements(By.xpath("//div[@class='DayPicker-Months']/div[1] //div[@class='DayPicker-Day']")).size();
		System.out.println(alldates);
		List<WebElement> dates = driver
				.findElements(By.xpath("//div[@class='DayPicker-Months']/div[1] //div[@class='DayPicker-Day']"));

		for (int i = 0; i < dates.size(); i++) {
			String[] rdate = dates.get(i).getAccessibleName().split(" ");

			if (rdate[2].equalsIgnoreCase("29")) {
				dates.get(i).click();
				break;
			}
		}
	}

	@Test
	static void babu() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(driver.findElements(By.tagName("a")).size());
		for (int i = 0; i < links.size(); i++) {
			links.get(i).getAttribute("href");
			System.out.println(links.get(i).getText());
			System.out.println(links.get(i).getAttribute("href"));

		}

	}

	@Test
	static void msrtc() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://msrtc.maharashtra.gov.in/");

		driver.findElement(By.id("journeydate")).click();
		WebElement rushi = driver.findElement(By.className("datepick-new-month"));
		Select s = new Select(rushi);
		s.selectByVisibleText("May");
		List<WebElement> date = driver.findElements(By.cssSelector(".datepick-days-cell "));
		for (int i = 0; i < date.size(); i++) {
			String actualdate = date.get(i).getText();
			if (actualdate.contains("5")) {
				date.get(i).click();
				System.out.println("test case passed");
				Assert.assertTrue(true);
			}
		}

	}

	@Test(dependsOnMethods= {"rushi"})
	void addtocart() {
		String[] items = { "Cauliflower", "Cashews", "Beetroot", "Mango", "Raspberry" };

		System.setProperty("WebDriver.Chrome.Driver", "C:\\java8\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		int j = 0;

		List<WebElement> products = driver.findElements(By.xpath("//h4[@class='product-name']"));

		for (int i = 0; i < products.size(); i++) {
			String name = driver.findElements(By.xpath("//h4[@class='product-name']")).get(i).getText();
			String[] t = name.split("-");
			String updatename = t[0].trim();
			List<String> ritems = Arrays.asList(items);
			// ArrayList<String> OriginalList=new ArrayList<String>();

			if (ritems.contains(updatename)) {
				j++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (j == items.length)

					break;
			}

		}
		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.xpath("//button[@class='promoBtn']")).click();
		System.out.println(driver.findElement(By.cssSelector(".promoInfo")).getText());
	}

	@Test
	static void alerts() {
		System.setProperty("WebDriver.Chrome.Driver", "C:\\java8\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.id("name")).sendKeys("ajay");
		driver.findElement(By.id("alertbtn")).click();
		// System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}

	@Test
	public void cricbuz() {
		System.setProperty("webdiver.chrome.driver", "\"C:\\java8\\chromedriver_win32\\chromedriver.exe\"");
		WebDriver driver = new ChromeDriver();
		int sum = 0;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.cricbuzz.com/live-cricket-scorecard/53357/eng-vs-aus-3rd-test-the-ashes-2023");
		WebElement inning = driver.findElement(By.xpath("(//div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'])[7]"));
		List<WebElement> runs = inning
				.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)"));
		for (int i = 0; i < runs.size() - 2; i++) {
			String truns = runs.get(i).getText();
			int totalruns = Integer.parseInt(truns);
			sum = totalruns + sum;
		}
		System.out.println(sum);
		String extras = driver.findElement(By.xpath(
				"(//div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'])[7] //div[text()='Extras']/following-sibling::div[1]"))
				.getText();
		int ex = Integer.parseInt(extras);
		System.out.println(ex);
		String extra = inning.findElement(By.xpath("//div[text()='Extras']/following-sibling::div[1]")).getText();
		System.out.println(extra);
		int ext = Integer.parseInt(extra);
		System.out.println(sum = sum + ex);
		String total = driver.findElement(By.xpath(
				"(//div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'])[7] //div[text()='Total']/following-sibling::div[1]"))
				.getText();
		System.out.println(total);
		int Total = Integer.parseInt(total);
		if (sum == Total) {
			System.out.println("testcase pass");
		} else {
			System.out.println("testcase fail");
		}
	}

	@Test
	static void linkcount() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		System.out.println(driver.findElements(By.tagName("a")).size());
		WebElement footer = driver.findElement(By.xpath("//div[@id='gf-BIG']"));
		System.out.println(footer.findElements(By.tagName("a")).size());
		WebElement firstcolumn = footer.findElement(By.xpath("//div[@id=\"gf-BIG\"]//table/tbody/tr/td[1]"));
		System.out.println(firstcolumn.findElements(By.tagName("a")).size());

		for (int i = 0; i < firstcolumn.findElements(By.tagName("a")).size(); i++) {
			String keys1 = Keys.chord(Keys.CONTROL, Keys.ENTER);
			firstcolumn.findElements(By.tagName("a")).get(i).sendKeys(keys1);

			Thread.sleep(5000);
		}

		Set<String> abc = driver.getWindowHandles();
		for (String p : abc) {
			String linkltab = driver.switchTo().window(p).getTitle();
			System.out.println(linkltab);
		}
	}

	static void autodropdown1() {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://ksrtc.in/oprs-web/");
		driver.findElement(By.id("fromPlaceName")).sendKeys("beng");
		// System.out.println(driver.findElement(By.id("fromPlaceName")).getText());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String box = "return document.getElementById(\"fromPlaceName\").value;";
		String txt = (String) js.executeScript(box);
		// System.out.println(txt);

		while (!txt.equalsIgnoreCase("BENGALURU INTERNATION AIRPORT")) {
			driver.findElement(By.xpath("//input[@id='fromPlaceName']")).sendKeys(Keys.ARROW_DOWN);
			txt = (String) js.executeScript(box);
		}
		System.out.println(txt);

	}

	@Test
	void ajay() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();

		Select s = new Select(driver.findElement(By.cssSelector("#dropdown-class-example")));
		s.selectByValue("option1");
		s.selectByIndex(2);
		s.selectByVisibleText("Option3");
	}

	@Test
	void rushi() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://ksrtc.in/oprs-web/");
		driver.findElement(By.id("fromPlaceName")).sendKeys("beng");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String box = "return document.getElementById('fromPlaceName').value;";
		String dropdown = (String) js.executeScript(box);

		while (!dropdown.equalsIgnoreCase("BENGALURU INTERNATION AIRPORT")) {
			driver.findElement(By.xpath("//input[@id='fromPlaceName']")).sendKeys(Keys.ARROW_DOWN);
			dropdown = (String) js.executeScript(box);
		}System.out.println(dropdown);

	}
}
