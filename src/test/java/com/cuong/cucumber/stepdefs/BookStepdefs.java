package com.cuong.cucumber.stepdefs;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.ResponseEntity;
import com.cuong.cucumber.config.CucumberConfig;
import com.cuong.entity.Book;
import com.cuong.errorhandling.BookException;
import com.cuong.repository.commandrepository.IBookCommandRepository;
import com.cuong.service.commandservice.commandserviceimpl.BookCommandServiceImpl;
import com.cuong.service.queryservice.queryserviceimpl.BookQueryServiceImpl;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SuppressWarnings("unused")
public class BookStepdefs extends CucumberConfig {

	@Given("^I have list book$")
	public void i_have_list_book() throws Throwable {

	}

	@When("^I create an book with the following information, I should get the response with http status code \"([^\"]*)\"$")
	public void i_create_an_book_with_the_following_information_I_should_get_the_response_with_http_status_code(
			String arg1, DataTable arg2) throws Throwable {

	}

	@Then("^the following book record should exist in the database$")
	public void the_following_book_record_should_exist_in_the_database(DataTable arg1) throws Throwable {

	}

}
