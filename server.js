var vertx = require('vertx');
var container = require('vertx/container');

var console = require('vertx/console');

console.log("hello, this is the bootstrap verticle");
container.deployVerticle('http.js');
container.deployVerticle('hd13/eventlog/EventLogger.java');
// container.deployVerticle('hd13/tank/TankControl.java');
