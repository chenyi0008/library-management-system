package com.library.controller;

import com.library.domain.*;
import com.library.service.BookService;
import com.library.util.JwtUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    

    @GetMapping
    public Result selectAll(){
        List<Book> books = bookService.selectAll();
        if(books == null)return new Result(Code.ERR,"查询失败");
        return new Result(Code.OK,books,"查询成功");
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        Book book = bookService.selectById(id);
        if(book == null)return new Result(Code.ERR,"查询失败");
        else return new Result(Code.OK,book,"查询成功");
    }

    @GetMapping("/page")
    public Result selectByPage(@RequestParam String currentPage,@RequestParam String pageSize){
        int currentPage_num = Integer.parseInt(currentPage);
        int pageSize_num = Integer.parseInt(pageSize);
        PageBean<Book> pageBean = bookService.selectByPage(currentPage_num, pageSize_num);
        return new Result(Code.OK,pageBean,"查询成功");
    }

    @GetMapping("/status")
    public Result selectStatus(){
        List<BookStatus> books = bookService.selectStatus();
        return new Result(Code.OK,books,"查询成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        int num = bookService.deleteById(id);
        if(num == 0)return new Result(Code.ERR,"删除失败");
        else return new Result(Code.OK,"删除成功");
    }

    @PostMapping()
    public Result preserve(@RequestBody Book book){
        int num = bookService.preserve(book);
        if(num > 0)return new Result(Code.OK,"保存成功");
        return new Result(Code.ERR,"保存失败");
    }

    @PutMapping
    public Result update(@RequestBody Book book){
        int num = bookService.update(book);
        if(num > 0)return new Result(Code.OK,"保存成功");
        return new Result(Code.ERR,"保存失败");
    }

    @PostMapping("/condition")
    public Result selectByCondition(@RequestBody Book book){
        List<Book> books = bookService.selectByCondition(book);
        if(books == null)return new Result(Code.DEFAULT,"查询失败");
        if(books.size() == 0)return new Result(Code.ERR,books,"暂无符合此条件的书籍");
        return new Result(Code.OK,books,"查询成功");
    }

    @PostMapping("/borrow")
    public Result borrowBook(@RequestBody BookBorrow bookBorrow){
        int id = bookBorrow.getId();
        BookAll bookAll = bookService.selectAllById(id);
        int status = bookAll.getStatus();
        if(status == 1)return new Result(Code.ERR,"此书已外借");
        bookService.borrowBook(bookBorrow);
        String borrower = bookBorrow.getBorrower();
        String borrowTime = bookBorrow.getBorrowTime();

        Log log = new Log();
        log.setBorrower(borrower);
        log.setDate(borrowTime);
        log.setBorrowBook(1);

        String bookname = bookAll.getBookname();
        log.setLog(log.createLog(bookname));
        bookService.log(log);
        return new Result(Code.OK,"借书成功");
    }

    @GetMapping("/return")
    public Result returnBook(@Param("id") int id, HttpServletRequest request){
        String token = request.getHeader("token");
        String username = JwtUtil.getUsername(token);
        BookAll bookAll = bookService.selectAllById(id);
        String s = bookAll.getBorrower();
        if(!s.equals(username))return new Result(Code.ERR,"还书失败，您不是借书者本人");
        bookService.returnBook(id);
        String bookname = bookAll.getBookname();
        Log log = new Log();
        log.setBorrower(username);
        log.setReturnBook(1);
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String time = ft.format(date);
        log.setDate(time);
        log.setLog(log.createLog(bookname));
        bookService.log(log);
        return new Result(Code.OK,"还书成功");
    }

    @GetMapping("/bill")
    public Result bill(HttpServletRequest request){
        String token = request.getHeader("token");
        String username = JwtUtil.getUsername(token);
        List<BookBorrowed> bookBorroweds = bookService.selectBookBorrowed(username);
        return new Result(Code.OK,bookBorroweds,"查询成功");
    }

    @GetMapping("/log")
    public Result getLog(){
        List<String> strings = bookService.selectLog();
        return new Result(Code.OK,strings,"查询成功");
    }




}
