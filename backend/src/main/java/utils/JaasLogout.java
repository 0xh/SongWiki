package utils;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "jaasLogout")
@RequestScoped
public class JaasLogout {
    private static Logger log = Logger.getLogger(JaasLogout.class.getName());

    public String logOut() {
        String destination = "/index.xhtml?faces-redirect=true";

        // FacesContext provides access to other container managed objects,
        // such as the HttpServletRequest object, which is needed to perform
        // the logout operation.
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            HttpSession session = request.getSession();
            session.invalidate();

            // This does not invalidate the session but does null out the user Principle
            request.logout();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            destination = "/loginError?faces-redirect=true";
        }

        return destination;
    }
}
