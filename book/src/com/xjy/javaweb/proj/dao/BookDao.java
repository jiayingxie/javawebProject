package com.xjy.javaweb.proj.dao;

import com.xjy.javaweb.proj.pojo.Book;

import java.util.List;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public interface BookDao {
    /*
     * @param book
     * @return return -1 means fail; otherwise, return the number of influenced rows
     * @Description: add a book to the database
     **/
    /* 注意！！！ 用idea向数据库写中文数据可能会乱码。处理：1. 在jdbc.properties里面，
        url后面加上?useUnicode=true&characterEncoding=utf8；2.idea setting-file encoding，
        把字符集改成utf-8
    * */
    public int addBook(Book book);

    /*
     * @param id
     * @return return -1 means fails; otherwise, return the number of influenced rows
     * @Description: delete a book in the database by book id
     **/
    public int deleteBookById(Integer id);

    /*
     * @param book
     * @return return -1 means fails; otherwise, return the number of influenced rows
     * @Description: update the information of a book
     **/
    public int updateBook(Book book);

    /*
     * @param id
     * @return Book
     * @Description: return null if no such book; otherwise, return the book.
     **/
    public Book queryBookById(Integer id);

    /*
     * @param
     * @return
     * @Description: return null if no result; otherwise, return all books.
     **/
    public List<Book> queryBooks();


    /*
     * @param 
     * @return
     * @Description: get total number of items
     **/
    public Integer queryForPageTotalCount();

    /*
     * @param begin
     * @param pageSize
     * @return
     * @Description: get current page's items' data
     **/
    public List<Book> queryForPageItems(int begin, int pageSize);

    /*
     * @param min
     * @param max
     * @return get total number of items between the min price and max price
     * @Description:
     **/
    Integer queryForPageTotalCountByPrice(double min, double max);

    /*
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return get current page's items' data between the min price and max price
     * @Description:
     **/
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, double min, double max);
}
