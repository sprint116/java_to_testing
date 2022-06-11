package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
    //attach(By.name("photo"), cd.getPhoto());
    type(By.name("home"), cd.getHomePhone());
    type(By.name("work"), cd.getWorkPhone());
    type(By.name("address"), cd.getAddress());
    type(By.name("mobile"), cd.getMobile());
    type(By.name("email"), cd.getEmail());
    type(By.name("email2"), cd.getEmail2());
    type(By.name("email3"), cd.getEmail3());
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

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector(String.format("input[id='%s']",id))).click();
  }

  public void addToGroup(String value){
    select(By.name("to_group"), value);
    click(By.xpath("//input[@name='add']"));
  }

  public void selectGroup(String value){
    select(By.name("group"), value);
  }

  public void selectAllGroup(){
    select(By.name("group"), "[all]");
  }

  public void removeFromGroup(){
    click(By.xpath("//input[@name='remove']"));
  }

  public void returnToGroupPage(String value) {
    click(By.linkText(String.format("group page \"%s\"",value)));
  }

  public void buttonDelContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void deletionConfirmationContact() {
    wd.switchTo().alert().accept();
  }

  public void changeModificationContactById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click((By.xpath("//input[22]")));
  }

  public void create(ContactData contact) {
    app.goTo().mainPage();
    selectAllGroup();
    initContactsCreation();
    fillContactsForm(contact, true);
    submitContactsCreation();
    contactCache = null;
    app.goTo().mainPage();
  }

  public void modify(ContactData contact) {
    app.goTo().mainPage();
    selectAllGroup();
    changeModificationContactById(contact.getId());
    fillContactsForm(contact, false);
    submitContactModification();
    contactCache = null;
    app.goTo().mainPage();
  }

  public void delete(ContactData contact) {
    app.goTo().mainPage();
    selectAllGroup();
    selectContactById(contact.getId());
    buttonDelContact();
    deletionConfirmationContact();
    contactCache = null;
    app.goTo().mainPage();
  }

  public void addToGroup(ContactData contact, String group){
    app.goTo().mainPage();
    selectAllGroup();
    selectContactById(contact.getId());
    addToGroup(group);
    returnToGroupPage(group);
  }

  public void remFromGroup(String name, int id){
    app.goTo().mainPage();
    selectGroup(name);
    selectContactById(id);
    removeFromGroup();
    returnToGroupPage(name);
  }

  /*public boolean isThereAContact() {
    return !isElementPresent(By.name("selected[]"));
  }*/

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String Address = element.findElement(By.xpath("td[4]")).getText();
      String allEmails = element.findElement(By.xpath("td[5]")).getText();
      String allPhones = element.findElement(By.xpath("td[6]")).getText();

      contactCache.add(new ContactData()
              .withId(id)
              .withFirstName(firstName)
              .withLastName(lastName)
              .withAddress(Address)
              .withAllPhones(allPhones)
              .withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact){
    changeModificationContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFirstName(firstname)
            .withLastName(lastname)
            .withHomePhone(home)
            .withMobile(mobile)
            .withWorkPhone(work)
            .withAddress(address)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3);
  }
}
