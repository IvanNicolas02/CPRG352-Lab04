
package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        // to read files
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
        Note note = new Note();
        String title = br.readLine();
        String message = br.readLine();
        String allLine ;
         
        while((allLine = br.readLine()) != null){
            message = message + "<br>" + allLine;
        }

        String edit = request.getParameter("edit");
        System.out.println(title);
        System.out.println(message);
        if(edit == null){
            note.setTitle(title);
            note.setMessage(message);
            request.setAttribute("note", note);
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
            br.close();
            return;
        }else if(edit.isEmpty()){
            note.setTitle(title);
            // \r\n Source: https://stackoverflow.com/questions/863779/how-to-add-line-breaks-to-an-html-textarea#:~:text=For%20a%20universal%20line%20break,you%20want%20%5Cr%5Cn%20.
            // Also adding a break on the text area not only in the viewnote.jsp
            if(message != null && !message.isEmpty()){
                note.setMessage(message.replace("<br>", "\r\n"));
            }
            else{
                note.setMessage(message);
            }
            request.setAttribute("note", note);
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
            br.close();
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        // to write to a file
        PrintWriter pw = new PrintWriter(new BufferedWriter(new 
        FileWriter(path, false)));
        
        String actions = request.getParameter("action");
        String title = request.getParameter("title");
        String message = request.getParameter("message");
        if(actions.equals("Save")){
            pw.println(title);
            pw.println(message);
        }
        else if(actions.equals("Delete")){
            pw.println("");
            pw.println("");
        }
       
        pw.close();
        doGet(request, response);
    }
}
