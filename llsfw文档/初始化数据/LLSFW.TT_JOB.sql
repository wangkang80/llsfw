﻿Insert into TT_JOB
   (JOB_CODE, JOB_NAME, ORG_CODE, CREATE_BY, CREATE_DATE, 
    UPDATE_BY, UPDATE_DATE)
 Values
   ('TEST_JOB', '测试岗位', 'ORG_ADMIN', '-1', TO_DATE('04/25/2014 11:41:06', 'MM/DD/YYYY HH24:MI:SS'), 
    'admin', TO_DATE('05/03/2014 13:54:12', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_JOB
   (JOB_CODE, JOB_NAME, ORG_CODE, CREATE_BY, CREATE_DATE, 
    UPDATE_BY, UPDATE_DATE)
 Values
   ('readonly', '只读岗位', 'ORG_ADMIN', 'admin', TO_DATE('05/03/2014 13:51:26', 'MM/DD/YYYY HH24:MI:SS'), 
    'admin', TO_DATE('05/03/2014 13:54:20', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;
