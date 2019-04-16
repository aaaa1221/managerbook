@book_api
Feature: Book API related features

# Normal Cases
  @book_creation
  Scenario: I can  create a book record in database
  Given I have list book
    When I create an book with the following information, I should get the response with http status code "201"
     |Uid | code | name      | description       | category | author            | publisher 					| create_date | create_user    | update_date | update_user |
     |	9	| b1   | văn cường | giọt lệ lung linh | AAA      | VanCuong    			| mùa xuân nóng bỏng  | 4/4/2019 		| cuong 					| 5/4/2019    | NVCUONG2  	|
    Then the following book record should exist in the database
      |Uid 	| code | name      | description       | category | author            | publisher 					| create_date | create_user    | update_date | update_user |
      |	9		| b1   | văn cường | giọt lệ lung linh | AAA      | VanCuong    			| mùa xuân nóng bỏng  | 4/4/2019 		| cuong 					| 5/4/2019    | NVCUONG2  	|
