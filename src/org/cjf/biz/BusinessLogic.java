package org.cjf.biz;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import org.cjf.dao.Dao;
import org.cjf.entity.Entity;
import org.cjf.utils.properties.AppConst.ImportExportFileType;
import org.cjf.utils.properties.AppConst.ImportExportType;

public interface BusinessLogic<T extends Entity> {
	void setDao(Dao<T> dao);
	Dao<T> getDao();
	
	/**
	 * 测试用，为了初始化
	 * @return
	 */
	int clear(T t);
	
	int getCount(T t);
	int getCountModify(T t);
	int getCountBiz(T t);
	int getCountCustom(T t, String statementId);
	T getOne(T t);
	T getOneCustom(T t, String statementId);
	List<T> getList(T t, int start, int limit);
	List<T> getListCustom(T t, String statementId, int start, int limit);
	
	int create(T t);
	int remove(T t);
	int modify(T t);
	int modifyCustom(T t, String statementId);
	
	boolean batchCreate(List<T> list, T t);
	boolean batchModify(List<T> list, T t);
	boolean batchRemove(List<T> list, T t);
	boolean batchCreateCustom(List<T> list, T t, String statementId);
	boolean batchModifyCustom(List<T> list, T t, String statementId);
	boolean batchRemoveCustom(List<T> list, T t, String statementId);
	
	void importFile(T t, ImportExportType type, File file, List<String> exportFields);
	ByteArrayInputStream exportFile(T t, ImportExportFileType type);
}
