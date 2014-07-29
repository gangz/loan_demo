exports.getPolicy = function getPolicy(suite_num){
	if (Number(suite_num)==1)
		return '1 times';
	return '1.1 times';
}