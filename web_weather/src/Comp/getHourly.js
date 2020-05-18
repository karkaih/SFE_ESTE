import React, { Component } from 'react';
import Hourly from './Hourly';
var i = 0;
var  Day1 = [];
    var  Day2 = [];
    var Dt = [];
class getHourly extends Component{

    
constructor (props){
 
super(props);

this.state ={
  items :[] ,
  isloaded : false,

}

}

componentDidMount(){
  fetch('https://api.openweathermap.org/data/2.5/onecall?lat=31.509560&lon=-9.767320&exclude=daily,current&appid=a6f41d947e0542a26580bcd5c3fb90ef&units=metric')
    .then(res => res.json())
    .then(json => {
      this.setState({
        isLoaded: true,
        items: json,
      })
      
    });
}
toDateTime =(secs)=> {
    var t = new Date(1970, 0, 1); // Epoch
    t.setSeconds(secs);
    return t;
}

 formatDate =(string)=>{
return new Date (string).getHours();
}

 chirok =(items) =>{
    for ( i=0;i<48;i++){
    
        if(i<24){
           // console.log(items.hourly[i].temp);
            Day1.push(items.hourly[i].temp) ; 
            Dt.push(this.formatDate(this.toDateTime(items.hourly[i].dt)));
          }
          else{
            Day2.push(items.hourly[i].temp) ; 
          }
    
    }

 }
 
render(){ 

 

  var { isLoaded, items } = this.state;



  if(!isLoaded){
    return <div className="test">Loading...</div>
  }else{
  //    console.log(items.hourly);

    // items.hourly.for(item => {
    //   
     
    //       i++;  
  
        
    //  });
//    console.log(Day1);
let x = 0;
if(x<1){
    this.chirok(this.state.items);
    x++;
}

    return(

      
          
  <div>

<Hourly Day1={Day1} Day2={Day2}  Dt={Dt} />
 </div>
    
    )
  }



}
}
export default getHourly;
