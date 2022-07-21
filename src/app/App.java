package app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {

		//Fazer uma conexão HTTP e buscar os top 250 filmes;
		
			String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
			URI adress = URI.create(url);
			var client = HttpClient.newHttpClient();
			var request = HttpRequest.newBuilder(adress).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			String body = response.body();
			
		//Parsear os dados(title, rating e cover);
			JsonParser jsonParser = new JsonParser();
			List<Map<String,String>> movieData = jsonParser.parse(body);
		//Exibir os dados;
			var generator = new StickerGenerator();
			for (Map<String, String> movie : movieData) {
				
				String imageUrl = movie.get("image");
				InputStream inputStream = new URL(imageUrl).openStream();
				
				String nomeArquivo = movie.get("image")+".png";
				
				generator.create(inputStream, movie.get("title")+".png");
				System.out.println(movie.get("title"));
				System.out.println();
				
			}
	}
}
