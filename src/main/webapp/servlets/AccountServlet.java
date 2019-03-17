package servlets;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import controllers.AccountController;
import entities.Account;
import entities.Role;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    @Inject
    private AccountController accountController;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String emailAddress = request.getParameter("email_address");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        String[] isAdminCheckbox = request.getParameterValues("is_admin");

        String hashedPassword = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();

        Account account = new Account();
        account.setUsername(username);
        account.setEmail(emailAddress);
        account.setPassword(hashedPassword);
        account.setAge(age);

        if (isAdminCheckbox != null && isAdminCheckbox[0].equals("on")) {
            account.setRole(Role.admin);
        }

        accountController.save(account);

        request.setAttribute("account", account);

        request.getRequestDispatcher("/accountCreated.jsp").forward(request, response);
    }
}
