package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactsForm(ContactData cd, boolean creation) {
    type(By.name("firstname"), cd.getFirstName());
    type(By.name("lastname"), cd.getLastName());
    type(By.name("nickname"), cd.getNickname());
    type(By.name("address"), cd.getAddress());
    type(By.name("mobile"), cd.getMobile());
    type(By.name("email"), cd.getEmail());
    select(By.name("bday"), cd.getBirthdayDay());
    select(By.name("bmonth"), cd.getBirthdayMonth());
    type(By.name("byear"), cd.getBirthdayYear());
    type(By.name("address2"), cd.getAddress2());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(cd.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void submitContactsCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initContactsCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void buttonDelContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void deletionConfirmationContact() {
    wd.switchTo().alert().accept();
  }

  public void changeModificationContact() {
    click(By.cssSelector("img[alt=\"Edit\"]"));
  }

  public void submitContactModification() {
    click((By.xpath("//input[22]")));
  }
}
