import "./navbar.scss";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import WbSunnyOutlinedIcon from "@mui/icons-material/WbSunnyOutlined";
import GridViewOutlinedIcon from "@mui/icons-material/GridViewOutlined";
import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import EmailOutlinedIcon from "@mui/icons-material/EmailOutlined";
import PersonOutlinedIcon from "@mui/icons-material/PersonOutlined";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import { Link } from "react-router-dom";
import { useContext } from "react";
import { DarkModeContext } from "../../context/darkModeContext";
import { AuthContext } from "../../context/authContext";
import LogoutOutlinedIcon from '@mui/icons-material/LogoutOutlined';
import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const { toggle, darkMode } = useContext(DarkModeContext);
  const { currentUser,logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.setItem("user",null)
   
  }

  return (
    <div className="navbar">
      <div className="left">
        <Link to="/" style={{ textDecoration: "none" }}>
          <span>hobbyTo</span>
        </Link>
        <HomeOutlinedIcon />
        {darkMode ? (
          <WbSunnyOutlinedIcon onClick={toggle} />
        ) : (
          <DarkModeOutlinedIcon onClick={toggle} />
        )}
        <GridViewOutlinedIcon />
        <div className="search">      
          <input type="text" placeholder="Ara..."  /> 
          <SearchOutlinedIcon />     
        </div>
      </div>
      <div className="right">
        <Link to={`/profile/${currentUser.userId}`} style={{ textDecoration: "none", color: "inherit" } }>
        <PersonOutlinedIcon />
        </Link>      
        {/* <EmailOutlinedIcon  /> */}
        <Link to={"/login"} style={{ textDecoration: "none", color: "inherit" } }>
        <LogoutOutlinedIcon onClick={handleLogout} />
        </Link> 
        <div className="user">
          <img
            src={"/upload/"+currentUser.profilePic}
            alt=""
          />
          <span>{currentUser.userName}</span>
        </div>
      </div>
    </div>
  );
};

export default Navbar;