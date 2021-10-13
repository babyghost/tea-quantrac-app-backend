package vn.tea.app.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.DanhMucDanToc;
import vn.tea.app.utils.Commons;

@Repository
public class DanhMucDanTocDAO {
	@Autowired
	private EntityManager entityManager;

	public List<DanhMucDanToc> getAll(Pageable pageable, String searchData, String sortData) {
		List<DanhMucDanToc> listDanToc = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		Boolean firstClause = true;
		Object maDanToc = null;
		Object tenDanToc = null;
		sql.append("FROM DanhMucDanToc dt");
		if (searchData != null && searchData != "") {
			Map<String, Object> searchDataObject = Commons.splitRequestParamsFromURL(searchData);
			maDanToc = searchDataObject.get("maDanToc");
			if (maDanToc != null) {
				if (firstClause) {
					sql.append(" WHERE");
					firstClause = false;
				} else {
					sql.append(" AND");
				}
				sql.append(" LOWER(dt.ma) LIKE :maDanToc");
			}
			tenDanToc = searchDataObject.get("tenDanToc");
			if (tenDanToc != null) {
				if (firstClause) {
					sql.append(" WHERE");
					firstClause = false;
				} else {
					sql.append(" AND");
				}
				sql.append(" LOWER(dt.ten) LIKE :tenDanToc");
			}
		}
		sql.append(" ORDER BY ").append(Commons.convertSortDataWithAlias(sortData, "dt"));
		TypedQuery<DanhMucDanToc> query = entityManager.createQuery(sql.toString(), DanhMucDanToc.class);
		if (maDanToc != null) {
			query.setParameter("maDanToc", "%" + maDanToc.toString().toLowerCase() + "%");
		}
		if (tenDanToc != null) {
			query.setParameter("tenDanToc", "%" + tenDanToc.toString().toLowerCase() + "%");
		}
		
		if (pageable != null) {
			listDanToc = query.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize())
					.getResultList();
		} else {
			listDanToc = query.getResultList();
		}
		return listDanToc;
	}
	
	public Integer getCount(String searchData) {
		
		StringBuilder sql = new StringBuilder();
		Object maDanToc = null;
		Object tenDanToc = null;
		sql.append(" SELECT COUNT(*) ");
		sql.append(" FROM DanhMucDanToc dt ");
		sql.append(" WHERE 1 = 1 ");
		
		if (searchData != null && searchData != "") {
			Map<String, Object> searchDataObject = Commons.splitRequestParamsFromURL(searchData);
			maDanToc = searchDataObject.get("maDanToc");
			if (maDanToc != null) {
				sql.append(" AND LOWER(dt.ma) LIKE :maDanToc");
			}
			tenDanToc = searchDataObject.get("tenDanToc");
			if (tenDanToc != null) {
				sql.append(" AND LOWER(dt.ten) LIKE :tenDanToc");
			}
		}
		
		TypedQuery<Long> query = entityManager.createQuery(sql.toString(), Long.class);
		
		if (maDanToc != null) {
			query.setParameter("maDanToc", "%" + maDanToc.toString().toLowerCase() + "%");
		}
		if (tenDanToc != null) {
			query.setParameter("tenDanToc", "%" + tenDanToc.toString().toLowerCase() + "%");
		}
		
		return query.getSingleResult().intValue();
	}
}
