import { useContext, useState } from "react"
import "./update.scss"
import { makeRequest } from "../../axios";
import { useQuery,useMutation,useQueryClient } from 'react-query'
import { AuthContext } from "../../context/authContext";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import CloseIcon from '@mui/icons-material/Close';
import { useEffect } from "react";

export const Update = ({setOpenUpdate,user}) => {

  const{currentUser} = useContext(AuthContext);
  const [cover, setCover] = useState(null);
  const [profile, setProfile] = useState(null);
  const [texts, setTexts] = useState({
    email: user.email,
    name: user.name,
    surName: user.surName,
    //cityId: user.city.id  , 
    
  });

  const upload = async (file) => {
    console.log(file)
    try {
      const formData = new FormData();
      formData.append("file", file);
      const res = await makeRequest.post("image/upload", formData);
      return res.data;
    } catch (err) {
      console.log(err);
    }
  };

  const { isLoading, error, data } = useQuery(['city'], () =>
  makeRequest.get("/cities").then(res => {
    return res.data;
  })
)

  // const handleChange = (e) => {
  //   setTexts((prev) => ({ ...prev, [e.target.name]: [e.target.value] }));
  // };
  const handleChange = (e) => {
    setTexts((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };
  

  const queryClient = useQueryClient();

  const mutation = useMutation(
    (user) => {
      return makeRequest.put(`/users/update?userId=${currentUser.userId}`, user);
    },
    {
      onSuccess: () => {
        // Invalidate and refetch
        queryClient.invalidateQueries(["user"]);
      },
    }
  );

  const handleClick = async (e) => {
    e.preventDefault();

    
    let coverUrl;
    let profileUrl;
    coverUrl = cover ? await upload(cover) : user.coverPic;
    profileUrl = profile ? await upload(profile) : user.profilePic;
    console.log(texts)
    mutation.mutate({ ...texts, coverPic: coverUrl, profilePic: profileUrl });
    setOpenUpdate(false);
    setCover(null);
    setProfile(null);

  

  }


  return (
    <div className="update">
      <div className="wrapper">
        <h1>Profilinizi Güncelleyin</h1>
        <form>
          <div className="files">
            <label htmlFor="cover">
              <span>Kapak Fotoğrafı</span>
              <div className="imgContainer">
                <img
                  src={
                    cover
                      ? URL.createObjectURL(cover)
                      : "/upload/" + user.coverPic
                  }
                  alt=""
                />
                <CloudUploadIcon className="icon" />
              </div>
            </label>
            <input
              type="file"
              id="cover"
              style={{ display: "none" }}
              onChange={(e) => setCover(e.target.files[0])}
            />
            <label htmlFor="profile">
              <span>Profil Fotoğrafı</span>
              <div className="imgContainer">
                <img
                  src={
                    profile
                      ? URL.createObjectURL(profile)
                      : "/upload/" + user.profilePic
                  }
                  alt=""
                />
                <CloudUploadIcon className="icon" />
              </div>
            </label>
            <input
              type="file"
              id="profile"
              style={{ display: "none" }}
              onChange={(e) => setProfile(e.target.files[0])}
            />
          </div>
          <label>Email</label>
          <input
            type="text"
            value={texts.email}
            name="email"
            onChange={handleChange}
          />
          {/* <label>Şehir</label> */}
          {/* <input
            type="text"
            value={texts.name}
            name="name"
            onChange={handleChange}
          /> */}
          {/* <select value={texts.cityId} name="cityId" onChange={handleChange}>
            <option value={null} >Şehrinizi Güncelleyin</option>
            {error
              ? "Bir şeyler yanlış gitti!"
              : isLoading
              ? "Yükleniyor"
              : data.map((city) => <option key={city.id} value={city.id}>{city.cityName}</option>)}
          </select> */}
          <label>Soyad</label>
          <input
            type="text"
            name="surName"
            value={texts.surName}
            onChange={handleChange}
          />
          {/* <label>Website</label>
          
          <input
            type="text"
            name="website"
            value={texts.website}
            onChange={handleChange}
          /> */}
          <button onClick={handleClick}>Güncelle</button>
        </form>
        <button className="close" onClick={() => setOpenUpdate(false)}>
          <CloseIcon/>
        </button>
      </div>
    </div>
  )
}
