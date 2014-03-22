var vertx = require('vertx');
var console = require('vertx/console');

var port = 9000;

var httpServer = vertx.createHttpServer().requestHandler(function(req) {
    console.log("req-path: "+req.path());

     var reqInfo = {
       "event-type":"http-access",
       "ip":req.remoteAddress().getAddress().getHostAddress(),
       "url":req.path()
     };
     vertx.eventBus.send("hd13.eventlogger", reqInfo);

     var file = req.path() === '/' ? 'index.html' : req.path();
     req.response.sendFile('webroot/' + file);
 });

var sockJSServer = vertx.createSockJSServer(httpServer);

sockJSServer.bridge({prefix : '/eventbus'}, [{}], [{}] );
httpServer.listen(port, function() {
	console.log('bridge is now up and running ...')
});
