package com.library.domain;

public class BookStatus {
    private Integer id;
    private String bookname;
    private String isbn;
    private Integer status;

    @Override
    public String toString() {
        return "BookStatus{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", isbn='" + isbn + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
