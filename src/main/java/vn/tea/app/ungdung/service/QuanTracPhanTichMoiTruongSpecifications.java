package vn.tea.app.ungdung.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.ungdung.entity.QuanTracPhanTichMoiTruong;

@Slf4j
public class QuanTracPhanTichMoiTruongSpecifications {
	
	
	public static Specification<QuanTracPhanTichMoiTruong> quickSearch(final String ten, final Long donViHanhChinhId, LocalDate ngayTaoTuNgay, LocalDate ngayTaoDenNgay) {

		return new Specification<QuanTracPhanTichMoiTruong>() {
			private static final long serialVersionUID = 1800791559336974675L;

			@Override
			public Predicate toPredicate(Root<QuanTracPhanTichMoiTruong> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));
//				predicates.add(cb.equal(root.<String>get("trangThai"), 1));

				if (Objects.nonNull(ten) && !ten.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("ten")), "%" + ten.toLowerCase().trim() + "%"));
				}
				log.info("donViHanhChinhId  "+donViHanhChinhId);
				if (Objects.nonNull(donViHanhChinhId) && donViHanhChinhId > 0) {
					predicates.add(cb.equal(root.get("chaId"), donViHanhChinhId));
				}
				
				if (Objects.nonNull(ngayTaoTuNgay)) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayTao").as(LocalDate.class),  ngayTaoTuNgay));
				}
				
				if (Objects.nonNull(ngayTaoDenNgay)) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayTao").as(LocalDate.class),  ngayTaoDenNgay));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
}
