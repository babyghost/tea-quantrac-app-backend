
package vn.tea.app.config.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import vn.tea.app.core.entity.CoreRole;
import vn.tea.app.core.entity.CoreUser;
import vn.tea.app.core.entity.CustomUserDetails;
import vn.tea.app.core.service.CoreRoleRepo;
import vn.tea.app.core.service.CoreUserRepo;

@Slf4j
public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
	private final String JWT_SECRET = "linhlt";
	private final long JWT_EXPIRATION = 604800000L;
	private final static String TOKEN_HEADER = "Authorization";

	@Autowired
	private CoreUserRepo coreUserRepo;
	@Autowired
	private CoreRoleRepo coreRoleRepo;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(TOKEN_HEADER);
		log.info("authToken", authToken);
		if (this.validateToken(authToken)) {
			String username = this.getUserNameFromJWT(authToken);
			log.info("username: "+ username);
			CoreUser coreUser = coreUserRepo.findFirstByUserNameAndDaXoa(username, 0);
			if (coreUser != null) {
				boolean enabled = true;
				boolean accountNonExpired = true;
				boolean credentialsNonExpired = true;
				boolean accountNonLocked = true;
				UserDetails userDetail = new User(username, coreUser.getPassword(), enabled, accountNonExpired,
						credentialsNonExpired, accountNonLocked, getAuthorities(coreUser));

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
						null, userDetail.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}
	
	public String generateToken(CustomUserDetails userDetails) {
		// Lấy thông tin user
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		// Tạo chuỗi json web token từ id của user.
		return Jwts.builder().setSubject(userDetails.getUser().getUserName()).setIssuedAt(now)
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}
	
	public String generateToken(UserDetails userDetails) {
		// Lấy thông tin user
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		// Tạo chuỗi json web token từ id của user.
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(now)
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}
	
//	public String generateTokenByUserName(String userName) {
//		// Lấy thông tin user
//		Date now = new Date();
//		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
//		// Tạo chuỗi json web token từ id của user.
//		return Jwts.builder().setSubject(userName).setIssuedAt(now)
//				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
//	}

	public String getUserNameFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}
	
	public List<GrantedAuthority> getAuthorities(CoreUser coreUser) {
		List<CoreRole> roles = coreRoleRepo.getRoles(coreUser.getId());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (CoreRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getTen()));
		}
		return authorities;
	}
}