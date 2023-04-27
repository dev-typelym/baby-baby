/**
 * 
 */


 let check=false;
 $(".Checkbox_icon").on("click", $(".Checkbox_icon"), function(){
     /*check=$("#checkboxtf").is(":checked");*/
     console.log(check);
     if(!check){
         $("#realbutton").css('opacity','1');
         $("#checkboxmint").css('backgroundColor', '#00c4c4');
         check = true;
     }else{
         $("#realbutton").css('opacity','0');
         $("#checkboxmint").css('backgroundColor', '#fff');
         check = false;
     }
 });
 
 