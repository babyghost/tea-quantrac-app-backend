package vn.tea.app.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.DanhMucTonGiao;
import vn.tea.app.utils.Commons;

@Repository
public class DanhMucTonGiaoDAO {
	@Autowired
	private EntityManager entityManager;

	public List<DanhMucTonGiao> getAll(Pageable pageable, String searchData, String sortData) {
		List<DanhMucTonGiao> listTonGiao = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		Boolean firstClause = true;
		Object maTonGiao = null;
		Object tenTonGiao = null;
		sql.append("FROM DanhMucTonGiao tg");
		if (searchData != null && searchData != "") {
			Map<String, Object> searchDataObject = Commons.splitRequestParamsFromURL(searchData);
			maTonGiao = searchDataObject.get("maTonGiao");
			if (maTonGiao != null) {
				if (firstClause) {
					sql.append(" WHERE");
					firstClause = false;
				} else {
					sql.append(" AND");
				}
				sql.append(" LOWER(tg.ma) LIKE :maTonGiao");
			}
			tenTonGiao = searchDataObject.get("tenTonGiao");
			if (tenTonGiao != null) {
				if (firstClause) {
					sql.append(" WHERE");
					firstClause = false;
				} else {
					sql.append(" AND");
				}
				sql.append(" LOWER(tg.ten) LIKE :tenTonGiao");
			}
		}
		sql.append(" ORDER BY ").append(Commons.convertSortDataWithAlias(sortData, "tg"));
		TypedQuery<DanhMucTonGiao> query = entityManager.createQuery(sql.toString(), DanhMucTonGiao.class);
		if (maTonGiao != null) {
			query.setParameter("maTonGiao", "%" + maTonGiao.toString().toLowerCase() + "%");
		}
		if (tenTonGiao != null) {
			query.setParameter("tenTonGiao", "%" + tenTonGiao.toString().toLowerCase() + "%");
		}
		
		if (pageable != null) {
			listTonGiao = query.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize())
					.getResultList();
		} else {
			listTonGiao = query.getResultList();
		}
		return listTonGiao;
	}
	
	public Integer getCount(String searchData) {
		
		StringBuilder sql = new StringBuilder();
		Object maTonGiao = null;
		Object tenTonGiao = null;
		sql.append(" SELECT COUNT(*) ");
		sql.append(" FROM DanhMucTonGiao tg ");
		sql.append(" WHERE 1 = 1 ");
		
		if (searchData != null && searchData != "") {
			Map<String, Object> searchDataObject = Commons.splitRequestParamsFromURL(searchData);
			maTonGiao = searchDataObject.get("maTonGiao");
			if (maTonGiao != null) {
				sql.append(" AND LOWER(tg.ma) LIKE :maTonGiao");
			}
			tenTonGiao = searchDataObject.get("tenTonGiao");
			if (tenTonGiao != null) {
				sql.append(" AND LOWER(tg.ten) LIKE :tenTonGiao");
			}
		}
		
		TypedQuery<Long> query = entityManager.createQuery(sql.toString(), Long.class);
		
		if (maTonGiao != null) {
			query.setParameter("maTonGiao", "%" + maTonGiao.toString().toLowerCase() + "%");
		}
		if (tenTonGiao != null) {
			query.setParameter("tenTonGiao", "%" + tenTonGiao.toString().toLowerCase() + "%");
		}
		
		return query.getSingleResult().intValue();
	}
}
