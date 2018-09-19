		$(function(){
			var navHeight=$('#mainShowHeader').height();
			var height = $(window).height() - navHeight-20;
			$(".statistical_chart").height(height);
            
			reflashMainShow();
			
			window.addEventListener('message',function(e){
                if(e.source!=window.parent) return;
                reflashMainShow();
            },false);
			
			$(window).resize(function() {
				$(".statistical_chart").height($(window).height() - $('#mainShowHeader').height()-20);

				myChart.resize();
				mychartTwo.resize();
			});
			
		});

		//饼状图2
		var mychartTwo = echarts.init(document.getElementById('chart_right'));
		// 条形图
		var myChart = echarts.init(document.getElementById('chart_left'));
		
		var optionBarChart = {
				title : {
					text : '全市案件Top10统计图',
					padding : 50,
					x : 'center',
					textStyle : {
						fontWeight : 'normal',
						color : '#fff'
					}
				},
				tooltip : {
					trigger : 'axis',
					axisPointer : {
						type : 'shadow'
					}
				},
				grid : {
					left : '6%',
					right : '8%',
					bottom : '8%',
					top : '18%',
					containLabel : true
				},
				itemStyle : {
					normal : {
						barBorderRadius : 8,
						color:'#47fef9'
					},
					label : {
						show : true,
						position : 'top',
						formatter : '{b}\n{c}'
					},
					emphasis : {
						barBorderRadius : 8
					}
				},
				xAxis : {
					type : 'value',
					boundaryGap : [ 0, 0.01 ],
					axisLabel : {
						show : true,

						textStyle : {
							color : '#fff'
						}
					},
					axisLine : {
						lineStyle : {
							color : '#898da3'
						}
					},
					splitLine : {
						show : false
					}
				},
				yAxis : {
					type : 'category',
					data : [],
					axisLabel : {
						show : true,
                        interval:0,
						textStyle : {
							color : '#fff'
						}
					},
					axisLine : {
						lineStyle : {
							color : '#898da3'
						}
					},
					splitLine : {
						show : false
					}
				},
				series : [ {
					type : 'bar',
					barWidth : 20,
					data : []
				} ]
			};


			var optionHistogram ={
				title : {
					text : '重点人员',
					padding : 50,
					x : 'center',
					textStyle : {
						fontWeight : 'bold', // 标题颜色
						color : '#fff'
					}
				},
				color : [ '#ED1460','#F4FC23','#47FEF9','#E7B601','#72F47A','#32E02C','#F5E8C8' ],
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					type : 'scroll',
					orient : 'vertical',
					icon : 'diamond',
					right : 2,
					top : 80,
					bottom : 30,
					data : [],
					textStyle : {
						fontWeight : 'normal',
						color : '#fff'
					}
				},
				series : [ {
					name : '重点人员数',
					type : 'pie',
					radius : '40%',
					center : [ '50%', '56%' ],
					data : [],
					itemStyle : {
                        // normal:{
                        //     label:{
                        //         show:true,
                        //         formatter: '{b}'
                        //     },
                        //     labelLine:{
                        //         show:true
                        //     }
                        //     },
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.7)'
						}
					}
				} ]
			};	
			function pie_charts() {
				// 通过Ajax获取数据
				$.ajax({
					type : "post",
					async : false, // 同步执行
					url : projectPath + "mainShow/statistics",
					dataType : "json", // 返回数据形式为json
					success : function(result) {
						if (result) {
							//
							$("div.middleBottom_person .middleBottom_label").empty();
							$("div.middleBottom_person .middleBottom_label").append(
									result.totalSuspectNum);
							$("div.middleBottom_info .middleBottom_label").empty();
							$("div.middleBottom_info .middleBottom_label").append(
									result.totalInforNum);
							$("div.middleBottom_case .middleBottom_label").empty();
							$("div.middleBottom_case .middleBottom_label").append(
									result.totalCaseNum);
							$("div.middleBottom_alarm .middleBottom_label").empty();
							$("div.middleBottom_alarm .middleBottom_label").append(
									result.totalAlarmNum);

							var serisdata = result.seriesData;

							//
							optionHistogram.legend.data = result.suspectLegendData;
							// jquery遍历
							var value = [];
							$.each(serisdata, function(i, p) {
								value[i] = {
									'name' : p['zoneName'],
									'value' : p['suspectNum']
								};
							});
							optionHistogram.series[0]['data'] = value;

							mychartTwo.hideLoading();
							mychartTwo.setOption(optionHistogram)

							//
							optionBarChart.yAxis.data = result.caseLegendData;
							optionBarChart.series[0].data = result.caseNumData;

							myChart.hideLoading();
							myChart.setOption(optionBarChart);
							
							myChart.resize();
							mychartTwo.resize();
						}
					},
					error : function(errorMsg) {
						alert("图表请求数据失败啦!");
					}
				});
			}

			function reflashMainShow() {
				pie_charts();
				//
				$.ajax({
					type : "get",
					async : false, // 同步执行
					url : projectPath + "ETAlarmHandler/selectEtAlarmListTopTen",
					dataType : "json", // 返回数据形式为json
					success : function(result) {
						if (result) {
							var html = [];
							for ( var i in result.data) {
								//html.push("<li>" + (i * 1 + 1) + "、"
								//		+ result.data[i].alarmDesc + "</li>");
								html.push("<li>"
										+ result.data[i].alarmDesc + "</li>");
							}
							$("#info_list").html(html.join(''));
						}
					},
					error : function(errorMsg) {
						alert("请求数据失败啦!");
					}
				});
			}
