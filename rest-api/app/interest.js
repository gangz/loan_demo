exports.getPolicy = function getPolicy(suite_num){
	if (Number(suite_num)==1)
		return '1';
	return '1.1';
}
