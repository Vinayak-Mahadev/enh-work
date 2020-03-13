package enh.team.interfaces.rdbms.queryCreation;

interface  QueryTemplate {

	final String interface_attr_update_1set_1where          = "UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = ? WHERE ATTRIBUTE_ID_N = ;";

	final String interface_attr_insertQuery                 = "INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (FIELD1, FIELD2, ?, ?, now())";

	final String ORIGIN_VM_ORGANIZATION_PROFILE_ATTR_UPATE  = "UPDATE ORIGIN.VM_ORGANIZATION_PROFILE_ATTR SET VALUE_V = ? WHERE ATTRIBUTE_ID_N = ;";

	final String ORIGIN_VM_ORGANIZATION_PROFILE_ATTR_INSERT = "INSERT INTO ORIGIN.VM_ORGANIZATION_PROFILE_ATTR (ATTRIBUTE_ID_N, PROFILE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (FIELD1, FIELD2, ?, ?, now())";

}