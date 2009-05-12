/*
Class: TreeMenu
Function: Enable CSS based tree menu that only show few codes of javascript
Version: 0.1
Platform suported: IE7, FF2.0, OPERA9.
Author: Web Master of Ruby China
Site: www.ruby-china.cn
*/


var $ = function(id) { return document.getElementById(id) || id; };
var Swap = function(obj, openClass, closeClass) {
    var parentObj = obj.parentNode;
    parentObj.className = (parentObj.className == openClass)?closeClass:openClass;
  };


//TreeMenu Begins Here

var TreeMenu = function(){};
TreeMenu.prototype =
{
  create: function(id, tagName, classNames, imgUrl) {
   this.id = id;
   this.obj = $(id);
   this.tagName = tagName || 'li';
   this.openClass = classNames.open || 'open';
   this.closeClass = classNames.close || 'close';
   this.childClass = classNames.child || 'child';
   this.imgUrl = imgUrl || '../images/blank.gif';
  },
  initialize: function() {
   this.nodes = this.obj.getElementsByTagName(this.tagName);
   var nodes = this.nodes;
   var imgFolder = "<img src=\"" + this.imgUrl + "\" class=\"flag\" onclick=\"Swap(this, '" + this.openClass + "', '" + this.closeClass + "');\" alt=\"\"/>";
   var imgFile = "<img src=\"" + this.imgUrl + "\" class=\"flag\" />";
   for(var i = 0; i < nodes.length; i++) {
    nodes[i].className == "" ? nodes[i].className = this.closeClass : "";
    nodes[i].innerHTML = ((nodes[i].className == this.childClass)?imgFile : imgFolder) + nodes[i].innerHTML;
   }
  },
  hide: function(id) {
    $(obj).style.display = "none";
  },
  show: function(id) {
    $(obj).style.display = "";
  },
  swap: function(obj, openClass, closeClass) {
    var parentObj = obj.parentNode;
    parentObj.className = (parentObj.className == openClass)?closeClass:openClass;
  },
  marshal: function(isOpen){
    var nodes = this.nodes;
    var className = isOpen ? this.openClass : this.closeClass;
    for( var i = 0; i < nodes.length; i++) {
      if(nodes[i].className != this.childClass) nodes[i].className = className;
    }
    return true;
  }
};



//This is the demo code 
//You may add your specified code here
var menu1 = null;

window.onload = function(){
menu1 = new TreeMenu();
menu1.create("TreeMenu", "li", {open:"open", close:"close", child: "child"}, "../images/blank.gif");
menu1.initialize();
}

