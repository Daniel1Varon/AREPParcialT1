
package edu.eci.escuelaing.arep;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Cliente {
    public static void main(String[] args) {
        HttpResponse<String> respuesta = null;
        try {
            respuesta = Unirest.get("https://arepparcialt1.herokuapp.com/calc?num=3.1415&op=sin").asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        System.out.println(respuesta.getBody());

        try {
            respuesta = Unirest.get("https://calculadoratrig.herokuapp.com/calc?op=sin&num=3.1415").asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        System.out.println(respuesta.getBody());
    }
}