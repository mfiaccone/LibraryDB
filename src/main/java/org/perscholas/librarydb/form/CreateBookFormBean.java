package org.perscholas.librarydb.form;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookFormBean {

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    private String isbn;

    @NotEmpty
    private String genre;

    private Integer bookId;

    @NotNull(message = "Available copies is required")
    @Min(value = 0, message = "Available copies must be at least 0")
    private Integer availableCopies = 0; // Initialize with a default value


}
