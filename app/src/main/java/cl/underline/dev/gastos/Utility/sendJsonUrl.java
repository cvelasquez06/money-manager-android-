package cl.underline.dev.gastos.Utility;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cvelasquez@underline.cl developer on 06-02-2018.
 */
public class sendJsonUrl extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... urlPath) {
        try{
            return downloadUrlData(urlPath[0], urlPath[1], urlPath[2]);
        }catch (IOException e){
            return "Error de conexión a la url: "+urlPath[0];
        }
    }
    private String downloadUrlData(String urlPath, String method, String stringData)throws IOException {
        urlPath = urlPath.replace(" ","%20");
        HttpURLConnection conn = null;
        URL url = new URL(urlPath);
        String dataContent=null;
        try{
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(method);
            conn.setRequestProperty( "Content-Type", "application/json" );
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.connect();
            OutputStream out = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out , "UTF-8");
            osw.write(stringData.toString());
            osw.flush();
            osw.close();
            dataContent = String.valueOf(conn.getResponseCode());
            conn.disconnect();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {

        }
        return dataContent;
    }
}