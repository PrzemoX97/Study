package com.EmailClient;

/**
 *
 * @author PrzemoX
 */
import com.EmailClient.model.FileMetaData;
import com.EmailClient.model.UserData;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.swing.BorderFactory;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JPanel;
        import javax.swing.JTextField;
        import javax.swing.SwingUtilities;
        import javax.swing.UIManager;

public class GUI extends  JFrame{

    private JLabel labelRecipient = new JLabel("Recipient: ");
    private JLabel labelTitle = new JLabel("Title: ");
    private JLabel labelContent = new JLabel("Content: ");
    private JTextField textRecipient = new JTextField(20);
    private JTextField fieldTitle = new JTextField(20);
    private JTextField fieldContent = new JTextField(40);
    private JButton buttonLogin ;

    public  GUI() {
        super("EmailClient");


        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);


        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelRecipient, constraints);

        constraints.gridx = 1;
        newPanel.add(textRecipient, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelTitle, constraints);

        constraints.gridx = 1;
        newPanel.add(fieldTitle, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(labelContent, constraints);

        constraints.gridx = 1;
        newPanel.add(fieldContent, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

    //przycisk wysylania inicjalizacja
        buttonLogin = new JButton("Send");
        buttonLogin.addActionListener( new ButtonSendListener());
        newPanel.add(buttonLogin,constraints);

        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Email Sending"));


        add(newPanel);

        pack();
        setLocationRelativeTo(null);

    }

    //akcja przyciska wysylajacego
    class ButtonSendListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
        exampleHttpClientForPostMethod();
        }
    }

    //funkcjonalnosc wysylania
    static void exampleHttpClientForPostMethod() {

        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/email/send");

        Gson gson = new Gson();

        // Tworzymy obiekt uzytkownika
        final UserData userData = new UserData("nisp.java@interia.pl", "How to send Emails?!", "A lot of bad project, but now it works :)");

        // Serializacja obiektu do JSONa
        final String json = gson.toJson(userData);

        try {

            final StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            final CloseableHttpResponse response = client.execute(httpPost);

            System.out.println("Kod odpowiedzi serwera: " + response.getStatusLine().getStatusCode());

            if(response.getStatusLine().getStatusCode() == 404) {

                System.out.println("Prosze poprawic w kontrolerze sciezke do pliku - sciezka jest nieprawidlowa!");
            } else if(response.getStatusLine().getStatusCode() == 201) {

                final HttpEntity httpEntity = response.getEntity();

                // Na tym etapie odczytujemy JSON'a, ale jako String.
                final String jsonAsFileMetaData = EntityUtils.toString(httpEntity);

                // Wyswietlamy zawartosc JSON'a na standardowe wyjscie.
                System.out.println("Odczytujemy JSON'a z serwera:");
                System.out.println(jsonAsFileMetaData);

                final FileMetaData fileMetaData = gson.fromJson(jsonAsFileMetaData, FileMetaData.class);

                // Dzialamy na obiekcie - mamy dostep do danych, ktore zostaly odczytane z JSON'a
                System.out.println("Dane zapisanego pliku:");
                System.out.printf("Data utworzenia pliku: %s \n", fileMetaData.getCreationDate());
                System.out.printf("Nazwa pliku: %s \n", fileMetaData.getFileName());
                System.out.printf("Rozmiar pliku: %d B \n", fileMetaData.getSize());
            }

            client.close();
        } catch (UnsupportedEncodingException e) {

            System.out.println("Houston, we have a problem with POST unsupported encoding");
            e.printStackTrace();
        } catch (ClientProtocolException e) {

            System.out.println("Houston, we have a problem with POST client protocol");
            e.printStackTrace();
        } catch (IOException e) {

            System.out.println("Houston, we have a problem with POST input output");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }



}