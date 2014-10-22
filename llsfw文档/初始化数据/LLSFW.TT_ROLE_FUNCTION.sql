Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('readonly', 'serviceParamController', 'view', 'admin', TO_DATE('05/03/2014 14:39:59', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('readonly', 'quartzController', 'view', 'admin', TO_DATE('05/03/2014 14:40:03', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('admin', 'userController', '*', 'admin', TO_DATE('05/03/2014 12:10:58', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('readonly', 'orgController', 'view', 'admin', TO_DATE('05/03/2014 13:50:37', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('readonly', 'userController', 'view', 'admin', TO_DATE('05/03/2014 13:50:35', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('readonly', 'roleController', 'view', 'admin', TO_DATE('05/03/2014 13:50:40', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('readonly', 'functionController', 'view', 'admin', TO_DATE('05/03/2014 13:50:43', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('admin', 'serviceParamController', '*', 'admin', TO_DATE('05/03/2014 13:58:01', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('admin', 'quartzController', '*', 'admin', TO_DATE('05/03/2014 13:58:03', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('admin', 'orgController', '*', 'admin', TO_DATE('05/02/2014 16:49:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('admin', 'functionController', '*', 'SYSTEM', TO_DATE('04/25/2014 16:41:24', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (ROLE_CODE, FUNCTION_CODE, PURVIEW_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('admin', 'roleController', '*', 'SYSTEM', TO_DATE('04/25/2014 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;
