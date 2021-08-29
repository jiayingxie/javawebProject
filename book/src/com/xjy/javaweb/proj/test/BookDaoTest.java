package com.xjy.javaweb.proj.test;

import com.xjy.javaweb.proj.dao.BookDao;
import com.xjy.javaweb.proj.dao.impl.BookDaoImpl;
import com.xjy.javaweb.proj.pojo.Book;
import com.xjy.javaweb.proj.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "xjy learning Java", "xjy", new BigDecimal(999.99), 2, 100, ""));
        bookDao.addBook(new Book(null, "小蝌蚪学JAVA", "科科z", new BigDecimal(999.99), 2, 100, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(26);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(32, "xjy learning Java", "科科仔", new BigDecimal(999.99), 2, 100, ""));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(25));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println( bookDao.queryForPageTotalCount() );
    }

    @Test
    public void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(8, Page.PAGE_SIZE)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println( bookDao.queryForPageTotalCountByPrice(10,32.5));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(1, Page.PAGE_SIZE, 10,32.5)) {
            System.out.println(book);
        }
    }
}