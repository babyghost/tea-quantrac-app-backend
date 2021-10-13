package vn.tea.app.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.tea.app.core.entity.CoreModule;

public class CoreModuleSpecifications {
	public static Specification<CoreModule> quickSearch(final String search, final Boolean trangThai) {

		return new Specification<CoreModule>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -2120569606409281450L;

			@Override
			public Predicate toPredicate(Root<CoreModule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));

				if (Objects.nonNull(search) && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase().trim() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase().trim() + "%");
					predicates.add(cb.or(ten, ma));
				}
				if (Objects.nonNull(trangThai)) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
}
