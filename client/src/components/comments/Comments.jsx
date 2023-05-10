import { useContext, useState } from "react";
import "./comments.scss";
import { AuthContext } from "../../context/authContext";
import { useQuery , useQueryClient,useMutation} from 'react-query'
import { makeRequest } from "../../axios";
import moment from "moment";
import "moment/locale/tr";
import SendIcon from '@mui/icons-material/Send';

const Comments = ({postId}) => {
  const { currentUser } = useContext(AuthContext);
  const[commentText,setCommentText] = useState("");
  //Temporary

  const { isLoading, error, data } = useQuery(['comments'], () =>
  makeRequest.get(`comments?postId=${postId}`).then(res => {
    return res.data;

  })
)
const queryClient = useQueryClient();

const mutation = useMutation(
  (newComment) => {
  return makeRequest.post("/comments/createComment",newComment)
},
 {
  onSuccess: () => {
    // Invalidate and refetch
    queryClient.invalidateQueries("comments")
  },
})

const handleClick = async (e) => {
  e.preventDefault();
  mutation.mutate({commentText,userId:currentUser.userId,postId})
  setCommentText("");
  
}

  return (
    <div className="comments">
      <div className="write">
        <img src={"/upload/"+currentUser.profilePic} alt="" />
        <input type="text" placeholder="bir yorum yazın" value={commentText} onChange={(e) => setCommentText(e.target.value)} />
        <button onClick={handleClick}><SendIcon/></button>
      </div>
      {error
        ? "Something went wrong"
        : isLoading
        ? "loading"
        : data.map((comment) => (
            <div className="comment" key={comment.id}>
              <img src={"/upload/"+comment.userProfilePic} alt="" />
              <div className="info">
                <span>{comment.userName}</span>
                <p>{comment.commentText}</p>
              </div>
              <span className="date">
                {moment(comment.createdAt).locale('tr').fromNow()}
              </span>
            </div>
          ))}
    </div>
  );
};

export default Comments;