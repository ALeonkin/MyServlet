package ibs.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ibs.appline.logic.Model;
import ibs.appline.logic.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null){
                jb.append(line);
            }
        }catch (Exception e) {
            System.out.println("ERROR");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        double salary = jobj.get("salary").getAsDouble();

        User user = new User(name,surname,salary);

        model.add(user, counter.getAndIncrement());

        response.setContentType("application/json;charset=utf-8");

        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(model.getFromList()));
    }
}


//@WebServlet(urlPatterns = "/add")
//public class ServletAdd extends HttpServlet {
//
//    private AtomicInteger counter = new AtomicInteger(5);
//    Model model = Model.getInstance();
//    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        StringBuffer jb = new StringBuffer();
//        String line;
//        try {
//            BufferedReader reader = request.getReader();
//            while ((line = reader.readLine()) != null){
//                jb.append(line);
//            }
//        }catch (Exception e) {
//            System.out.println("ERROR");
//        }
//
//        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
//
//        String name = jobj.get("name").getAsString();
//        String surname = jobj.get("surname").getAsString();
//        double salary = jobj.get("salary").getAsDouble();
//
//        User user = new User(name,surname,salary);
//
//        model.add(user, counter.getAndIncrement());
//
//        response.setContentType("application/json;charset=utf-8");
//
//        PrintWriter pw = response.getWriter();
//
//        pw.print(gson.toJson(model.getFromList()));
//    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw = response.getWriter();
//
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//
//        User user = new User(name,surname,salary);
//        model.add(user, counter.getAndIncrement());
//
//        pw.print("<html>" +
//                "<h3>Пользователь " + name + " " + surname + " " + "с зарплатой: " + salary + " успешно создан! :)</h3>" +
//                "<a href=\"addUser.html\">Создать нового пользователя</a><br/>" +
//                "<a href=\"index.jsp\">Домой</a>" +
//                "</html>");
//    }

