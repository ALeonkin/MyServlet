package ibs.appline;

import com.google.gson.Gson;
import ibs.appline.logic.Model;
import ibs.appline.logic.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    Model model = Model.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        Gson gson = new Gson();

        if(id==0){
            List<User> userList = new ArrayList<>(model.getFromList().values());
            String json = gson.toJson(userList);
            pw.print(json);
        }else if(id>0){
            User user = model.getFromList().get(id);
            if(user != null) {
                String json = gson.toJson(user);
                pw.print(json);
            }else {
                pw.print(gson.toJson("Пользователь с ID: " + id + "не найден"));
            }
        }else {
            pw.print(gson.toJson("ID не должен быть меньше нуля"));
        }
       }
    }
