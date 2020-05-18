import React, { Component } from 'react';


class weather extends Component{
constructor (props){
super(props);
this.state ={
  items :[] ,
  isloaded : false,

}

}

componentDidMount(){
  fetch('http://dataservice.accuweather.com/forecasts/v1/daily/5day/244233?apikey=GpHknc5gdCO9DODRbtPlSXAb2gFtsKpv')
    .then(res => res.json())
    .then(json => {
      this.setState({
        isLoaded: true,
        items: json,
      })
    });
}

render(){ 

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
          
        this.state.items.DailyForecasts.map(item => (
          <div className ="chirok">
             Date :  { formatDate(item.Date)} <br></br> 
              Temp_min: {Math.floor((item.Temperature.Minimum.Value -32 )*(5/9))} °C
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
