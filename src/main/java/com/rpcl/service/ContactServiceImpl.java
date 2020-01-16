package com.rpcl.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpcl.domain.Contact;
import com.rpcl.entity.ContactDetailsEntity;
import com.rpcl.repository.ContactDetailsRepository;
import com.rpcl.utils.EmailUtils;
/**
 * This class is used to process the business logic
 * @author Ritesh
 *
 */
@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactDetailsRepository contactDtlsRepo;
	@Autowired
	private EmailUtils emailUtil;

	/**
	 * This method is used to save or update the contact 
	 */
	@Override
	public boolean saveContact(Contact contact) {
		ContactDetailsEntity entity=new ContactDetailsEntity();
		entity.setActiveSw("Y");
		BeanUtils.copyProperties(contact, entity);
		entity = contactDtlsRepo.save(entity);
		boolean isSaved=entity.getContactId()>0;
		if(isSaved)
			sendTheMail(contact);
		return isSaved;
	}
	
	/**
	 * This method is used to get all the filtered contacts from database
	 */
	@Override
	public List<Contact> getAllContacts() {
		List<Contact> contactList=new ArrayList<>();
		List<ContactDetailsEntity> entityList = contactDtlsRepo.findAll();
		List<ContactDetailsEntity> filteredEntiList = entityList.stream().filter(entity->"Y".equals(entity.getActiveSw())).collect(Collectors.toList());
		filteredEntiList.forEach(entity->{
			Contact contact=new Contact();
			BeanUtils.copyProperties(entity, contact);
			contactList.add(contact);
		});
		return contactList;
	}

	/**
	 * This method is used to find one Contact By passing their id
	 */
	@Override
	public Contact getContactById(Integer cid) {
		Optional<ContactDetailsEntity> optional = contactDtlsRepo.findById(cid);
		Contact contact=null;
		if(optional.isPresent()) {
			ContactDetailsEntity entity = optional.get();
			contact=new Contact();
			BeanUtils.copyProperties(entity, contact);
		}
		
		return contact;
	}

	/**
	 * This method is used to delete the one contact temporarily
	 */
	@Transactional
	@Override
	public boolean deleteContactById(Integer cid) {
		contactDtlsRepo.softDeleteContactById("N", cid);
		return true;
	}
	
	/**
	 * This method is used to send mail bay passing contact
	 * @param c
	 */
	private void sendTheMail(Contact c) {
		String to=c.getContactEmail();
		String subject="Test Subject";
		String body;
		try {
			body = getBody(c);
			emailUtil.sendMail(to, subject, body);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to get body from txt file
	 * @param c
	 * @return
	 * @throws IOException
	 */
	private String getBody(Contact c) throws IOException {
		File file=new File("email-template.txt");
		FileReader reader=new FileReader(file);
		BufferedReader br=new BufferedReader(reader);
		String line=null;
		StringBuilder builder=new StringBuilder();
		while((line=br.readLine())!=null) {
			
			if(line.contains("${NAME}")) {
				line=line.replace("${NAME}", c.getContactName());
			}
			if(line.contains("${MOB}")) {
				line=line.replace("${MOB}", String.valueOf(c.getPhno()));
			}
			if(line.contains("${FRIEND-NAME}")) {
				line=line.replace("${FRIEND-NAME}", "Ritesh");
			}
			builder.append(line);
			
		}
		br.close();
		return builder.toString();
	}

}
