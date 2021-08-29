package com.xjy.javaweb.proj.service;

import com.xjy.javaweb.proj.pojo.Book;
import com.xjy.javaweb.proj.pojo.Page;

import java.util.List;

/**
 * @Author Jiaying Xie
 * @Description: actions about handling books
 */
public interface BookService {
    /*
     * @param book
     * @return
     * @Description: add a new book
     **/
    public void addBook(Book book);

    /*
     * @param id
     * @return
     * @Description: delete a book by id
     **/
    public void deleteBookById(Integer id);

    /*
     * @param book
     * @return
     * @Description: update the information of a book
     **/
    public void updateBook(Book book);

    /*
     * @param id
     * @return null if no such book, otherwise, return the book.
     * @Description: search a book by id
     **/
    public Book queryBookById(Integer id);

    /*
     * @param
     * @return return null if no book; otherwise, return all books.
     * @Description: search all books
     **/
    public List<Book> queryBooks();

    /*
     * @param pageNo
     * @param pageSize
     * @return
     * @Description: to paging the showing list
     **/
    Page<Book> page(int pageNo, int pageSize);

    /*
     * @param pageNo
     * @param pageSize
     * @return
     * @Description: paging the books by price
     **/
    Page<Book> pageByPrice(int pageNo, int pageSize, double min, double max);
}
