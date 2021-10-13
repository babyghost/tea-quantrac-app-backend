package vn.tea.app.utils.dto;

import lombok.Data;

@Data
public class PaginationDto {
	private Integer page;
	private Integer size;
	private Integer total;
	public PaginationDto(Integer page, Integer size, Integer total) {
		super();
		this.page = page;
		this.size = size;
		this.total = total;
	}
	
}
