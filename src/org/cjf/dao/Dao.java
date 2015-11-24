package org.cjf.dao;

import java.util.List;

import org.cjf.entity.Entity;
import org.mybatis.spring.SqlSessionTemplate;

public interface Dao<T extends Entity> {
	public static final String STATEMENT_COUNT = "count";
	public static final String STATEMENT_COUNTBIZ = "countBiz";
	public static final String STATEMENT_COUNTMODIFY = "countModifyCheck";
	public static final String STATEMENT_COUNTRELATION = "CR_";
	public static final String STATEMENT_SINGLE = "single";
	public static final String STATEMENT_LIST = "list";
	public static final String STATEMENT_ADD = "add";
	public static final String STATEMENT_DELETE = "delete";
	public static final String STATEMENT_UPDATE = "update";
	public static final String STATEMENT_CLEAR = "clear";
	
	int count(T t);
	int countBiz(T t);
	int countModifyCheck(T t);
	int countRelation(T t, String childClassName);
	T single(T t);
	List<T> list(T t, int start, int limit);
	int add(T t);
	int delete(T t);
	int update(T t);
	int saveAs(T t);
	/**
	 * 测试用，为了初始化
	 * @return
	 */
	int clear(T t);
	
	int countCustom(T t, String statementId);
	T singleCustom(T t, String statementId);
	List<T> listCustom(T t, String statementId, int start, int limit);
	int addCustom(T t, String statementId);
	int deleteCustom(T t, String statementId);
	int updateCustom(T t, String statmentId);
	
	List<T> listCustomParam(T t, String statementId, Object param, int start, int limit);
	
	void addBatch(List<T> list, T t) throws Exception;
	void deleteBatch(List<T> list, T t) throws Exception;
	void updateBatch(List<T> list, T t) throws Exception;
	
	void addBatchCustom(List<T> list, T t, String statementId) throws Exception;
	void deleteBatchCustom(List<T> list, T t, String statementId) throws Exception;
	void updateBatchCustom(List<T> list, T t, String statementId) throws Exception;
	
	void setBatchSession(SqlSessionTemplate session);
}
