package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactHellper {
  private WebDriver wd;

  public ContactHellper(WebDriver wd) {
    this.wd = wd;
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

  public void submitContactsCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void initContactsCreation() {
    wd.findElement(By.linkText("add new")).click();


  }
}
