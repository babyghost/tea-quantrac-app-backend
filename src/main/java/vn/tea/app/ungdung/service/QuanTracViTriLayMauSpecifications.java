package vn.tea.app.ungdung.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.ungdung.entity.QuanTracViTriLayMau;

@Slf4j
public class QuanTracViTriLayMauSpecifications {
	
	
	public static Specification<QuanTracViTriLayMau> quickSearch(final Long phanTichId, final List<Long> viTriLayMauIds) {

		return new Specification<QuanTracViTriLayMau>() {
			private static final long serialVersionUID = 1800791559336974675L;

			@Override
			public Predicate toPredicate(Root<QuanTracViTriLayMau> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));

				if (Objects.nonNull(phanTichId) && phanTichId > 0) {
					predicates.add(cb.equal(root.get("phanTichId"), phanTichId));
				}
//				if (Objects.nonNull(tacDongId) && tacDongId > 0) {
//					predicates.add(cb.equal(root.get("tacDongId"), tacDongId));
//				}
				if (viTriLayMauIds != null && viTriLayMauIds.size() > 0) {
					Expression<List<Long>> listDanhMucId = cb.literal(viTriLayMauIds);
					Expression<String> expression = root.get("viTriQuanTracId");
					Predicate inList = expression.in(listDanhMucId);
					predicates.add(inList);
				}
				cb.desc(root.get("viTriQuanTracId"));
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
	public static Specification<QuanTracViTriLayMau> searcViTriByPhanTich(final Long phanTichId, final String ten) {

		return new Specification<QuanTracViTriLayMau>() {
			private static final long serialVersionUID = 1800791559336974675L;

			@Override
			public Predicate toPredicate(Root<QuanTracViTriLayMau> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));

				if (Objects.nonNull(phanTichId) && phanTichId > 0) {
					predicates.add(cb.equal(root.get("phanTichId"), phanTichId));
				}
				if (Objects.nonNull(ten) && !ten.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("viTriLayMau")), "%" + ten.toLowerCase().trim() + "%"));
				}
				cb.desc(root.get("viTriQuanTracId"));
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
	
}
