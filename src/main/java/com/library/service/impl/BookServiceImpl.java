package com.library.service.impl;

import com.library.dao.BookDao;
import com.library.domain.*;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public List<Book> selectAll() {
        return bookDao.selectAll();
    }

    public Book selectById(int id) {
        return bookDao.selectById(id);
    }

    public int deleteById(int id) {
        return bookDao.deleteById(id);
    }

    public int preserve(Book book) {
        return bookDao.preserve(book);
    }

    public int update(Book book) {
        return bookDao.updata(book);
    }

    @Override
    public PageBean<Book> selectByPage(int currentPage, int pageSize) {
        int begin =(currentPage - 1) * pageSize;
        int size = pageSize;
        List<Book> rows = bookDao.selectByPage(begin, size);
        int totalCount = bookDao.selectTotalCount();
        PageBean<Book> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        return pageBean;
    }

    @Override
    public List<Book> selectByCondition(Book book) {
        String bookname = book.getBookname();
        if(bookname != null && bookname !="")bookname = "%" + bookname + "%";
        book.setBookname(bookname);

        String description = book.getDescription();
        if(description != null && description !="")description = "%" + description + "%";
        book.setDescription(description);


        return bookDao.selectByCondition(book);
    }

    @Override
    public List<BookStatus> selectStatus() {
        return bookDao.selectStatus();
    }

    @Override
    public int selectStatusById(int id) {
        return bookDao.selectStatusById(id);
    }

    @Override
    public int borrowBook(BookBorrow borrowBook) {
        return bookDao.borrowBook(borrowBook);
    }

    @Override
    public int returnBook(int id) {
        return bookDao.returnBook(id);
    }

    @Override
    public String selectBorrowerById(int id) {
        return bookDao.selectBorrowerById(id);
    }

    @Override
    public BookAll selectAllById(int id) {
        return bookDao.selectAllById(id);
    }

    @Override
    public List<BookBorrowed> selectBookBorrowed(String borrower) {
        return bookDao.selectBookBorrowed(borrower);
    }

    @Override
    public List<String> selectLog() {
        return bookDao.selectLog();
    }

    @Override
    public int log(Log log) {
        return bookDao.log(log);
    }
}
