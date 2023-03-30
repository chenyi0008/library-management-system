package com.library.service;

import com.library.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface BookService {

    /**
     * 搜索全部图书信息
     * @return
     */
    public List<Book> selectAll();

    /**
     * 根据id搜索图书信息
     * @param id
     * @return
     */
    public Book selectById(int id);

    /**
     * 根据id删除图书数据
     * @param id
     * @return
     */
    public int deleteById(int id);

    /**
     * 创建图书数据
     * @param book
     * @return
     */
    public int preserve(Book book);

    /**
     * 更改图书数据
     * @param book
     * @return
     */
    public int update(Book book);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Book> selectByPage(int currentPage, int pageSize);

    /**
     * 根据关键字查询书本数据
     * @param book
     * @return
     */
    public List<Book> selectByCondition(Book book);

    /**
     * 得到bookname status isbn
     * @return
     */
    public List<BookStatus> selectStatus();

    /**
     * 根据id得到书本的status
     * @return
     */
    public int selectStatusById(int id);

    /**
     * 借书
     * @param borrowBook
     * @return
     */
    public int borrowBook(BookBorrow borrowBook);

    /**
     * 还书
     * @param id
     * @return
     */
    public int returnBook(int id);

    /**
     * 根据id获取Borrower
     * @param id
     * @return
     */
    public String selectBorrowerById(int id);

    /**
     * 记录日志
     * @param log
     * @return
     */
    public int log(Log log);

    /**
     * 根据id获取book的所有属性
     * @param id
     * @return
     */
    public BookAll selectAllById(int id);

    /**
     * 根据borrower获取借书单
     * @param borrower
     */
    public List<BookBorrowed> selectBookBorrowed(String borrower);

    /**
     * 输出日志
     * @return
     */
    public List<String> selectLog();
}
