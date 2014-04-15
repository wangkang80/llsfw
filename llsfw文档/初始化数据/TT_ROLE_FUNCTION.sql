SET DEFINE OFF;
Insert into TT_ROLE_FUNCTION
   (FUNCTION_CODE, ROLE_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('TEST_FUNCTION', 'admin', 'admin', TO_DATE('12/16/2013 16:54:15', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (FUNCTION_CODE, ROLE_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('quartzController', 'admin', 'admin', TO_DATE('12/20/2013 23:26:30', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (FUNCTION_CODE, ROLE_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('functionController', 'admin', 'SYSTEM', TO_DATE('12/14/2013 23:04:24', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (FUNCTION_CODE, ROLE_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('roleController', 'admin', 'SYSTEM', TO_DATE('12/14/2013 23:05:20', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (FUNCTION_CODE, ROLE_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('userController', 'admin', 'SYSTEM', TO_DATE('12/15/2013 15:40:26', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE_FUNCTION
   (FUNCTION_CODE, ROLE_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('serviceParamController', 'admin', 'SYSTEM', TO_DATE('12/14/2013 23:04:24', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;
