package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(app.gd);
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(app.gd);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    app.getNavigationHelper().logout();
  }
}
