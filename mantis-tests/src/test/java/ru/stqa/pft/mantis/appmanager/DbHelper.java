package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

public class DbHelper {
  private ApplicationManager app;
  private final SessionFactory sessionFactory;

  public DbHelper(ApplicationManager app) {
    this.app = app;
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
  }

  public Users users(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData WHERE access_level < 90 AND enabled = 1").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }
}
