package servlets;

import com.google.gson.Gson;
import controllers.AccountController;
import entities.Account;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/rest/accounts")
public class AccountRESTServlet extends HttpServlet {

    @Inject
    private AccountController accountController;

    @Inject
    private Gson gson;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String output;

        if (request.getParameter("username") != null) {
            Account account = accountController.find(request.getParameter("username"));
            output = gson.toJson(account);
        } else {
            List<Account> accountList = accountController.all();
            output = gson.toJson(accountList);
        }

        PrintWriter writer = response.getWriter();
        writer.write(output);
        writer.close();
    }
}
