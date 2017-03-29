var selectDateDom = $('#selectDate');
var showDateDom = $('#showDate');
// 初始化时间
var now = new Date();
var nowYear = now.getFullYear();
var nowMonth = now.getMonth() + 1;
var nowDate = now.getDate();
showDateDom.attr('data-year', nowYear);
showDateDom.attr('data-month', nowMonth);
showDateDom.attr('data-date', nowDate);
// 数据初始化
function formatYear (nowYear) {
    var arr = [];
    for (var i = nowYear; i <= nowYear + 1; i++) {
        arr.push({
            id: i + '',
            value: i + '年'
        });
    }
    return arr;
}
function formatMonth () {
    var arr = [];
    for (var i = nowMonth; i <= nowMonth+1; i++) {
        arr.push({
            id: i + '',
            value: i + '月'
        });
    }
    return arr;
}
function formatDate (count) {
    var arr = [];
    for (var i = 1; i <= count; i++) {
        arr.push({
            id: i + '',
            value: i + '日'
        });
    }
    return arr;
}
var yearData = function(callback) {
    callback(formatYear(nowYear))
}
var monthData = function (year, callback) {
    callback(formatMonth());
};
var dateData = function (year, month, callback) {
    if (/^(1|3|5|7|8|10|12)$/.test(month)) {
        callback(formatDate(31));
    }
    else if (/^(4|6|9|11)$/.test(month)) {
        callback(formatDate(30));
    }
    else if (/^2$/.test(month)) {
        if (year % 4 === 0 && year % 100 !==0 || year % 400 === 0) {
            callback(formatDate(29));
        }
        else {
            callback(formatDate(28));
        }
    }
    else {
        throw new Error('month is illegal');
    }
};
var hourData = function(one, two, three, callback) {
    var hours = [];
    for (var i = 0,len = 24; i < len; i++) {
        hours.push({
            id: i,
            value: i + '时'
        });
    }
    callback(hours);
};
var minuteData = function(one, two, three, four, callback) {
    var minutes = [];
    for (var i = 0, len = 60; i < len; i++) {
        minutes.push({
            id: i,
            value: i + '分'
        });
    }
    callback(minutes);
};
selectDateDom.bind('click', function () {
    var oneLevelId = showDateDom.attr('data-year');
    var twoLevelId = showDateDom.attr('data-month');
    var threeLevelId = showDateDom.attr('data-date');
    var fourLevelId = showDateDom.attr('data-hour');
    var fiveLevelId = showDateDom.attr('data-minute');
    var iosSelect = new IosSelect(5,
        [yearData, monthData, dateData, hourData, minuteData],
        {
            title: '预约停车时间',
            itemHeight: 35,
            relation: [1, 1, 0, 0],
            itemShowCount: 9,
            oneLevelId: oneLevelId,
            twoLevelId: twoLevelId,
            threeLevelId: threeLevelId,
            fourLevelId: fourLevelId,
            fiveLevelId: fiveLevelId,
            callback: function (selectOneObj, selectTwoObj, selectThreeObj, selectFourObj, selectFiveObj) {
                showDateDom.attr('data-year', selectOneObj.id);
                showDateDom.attr('data-month', selectTwoObj.id);
                showDateDom.attr('data-date', selectThreeObj.id);
                showDateDom.attr('data-hour', selectFourObj.id);
                showDateDom.attr('data-minute', selectFiveObj.id);
                showDateDom.html(selectOneObj.value + ' ' + selectTwoObj.value + ' ' + selectThreeObj.value + ' ' + selectFourObj.value + ' ' + selectFiveObj.value);
            }
        });
});