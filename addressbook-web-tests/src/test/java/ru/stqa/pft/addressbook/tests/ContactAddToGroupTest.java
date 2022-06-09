package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactAddToGroupTest extends TestBase{
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
  public void testContactAddToGroup() {
    ContactData selectContact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();
    System.out.println(selectContact.getGroups());
    System.out.println(group);


    app.contact().selectGroup(group.getName());

    /*app.goTo().mainPage();
    app.contact().selectContactById(selectContact.getId());
    app.contact().addToGroup(group.getName());
    app.contact().returnToGroupPage(group.getName());*/

    app.contact().addToGroup(selectContact,group.getName());
  }
}
