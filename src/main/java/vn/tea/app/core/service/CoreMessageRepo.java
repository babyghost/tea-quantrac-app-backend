package vn.tea.app.core.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.CoreMessage;

@Repository
public interface CoreMessageRepo extends JpaRepository<CoreMessage, Long>, JpaSpecificationExecutor<CoreMessage> {
	public List<CoreMessage> findByIdIn(List<Long> idList);
}