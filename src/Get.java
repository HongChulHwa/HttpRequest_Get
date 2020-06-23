import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Get {

    public static void main(String[] args) {

        // HttpClient 생성
        HttpClient httpclient = new DefaultHttpClient();
        try {
            // HttpGet생성
            HttpGet httpget = new HttpGet("https://gitlab.twoseed.co.kr/api/v4/projects");
            httpget.addHeader("PRIVATE-TOKEN","AM1u7sNsG_KnfEEzYE6r");

            System.out.println("executing request " + httpget.getURI());

            HttpResponse response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();

            System.out.println("----------------------------------------");
            // 응답 결과
            System.out.println(response.getStatusLine());
            if (entity != null) {
                System.out.println("Response content length: "
                        + entity.getContentLength());
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        response.getEntity().getContent()));

                String line = "";
                while ((line = rd.readLine()) != null) {
                    System.out.println(line);
                }
            }
            httpget.abort();
            System.out.println("----------------------------------------");
            httpclient.getConnectionManager().shutdown();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

}