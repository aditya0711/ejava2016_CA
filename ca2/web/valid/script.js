/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {

	var writeToChatboard = function(text) {
		$("#chatarea").val(text + "\n" + $("#chatarea").val());
	}

	var url = "ws://localhost:8080/week04_ca/display";
	var socket = new WebSocket(url);

	$("#sendBtn").on("click", function() {
		socket.send($("#message").val());
		$("#message").val("");
	})

	socket.onmessage = function(evt) {
		// {message: "the message" , timestamp: "time" }
		var msg = JSON.parse(evt.data);
		writeToChatboard(msg.timestamp + ": " + msg.message);
	}
	socket.onopen = function() {
		writeToChatboard("Connected to chat server");
	}
	socket.onclose = function() {
		writeToChatboard("Disconnected from chat server");
	}

});

