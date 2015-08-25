//get component by id
/*
function $(id)
{
   return document.getElementById(id);
}
*/

//hide or show component by id
/*
function hide(id)
{
  
   if($(id).style.display=="none")
        $(id).style.display="block";
   else
        $(id).style.display="none";
   
   $(id).style.display=$(id).style.display=="none"?"block":"none"; 
}

*/

/* 在container中显示当前系统时间 */
function showTime(container)
{
   var nowTime=new Date();
   
   var datePart=nowTime.getYear()+"年"+(nowTime.getMonth()+1)+"月"+nowTime.getDate()+"日";
   
   var weekPart="星期"+"日一二三四五六".charAt(nowTime.getDay());
   
   var timePart=convertDblSize(nowTime.getHours())+"时"
                    +convertDblSize(nowTime.getMinutes())+"分"
                    +convertDblSize(nowTime.getSeconds())+"秒";
                    
   output=datePart+" "+weekPart+" "+timePart;
   
   container.innerText=output;
}

function convertDblSize(value)
{
   return (value+"").length!=2?"0"+value:value; 
} 

function onlyNumber()
{
   if(event.keyCode==13)
   {
     return true;
   }
   
   if(event.keyCode<48 || event.keyCode>57){
      event.keyCode=0;
      event.returnValue=false;
   }
   
   event.returnValue=true;
}
