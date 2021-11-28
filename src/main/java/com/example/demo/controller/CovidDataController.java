package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.jsoup.HttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CovidDataDto;
import com.example.demo.services.CovidDataService;

@RestController
@RequestMapping("/covid/")
public class CovidDataController {

	class ErrorResponse {
		public String message = "Erro";
	}

	@GetMapping("data")
	ResponseEntity<List<CovidDataDto>> getCovidData() {
		try {
			return new ResponseEntity(CovidDataService.retrieveCovidData(), HttpStatus.OK);
		} catch (HttpStatusException e) {
			return new ResponseEntity(new ErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			return new ResponseEntity(new ErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
