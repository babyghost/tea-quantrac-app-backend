package vn.tea.app.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.tea.app.core.entity.CoreMessage;


public class CoreMessageSpecifications {
public static Specification<CoreMessage> quickSearch(final Long donViId,final Long phongBanId,final String nguoiDungId) {
		
		return new Specification<CoreMessage>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6362583029010954025L;
			@Override
			public Predicate toPredicate(Root<CoreMessage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
			//	predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if  (donViId!=null  &&  donViId>0) {
					predicates.add(cb.equal(root.<Long>get("donViId"), donViId));
				}
				if  (phongBanId!=null  &&  phongBanId>0) {
					predicates.add(cb.equal(root.<Long>get("phongBanId"), phongBanId));
				}
				if  (nguoiDungId!=null  &&  !nguoiDungId.isEmpty()) {
					//predicates.add(cb.like(root.<String>get("nguoiDungId"), nguoiDungId));
					predicates.add(cb.like(cb.lower(root.<String>get("nguoiDungId")), "%" + nguoiDungId.toLowerCase() + "%"));
				}
				if(predicates.size() > 0){
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
			
		};
	}
}