package com.EmailClient;

import com.google.gson.Gson;
import com.EmailClient.model.FileMetaData;
import com.EmailClient.model.UserData;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class Main extends GUI {





    public static void main(String[] args) {


        System.out.println("Test metody POST: \n");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
        System.out.println();


    }
}
