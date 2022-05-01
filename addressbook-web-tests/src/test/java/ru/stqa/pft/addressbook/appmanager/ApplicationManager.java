package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;
  public GroupData gd = new GroupData(
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

  public void init() {
    wd = new ChromeDriver();
    //wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook");
    login("admin", "secret");
  }

  public void login(String username, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[3]")).click();
  }

  public void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  public void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  public void fillGroupForm() {
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(gd.getGname());
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys(gd.getHeader());
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys(gd.getFooter());
  }

  public void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }

  public void deleteSelectedGroups() {
    wd.findElement(By.name("delete")).click();
  }

  public void stop() {
    wd.findElement(By.linkText("Logout")).click();
    wd.quit();
  }

  public void fillContactsForm(GroupData gd) {
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

  public void returnToMainPage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void submitContactsCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void initContactsCreation() {
    wd.findElement(By.linkText("add new")).click();
  }
}
