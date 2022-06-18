package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void loginUser(String username, String password) {
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Вход']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Вход']"));
  }

  public String findConfirmationResetLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression res = VerbalExpression.regex().find("Your password has been reset. Please visit the following URL to set a new one").nonSpace().oneOrMore().build();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
