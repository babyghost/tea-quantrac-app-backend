package vn.tea.app.core.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.CoreAttachmentTemp;

@Repository
public interface CoreAttachmentTempRepo extends JpaRepository<CoreAttachmentTemp, Long> {
	
}
