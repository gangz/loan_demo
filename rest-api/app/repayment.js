exports.getMonthLimit = function getMonthLimitation(income,exist_debts,co_income,co_exist_debts){
	var total_income=Number(income)+Number(co_income);
	var total_debts = Number(exist_debts)+Number(co_exist_debts);
	var month_limitation= (Number(total_income)-Number(total_debts))/3;
	month_limitation = month_limitation>=0?month_limitation:0;
	return month_limitation;
}
