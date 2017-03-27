/**
 * 默认日期查询
 */
function todayQuery() {
	$("#start_dt").val(getDateStr(-1));
	$("#end_dt").val(getDateStr(0));
	$("#dateQuery").click();
}

/**
 * 比例转换
 *
 * @param num
 * @returns {*}
 */
function getPercent(num){
	try{
		var result=(fixed(num*100))+"%";
		return result;
	}catch(e){
		alert(e);
		return "0%";
	}
}

function fixed(str){
	return str.toFixed(2);
}

/**
 * 获取日期
 * @param AddDayCount
 * @returns {string}
 */
function getDateStr(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = dd.getMonth() + 1;// 获取当前月份的日期
	var d = dd.getDate();
	return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
}

function tableQuery(option, callback) {
	var filterWord = "日期";
	if (option.filterWord != null) {
		filterWord = option.filterWord;
	}
	var defaultOption = {
		"id" : "#table",
		"destroy" : true,
		"fixedOrder" : true,
		"aoColumnDefs": [],
		"order" : [ [ 1, 'asc' ] ],
		"bAutoWidth" : false,
		"bProcessing" : true,
		"sDom" : "<'row'<'col-sm-6'l>r>t<'row'<'col-sm-4'i><'col-sm-8'p>>",
		"sPaginationType" : "full_numbers",
		"type" : 'post',
		"dataType" : "json",
		"oLanguage" : {
			"sProcessing" : "正在加载数据...",
			"sLengthMenu" : "显示 &nbsp;_MENU_ 条 ",
			"sZeroRecords" : "没有记录",
			"sInfo" : "第 _START_ 到 _END_ 条&nbsp;&nbsp;总数 _TOTAL_ 条",
			"sInfoEmpty" : "记录数为&nbsp;0",
			"sInfoFiltered" : "(全部记录数 _MAX_条)",
			"sInfoPostFix" : "",
			"sSearch" : "<i class='fa-filter fa'></i>" + filterWord
					+ "&nbsp;",
			"sUrl" : "",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : " 上一页 ",
				"sNext" : " 下一页 ",
				"sLast" : " 最后一页 "
			}
		},
		"initComplete" : function() {
			endBackdrop();
		}
	};
	var result = $.extend(false, defaultOption, option);
	//设置排序列不能sort
	var sortset={"bSortable": false,"aTargets": [0]};
	result.aoColumnDefs.push(sortset);

	// 序号
	if (result.fixedOrder && $(result.id).find("thead tr").length > 0) {
		result["columnDefs"] = [ {
			"searchable" : false,
			"orderable" : false,
			"targets" : 0
		} ];
		for (d in result.aaData) {
			result.aaData[d].unshift(0);
		}
		if ($(result.id).find("thead tr th:nth-child(1)").text() != "序号") {
			$(result.id).find("thead tr th:nth-child(1)").before(
					"<th class='w-order'>序号</th>");
		}
	}
	// 动画
	startBackdrop();
	// 初始化
	var table = $(result.id).DataTable(result);
	// 序号
	if (result.fixedOrder) {
		table.on('order.dt search.dt', function() {
			table.column(0, {
				search : 'applied',
				order : 'applied'
			}).nodes().each(function(cell, i) {
				cell.innerHTML = i + 1;
			});
		}).draw();
	}
	$(result.id).find("thead tr th").each(function(index) {
		if ($(this).attr("show") == "false") {
			table.column(index).visible(false);
		}
	});
	// 自定义字段
	$('#confimCustomColumn').on('click', function(e) {
		$("#customColumnDiv").removeClass('open');
		$(".customColumn .toggle-vis [type='checkbox']").each(function() {
			e.preventDefault();
			var column = table.column($(this).attr('data-column'));
			if ($(this).prop("checked")) {
				column.visible(true);
			} else {
				column.visible(false);
			}
		});

	});

	if ($.isFunction(callback)) {
		callback(table);
	}
	return table;
}

/**
 * 定义公共查询方法
 */
function commonQuery() {
    query();
    // 设置csv导出action
    csvExport();
}

function commonAjax(option) {
	var defaultOption = {
		dataType : "json",
		beforeSend : startBackdrop,
		complete : endBackdrop
	};
	var result = $.extend(false, option, defaultOption);
	$.ajax(result);
}

var backdrop = $("#backdrop");

function startBackdrop() {
	var back = $(".main");
	back.css('position', 'relative');
	backdrop.addClass('in');
	backdrop.show();
}
function endBackdrop() {
	backdrop.hide();
}

/**
 * 表格查询选择的条件址
 *
 * @param i 循环的列的索引
 * @param filterName 查询的名称
 * @param name 查询选择值的key
 * @param num 表格的索引
 * @param selectValues 选择的列的值
 * @param column 列
 * @param dateType 日期类型
 */
function conditionSearch(i, filterName, name, num, selectValues, column, dateType){
	var value = "#table" + num + "_wrapper div:first div#select" + i + " select";
	var selectValue = "#table" + num + "_wrapper div#select" + i + " select";
	var tableName = "#table" + num + "_wrapper div:first";
	var select = $('<div id="select' + i + '" class="col-sm-4"><div class="dataTables_filter"><label><i class="fa fa-search">&nbsp;' + filterName + '&nbsp;</i><select style="width: 100px"><option value="">-请选择-</option></select></label></div></div>')
			.appendTo($(tableName))
			.on('change', function () {
				var val = $.fn.dataTable.util.escapeRegex(
						$(value).val()
				);
				for (var n = 0; n < selectValues.length; n++) {
					if (selectValues[n].includes(name)) {
						selectValues.splice(n, 1);
					}
				}
				selectValues.push(name + ':' + val);
				column
						.search(val ? '^' + val + '$' : '', true, false)
						.draw();
				csvExport(selectValues, dateType);

			});
	column.data().unique().sort().each(function (d, j) {
		$(selectValue).append('<option value="' + d + '">' + d + '</option>')
	});
}

$(document).ready(function() {
});
