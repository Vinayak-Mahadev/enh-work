

BEAT_DEFN




{
	"retailers.org_id": 50365,
	"status": {
		"$in": [
			168,
			174
		]
	},
	"$or": [
		{
			"start_dt": {
				"$gte": {
					"$date": "2020-03-11T18:30:00.000Z"
				},
				"$lte": {
					"$date": "2020-03-31T18:29:59.999Z"
				}
			}
		},
		{
			"end_dt": {
				"$gte": {
					"$date": "2020-03-11T18:30:00.000Z"
				},
				"$lte": {
					"$date": "2020-03-31T18:29:59.999Z"
				}
			}
		}
	]
}
------------------------------------------------------------------------------------------
{
	"retailers.org_id": 50365,
	"status": {
		"$in": [
			168,
			174
		]
	},
	"$or": [
		{
			"start_dt": {
				"$gte": ISODate("2020-03-11T18:30:00.000Z"),
				"$lte": ISODate("2020-03-31T18:29:59.999Z")
			}
		},
		{
			"end_dt": {
				"$gte": ISODate("2020-03-11T18:30:00.000Z"),
				"$lte": ISODate("2020-03-31T18:29:59.999Z")
			}
		}
	]
}

{ "retailers.org_id": 50365, "status": { "$in": [ 168, 174 ] }, "$or": [ { "start_dt": { "$gte": ISODate("2020-03-11T18:30:00.000Z"), "$lte": ISODate("2020-03-31T18:29:59.999Z") } }, { "end_dt": { "$gte": ISODate("2020-03-11T18:30:00.000Z"), "$lte": ISODate("2020-03-31T18:29:59.999Z") } } ] }

--------------------------------------------------------------------------------------------------
BEAT_SCHEDULE

{
	"org_id": 52666,
	"schedule_dt_num": {
		"$gte": 20200301,
		"$lte": 20200311
	},
	"status": {
		"$ne": 171
	}
}

in side of BEAT_SCHEDULE => BEAT_DEFN 

{
	"_id": {
		"$in": [
			4088,
			4057
		]
	}
}

------------------------------------------------------------------

