package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {
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
  public void testContactAddToGroup() {
    ContactData selectContact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();

    if (selectContact.getGroups().size() != 0) {
      group = selectContact.getGroups().iterator().next();
      app.contact().remFromGroup(group.getName(), selectContact.getId());
    }

    selectContact = app.db().contacts().iterator().next();
    Groups before = selectContact.getGroups();
    app.contact().addToGroup(selectContact, group.getName());
    selectContact = app.db().contacts().iterator().next();
    Groups after = selectContact.getGroups();
    assertThat(after, equalTo(before.withAdded(selectContact.getGroups().iterator().next())));
  }
}
