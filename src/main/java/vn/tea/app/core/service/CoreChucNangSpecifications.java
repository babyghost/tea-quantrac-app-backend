package vn.tea.app.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.tea.app.core.entity.CoreChucNang;

public class CoreChucNangSpecifications {
	public static Specification<CoreChucNang> quickSearch(final String search, final Long nhomChucNangId,
			final Integer trangThai) {

		return new Specification<CoreChucNang>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -994040784091677712L;

			@Override
			public Predicate toPredicate(Root<CoreChucNang> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));

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
