package vn.tea.app.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.tea.app.core.entity.CoreUser;
import vn.tea.app.utils.CoreUtil;

public class CoreUserSpecifications {
	public static Specification<CoreUser> quickSearch(final String email, final String hoTen,
			final List<Long> roleIds) {

		return new Specification<CoreUser>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1800791559336974675L;

			@Override
			public Predicate toPredicate(Root<CoreUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));

				if (Objects.nonNull(email) && !email.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("email")), "%" + email.toLowerCase().trim() + "%"));
				}
				if (hoTen != null && !hoTen.isEmpty()) {
					Predicate pHoTen = cb.like(cb.lower(root.<String>get("hoTen")),
							"%" + hoTen.toLowerCase().trim() + "%");
					Predicate convertTVkdauTen = cb.like(
							cb.function("convertTVkdau", String.class, cb.lower(root.get("hoTen"))),
							"%" + CoreUtil.stripAccents(hoTen.toLowerCase().trim()) + "%");
					predicates.add(cb.or(pHoTen, convertTVkdauTen));
				}
				if (roleIds != null && !roleIds.isEmpty()) {
					Expression<List<Long>> vRoleIds = cb.literal(roleIds);
					Expression<String> expression = root.join("coreRoles").get("id");
					Predicate inList = expression.in(vRoleIds);
					predicates.add(inList);
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
}
