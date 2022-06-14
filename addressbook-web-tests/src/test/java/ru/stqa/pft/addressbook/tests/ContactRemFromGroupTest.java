package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemFromGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
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
    GroupData group;

    if (selectContact.getGroups().size() == 0) {
      group = app.db().groups().iterator().next();
      app.contact().addToGroup(selectContact, group.getName());
    } else {
      group = selectContact.getGroups().iterator().next();
    }

    selectContact = app.db().contacts().iterator().next();
    Groups before = selectContact.getGroups();
    app.contact().remFromGroup(group.getName(), selectContact.getId());
    selectContact = app.db().contacts().iterator().next();
    Groups after = selectContact.getGroups();
    assertThat(after, equalTo(before.without(before.iterator().next())));
  }
}
