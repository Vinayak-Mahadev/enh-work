{ "retailers.org_id": 54748, "$or": [ { "$and": [ { "start_dt": { "$lte": ISODate("2020-03-11T18:30:00.000Z") } }, { "end_dt": { "$gte": ISODate("2020-03-11T18:30:00.000Z") } } ] }, { "$and": [ { "start_dt": { "$lte": ISODate("2020-03-31T18:29:59.999Z") } }, { "end_dt": { "$gte":ISODate("2020-03-31T18:29:59.999Z") } } ] }, { "$and": [ { "start_dt": { "$gte": ISODate("2020-03-11T18:30:00.000Z") } }, { "start_dt": { "$lte": ISODate("2020-03-31T18:29:59.999Z") } } ] }, { "$and": [ { "end_dt": { "$gte": ISODate("2020-03-11T18:30:00.000Z") } }, { "end_dt": { "$lte": ISODate("2020-03-31T18:29:59.999Z") } } ] } ], "status": { "$in": [ 168, 174 ] } }    

{
	"retailers.org_id": 54748,
	"$or": [
		{
			"$and": [
				{
					"start_dt": {
						"$lte": ISODate("2020-03-11T18:30:00.000Z")
					}
				},
				{
					"end_dt": {
						"$gte": ISODate("2020-03-11T18:30:00.000Z")
					}
				}
			]
		},
		{
			"$and": [
				{
					"start_dt": {
						"$lte": ISODate("2020-03-31T18:29:59.999Z")
					}
				},
				{
					"end_dt": {
						"$gte":	ISODate("2020-03-31T18:29:59.999Z")
					}
				}
			]
		},
		{
			"$and": [
				{
					"start_dt": {
						"$gte": ISODate("2020-03-11T18:30:00.000Z")
					}
				},
				{
					"start_dt": {
						"$lte": ISODate("2020-03-31T18:29:59.999Z")
					}
				}
			]
		},
		{
			"$and": [
				{
					"end_dt": {
						"$gte": ISODate("2020-03-11T18:30:00.000Z")
					}
				},
				{
					"end_dt": {
						"$lte": ISODate("2020-03-31T18:29:59.999Z")
					}
				}
			]
		}
	],
	"status": {
		"$in": [
			168,
			174
		]
	}
}



