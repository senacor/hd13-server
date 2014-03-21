var vertx = require('vertx');
var container = require('vertx/container');

var console = require('vertx/console');

console.log("hello, this is the bootstrap verticle");
container.deployVerticle('http.js');
