package com.rpcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rpcl.entity.ContactDetailsEntity;
/**
 * This interface is used to interact with database for performing CURD operation
 * @author Ritesh
 *
 */
@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetailsEntity, Integer> {

	@Modifying
	@Query(value = "update ContactDetailsEntity set activeSw=:sw where contactId=:cid")
	public void softDeleteContactById(String sw,Integer cid);
}
