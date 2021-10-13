package vn.tea.app.config.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Optional<String> result = Optional.empty();
		if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails ) {
			log.info("Tên đăng nhập tự động");
			return Optional.of(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getUsername());
		} else {
			log.info("else :" + result);
			return result;
		}
	}
}
