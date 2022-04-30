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
    fillContactsForm(new ContactsGroup("TestName", "LastName", "testing", "Testing", "+79999999999", "test@test.test", "17", "January", "1990", "TestingAddress"));
    submitContactsCreation();
    returnToMainPage();
  }

  private void returnToMainPage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void submitContactsCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void fillContactsForm(ContactsGroup contactsGroup) {
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactsGroup.name());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactsGroup.lastName());
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys(contactsGroup.nickname());
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(contactsGroup.address());
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(contactsGroup.mobile());
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contactsGroup.email());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactsGroup.birthdayDay());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactsGroup.birthdayMonth());
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(contactsGroup.birthdayYear());
    wd.findElement(By.name("address2")).click();
    wd.findElement(By.name("address2")).sendKeys(contactsGroup.address2());
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
