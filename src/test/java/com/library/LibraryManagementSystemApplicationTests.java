package com.library;

import com.library.domain.Book;
import com.library.domain.BookBorrow;
import com.library.service.BookService;
import com.library.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@SpringBootTest
class LibraryManagementSystemApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Test
    void testlogin() {
        Book book = new Book();
//        book.setBookname("收");
        book.setDescription("本");
        book.setBookname("");
        book.setIsbn("");
        System.out.println(book);
        List<Book> books = bookService.selectByCondition(book);
        System.out.println(books);
    }

    private long time = 1000*60*60*24;//单位：毫秒
    private String signature = "admin";

    @Test
    public void jwt(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username","chenyi")
                .claim("password","bugaosunimimaxixixi")
                .setSubject("admin-test")//可以自定义
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        System.out.println(jwtToken);
    }

    @Test
    public void parse(){
        BookBorrow bookBorrow = new BookBorrow();
        bookBorrow.setBorrower("xiaohei");
        bookBorrow.setId(1);
        bookBorrow.setBorrowTime("2020-01-01");
        bookBorrow.setReturnTime("2020-03-01");
        bookService.borrowBook(bookBorrow);
    }

    @Test
    public void time(){
        String hi = "sss";
        System.out.println("chen{hi}yi");
    }





}
