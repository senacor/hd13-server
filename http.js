var vertx = require('vertx');
var container = require('vertx/container');
var eventBus = vertx.eventBus;

var console = require('vertx/console');

var ip = container.env['OPENSHIFT_VERTX_IP'] || '127.0.0.1';
var port = parseInt(container.env['OPENSHIFT_VERTX_PORT'] || 8080);

vertx.createHttpServer().requestHandler(function(req) {
	console.log("req-path: "+req.path());

    var reqInfo = {
      "event-type":"http-access",
      "ip":req.remoteAddress().getAddress().getHostAddress(),
      "url":req.path()
    };
    eventBus.send("hd13.eventlogger", reqInfo);

    var file = req.path() === '/' ? 'index.html' : req.path();
    req.response.sendFile('webroot/' + file);
}).listen(port, ip);
