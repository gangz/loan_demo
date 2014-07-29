exports.checkCredit = function checkCredit(level)
{
	if (level=='a' || level=='b' || level=='c')
		return true;
	return false;
}

exports.getCount = function() { return count; };