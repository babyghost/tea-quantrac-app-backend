package vn.tea.app.ungdung.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.tea.app.ungdung.entity.QuanTracDanhMuc;

public class QuanTracDanhMucSpecifications {
	public static Specification<QuanTracDanhMuc> quickSearch(final String ten, Long donViHanhChinhId, String catCode, String ma) {

		return new Specification<QuanTracDanhMuc>() {
			private static final long serialVersionUID = 1800791559336974675L;

			@Override
			public Predicate toPredicate(Root<QuanTracDanhMuc> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));

				if (Objects.nonNull(ten) && !ten.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("ten")), "%" + ten.toLowerCase().trim() + "%"));
				}
				
				if (Objects.nonNull(donViHanhChinhId) && donViHanhChinhId > 0) {
					predicates.add(cb.equal(root.get("chaId"), donViHanhChinhId));
				}
				
				if (Objects.nonNull(ma) && !ma.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("ma")), "%" + ma.toLowerCase().trim() + "%"));
				}
				
				if (Objects.nonNull(catCode) && !catCode.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("catCode")), "%" + catCode.toLowerCase().trim() + "%"));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
}
