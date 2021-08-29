package com.xjy.javaweb.proj.web;

import com.google.gson.Gson;
import com.xjy.javaweb.proj.pojo.Book;
import com.xjy.javaweb.proj.pojo.Cart;
import com.xjy.javaweb.proj.pojo.CartItem;
import com.xjy.javaweb.proj.service.BookService;
import com.xjy.javaweb.proj.service.impl.BookServiceImpl;
import com.xjy.javaweb.proj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.WebConnection;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class CartServlet extends BaseServlet{
    /*
     * @param req
     * @param resp
     * @return
     * @Description: add item to cart
     **/
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookService bookService = new BookServiceImpl();

        // 1. get parameters
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        // 2. using BookService.queryBookById() get the data of book
        Book book = bookService.queryBookById(id);

        // 3. create CartItem Object using book;
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 4. add CartItem to the cart
        // 4.1 check whether cart is exists or not
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        // 4.2 add cartItem to cart
        cart.addItem(cartItem);
        // 4.3 add last cartitem's name to the session scope
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 5. redirect to the page just browsing
        resp.sendRedirect(req.getHeader("Referer"));
    }


    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();

        // 1. get parameters
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2. using BookService.queryBookById() get the data of book
        Book book = bookService.queryBookById(id);

        // 3. create CartItem Object using book;
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 4. add CartItem to the cart
        // 4.1 check whether cart is exists or not
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        // 4.2 add cartItem to cart
        // msg is used to tell user that stock is not enough, if it is not "", means not enough
        String msg = "";
        if (book.getStock() == 0 ||
                (cart.getItems().containsKey(book.getId()) && cart.getItems().get(book.getId()).getCount() >= book.getStock())) {
            // stock is not enough
            msg = "Stock is not enough.";
        } else {
            cart.addItem(cartItem);
            msg = "";
        }

        // 4.3 add last cartItem's name to the session scope
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 5. return stream to
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());
        resultMap.put("msg", msg);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

    /*
     * @param req
     * @param resp
     * @return
     * @Description: delete an item from cart
     **/
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameter
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        // 2. get cart object
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        // 3. delete the CartItem from the cart
        if (cart != null) {
            cart.deleteItem(id);

            // 4. go to the page just viewing
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /*
     * @param req
     * @param resp
     * @return
     * @Description: clear the cart
     **/
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get the cart object
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        // 2. clear cart
        if (cart != null) {
            cart.clear();
            // go back to the previous page
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /*
     * @param req
     * @param resp
     * @return
     * @Description: update the cartitem's count
     **/
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get request parameters
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);

        // 2. get cart object
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        // 3. update count
        if (cart != null) {
            cart.updateCount(id, count);

            // go back to the previous page
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
