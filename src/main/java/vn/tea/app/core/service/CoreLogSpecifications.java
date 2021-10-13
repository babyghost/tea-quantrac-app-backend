package vn.tea.app.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.tea.app.core.entity.CoreLog;

public class CoreLogSpecifications {
	public static Specification<CoreLog> quickSearch(final String nguoiCapNhat, final Long objectId,
			final Integer type) {

		return new Specification<CoreLog>() {
			private static final long serialVersionUID = -994040784091677712L;

			@Override
			public Predicate toPredicate(Root<CoreLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (Objects.nonNull(nguoiCapNhat) && !nguoiCapNhat.isEmpty()) {
					predicates.add(cb.equal(root.<String>get("nguoiCapNhat"), nguoiCapNhat));
				}
				if(Objects.nonNull(objectId)) {
					predicates.add(cb.equal(root.<String>get("objectId"), objectId));
				}
				if (Objects.nonNull(type)) {
					predicates.add(cb.equal(root.<String>get("type"), type));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
}
