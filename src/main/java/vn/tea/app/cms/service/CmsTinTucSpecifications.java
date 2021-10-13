package vn.tea.app.cms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.tea.app.cms.entity.CmsTinTuc;

public class CmsTinTucSpecifications {
	public static Specification<CmsTinTuc> quickSearch(final String ten) {

		return new Specification<CmsTinTuc>() {
			private static final long serialVersionUID = 1800791559336974675L;

			@Override
			public Predicate toPredicate(Root<CmsTinTuc> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	
	public static Specification<CmsTinTuc> danhSachTinTucPublic(final String search, List<Long> danhMucIds) {

		return new Specification<CmsTinTuc>() {
			private static final long serialVersionUID = 1800791559336974675L;

			@Override
			public Predicate toPredicate(Root<CmsTinTuc> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));
				predicates.add(cb.equal(root.<String>get("trangThai"), 1));

				if (Objects.nonNull(search) && !search.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase().trim() + "%"));
				}

				if (danhMucIds != null) {
					Expression<List<Long>> listDanhMucId = cb.literal(danhMucIds);
					Expression<String> expression = root.get("listCmsTinTuc2DanhMuc").get("danhMucId");
					Predicate inList = expression.in(listDanhMucId);
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
