package vn.tea.app.exceptions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException ex) throws IOException, ServletException {
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
        apiError.setCode("012");
        //apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
        apiError.setMessage("Access denied");
        apiError.setDebugMessage(ex.getMessage());
        ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
	    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  		String json = mapper.writeValueAsString(apiError);
        //  mapper.writeValue(out, apiError);
	      httpServletResponse.setContentType("application/json");
	      httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
	      httpServletResponse.setCharacterEncoding("UTF-8");
	      httpServletResponse.getWriter().write(json);
    }
}