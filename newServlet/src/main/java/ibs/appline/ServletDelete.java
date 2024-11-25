package ibs.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ibs.appline.logic.Model;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder jsonInput = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            jsonInput.append(line);
        }

        JsonObject jsonObject = new JsonParser().parse(jsonInput.toString()).getAsJsonObject();
        int id = jsonObject.get("id").getAsInt();

        Model model = Model.getInstance();
        model.delete(id);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println("{\"message\": \"Пользователь с ID " + id + " удален успешно\"}");
    }
}
