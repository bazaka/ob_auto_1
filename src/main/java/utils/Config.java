package utils;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by User on 2/10/2015.
 */
public class Config {
    private String link;
    private String emailDomain;

    public void setLink(String a) {
        this.link = a;
    }
    public void setEmailDomain(String a){
        this.emailDomain = a;
    }

    public String getLink() {
        return link;
    }
    public String getEmailDomain(){
        return emailDomain;
    }

    public Config(String link, String emailDomain) {
        this.setLink(link);
        this.setEmailDomain(emailDomain);
    }

    public static Config getConfig() {
        //
        try {
            FileReader reader = new FileReader("src/Config.json");
            BufferedReader br = new BufferedReader(reader);
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            JSONObject object = new JSONObject(result);

            return new Config(object.getString("link"), object.getString("emailDomain"));


        } catch (Exception e) {
            System.out.println("File not found. Exception: " + e);
            return null;
        }

    }
}
