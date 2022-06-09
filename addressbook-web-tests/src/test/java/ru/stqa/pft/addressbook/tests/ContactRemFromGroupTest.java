package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

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
      GroupData group = app.db().groups().iterator().next();
      app.contact().addToGroup(selectContact,group.getName());
  }
  GroupData selectGroup = selectContact.getGroups().iterator().next();
  app.goTo().mainPage();
  app.contact().selectGroup(selectGroup.getName());
  app.contact().selectContactById(selectContact.getId());
  app.contact().removeFromGroup();
  app.contact().returnToGroupPage(selectGroup.getName());

  //app.goTo().mainPage();



  }


}
