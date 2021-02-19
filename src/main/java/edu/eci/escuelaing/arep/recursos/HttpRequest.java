package edu.eci.escuelaing.arep.recursos;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpRequest {

    public static String get(String op, String num){

        HttpResponse<String> respuesta = null;
        try {
            respuesta = Unirest.get("https://calculadoratrig.herokuapp.com/calc?op="+op+"&num="+num)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return respuesta.getBody();



    }




}