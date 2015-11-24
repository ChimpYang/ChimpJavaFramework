package org.cjf.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.cjf.dao.Dao;
import org.cjf.entity.Entity;
import org.cjf.utils.mapper.FormatFullMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class DaoImpl<T extends Entity> extends SqlSessionDaoSupport implements Dao<T> {
	
	private static FormatFullMapper formatFullMapper = null; 
	
	//使用spring注入
	public static void setFormatFullMapper(FormatFullMapper bean) {
		DaoImpl.formatFullMapper = bean;
	}
	
	private String getFullStatumentId(String statementId, T t) {
		String sampleClassName = t.getClass().getSimpleName();
		
		return formatFullMapper.getFullStatementId(statementId, sampleClassName);
	}

	private int countInner(T t, String statementId) {
		return getSqlSession().selectOne(getFullStatumentId(statementId, t), t);
	}
	
	private T singleInner(T t, String statementId) {
		return getSqlSession().selectOne(getFullStatumentId(statementId, t), t);
	}
	
	private List<T> listInner(T t, int start, int limit, String statementId) {
		List<T> list = getSqlSession().selectList(getFullStatumentId(statementId, t), t, new RowBounds(start, limit));
		return list;
	}
	
	private int updateInner(T t, String statementId) {
		int count = getSqlSession().update(getFullStatumentId(statementId, t), t);
		return count;
	}
	
	private int addInner(T t, String statementId) {
		int count = getSqlSession().insert(getFullStatumentId(statementId, t), t);
		
		return count;
	}
	
	private int deleteInner(T t, String statementId) {
		int count = getSqlSession().delete(getFullStatumentId(statementId, t), t);
		
		return count;
	}
	
	@Override
	public int count(T t) {
		return countInner(t, STATEMENT_COUNT);
	}

	@Override
	public int countBiz(T t) {
		return countInner(t, STATEMENT_COUNTBIZ);
	}

	@Override
	public int countModifyCheck(T t) {
		return countInner(t, STATEMENT_COUNTMODIFY);
	}

	@Override
	public int countRelation(T t, String childClassName) {
		String statement = STATEMENT_COUNTRELATION + childClassName;
		String fullStatementId = getFullStatumentId(statement, t);
		return getSqlSession().selectOne(fullStatementId, t);
	}
	
	@Override
	public int countCustom(T t, String statementId) {
		return countInner(t, statementId);
	}

	@Override
	public T single(T t) {
		return singleInner(t, STATEMENT_SINGLE);
	}
	
	@Override
	public T singleCustom(T t, String statementId) {
		return singleInner(t, statementId);
	}

	@Override
	public List<T> list(T t, int start, int limit) {
		return listInner(t, start, limit, STATEMENT_LIST);
	}
	
	@Override
	public List<T> listCustom(T t, String statementId, int start, int limit) {
		return listInner(t, start, limit, statementId);
	}
	
	@Override
	public List<T> listCustomParam(T t, String statementId, Object param, int start, int limit) {
		List<T> list = getSqlSession().selectList(getFullStatumentId(statementId, t), param, new RowBounds(start, limit));
		return list;
	}

	@Override
	public int add(T t) {
		return addInner(t, STATEMENT_ADD);
	}
	
	@Override
	public int addCustom(T t, String statementId) {
		return addInner(t, statementId);
	}
	

	@Override
	public int saveAs(T t) {
		t.setId(0);
		return addInner(t, STATEMENT_ADD);
	}

	@Override
	public int delete(T t) {
		return deleteInner(t, STATEMENT_DELETE);
	}
	
	@Override
	public int deleteCustom(T t, String statementId) {
		return deleteInner(t, statementId);
	}

	@Override
	public int update(T t) {
		return updateInner(t, STATEMENT_UPDATE);
	}

	@Override
	public int updateCustom(T t, String statmentId) {
		return updateInner(t, statmentId);
	}

	@Override
	public int clear(T t) {
		return getSqlSession().delete(getFullStatumentId(STATEMENT_CLEAR, t));
	}

	private SqlSessionTemplate batchSession = null;
	@Override
	public void setBatchSession(SqlSessionTemplate session) {
		this.batchSession = session;
	}
	
	private void addBatchInner(List<T> list, T t, String statementId) throws Exception {
		if(null == batchSession) {
			throw new Exception("BatchSession is null.");
		}
		
		String fullStatementId = getFullStatumentId(statementId, t);
		for(T item : list) {
			batchSession.insert(fullStatementId, item);
		}
	}
	
	@Override
	public void addBatch(List<T> list, T t) throws Exception {
		addBatchInner(list, t, STATEMENT_ADD);
	}
	
	@Override
	public void addBatchCustom(List<T> list, T t, String statementId) throws Exception {
		addBatchInner(list, t, statementId);
	}
	
	private void deleteBatchInner(List<T> list, T t, String statementId) throws Exception {
		if(null == batchSession) {
			throw new Exception("BatchSession is null.");
		}
		
		String fullStatementId = getFullStatumentId(statementId, t);
		for(T item : list) {
			batchSession.delete(fullStatementId, item);
		}
	}

	@Override
	public void deleteBatch(List<T> list, T t) throws Exception {
		deleteBatchInner(list, t, STATEMENT_DELETE);
	}
	
	@Override
	public void deleteBatchCustom(List<T> list, T t, String statementId) throws Exception {
		deleteBatchInner(list, t, statementId);
	}
	
	private void updateBatchInner(List<T> list, T t, String statementId) throws Exception {
		if(null == batchSession) {
			throw new Exception("BatchSession is null.");
		}
		
		String fullStatementId = getFullStatumentId(statementId, t);
		for(T item : list) {
			batchSession.update(fullStatementId, item);
		}
	}

	@Override
	public void updateBatch(List<T> list, T t) throws Exception {
		updateBatchInner(list, t, STATEMENT_UPDATE);
	}
	
	@Override
	public void updateBatchCustom(List<T> list, T t, String statementId) throws Exception {
		updateBatchInner(list, t, statementId);
	}

}
