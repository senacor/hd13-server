load('vertx.js');
var console = require('vertx/console');

console.log("hello, this is the bootstrap verticle");
vertx.deployVerticle('http.js');
