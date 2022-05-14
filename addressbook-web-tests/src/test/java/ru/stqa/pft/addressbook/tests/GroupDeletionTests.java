package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(app.gd);
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int randomGroup = (int) (Math.random() * before.size());
    app.getGroupHelper().selectGroup(randomGroup);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    app.getNavigationHelper().logout();
    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
