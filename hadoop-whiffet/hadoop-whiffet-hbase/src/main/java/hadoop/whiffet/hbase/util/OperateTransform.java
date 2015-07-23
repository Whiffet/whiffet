package hadoop.whiffet.hbase.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;

public class OperateTransform {

	
	public static CompareOp transfrom(String operate){
		
	if(StringUtils.isNotBlank(operate)){
		switch (operate) {
		case "=":		
			return CompareOp.EQUAL;
		case "<":
			return CompareOp.LESS;
		case "<=":
			return CompareOp.LESS_OR_EQUAL;
		case ">":
			return CompareOp.GREATER;
		case ">=":
			return CompareOp.GREATER_OR_EQUAL;
		case "!=":
			return CompareOp.NOT_EQUAL;
		default:
			return CompareOp.NO_OP;
		}	
	}
	return CompareOp.NO_OP;
	}
}
