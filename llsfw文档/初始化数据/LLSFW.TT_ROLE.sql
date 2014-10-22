Insert into TT_ROLE
   (ROLE_CODE, ROLE_NAME, CREATE_BY, CREATE_DATE)
 Values
   ('readonly', '只读', 'admin', TO_DATE('05/03/2014 13:50:24', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_ROLE
   (ROLE_CODE, ROLE_NAME, CREATE_BY, CREATE_DATE)
 Values
   ('admin', '系统管理员', 'SYSTEM', TO_DATE('12/14/2013 23:04:20', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;
