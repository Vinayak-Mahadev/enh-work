Below changes done by interface side
1055|INT0013 - Question & Answers(Audit)

Data form queue
ActiveMQ Queue Name   |  ACTIVEMQ_AUDIT_QUEUE_NAME
Failure Queue Name    |  ACTIVEMQ_AUDIT_FAILURE_QUEUE_NAME


File header : 

SurveyId|SurveyDate|SurveyedEntityId|SurveyingUserId|SurveyingEntityId|NOOFRECORDSINSUBFILE|SurveyedUserId


SurveyId               = _id                                   ,required:true
SurveyDate             = crtd_dttm:timestamp                   ,required:true
SurveyedEntityId       = activity_for                          ,required:true
SurveyingUserId        = crtd_by                               ,required:true
SurveyingEntityId      = node_id                               ,required:false,skip-order-type:76
NOOFRECORDSINSUBFILE   = default-value:ORDER_ITEMS_RECORD_COUNT,required:true

----------------------------
New column : 

SurveyedUserId         = ? <<seller_user_id>>

----------------------------

"seller_user_id": "12672",
"seller_login_name" : "KSADMIN",
"seller_name" : "Nandhagopal Saravanakumar",
"seller_mobile" : "892378912371"


Example queue request:

{
	"activityId": 2467,
	"org_id": 125441,
	"img_ref_code": "20249711797324672362020135252",
	"activity_for": 83269,
	"form_name": "Дневной Отчет Индексы V8",
	"is_form": true,
	"crtd_by": 163145,
	"feature_code": "activity12467",
	"crtd_dttm": {
		"$date": "2020-07-23T10:53:19.535Z"
	},
	"node_id": 202497,
	"res_msg": null,
	"_id": 5199933,
	"act_id": 2467,
	"beat_id": 192485,
	"schedule_dt": {
		"$date": "2020-07-23T00:00:00.000Z"
	},
	"crtd_dt": {
		"$date": "2020-07-23T18:00:35.385Z"
	},
	"updtd_by": null,
	"updtd_dt": null,
	"file_type": "pdf",
	"report_id": "getBeatActivityFormData",
	"audit": [
		{
			"question": "Кількість СП Київстар Відео без меж (без поповнення)",
			"answer": "1"
		},
		{
			"question": "Стандарт викладки СП",
			"answer": "паритет"
		},
		{
			"question": "Кількість СП Київстар Відео без меж (АП)",
			"answer": "0"
		}
	]
}