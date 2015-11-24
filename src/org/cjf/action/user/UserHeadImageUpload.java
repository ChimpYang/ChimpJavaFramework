package org.cjf.action.user;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.biz.UserBiz;
import org.cjf.entity.User;
import org.cjf.util.FileUtil;
import org.cjf.utils.properties.AppConfig;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class UserHeadImageUpload extends AbstractWebAction<User> {
	private static final long serialVersionUID = 7570059378399188192L;
	private static final Logger logger = Logger.getLogger(UserHeadImageUpload.class);
	
	private String fileName;
	private File photo;
	
	public UserHeadImageUpload() {
		actionType = ActionType.Modify;
	}

	private UserBiz biz;
	private String userCode;
	@Override
	public void setBiz(BusinessLogic<User> biz) {
		this.biz = (UserBiz)biz;
	}

	@Override
	protected boolean process(Object obj) {
		
		String extName = FileUtil.getExtensionName(fileName);
//		logger.info(fileName);
//		logger.info(photo.getAbsoluteFile());
//		logger.info(photo.getAbsolutePath());
//		logger.info(photo.getName());
//		logger.info(photo.getPath());

		//DONE; 这里的头像路径改成从配置文件读取
		String basePath = AppConfig.getUserHeadImagePath();
		logger.info(basePath);
		String uuid = UUID.randomUUID().toString();
		fileName = String.format("%s.%s", uuid, extName);
		File destFile = new File(basePath, fileName);
		logger.info(destFile.toString());
		try {
			//01; get old head image
			User t = new User();
			t.setUserCode(userCode);
			t = biz.getOneCustom(t, "singleByCode");
			if(null == t) {
				message = "get user old head image error.";
				logger.info(message);

				jsonObject.put("success", false);
				jsonObject.put("obj", getEmptyObjString());
				jsonObject.put("message", message); 
				jsonObject.put("errcode", AppConst.ACTION_ERROR_DB_DATA_NOT_EXIST);
				jsonValue = jsonObject.toString();
				
				return false;
			}
			String oldHeadImage = basePath + "/" + t.getUserImage();
			//02; save new head image
			FileUtils.copyFile(photo, destFile);
			
			//03; save new head image in user info
			biz.modifyUserHead(userCode, fileName);
			
			//04; delete old head image
			FileUtil.deleteFile(oldHeadImage);
		} catch (IOException e) {
			logger.error(e.getMessage());
			
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", e.getMessage()); 
			jsonObject.put("errcode", AppConst.ACTION_ERROR_FILESYSTEM);
			jsonValue = jsonObject.toString();
			
			return false;
		}
		
		jsonObject.put("success", true);
		jsonObject.put("obj", String.format("{url:'%s'}", fileName));
		jsonObject.put("message", message); 
		jsonObject.put("errcode", errorCode);
		
		jsonValue = jsonObject.toString();
		
		return true;
	}

	@Override
	protected Object getParamObject() {
		return null;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == photo) {
			message = "null file.";
			return false;
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(AppConst.SESSION_USER);
		if(null == user) {
			message = "session error.";
			return false;
		}
		userCode = user.getUserCode();
		if(null == userCode || "".equals(userCode)) {
			message = "session data error.";
			return false;
		}
		
		if(null == fileName || "".equals(fileName)) {
			message = "null or empty fileName.";
			return false;
		}
		
		return true;
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected boolean getPermission() {
		//TODO; 得到真实的权限
		return true;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
