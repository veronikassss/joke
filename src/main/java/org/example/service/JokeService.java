package org.example.service;

import org.example.exception.JokeServiceException;
import org.example.models.Joke;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JokeService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Joke getJoke() {
        String url = "https://baneks.ru/random";
        try {
            // Получаем HTML страницы
            String html = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    String.class
            ).getBody();

            Long jokeId = extractIdFromHtml(html);
            String text = extractJokeText(html);

            return new Joke(jokeId, text);

        } catch (RestClientException | JokeServiceException e) {
            throw new JokeServiceException("Ошибка при получении анекдота", e);
        }
    }

    private Long extractIdFromHtml(String html) {
        Pattern pattern = Pattern.compile("<main\\s+[^>]*data-id=\"(\\d+)\"");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            try {

                return Long.parseLong(matcher.group(1));
            } catch (NumberFormatException e) {
                throw new JokeServiceException("Некорректный ID");
            }
        }
        throw new JokeServiceException("ID не найден");
    }

    private String extractJokeText(String html) {
        // Ищем основной контент анекдота
        int start = html.indexOf("<p>");
        int end = html.indexOf("</p>", start);

        if (start != -1 && end != -1 && end > start) {
            String rawText = html.substring(start + 3, end)
                    .replaceAll("<[^>]+>", "") // Удаляем все HTML-теги
                    .replaceAll("(?i)<br\\s*/?>", "\n") // Заменяем <br> на переносы
                    .replaceAll("&nbsp;", " ") // Неразрывные пробелы → обычные
                    .trim();

            return rawText.isEmpty() ? "Текст анекдота отсутствует" : rawText;
        }

        return "Анекдот не найден";
    }
}