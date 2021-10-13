package vn.tea.app.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.tea.app.core.entity.CoreCaiDatHienThi;

public class CoreCaiDatHienThiSpecifications {
public static Specification<CoreCaiDatHienThi> quickSearch(final String ma) {
		
		return new Specification<CoreCaiDatHienThi>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6362583029010954025L;
			@Override
			public Predicate toPredicate(Root<CoreCaiDatHienThi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (ma != null && !ma.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("maDanhSach")), "%" + ma.toLowerCase() + "%"));
					//Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase() + "%");
				}
				if(!predicates.isEmpty()){
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
			
		};
	}
}