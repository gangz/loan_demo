exports.getMonthLimit = function getMonthLimitation(income,exist_debts,co_income,co_exist_debts){
	var total_income=Number(income)+Number(co_income);
	var total_debts = Number(exist_debts)+Number(co_exist_debts);
	return Number(total_income)/3-Number(total_debts);
}