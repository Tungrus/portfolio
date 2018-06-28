var React = require('react')
var createReactClass = require('create-react-class');
var ReactDOM = require('react-dom')

const email = (value) => {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(event);
  };


var EmailField = createReactClass({
  render: function() {
    return (
      <div>
        <h2>Account Details</h2>
        <ul className="form-fields">
          <li>
            <label>Email</label>
            <input type="email" ref="EmailField" defaultValue={this.props.fieldValues.email} required/>
            <h3 color="red" ref="EmailError">{this.props.emailErrorMessage}</h3>
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
    var emailFromInput = ReactDOM.findDOMNode(this.refs.EmailField).value;
    var isValid = email(emailFromInput);
    if(isValid !== true) {
        ReactDOM.render("email is not valid or empty", ReactDOM.findDOMNode(this.refs.EmailError));
        return;
    }
    var data = {
        email : emailFromInput,
    }

    this.props.saveValues(data)
    this.props.nextStep()
  }
})

export default EmailField;