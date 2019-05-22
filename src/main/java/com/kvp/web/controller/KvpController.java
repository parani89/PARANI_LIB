package com.kvp.web.controller;

import com.kvp.service.UserActions;
import com.kvp.web.domain.Book;
import com.kvp.web.domain.BookMaster;
import com.kvp.web.domain.BookRack;
import com.kvp.web.domain.User;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, value = "/api")
@Api(tags="ACTIONS")
public class KvpController {

    private static final Logger LOGGER = LogManager.getLogger(KvpController.class);

    @Autowired
    private UserActions userActions;

    @RequestMapping("/addUser")
    public String addUser(@RequestParam(name="userId", required = true) int userId,
                          @RequestParam(name="firstName", required =  true) String firstName,
                          @RequestParam(name="lastName", required =  true) String lastName,
                          @RequestParam(name="category", required =  true) String category,
                          @RequestParam(name="gender", required =  true) String gender,
                          @RequestParam(name="bookLimit", required =  true) int bookLimit,
                          @RequestParam(name="dob", required =  true) String dob,
                          @RequestParam(name="doj", required =  true) String doj,
                          @RequestParam(name="dor", required =  false) String dor,
                          @RequestParam(name="crtUser", required =  true) String crtUser,
                          @RequestParam(name="updUser", required =  true) String updUser) throws ParseException {

        LOGGER.info("Before Adding User "+firstName);
        SimpleDateFormat formatter1=new SimpleDateFormat("dd-MM-yyyy");
        Date dateOfBirth = formatter1.parse(dob);
        Date dateOfJoin = formatter1.parse(doj);
        User user = new User(userId, firstName, lastName, category, gender, bookLimit, crtUser, updUser, dateOfBirth, dateOfJoin);

        userActions.addUserToDatabase(user);

        return user.getFirstName()+" Added";
    }

    @RequestMapping("/addBook")
    public String addBook(@RequestParam(name = "bookId", required = true) int bookId,
                          @RequestParam(name = "bookName", required = true) String bookName,
                          @RequestParam(name = "author", required = true) String author,
                          @RequestParam(name = "year", required = true) int year,
                          @RequestParam(name = "bookGroupId", required = true) int bookGroupId,
                          @RequestParam(name = "availability", required = true, defaultValue = "Y") String availability,
                          @RequestParam(name = "userHolding", required = false, defaultValue = "Y") String userHolding,
                          @RequestParam(name="crtUser", required =  true) String crtUser,
                          @RequestParam(name="crtUser", required =  true) String updUser
                          ) {

        LOGGER.info("Before Adding Book "+bookId);

        Book book = new Book(bookId, bookGroupId, availability, userHolding, crtUser, updUser);
        BookMaster bookMaster = new BookMaster(bookGroupId, bookName, author, year, crtUser, updUser);

        userActions.addBookToDatabase(book);
        userActions.addBookMasterToDatabase(bookMaster);

        return bookMaster.getBookName()+" Added to database";
    }

    @RequestMapping("/listUser")
    public List<User> listUser(@RequestParam(name="userId", defaultValue = "0", required = false) int userId,
                           @RequestParam(name="firstName", required = false) String firstName) {

        LOGGER.info("Before Listing User "+userId);
        LOGGER.debug("Before Listing User "+userId); // TODO Learn what is the diff b/w info debug and error
        return userActions.listUserFromDatabase(firstName, userId);
        
    }

    @RequestMapping("/listBookRack")
    public List<BookRack> listBookRack(@RequestParam(name="bookGrpId", defaultValue = "0", required = false) int bookGrpId,
                                       @RequestParam(name="bookName", required = false) String bookName) {

        LOGGER.info("Before Listing User "+bookGrpId);
        LOGGER.debug("Before Listing User "+bookName); // TODO Learn what is the diff b/w info debug and error
        return userActions.listBookFromDatabase(bookName, bookGrpId);

    }
}