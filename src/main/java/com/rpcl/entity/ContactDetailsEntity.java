package com.rpcl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * This class is used to map with CONTACT_DETAILS table
 * @author Ritesh
 *
 */
@Data
@Entity
@Table(name = "CONTACT_DETAILS")
public class ContactDetailsEntity {
	@Id
	@GeneratedValue
	@Column(name = "CONTACT_ID",length = 10)
	private Integer contactId;
	@Column(name = "CONTACT_NAME",length = 50)
	private String contactName;
	@Column(name = "CONTACT_EMAIL",length = 50)
	private String contactEmail;
	@Column(name = "CONTACT_NUMBER",length = 10)
	private Long phno;
	@Column(name="ACTIVE_SWITCH",length = 5)
	private String activeSw;

}
