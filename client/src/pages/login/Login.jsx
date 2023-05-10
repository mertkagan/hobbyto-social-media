import { useContext,useState } from "react";
import { Link ,useNavigate} from "react-router-dom";
import { AuthContext } from "../../context/authContext";
import "./login.scss";
import { useFormik } from "formik";
import { loginSchema } from "../../validation";
import { makeRequest } from "../../axios";



const Login = () => {



  const[err,setErr] = useState(null);
  
  
  var navigate = useNavigate();

  const { login } = useContext(AuthContext);


  const {handleSubmit,handleChange,handleBlur,values,isSubmitting,errors,touched} = useFormik({
    initialValues: {
      userName: '',
      password: '',
    },
    validationSchema:loginSchema,
    onSubmit: async (values,bag) => {  

          const res =  await makeRequest.post("users/login",values).then((res) => {
            if(res.data.message == "Password Not Match"){
              alert("Şifre Yanlış")
            }else if(res.data.message == "Username not exits"){
              alert("Kullanıcı Adı Geçersiz")
            }else if (res.data.message == "Login Failed"){
              alert("Giriş Başarısız")
            }else{  
              login(values)
              navigate("/");
              bag.resetForm();
              
            }
          })
            
     

  }});



  return (
    
    <div className="login">
      <div className="card">
        <div className="left">
          <h1>Hobby To</h1>
          <p>
          Herkese merhaba! Hobbyto'ya hoş geldiniz, burası hobilerinizi paylaşabileceğiniz ve yeni 
          etkinlikler keşfedebileceğiniz bir sosyal medya platformu. Burada sizin gibi ilgi duyan 
          insanlarla tanışabilir, yeni deneyimler yaşayabilir ve hobileriniz hakkında bilgi edinebilirsiniz.
           Ayrıca siz de kendi etkinliklerinizi düzenleyebilir, diğer kullanıcılarla paylaşabilir ve 
           onlarla birlikte keyifli zamanlar geçirebilirsiniz. Hobbyto'ya katılmak için hemen 
           hesap oluşturun ve hobilerinize yeni bir boyut katın!
          </p>
          {/* <span>Don't you have an account?</span> */}
          <Link to="/register">
            <button>Kaydol</button>
          </Link>
        </div>
        <div className="right">
          <h1>Giriş Yap</h1>
          <form onSubmit={handleSubmit}>
            <input type="text" placeholder="Kullanıcı Adı" name="userName"
                onChange={handleChange}
                value={values.userName} 
                disabled={isSubmitting} 
                onBlur = {handleBlur}        
            />
            {errors.userName && touched.userName && (<div style={{color:"red",fontSize:15}}>{errors.userName}</div>)}
            <input type="password" placeholder="Parola" name="password" 
                 onChange={handleChange}
                 value={values.password}
                 disabled={isSubmitting}  
                 onBlur = {handleBlur}            
            />
            {errors.password && touched.password && (<div style={{color:"red",fontSize:15}}>{errors.password}</div>)}
            {err && err}
            <button type="submit" disabled={isSubmitting}>Giriş</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;