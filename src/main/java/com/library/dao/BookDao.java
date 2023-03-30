package com.library.dao;

import com.library.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface BookDao {

    @Select("select * from tbl_book")
    public List<Book> selectAll();

    @Select("select * from tbl_book where id = #{id}")
    public BookAll selectAllById(@Param("id") int id);

    @Select("select * from tbl_book where id = #{id}")
    public Book selectById(@Param("id") int id);

    @Delete("delete from tbl_book where id = #{id}")
    public int deleteById(@Param("id") int id);

    @Insert("insert into tbl_book (bookname,description,isbn) values (#{bookname},#{description},#{isbn})")
    public int preserve(Book book);

    @Update("update tbl_book set isbn = #{isbn}, bookname = #{bookname},description = #{description} where id = #{id}")
    public int updata(Book book);

    @Select("select * from tbl_book limit #{begin} ,#{size}")
    public List<Book> selectByPage(@Param("begin")int begin,@Param("size")int size);

    @Select("select count(*) from tbl_book")
    public int selectTotalCount();

    public List<Book> selectByCondition(Book book);

    @Select("select id,bookname,isbn,status from tbl_book")
    public List<BookStatus> selectStatus();

    @Select("select status from tbl_book where id = #{id}")
    public int selectStatusById(@Param("id") int id);

    @Update("update tbl_book set status = 1,borrower = #{borrower},borrow_time = #{borrowTime}, return_time = #{returnTime} where id = #{id}")
    public int borrowBook(BookBorrow BookBorrow);

    @Update("update tbl_book set status = 0,borrower = null,borrow_time = null,return_time = null where id = #{id}")
    public int returnBook(int id);

    @Select("select borrower from tbl_book where id = #{id}")
    public String selectBorrowerById(@Param("id") int id);

    @Insert("insert into tbl_log (borrower, borrow_book, return_book, log, date) VALUES (#{borrower},#{borrowBook},#{returnBook},#{log},#{date})")
    public int log(Log log);

    @Select("select id,bookname ,borrow_time as borrowTime,return_time as returnTime from tbl_book where borrower = #{borrower} ")
    public List<BookBorrowed> selectBookBorrowed(String borrower);

    @Select("select log from tbl_log")
    public List<String> selectLog();




}
