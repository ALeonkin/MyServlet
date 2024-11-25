package com.example.calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calculator")
public class CalculatorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String requestBody = request.getReader().lines().reduce((s1, s2) -> s1 + s2).orElse("");
            JSONObject jsonObject = new JSONObject(requestBody);

            double a = jsonObject.getDouble("a");
            double b = jsonObject.getDouble("b");
            String mathOperation = jsonObject.getString("math");

            double result = 0;
            boolean error = false;
            String errorMessage = "";

            switch (mathOperation) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        error = true;
                        errorMessage = "Делить на 0 нельзя.";
                    } else {
                        result = a / b;
                    }
                    break;
                default:
                    error = true;
                    errorMessage = "Неправильная операция.";
            }

            JSONObject responseJson = new JSONObject();
            if (error) {
                responseJson.put("error", errorMessage);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
            } else {
                responseJson.put("result", result);
            }
            out.print(responseJson.toString());

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception properly in a real application
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(new JSONObject().put("error", "error"));
        }
    }
}
