import { object, string} from 'yup';

const registerSchema = object({
    name: string().required("İsim Alanı Boş Geçilemez."),
    surName: string().required("Soyad Alanı Boş Geçilemez."),
    userName: string().required("Kullanıcı Adı Alanı Boş Geçilemez."),
    email: string().email("Lütfen Geçerli Bir Mail Adresi Girin").required("Email Alanı Boş Geçilemez."),
    password: string().min(5,"Parola Minimum 5 Karakter Olmalıdır.").required("Parola Alanı Boş Geçilemez.")
  });

  export default registerSchema;

  export const loginSchema = object({
    userName: string().required("Lütfen Kullanıcı Adınızı Girin."),
    password: string().required("Lütfen Parolanızı Girin.")
  });

