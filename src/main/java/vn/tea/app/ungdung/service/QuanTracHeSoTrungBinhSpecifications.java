package vn.tea.app.ungdung.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.tea.app.ungdung.entity.QuanTracHeSoTrungBinh;

public class QuanTracHeSoTrungBinhSpecifications {
	public static Specification<QuanTracHeSoTrungBinh> quickSearch(final String ten) {

		return new Specification<QuanTracHeSoTrungBinh>() {
			private static final long serialVersionUID = 1800791559336974675L;

			@Override
			public Predicate toPredicate(Root<QuanTracHeSoTrungBinh> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));

				if (Objects.nonNull(ten) && !ten.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("ten")), "%" + ten.toLowerCase().trim() + "%"));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
}
