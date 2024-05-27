package com.example.cookies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean nuevoUsuario = true;
        Cookie[] cookies = request.getCookies();
        int contador=0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("UsuarioConcurrente")&&cookie.getValue().equals("si")) {
                    nuevoUsuario = false;
                }
                if (cookie.getName().equals("Visitadorconteo")) {
                    contador = Integer.parseInt(cookie.getValue());
                }
            }
        }
        String mensaje = null;
        if (nuevoUsuario) {
            Cookie cookie = new Cookie("UsuarioConcurrente", "si");
            response.addCookie(cookie);
            mensaje = "Bienvenido nuevo Usuario a este servidor";
        }else {
            mensaje="Gracias por visitar mi sitio nuevamente";
        }
        contador ++;
        Cookie cookie = new Cookie("Visitadorconteo", String.valueOf(contador));
        response.addCookie(cookie);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Mensaje:"+mensaje+"</h1>");
        out.println("<h1>Las veces que el usuario se detecto en mi navegador"+contador+"</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
