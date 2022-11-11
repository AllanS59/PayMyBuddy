package com.PayMyBuddy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	public Iterable<User> findByEmail(String email);
	
	@Query(value = "SELECT * FROM assoc_contacts WHERE email_user = :userMail", nativeQuery = true)
	public Iterable<User> findContactsByUser(@Param("userMail") String userMail);
	
	@Query(value = "INSERT INTO assoc_contacts(`email_user`, `email_contact`) VALUES (:userMail , :contactMail", nativeQuery = true)
	public void addContactToUser(@Param("userMail") String userMail, @Param("contactMail") String contactMail);
	
	@Query(value = "DELETE FROM assoc_contacts WHERE (email_user = :userMail ) AND (email_contact = :contactMail)", nativeQuery = true)
	public void deleteContactToUser(@Param("userMail") String userMail, @Param("contactMail") String contactMail);
}
