package com.rpcl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpcl.constant.AppConstant;
import com.rpcl.domain.Contact;
import com.rpcl.service.ContactService;

/**
 * This class is used to handle request from View Contacts page
 * @author Ritesh
 *
 */
@Controller
public class ViewContactsController {
	
	@Autowired
	private ContactService contactService;
	
	/**
	 * This method is used to get one Contact by passing  Contact id
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/editContact")
	public String editContact(HttpServletRequest request,Model model) {
		String contactId = request.getParameter("contactId");
		if(contactId!=null && !contactId.isEmpty()) {
			Integer cid=Integer.parseInt(contactId);
			Contact contact = contactService.getContactById(cid);
			model.addAttribute(AppConstant.CONTACT, contact);
		}
		return AppConstant.CONTACT_INFO;
	}
	
	@RequestMapping(value = "/deleteContact")
	public String deleteContact(HttpServletRequest  request) {
		String contactId = request.getParameter("contactId");
		if(contactId!=null && !contactId.isEmpty()) {
			int cid = Integer.parseInt(contactId);
			contactService.deleteContactById(cid);
		}
		return "redirect:/viewAllContacts";
	}

}
