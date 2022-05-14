package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(app.gd);
      before++;
    }
    int randomGroup = (int) (Math.random() * before);
    app.getGroupHelper().selectGroup(randomGroup);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(app.gd);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after, before);
  }
}
