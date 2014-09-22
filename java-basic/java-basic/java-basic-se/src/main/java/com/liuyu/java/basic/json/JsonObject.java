package com.liuyu.java.basic.json;

import java.util.Date;

/**
 * json object
 * @author pengyao
 *
 */
public class JsonObject {
	
	private String strNotNull;
	
	private String strNull;
	
	private Date dateNull;
	
	private Date dateNotNull;

	public String getStrNotNull() {
		return strNotNull;
	}

	public void setStrNotNull(String strNotNull) {
		this.strNotNull = strNotNull;
	}

	public String getStrNull() {
		return strNull;
	}

	public void setStrNull(String strNull) {
		this.strNull = strNull;
	}

	public Date getDateNull() {
		return dateNull;
	}

	public void setDateNull(Date dateNull) {
		this.dateNull = dateNull;
	}

	public Date getDateNotNull() {
		return dateNotNull;
	}

	public void setDateNotNull(Date dateNotNull) {
		this.dateNotNull = dateNotNull;
	}
	
}
