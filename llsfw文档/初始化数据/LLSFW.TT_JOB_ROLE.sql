Insert into TT_JOB_ROLE
   (ROLE_CODE, JOB_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('readonly', 'readonly', 'admin', TO_DATE('05/03/2014 13:51:30', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_JOB_ROLE
   (ROLE_CODE, JOB_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('admin', 'TEST_JOB', '-1', TO_DATE('04/25/2014 11:42:08', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;
