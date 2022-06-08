package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;
import java.util.stream.Collectors;

public class ContactRemFromGroupTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){
      app.contact().create(app.cd);
      app.goTo().mainPage();
    }
    if (app.db().groups().size() == 0) {
      app.group().create(app.gd);
    }
  }
@Test
  public void testContactRemFromGroup() {
  ContactData selectContact = app.db().contacts().iterator().next();

    if(selectContact.getGroups().size() == 0){
      System.out.println("Not found groups");
      //Тут добавить сценарий добавления контакта в группу
  }
    Set<String> groupName = selectContact.getGroups().stream().map((g) -> g.getName()).collect(Collectors.toSet());
  System.out.println(groupName);
  }
}
