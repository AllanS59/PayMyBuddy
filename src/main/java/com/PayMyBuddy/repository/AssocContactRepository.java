package com.PayMyBuddy.repository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.model.AssocContact;
import com.PayMyBuddy.model.AssocContactId;

@Repository
public interface AssocContactRepository extends JpaRepository<AssocContact, AssocContactId>{

	public List<AssocContact> findByUserMail(String userMail);
	
	@Modifying
	@Query(value = "DELETE FROM assoc_contacts WHERE (email_user = :userMail) AND (email_contact = :contactMail)", nativeQuery = true)
	public void deleteByUserMailAndContactMail(@Param("userMail") String userMail, @Param("contactMail") String contactMail);
	
}
