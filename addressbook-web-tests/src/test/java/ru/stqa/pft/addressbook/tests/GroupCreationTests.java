package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(app.gd);
    int after = app.getGroupHelper().getGroupCount();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after, ++before);
  }
}
