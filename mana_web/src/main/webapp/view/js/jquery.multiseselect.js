// JavaScript Document
(function($){
	//������¼�
	$(document).click(function(event){
		$(".multiselect-content").hide();
	});	
   $.fn.multiseSelect= function(settings){     
    //Ĭ�ϲ���
    var defaultSettings = {
    	id:'',
    	width:'90%',
		valItem:'',
		txtItem:'',
		selectItem:'',
		disableCheck:false,
		flagStr:'',
		render:null,
		changeFunction:null
    };
           
    /* �ϲ�Ĭ�ϲ������û��Զ������ */
    settings = $.extend(defaultSettings,settings);
           
    return this.each(function(){
		//����
		var selObject = $(this);//��ȡ��ǰ����
		var selId = selObject.attr("id");//�õ�select id����
		var valArray = new Array();//����value
		var txtArray = new Array();//����text
		var multiseArrayCheck = [];
		var m = 0;
		//����ȡ�����������value��Text
		selObject.find("option").each(function(index){
			valArray[index] = $.trim($(this).val());
			txtArray[index] = $.trim($(this).text());
			if($(this).attr("selected")){
				multiseArrayCheck[m] = $.trim($(this).val());	
				m +=1;
			}			
		}); 
		var selParent = selObject.parent();
		selObject.remove();//�Ƴ�select
		settings.id = selId;
		settings.valItem = valArray;
		settings.txtItem = txtArray;
		settings.render = selParent;	
		settings.selectItem = multiseArrayCheck;
		//��ʼ�������б�
		$.initMultiseSelect(settings);
	});
   };
  //��̬������ѡ�����б�
  $.fn.createMultiseSelect = function(settings){
	//Ĭ�ϲ���
    var defaultSettings = {
		id:'',
    	width:'90%',
		valItem:'',
		txtItem:'',
		selectItem:'',
		disableCheck:false,
		flagStr:'',
		render:null,
		changeFunction:null
    };       
    /* �ϲ�Ĭ�ϲ������û��Զ������ */
    settings = $.extend(defaultSettings,settings);	
	if(settings.render==null){
		settings.render = this;		
	}	
	//��ʼ�������б�
    $.initMultiseSelect(settings);  
  };
  //��̬�޸Ķ�ѡ�����б��ֵ
  $.fn.changeMultiseSelectContent = function(settings){
	//Ĭ�ϲ���
    var defaultSettings = {
		valItem:'',
		txtItem:'',
		selectItem:'',
		disableCheck:false,
		flagStr:'',
		changeFunction:null
    };       
    /* �ϲ�Ĭ�ϲ������û��Զ������ */
    settings = $.extend(defaultSettings,settings);	
	var $cId = this.attr("id");
	//����б��е�����
	var $content = $("#"+$cId+"_multiselect_content");
	$content.children().remove();
	//���ø߶�
	if(settings.valItem.length>8){
		$content.css("height",8*27);//���ø�		
	}else{
		$content.css("height",settings.valItem.length*27);//���ø�		
	}	
	if(settings.selectItem!=null&&settings.selectItem!=""){
		settings.selectItem = $.trim(settings.selectItem);
	}	
	if(settings.changeFunction==null||settings.changeFunction==""){
		var strFunc = $.trim($("#"+$cId).attr("title"));
		settings.changeFunction = strFunc;
	}
	var htmlArray = [];
	var $choseItems = "";	
	var $disableCheck = "";
	 //�����б���ѡ��
	 if(settings.disableCheck){
		$disableCheck = 'disabled="disabled"';
	 }
	 $.each(settings.valItem,function(i){
		var contentdiv = "";
		var selectFlag = "";
		var checkFlag = "";
		if(settings.selectItem!=null&&settings.selectItem!=""){
			$.each(settings.selectItem,function(m){
				if(settings.valItem[i]==settings.selectItem[m]){
					$choseItems = $choseItems + settings.txtItem[i]+",";
					selectFlag = ' multiseSelectCheckDiv_select';
					checkFlag = ' checked="checked"';
				}	
			});
		}
		htmlArray[i] = '<div class="multiselectCheckDiv '+selectFlag+'" onmouseenter="multiseSelectMouseEnter(\''+$cId+'\',this,event)" onclick="multiseSelectCheckDivClick(\''+$cId+'\',this,event,\''+settings.changeFunction+'\','+settings.disableCheck+')" ><span style="float:left;margin-left:2px;margin-top:8px;color:red">'+settings.flagStr+'</span><input type="checkbox" value="'+settings.valItem[i]+'" style="float:left;margin:3px;" class="multiselectCheckBox"'+checkFlag+$disableCheck+' onclick="multiseSelectCheckBoxClick(\''+$cId+'\',this,\''+settings.changeFunction+'\',event)"/><div style="float:left;margin-left:2px;margin-top:8px;" class="multiselectCheckSpan">'+ settings.txtItem[i]+'</div></div>';	
	 });
	 $content.append(htmlArray.join(""));//չʾ����
	if($choseItems!=""){
		$choseItems = $choseItems.substring(0,$choseItems.length-1);	
	}
	$("#"+$cId+"_multiselect_input").val($choseItems);
	//�رջ���
	$("#"+$cId).multiseSelectLoaddingOff();
  };
  //��ʼ�������б�
  $.initMultiseSelect = function(settings){
	if(settings.id==''||settings.render==null){return;} 
	var strFunc = "";
	if(settings.changeFunction!=null&&settings.changeFunction!=""){
		strFunc = settings.changeFunction;
	}
	 var selHtml = '<div style="display:none;" id="'+settings.id+'" title="'+strFunc+'"></div><div style="margin-left:2px;"><div class="multiseSelect-loader" id="'+settings.id+'_multiseSelect_loader">���ݼ�����...</div><input type="text" class="multiselect-input" style="width:'+settings.width+'" id="'+settings.id+'_multiselect_input" onfocus="multiseSelectInputFocus(this)" onkeyup="multiseSelectInputKeyUp(\''+settings.id+'\',this,event)"/><div class="multiselect-img" id="'+settings.id+'_multiselect_img" onclick="multiseSelectContentShow(\''+settings.id+'\',event)"/></div>';	
	 settings.render.append(selHtml);//�滻�µ�
	 var selHeight = 0;
	 if(settings.valItem.length>8){
	 	selHeight = 8*27;		
	 }else{
		selHeight = settings.valItem.length*27;	
	 }
	 var selInput = $("#"+settings.id+"_multiselect_input");
	 var cWidth = selInput.width()+24;//�����б�Ŀ�
	 var htmlArray = [];
	 htmlArray[0] = '<div class="multiselect-content" id="'+ settings.id+'_multiselect_content" style="height:'+selHeight+'px;width:'+cWidth+'px;">';
	 var $choseItems = "";
	 var $disableCheck = "";
	 //�����б���ѡ��
	 if(settings.disableCheck){
		$disableCheck = 'disabled="disabled"';
	 }	 
	 var len = settings.valItem.length;
	 var selectFlag = '';
	 var checkFlag = '';
	 $.each(settings.valItem,function(i){		
		if(settings.selectItem!=null&&settings.selectItem!=""){
			$.each(settings.selectItem,function(m){
				if(settings.valItem[i]==settings.selectItem[m]){
					$choseItems = $choseItems + settings.txtItem[i]+",";
					selectFlag = ' multiseSelectCheckDiv_select';
					checkFlag = ' checked="checked"';
				}	
			});			
		}
		htmlArray[i+1] = '<div class="multiselectCheckDiv '+selectFlag+'" onmouseenter="multiseSelectMouseEnter(\''+settings.id+'\',this,event)" onclick="multiseSelectCheckDivClick(\''+settings.id+'\',this,event,\''+settings.changeFunction+'\','+settings.disableCheck+')"><span style="float:left;margin-left:2px;margin-top:8px;color:red">'+settings.flagStr+'</span><input type="checkbox" value="'+settings.valItem[i]+'" style="float:left;margin:3px;" class="multiselectCheckBox"'+checkFlag+$disableCheck+' onclick="multiseSelectCheckBoxClick(\''+settings.id+'\',this,\''+settings.changeFunction+'\',event)"/><div style="float:left;margin-left:2px;margin-top:8px;" class="multiselectCheckSpan">'+ settings.txtItem[i]+'</div></div>';	
		selectFlag = '';
		checkFlag = '';
	 });
	 htmlArray[len+2] = "</div>";
	$("body").append(htmlArray.join(""));//չʾ����
	if($choseItems!=""){
		$choseItems = $choseItems.substring(0,$choseItems.length-1);	
	}
	selInput.val($choseItems);
  };
  //�õ�ѡ�е�value
  $.fn.getMultiseSelectCheckValue = function(){
	 var $cId = this.attr("id");
	 var $id = $cId+"_multiselect_content";	
	 var val = "";
	 $("#"+$id+" input[type='checkbox']").each(function(){
		if($(this).is(":checked")){
			val = val + $(this).val()+",";
		}											   
	 });
	 if(val!=""){
		val = val.substring(0,val.length-1);	
	 }
	return val;
  };
  //�õ�ѡ�е�text
  $.fn.getMultiseSelectCheckText = function(){
	  var $cId = this.attr("id");
	  var $id = $cId+"_multiselect_content";	
	  var val = "";
	  $("#"+$id+" input[type='checkbox']").each(function(){
		  if($(this).is(":checked")){
			val = val + $.trim($(this).parent().find(".multiselectCheckSpan").eq(0).html())+",";
		  }											   
	   });
	   if(val!=""){
	   	 val = val.substring(0,val.length-1);	
	   }
	  return val;
	};
	//�õ�δѡ�е�value
	$.fn.getMultiseUnselectCheckValue = function(){
		var $cId = this.attr("id");
		var $id = $cId+"_multiselect_content";	
		var val = "";
		$("#"+$id+" input[type='checkbox']").each(function(){
			if(!$(this).is(":checked")){
				val = val + $(this).val()+",";
			}											   
		});
		if(val!=""){
			val = val.substring(0,val.length-1);	
		}
		return val;
	};
	//�õ�δѡ�е�text
	$.fn.getMultiseUnselectCheckText = function(){
		var $cId = this.attr("id");
		var $id = $cId+"_multiselect_content";	
		var val = "";
		$("#"+$id+" input[type='checkbox']").each(function(){
			if(!$(this).is(":checked")){
				val = val + $.trim($(this).parent().find(".multiselectCheckSpan").eq(0).html())+",";
			}											   
		});
		if(val!=""){
			val = val.substring(0,val.length-1);	
		}
		return val;
	};
	//�õ����е�value
	$.fn.getMultiseSelectAllValue = function(){
		var $cId = this.attr("id");
		var $id = $cId+"_multiselect_content";	
		var val = "";
		$("#"+$id+" input[type='checkbox']").each(function(){
			val = val + $(this).val()+",";										   
		});
		if(val!=""){
			val = val.substring(0,val.length-1);	
		}
		return val;
	};
	//�õ����е�text
	$.fn.getMultiseSelectAllText = function(){
		var $cId = this.attr("id");
		var $id = $cId+"_multiselect_content";	
		var val = "";
		$("#"+$id+" input[type='checkbox']").each(function(){
			val = val + $.trim($(this).parent().find(".multiselectCheckSpan").eq(0).html())+",";										   
		});
		if(val!=""){
			val = val.substring(0,val.length-1);	
		}
		return val;
	};
   //�����
  $.fn.multiseSelectLoaddingOn = function(){
	var $cId = this.attr("id");
	var $load = $("#"+$cId+"_multiseSelect_loader");//����div	
	if($load.is(":hidden")){
		var $parent = $load.parent();
		$load.css("left",$parent.offset().left);
		$load.css("top",$parent.offset().top);
		$load.css("width",$parent.width()-2);
		$load.show();	
	}
  };
   //����ر�
  $.fn.multiseSelectLoaddingOff = function(){
	var $cId = this.attr("id");
	var $load = $("#"+$cId+"_multiseSelect_loader");//����div
	if(!$load.is(":hidden")){
		$load.hide();	
	}
  };
  //��������б��ֵ
  $.fn.multiseSelectInputClear = function(){
	var $cId = this.attr("id");
	$("#"+$cId+"_multiselect_input").val("");
	$("#"+$cId+"_multiselect_content .multiselectCheckDiv").removeClass("multiseSelectCheckDiv_select");
	$("#"+$cId+"_multiselect_content .multiselectCheckBox").removeAttr("checked");
  };
  //���������б�������
  $.fn.multiseSelectInputSetText = function(valArray){
	  if(valArray!=null&&valArray!=null){
		var txt = "";
	  	var $cId = this.attr("id");
	  	var cObj = null;
		$("#"+$cId+"_multiselect_content .multiselectCheckDiv").removeClass("multiseSelectCheckDiv_select");
		$("#"+$cId+"_multiselect_content .multiselectCheckDiv").each(function(){
			cObj = $(this);
			var lineCk = cObj.find(".multiselectCheckBox");
			lineCk.removeAttr("checked"); 
			var lineVal = $.trim(lineCk.val());	
			$.each(valArray,function(i){
				if(valArray[i]!=""&&valArray[i]==lineVal){
					cObj.addClass("multiseSelectCheckDiv_select");
					txt +=$.trim(cObj.find(".multiselectCheckSpan").html())+",";	
					lineCk.attr("checked","checked");
				}
			});
		});	
		if(txt!=""){
			txt = txt.substring(0,txt.length-1);	
		}
		$("#"+$cId+"_multiselect_input").val(txt);
	  }
  };
 //�Ƴ�ѡ�е���
  $.fn.multiseSelectClearSelect = function(valArray){
 	if(valArray!=null&&valArray!=""){
		var $cId = this.attr("id");
		var $input = $("#"+$cId+"_multiselect_input");
		var $val = "";
		var cObj = null;
		$("#"+$cId+"_multiselect_content .multiselectCheckDiv").each(function(){
			cObj = $(this);
			var lineCk = cObj.find(".multiselectCheckBox");			
			var lineVal = $.trim(lineCk.val());
			$.each(valArray,function(i){
				if(valArray[i]==lineVal){	
					if(lineCk.is(":checked")){
						lineCk.removeAttr("checked");
						cObj.removeClass("multiseSelectCheckDiv_select");
					}
				}
			});
			if(lineCk.is(":checked")){
				$val = $val + $.trim(cObj.find(".multiselectCheckSpan").html())+",";
			}
		});	
		if($val!=""){
			$val = $val.substring(0,$val.length-1);
		}
		$input.val($val);
	}
  };
  //�������б�۽�
  $.fn.multiseSelectInputFocus = function(){
	  var $cId = this.attr("id");
	  $("#"+$cId+"_multiselect_input").focus();
  };
 })(jQuery);
 //�����ťչʾ�����б�
 function multiseSelectContentShow(cId,eve){
	var contentList = $("#"+cId+"_multiselect_content");
	var cInput = $("#"+cId+"_multiselect_input");
	if(contentList.is(":hidden")){
		$(".multiselect-content").hide();
		//��ʾ
		var cLeft = cInput.offset().left;//����
		var cTop = cInput.offset().top+cInput.outerHeight();//����
		var cWidth = cInput.width()+24;//�����б�Ŀ�
		contentList.css("left",cLeft);
		contentList.css("top",cTop);
		contentList.css("width",cWidth);
		contentList.show();
		cInput.focus();
	}else{
		//����	
		contentList.hide();
	}	
	var  eve = eve || window.event;
    if(eve.stopPropagation) { //W3C��ֹð�ݷ���
       eve.stopPropagation();
    } else {
       eve.cancelBubble = true; //IE��ֹð�ݷ���
    }
 };
   //�������
  function multiseSelectMouseEnter(id,obj,eve){	
	  $("#"+id+"_multiselect_content .multiselectCheckDiv").removeClass("multiseSelectCheckDiv_hover");										   
	  $(obj).addClass("multiseSelectCheckDiv_hover");
	  var  eve = eve || window.event;
      if(eve.stopPropagation) { //W3C��ֹð�ݷ���
        eve.stopPropagation();
      } else {
        eve.cancelBubble = true; //IE��ֹð�ݷ���
      }
  };
  //�����ʧ��
  function multiseSelectInputBlur(id){
	var cText = $("#"+id).getMultiseSelectCheckText();
	var cInput = $("#"+id+"_multiselect_input");
	if(cText!=""){
		cInput.val(cText);
	}else{
		cInput.val("��ѡ��");
	}	
  };
	//�����۽�
  function multiseSelectInputFocus(obj){
		var val = $(obj).val();
		if($.trim(val.substring(0,3))=='��ѡ��'){
			$(obj).val("");	
		}
	};
