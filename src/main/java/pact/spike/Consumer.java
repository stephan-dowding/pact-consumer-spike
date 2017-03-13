package pact.spike;

import okhttp3.*;
import okio.BufferedSink;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by stephandowding on 9/3/17.
 */
public class Consumer {

    private String baseUrl;

    public Consumer(String baseUrl){

        this.baseUrl = baseUrl;
    }

    OkHttpClient client = new OkHttpClient();

    public String run(String path) throws IOException {
        String url;
        try {
            url = new URIBuilder(baseUrl).setPath(path).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Eeek~~",e);
        }
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String hello(String name) throws IOException {
        String url;
        try {
            url = new URIBuilder(baseUrl).setPath("/hello").toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Eeek~~",e);
        }

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json"), name))
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }
}
