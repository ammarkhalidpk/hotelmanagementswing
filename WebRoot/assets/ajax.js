/*

李亦然 simaple Ajxa util
TODO: 
	1,i18n;
	2,doPost : 自动查找表单并生成 -> ?aa=xx&xxx=xx
	3,mutilPart =?? 
	4,
	

*/

var Ajax={
	debug:true,
	
	lang:"cn",
	stateCode:['尚未初始化','正在读取中...','读取结束','正在处理中...','处理完成'],
	showLoading:function(msg){
		return "<span id=\"loading\">"+msg+"</span>";
	},
	getElement:function(el){
		if (el && typeof el == "string")
			el=document.getElementById(el);
		//if(el) 
		return el;
	},
	getXmlReq:function() {  
		if (window.XMLHttpRequest){ // Mozilla, IE7, Safari 
			return new XMLHttpRequest();
		}else if (window.ActiveXObject){ // IE6 -
			try {
				return new ActiveXObject("Msxml2.XMLHTTP")
			}catch (e){
				try{
					return  new ActiveXObject("Microsoft.XMLHTTP")
				}catch (e){
				}
			}
		}else{				
			alert("您的浏览器不支持AJAX") 
			return null;
		}
	},
	doGet:function(url,targetContiner){
		var xmlhttp=this.getXmlReq();

		if(xmlhttp==null){
				return false;
		}
		var continer=this.getElement(targetContiner);
		
		if(!continer){
			 if(this.debug) alert("!-_- no continer named:"+targetContiner)
			 return false;
		}
		try{
			//IE......
			if(document.all) {
				if(url.indexOf("?")>0){
					url+="&";
				}else{
					url+="?";
				}
				
				url+='ajaxcode=' + Math.random();
			}
			xmlhttp.open("GET",url,true);
			xmlhttp.onreadystatechange = function(){ 
				if (xmlhttp.readyState == 4){  
					if (xmlhttp.status == 200){  
						continer.innerHTML=xmlhttp.responseText;
					}else{
						continer.innerHTML=xmlhttp.status+":["+url+"]";
					}
				}else{
					continer.innerHTML=Ajax.showLoading(Ajax.stateCode[xmlhttp.readyState]);
				}	
			};
			xmlhttp.send(null);
		}catch (e) {
			if(debug) continer.innerHTML=e;
		}
		return false;
	}
}
