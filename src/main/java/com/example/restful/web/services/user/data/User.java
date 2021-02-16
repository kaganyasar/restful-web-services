package com.example.restful.web.services.user.data;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@JsonFilter("UserFilterProvider")
@ApiModel(description = "Details about user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, max = 32, message = "Character size should be in 2 - 32")
    @ApiModelProperty(notes = "Character size should be in 2 - 32")
    private String name;

    @Size(min = 8, max = 16, message = "Password should be alphanumeric and character size should be in 8 - 16")
    @ApiModelProperty(notes = "Password should be alphanumeric and character size should be in 8 - 16")
    private String password;

    private Role role;

    @Past(message = "Birth date should be in the past")
    @ApiModelProperty(notes = "Birth date should be in the past")
    private Date birthDate;

    public User() {
    }

    public User(Integer id, String name, String password, Role role, Date birthDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
