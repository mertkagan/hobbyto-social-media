import "./post.scss";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteOutlinedIcon from "@mui/icons-material/FavoriteOutlined";
import TextsmsOutlinedIcon from "@mui/icons-material/TextsmsOutlined";
import PersonAddOutlinedIcon from '@mui/icons-material/PersonAddOutlined';
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { Link } from "react-router-dom";
import Comments from "../comments/Comments";
import { useContext, useState } from "react";
import moment from "moment";
import "moment/locale/tr";
import { makeRequest } from "../../axios";
import { useQuery ,useMutation,useQueryClient} from 'react-query'
import { AuthContext } from "../../context/authContext";
import verify from "../../assets/verify.png";


const Post = ({ post }) => {
  const [commentOpen, setCommentOpen] = useState(false);
  const{currentUser} = useContext(AuthContext);
  const [menuOpen, setMenuOpen] = useState(false);

  const eventDate = new Date(post.eventDate);
  const date = { year: 'numeric', month: 'long', day: 'numeric', timeZone: 'Europe/Istanbul' };
  const formattedEventDate = eventDate.toLocaleDateString('tr-TR', date);


  const { isLoading, error, data } = useQuery(['likes',post.id], () =>
     makeRequest.get("/likes?postId="+post.id).then(res => {
       return res.data;
     })
  )

  const { isLoading : isLoadingAttendee, error : errorAttendee, data : dataAttendee} = useQuery(['attendees',post.id], () =>
  makeRequest.get("/attendees?postId="+post.id).then(res => {
    return res.data;
  })
)



  const queryClient = useQueryClient();

  const mutation = useMutation(
    (liked) => {
    if(liked) return makeRequest.delete(`/likes/delete?postId=${post.id}&userId=${currentUser.userId}`);
    return   makeRequest.post("/likes",{postId:post.id,userId:currentUser.userId});
  }, {
    onSuccess: () => {
      // Invalidate and refetch
      queryClient.invalidateQueries("likes")
    },
  })

  const deleteMutation = useMutation(
    (postId) => {
      return makeRequest.delete("/posts/delete?postId=" + post.id);
    },
    {
      onSuccess: () => {
        // Invalidate and refetch
        queryClient.invalidateQueries(["posts"]);
      },
    }
  );


  const queryClientAttendee = useQueryClient();

  const mutationAttendee = useMutation(
    (attendeed) => {
    if(attendeed) return makeRequest.delete(`/attendees/delete?postId=${post.id}&userId=${currentUser.userId}`);
    return   makeRequest.post("/attendees/createAttendee",{postId:post.id,userId:currentUser.userId});
  }, {
    onSuccess: () => {
      // Invalidate and refetch
      queryClientAttendee.invalidateQueries("attendees")
    },
  })

  const handleLike = () => {
    mutation.mutate(data.includes(currentUser.userId))
  }

  const handleAttendee = () => {
    mutationAttendee.mutate(dataAttendee.includes(currentUser.userId))
  }

  const handleDelete = () => {
    deleteMutation.mutate(post.id);
  };

  

  return (
    <div className="post">
      <div className="container">
        <div className="user">
          <div className="userInfo">
            <img src={"/upload/"+post.profilePic /*|| "../upload/"+post.profilePic*/ } alt="" />
            <div className="details">
              <Link
                to={`/profile/${post.userId}`}
                style={{ textDecoration: "none", color: "inherit" }}
              >
                <span className="name">{post.userUserName}</span>
              </Link>
              <span className="date">{moment(post.creationDate).locale('tr').fromNow()}</span>
            </div>
          </div>
          <MoreHorizIcon onClick={() => setMenuOpen(!menuOpen)} />
          {menuOpen && post.userId === currentUser.userId && (
            <button onClick={handleDelete}>Gönderiyi Sil</button>
          )}
        </div>
        <div className="content">
          <h2 style={{textAlign:"center"}}>{post.title}</h2>
          <p>Tarih: {formattedEventDate}  ({moment(post.eventDate).locale('tr').fromNow()})</p>
          <p>Şehir:{post.cityName}</p>
          <br/>
          <p>{post.text}</p>
          <img src={"/upload/"+post.img} alt="" />
        </div>
        <div className="info">
          <div className="item">
          {isLoading ? (
              "loading"
            ) : data.includes(currentUser.userId) ? (
              <FavoriteOutlinedIcon
                style={{ color: "red" }}
                onClick={handleLike}
              />
            ) : (
              <FavoriteBorderOutlinedIcon onClick={handleLike} />
            )}
            {data?.length} Beğeni
          </div>
          <div className="item" onClick={() => setCommentOpen(!commentOpen)}>
            <TextsmsOutlinedIcon />
              Yorumlar
          </div>
          <div className="item">
          {isLoadingAttendee ? (
              "loading"
            ) : dataAttendee.includes(currentUser.userId) ? (
              <PersonAddOutlinedIcon
                style={{ color: "green" }}
                onClick={handleAttendee}
              />
            ) : (
              <PersonAddOutlinedIcon onClick={handleAttendee} />
            )}
            {dataAttendee?.length} Katılımcı
          </div>
        </div>
        {commentOpen && <Comments postId= {post.id} />}
      </div>
    </div>
  );
};

export default Post;