package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm();
    app.submitGroupCreation();
    app.returnToGroupPage();
  }
}
