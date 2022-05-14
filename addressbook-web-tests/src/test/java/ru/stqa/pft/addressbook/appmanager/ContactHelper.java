package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public GroupHelper groupHelper;

  public void fillContactsForm(ContactData cd, boolean creation) {
    if (creation) {
      if (wd.findElements(By.xpath("//*[@name='new_group']/option")).size() <= 1) {
        groupHelper = new GroupHelper(wd);
        GroupData gd = new GroupData(
                "Test1",
                "Test2",
                "Test3"
        );
        click(By.linkText("groups"));
        groupHelper.createGroup(gd);
        initContactsCreation();
        new Select(wd.findElement(By.xpath("//*[@name='new_group']"))).selectByIndex(1);
      } else {
        new Select(wd.findElement(By.xpath("//*[@name='new_group']"))).selectByIndex(1);
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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
  }


  public void submitContactsCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initContactsCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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

  public void createContact(ContactData contact) {
    initContactsCreation();
    fillContactsForm(contact, true);
    submitContactsCreation();
  }

  public boolean isThereAContact() {
    return !isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      System.out.println(firstName);
      //System.out.println(element.getText());
      ContactData contact = new ContactData(
              null,
              null,
              null,
              null,
              null,
              null,
              null,
              null,
              null,
              null);
      contacts.add(contact);
    }
    return contacts;
  }
}
