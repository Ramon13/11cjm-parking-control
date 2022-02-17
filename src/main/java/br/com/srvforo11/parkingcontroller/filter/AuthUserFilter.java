package br.com.srvforo11.parkingcontroller.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.GenericFilterBean;

import br.com.srvforo11.parkingcontroller.domain.entity.User;

public class AuthUserFilter extends GenericFilterBean {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		
		if(isLoggedUser(session)) {
			if (isResetPass(session))
				response.sendRedirect(request.getContextPath()  + "/auth/redefine");
				
			chain.doFilter(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath()  + "/auth/login");
	}
	
	private boolean isLoggedUser(HttpSession session) {
		return session.getAttribute("loggedUser") != null;
	}
	
	private boolean isResetPass(HttpSession session) {
		User user = (User) session.getAttribute("loggedUser");
		return user.getResetCredentials();
	}
}
