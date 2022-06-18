package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{

  int n = 1;

  @BeforeMethod
  public void startMailServer() throws MessagingException, IOException {
    app.mail().start();
    /*if (app.db().users().size() == 0){
      app.registration().registrationUser();
      n = 3;
    }*/
  }

  @Test
  public void testResetPassword() throws MessagingException, IOException {
    UserData selectUser = app.db().users().iterator().next();

    String username = selectUser.getUsername();
    String email = selectUser.getEmail();
    String password ="password";

    app.admin().loginAdmin();
    app.admin().goToUserManagePage(selectUser.getId());
    app.admin().resetUserPass();

    List<MailMessage> mailMessages = app.mail().waitForMail(n, 10000);
    String confirmationLink = app.userHelper().findConfirmationResetLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(username, password));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}

