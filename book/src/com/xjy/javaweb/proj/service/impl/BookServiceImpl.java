package com.xjy.javaweb.proj.service.impl;

import com.xjy.javaweb.proj.dao.BookDao;
import com.xjy.javaweb.proj.dao.impl.BookDaoImpl;
import com.xjy.javaweb.proj.pojo.Book;
import com.xjy.javaweb.proj.pojo.Page;
import com.xjy.javaweb.proj.service.BookService;

import java.util.List;

/**
 * @Author Jiaying Xie
 * @Description: actions about handling books
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        // 1. get the pageTotalCount and pageTotal data
        // total number of items
        Integer pageTotalCount = bookDao.queryForPageTotalCount();

        // total page
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }

        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);

        // 2. set current page's items' data
        // begin is the first index of current page's item
        int begin = (page.getPageNo() - 1) * pageSize;
        // items is current page's items
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, double min, double max) {
        Page<Book> page = new Page<>();

        // total number of items
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);

        // total page
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }

        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);

        // set current page's items' data
        // begin is the first index of current page's item
        int begin = (page.getPageNo() - 1) * pageSize;
        // items is current page's items
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        page.setItems(items);

        return page;
    }
}
