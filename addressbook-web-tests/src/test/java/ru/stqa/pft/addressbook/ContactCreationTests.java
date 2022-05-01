package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests {
  private WebDriver wd;
  GroupData gd = new GroupData(
          "Test1",
          "Test1",
          "Test1",
          "TestName",
          "LastName",
          "testing",
          "Testing",
          "+79999999999",
          "test@test.test",
          "17",
          "January",
          "1990",
          "TestingAddress"
  );

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.get("http://localhost/addressbook");
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.id("LoginForm")).submit();
  }

  @Test
  public void testUntitledTestCase() {
    initContactsCreation();
    fillContactsForm(gd);
    submitContactsCreation();
    returnToMainPage();
  }

  private void returnToMainPage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void submitContactsCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void fillContactsForm(GroupData gd) {
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(gd.getName());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(gd.getLastName());
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys(gd.getNickname());
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(gd.getAddress());
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(gd.getMobile());
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(gd.getEmail());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(gd.getBirthdayDay());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(gd.getBirthdayMonth());
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(gd.getBirthdayYear());
    wd.findElement(By.name("address2")).click();
    wd.findElement(By.name("address2")).sendKeys(gd.getAddress2());
  }

  private void initContactsCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    wd.findElement(By.linkText("Logout")).click();
    wd.quit();
  }
}
