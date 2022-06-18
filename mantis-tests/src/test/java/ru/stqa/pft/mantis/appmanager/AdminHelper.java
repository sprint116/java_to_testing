package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAdmin() {
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), app.getProperty("web.adminLogin"));
    click(By.cssSelector("input[value='Вход']"));
    type(By.name("password"), app.getProperty("web.adminPass"));
    click(By.cssSelector("input[value='Вход']"));
  }

}