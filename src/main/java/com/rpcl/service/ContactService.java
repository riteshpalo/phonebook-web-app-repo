package com.rpcl.service;

import java.util.List;

import com.rpcl.domain.Contact;
/**
 * This interface is used to process business logic
 * @author Ritesh
 *
 */
public interface ContactService {
	public boolean saveContact(Contact contact);
	public List<Contact> getAllContacts();
	public Contact getContactById(Integer cid);
	public boolean deleteContactById(Integer cid);

}
