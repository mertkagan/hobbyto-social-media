import "./leftBar.scss";
import Friends from "../../assets/1.png";
import Groups from "../../assets/2.png";
import Market from "../../assets/3.png";
import Watch from "../../assets/4.png";
import Memories from "../../assets/5.png";
import profileImage from "../../assets/profileImage.png";
import Gaming from "../../assets/7.png";
import Gallery from "../../assets/8.png";
import Videos from "../../assets/9.png";
import Messages from "../../assets/10.png";
import Tutorials from "../../assets/11.png";
import Courses from "../../assets/12.png";
import Fund from "../../assets/13.png";
import { AuthContext } from "../../context/authContext";
import { useContext } from "react";
import { Link } from "react-router-dom";

const LeftBar = () => {

  const { currentUser } = useContext(AuthContext);

  return (
    <div className="leftBar">
      <div className="container">
        <div className="menu">
          <div className="user">
            <img
              src={"/upload/"+currentUser.profilePic}
              alt=""
            />
            <Link to={`/profile/${currentUser.userId}`} style={{ textDecoration: "none", color: "inherit" }}>
            <span>{currentUser.userName}</span>
            </Link>
          </div>
          <div className="item">
            <img src={Friends} alt="" />
            <span>Arkadaşlar</span>
          </div>
          <div className="item">
            <img src={Groups} alt="" />
            <span>Gruplar</span>
          </div>
          <div className="item">
            <img src={Market} alt="" />
            <span>Store</span>
          </div>
          <div className="item">
            <img src={Watch} alt="" />
            <span>İzle</span>
          </div>
          <div className="item">
            <img src={Memories} alt="" />
            <span>Hatıralar</span>
          </div>
        </div>
        <hr />
        <div className="menu">
          <span>Kısayollarınız</span>
          {/* <div className="item">
            <img src={Events} alt="" />
            <span>Events</span>
          </div> */}
          <div className="item">
            <img src={Gaming} alt="" />
            <span>Oyun</span>
          </div>
          <div className="item">
            <img src={Gallery} alt="" />
            <span>Galeri</span>
          </div>
          <div className="item">
            <img src={Videos} alt="" />
            <span>Videolar</span>
          </div>
          <div className="item">
            <img src={Messages} alt="" />
            <span>Mesajlar</span>
          </div>
        </div>
        <hr />
        <div className="menu">
          <span>Diğerleri</span>
          <div className="item">
            <img src={Fund} alt="" />
            <span>Bağış</span>
          </div>
          <div className="item">
            <img src={Tutorials} alt="" />
            <span>Öğreticiler</span>
          </div>
          <div className="item">
            <img src={Courses} alt="" />
            <span>Kurslar</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LeftBar;