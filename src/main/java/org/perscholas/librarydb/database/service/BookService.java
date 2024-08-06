package org.perscholas.librarydb.database.service;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.librarydb.database.dao.BookDAO;
import org.perscholas.librarydb.database.entity.Book;
import org.perscholas.librarydb.form.CreateBookFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
public class BookService {

    @Autowired
    private BookDAO bookDao;

    public Book createBook(CreateBookFormBean form, MultipartFile cover) {

        Book book = new Book();
        book.setTitle(form.getTitle());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        book.setGenre(form.getGenre());
        book.setAvailableCopies(form.getAvailableCopies());

        String saveFilename = "./src/main/webapp/pub/image/" + cover.getOriginalFilename();

        try {
            Files.copy(cover.getInputStream(), Paths.get(saveFilename), StandardCopyOption.REPLACE_EXISTING);
        } catch ( Exception e ) {
            log.error("Unable to finish reading file", e);
        }

        String url = "/pub/image/" + cover.getOriginalFilename();
        book.setCoverImageUrl(url);

        bookDao.save(book);

        return book;
    }
}
