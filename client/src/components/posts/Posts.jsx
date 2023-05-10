import Post from "../post/Post";
import "./posts.scss";
import { makeRequest } from "../../axios";
import { useQuery } from 'react-query'
import { useContext } from "react";
import { AuthContext } from "../../context/authContext";

const Posts = ({userId}) => {

  const {currentUser} = useContext(AuthContext)


  
  const { isLoading, error, data } = useQuery('posts', () =>
  makeRequest.get(`/posts?userId=${userId || ''}&followerId=${currentUser.userId || ''}`)
    .then(res => res.data)
);





  return (
    <div className="posts">
      {error
        ? "Bir şeyler yanlış gitti!"
        : isLoading
        ? "Yükleniyor"
        : data.map((post) => <Post post={post} key={post.id} />)}
    </div>
  );
};

export default Posts;
