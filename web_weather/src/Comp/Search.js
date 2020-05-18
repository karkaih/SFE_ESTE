import React,{useState} from 'react';
const api = {key : "4fb4a7ce04bfdac5a90209896600dfa9",
base :"https://api.openweathermap.org/data/2.5/"}

function Search(){
const [query,setQuery]=useState('');
const [weather,setWeather]=useState({});

const dateBuilder =(d)=> {

    let months =["January" , "February" , "March" ,"April" , "May"
    ,"June" ,"July" , "August" ,"September" ,"November" ,"December"];
    
    let days = ["Sunday" ,"Monday" ,"Tuesday" ,"Wednesday"
    ,"Thursday" ,"Friday" ," Saturday"];
    
    let day = days[d.getDay()];
    let date = d.getDate();
    let month = months[d.getMonth()];
    let year = d.getFullYear();
    
    
    return `${day} ${date} ${month} ${year}`
      }

const search = evt => {
    if(evt.key=="Enter") {
      fetch(`${api.base}weather?q=${query}&units=metric&APPID=${api.key}`)
      .then(res=>res.json())
      .then(result =>{
        setWeather(result) ;
         setQuery(''); 
        console.log(result)
        } );
    }
  }
return (
 <div className={(typeof weather.main !="undefined" )? 
    ( (weather.main.temp >16) ? 'App warm' : 'App' )
    :'App'}>

      <main>
        <div className="search-box">
     <input type="text" className="search-bar" placeholder="Search By City...."
     onChange={e=>setQuery(e.target.value)} value={query}
     onKeyPress={search}/>
       </div>
{(typeof weather.main != "undefined") ? (
      <div>
      <div className="location-box"> 
         <div className="location"> {weather.name},{weather.sys.country} </div>
         <div className="date"> {dateBuilder(new Date())} </div>
       </div>

       <div className="weather-box">
         <div className="temp">
         temp_norm: {Math.round(weather.main.temp)}°C <br></br>
         temp_max : {Math.round(weather.main.temp_max)}°C<br></br>
        temp_min :  {Math.round(weather.main.temp_min)}°C<br></br>
        pressure : {Math.round(weather.main.pressure)}hPa<br></br>
        humidity: {Math.round(weather.main.humidity)}%<br></br>
        Wind Speed :  {Math.round(weather.wind.speed)}mPh
         </div>
        
       <div className="weather">  
       
        {weather.weather[0].description}</div>
       </div>
       </div>

): ('')}
     </main>

    </div>

)

}
export default Search ;