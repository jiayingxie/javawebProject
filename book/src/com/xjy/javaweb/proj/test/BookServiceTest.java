package com.xjy.javaweb.proj.test;

import com.xjy.javaweb.proj.pojo.Book;
import com.xjy.javaweb.proj.pojo.Page;
import com.xjy.javaweb.proj.service.BookService;
import com.xjy.javaweb.proj.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "小蝌蚪学JAVA", "科科z", new BigDecimal(999.99), 2, 100, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(25);
    }

    @Test
    public void updateBook() {
        bookService.addBook(new Book(null, "小蝌蚪学JAVA", "科科仔仔", new BigDecimal(999.99), 233, 100, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(27));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE ));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 32.5));
    }
}