package com.training.myshop;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.ClientResponse;
import java.io.IOException;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import java.util.StringTokenizer;  

//import org.json.JSONObject;
public class SearchServlet extends HttpServlet {
    public void init() {
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        try {
            java.io.PrintWriter pw = response.getWriter();
            String searchitem = request.getParameter("stname");
            int length = searchitem.length();
/************check weather the user given the serching item or item*************/
/************if given**************/
            if(length!=0) {
                ClientConfig config = new DefaultClientConfig();
                Client client = Client.create(config);
                WebResource service = client.resource("http://172.20.105.121:8080/myShop1.0/rest/category/search/"+searchitem);
                JSONObject obj = new JSONObject();
                ClientResponse cresponse = service.type("application/json").post(ClientResponse.class, searchitem);
                String output = cresponse.getEntity(String.class);
                ClientResponse response2 = service.accept("application/json").post(ClientResponse.class);
                output = response2.getEntity(String.class);
                
                //pw.println(output);
                StringTokenizer st = new StringTokenizer(output,"}");  
                String str2 ="";
                String str3 = "";
                //String st2 = st.toString();
               // pw.println(response2.getEntityInputStream());
               // StringTokenizer st1 = new StringTokenizer(st,"{");
                pw.println("<html><body>");
                while(st.hasMoreTokens()) {
                    //pw.println(st.nextToken());
                    str2 = str2+st.nextToken();
                    //pw.println(" <br>");
                }
                //pw.println(st2);
                StringTokenizer st1 = new StringTokenizer(str2,"{");
                while(st1.hasMoreTokens()) {
                    //pw.println(st.nextToken());
                    str3 = str3+st1.nextToken();
                    //pw.println(" <br>");
                }
                pw.println(str3);
                pw.println("</body></html>");
                /*pw.println("<html>");
                pw.println("<body align=\"center\">");
                pw.println("<form action = \"http://172.20.105.121:8080/myShop1.0/index.html\" method = \"post\">");
                pw.println("<input type = \"text\" value = "+output+" />");         
                pw.println("<input type = \"submit\" value = \"BACK\" >");
//              pw.println("<input type = \"button\" value = \"AddTOCART\" >");
                pw.println("</form>");
                pw.println("</body>");
                pw.println("</html>");*/
            } /****if not given***/else {
                response.sendRedirect("http://172.20.105.121:8080/myShop1.0/index.html");
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
            doPost(request,response);
            throw new ServletException("GET method used with " +
                    getClass( ).getName( )+": POST method required.");
        }
}
