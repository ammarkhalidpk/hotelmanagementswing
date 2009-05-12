/*
	Ajax选项卡组件
	浏览器：ie7，ff2 opr9 saf3
	Todo:
		1,健壮性 需要很多 if(!xxx) return
		2,IE6下未测试
		
	李亦然 lee_erix@163.com
*/

var AjaxTab={
	config:{
		cacheRequest:true
	},
	getElement:function(element){
		return (element && typeof element == "string")?document.getElementById(element):element;
	},
	getElementChildren:function(element){
		element=this.getElement(element)
		var children = [];
		var child = element.firstChild;
		while (child)	{
			if (child.nodeType == 1)
				children.push(child);
			child = child.nextSibling;
		}
		return children;
	},

	createTabItem:function(){
	},
	expandTab:function (tab,reload){
		var continers=this.getElementChildren(tab.parentNode.getAttribute("target"));
		var index=0;
		var tabs=this.getElementChildren(tab.parentNode);
		for (var i=0; i<tabs.length; i++){
			if(tab==tabs[i]) index=i;
			tabs[i].className="tab";
			continers[i].className="tabContent";
		}
		
		tab.className="tabSelected";
		continers[index].className="activeContent";
		if (!Ajax.getXmlReq())	return false;
		if(tab.getAttribute("href")){
			if(!tab.getAttribute("loaded") || reload){
				Ajax.doGet(tab.getAttribute("href"), continers[index]);
				if(this.config.cacheRequest){
					tab.setAttribute("loaded","true")
				}
			}
		}
		return false;
	},

	create:function(tabid,defaultTabNumber){
		if(!defaultTabNumber)
			defaultTabNumber=0;
		var tabPanel=this.getElement(tabid);
		var tabs=this.getElementChildren(tabid); 
		var continers=this.getElementChildren(tabPanel.getAttribute("target"));
		for (var i=0; i<continers.length; i++){
			
		}
		for (var i=0; i<tabs.length; i++){ 
			tabs[i].className="tab";
			continers[i].className="tabContent";
			tabs[i].onclick=function(){
				if(this.className=="tabSelected")
					return false;
				return AjaxTab.expandTab(this);
			}
			tabs[i].onmouseover=function(){
					if(this.className=="tabSelected")
						return;
					this.className="tabHover"
			}
			tabs[i].onmouseout=function(){
					if(this.className=="tabSelected")
						return;
					this.className="tab"
			}
		}
		this.expandTab(tabs[defaultTabNumber]);
	},
	
		
	show:function(tabid, tabNumber,reload){
		this.expandTab(this.getElementChildren(tabid)[tabNumber],reload) 
	}
}