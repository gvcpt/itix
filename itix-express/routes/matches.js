var express = require('express');
var router = express.Router();
const axios = require('axios');

const wsMatches = 'http://localhost:4567/getMatches';

router.get('/', async function (req, res, next) {
  const resWs = await axios.get(wsMatches);
  const matches = JSON.parse(JSON.stringify(resWs.data));
  res.status(200).render('index', {matches: matches});

});
module.exports = router;
