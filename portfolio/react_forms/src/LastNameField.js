var React = require('react')
var createReactClass = require('create-react-class');
var ReactDOM = require('react-dom')

var LastNameField = createReactClass({
  render: function() {
    return (
      <div>
        <h2>Account Details</h2>
        <ul className="form-fields">
          <li>
            <label>Last Name</label>
            <input type="text" ref="LastNameField" defaultValue={this.props.fieldValues.lastName} required />
            <h3 color="red" ref="LastNameError"></h3>
          </li>
          <li className="form-footer">
            <button className="btn -primary pull-right" onClick={this.nextStep}>Save &amp; Continue</button>
            <button className="btn -default pull-left" onClick={this.props.previousStep}>Back</button>
          </li>
        </ul>
      </div>
    )
  },

  nextStep: function(e) {
    e.preventDefault()
    var value = ReactDOM.findDOMNode(this.refs.LastNameField).value;
    console.log( value.length);
    if(value == null || value.length == 0) {
      ReactDOM.render("lastname empty", ReactDOM.findDOMNode(this.refs.LastNameError));
      return;
    }

    var data = {
      lastName     : value,
    }

    this.props.saveValues(data)
    this.props.nextStep()
  }
})

export default LastNameField;