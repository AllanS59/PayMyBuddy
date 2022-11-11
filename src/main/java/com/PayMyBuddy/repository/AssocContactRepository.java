package com.PayMyBuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.model.AssocContact;
import com.PayMyBuddy.model.AssocContactId;

@Repository
public interface AssocContactRepository extends CrudRepository<AssocContact, AssocContactId>{

	public Iterable<AssocContact> findByuserMail(String userMail);
}
