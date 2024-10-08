package dataBaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataTest {

	WebDriver driver = new ChromeDriver();
	String webSiteURL = "https://magento.softwaretestingboard.com/customer/account/create/";

	Connection con;
	Statement stmt;
	ResultSet rs;
	Random rand = new Random();
	int randomNumber = rand.nextInt(888);

	@BeforeTest
	public void mySetup() throws SQLException {
		// Open path between eclipse and mysql_workbech
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "Jarad1997");
	}

	@Test(priority = 1, enabled = false)
	public void addData() throws SQLException {

		int randomNumber = rand.nextInt(888);

		String query = "INSERT INTO customers (" + randomNumber
				+ ", customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES (502, 'Acme Corporation', 'Doe', 'John', '123-456-7890', '123 Elm St', 'Suite 500', 'New York', 'NY', '10001', 'USA', 1370, 50000.00)";

		stmt = con.createStatement();

		// insert query
		int rowInserted = stmt.executeUpdate(query);

		System.out.println(rowInserted);

	}

	@Test(priority = 2, enabled = false)
	public void updateData() throws SQLException {

		String query = "update customers set customerName ='dania' where customerNumber=" + randomNumber;

		stmt = con.createStatement();

		// insert query
		int rowInserted = stmt.executeUpdate(query);

		System.out.println(rowInserted);

	}

	@Test(priority = 3)
	public void getORraedData() throws SQLException {

		stmt = con.createStatement();
		// select record
		rs = stmt.executeQuery("select * from customers where customerNumber =" + randomNumber);

		while (rs.next()) {

			int customerNumber = rs.getInt("customerNumber");
			String customerName = rs.getString("customerName");

			System.out.println(customerNumber);
			System.out.println(customerName);

			String firstname = rs.getString("contactFirstName");
			String lastname = rs.getString("contactLastName");

			driver.get(webSiteURL);
			WebElement first_name = driver.findElement(By.id("firstname"));
			WebElement last_name = driver.findElement(By.id("lastname"));

			first_name.sendKeys(firstname);
			last_name.sendKeys(lastname);

		}

	}

	@Test(priority = 4, enabled = false)
	public void deleteData() throws SQLException {

		String query = "delete form customers where customerNumber =" + randomNumber;
		stmt = con.createStatement();

		int rowInserted = stmt.executeUpdate(query);

		System.out.println(rowInserted);
	}

}
