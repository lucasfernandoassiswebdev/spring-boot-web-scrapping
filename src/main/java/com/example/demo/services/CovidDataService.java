package com.example.demo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.dto.CovidDataDto;

public class CovidDataService {

	public static List<CovidDataDto> retrieveCovidData() throws HttpStatusException, IOException {
		List<CovidDataDto> covidDataList = new ArrayList<>();

		try {
			// retrieving the desired web page
			Document webPage;
			try {
				webPage = Jsoup.connect("https://en.wikipedia.org/wiki/COVID-19_pandemic_by_country_and_territory")
						.get();
			} catch (IOException e) {
				throw e;
			}

			Element tbody = webPage.getElementById("thetable").getElementsByTag("tbody").get(0);

			Elements rows = tbody.children(); // dropping the headers
			rows.remove(0);
			rows.remove(0);

			for (Element row : rows) {
				try {
					String country = row.getElementsByTag("a").get(0).text();

					Elements tds = row.getElementsByTag("td");

					// skipping the footer
					if (tds.size() < 3)
						continue;

					Integer cases = Integer.parseInt(tds.get(1).text().replace(",", ""));
					Integer deaths = Integer.parseInt(tds.get(2).text().replace(",", ""));
					Integer recoveries = Integer.parseInt(tds.get(3).text().replace(",", ""));

					covidDataList.add(new CovidDataDto(country, cases, deaths, recoveries));
				} catch (Exception e) {
					
				}
			}
		} catch (HttpStatusException e) {
			// an error occurred while connecting to the page

			// logging errors
			// ...

			throw e;
		}

		return covidDataList;
	}
}