//�����б�checkbox�Ƿ�ѡ��
  function multiseSelectCheckBoxClick(cId,obj,func,eve){
	if($(obj).is(":checked")){
		$(obj).parent().removeClass("multiseSelectCheckDiv_select");	
	}else{
		$(obj).parent().addClass("multiseSelectCheckDiv_select");	
	}
	multiseSelectInputBlur(cId);
	$("#"+cId+"_multiselect_input").focus();
	//�ⲿonchange�¼�
	if(func!=null&&func!=""){
		eval(func);		
	}	
	var  eve = eve || window.event;
    if(eve.stopPropagation) { //W3C��ֹð�ݷ���
       eve.stopPropagation();
    } else {
       eve.cancelBubble = true; //IE��ֹð�ݷ���
    }
  };
 function multiseSelectCheckDivClick(id,obj,eve,func,flag){
	if(!flag){
		//�����checkbox��	
		var cCheckBox = $(obj).find(".multiselectCheckBox");
		if(cCheckBox.is(":checked")){
			cCheckBox.removeAttr("checked");
			cCheckBox.parent().removeClass("multiseSelectCheckDiv_select");						
		}else{
			cCheckBox.attr("checked","checked");
			cCheckBox.parent().addClass("multiseSelectCheckDiv_select");
		}
		var selInput = $("#"+id+"_multiselect_input");
		multiseSelectInputBlur(id);
		selInput.focus();		
	}	
	//�ⲿonchange�¼�
	if(func!=null&&func!=""){
		eval(func);		
	}
	var  eve = eve || window.event;
    if(eve.stopPropagation) { //W3C��ֹð�ݷ���
       eve.stopPropagation();
    } else {
       eve.cancelBubble = true; //IE��ֹð�ݷ���
    }
 }
  //�ı�����������
 function multiseSelectInputKeyUp(cId,obj,eve){
		var val = $.trim($(obj).val());	
		cId +="_multiselect_content";
		if(eve.keyCode==8){
		//ɾ��					
		}else if(eve.keyCode==37||eve.keyCode==39){
		//����				
		}else if(eve.keyCode==38){
		//����
			var $index = 0;
			var $allshowdiv = $("#"+cId+" > .multiselectCheckDiv:visible");
			var $focusDiv = $("#"+cId).find(".multiseSelectCheckDiv_hover");//��ǰ�۽���div
			if($focusDiv.html()==undefined){
				var $selectDiv = $("#"+cId).find(".multiseSelectCheckDiv_select");//��ǰѡ�е�div
				if($selectDiv.html()!=undefined){
					$index = $allshowdiv.index($selectDiv)-1;	
				}					
			}else{
				$index = $allshowdiv.index($focusDiv)-1;
			}				
			var $count = $allshowdiv.size();
			if($index<$count&&$index>-1){
				$focusDiv.removeClass("multiseSelectCheckDiv_hover");
				var zz = parseInt($index/8);
				$("#"+cId).scrollTop(zz*8*27);						
				$allshowdiv.eq($index).addClass("multiseSelectCheckDiv_hover");
			}
			return;
		}else if(eve.keyCode==40){
		//����
			var $index = 0;
			var $allshowdiv = $("#"+cId+" > .multiselectCheckDiv:visible");
			var $focusDiv = $("#"+cId).find(".multiseSelectCheckDiv_hover");//��ǰ�۽���div
			if($focusDiv.html()==undefined){
				var $selectDiv = $("#"+cId).find(".multiseSelectCheckDiv_select");//��ǰѡ�е�div
				if($selectDiv.html()!=undefined){
					$index = $allshowdiv.index($selectDiv)+1;	
				}					
			}else{
				$index = $allshowdiv.index($focusDiv)+1;
			}				
			var $count = $allshowdiv.size();
			if($index<$count&&$index>-1){
				$focusDiv.removeClass("multiseSelectCheckDiv_hover");
				var zz = parseInt($index/8);
				$("#"+cId).scrollTop(zz*8*27);						
				$allshowdiv.eq($index).addClass("multiseSelectCheckDiv_hover");
			}
			return;
		}else if(eve.keyCode==13){
		//�س�
			var $focusDiv = $("#"+cId).find(".multiseSelectCheckDiv_hover");//��ǰ�۽���div
			if($focusDiv.html()!=undefined){
				var cCheckBox = $focusDiv.find(".multiselectCheckBox");
				if(cCheckBox.is(":checked")){
					cCheckBox.removeAttr("checked");
					cCheckBox.parent().removeClass("multiseSelectCheckDiv_select");						
				}else{
					cCheckBox.attr("checked","checked");
					cCheckBox.parent().addClass("multiseSelectCheckDiv_select");
				}
				$("#"+cId.replace("content","input")).focus();
			}
			return;
		}			
		//չʾ����
		var count = 0;
		var cObj = null;
		var txt = "";
		var qp = "";
		var jp = "";
		$("#"+cId+" .multiselectCheckSpan").each(function(){
			cObj = $(this).parent();			
			txt = $(this).html().toLowerCase();
			qp = ConvertPinyin(txt);//ȫƴ
			jp = makePy(txt).toString().toLowerCase();//ȡ��������ĸ
			if(txt.indexOf(val)!=-1||qp.indexOf(val)!=-1||jp.indexOf(val)!=-1){
				cObj.show();
				count +=1;
			}else{
				cObj.hide();
			}
		 });
		//������߶�
		if(count>8){
			$("#"+cId).css("height",8*27);//���ø�		
		}else{
			$("#"+cId).css("height",count*27);//���ø�		
		}
		var cContent = $("#"+cId);
		if(cContent.is(":hidden")){//û��ʾʱ��ʾ������
			cContent.show();
			var cLeft = $(obj).offset().left;//����
			var cTop = $(obj).offset().top+$(obj).outerHeight();//����
			var cWidth = $(obj).width()+24;//�����б�Ŀ�
			cContent.css("left",cLeft);
			cContent.css("top",cTop);
			cContent.css("width",cWidth);
		}
 }