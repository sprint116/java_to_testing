package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactHellper extends HandlerBase {

  public ContactHellper(WebDriver wd) {
    super(wd);
  }

  public void fillContactsForm(GroupData gd) {
    type(By.name("firstname"), gd.getName());
    type(By.name("lastname"), gd.getLastName());
    type(By.name("nickname"), gd.getNickname());
    type(By.name("address"), gd.getAddress());
    type(By.name("mobile"), gd.getMobile());
    type(By.name("email"), gd.getEmail());
    select(By.name("bday"), gd.getBirthdayDay());
    select(By.name("bmonth"), gd.getBirthdayMonth());
    type(By.name("byear"), gd.getBirthdayYear());
    type(By.name("address2"), gd.getAddress2());
  }


  public void submitContactsCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initContactsCreation() {
    click(By.linkText("add new"));


  }
}
