package vn.tea.app.core.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import vn.tea.app.core.entity.FileList;

@Data
public class FileDinhKem {

	private List<Long> ids = new ArrayList<>();

	private List<FileList> fileLists = new ArrayList<>();
}
