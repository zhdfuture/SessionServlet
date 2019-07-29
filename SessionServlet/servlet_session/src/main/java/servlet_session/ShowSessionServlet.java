package servlet_session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/showSessionServlet")
public class ShowSessionServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        //测试Session
        HttpSession httpSession=req.getSession(true);
        String message=null;
        Integer accessCount=(Integer)httpSession.getAttribute("accessCount");
        if(accessCount==null) {
            accessCount = new Integer(1);
            message = "newer";
            httpSession.setAttribute("accessCount", accessCount);
        }
        else{
            accessCount=new Integer(accessCount.intValue()+1);
            message="older";
            httpSession.setAttribute("accessCount",accessCount);
        }
        out.append("<html>")
                .append("<head><title>CookieServletDemo</title></head>")
                .append("<body>")
                .append("<h1>'"+message+"'</h1></br>")
                .append("<table border='1' align='center'>")

                .append("<tr>")
                .append("<td>SessionID</td>")
                .append("<td>'"+httpSession.getId()+"'</td>")
                .append("</tr>")

                .append("<tr>")
                .append("<td>Session Create Time</td>")
                .append("<td>'"+httpSession.getCreationTime()+"'</td>")
                .append("</tr>")

                .append("<tr>")
                .append("<td>Session Access Lat Time</td>")
                .append("<td>'"+httpSession.getLastAccessedTime()+"'</td>")
                .append("</tr>")

                .append("<tr>")
                .append("<td>AccessCount</td>")
                .append("<td>'"+accessCount+"'</td>")
                .append("</tr>")

                .append("</table>")
                .append("</body>")
                .append("</html>");

    }

}
