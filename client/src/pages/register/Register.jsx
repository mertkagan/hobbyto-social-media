import { Link, useNavigate } from "react-router-dom";
import "./register.scss";
import { useState } from "react";
import { makeRequest } from "../../axios";
import { useFormik } from "formik";
import registerSchema from "../../validation";




const Register = () => {
  
  const[err,setErr] = useState(null);
  const navigate = useNavigate();
  

  const {handleSubmit,handleChange,handleBlur,values,isSubmitting,errors,touched} = useFormik({
    initialValues: {
      name: '',
      surName: '',
      userName:'',
      email:'',
      password:'',
    },
    validationSchema:registerSchema,
    onSubmit: async (values,bag) => {
      try{
           const res = await makeRequest.post("/users/signUp",values)    
            await new Promise((r) => setTimeout(r,1000))
            if(res.data == "Girilen Kullanıcı adı zaten kayıtlı. "){
              return bag.setErrors({userName:"Kullanıcı Adı Zaten Mevcut."})
            }else if(res.data == "Girilen E-posta adresi zaten kayıtlı. ") {
              return bag.setErrors({email:"Email Adresi Zaten Mevcut."})
            }else{
              await new Promise((r) => setTimeout(r,1000))
              bag.resetForm();
              navigate("/login") 
            }
      }catch (e) {
        setErr(err.response.data);       
        }
  },  
});





  return (
    <div className="register">
      <div className="card">
        <div className="left">
          <h1>Hobby To.</h1>
          <p>
          Hobbyto'ya hoş geldiniz!
          Lütfen kayıt olmak için tüm bilgileri doğru ve eksiksiz bir şekilde doldurun. Kaydınızı tamamladıktan sonra, 
          Hobbyto dünyasında yeni insanlarla tanışabilir, ilgi duyduğunuz hobiler hakkında bilgi edinebilir ve kendi
           etkinliklerinizi düzenleyebilirsiniz. Hadi, hemen kayıt olun ve hobilerinize yeni bir boyut katın!
          </p>
          <span>Hesabın var mı?</span>
          <Link to="/login">
          <button>Giriş Yap</button>
          </Link>
        </div>
        <div className="right">
          <h1>Kaydol</h1>
          <form onSubmit={handleSubmit}>
            <input type="text"
             placeholder="Ad"
             name="name"
              value={values.name} 
              onChange={handleChange}  
              disabled={isSubmitting}
              onBlur = {handleBlur}
              style={errors.name && {borderColor:"red"}}
            />
            {errors.name && touched.name && (
              <div className="error">{errors.name}</div>
            )}
            <input 
            type="text" 
            placeholder="Soyad" 
            name="surName" 
            value={values.surName} 
            onChange={handleChange} 
            disabled={isSubmitting}
            onBlur = {handleBlur}
            style={errors.surName && {borderColor:"red"}}
            />
            {errors.surName && touched.surName && (
              <div className="error">{errors.surName}</div>
            )}
            <input 
            type="text" 
            placeholder="Kullanıcı Adı"
            name="userName" 
            value={values.userName} 
            onChange={handleChange} 
            disabled={isSubmitting}
            onBlur = {handleBlur}
            style={errors.userName && {borderColor:"red"}}
            />
            {errors.userName && touched.userName && (
              <div className="error">{errors.userName}</div>
            )}
            <input type="email" 
            placeholder="Email" 
            name="email" 
            value={values.email} 
            onChange={handleChange}
            disabled = {isSubmitting}
            onBlur = {handleBlur}
            style={errors.email && {borderColor:"red"}}
            />
            {errors.email && touched.email && (
              <div className="error">{errors.email}</div>
            )}
            <input 
            type="password" 
            placeholder="Parola" 
            name="password" 
            value={values.password} 
            onChange={handleChange} 
            disabled={isSubmitting}
            onBlur = {handleBlur} 
            style={errors.password && {borderColor:"red"}}
            />
            {errors.password && touched.password && (
              <div className="error">{errors.password}</div>
            )}
            <button type="submit" disabled={isSubmitting}>Kayıt Ol</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;