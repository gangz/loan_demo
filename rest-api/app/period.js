exports.getPeriodLimit = function getPeriodLimit(age,gender,
				houseAge)
{
	var approveYears = 30; //MAX_LOAN_YEARS
	approveYears = maxLeftEarnableYears(age,gender)<approveYears?maxLeftEarnableYears(age,gender):approveYears;
	approveYears = maxLeftHouseValueableYears(houseAge)<approveYears?maxLeftHouseValueableYears(houseAge):approveYears;
	return approveYears;
}

function maxLeftEarnableYears(age,gender)
{
	var leftEarnableYears;
	if (gender=='male')
		leftEarnableYears = 65-age;
	else
		leftEarnableYears = 60-age;
	if (leftEarnableYears <0)
		leftEarnableYears = 0;
	return leftEarnableYears;
}

function maxLeftHouseValueableYears(houseAge){
	return 40-houseAge;
}

