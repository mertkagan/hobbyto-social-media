import "./profile.scss";
import verify from "../../assets/verify.png";
import FacebookTwoToneIcon from "@mui/icons-material/FacebookTwoTone";
import LinkedInIcon from "@mui/icons-material/LinkedIn";
import InstagramIcon from "@mui/icons-material/Instagram";
import PinterestIcon from "@mui/icons-material/Pinterest";
import TwitterIcon from "@mui/icons-material/Twitter";
import PlaceIcon from "@mui/icons-material/Place";
import LanguageIcon from "@mui/icons-material/Language";
import EmailOutlinedIcon from "@mui/icons-material/EmailOutlined";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import Posts from "../../components/posts/Posts"
import { makeRequest } from "../../axios";
import { useQuery ,useMutation,useQueryClient} from 'react-query'
import profileImage from "../../assets/profileImage.png";
import { useContext } from "react";
import { AuthContext } from "../../context/authContext";
import { useState } from "react";
import { useLocation } from "react-router-dom";
import { Update } from "../../components/update/Update";

const Profile = () => {
  const [openUpdate, setOpenUpdate] = useState(false);
  const {currentUser} = useContext(AuthContext)

  const userId = parseInt(useLocation().pathname.split("/")[2]);
 

  const { isLoading, error, data } = useQuery(['user'], () =>
  makeRequest.get("/users/"+userId).then(res => {
    return res.data;
  })
)
const { isLoading: rIsLoading, data: relationshipData } = useQuery(
  ["relationship"],
  () =>
    makeRequest.get("/relationShips?followedUserId=" + userId).then((res) => {
      return res.data;
    })
);

const queryClient = useQueryClient();

const mutation = useMutation(
  (following) => {
    if (following)
      return makeRequest.delete(`/relationShips/delete?followedUserId=${userId}&followerUserId=${currentUser.userId}`);
    return makeRequest.post("/relationShips", { followerUserId:currentUser.userId,followedUserId:userId });
  },
  {
    onSuccess: () => {
      queryClient.invalidateQueries(["relationship"]);
    },
  }
);

const handleFollow = () => {
  mutation.mutate(relationshipData.includes(currentUser.userId));
};


  return (
    <div className="profile">
    {isLoading ? (
      "loading"
    ) : (
      <>
        <div className="images">
          <img src={"/upload/"+data.coverPic} alt="" className="cover" />
          <img src={"/upload/"+data.profilePic} alt="" className="profilePic" />
        </div>
        <div className="profileContainer">
          <div className="uInfo">
            <div className="left">
              <a href="http://facebook.com">
                <FacebookTwoToneIcon fontSize="large" />
              </a>
              <a href="http://facebook.com">
                <InstagramIcon fontSize="large" />
              </a>
              <a href="http://facebook.com">
                <TwitterIcon fontSize="large" />
              </a>
              <a href="http://facebook.com">
                <LinkedInIcon fontSize="large" />
              </a>
              <a href="http://facebook.com">
                <PinterestIcon fontSize="large" />
              </a>
            </div>
            <div className="center">
              <span>{data.name}  </span>
               
              
              <div className="info">
                <div className="item">
                  <PlaceIcon />
                  <span>TR</span>
                </div>
                <div className="item">
                  <LanguageIcon />
                  <span>website</span>
                </div>
              </div>
              {rIsLoading ? (
                "loading"
              ) : userId === currentUser.userId ? (
                <button onClick={() => setOpenUpdate(true)}>Güncelle</button>
              ) : (
                <button onClick={handleFollow}>
                  {relationshipData.includes(currentUser.userId)
                    ? "Takip Ediliyor"
                    : "Takip Et"}
                </button>
              )}
            </div>
            <div className="right">
              <EmailOutlinedIcon />
              <MoreVertIcon />
            </div>
          </div>
          <Posts userId={userId} />
        </div>
      </>
    )}
     {openUpdate && <Update setOpenUpdate={setOpenUpdate} user={data} />} 
  </div>
  );
};

export default Profile;