var React = require('react')
var ReactDOM = require('react-dom')

import Registration from "./Registration"

window.onload = function() {
  ReactDOM.render(
    <Registration />,
    document.getElementById('registration-form')
  )
};
