const express = require('express')
const app = express();
app.use(express.json());

const http = require('http');

// default URL to API
app.use('/', function (req, res) {
  res.send('node-ex-api works :-)');
});

const server = http.createServer(app);
const port = 3000;
server.listen(port);

console.debug('Server listening on port ' + port);

