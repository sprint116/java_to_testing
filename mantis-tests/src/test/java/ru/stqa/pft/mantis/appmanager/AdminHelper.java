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

  public void goToUserManagePage(int id){
    wd.get( app.getProperty("web.baseUrl") + "/manage_overview_page.php");
    wd.findElement(By.cssSelector(String.format("a[href='/%s/manage_user_page.php']", app.getProperty("web.version")))).click();
    wd.findElement(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id))).click();
  }

  public void resetUserPass(){
    click(By.cssSelector("input[value='Сбросить пароль']"));
  }
}