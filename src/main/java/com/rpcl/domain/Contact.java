package com.rpcl.domain;

import lombok.Data;
/**
 * This class is used to bind the Contact Info form
 * @author Ritesh
 *
 */
@Data
public class Contact {
	private Integer contactId;
	private String contactName;
	private String contactEmail;
	private Long phno;

}
