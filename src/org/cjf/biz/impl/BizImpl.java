package org.cjf.biz.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.cjf.biz.BusinessLogic;
import org.cjf.dao.Dao;
import org.cjf.entity.Entity;
import org.cjf.utils.properties.AppConst.ImportExportFileType;
import org.cjf.utils.properties.AppConst.ImportExportType;

public class BizImpl<T extends Entity> implements BusinessLogic<T> {
	private static Logger logger = Logger.getLogger(BizImpl.class);
	
	private Dao<T> dao;
	
	@Override
	public void setDao(Dao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public Dao<T> getDao() {
		return dao;
	}

	@Override
	public int getCount(T t) {
		int count = dao.count(t);
		return count;
	}
	
	@Override
	public int getCountModify(T t) {
		int count = dao.countModifyCheck(t);
		return count;
	}
	
	@Override
	public int getCountBiz(T t) {
		int count = dao.countBiz(t);
		return count;
	}

	@Override
	public T getOne(T t) {
		T item = dao.single(t);
		return item;
	}
	
	@Override
	public T getOneCustom(T t, String statementId) {
		T item = dao.singleCustom(t, statementId);
		return item;
	}

	@Override
	public List<T> getList(T t, int start, int limit) {
		List<T> list = dao.list(t, start, limit);
		return list;
	}

	@Override
	public int create(T t) {
		int count = dao.add(t);
		return count;
	}

	@Override
	public int remove(T t) {
		int count = dao.delete(t);
		return count;
	}

	@Override
	public int modify(T t) {
		int count = dao.update(t);
		return count;
	}
	
	@Override
	public int modifyCustom(T t, String statementId) {
		int count = dao.updateCustom(t, statementId);
		return count;
	}

	@Override
	public void importFile(T t, ImportExportType type, File file, List<String> exportFields) {
		//TODO; 编写通用的导入类
		logger.warn("no implement");
	}

	@Override
	public ByteArrayInputStream exportFile(T t, ImportExportFileType type) {
		//TODO; 编写通用的导出类
		logger.warn("no implement");
		
		return null;
	}

	@Override
	public int clear(T t) {
		int count = dao.clear(t);
		return count;
	}

	@Override
	public boolean batchCreate(List<T> list, T t) {
		boolean flag = true;
		
		try {
			System.out.println("prepare batch create");
			logger.info("+++");
			dao.addBatch(list, t);
		} catch (Exception e) {
			//TODO; 注意这里是否被Spring容器的事务进行处理了
			//这里是批量处理，又可以某个记录出错
			System.out.println("*****************");
			logger.error(e.getMessage());
			flag = false;
		}
		
		return flag;
	}

	@Override
	public boolean batchModify(List<T> list, T t) {
		boolean flag = true;
		
		try {
			dao.updateBatch(list, t);
		} catch (Exception e) {
			//TODO; 注意这里是否被Spring容器的事务进行处理了
			//这里是批量处理，又可以某个记录出错
			logger.error(e.getMessage());
			flag = false;
		}
		
		return flag;
	}

	@Override
	public boolean batchRemove(List<T> list, T t) {
		boolean flag = true;
		
		try {
			dao.deleteBatch(list, t);
		} catch (Exception e) {
			//TODO; 注意这里是否被Spring容器的事务进行处理了
			//这里是批量处理，又可以某个记录出错
			logger.error(e.getMessage());
			flag = false;
		}
		
		return flag;
	}

	@Override
	public boolean batchCreateCustom(List<T> list, T t, String statementId) {
		boolean flag = true;
		
		try {
			dao.addBatchCustom(list, t, statementId);
		} catch (Exception e) {
			//TODO; 注意这里是否被Spring容器的事务进行处理了
			//这里是批量处理，又可以某个记录出错
			logger.error(e.getMessage());
			flag = false;
		}
		
		return flag;
	}

	@Override
	public boolean batchModifyCustom(List<T> list, T t, String statementId) {
		boolean flag = true;
		
		try {
			dao.updateBatchCustom(list, t, statementId);
		} catch (Exception e) {
			//TODO; 注意这里是否被Spring容器的事务进行处理了
			//这里是批量处理，又可以某个记录出错
			logger.error(e.getMessage());
			flag = false;
		}
		
		return flag;
	}

	@Override
	public boolean batchRemoveCustom(List<T> list, T t, String statementId) {
		boolean flag = true;
		
		try {
			dao.deleteBatchCustom(list, t, statementId);
		} catch (Exception e) {
			//TODO; 注意这里是否被Spring容器的事务进行处理了
			//这里是批量处理，又可能某个记录出错
			logger.error(e.getMessage());
			flag = false;
		}
		
		return flag;
	}

	@Override
	public List<T> getListCustom(T t, String statementId, int start, int limit) {
		List<T> list = dao.listCustom(t, statementId, start, limit);
		return list;
	}

	@Override
	public int getCountCustom(T t, String statementId) {
		int count = dao.countCustom(t, statementId);
		return count;
	}

}
