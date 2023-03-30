package com.library.domain;

public class Log {
    private int id;
    private String borrower;
    private String log;
    private String date;
    private int borrowBook=0;
    private int returnBook=0;

    public String createLog(String bookname) {
        if(borrowBook == 1){
            return date + " " + borrower + "借取" + bookname;
        }
        return date + "  " + borrower + " 返还 " + bookname;
    }

    public String getLog() {
        return log;
    }

    public int getReturnBook() {
        return returnBook;
    }

    public int getBorrowBook() {
        return borrowBook;
    }

    public void setBorrowBook(int borrowBook) {
        this.borrowBook = borrowBook;
    }

    public void setReturnBook(int returnBook) {
        this.returnBook = returnBook;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }



    public void setLog(String log) {
        this.log = log;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
