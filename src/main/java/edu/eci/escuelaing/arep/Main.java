package edu.eci.escuelaing.arep;

import org.json.JSONObject;
import edu.eci.escuelaing.arep.recursos.HttpRequest;
import spark.Request;
import spark.Response;

import static spark.Spark.*;
public class Main {
    public static void main(String[] args) {
        port(getPort());
        get("/",(req,res)->inputDataPage(req,res));
        get("/calc",(req,res)->{
            String operation = req.queryParams("op");
            String number = req.queryParams("num");
            JSONObject jsonObject = new JSONObject(HttpRequest.get(operation,number));
            return jsonObject;
        });

        get("/res",(req,res)->{
            String operation = req.queryParams("op");
            String number = req.queryParams("num");
            JSONObject jsonObject = new JSONObject(HttpRequest.get(operation,number));
            return outputDataPage(jsonObject);

        });

    }

    /**
     * This function returns the HTML structure to present the output data
     * @param json A JSON with the structure operation and number
     * @return A String that have a template of a HTML
     */
    private static String outputDataPage(JSONObject json){
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h1>Calculadora Trigonométrica</h1>"
                + "<h4>Función Trigonométrica</h>"
                + json.get("operación")
                + "<h4>Valor</h4>"
                + json.get("valor")
                + "<br/>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    /**
     * This function returns the HTML structure to present the input data
     * @param req The Spark HTTP Request
     * @param res The Spark HTTP Response
     * @return A String that have a template of a HTML
     */
    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h1>Calculadora Trigonométrica</h1>"
                + "<form action=\"/results\">"
                + "  Number:<br>"
                + "  <input type=\"text\" name=\"num\" value=\"\">"
                + "  <br><br>"
                + "<label for=\"method\">Escoja una operación:</label>"
                + "<select name=\"op\" id=\"op\">"
                + "<option value=\"sin\">Seno</option>"
                + "<option value=\"cos\">Coseno</option>"
                + "<option value=\"tan\">Tangente</option>"
                + "</select>"
                + "<br/>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }


    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}