/** 管理员 **/
INSERT INTO PM_ORGANIZE_UserAccount (obj_Id, obj_createDate, obj_updateDate, user_Mail, login_Pwd, user_Name, user_State, user_Tel, user_Admin) 
	VALUES ('user_admin', '2000-01-01', '2000-01-01', 'admin@admin.com', '6b71ada724dda18b14f752f14c0e777d', '管理员', 'Y', '18616975728', 1);

/** 客户CODE流水号 **/
INSERT INTO PM_FRAMEWORK_NumberLog (obj_Id, obj_createDate, obj_updateDate, number_Code, number_Format, number_Last, number_Length) 
	VALUES ('1', '2000-01-01', '2000-01-01', 'companycode', 'C{num}', 0, 5);

/** 附件流水号 **/
INSERT INTO PM_FRAMEWORK_NumberLog (obj_Id, obj_createDate, obj_updateDate, number_Code, number_Format, number_Last, number_Length) 
	VALUES ('2', '2000-01-01', '2000-01-01', 'uploadfilecode', 'F{date}{num}', 0, 3);
	
	
	/** 供应商CODE流水号 **/
INSERT INTO PM_FRAMEWORK_NumberLog (obj_Id, obj_createDate, obj_updateDate, number_Code, number_Format, number_Last, number_Length) 
	VALUES ('3', '2000-01-01', '2000-01-01', 'supcompanycode', 'S{num}', 0, 5);

	


