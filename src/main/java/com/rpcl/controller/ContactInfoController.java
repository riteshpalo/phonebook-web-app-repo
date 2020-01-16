package com.rpcl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpcl.constant.AppConstant;
import com.rpcl.domain.Contact;
import com.rpcl.properties.AppProperties;
import com.rpcl.service.ContactService;

/**
 * This class is used to handle request from Contact Info page
 * @author Ritesh
 *
 */
@Controller
public class ContactInfoController {
	
	@Autowired 
	private ContactService contactService;
	@Autowired
	AppProperties app;
	
	/**
	 * This method is used to display Contact Info form on browser
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/")
	public String displayContactForm(Model model) {
		Contact contact=new Contact();
		model.addAttribute(AppConstant.CONTACT, contact);
		return AppConstant.CONTACT_INFO;
	}
	
	/**
	 * This method is used to handle submit button
	 * @param contact
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/handleSubmitBtn" ,method = RequestMethod.POST)
	public String handleSubmitBtn(@ModelAttribute("contact")Contact contact,RedirectAttributes attributes,HttpServletRequest request) {
		boolean flag = contactService.saveContact(contact);
		String contactId = request.getParameter("contactId");
		if(contactId!=null && !contactId.isEmpty()) {
			if(flag) {
				attributes.addFlashAttribute(AppConstant.SUCCESS_MSG, app.getMessages().get(AppConstant.UPDATE_SUCCESS));
			}else {
				attributes.addFlashAttribute(AppConstant.ERROR_MSG, app.getMessages().get(AppConstant.UPDATE_ERROR));
			}
		}else {
			if(flag) {
				attributes.addFlashAttribute(AppConstant.SUCCESS_MSG, app.getMessages().get(AppConstant.SAVE_SUCCESS));
			}else {
				attributes.addFlashAttribute(AppConstant.ERROR_MSG, app.getMessages().get(AppConstant.SAVE_ERROR));
			}
		}
		
		return "redirect:/handleSubmitBtnSuccessfully";
	}
	
	/**
	 * This method is used to handle submit button successfully post request submission
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/handleSubmitBtnSuccessfully",method = RequestMethod.GET)
	public String handleSubmitBtnSuccessfully(Model model) {
		Contact contact=new Contact();
		model.addAttribute(AppConstant.CONTACT,contact );
		return AppConstant.CONTACT_INFO;
	}
	/**
	 * This method is used to display All contacts in the browser
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/viewAllContacts")
	public String viewContacts(Model model) {
		List<Contact> contactList=contactService.getAllContacts();
		model.addAttribute(AppConstant.CONTACTS, contactList);
		return AppConstant.VIEW_CONTACTS;
	}

}
