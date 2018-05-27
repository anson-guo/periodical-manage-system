// 出版周期
let publicationCycle = {
    "年刊（N）": "年刊",
    "月刊（Y）": "月刊",
    "周刊（Z）": "周刊"
}

// 获取今年的年份
let current_date = new Date();
let current_year = current_date.getFullYear();
let year_sign =  "00";

let year_obj = {};
year_obj[year_sign] = current_year; 



// 月
let month_obj = {};
for(let i = 0; i < 12; i ++){
	let temp = i + 1;
	if(temp.toString().length == 1) {
		temp = "0" + temp;
	}
	let show_temp = temp + "月";
	month_obj[temp] = show_temp;
}

// 周
let week_obj = {};
let week_index = 0;
for(let i = 12; i < 64; i ++){
	week_index ++;
	let temp = i + 1;
	let show_temp =  "第" + week_index + "周";
	week_obj[temp] = show_temp;
}

// 出版期号
let period = {
		"年刊（N）": year_obj,
		"月刊（Y）": month_obj,
		"周刊（Z）": week_obj
}

