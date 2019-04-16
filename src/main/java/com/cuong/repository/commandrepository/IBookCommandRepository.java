package com.cuong.repository.commandrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuong.entity.Book;

@Repository
public interface IBookCommandRepository extends JpaRepository<Book, Integer> {
	Book findByUid(int uid);
	Book findByCode(String code);
	List<Integer> getListUid();
	List<String> getListCode();

}
