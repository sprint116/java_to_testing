package ru.stqa.pft.mantis.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")

public class UserData {
    @Id @Column(name = "id") private int id = Integer.MAX_VALUE;
    @Column(name = "username") private String username= "";
    @Column(name = "email") private String email= "";
    @Column(name = "enabled") private int enabled = 0;
    @Column(name = "access_level") private int access_level = 0;

    public UserData withId(int id) {
      this.id = id;
      return this;
    }

    public UserData withUsername(String username) {
      this.username = username;
      return this;
    }

    public UserData withEmail(String email) {
      this.email = email;
      return this;
    }

  public int getId() {
    return id;
  }

    public String getUsername() {
      return username;
    }

    public String getEmail() {
      return email;
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id && Objects.equals(username, userData.username) && Objects.equals(email, userData.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email);
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
