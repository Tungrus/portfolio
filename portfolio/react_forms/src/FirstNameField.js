var React = require('react')
var createReactClass = require('create-react-class');
var ReactDOM = require('react-dom')

var FirstNameField = createReactClass({
  render: function() {
    return (
      <div>
        <h2>Account Details</h2>
        <ul className="form-fields">
          <li>
            <label>Name</label>
            <input type="text" ref="name" defaultValue={this.props.fieldValues.firstName} required/>
            <h3 color="red" ref="nameError"></h3>
          </li>
          <li className="form-footer">
            <button className="btn -primary pull-right" onClick={this.nextStep}>Save &amp; Continue</button>
          </li>
        </ul>
      </div>
    )
  },

  nextStep: function(e) {
    e.preventDefault()
    var value = ReactDOM.findDOMNode(this.refs.name).value;
    if(value == null) {
      ReactDOM.render("name empty", ReactDOM.findDOMNode(this.refs.nameError));
      return;
    }
    var data = {
      firstName     : value,
    }

    this.props.saveValues(data)
    this.props.nextStep()
  }
})

export default FirstNameField;
