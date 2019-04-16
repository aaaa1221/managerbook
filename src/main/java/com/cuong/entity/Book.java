package com.cuong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book", schema = "macbook")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "uid", nullable = false)
  int uid;
  
  @NotEmpty(message = "code must not be null nor empty")
  @NotBlank(message = " code must not be null and must contain at least one non-whitespace character.")
  @Column(name = "code")
  @Size(max = 20 , message = "code must not exceed 20 characters")
  String code;
  
 
  @Column(name = "name")
  @NotEmpty(message = "name must not be null nor empty")
  @NotBlank(message = " name must not be null and must contain at least one non-whitespace character.")  @Size(max = 255, message = "Name  must not exceed 255 characters")
  String name;

 
  @Size(max = 500, message = "description must not exceed 500 characters")
  @Column(name = "description")
  String description;

  @Column(name = "category")
  @NotEmpty(message = "category must not be null nor empty")
  @NotBlank(message = " category must not be null and must contain at least one non-whitespace character.")
  @Size(max = 255, message = "category must not exceed 255 characters")
  String category;


  @Column(name = "author")
  @NotEmpty(message = "author must not be null nor empty")
  @NotBlank(message = " author must not be null and must contain at least one non-whitespace character.")
  @Size(max = 255, message = "author must not exceed 255 characters")
  String author;

  
  @Column(name = "publisher")
  @NotEmpty(message = "publisher must not be null nor empty")
  @NotBlank(message = " publisher must not be null and must contain at least one non-whitespace character.")
  @Size(max = 255, message = "publisher must not exceed 255 characters")
  String publisher;

 
  @Column(name = "createUser")
  @NotEmpty(message = "createUser must not be null nor empty")
  @NotBlank(message = " createUser must not be null and must contain at least one non-whitespace character.")
  @Size(max = 100, message = "createUser must not exceed 100 characters")
  String createUser;

  
  @Column(name = "createDate")
  @NotEmpty(message = "createDate must not be null nor empty")
  @NotBlank(message = " createDate must not be null and must contain at least one non-whitespace character.")
  @Size(max = 100, message = "createDate must not exceed 100 characters")
  String createDate;


  @Column(name = "updateUser")
  @NotEmpty(message = "updateUser must not be null nor empty")
  @NotBlank(message = " updateUser must not be null and must contain at least one non-whitespace character.")
  @Size(max = 100, message = "updateUser must not exceed 100 characters")
  String updateUser;


  @Column(name = "updateDate")
  @NotEmpty(message = "updateDate must not be null nor empty")
  @NotBlank(message = " updateDate must not be null and must contain at least one non-whitespace character.")
  @Size(max = 100, message = "updateDate must not exceed 100 characters")
  String updateDate;

}
