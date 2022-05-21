package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public GroupHelper groupHelper;

  public void fillContactsForm(ContactData cd, boolean creation) {
    if (creation) {
      if (wd.findElements(By.xpath("//*[@name='new_group']/option")).size() <= 1) {
        groupHelper = new GroupHelper(wd);
        click(By.linkText("groups"));
        groupHelper.create(app.gd);
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

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void buttonDelContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void deletionConfirmationContact() {
    wd.switchTo().alert().accept();
  }

  public void changeModificationContact(int index) {
    wd.findElement(By.xpath("//tr[" + (index + 2) + "]/td[8]/a")).click();
  }

  public void submitContactModification() {
    click((By.xpath("//input[22]")));
  }

  public void create(ContactData contact) {
    initContactsCreation();
    fillContactsForm(contact, true);
    submitContactsCreation();
  }

  public void modify(int randomContact, ContactData contact) {
    changeModificationContact(randomContact);
    fillContactsForm(contact, false);
    submitContactModification();
    app.goTo().MainPage();
  }

  public void delete(int randomContact) {
    selectContact(randomContact);
    buttonDelContact();
    deletionConfirmationContact();
    app.goTo().MainPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    buttonDelContact();
    deletionConfirmationContact();
    app.goTo().MainPage();
  }

  /*public boolean isThereAContact() {
    return !isElementPresent(By.name("selected[]"));
  }*/

  /*public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }*/

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String Address = element.findElement(By.xpath("td[4]")).getText();
      String email = element.findElement(By.xpath("td[5]")).getText();
      String mobile = element.findElement(By.xpath("td[6]")).getText();
      ContactData contact = new ContactData()
              .withId(id)
              .withFirstName(firstName)
              .withLastName(lastName)
              .withAddress(Address)
              .withMobile(mobile)
              .withEmail(email);
      contacts.add(contact);
    }
    return contacts;
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String Address = element.findElement(By.xpath("td[4]")).getText();
      String email = element.findElement(By.xpath("td[5]")).getText();
      String mobile = element.findElement(By.xpath("td[6]")).getText();
      ContactData contact = new ContactData()
              .withId(id)
              .withFirstName(firstName)
              .withLastName(lastName)
              .withAddress(Address)
              .withMobile(mobile)
              .withEmail(email);
      contacts.add(contact);
    }
    return contacts;
  }
}
