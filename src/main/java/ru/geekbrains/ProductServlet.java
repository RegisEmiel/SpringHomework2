package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "ProductServlet", urlPatterns = "/show_product")
public class ProductServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Log: ProductServlet - doGet");

        List<Product> productList = new CopyOnWriteArrayList(new ArrayList<Product>());
        for (int i = 1; i < 11; i++) {
            Product prod = new Product(i, "Pr_" + i, i * 10);
            productList.add(prod);
        }

        StringBuilder sbProductHTMLBody = new StringBuilder();
        sbProductHTMLBody.append("<html><body><h1>Product servlet</h1>");
        sbProductHTMLBody.append("<ul>");
        for (Product product : productList) {
            sbProductHTMLBody.append("<li>");
            sbProductHTMLBody.append(product);
            sbProductHTMLBody.append("</li>");
        }
        sbProductHTMLBody.append("</ul>");
        sbProductHTMLBody.append("</body></html>");

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.printf(sbProductHTMLBody.toString());
        out.close();
    }
}
