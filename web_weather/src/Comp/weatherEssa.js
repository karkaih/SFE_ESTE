import React, { Component } from 'react';
var i = 0;

class weather extends Component{
constructor (props){
 
super(props);
this.state ={
  items :[] ,
  isloaded : false,

}

}

componentDidMount(){
  fetch('https://api.openweathermap.org/data/2.5/onecall?lat=31.509560&lon=-9.767320&exclude=hourly,current&appid=a6f41d947e0542a26580bcd5c3fb90ef&units=metric')
    .then(res => res.json())
    .then(json => {
      this.setState({
        isLoaded: true,
        items: json,
      })
    });
}

render(){ 

  function toDateTime(secs) {
      var t = new Date(1970, 0, 1); // Epoch
      t.setSeconds(secs);
      return t;
  }

  function formatDate(string){
    var options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(string).toLocaleDateString([],options);
}

  var { isLoaded, items } = this.state;
  if(!isLoaded){

    return <div className="test">Loading...</div>
  }else{

    return(
      <div className="test">
      <ul>
        {
          
        this.state.items.daily.map(item => (
        
          <div className ="chirok" key ={i++}>
             Date :  {formatDate(toDateTime(item.dt))} <br></br> 
             Temp: {Math.floor((item.temp.day ))} °C<br></br> 
             Temp_min: {Math.floor((item.temp.min ))} °C<br></br> 
             Temp_max: {Math.floor((item.temp.max ))} °C<br></br> 
             night: {Math.floor((item.temp.night))} °C<br></br> 
             eve: {Math.floor((item.temp.eve ))} °C<br></br> 
             morn: {Math.floor((item.temp.morn ))} °C <br></br> 
        
          </div>
          ))
        }
      </ul>
      </div>
    );
  }
}
}
export default weather;
