package org.perscholas.librarydb.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserFormBean {

    private Integer id;

    @Email(message = "Please enter a valid email address")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
