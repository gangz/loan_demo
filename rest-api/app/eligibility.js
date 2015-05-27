exports.checkCredit = function check_credit(level)
{
	if (level=='a' || level=='b' || level=='c')
		return '符合';
        if (level=='NA')
                return '信用记录不存在';
	return '不符合';
}

exports.checkSuiteCount= function check_suite_count(suite_count)
{
	if (Number(suite_count)>2) 
		return '不符合';
	return '符合';
}

exports.checkCoBorrower = function checkCoBorrower(relation,is_host)
{
	if (relation=='夫妻')
		return '符合';

	if (is_host!='是')
		return '不符合';

	if (relation=='子女'||
		relation=='父母')
		return '符合';
	return '不符合';
		
}
