/*
	李亦然 
	 tree
	无限级树 v0.7 2007-03-20
	IE 6.0 + FF2.0 Opera 7.0 
*/

var Cnup;
if (!Cnup) Cnup = {};

Cnup.TreeMenu=function(tree_id,isCollapseAll){
	this.init(tree_id,isCollapseAll);
}

Cnup.TreeMenu.prototype.init=function (tree_id,isCollapseAll) {
	var tree=document.getElementById(tree_id);
	tree.className="treemenu";
	var lists =tree.getElementsByTagName('UL');
	
 	for (var i = 0; i < lists.length; i++) {
 		lists[i].style.display =isCollapseAll?'none':'block';
	//	this.getLastElement(lists[i].lastChild).style.border="1px solid #369"
		if(lists[i].parentNode.tagName=='LI'){
			this.createTreeBranch(lists[i].parentNode,isCollapseAll);
		}else{
			lists[i].style.display = "block";
		}
	}
}
Cnup.TreeMenu.prototype.getLastElement=function (e){
	 	while (e != null) {
  			if (e.nodeType == 1 ) 
					return e;
  			e = e.previousSibling;
 		}

}
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
