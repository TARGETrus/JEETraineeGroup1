package servlets;

import DAOHandler.UserDataManager;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import model.Event;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    UserDataManager dataManager = new UserDataManager();
    User user;
    List<Event> events;
    String lat = "";
    String lon = "";


    public static String encodeParams(final Map<String, String> params) {
        final String paramsUrl = Joiner.on('&').join(// получаем значение вида key1=value1&key2=value2...
                Iterables.transform(params.entrySet(), input -> {
                    try {
                        final StringBuffer buffer = new StringBuffer();
                        buffer.append(input.getKey());// получаем значение вида key=value
                        buffer.append('=');
                        buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));// кодируем строку в соответствии со стандартом HTML 4.01
                        return buffer.toString();
                    } catch (final UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }));
        return paramsUrl;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        user = dataManager.findUserById(Integer.parseInt(req.getParameter("user_id")));
        events = new ArrayList<>(user.getEvents());
        for(int i = 0; i < events.size(); i++) {
            lat += events.get(i).getLatitude() + " ";
            lon += events.get(i).getLongitude() + " ";
        }

        resp.addCookie(new Cookie("latitude", lat));
        resp.addCookie(new Cookie("longitude", lon));

        resp.sendRedirect("map.jsp");
    }

}
