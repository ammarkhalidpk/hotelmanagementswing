var Cnup;
if (!Cnup) Cnup = {};

Cnup.TreeMenu=function(tree_id,isCollapseAll){
	this.init(tree_id,isCollapseAll);
	this.config=null;
}

Cnup.TreeMenu.prototype.init=function (tree_id,isCollapseAll) {
	var tree=document.getElementById(tree_id);
	if(tree==null) return;
	
	tree.className="treemenu";
/*	
	tree.onclick=function(e){
	//浏览器 
		var o=window.event?event.srcElement:e.target;
		while(o!=this) {
			if(o.tagName=="A"){
				
				var lastSelect=document.getElementById("select")
				if(lastSelect) 
					lastSelect.removeAttribute("id")
				o.id="select";

				break;
			}
			o=o.parentNode;
		}
	}
	*/
	var lists =tree.getElementsByTagName('UL');
	//IE...
	//if(document.all) this.getLastElement(tree.lastChild).className="last-child" 
 	for (var i = 0; i < lists.length; i++) {
 		lists[i].style.display =isCollapseAll?'none':'block';
 		//IE
	//	if(document.all) this.getLastElement(lists[i].lastChild).className="last-child";
		if(lists[i].parentNode.tagName=='LI'){
			this.createTreeBranch(lists[i].parentNode,isCollapseAll);
		}else{
			lists[i].style.display = "block";
		}
	}
}


Cnup.TreeMenu.prototype.getLastElement=function(e){
	 	while (e != null) {
  			if (e.nodeType == 1 ) 
					return e;
  			e = e.previousSibling;
 		}
}
/**
创建“支”节点
*/
Cnup.TreeMenu.prototype.createTreeBranch=function (element,isCollapseAll){
	var expandImg='../images/plus.gif';
	var collapseImg='../images/minus.gif';
	var img=document.createElement("IMG");
	img.src =isCollapseAll? expandImg:collapseImg;
 	img.align='absmiddle';
	img.className='branch';

	img.onclick=function(){

		var newDisplay = "none";
 		var e = this.nextSibling; 
		while (e != null) {
			if (e.tagName == "UL") {
				if (e.style.display == "none") {
					newDisplay = "block";
					this.src= collapseImg;
				}else{
					this.src= expandImg;
				}
  		 		break;
  			}
  			e = e.nextSibling;
 		}
 		while (e != null) {
  			if (e.tagName == "UL" ) e.style.display = newDisplay;
  			e = e.nextSibling;
 		}
	}
	
	element.insertBefore(img,element.firstChild);
}

