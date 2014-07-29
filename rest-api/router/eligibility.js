exports.checkCredit = function check_credit(level)
{
	if (level=='a' || level=='b' || level=='c')
		return true;
	return false;
}

exports.checkSuiteCount= function check_suite_count(suite_count)
{
	if (Number(suite_count)>2) 
		return false;
	return true;
}

exports.checkCoBorrower = function checkCoBorrower(relation,is_host)
{
	if (relation=='wife' ||
		relation=='husband')
		return true;
	if (is_host!='yes')
		return false;
	if (relation=='son'||
		relation=='daughter'||
		relation=='father'||
		relation=='monther'||
		relation=='parent')
		return true;
	return false;
		
}