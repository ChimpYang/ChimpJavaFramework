package org.cjf.utils.mapper;

public class FormatFullMapperImpl implements FormatFullMapper {

	private String packagePath;
	
	@Override
	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	@Override
	public String getFullStatementId(String statementId, String sampleClassName) {
		return String.format("%s.%sMapper.%s", packagePath, sampleClassName, statementId);
	}

}
