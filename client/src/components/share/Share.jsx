import "./share.scss";
import Image from "../../assets/img.png";
import Map from "../../assets/map.png";
import Friend from "../../assets/friend.png";
import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../../context/authContext";
import axios from "axios";
import {useMutation, useQueryClient} from 'react-query'
import { makeRequest } from "../../axios";

const Share = () => {

  const {currentUser} = useContext(AuthContext)
  const[title,setTitle] = useState("");
  const[eventDate,setEventDate] = useState("");
  const[cities,setCities] = useState([]);
  const[cityId,setCityId] = useState("");
  const[text,setText] = useState("");
  const[file,setFile] = useState(null);

  const[userId] = useState(currentUser.userId);


  const getCities = () => {
    axios.get("http://localhost:8085/cities",{withCredentials:true})
    .then((res) => {
      setCities(res.data)
    })
  }

  useEffect(()=>{
    getCities();
  },[])

  const upload = async () => {
    try {
      const formData = new FormData();
      formData.append("file", file);
      const res = await makeRequest.post("/image/upload", formData);
      return res.data;
    } catch (err) {
      console.log(err);
    }
  };

  const queryClient = useQueryClient();

  const mutation = useMutation((newPost) => {
    return makeRequest.post("/posts/createPost",newPost)
  }, {
    onSuccess: () => {
      // Invalidate and refetch
      queryClient.invalidateQueries("posts")
    },
  })

 

  const handleClick = async (e) => {
    e.preventDefault();
    let imgUrl = "";
    if(file) imgUrl = await upload();
    mutation.mutate({title,eventDate,cityId,text,userId,img:imgUrl})
    setTitle("");
    setEventDate("");
    setCityId("");
    setText("")
    setFile(null)
    
  }

  
  return (
    <div className="share">
      <div className="container">
        <div className="top">
          <img
            src={"/upload/"+currentUser.profilePic}
            alt=""
          />
          <input type="text" placeholder={`Başlık`} name="title" onChange={e => setTitle(e.target.value)} />
        </div>
        <div className="top">
         <p>Tarih:</p>
          <input type="date"  name="eventDate" onChange={e => setEventDate(e.target.value)}/>
        </div>
        <div className="top">
          <p>Gerçekleştirilecek Şehir:</p> 
          <select id="city-select"  name="cityId" value={cityId} onChange={e => setCityId(e.target.value)} >
            <option value={""}>-- Şehir Seçin --</option>
            {
              cities.map(city => <option key={city.id} value={city.id}>{city.cityName}</option>)
            }        
          </select>  
        </div>
        <br/>
        <div className="top">
          <p>Açıklama:</p>
          <textarea cols={50}  type="textarea"  name="text" placeholder={`Etkinlik hakkında bilgi verir misin ${currentUser.userName}?`} 
          onChange={e => setText(e.target.value)}/>
        </div>
      
        <hr />
        <div className="bottom">
          <div className="left">
            <input type="file" id="file" style={{display:"none"}} onChange={e => setFile(e.target.files[0])}/>
            <label htmlFor="file">
              <div className="item">
                <img src={Image} alt="" />
                <span>Görsel Ekle</span>
              </div>
            </label>
            <div className="item">
              <img src={Map} alt="" />
              <span>Konum</span>
            </div>
            <div className="item">
              <img src={Friend} alt="" />
              <span>Etiketle</span>
            </div>
          </div>
          <div className="right">
            <button onClick={handleClick}>Paylaş</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Share;