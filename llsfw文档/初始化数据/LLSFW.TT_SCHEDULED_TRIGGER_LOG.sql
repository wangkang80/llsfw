Insert into TT_SCHEDULED_TRIGGER_LOG
   (LOGID, SCHEDULED_FIRE_TIME, FIRE_TIME, END_TIME, JOB_RUN_TIME, 
    STATUS, RESULT, TRIGGER_NAME, TRIGGER_GROUP, JOB_NAME, 
    JOB_GROUP, JOB_CLASS, THREAD_GROUP_NAME, THREAD_ID, THREAD_NAME, 
    THREAD_PRIORITY, SCHEDULED_ID, SCHEDULED_NAME, CREATE_DATE)
 Values
   ('wangkang-PC14139595182341413959518210', TO_DATE('10/22/2014 14:32:06', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('10/22/2014 14:32:07', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('10/22/2014 14:32:08', 'MM/DD/YYYY HH24:MI:SS'), 179, 
    'complete', 'NOOP', 'clearAppLogTrigger', 'SystemAutoRun', 'clearAppLogJobDetail', 
    'SystemAutoRun', 'com.llsfw.core.scheduler.ClearAppLog', 'main', '15', 'clusterQuartzScheduler_Worker-3', 
    '5', 'wangkang-PC1413959518234', 'clusterQuartzScheduler', TO_DATE('10/22/2014 14:32:08', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_SCHEDULED_TRIGGER_LOG
   (LOGID, STATUS, TRIGGER_NAME, TRIGGER_GROUP, JOB_NAME, 
    JOB_GROUP, THREAD_GROUP_NAME, THREAD_ID, THREAD_NAME, THREAD_PRIORITY, 
    CREATE_DATE)
 Values
   ('e73e84d7-906f-4354-ade6-c7d03da3f764', 'misfired', 'clearScheduledLogTrigger', 'SystemAutoRun', 'clearScheduledLogJobDetail', 
    'SystemAutoRun', 'main', '51', 'QuartzScheduler_clusterQuartzScheduler-wangkang-PC1413959518234_MisfireHandler', '5', 
    TO_DATE('10/22/2014 14:32:06', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_SCHEDULED_TRIGGER_LOG
   (LOGID, STATUS, TRIGGER_NAME, TRIGGER_GROUP, JOB_NAME, 
    JOB_GROUP, THREAD_GROUP_NAME, THREAD_ID, THREAD_NAME, THREAD_PRIORITY, 
    CREATE_DATE)
 Values
   ('1b2f4c26-1e65-4a5b-a7fd-828c9bfd3d44', 'misfired', 'clearScheduledTriggerLogTrigger', 'SystemAutoRun', 'clearScheduledTriggerLogJobDetail', 
    'SystemAutoRun', 'main', '51', 'QuartzScheduler_clusterQuartzScheduler-wangkang-PC1413959518234_MisfireHandler', '5', 
    TO_DATE('10/22/2014 14:32:06', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_SCHEDULED_TRIGGER_LOG
   (LOGID, STATUS, TRIGGER_NAME, TRIGGER_GROUP, JOB_NAME, 
    JOB_GROUP, THREAD_GROUP_NAME, THREAD_ID, THREAD_NAME, THREAD_PRIORITY, 
    CREATE_DATE)
 Values
   ('e9c46ff3-bf52-4f99-ad8f-e5733a75fc4d', 'misfired', 'clearAppLogTrigger', 'SystemAutoRun', 'clearAppLogJobDetail', 
    'SystemAutoRun', 'main', '51', 'QuartzScheduler_clusterQuartzScheduler-wangkang-PC1413959518234_MisfireHandler', '5', 
    TO_DATE('10/22/2014 14:32:06', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_SCHEDULED_TRIGGER_LOG
   (LOGID, SCHEDULED_FIRE_TIME, FIRE_TIME, END_TIME, JOB_RUN_TIME, 
    STATUS, RESULT, TRIGGER_NAME, TRIGGER_GROUP, JOB_NAME, 
    JOB_GROUP, JOB_CLASS, THREAD_GROUP_NAME, THREAD_ID, THREAD_NAME, 
    THREAD_PRIORITY, SCHEDULED_ID, SCHEDULED_NAME, CREATE_DATE)
 Values
   ('wangkang-PC14139595182341413959518209', TO_DATE('10/22/2014 14:32:06', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('10/22/2014 14:32:07', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('10/22/2014 14:32:08', 'MM/DD/YYYY HH24:MI:SS'), 323, 
    'complete', 'NOOP', 'clearScheduledTriggerLogTrigger', 'SystemAutoRun', 'clearScheduledTriggerLogJobDetail', 
    'SystemAutoRun', 'com.llsfw.core.scheduler.ClearScheduledTriggerLog', 'main', '14', 'clusterQuartzScheduler_Worker-2', 
    '5', 'wangkang-PC1413959518234', 'clusterQuartzScheduler', TO_DATE('10/22/2014 14:32:08', 'MM/DD/YYYY HH24:MI:SS'));
Insert into TT_SCHEDULED_TRIGGER_LOG
   (LOGID, SCHEDULED_FIRE_TIME, FIRE_TIME, END_TIME, JOB_RUN_TIME, 
    STATUS, RESULT, TRIGGER_NAME, TRIGGER_GROUP, JOB_NAME, 
    JOB_GROUP, JOB_CLASS, THREAD_GROUP_NAME, THREAD_ID, THREAD_NAME, 
    THREAD_PRIORITY, SCHEDULED_ID, SCHEDULED_NAME, CREATE_DATE)
 Values
   ('wangkang-PC14139595182341413959518208', TO_DATE('10/22/2014 14:32:06', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('10/22/2014 14:32:07', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('10/22/2014 14:32:08', 'MM/DD/YYYY HH24:MI:SS'), 327, 
    'complete', 'NOOP', 'clearScheduledLogTrigger', 'SystemAutoRun', 'clearScheduledLogJobDetail', 
    'SystemAutoRun', 'com.llsfw.core.scheduler.ClearScheduledLog', 'main', '13', 'clusterQuartzScheduler_Worker-1', 
    '5', 'wangkang-PC1413959518234', 'clusterQuartzScheduler', TO_DATE('10/22/2014 14:32:08', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;
