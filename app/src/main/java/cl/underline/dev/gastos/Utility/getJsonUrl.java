package cl.underline.dev.gastos.Utility;
import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cvelasquez@underline.cl developer on 06-02-2018.
 */
public class getJsonUrl extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... urlPath) {
        try{
            return downloadUrlData(urlPath[0], urlPath[1]);
        }catch (IOException e){
            return "Error de conexión a la url";
        }
    }
    private String downloadUrlData(String urlPath, String method)throws IOException {
        urlPath = urlPath.replace(" ","%20");
        HttpURLConnection conn = null;
        URL url = new URL(urlPath);
        String dataContent=null;
        try{
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setUseCaches(true);
            conn.connect();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                sb.append(line);
            }
            dataContent = sb.toString();
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