package vn.tea.app.exceptions;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorResponse {	
	private String serverName = "tea-app-backend";
	private Date errorTimestamp = new Date();
	private String errorCode;
	private String errorMessage;
	private String errorDetails;
}
