/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {

	var url = "ws://localhost:8080/ca2/notesDisplay";
	var socket = new WebSocket(url);


	socket.onmessage = function(evt) {
		var msg = evt.data;
                var str = jQuery.parseJSON(msg);
                
                var showData = $('#show-data');
                console.log("onmessage", msg);      
                var newPost = $("#newPost");
                newPost.append("<b>NEW NOTE INCOMING!!</b> <br>   Title: " + 
                        str.title + "<br>   Category: "+ 
                        str.category +  "<br>   Content: "+
                        str.content +  "<br>   Timestamp: "+
                        str.timestamp +   "<br><br>");
                
               

 
     
         $("#ResultArea").html("");

    var table = $("<table cellpadding=5 id=abcd class=" + '"table table-striped"'+ " border=1 width=80%  ></table>").appendTo("#ResultArea")
    
  $(document).ready(function() {
    $('#abcd').DataTable();
} );
            var rowHeader = $("<tr></tr>").appendTo(table);
            $("<th class=order-table-header></th>").text("Title").appendTo(rowHeader);
            $("<th class=order-table-header></th").text("Content").appendTo(rowHeader);
            $("<th class=order-table-header></th>").text("Category").appendTo(rowHeader)
            $("<th class=order-table-header></th>").text("Author").appendTo(rowHeader);
            $("<th class=order-table-header></th>").text("Timestamp").appendTo(rowHeader);
		//writeToChatboard("Connected to chat server");
               

  var cat=$("#category").val()
  var url="http://localhost:8080/ca2/api/findAll/"+cat;
               
                $.getJSON(url, function (data) {
                $.each(data, function (i, value) {

                    //Create new row for each record
                    var row = $("<tr></tr>").appendTo(table);
                    $("<td></td>").text(value.title).appendTo(row);
                    $("<td></td>").text(value.content).appendTo(row);
                    $("<td></td>").text(value.category).appendTo(row);
                    $("<td></td>").text(value.author).appendTo(row);
                    $("<td></td>").text(value.timestamp).appendTo(row);
                });
            });
 
                
               
                  showData.text('Loading the JSON file.');
                
	}
	socket.onopen = function(evt) {
           
         $("#displayBtn").on("click", function() {
             $("#ResultArea").html("");

    var table = $("<table cellpadding=5 id=abcd class=" + '"table table-striped"'+ " border=1 width=80%  ></table>").appendTo("#ResultArea")
         $(document).ready(function() {
    $('#abcd').DataTable();
} );
            var rowHeader = $("<tr></tr>").appendTo(table);
            $("<th class=order-table-header></th>").text("Title").appendTo(rowHeader);
            $("<th class=order-table-header></th").text("Content").appendTo(rowHeader);
            $("<th class=order-table-header></th>").text("Category").appendTo(rowHeader)
            $("<th class=order-table-header></th>").text("Author").appendTo(rowHeader);
            $("<th class=order-table-header></th>").text("Timestamp").appendTo(rowHeader);
                var msg = evt.data;
                var showData = $('#show-data');
         //Get JSON data by calling action method in controller
         
             
             
  var cat=$("#category").val();
  var url="http://localhost:8080/ca2/api/findAll/"+cat;
         
         
            $.getJSON(url, function (data) {
                $.each(data, function (i, value) {

                    //Create new row for each record
                    var row = $("<tr></tr>").appendTo(table);
                    $("<td></td>").text(value.title).appendTo(row);
                    $("<td></td>").text(value.content).appendTo(row);
                    $("<td></td>").text(value.category).appendTo(row);
                    $("<td></td>").text(value.author).appendTo(row);
                    $("<td></td>").text(value.timestamp).appendTo(row);
                });
            });
                  
                 });  
	}
	socket.onclose = function() {
		//writeToChatboard("Disconnected from chat server");
	}

});

