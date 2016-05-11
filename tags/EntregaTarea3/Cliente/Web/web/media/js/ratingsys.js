var rated=new Array();
var star_grey = '/media/images/star_grey.png';
var star_ = '/media/images/star.png';

// When you actually rate something //
function rateIt(star){
    var star_id = parseInt(star.id);
    for(i=1; i<=star_id; i++){
        //document.getElementById(i.toString()).style.color = "rgb(230, 230, 0)";
        document.getElementById(i.toString()).src = star_; 
        rated[i] = 1;
        
    }
    
    star_id += 1;
    var star_str = star_id.toString();
    while (document.getElementById(star_str) !== null){
        //document.getElementById(star_str).style.color = "#888";
        document.getElementById(star_str).src = star_grey;
        rated[star_id] = 0;
        star_id += 1;
        star_str = star_id.toString();
    }
    
    $("#ester").attr("value", star.id);
    
//    var val = $.trim($("#textoC").val());
//    if (val !== "") {
        $("#textoC").removeAttr("disabled");
        $("#ingresarCom").removeAttr("disabled");
//    }
}

function on(star){
    var star_id = parseInt(star.id);
    for(i=1; i<=star_id; i++){
        //document.getElementById(i.toString()).style.color = "rgb(230, 230, 0)";
        document.getElementById(i.toString()).src = star_;
    }
}

function off(star){
    var star_id = parseInt(star.id);
    for(i=1; i<=star_id; i++){
        if (!rated[i]){
            //document.getElementById(i.toString()).style.color = "#888";
            document.getElementById(i.toString()).src = star_grey;
        }
    }
}
